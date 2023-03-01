
import "./style.css";
import 'font-awesome/css/font-awesome.min.css';
import React, {useEffect, useRef, useState} from "react";
import { useLocation } from "react-router";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import Navbar from "../../../../../Navigation/Navbar";
import DisplayProblem from "../../../../../Components/DisplayProblem";
import DisplayExerciseStickyNote from "../../../../../Components/DisplayExerciseStickyNote";
import {
    handleConfirm,
    handleSelectChange,
    handleTextClick,
    hasUserAttempted,
    hasUserSolved
} from "./CPSelectionLogic";
import {fetchUserData, getSelectedProblems} from "../../../../../Helpers/Functions";
import { COFFEE_CODER_DB_URL } from "../../../../../Data/Urls";
import { trackPromise } from "react-promise-tracker";
import DisplayLoadingIndicator from "../../../../../Components/DisplayLoadingIndicator";
import {ToastContainer, Zoom} from "react-toastify";

const StyledParagraph = styled.p`
  width: fit-content;
  color: var(--black);
  text-decoration: underline;
  cursor: pointer;
  margin: 0;
  transition: 0.1s color ease-in;
  
  &:hover {
    color: var(--light-gray);
  }
`;

const confirmButtonStyle = {
    border: "none",
    borderRadius: "5px",
    fontSize: "1.25rem",
    fontWeight: "bolder",
    textDecoration: "none",
    userSelect: "none",
    padding: "0.5em 1.5em",
};

export let navigate;
export let currentState, setCurrentState;
export let allProblems;
export let selectProblems, setSelectProblems;

/**
 * The Problems page allows the user to view and select their available coding problems.
 */
const CPSelectionPage = () => {
  navigate = useNavigate();
  [currentState, setCurrentState] = useState({
    contentLoaded: false,
    account: useLocation().state.account,
    currentProblem: useLocation().state.problem,
    selectedDifficulty: "All problems",
    showSelectedProblem: false
  });
  allProblems = useRef([]);
  [selectProblems, setSelectProblems] = useState(allProblems.current);

  const DisplaySelection = () => {
    return (
      <div id={"management-container"}
           style={{width: "100%", paddingTop: "0"}}>
        <div className={"account-management-container"}>
          <label htmlFor={"select-difficulty"} style={{width: "40%", fontSize: "1.15rem", fontWeight: "bolder"}}>
            Coding Problems:
          </label>
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
          {currentState.showSelectedProblem ?
            <DisplayProblemSticky /> :
            <DisplaySelectionResults />}
        </div>
      </div>
    );
  }

  const DisplayProblemSticky = () => {
    // Return the JSX representing the coding problem's fields
    return (
      <>
        <div id={"problems-container"} style={{width: "100%", paddingTop: "0.5em"}}>
          <DisplayProblem
            headerText={"Coding Problem Selected: " + currentState.currentProblem.problemName}
            problemDescription={currentState.currentProblem.problemDescription}
            examples={currentState.currentProblem.problemExamples}
            options={options}/>
          <DisplayExerciseStickyNote
            headerText={"The Daily Exercises involved in this problem consist of the following:"}
            problemCategories={currentState.currentProblem.dailyExercises} />
        </div>
      </>
    );
  };

  const DisplaySelectionResults = () => {
    return (
      <>
        <div className={"account-management-container"}
             style={{width: "100%", textAlign: "left", borderBottom: "2px solid black", paddingBottom: "0.5em",
               marginBottom: "0.5em"}}>
          <h3 className={"management-column"}
              style={{width: "40%", fontSize: "1.15rem", fontWeight: "bold"}}>Name</h3>
          <h3 className={"management-column hide-on-media"}
              style={{width: "20%", fontSize: "1.15rem", fontWeight: "bold"}}>Attempted</h3>
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
    // Select all problems if no specific difficulty was chosen; otherwise, filter by the chosen difficulty
    // Filter only the problems matching the chosen difficulty
    return selectProblems.map((problem) => {
      return (
        <div style={{display: "flex", flexDirection: "row", width: "100%"}}>
          <div className={"management-column"}
               style={{width: "40%"}}>
            <StyledParagraph onClick={() => handleTextClick(problem)}>{problem.problemName}</StyledParagraph>
          </div>
          <p className={"management-column hide-on-media"}
             style={{width: "20%"}}>
            {hasUserAttempted(problem) ?
              "Yes" :
              "No"}
          </p>
          <p className={"management-column"}
             style={{width: "20%"}}>
            {hasUserSolved(problem) ?
              "Yes" :
              "No"}
          </p>
          <p className={"management-column"}
             style={{width: "20%"}}>{problem.difficultyCategory}</p>
        </div>)
    })
  }

  const options = [
    <button className={"button-compile-code"}
            style={confirmButtonStyle}
            onClick={handleConfirm}>Confirm</button>
  ];

  useEffect(() => {
    async function fetchData() {
      await fetchUserData(COFFEE_CODER_DB_URL, currentState.account)
        .then((result) => {
          setCurrentState({
            contentLoaded: true,
            account: result.account,
            currentProblem: result.problem
          });
        });
      let result = await getSelectedProblems("All problems");
      allProblems.current = result;
      setSelectProblems(result);
    }

    trackPromise(
      fetchData()
    ).catch();
  }, []);

  return (
    <>
      {/* Re-use Navbar component for readability */}
      <Navbar pageName={"Problems"}
              tabName={"Problems"}
              state={{
                account: {...currentState.account},
                problem: {...currentState.currentProblem}}} />
      <main id={"main-container"} style={{display: "block", width: "100%", paddingTop: "0"}}>
        <div id={"problems-container"}
             style={{paddingTop: "2em"}}>
          <DisplaySelection />
        </div>
      </main>
      <DisplayLoadingIndicator />
      <ToastContainer transition={Zoom} />
    </>
  );
}

export default CPSelectionPage;
