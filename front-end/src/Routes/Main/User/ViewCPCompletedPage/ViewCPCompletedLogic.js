
import {
  allProblems,
  currentState,
  setCurrentState,
  setSelectProblems,
  solvedProblems,
  unsolvedProblems
} from "./ViewCPCompletedPage";
import {fetchFromDatabase} from "../../../../Helpers/Functions";
import {COFFEE_CODER_DB_URL} from "../../../../Data/Urls";

export const handleSelectChange = (event) => {
  setCurrentState({
    ...currentState,
    selectedDifficulty: event.target.value
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
  solvedProblems.current = getSolvedProblems(newProblems);
  unsolvedProblems.current = getUnsolvedProblems(newProblems);
}

export const hasUserSolved = (problem) => {
  const userCodingProblems = problem.userCodingProblems;

  for (const index in userCodingProblems) {
    if (userCodingProblems[index].userId === currentState.account.csulbId && userCodingProblems[index].solved) {
      return true;
    }
  }
  return false;
}

export const getSolvedProblems = (problems) => {
  return problems.filter((problem) => hasUserSolved(problem));
}

export const getUnsolvedProblems = (problems) => {
  return problems.filter((problem) => !hasUserSolved(problem));
}

export async function getSelectedProblems(difficulty) {
  return await fetchFromDatabase(COFFEE_CODER_DB_URL + "/coding_problem/getProblemsByDifficulty?difficulty=" + difficulty);
}
