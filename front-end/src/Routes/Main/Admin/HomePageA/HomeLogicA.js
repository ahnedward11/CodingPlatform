
import {numOfUsers} from "./HomePageA";

// Return the number of users that attempted input the problem
function getNumOfUsersAttempted(problem) {
  return problem.userCodingProblems.length;
}

// Return the number of users that solved the input problem
function getNumOfUsersSolved(problem) {
  return problem.userCodingProblems.filter(
    (userCodingProblem) => userCodingProblem.solved).length;
}

// Return the number of users that did not attempt the input problem
function getNumOfUsersNotAttempted(problem) {
  return numOfUsers - getNumOfUsersAttempted(problem);
}

// Return the percent of users that attempted the input problem
export function getPercentUsersAttempted(problem) {
  if (numOfUsers === 0) {
    return 0;
  }

  return (getNumOfUsersAttempted(problem) / numOfUsers) * 100.0;
}

// Return the percent of users that solved the input problem
export function getPercentUsersSolved(problem) {
  if (numOfUsers === 0) {
    return 0;
  }

  return (getNumOfUsersSolved(problem) / numOfUsers) * 100.0;
}

// Return the percent of users that did not attempt the input problem
export function getPercentUsersNotAttempted(problem) {
  if (numOfUsers === 0) {
    return 0;
  }

  return (getNumOfUsersNotAttempted(problem) / numOfUsers) * 100.0;
}
