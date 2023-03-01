
import exercises from "../../../../Data/DailyExercises";
import {toast} from "react-toastify";
import {currentState, setCode, setCurrentState} from "./DailyExercisesPageA";

// Return all possible exercises
export const getAllExercises = () => {
  return Array.from(exercises.keys());
}

// onClick handler for showing the list of daily exercises
export const handleShowSelection = () => {
  setCurrentState({
    ...currentState,
    showSelection: !currentState.showSelection
  });
}

// onClick handler for changing the currently displayed daily exercise
export const handleChangeExercise = (exerciseName) => {
  setCurrentState({
    ...currentState,
    currentExerciseName: exerciseName,
    currentExercise: exercises.get(exerciseName),
    showSelection: !currentState.showSelection
  });
  setCode(currentState.currentExercise.codeTemplate);
}

// onClick handler for updating the code within the code editor
export const handleCodeChange = (event) => {
  setCode(event.target.value);
}

// onSubmit handler for posting the solution to the daily exercise
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
}
