import 'font-awesome/css/font-awesome.min.css';
import React, {useEffect, useRef, useState} from "react";
import {useLocation} from "react-router";
import {ToastContainer, Zoom} from "react-toastify";
import styled from "styled-components";
import Navbar from "../../../../Navigation/Navbar";
import DisplayProblem from "../../../../Components/DisplayProblem";
import DisplayCodeEditor from "../../../../Components/DisplayCodeEditor";
import {
  getNumOfUsersAttempted, getNumOfUsersSolved,
  getSelectedProblems,
  handleChangeProblem,
  handleCodeChange,
  handlePostSolution,
  handleSelectChange,
  handleShowSelection, problemHasSolution
} from "./CodingProblemLogicA";
import { fetchFromDatabase } from "../../../../Helpers/Functions";
import { trackPromise } from "react-promise-tracker";
import { COFFEE_CODER_DB_URL } from "../../../../Data/Urls";
import DisplayLoadingIndicator from "../../../../Components/DisplayLoadingIndicator";

const StyledParagraph = styled.p `
  width: fit-content;
  text-decoration: underline;
  cursor: pointer;
  margin: 0;
  transition: 0.1s color ease-in;
  
  &:hover {
    color: var(--light-gray);
  }
`;

export let currentState, setCurrentState;
export let code, setCode;
export let allProblems;
export let selectProblems, setSelectProblems;

/**
 * The Problems page allows the user to view and select their available coding problems.
 */
const CodingProblemPageA = () => {
  [currentState, setCurrentState] = useState({
    contentLoaded: false,
    account: useLocation().state.account,
    currentProblem: null,
    solutionCode: "",
    selectedDifficulty: "All problems",
    showSelection: true
  });
  [code, setCode] = useState("");
  allProblems = useRef([]);
  [selectProblems, setSelectProblems] = useState(allProblems.current);

  const DisplaySelection = () => {
    return (
      <div id={"management-container"}>
        <div className={"account-management-container"}>
          <label htmlFor={"select-difficulty"}
                 style={{textAlign: "left", width: "40%", fontSize: "1.15rem", fontWeight: "bolder"}}>
            Coding Problems:</label>
          <select name={"select-difficulty"}
                  className={"input-field"}
                  style={{width: "40%", border: "3px solid #666666", borderRadius: "0", outline: "none", fontSize: "1rem"}}
                  onChange={handleSelectChange}
                  defaultValue={currentState.selectedDifficulty}>
            <option value={"All problems"}>All problems</option>
            <option value={"Easy"}>Easy</option>
            <option value={"Medium"}>Medium</option>
            <option value={"Hard"}>Hard</option>
          </select>
        </div>
        <div className={"account-management-container"}
             style={{textAlign: "left", borderBottom: "2px solid black", paddingBottom: "0.5em", marginBottom: "0.5em"}}>
          <h3 className={"management-column"}
              style={{width: "40%", fontSize: "1.15rem", fontWeight: "bold"}}>Name</h3>
          <h3 className={"management-column hide-on-media"}
              style={{width: "20%", fontSize: "1.15rem", fontWeight: "bold"}}>Users Attempted</h3>
          <h3 className={"management-column"}
              style={{width: "20%", fontSize: "1.15rem", fontWeight: "bold"}}>Users Solved</h3>
          <h3 className={"management-column"}
              style={{width: "20%", fontSize: "1.15rem", fontWeight: "bold"}}>Solution Available</h3>
        </div>
        <div className={"account-management-container"} style={{display: "block", textAlign: "left"}}>
          {/* Flexibly render all accounts that match our search criteria */}
          <DisplayMatchingProblems />
        </div>
      </div>
    );
  }

  const DisplayMatchingProblems = () => {
    return selectProblems.map((problem) => {
      return (
        <div style={{display: "flex", flexDirection: "row", width: "100%"}}>
          <div className={"management-column"}
               style={{width: "40%"}}>
            <StyledParagraph onClick={() => handleChangeProblem(problem)}>
              {problem.problemName}
            </StyledParagraph>
          </div>
          <p id={"management-id"}
             className={"management-column hide-on-media"}
             style={{width: "20%"}}>{getNumOfUsersAttempted(problem)}</p>
          <p className={"management-column"}
             style={{width: "20%"}}>{getNumOfUsersSolved(problem)}</p>
          <p style={{width: "20%", margin: "0"}}>
            {problemHasSolution(problem) ?
              "Yes" :
              "No"}
          </p>
        </div>)
    })
  }

  useEffect(() => {
    async function fetchData() {
      let result = await getSelectedProblems("All problems");
      allProblems.current = result;
      setSelectProblems(result);

      await fetchFromDatabase(COFFEE_CODER_DB_URL +
        "/coding_problem_solution/getProblemSolution?problemId=" + currentState.currentProblem.problemId)
        .then((result) => {
          setCurrentState({
            ...currentState,
            currentProblem: allProblems[0],
            solutionCode: result.solutionCode
          });
          setCode(result.solutionCode);
        });
    }

    trackPromise(
      fetchData()
    ).catch();
  }, []);

  return (
    <div>
      {/* Re-use Navbar component for readability */}
      <Navbar pageName={"Edit Problems"}
              tabName={"Problems"}
              state={{account: {...currentState.account}}} />
      <main id={"main-container"} style={{display: "block", paddingTop: "0"}}>
        <button id={"page-button"}
                className={"button-tab"}
                style={{width: "75px", fontSize: "1.15rem", cursor: "pointer", padding: "0.5em", marginTop: "1em"}}
                onClick={handleShowSelection}>
          <i className={"fa-solid fa-arrow-right-arrow-left"}></i></button>
        {currentState.showSelection ?
          <DisplaySelection /> :
          <div id={"problems-container"}
               style={{paddingTop: "1em"}}>
            <DisplayProblem
              headerText={"Coding Problem Selected: " + currentState.currentProblem.problemName}
              problemDescription={currentState.currentProblem.problemDescription}
              examples={currentState.currentProblem.problemExamples} />
            <DisplayCodeEditor
              programmingLanguage={"Java"}
              handleSubmit={handlePostSolution}
              handleCodeChange={handleCodeChange}
              codeDefaultValue={code}
              buttonText={"Post Solution"} />
          </div>}
        <DisplayLoadingIndicator />
        <ToastContainer transition={Zoom} />
      </main>
    </div>
  );
};

export default CodingProblemPageA;
