
import "./style.css";
import React, {useEffect, useState} from "react";
import { useNavigate } from "react-router-dom";
import {
  handleConfirmPasswordChange,
  handleEmailOrIdChange,
  handleNameChange,
  handlePasswordChange,
  handleSubmit
} from "./RegistrationLogic";
import { ToastContainer, Zoom } from "react-toastify";
import DisplayLoadingIndicator from "../../../Components/DisplayLoadingIndicator";
import {useLocation} from "react-router";

const validStyle = {
  border: "2px solid var(--color-correct)"
};

const invalidStyle = {
  border: "2px solid var(--color-incorrect)"
};

export let navigate;
export let currentState, setCurrentState;
export let users, setUsers;

/* Use a functional component to enable the use of the `useNavigate` hook upon form submission;
   We can also keep track of state and update to the RegistrationPage using the original `props` */
const RegistrationPage = () => {
  let account = useLocation().state;
  navigate = useNavigate();
  [currentState, setCurrentState] = useState({
    emailOrId: "",
    profileName: "",
    password: "",
    confirmPassword: "",
    emailOrIdState: "default",
    profileNameState: "default",
    passwordState: "default",
    confirmPasswordState: "default"
  });
  [users, setUsers] = useState([]);

  const getStateStyle = (inputState) => {
    switch (inputState) {
      case "valid":
        return validStyle;
      case "invalid":
        return invalidStyle;
      default:
        return {};
    }
  }

  const EmailOrIdInput = () => {
      return (
        <input type={"text"}
               name={"emailOrId"}
               className={"input-field"}
               style={getStateStyle(currentState.emailOrIdState)}
               onChange={handleEmailOrIdChange}
               value={currentState.emailOrId}
               placeholder={"CSULB ID or E-mail"} />
      );
  };

  const ProfileNameInput = () => {
    return (
      <input type={"text"}
             name={"profileName"}
             className={"input-field"}
             style={getStateStyle(currentState.profileNameState)}
             onChange={handleNameChange}
             value={currentState.profileName}
             placeholder={"Profile Name"} />
    );
  };

  const PasswordInput = () => {
    return (
      <input type={"password"}
             name={"password"}
             className={"input-field"}
             style={getStateStyle(currentState.passwordState)}
             onChange={handlePasswordChange}
             value={currentState.password}
             placeholder={"Password"} />
    );
  };

  const ConfirmPasswordInput = () => {
    return (
      <input type={"password"}
             name={"confirmPassword"}
             className={"input-field"}
             style={getStateStyle(currentState.confirmPasswordState)}
             onChange={handleConfirmPasswordChange}
             value={currentState.confirmPassword}
             placeholder={"Confirm Password"} />
    );
  };

  useEffect(() => {
    window.onpopstate = () => {
      navigate("/Login", {state: {...account, fromRegistration: true}});
    }
  }, []);

  return (
    <div className={"login-page"}>
      <main className={"login-container"}>
        <form onSubmit={handleSubmit}>
          <h1 className={"header-text"}>Registration</h1>
          {EmailOrIdInput()}
          {ProfileNameInput()}
          {PasswordInput()}
          {ConfirmPasswordInput()}
          <input type={"submit"}
                 className={"login-button"}
                 value={"Create Account"} />
        </form>
      </main>
      <DisplayLoadingIndicator />
      <ToastContainer transition={Zoom} />
    </div>
  );
};

export default RegistrationPage;
