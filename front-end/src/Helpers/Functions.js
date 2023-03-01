
import problems from "../Data/CodingProblems";
import users from "../Data/Accounts";
import reports from "../Data/Reports";
import cpSolutions from "../Data/CodingProblemSolutions";
import { COFFEE_CODER_DB_URL } from "../Data/Urls";

/**
 * Given a base database url, builds the rest of the url fetch request using the provided arguments
 * and returns the resulting attribute as a JSON object.
 * @param databaseUrl the url of the database to fetch data from
 * @param table the table mapping reference of the database
 * @param filter the key mapping reference of the database table
 * @param filterValue the filter value to apply as an argument to the controller (will be null if not applicable)
 * @returns {Promise<any>} the promise state of the fetch request
 */
export async function getModelAttribute(databaseUrl, table, filter, filterValue) {
  let url = (filterValue === null) ?
    (databaseUrl + "/" + table + "/get" + filter.charAt(0).toUpperCase() + filter.substring(1)) :
    (databaseUrl + "/" + table + ("/getBy" + filter.charAt(0).toUpperCase() + filter.substring(1)) +
      "?" + filter + "=" + filterValue);

  return await fetchFromDatabase(url, "GET");
}

export async function getUpdatedAccount(account) {
    return await getModelAttribute(COFFEE_CODER_DB_URL, account.role.toLowerCase(), "csulbId", account.csulbId);
}

export async function getCodingProblem(problemId) {
    return await getModelAttribute(COFFEE_CODER_DB_URL, "coding_problem", "problemId", problemId);
}

export async function getSelectedProblems(difficulty) {
  return await fetchFromDatabase(COFFEE_CODER_DB_URL + "/coding_problem/getProblemsByDifficulty?difficulty=" + difficulty);
}

export async function getCodingProblemSolution(solutionId)  {
    return await getModelAttribute(COFFEE_CODER_DB_URL, "coding_problem_solution", "solutionId", solutionId);
}

/**
 * Returns a JSON object result from the url fetch request.
 * @param url the database url fetch request
 * @param method the fetch method (e.g. GET, POST, PUT, etc.)
 * @returns {Promise<any>} the promise state of the fetch request
 */
export async function fetchFromDatabase(url, method) {
  const response = await fetch(url, {
    method: method,
    headers: {
      "Content-Type": "application/json"}
  });

  return await response.json();
}

/**
 * Updates the current state with the database's current data.
 * SetState calls are asynchronous; we must set the current state values after fetching data
 * to ensure that the values are updated by the time we try to access them in other synchronous components.
 * @param url the database url to fetch data from
 * @param account the potentially outdated account to fetch data for
 * @returns {Promise<{[p: string]: *}>} the combined states of the account and problem as a single JSON object
 */
export async function fetchAdminData(url, account) {
  const accountResponse = await getModelAttribute(url, account.role.toLowerCase(),
    "csulbId", account.csulbId);

  return {account: {...accountResponse}};
}

/**
 * Updates the current state with the database's current data.
 * SetState calls are asynchronous; we must set the current state values after fetching data
 * to ensure that the values are updated by the time we try to access them in other synchronous components.
 * @param url the database url to fetch data from
 * @param account the potentially outdated account to fetch data for
 * @returns {Promise<{[p: string]: *}>} the combined states of the account and problem as a single JSON object
 */
export async function fetchUserData(url, account) {
  const accountResponse = await getModelAttribute(url, account.role.toLowerCase(),
      "csulbId", account.csulbId);
  const problemResponse = await getCodingProblem(accountResponse.currentProblemId);

  return {account: {...accountResponse}, problem: {...problemResponse}};
}

/**
 * Given a base database url, builds the rest of the url fetch request using the provided arguments
 * and updates the row entry with a single new value.
 * @param databaseUrl the url of the database to fetch data from
 * @param table the table mapping reference of the database
 * @param filter the key mapping reference the database table
 * @param filterValue the row filter to apply as an argument to the controller (will be null if not applicable)
 * @param attribute the attribute to access from the row
 * @param newValue the row entry's updated value
 * @returns {Promise<any>} the promise state of the fetch request
 */
export async function updateModelAttribute(databaseUrl, table, filter, filterValue, attribute, newValue) {
  let url = (databaseUrl + "/" + table + "/update" + attribute.charAt(0).toUpperCase() + attribute.substring(1) +
    "?" + filter + "=" + filterValue + "&" + attribute + "=" + newValue);

  return await fetchFromDatabase(url, "PATCH");
}

export const getUser = (csulbID) => {
  return(
    users.filter((user) => (user.csulbID === csulbID))[0]
  );
};

// Return an array of users whose profile name, csulbID, or email matches the given search text
export const getUsers = (searchText, userArray) => {
  return (
    userArray.filter((user) =>
      (user.profileName.toLowerCase().includes(searchText.toLowerCase()) ||
        user.email.toLowerCase().includes(searchText.toLowerCase()) ||
        user.csulbId.toString().includes(searchText))
    )
  );
};

export const getNotes = (searchText) => {
  return (
      users.filter((note) =>
          (note.noteId.includes(searchText) ||
              note.noteTitle.toLowerCase().includes(searchText) ||
              note.dateCreated.includes(searchText))
      )
  );
};

// Get a coding problem based on its name
export const getCodingProblemData = (problemName) => {
  // Get and return the coding problem we are searching for
  return problems.filter(problem => problem.name === problemName)[0];
};

// Return an array of all the reports
export const getReports = () => {
  return reports;
};

// Return the user associated with the given report
export const getReporter = (report) => {
  return users.filter((user) => user.csulbID === report.reporterID)[0].profileName
};

// Return the solution code for the current problem
export const getSolutionCode = (problem) => {
  return cpSolutions.get(problem.name).code
}
