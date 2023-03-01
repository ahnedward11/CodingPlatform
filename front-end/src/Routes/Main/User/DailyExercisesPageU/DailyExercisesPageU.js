
import "./style.css";
import 'font-awesome/css/font-awesome.min.css';
import React, {useEffect, useState} from "react";
import { useLocation } from "react-router";
import {ToastContainer, Zoom} from "react-toastify";
import styled from "styled-components";
import Navbar from "../../../../Navigation/Navbar";
import DisplayProblem from "../../../../Components/DisplayProblem";
import DisplayCodeEditor from "../../../../Components/DisplayCodeEditor";
import {fetchFromDatabase, fetchUserData} from "../../../../Helpers/Functions";
import {
  getProblemCategories, getRandomExerciseName,
  handleChangeExercise, handleCodeChange, handleCompile,
  handleMoveToTrash, handleShowSelection,
  handleViewSolution
} from "./DailyExercisesLogicU";
import {trackPromise} from "react-promise-tracker";
import {COFFEE_CODER_DB_URL} from "../../../../Data/Urls";
import DisplayLoadingIndicator from "../../../../Components/DisplayLoadingIndicator";
import DisplayCodeConsole from "../../../../Components/DisplayCodeConsole";
import DisplayAllNotes from "../../../../Components/DisplayAllNotes";

export const StyledParagraph = styled.p `
  width: fit-content;
  text-decoration: underline;
  cursor: pointer;
  margin: 1em;
  transition: 0.1s color ease-in;
  
  &:hover {
    color: var(--light-gray);
  }
`;

const StyledButton = styled.button`
  border: 0.5px solid #DDDDDD;
  border-radius: 5px;
  font-size: 1.15rem;
  font-weight: bolder;
  text-decoration: none;
  user-select: none;
  cursor: pointer;
  color: var(--black);
  background-color: var(--light-gray);
  padding: 0.5em 1.5em;
  margin: 0 1em 0 0;
  transition: 0.1s background-color ease-out;
  
  :hover {
    background-color: var(--whitesmoke);
  }
`;

export let account;
export let currentState, setCurrentState;
export let code, setCode;
export let consoleContent, setConsoleContent;

/**
 * The DailyExercises page allows the user to view and select their available daily exercises.
 */
const DailyExercisesPageU = () => {
  [currentState, setCurrentState] = useState({
    contentLoaded: false,
    account: useLocation().state.account,
    currentProblem: useLocation().state.problem,
    exercises: [],
    allNotes: [],
    currentExercise: null,
    showSelection: true,
    showSolution: false,
    showConsole: false,
  });
  [code, setCode] = useState("");
  [consoleContent, setConsoleContent] = useState("Awaiting code compilation ...");

  const DisplaySelection = () => {
    return (
      <>
        <p><strong>Current Coding Problem: <u>{currentState.currentProblem.problemName}</u></strong></p>
        <p>Select a category:</p>
          {/* Flexibly render all exercises matching the problem's categories */}
          <DisplayMatchingExercises />
      </>
    );
  };

  const DisplayMatchingExercises = () => {
    return (
      <>
        {getProblemCategories(currentState.currentProblem.dailyExercises)}
        {currentState.exercises.length > 1 &&
          <StyledParagraph onClick={() => handleChangeExercise(
            getRandomExerciseName(currentState.currentProblem.dailyExercises))}>
            <u>Surprise me! (Selects a category above at random)</u>
          </StyledParagraph>
        }
      </>
    );
  }

  const options = [
    <StyledButton onClick={handleViewSolution}>
      {currentState.showSolution ?
        "Back to Code " :
        "View Solution"}
    </StyledButton>,
    <StyledButton onClick={handleMoveToTrash}>Move to Trash</StyledButton>
  ];

  useEffect(() => {
    async function fetchData() {
      let notesResult = await fetchFromDatabase(COFFEE_CODER_DB_URL + "/note/getAll");

      const resultUserData = await fetchUserData(COFFEE_CODER_DB_URL, currentState.account);

      const dailyExercises = await fetchFromDatabase(COFFEE_CODER_DB_URL +
        "/user_daily_exercise/getUserDailyExercisesByProblemId?csulbId=" + currentState.account.csulbId +
        "&problemId=" + currentState.currentProblem.problemId);

      setCurrentState((state) => ({
        ...state,
        contentLoaded: true,
        account: resultUserData.account,
        currentProblem: resultUserData.problem,
        exercises: dailyExercises,
        allNotes: notesResult,
        currentExercise: resultUserData.problem.dailyExercises[0]
      }));
      setCode(currentState.currentExercise.codeContent);
    }

    trackPromise(
      fetchData()
    ).catch();
  }, []);

  return (
    <>
      {/* Re-use Navbar component for readability */}
      <Navbar pageName={"Daily Exercises"} tabName={"Daily Exercises"}
              state={{
                account: {...currentState.account},
                problem: {...currentState.currentProblem}}} />
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
              headerText={"Daily Exercise Category: " + currentState.currentExercise.category}
              problemDescription={currentState.currentExercise.exerciseDescription}
              examples={currentState.currentExercise.exerciseExamples}
              options={options}/>
            <DisplayCodeEditor
              enableFade={currentState.showSolution}
              programmingLanguage={"Java"}
              handleSubmit={handleCompile}
              handleCodeChange={handleCodeChange}
              codeDefaultValue={code}
              isSolution={currentState.showSolution}
              buttonText={
              currentState.showSolution ?
                null :
                "Compile"}/>
          </div>}
        <DisplayAllNotes allNotes={currentState.allNotes} />
        <DisplayCodeConsole content={consoleContent} showConsole={currentState.showConsole} />
        <DisplayLoadingIndicator />
        <ToastContainer transition={Zoom} />
      </main>
    </>
  );
}

export default DailyExercisesPageU;
