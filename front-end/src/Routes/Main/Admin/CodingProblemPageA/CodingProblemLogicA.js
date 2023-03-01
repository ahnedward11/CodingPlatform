
import "./style.css";
import {currentState, setCurrentState, code, setCode, allProblems, setSelectProblems} from "./CodingProblemPageA";
import { fetchFromDatabase } from "../../../../Helpers/Functions";
import { COFFEE_CODER_DB_URL } from "../../../../Data/Urls";
import { trackPromise } from "react-promise-tracker";
import { toast } from "react-toastify";

export async function getSelectedProblems(difficulty) {
  return await fetchFromDatabase(COFFEE_CODER_DB_URL + "/coding_problem/getProblemsByDifficulty?difficulty=" + difficulty);
}

// Return the number of users that attempted input the problem
export function getNumOfUsersAttempted(problem) {
  return problem.userCodingProblems.length;
}

// Return the number of users that solved the input problem
export function getNumOfUsersSolved(problem) {
  return problem.userCodingProblems.filter(
    (userCodingProblem) => userCodingProblem.solved).length;
}

export function problemHasSolution(problem) {
  return problem.problemSolutions.length > 0;
}

// onChange handler for the problem difficulty dropdown menu
export const handleSelectChange = (event) => {
  setCurrentState({
    ...currentState,
    showSelectedProblem: false,
    selectedDifficulty: event.target.value,
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

// onClick handler for showing the list of coding problems
export const handleShowSelection = () => {
  setCurrentState({
    ...currentState,
    showSelection: !currentState.showSelection
  });
}

// onClick handler for changing the currently displayed coding problem
export async function handleChangeProblem(problem) {
  trackPromise(
    fetchFromDatabase(COFFEE_CODER_DB_URL +
    "/coding_problem_solution/getProblemSolution?problemId=" + problem.problemId)
    .then((result) => {
      setCurrentState({
        ...currentState,
        currentProblem: problem,
        solutionCode: result.solutionCode,
        showSelection: !currentState.showSelection
      });
      setCode((result.solutionCode !== "") ?
        result.solutionCode :
        problem.codeContent);
    })).catch();
}

// onChange handler for updating the code within the code editor
export const handleCodeChange = (event) => {
  setCode(event);
}

// onSubmit handler for posting the solution for the current coding problem
export const handlePostSolution = (event) => {
  event.preventDefault();

  trackPromise(
    addProblemSolution()
  ).catch();
}

async function addProblemSolution() {
  let problemSolution = {
    problemDescription: currentState.currentProblem.problemDescription,
    solutionCode: code,
    solutionOutput: "",
    codingProblem: currentState.currentProblem,
    admin: await fetchFromDatabase(COFFEE_CODER_DB_URL +
      "/admin/getByCsulbId?csulbId=" + currentState.account.csulbId)
  };

  return await fetch(COFFEE_CODER_DB_URL + "/coding_problem_solution/add", {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(problemSolution)

  }).then(result => result.json())
    .then(result => {
      if (result === 0) {
        showCreationSuccessToast("Solution has been posted.");
      } else {
        showCreationErrorToast("Error has occurred.");
      }
    });
}

const showCreationSuccessToast = (message) => {
  toast.success(message, {
    toastId: message.toString(),
    position: "top-center",
    theme: "colored",
    autoClose: 5_000,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true
  });
}

const showCreationErrorToast = (errorMessage) => {
  toast.error(errorMessage, {
    toastId: errorMessage.toString(),
    position: "top-center",
    theme: "colored",
    autoClose: 5_000,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true
  });
}
