
import { getCodingProblemData } from "../../../../Helpers/Functions";
import cpSolutions from "../../../../Data/CodingProblemSolutions";
import { toast } from "react-toastify";
import {
  currentState,
  setCurrentState,
  setCode
} from "./ReportsPage";

// onClick handler for showing the list of reports
export const handleShowSelection = () => {
  setCurrentState({
    ...currentState,
    showSelection: !currentState.showSelection
  });
};

// onClick handler for clicking the highlighted name of the coding problem's report
export const handleChangeReport = (report) => {
  setCurrentState({
    ...currentState,
    currentReport: report,
    currentProblem: getCodingProblemData(report.codingProblemName),
    showSelection: !currentState.showSelection
  });
  setCode(cpSolutions.get(report.codingProblemName).code);
};

// onChange handler for updating the code within the code editor
export const handleCodeChange = (event) => {
  setCode(event.target.value);
};

// onSubmit handler for posting the solution for the current coding problem
export const handlePostSolution = (event) => {
  event.preventDefault();
  let toastID = "toast-error";

  toast.error("Sorry, not yet implemented.", {
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
};
