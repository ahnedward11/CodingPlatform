
import { toast } from "react-toastify";
import { currentState, navigate, setCurrentState } from "./LoginPage";
import { trackPromise } from "react-promise-tracker";
import { isInteger } from "lodash";
import {getCodingProblem, getModelAttribute} from "../../../Helpers/Functions";
import {COFFEE_CODER_DB_URL} from "../../../Data/Urls";

// Event handlers to listen for changes to user input within text boxes
export const handleEmailOrIdChange = (event) => {
  setCurrentState({
    ...currentState,
    emailOrId: event.target.value,
    emailOrIdState: "default",
    passwordState: "default"
  });
};

export const handlePasswordChange = (event) => {
  setCurrentState({
    ...currentState,
    password: event.target.value,
    emailOrIdState: "default",
    passwordState: "default"
  });
};

export const handleSubmit = (event) => {
  event.preventDefault();

  trackPromise(
    verifyAccount(event.target.emailOrId.value, event.target.password.value)
  ).catch((error) => console.log(error));
};

async function verifyAccount(emailOrId, password) {
  const attribute = (isInteger(+emailOrId)) ? "csulbId" : "email";

  // Check if the input email/id exists within the list of either user or admin accounts
  let account = await getModelAttribute(COFFEE_CODER_DB_URL, "admin", attribute, currentState.emailOrId);
  let verified = false;

  if (account.email && account.email !== "" && account.password === password) {
    verified = true;
  } else {
    account = await getModelAttribute(COFFEE_CODER_DB_URL, "user", attribute, currentState.emailOrId);

    if (account.email && account.email !== "" && account.password === password) {
      verified = true;
    }
  }

  if (verified) {
    // Redirect to the designated page depending on the role, and pass the account object as a location state
    if (account.role === "ADMIN") {
      navigate("/AdminHome", {
        state: {account: {...account}}
      });
    } else {
      navigate("/UserHome", {
        state: {
          account: {...account},
          problem: {...await getCodingProblem(account.currentProblemId)}}
      });
    }
    return Promise.resolve();

  } else {
    setCurrentState({
      ...currentState,
      password: "",
      emailOrIdState: "invalid",
      passwordState: "invalid",
    });
    showFormErrorToast("Invalid username/password.");
    return Promise.reject("Invalid username/password.");
  }
}

const showFormErrorToast = (errorMessage) => {
  toast.error(errorMessage, {
    toastId: errorMessage.toString(),
    position: "top-center",
    theme: "colored",
    autoClose: 5000,
    hideProgressBar: true,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true,
  });
}

export const showCreationSuccessToast = () => {
  toast.success("Account created successfully.", {
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
