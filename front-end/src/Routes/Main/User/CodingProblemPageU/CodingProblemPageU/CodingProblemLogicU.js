
import { toast } from "react-toastify";
import {currentState, setCurrentState, code, setCode, currentProblemId, setConsoleContent} from "./CodingProblemPageU";
import {fetchFromDatabase} from "../../../../../Helpers/Functions";
import {COFFEE_CODER_DB_URL} from "../../../../../Data/Urls";
import {trackPromise} from "react-promise-tracker";

// onChange handler for updating the code within the code editor
export const handleCodeChange = (event) => {
  setCode(event);
}

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
    "/coding_problem_test_case/getByProblemId?problemId=" + 1);

  const divider = "\n===================================================================\n";
  let consoleOutput = divider;
  let testLog = "";
  let allSolved = true;

  for (let i = 0; i < testCases.length; ++i) {
    const currentTest = testCases[i];
    const codeOutput = await getCompileResponse(currentTest.testInput + "\n\n" + code);

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
    "/user_coding_problem/updateCodeContent?csulbId=" + currentState.account.csulbId, {
    method: "PATCH",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify({content: code})
  });
  await fetch(COFFEE_CODER_DB_URL +
    "/user_coding_problem/updateIsSolved?csulbId=" + currentState.account.csulbId +
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
    "/coding_problem/compileCode", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(codeBody)
  });
  return await resultCode.json();
}

export const showChangeSuccessToast = () => {
  toast.success("Current coding problem has been changed.", {
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
