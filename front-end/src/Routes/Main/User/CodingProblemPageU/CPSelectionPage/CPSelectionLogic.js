import {
  allProblems,
  currentState,
  navigate,
  setCurrentState,
  setSelectProblems
} from "./CPSelectionPage";
import {fetchFromDatabase, updateModelAttribute} from "../../../../../Helpers/Functions";
import { COFFEE_CODER_DB_URL } from "../../../../../Data/Urls";
import {toast} from "react-toastify";
import {trackPromise} from "react-promise-tracker";

// Return whether the user id exists within the list of user coding problems
export const hasUserAttempted = (problem) => {
  const userCodingProblems = problem.userCodingProblems;

  for (const index in userCodingProblems) {
    if (userCodingProblems[index].userId === currentState.account.csulbId) {
      return true;
    }
  }
  return false;
};

// Return whether the user exists within the list of users solved
export const hasUserSolved = (problem) => {
  const userCodingProblems = problem.userCodingProblems;

  for (const index in userCodingProblems) {
    if (userCodingProblems[index].userId === currentState.account.csulbId && userCodingProblems[index].solved) {
      return true;
    }
  }
  return false;
}

// onChange handler for the difficulty dropdown selection bar
export const handleSelectChange = (event) => {
  setCurrentState({
    ...currentState,
    selectedDifficulty: event.target.value,
    showSelectedProblem: false
  });

  let newProblems;

  if (event.target.value === "All problems") {
    newProblems = allProblems.current;
  } else {
    // Using the set of all problems, filter only the problems of the selected difficulty
    newProblems = allProblems.current.filter((problem) =>
      problem.difficultyCategory === event.target.value);

  }
  setSelectProblems(newProblems);
}

// onClick handler for the StyledP elements representing the titles of the matched coding problems
export const handleTextClick = (problem) => {
  setCurrentState({
    ...currentState,
    currentProblem: problem,
    showSelectedProblem: true
  });
}

// onClick handler for changing a user's current coding problem
export const handleConfirm = () => {
  console.log(currentState.currentProblem);

  trackPromise(
    addUserCodingProblem(currentState.account.csulbId, currentState.currentProblem.problemId)
  ).catch();
}

async function addUserCodingProblem(csulbId, problemId) {
  let result = await fetchFromDatabase(COFFEE_CODER_DB_URL +
    "/user_coding_problem/addUserCodingProblem?csulbId=" + csulbId + "&problemId=" + problemId, "POST");

  if (result === 0) {
    result = await updateModelAttribute(COFFEE_CODER_DB_URL, "user",
      "csulbId", currentState.account.csulbId, "problemId", currentState.currentProblem.problemId);

    if (result.currentProblemId === currentState.currentProblem.problemId) {
      navigate("/CurrentCodingProblem", {
        state: {
          account: {...currentState.account},
          problem: {...currentState.currentProblem},
          changedProblem: true
        }
      });
    }
  }

  showChangeErrorToast();
}

const showChangeErrorToast = () => {
  toast.error("Error has occurred.", {
    toastId: "toast-error",
    position: "top-center",
    theme: "colored",
    autoClose: 5000,
    hideProgressBar: true,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true,
  });
}

