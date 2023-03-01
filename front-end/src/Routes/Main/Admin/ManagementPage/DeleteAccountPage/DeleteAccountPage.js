
import "./style.css";
import Navbar from "../../../../../Navigation/Navbar";
import { useLocation } from "react-router";
import {ToastContainer, Zoom} from "react-toastify";
import { handleDeletion } from "./DeleteAccountLogic";
import {useEffect, useState} from "react";
import {fetchUserData} from "../../../../../Helpers/Functions";
import {COFFEE_CODER_DB_URL} from "../../../../../Data/Urls";
import {trackPromise} from "react-promise-tracker";
import {useNavigate} from "react-router-dom";

const customMarginStyle = {
  margin: "0.5em 0"
}

export let navigate;
export let currentState, setCurrentState;

/**
 * The Delete Account page allows the admin to delete user accounts.
 */
const DeleteAccountPage = () => {
  navigate = useNavigate();
  [currentState, setCurrentState] = useState({
    contentLoaded: false,
    account: useLocation().state.account,
    user: useLocation().state.user,
    currentProblem: "",
  });


  const DisplayUser = () => {
    return  (
        <form className={"user-settings-form"}
              onClick={handleDeletion}>
          <div className={"user-settings-field"}>
            <label style={customMarginStyle}>Profile Name:</label>
            {/* Render the style of the input box depending on the toggle value */}
            <p style={customMarginStyle}>{currentState.user.profileName}</p>
          </div>
          <div className={"user-settings-field"}>
            <label style={customMarginStyle}>CSULB ID:</label>
            <p style={customMarginStyle}>{currentState.user.csulbId}</p>
          </div>
          <div className={"user-settings-field"}>
            <label style={customMarginStyle}>E-mail:</label>
            <p style={customMarginStyle}>{currentState.user.email}</p>
          </div>
          <div className={"user-settings-field"}>
            <label style={customMarginStyle}>Current Coding Problem:</label>
            <p style={customMarginStyle}>{currentState.currentProblem.problemName}</p>
          </div>
          <div className={"user-settings-field"}>
            <label style={customMarginStyle}>Account Creation Date:</label>
            <p style={customMarginStyle}>{currentState.user.creationDate}</p>
          </div>
          <div className={"user-settings-field"}>
            <label style={customMarginStyle}>Last Login:</label>
            <p style={customMarginStyle}>{currentState.user.lastLoginTimestamp}</p>
          </div>
          <div className={"button-container"}>
            <input type={"submit"}
                   className={"button-tab button-edit button-delete"}
                   value={"Delete"} />
          </div>
        </form>
    );
  }

  useEffect(() => {
    async function fetchData() {
      await fetchUserData(COFFEE_CODER_DB_URL, currentState.user)
        .then((result) => {
          setCurrentState({
            contentLoaded: true,
            account: {...currentState.account},
            user: result.account,
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
      <Navbar pageName={"Delete Account"}
              tabName={"Management"}
              state={{
                account: {...currentState.account},
                user: {...currentState.user}}} />
      <main id={"main-container"}>
        <div>
          <div className={"user-profile-wrapper"}>
            <div className={"user-profile-label"}>User Profile</div>
            <img className={"user-profile-img"}
                 src={"https://icon-library.com/images/anonymous-person-icon/anonymous-person-icon-18.jpg"}
                 alt={"Visual representation of current profile."}></img>
          </div>
        </div>
        <DisplayUser />
        <ToastContainer transition={Zoom} />
      </main>
    </>
  );
};

export default DeleteAccountPage;
