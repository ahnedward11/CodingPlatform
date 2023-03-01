
import 'font-awesome/css/font-awesome.min.css';
import React, { useEffect, useRef, useState } from "react";
import { useLocation } from "react-router";
import { ToastContainer, Zoom } from "react-toastify";
import Navbar from "../../../../Navigation/Navbar";
import {
  getSelectedProblems,
  getSolvedProblems,
  getUnsolvedProblems,
  handleSelectChange,
  hasUserSolved
} from "./ViewCPCompletedLogic";
import { fetchUserData } from "../../../../Helpers/Functions";
import users from "../../../../Data/Accounts";
import { COFFEE_CODER_DB_URL } from "../../../../Data/Urls";
import { trackPromise } from "react-promise-tracker";
import DisplayLoadingIndicator from "../../../../Components/DisplayLoadingIndicator";

export let account;
export let currentState, setCurrentState;
export let allProblems, solvedProblems, unsolvedProblems;
export let selectProblems, setSelectProblems;

/**
 * The Problems page allows the user to view and select their available coding problems.
 */
const ViewCPCompletedPage = () => {
    account = users[0];
    [currentState, setCurrentState] = useState({
      contentLoaded: false,
      account: useLocation().state.account,
      currentProblem: useLocation().state.problem,
      selectedDifficulty: "All problems",
      showSelection: true,
    });
  allProblems = useRef([]);
  solvedProblems = useRef([]);
  unsolvedProblems = useRef([]);
  [selectProblems, setSelectProblems] = useState(allProblems.current);

    const DisplaySelection = () => {
        return (
            <div id={"management-container"}>
                <div className={"account-management-container"}>
                    <label htmlFor={"select-difficulty"} style={{width: "40%", fontSize: "1.15rem", fontWeight: "bolder"}}>Coding Problems Completed:</label>
                    <select name={"select-difficulty"}
                            className={"input-field"}
                            style={{width: "40%", border: "3px solid #666666", borderRadius: "0", fontSize: "1rem"}}
                            onChange={handleSelectChange}
                            defaultValue={currentState.selectedDifficulty}>
                        <option value={"All problems"}>All problems</option>
                        <option value={"Easy"}>Easy</option>
                        <option value={"Medium"}>Medium</option>
                        <option value={"Hard"}>Hard</option>
                    </select>
                </div>
                <div className={"account-management-container"} style={{width: "100%", display: "block", textAlign: "left"}}>
                    {/* Flexibly render all accounts that match our search criteria */}
                    <DisplaySelectionResults />
                </div>
                <div className={"account-management-container"} style={{display: "block", textAlign: "left"}}>
                    {/* Flexibly render all accounts that match our search criteria */}
                </div>
            </div>
        );
    }

    const DisplaySelectionResults = () => {
        return (
            <>
                <div className={"account-management-container"}
                     style={{width: "100%", textAlign: "left", borderBottom: "2px solid black", paddingBottom: "0.5em",
                         marginBottom: "0.5em"}}>
                    <h3 className={"management-column"}
                        style={{width: "40%", fontSize: "1.15rem", fontWeight: "bold"}}>Name</h3>
                    <h3 className={"management-column"}
                        style={{width: "20%", fontSize: "1.15rem", fontWeight: "bold"}}>Solved</h3>
                    <h3 className={"management-column"}
                        style={{width: "20%", fontSize: "1.15rem", fontWeight: "bold"}}>Difficulty</h3>
                </div>
                <DisplayMatchingProblems />
            </>
        );
    }

    const DisplayMatchingProblems = () => {
      let rows = [];

      // Add the solved problems matching the chosen difficulty
      rows.push((solvedProblems.current.length > 0) ?
        solvedProblems.current.map((problem) => {
          return (
            <div style={{display: "flex", flexDirection: "row", width: "100%"}}>
              <div className={"management-column"}
                   style={{width: "40%"}}>
                {problem.problemName}
              </div>
              <p className={"management-column"}
                 style={{width: "20%"}}>
                {hasUserSolved(problem) ?
                  "Yes" :
                  "No"}</p>
              <p className={"management-column"}
                 style={{width: "25%"}}>{problem.difficultyCategory}</p>
            </div>)
        }) :
        (currentState.contentLoaded) ?
          <p style={{color: "var(--light-gray)", margin: "0.75em 0.25em 0.25em 0.25em"}}>
            <i>(No solved problems found)</i>
          </p> :
          <></>);

      rows.push(
        currentState.contentLoaded &&
        <>
          <br /><hr style={{width: "100%", border: "1px dashed var(--light-gray)"}} /><br />
        </>);

      // Add the unsolved problems matching the chosen difficulty
      rows.push((unsolvedProblems.current.length > 0) ?
        unsolvedProblems.current.map((problem) => {
          return (
            <div style={{display: "flex", flexDirection: "row", width: "100%"}}>
              <div className={"management-column"}
                   style={{width: "40%"}}>
                {problem.problemName}
              </div>
              <p className={"management-column"}
                 style={{width: "20%"}}>
                {hasUserSolved(problem) ?
                  "Yes" :
                  "No"}</p>
              <p className={"management-column"}
                 style={{width: "25%"}}>{problem.difficultyCategory}</p>
            </div>)
        }) :
        (currentState.contentLoaded) ?
          <p style={{color: "var(--light-gray)", margin: "0.25em 0.25em 0em 0.25em"}}>
            <i>(No unsolved problems found)</i>
          </p> :
          <></>);

      return (<>{rows}</>);
    }

  useEffect(() => {
    async function fetchData() {
      await fetchUserData(COFFEE_CODER_DB_URL, currentState.account)
        .then(async (result) => {
          let problems = await getSelectedProblems("All problems");
          allProblems.current = problems;
          solvedProblems.current = getSolvedProblems(problems);
          unsolvedProblems.current = getUnsolvedProblems(problems);
          setSelectProblems(problems);

          setCurrentState({
            contentLoaded: true,
            account: result.account,
            currentProblem: result.problem
          });
        });

    }

    trackPromise(
      fetchData()
    ).catch();
  }, []);

    return (
        <div>
          {/* Re-use Navbar component for readability */}
          <Navbar pageName={"View Coding Problems Completed"}
                  tabName={"View Coding Problems Completed"}
                  state={{
                    account: {...currentState.account},
                    problem: {...currentState.currentProblem}}} />
          <main id={"main-container"} style={{display: "block", paddingTop: "20"}}>
            {<DisplaySelection />}
          </main>
          <DisplayLoadingIndicator />
          <ToastContainer transition={Zoom} />
        </div>
    );
}

export default ViewCPCompletedPage;
