
import { toast } from "react-toastify";
import { currentState, setCurrentState } from "./SettingsPage";
import { updateModelAttribute } from "../../../../Helpers/Functions";
import { trackPromise } from "react-promise-tracker";
import { COFFEE_CODER_DB_URL } from "../../../../Data/Urls";


// With each new character input within the input field, update the state
export const handleProfileNameChange = (event) => {
  setCurrentState({
    ...currentState,
    profileName: event.target.value
  });
};

// onClick handler for allowing the user to open and save a chosen photo as their new profile image
export const handlePhotoChange = (event) => {
  setCurrentState({
    ...currentState,
    profileImgSrc: event.target.value
  });
};

// onSubmit handler for saving the changes of the profile name or profile photo to the database
export const handleSubmit = (event) => {
  // Prevent the form's default behavior, which is to refresh the page upon each submission
  event.preventDefault();

  trackPromise(
    verifyNamePhotoChange(currentState.account)
  ).catch();

  setCurrentState({
    ...currentState,
    contentLoaded: true,
    toggleButton: true
  });
};

async function verifyNamePhotoChange(account) {
  const updatedAccount = await updateModelAttribute(COFFEE_CODER_DB_URL, "user",
    "csulbId", account.csulbId, "profileName", currentState.profileName);

  if (updatedAccount.profileName === currentState.profileName) {
    return await verifyPhotoChange(account);
  } else {
    showUpdateErrorToast("Something went wrong; please try again later.");
    return Promise.reject("Something went wrong; please try again later.");
  }
}

async function verifyPhotoChange(account) {
  const updatedAccount = await updateModelAttribute(COFFEE_CODER_DB_URL, "user",
    "csulbId", account.csulbId, "profileImgSrc", currentState.profileImgSrc);

  if (updatedAccount.profileImgSrc === currentState.profileImgSrc) {
    showUpdateSuccessToast("Profile updated.");
    return Promise.resolve();
  } else {
    showUpdateErrorToast("Something went wrong; please try again later.");
    return Promise.reject("Something went wrong; please try again later.");
  }
}

const showUpdateSuccessToast = (message) => {
  toast.success(message, {
    toastId: message.toString(),
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

const showUpdateErrorToast = (errorMessage) => {
  toast.error(errorMessage, {
    toastId: errorMessage.toString(),
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
