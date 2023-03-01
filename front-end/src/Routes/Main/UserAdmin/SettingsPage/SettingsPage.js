
import "./style.css";
import React, { useEffect, useState } from "react";
import Navbar from "../../../../Navigation/Navbar";
import { useLocation } from "react-router";
import { ToastContainer, Zoom } from "react-toastify";
import {
  handlePhotoChange,
  handleProfileNameChange,
  handleSubmit,
} from "./SettingsLogic";
import DisplayLoadingIndicator from "../../../../Components/DisplayLoadingIndicator";
import { fetchUserData } from "../../../../Helpers/Functions";
import {COFFEE_CODER_DB_URL, defaultImgSrc} from "../../../../Data/Urls";
import { trackPromise } from "react-promise-tracker";

// We can define styling in-line, or ahead of time as JSON objects as shown below
const absoluteStyle = {
  position: "absolute",
  fontSize: "1.25rem",
  marginTop: "0.8em"
};

const hideInputBoxStyle = {
  position: "absolute",
  border: "none",
  cursor: "normal",
  color: "var(--black)",
  backgroundColor: "var(--white)",
  fontSize: "1.25rem",
  marginTop: "0.8em",
  marginLeft: "0",
  transform: "translateX(-50)"
}

export let currentState, setCurrentState;

/**
 * The Settings page allows the user to change their profile photo or username.
 */
const SettingsPage = () => {
  [currentState, setCurrentState] = useState({
    contentLoaded: false,
    account: useLocation().state.account,
    profileName: "",
    profileImgSrc: "",
    profileImgFile: "",
    currentProblem: useLocation().state.problem,
    toggleButton: true
  });

  /*
    useEffect has three possible configurations:
      - useEffect({...code...}) - this renders the parent component indefinitely
      - useEffect({...code...}, []) - this renders the parent component exactly once with no dependencies ([])
      - useEffect({...code...}, [dep1, dep2]) - this renders the parent component once at component mount, and
          then renders an additional time for each dependency that is updated from a side effect
   */
  useEffect(() => {
    // setState calls are asynchronous; we must set the current state values after fetching data
    // to ensure that the values are updated by the time we try to access them in other synchronous components
    async function fetchData() {
      await fetchUserData(COFFEE_CODER_DB_URL, currentState.account)
        .then((result) => {
          setCurrentState({
            ...currentState,
            contentLoaded: true,
            account: result.account,
            profileName: result.account.profileName,
            profileImgSrc: result.account.profileImgSrc,
            currentProblem: result.problem
          });
        });
    }

    trackPromise(
      fetchData()
    ).catch();
  }, []);

  return (
    <>
      {/* Re-use Navbar component for readability */}
      <Navbar pageName={"Settings"}
              tabName={"Settings"}
              state={{
                account: {...currentState.account},
                problem: {...currentState.currentProblem}}} />
      <main id={"main-container"}>
        {currentState.contentLoaded &&
          <>
            <div className={"user-profile-wrapper"}>
              <div className={"user-profile-label"}>User Profile</div>
              <img className={"user-profile-img"}
                   src={currentState.profileImgSrc}
                   alt={"Visual representation of current profile."}
                   defaultValue={defaultImgSrc}/>
              {/* Render the upload button only if in edit mode */}
              <div style={currentState.toggleButton ? {visibility: "hidden"} : {visibility: "visible"}}>
                <input name={"profile-img-src"}
                       style={{width: "100%", fontSize: "1.15rem", marginTop: "1em"}}
                       onChange={handlePhotoChange}
                       placeholder={"Image URL address"}/>
                {/*<input name={"profile-img-file"}
                       type={"button"}
                       style={{width: "auto", margin: "auto"}}
                       value={"Upload Photo"}
                       onClick={() => {document.getElementById('file').click();}}
                       className={"user-profile-upload"}
                       onChange={handlePhotoChange} />
                <input id={"file"}
                       name={"file"}
                       type={"file"}
                       style={{display: "none"}}
                       onChange={handlePhotoChange} />*/}
                </div>
            </div>
            <form className={"user-settings-form"}
                  onSubmit={handleSubmit}>
              <div className={"user-settings-input"}>
                <label htmlFor={"profile-name"}>Profile Name:</label>
                {/* Render the style of the input box depending on the toggle value */}
                <input name={"profile-name"}
                       style={currentState.toggleButton ?
                         hideInputBoxStyle :
                         absoluteStyle}
                       onChange={handleProfileNameChange}
                       value={currentState.profileName}
                       disabled={currentState.toggleButton} />
              </div>
              <div className={"user-settings-field"}>
                <label>CSULB ID:</label>
                <p>{currentState.account.csulbId}</p>
              </div>
              <div className={"user-settings-field"}>
                <label>E-mail:</label>
                <p>{currentState.account.email}</p>
              </div>
              {currentState.account.role === "USER" &&
              <div className={"user-settings-field"}>
                <label>Current Coding Problem:</label>
                <p>{currentState.currentProblem.problemName}</p>
              </div>}
              <div className={"button-container"}>
                {/* Render a specific button based on the state's current toggle value */}
                {currentState.toggleButton ?
                  <button className={"button-tab button-edit no-media-margin"}
                          onClick={() => setCurrentState({
                            ...currentState,
                            toggleButton: !currentState.toggleButton
                          })}>Edit</button> :
                  <input type={"submit"}
                         className={"button-tab button-edit no-media-margin"}
                         value={"Save Profile"} />}
              </div>
            </form>
          </>}
      </main>
      <DisplayLoadingIndicator />
      <ToastContainer transition={Zoom} />
    </>
  );
};

export default SettingsPage;
