
import "./style.css";
import 'font-awesome/css/font-awesome.min.css';
import React, {useEffect, useState} from "react";
import { useLocation } from "react-router";
import {ToastContainer, Zoom} from "react-toastify";
import styled from "styled-components";
import Navbar from "../../../../Navigation/Navbar";
import exercises from "../../../../Data/DailyExercises";
import DisplayProblem from "../../../../Components/DisplayProblem";
import DisplayCodeEditor from "../../../../Components/DisplayCodeEditor";
import {getCodingProblemData} from "../../../../Helpers/Functions";
import {
  getAllExercises,
  handleChangeExercise,
  handleCodeChange,
  handlePostSolution,
  handleShowSelection
} from "./DailyExercisesLogicA";
import {users} from "../../../../Data/Accounts";

const StyledParagraph = styled.p `
  width: fit-content;
  text-decoration: underline;
  cursor: pointer;
  margin: 1em;
  transition: 0.1s color ease-in;
  
  &:hover {
    color: var(--light-gray);
  }
`;

export let account;
export let currentState, setCurrentState;
export let code, setCode;

/**
 * The DailyExercises page allows the user to view and select their available daily exercises.
 */
const DailyExercisesPageA = () => {
  // TODO: delete/alter the hard-coded objects and instead fetch from the database
  account = users[0];
  [currentState, setCurrentState] = useState({
    contentLoaded: false,
    account: useLocation().state.account,
    currentExerciseName: getCodingProblemData(account.currentCodingProblem).categories[0],
    currentExercise: exercises.get(getCodingProblemData(account.currentCodingProblem).categories[0]),
    showSelection: true
  });
  [code, setCode] = useState();
  console.log(currentState.account);

  const DisplaySelection = () => {
    return (
      <>
        <p><strong>Select a category to edit:</strong></p>
        <div style={{display: "block", textAlign: "left", lineHeight: "0.75em"}}>
          {/* Render all possible exercises */}
          <DisplayExercises />
        </div>
      </>
    );
  }

  const DisplayExercises = () => {
    let exercises = getAllExercises();
    // Admins can edit all exercises;
    // Generate an array from the map's key set, then return a StyledP element with each key
    return (exercises.map((exerciseName) => {
      return (
        <>
          <StyledParagraph onClick={() => handleChangeExercise(exerciseName)}>
            <u>{exerciseName}</u>
          </StyledParagraph>
        </>);
    }));
  }

  useEffect(() => {
    /*
    async function fetchData() {
      await fetchAccountProblemData(COFFEE_CODER_DB_URL, currentState.account)
        .then((result) => {
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
     */
  }, []);

  return (
    <>
      {/* Re-use Navbar component for readability */}
      <Navbar pageName={"Edit Daily Exercises"}
              tabName={"Daily Exercises"}
              state={{account: {...currentState.account}}} />
      <main id={"main-container"} style={{display: "block", paddingTop: "0"}}>
        <button id={"page-button"}
                className={"button-tab"}
                style={{width: "75px", fontSize: "1.15rem", cursor: "pointer", padding: "0.5em", marginTop: "1em"}}
                onClick={handleShowSelection}><i className={"fa-solid fa-arrow-right-arrow-left"}></i></button>
        {currentState.showSelection ?
          <DisplaySelection /> :
          <div id={"problems-container"}
               style={{paddingTop: "1em"}}>
            <DisplayProblem
              headerText={"Daily Exercise Category: " + currentState.currentExerciseName}
              problemDescription={currentState.currentExercise.description}
              examples={currentState.currentExercise.examples} />
            <DisplayCodeEditor
              programmingLanguage={"Java"}
              handleSubmit={handlePostSolution}
              handleCodeChange={handleCodeChange}
              codeDefaultValue={currentState.currentExercise.solution}
              buttonText={"Post Solution"} />
          </div>}
        <ToastContainer transition={Zoom} />
      </main>
    </>
  );
}

export default DailyExercisesPageA;
