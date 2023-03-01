
import { currentState, navigate, setCurrentState } from "./RegistrationPage";
import { toast } from "react-toastify";
import { isInteger } from "lodash";
import { trackPromise } from "react-promise-tracker";
import { getModelAttribute } from "../../../Helpers/Functions";
import { COFFEE_CODER_DB_URL, MOCK_CSULB_DB_URL } from "../../../Data/Urls";

const CSULB_ID_LENGTH = 9;
const PROFILE_NAME_LENGTH_MIN = 3;
const PROFILE_NAME_LENGTH_MAX = 25;
const PASSWORD_LENGTH_MIN = 8;
const PASSWORD_LENGTH_MAX = 100;

// Event handlers to listen for changes to user input within text boxes
export const handleEmailOrIdChange = (event) => {
  const input = event.target.value;
  const newEmailOrIdState = (matchesEmailOrIdPattern(input)) ? "valid" : "invalid";

  setCurrentState({
    ...currentState,
    emailOrId: input,
    emailOrIdState: newEmailOrIdState
  });

};

export const handleNameChange = (event) => {
  const input = event.target.value;
  const newProfileNameState = (input.length >= PROFILE_NAME_LENGTH_MIN && input.length <= PROFILE_NAME_LENGTH_MAX) ?
    "valid" : "invalid";

  setCurrentState({
    ...currentState,
    profileName: input,
    profileNameState: newProfileNameState
  });
};

export const handlePasswordChange = (event) => {
  const input = event.target.value;
  const newPasswordState = (input.length >= PASSWORD_LENGTH_MIN && input.length <= PASSWORD_LENGTH_MAX) ?
    "valid" : "invalid";
  let newConfirmPasswordState;

  if (currentState.confirmPasswordState === "default") {
    newConfirmPasswordState = "default";
  } else if (input === currentState.confirmPassword && currentState.confirmPassword !== "") {
    newConfirmPasswordState = "valid";
  } else {
    newConfirmPasswordState = "invalid";
  }

  setCurrentState({
    ...currentState,
    password: input,
    passwordState: newPasswordState,
    confirmPasswordState: newConfirmPasswordState
  });
};

export const handleConfirmPasswordChange = (event) => {
  const input = event.target.value;
  const newConfirmPasswordState = (input === currentState.password && input !== "") ? "valid" : "invalid";

  setCurrentState({
    ...currentState,
    confirmPassword: input,
    confirmPasswordState: newConfirmPasswordState
  });
};

export const handleSubmit = (event) => {
  event.preventDefault();

  // Normalize field inputs
  const form = {
    ...currentState,
    emailOrId: event.target.emailOrId.value.trim().toLowerCase(),
    profileName: event.target.profileName.value.trim(),
    password: event.target.password.value.trim(),
    confirmPassword: event.target.confirmPassword.value.trim(),
  }

  const isValidForm = validateForm(form);

  if (isValidForm) {
    // Display a loading indicator while the fetch requests try to return a promise
    trackPromise(
      verifyUserAdminAccount(event.target.emailOrId.value)
    ).catch((error) => console.log(error));
  }
}

function validateForm(form) {
  // Base case - we're done if all fields are already valid
  if (allFieldsAreValid(form)) {
    return true;
  }

  const emailOrIdState = (form.emailOrIdState === "valid") ? "valid" : "invalid";
  const profileNameState = (form.profileNameState === "valid") ? "valid" : "invalid";
  const passwordState = (form.passwordState === "valid") ? "valid" : "invalid";
  const confirmPasswordState = (form.confirmPasswordState === "valid") ? "valid" : "invalid";

  setCurrentState({
    ...currentState,
    emailOrIdState: emailOrIdState,
    profileNameState: profileNameState,
    passwordState: passwordState,
    confirmPasswordState: confirmPasswordState
  });

  if (emailOrIdState === "invalid") {
    showFormErrorToast("Identity must be a valid CSULB ID or e-mail.");
    setCurrentState((currentState) => ({
      ...currentState,
      emailOrId: ""
    }));
  }

  if (profileNameState === "invalid") {
    showFormErrorToast("Profile name must be between " + PROFILE_NAME_LENGTH_MIN + " and " +
      PROFILE_NAME_LENGTH_MAX + " characters.");
    setCurrentState((currentState) => ({
      ...currentState,
      profileName: ""
    }));
  }

  if (passwordState === "invalid") {
    showFormErrorToast("Password must be between " + PASSWORD_LENGTH_MIN + " and " +
      PASSWORD_LENGTH_MAX + " characters");
    setCurrentState((currentState) => ({
      ...currentState,
      password: "",
      confirmPassword: ""
    }));
  }

  if (confirmPasswordState === "invalid") {
    showFormErrorToast("Passwords must match.");
    setCurrentState((currentState) => ({
      ...currentState,
      password: "",
      confirmPassword: "",
      passwordState: "invalid"
    }));
  }

  return false;
}

function allFieldsAreValid(form) {
  return (matchesEmailOrIdPattern(form.emailOrId) && form.profileNameState === "valid" &&
    form.passwordState === "valid" && form.confirmPasswordState === "valid");
}

function matchesEmailOrIdPattern(input) {
  return (input.match("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$") ||
    (isInteger(+input) && input.length === CSULB_ID_LENGTH) ||
    // placeholder accounts for easy access (development only)
    input === "user" || input === "admin");
}

/**
 * Verifies the input account against the mock CSULB database.
 * We use an async function since, when we call the fetch request, the data will not be immediately
 * available; we must stall the program just long enough for the data to be fetched.
 * @param emailOrId a string of either email or CSULB ID format
 * @returns {Promise<*>} the promise state of the valid account verification
 */
async function verifyUserAdminAccount(emailOrId) {
  const attribute = (isInteger(+emailOrId)) ? "csulbId" : "email";

  // Verify if a matching User exists first
  const user = await getModelAttribute(MOCK_CSULB_DB_URL, "user", attribute, emailOrId);

  // Add the account if the User object returned was not the empty/default User
  if (user.email && user.email !== "") {
    return await addAccount("user", user);
  } else {
    // Then, verify if a matching Admin exists
    return await verifyAdminAccount(attribute, emailOrId);
  }
}

async function verifyAdminAccount(attribute, emailOrId) {
  const admin = await getModelAttribute(MOCK_CSULB_DB_URL, "admin", attribute, emailOrId);

  // Add the account if the Admin object returned was not the empty/default Admin
  if (admin.email && admin.email !== "") {
    return await addAccount("admin", admin);
  } else {
    showFormErrorToast("No identity exists for that CSULB ID or e-mail.");
    setCurrentState({
      ...currentState,
      emailOrId: "",
      emailOrIdState: "invalid"
    });
    return Promise.reject("No matching identity was found.");
  }
}

/**
 * Adds a verified account to the Coffee Coder database.
 * Throws an error toast if the account entry is a duplicate.
 * @param accountType the type of the account - either user or admin
 * @param account the account as a JSON object
 * @returns {Promise<any>} the promise state of the account posting
 */
async function addAccount(accountType, account) {
  const csulbId = account.csulbID,
    email = account.email,
    password = currentState.password,
    profileName = currentState.profileName;
  const newAccount = {csulbId, email, password, profileName};

  return await fetch(COFFEE_CODER_DB_URL + "/" + accountType + "/add?csulbId=" + csulbId, {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(newAccount)

  }).then(result => result.json())
    .then((result) => {
      if (result === 0) {
        // Redirect to the designated page
        navigate("/Login", {state: {verifiedAccount: true}});
        return Promise.resolve();

      } else if (result === -1) {
        showFormErrorToast("An account already exists for that CSULB ID or e-mail.");
        setCurrentState({
          ...currentState,
          emailOrId: "",
          emailOrIdState: "invalid"
        });
        return Promise.reject("An account already exists for that CSULB ID or e-mail.");
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
