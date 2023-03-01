
import { currentState, setCurrentState } from "./NotesPage";
import { toast } from "react-toastify";
import {COFFEE_CODER_DB_URL} from "../../../../Data/Urls";
import {fetchFromDatabase} from "../../../../Helpers/Functions";
import {trackPromise} from "react-promise-tracker";

export const handleTitle = (event) => {
  const input = event.target.value;
  setCurrentState({
    ...currentState,
    noteTitle: input

  });

};

export const handleNoteContent = (event) => {
  const input = event.target.value;

  setCurrentState({
    ...currentState,
    noteContent: input
  });
};

export async function handleSubmit(event) {
  // Prevent the form's default behavior, which is to refresh the page upon each submission
  event.preventDefault();

  await trackPromise(
    saveNote()
  );
}

async function saveNote() {
      const note = {noteTitle: currentState.noteTitle,
          noteContent: currentState.noteContent,
          user: await fetchFromDatabase(COFFEE_CODER_DB_URL + "/user/getByCsulbId?csulbId=" + currentState.account.csulbId)};

  console.log(note);

  return await fetch(COFFEE_CODER_DB_URL + "/note/add",{
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(note)

  }).then(result => result.json())
    .then((result) => {
      if (result.noteContent === currentState.noteContent) {
        showCreationSuccessToast("Note has been added.");
      } else {
        showCreationErrorToast("Error has occurred.");
      }
    });
}

const showCreationSuccessToast = (message) => {
  toast.success(message, {
    toastId: message.toString(),
    position: "top-center",
    theme: "colored",
    autoClose: 5_000,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true
  });
}

const showCreationErrorToast = (errorMessage) => {
  toast.error(errorMessage, {
    toastId: errorMessage.toString(),
    position: "top-center",
    theme: "colored",
    autoClose: 5_000,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true
  });
}
