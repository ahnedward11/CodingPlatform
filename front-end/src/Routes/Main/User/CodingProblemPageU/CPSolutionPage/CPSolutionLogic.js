
import { toast } from "react-toastify";
import {
  currentState,
  setHighlightedText,
  setVoteState,
  voteState,
  highlightedText,
} from "./CPSolutionPage";
import {COFFEE_CODER_DB_URL} from "../../../../../Data/Urls";
import {setCurrentState} from "./CPSolutionPage";
import {fetchFromDatabase, getModelAttribute} from "../../../../../Helpers/Functions";
import {trackPromise} from "react-promise-tracker";

// onClick handler for updating the solution's upvote count
export async function handleUpvote() {
  // Solution was already upvoted by this user
  if (voteState.voteState === 1) {
    setVoteState((state) => ({
      ...state,
      voteState: 0,
      upvoteCount: state.upvoteCount - 1,
      upvoteColor: "var(--black)",
      downvoteColor: "var(--black)"
    }));

    // Solution was previously downvoted by this user
  } else if (voteState.voteState === 2) {
    setVoteState((state) => ({
      ...state,
      voteState: 1,
      upvoteCount: state.upvoteCount + 1,
      downvoteCount: state.downvoteCount - 1,
      upvoteColor: "var(--baby-blue)",
      downvoteColor: "var(--black)"
    }));

    // User did not vote on this solution yet
  } else {
    setVoteState((state) => ({
      ...state,
      voteState: 1,
      upvoteCount: state.upvoteCount + 1,
      upvoteColor: "var(--baby-blue)",
      downvoteColor: "var(--black)"
    }));
  }
  await fetchFromDatabase(COFFEE_CODER_DB_URL +
    "/coding_problem_solution/handleUpvote?solutionId=" + currentState.solutionId +
    "&userId=" + currentState.account.csulbId, "POST");
}

// onClick handler for updating the solution's downvote count
export async function handleDownvote() {
  // Solution was previously upvoted by this user
  if (voteState.voteState === 1) {
    setVoteState((state) => ({
      ...state,
      voteState: 2,
      upvoteCount: state.upvoteCount - 1,
      downvoteCount: state.downvoteCount + 1,
      upvoteColor: "var(--black)",
      downvoteColor: "var(--red)"
    }));

    // Solution was already downvoted by this user
  } else if (voteState.voteState === 2) {
    setVoteState((state) => ({
      ...state,
      voteState: 0,
      downvoteCount: state.downvoteCount - 1,
      upvoteColor: "var(--black)",
      downvoteColor: "var(--black)"
    }));

    // User did not vote on this solution yet
  } else {
    setVoteState((state) => ({
      ...state,
      voteState: 2,
      downvoteCount: state.downvoteCount + 1,
      upvoteColor: "var(--black)",
      downvoteColor: "var(--red)"
    }));
  }
  await fetchFromDatabase(COFFEE_CODER_DB_URL +
    "/coding_problem_solution/handleDownvote?solutionId=" + currentState.solutionId +
    "&userId=" + currentState.account.csulbId, "POST");
}

// onClick handler for submitting the user's report for admin review
export const handleReport = (event) => {
  event.preventDefault();

  // Update the state with the user's current highlighted text
  setHighlightedText(window.getSelection().toString());

  if (highlightedText !== "")  {
    trackPromise(
        addReport(highlightedText, currentState.account.csulbId)
    ).catch((error) => console.log(error));
  }
}

/**
 * Adds a report to the Coffee Coder database.
 * Throws an error toast if the report entry is a duplicate.
 * @param highlightedText the highlighted text set by the user
 * @param csulbId the user csulb id
 * @returns {Promise<any>} the promise state of the report posting
 */
async function addReport(highlightedText, csulbId) {
  // Verify if a matching User exists first
  const user = await getModelAttribute(COFFEE_CODER_DB_URL, "user", "csulbId", csulbId);

  // Verify that user current coding problem has a matching solution
  const codingProblemSolution = currentState.solutionObject;

  const reportContent = highlightedText;

  const newReport = {reportContent, user, codingProblemSolution};

  return await fetch(COFFEE_CODER_DB_URL + "/report/add", {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(newReport)

  }).then(result => result.json())
      .then((result) => {
        if (result === 0) {
          setCurrentState({...currentState, isReported: true})
          showReportSuccessToast();
          return Promise.resolve();

        } else if (result === -1) {
          showFormErrorToast("Invalid report. Please try again.");
          setCurrentState({
            ...currentState
          });
          return Promise.reject("Invalid report. Please try again.");
        }
      });
}

const showFormErrorToast = (errorMessage) => {
  // The toastID does not need to follow a convention; it just ignores rapid additional renders if
  // another toast with an identical ID is already active
  toast.error(errorMessage, {
    toastId: errorMessage.toString(),
    position: "top-center",
    theme: "colored",
    autoClose: 10_000,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true
  });
}

export const showReportSuccessToast = () => {
  toast.success("Solution reported and pending review.", {
    toastId: "toast-success",
    position: "top-center",
    theme: "colored",
    autoClose: 5000,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true,
    hideProgressBar: true
  });
}