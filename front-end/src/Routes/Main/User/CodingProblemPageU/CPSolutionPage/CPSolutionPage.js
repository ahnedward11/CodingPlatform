
import "./style.css";
import 'font-awesome/css/font-awesome.min.css';
import React, {useEffect, useState} from "react";
import { useLocation } from "react-router";
import {ToastContainer, Zoom} from "react-toastify";
import Navbar from "../../../../../Navigation/Navbar";
import DisplayProblem from "../../../../../Components/DisplayProblem";
import DisplayCodeEditor from "../../../../../Components/DisplayCodeEditor";
import {
  handleDownvote,
  handleReport,
  handleUpvote,
} from "./CPSolutionLogic";
import {fetchFromDatabase} from "../../../../../Helpers/Functions";
import {COFFEE_CODER_DB_URL} from "../../../../../Data/Urls";
import {trackPromise} from "react-promise-tracker";
import DisplayLoadingIndicator from "../../../../../Components/DisplayLoadingIndicator";

const reportButtonStyle = {
  border: "none",
  borderRadius: "5px",
  fontSize: "1.25rem",
  fontWeight: "bolder",
  textDecoration: "none",
  userSelect: "none"
};

export let account;
export let currentState, setCurrentState;
export let voteState, setVoteState;
export let highlightedText, setHighlightedText;
export let solutionCode, solutionObject;
export let isReported;

/**
 * The Problems page allows the user to view and select their available coding problems.
 */
const CPSolutionPage = () => {
  [highlightedText, setHighlightedText] = useState("");
  [currentState, setCurrentState] = useState({
    contentLoaded: false,
    account: useLocation().state.account,
    currentProblem: useLocation().state.problem,
    solutionId: 0,
    solutionCode: "",
    solutionObject: "",
    isReported: false
  });
  [voteState, setVoteState] = useState({
    voteState: 0,
    upvoteCount: 0,
    downvoteCount: 0,
    upvoteColor: "var(--black)",
    downvoteColor: "var(--black)",
  });

  const options = [
    <button className={"button-tab button-delete"}
            style={reportButtonStyle}
            onClick={handleReport}>Report</button>
  ];

  useEffect(() => {
    async function fetchData() {
      // Fetching solution code
      const resultSolution = await fetchFromDatabase(COFFEE_CODER_DB_URL +
        "/coding_problem_solution/getProblemSolution?problemId=" + currentState.currentProblem.problemId)

      if (resultSolution.solutionCode !== "") {
        setCurrentState((state) => ({
          ...state,
          solutionId: resultSolution.solutionId,
          solutionCode: resultSolution.solutionCode,
          solutionObject: resultSolution
        }));
      } else {
        setCurrentState((state) => ({
          ...state,
          solutionId: resultSolution.solutionId,
          solutionCode: "// No solution has been created yet."
        }));
      }

      // Fetching vote state
      const resultState = await fetchFromDatabase(COFFEE_CODER_DB_URL +
        "/coding_problem_solution/getVoteState?solutionId=" + resultSolution.solutionId +
        "&userId=" + currentState.account.csulbId);

      // Fetching upvote count
      const resultUpvoteCount = await fetchFromDatabase(COFFEE_CODER_DB_URL +
        "/coding_problem_solution/getUpvoteCount");

      // Fetching downvote count
      const resultDownvoteCount = await fetchFromDatabase(COFFEE_CODER_DB_URL +
        "/coding_problem_solution/getDownvoteCount");

      setVoteState((state) => ({
        ...state,
        voteState: resultState,
        upvoteColor: (resultState === 1) ? "var(--baby-blue)" : "var(--black)",
        downvoteColor: (resultState === 2) ? "var(--red)" : "var(--black)",
        upvoteCount: resultUpvoteCount,
        downvoteCount: resultDownvoteCount
      }));
    }

    trackPromise(
      fetchData()
    ).catch();
  }, []);

  return (
    <>
      {/* Re-use Navbar component for readability */}
      <Navbar pageName={"Current Coding Problem Solution"}
              tabName={"Problems"}
              state={{
                account: {...currentState.account},
                problem: {...currentState.currentProblem}}} />
      <main id={"main-container"} style={{display: "block", paddingTop: "0"}}>
        <div id={"problems-container"}
             style={{paddingTop: "2em"}}>
          <DisplayProblem
            headerText={currentState.currentProblem.problemName + " Solution"}
            problemDescription={currentState.currentProblem.problemDescription}
            examples={currentState.currentProblem.problemExamples}
            currentProblem={currentState.currentProblem}
            handleUpvote={handleUpvote}
            handleDownvote={handleDownvote}
            voteState={voteState}
            options={options} />
          <DisplayCodeEditor
            enableFade={true}
            programmingLanguage={"Java"}
            handleSubmit={null}
            handleCodeChange={null}
            codeDefaultValue={currentState.solutionCode}
            isSolution={true} />
        </div>
        <DisplayLoadingIndicator />
        <ToastContainer transition={Zoom} />
      </main>
    </>
  );
}

export default CPSolutionPage;
