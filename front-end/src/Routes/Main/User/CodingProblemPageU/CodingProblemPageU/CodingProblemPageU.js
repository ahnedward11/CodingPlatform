
import "./style.css";
import 'font-awesome/css/font-awesome.min.css';
import React, {useEffect, useState} from "react";
import { useLocation } from "react-router";
import {ToastContainer, Zoom} from "react-toastify";
import Navbar from "../../../../../Navigation/Navbar";
import styled from "styled-components";
import { Link } from "react-router-dom";
import DisplayProblem from "../../../../../Components/DisplayProblem";
import DisplayCodeEditor from "../../../../../Components/DisplayCodeEditor";
import {handleCodeChange, handleCompile, showChangeSuccessToast} from "./CodingProblemLogicU";
import DisplayProblemNotes from "../../../../../Components/DisplayProblemNotes";
import {fetchUserData, fetchFromDatabase} from "../../../../../Helpers/Functions";
import {trackPromise} from "react-promise-tracker";
import {COFFEE_CODER_DB_URL} from "../../../../../Data/Urls";
import DisplayCodeConsole from "../../../../../Components/DisplayCodeConsole";
import DisplayLoadingIndicator from "../../../../../Components/DisplayLoadingIndicator";

const StyledLink = styled(Link)`
  border: 0.5px solid #DDDDDD;
  border-radius: 5px;
  font-weight: bolder;
  text-decoration: none;
  user-select: none;
  color: var(--black);
  background-color: var(--light-gray);
  padding: 0.5em 1.5em;
  transition: 0.1s background-color ease-out;
  
  :hover {
    background-color: var(--whitesmoke);
  }
`;

export let account;
export let currentState, setCurrentState, currentProblemId, setCurrentProblemId;
export let code, setCode;
export let consoleContent, setConsoleContent;

/**
 * The Problems page allows the user to view and select their available coding problems.
 */
const CodingProblemPageU = () => {
  const location = useLocation().state;
  [currentState, setCurrentState] = useState({
    contentLoaded: false,
    account: location.account,
    currentProblem: location.problem,
    allNotes: [],
    showConsole: false,
    shownToast: false
  });
  [code, setCode] = useState("// Loading...");
  [currentProblemId, setCurrentProblemId] = useState(0);
  [consoleContent, setConsoleContent] = useState("Awaiting code compilation ...");

  const options = [
    <StyledLink to={"/Problems"}
                style={{marginRight: "1em"}}
                state={{
                  account: {...currentState.account},
                  problem: {...currentState.currentProblem}}}>Change Current Problem</StyledLink>,
    <StyledLink to={"/CodingProblemSolution"}
                state={{
                  account: {...currentState.account},
                  problem: {...currentState.currentProblem}}}>View Solution</StyledLink>
  ];

  useEffect(() => {
    // Show the toast if the user came from another page route, the other page passed in a `deletedAccount` field,
    // and this page has not shown the toast before
    if (location.changedProblem && !currentState.shownToast) {
      showChangeSuccessToast();

      setCurrentState({
        ...currentState,
        shownToast: false
      })
    }

    async function fetchData() {
      let notesResult = await fetchFromDatabase(COFFEE_CODER_DB_URL + "/note/getAll");

      const resultUserData = await fetchUserData(COFFEE_CODER_DB_URL, currentState.account);

      const userCodingProblem = await fetchFromDatabase(COFFEE_CODER_DB_URL +
        "/user_coding_problem/getUserCodingProblem?csulbId=" + currentState.account.csulbId +
        "&problemId=" + resultUserData.problem.problemId);

      setCurrentState((state) => ({
        ...state,
        contentLoaded: true,
        account: resultUserData.account,
        currentProblem: resultUserData.problem,
        allNotes: notesResult
      }));
      setCode(userCodingProblem.codeContent);
      setCurrentProblemId(resultUserData.problem.problemId);
    }

    trackPromise(
      fetchData()
    ).catch();
  }, []);

  return (
    <>
      {/* Re-use Navbar component for readability */}
      <Navbar pageName={"Current Coding Problem"}
              tabName={"Problems"}
              state={{
                account: {...currentState.account},
                problem: {...currentState.currentProblem}}} />
      <main id={"main-container"} style={{display: "block", paddingTop: "0"}}>
        <div id={"problems-container"}
             style={{paddingTop: "2em"}}>
          <DisplayProblem
            headerText={"Coding Problem Selected: " + currentState.currentProblem.problemName}
            problemDescription={currentState.currentProblem.problemDescription}
            examples={currentState.currentProblem.problemExamples}
            options={options}/>
          <DisplayCodeEditor
            programmingLanguage={"Java"}
            handleSubmit={handleCompile}
            handleCodeChange={handleCodeChange}
            codeDefaultValue={code}
            buttonText={"Compile"} />
        </div>
        <DisplayCodeConsole content={consoleContent} showConsole={currentState.showConsole} />
        <DisplayProblemNotes allNotes={currentState.allNotes} />
        <DisplayLoadingIndicator />
        <ToastContainer transition={Zoom} />
      </main>
    </>
  );
}

export default CodingProblemPageU;
