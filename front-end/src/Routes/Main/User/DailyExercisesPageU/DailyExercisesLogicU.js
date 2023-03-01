
import React from "react";
import {currentState, code, setCode, setConsoleContent, setCurrentState, StyledParagraph} from "./DailyExercisesPageU";
import { toast } from "react-toastify";
import {COFFEE_CODER_DB_URL} from "../../../../Data/Urls";
import {trackPromise} from "react-promise-tracker";
import {fetchFromDatabase} from "../../../../Helpers/Functions";

// Return the exercise categories associated with the coding problem
export const getProblemCategories = (exercises) => {
  const problemCategories = [];

  exercises.map((exercise) => {
    const targetExercise = currentState.exercises.filter((currentExercise) =>
      currentExercise.exerciseId === exercise.exerciseId &&
      currentExercise.csulbId === exercise.csulbId
    )
    if (targetExercise.length > 0) {
      problemCategories.push(
        <StyledParagraph onClick={() => handleChangeExercise(exercise)}>
          <u>{exercise.category}</u>
        </StyledParagraph>
      );
    }
  });

  return (<>{problemCategories}</>);
};

// Return the name of a randomly chosen exercise
export const getRandomExerciseName = (exercises) => {
  // Generate and store a random number from [0, max)
  let randomNum = Math.floor(Math.random() * exercises.length);

  return exercises[randomNum].category;
};

// onClick handler for showing the list of daily exercises
export const handleShowSelection = () => {
  setCurrentState({
    ...currentState,
    showSelection: !currentState.showSelection,
    showSolution: false
  });
};

// onClick handler for changing the currently displayed daily exercise
export const handleChangeExercise = (exercise) => {
  setCurrentState((state) => ({
    ...state,
    currentExercise: exercise,
    showSelection: !currentState.showSelection,
    showSolution: false
  }));
  setCode(exercise.codeContent);
};

// onClick handler for viewing the solution to the current daily exercise
export const handleViewSolution = () => {
  setCurrentState({
    ...currentState,
    showSolution: !currentState.showSolution
  });
};

// onClick handler for removing the current daily exercise from the user's list of daily exercises
export async function handleMoveToTrash(event) {
  event.preventDefault();

  await fetch(COFFEE_CODER_DB_URL +
    "/user_daily_exercise/deleteUserDailyExercise?csulbId=" + currentState.account.csulbId +
    "&exerciseId=" + currentState.currentExercise.exerciseId, {
    method: "DELETE",
    headers: {"Content-Type": "application/json"}
  });
  setCurrentState((state) => ({
    ...state,
    showSelection: true
  }))

  let toastID = "toast-success";

  toast.success("Removed daily exercise.", {
    toastId: toastID,
    position: "top-center",
    theme: "colored",
    autoClose: 5000,
    hideProgressBar: true,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true,
    progress: undefined,
  });
}

// onClick handler for updating the code within the code editor
export const handleCodeChange = (event) => {
  setCode(event);
};

// onSubmit handler for compiling the user's code
export async function handleCompile(event) {
  event.preventDefault();
  setCurrentState((state) => ({
    ...state,
    showConsole: false
  }));

  trackPromise(
    compileCode()
  ).catch();
}

async function compileCode() {
  const testCases = await fetchFromDatabase(COFFEE_CODER_DB_URL +
    "/daily_exercise_test_case/getByExerciseId?exerciseId=" + (currentState.currentExercise.exerciseId));
  console.log(testCases);

  const divider = "\n===================================================================\n";
  let consoleOutput = divider;
  let testLog = "";
  let allSolved = true;

  for (let i = 0; i < testCases.length; ++i) {
    const currentTest = testCases[i];
    const codeOutput = await getCompileResponse(currentTest.testInput + "\n\n" + code);
    console.log(currentTest.testInput + "\n\n" + code);

    consoleOutput += "Test case " + (i + 1) +
      "\n\nExpected output: " + currentTest.testOutput +
      "\nActual output: " + codeOutput.output;

    if (codeOutput.output === currentTest.testOutput) {
      testLog += "Test " + (i + 1) + ":  + Passed\n";
    } else {
      testLog += "Test " + (i + 1) + ":  - Failed\n";
      allSolved = false;
    }

    consoleOutput += divider;
  }

  consoleOutput += "\n" + testLog + divider;
  setCurrentState((state) => ({
    ...state,
    showConsole: true
  }));
  setConsoleContent(consoleOutput);

  await fetch(COFFEE_CODER_DB_URL +
    "/user_daily_exercise/updateCodeContent?csulbId=" + currentState.account.csulbId, {
    method: "PATCH",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify({content: code})
  });
  await fetch(COFFEE_CODER_DB_URL +
    "/user_daily_exercise/updateIsSolved?csulbId=" + currentState.account.csulbId +
    "&exerciseId=" + currentState.currentExercise.exerciseId +
    "&isSolved=" + allSolved, {
    method: "PATCH",
    headers: {"Content-Type": "application/json"}
  });
}

async function getCompileResponse(code) {
  const codeBody = {
    content: code
  };
  const resultCode = await fetch(COFFEE_CODER_DB_URL +
    "/daily_exercise/compileCode", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(codeBody)
  });
  return await resultCode.json();
}
