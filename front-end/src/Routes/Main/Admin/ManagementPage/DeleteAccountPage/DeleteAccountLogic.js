import {currentState, setCurrentState, navigate} from "./DeleteAccountPage";
import { toast } from "react-toastify";
import {COFFEE_CODER_DB_URL} from "../../../../../Data/Urls";
import {trackPromise} from "react-promise-tracker";

export const handleDeletion = (event) => {
  event.preventDefault();

  trackPromise(
      deleteAccount(currentState.user.csulbId)
  ).catch((error) => console.log(error));
};

async function deleteAccount(csulbId) {

  return await fetch(COFFEE_CODER_DB_URL + "/admin/delete?csulbId=" + csulbId, {
    method: "DELETE"
  }).then(result => result.json())
      .then((result) => {
        if (result === 0) {
          // Redirect to the Management Page
          navigate("/Management", {state: {...currentState, accountDeleted: true}});
          return Promise.resolve()
        } else if (result === -1) {
          showToast("An account does not exist for that user");
          setCurrentState({
            ...currentState
          });
          return Promise.reject("An account does not exist for that user.");
        }
      })
}

const showToast = (message) => {
  // The toastID does not need to follow a convention; it just ignores rapid additional renders if
  // another toast with an identical ID is already active
  toast.error(message, {
    toastId: message.toString(),
    position: "top-center",
    theme: "colored",
    autoClose: 10_000,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true
  });
}