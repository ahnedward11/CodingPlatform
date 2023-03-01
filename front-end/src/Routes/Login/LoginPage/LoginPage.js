
import "./style.css";
import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, Zoom } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import {
  handleEmailOrIdChange,
  handlePasswordChange,
  handleSubmit,
  showCreationSuccessToast } from "./LoginLogic";
import { useLocation } from "react-router";
import DisplayLoadingIndicator from "../../../Components/DisplayLoadingIndicator";

const validStyle = {
  border: "2px solid var(--color-correct)"
};

const invalidStyle = {
  border: "2px solid var(--color-incorrect)"
};

export let navigate;
export let currentState, setCurrentState;

/* Use a functional component to enable the use of the `useNavigate` hook upon form submission;
   We can also keep track of state and update to the LoginPage using the original `props` */
const LoginPage = () => {
  let account = useLocation().state;
  navigate = useNavigate();
  [currentState, setCurrentState] = useState({
    emailOrId: "",
    password: "",
    emailOrIdState: "default",
    passwordState: "default",
    verifiedAccount: false
  });

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

  useEffect(() => {
    // Show the toast if the user came from another page route, the other page passed in a `verifiedAccount` field,
    // and this page has not shown the verification toast before
    if (account && account.verifiedAccount && !currentState.verifiedAccount) {
      showCreationSuccessToast();

      setCurrentState({
        ...currentState,
        verifiedAccount: true
      });
    }
  }, [])

  return (
    <div className={"login-page"}>
      <main className={"login-container"}>
        <form onSubmit={handleSubmit}>
          <h1 className={"header-text"}>Coffee Coder</h1>
          {EmailOrIdInput()}
          {PasswordInput()}
          <input type={"submit"}
                 className={"login-button"}
                 value={"Sign In"} />
        </form>
        <Link to={"/Registration"}
              className={"link"}>Create Account</Link>
      </main>
      <DisplayLoadingIndicator />
      <ToastContainer transition={Zoom} />
    </div>
  );
};

export default LoginPage;
