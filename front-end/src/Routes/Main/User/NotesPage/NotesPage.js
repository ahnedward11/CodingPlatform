
import "./style.css";
import React, { useEffect, useState } from "react";
import Navbar from "../../../../Navigation/Navbar";
import { useLocation } from "react-router";
import { ToastContainer, Zoom } from "react-toastify";
import { handleSubmit } from "./NotesLogic";
import { handleTitle, handleNoteContent } from "./NotesLogic";
import { useNavigate } from "react-router-dom";
import DisplayLoadingIndicator from "../../../../Components/DisplayLoadingIndicator";
import DisplayAllNotes from "../../../../Components/DisplayAllNotes";
import {fetchUserData, fetchFromDatabase} from "../../../../Helpers/Functions";
import {trackPromise} from "react-promise-tracker";
import {COFFEE_CODER_DB_URL} from "../../../../Data/Urls";

export let account;
export let currentState, setCurrentState;
export let navigate;

/**
 * The Notes page allows the user create and store notes.
 */
const NotesPage = () => {
  navigate = useNavigate();
  [currentState, setCurrentState] = useState({
    noteId: "",
    noteTitle: "",
    noteContent: "",

    contentLoaded: false,
    account: useLocation().state.account,
    currentProblem: useLocation().state.problem,
    toggleButton: false,
  });

  const title = () => {
    return (
        <input type={"text"}
               name={"noteTitle"}
               className={"input-field"}
               onChange={handleTitle}
               placeholder={"Note Title"} />
    );
  }

  const noteContent = () => {
    return (
        <textarea
               name={"noteContent"}
               className={"input-field"}
               style={{
                 height: "50vh",
                 resize: "none",
                 fontFamily: "'Google Sans', sans-serif, system-ui",
                 border: "2px solid black",
                 whiteSpace: "pre-wrap"}}
               onChange={handleNoteContent}
               placeholder={"Note Content"} />
    );
  }

  useEffect(() => {
    async function fetchData() {
      let notesResult = await fetchFromDatabase(COFFEE_CODER_DB_URL + "/note/getAll");
      await fetchUserData(COFFEE_CODER_DB_URL, currentState.account)
          .then((result) => {
            setCurrentState({
              contentLoaded: true,
              account: result.account,
              currentProblem: result.problem,
              allNotes: notesResult
            });
          });
    }

    trackPromise(
        fetchData()
    ).catch();
  }, []);

  return (
    <div >
      {/* Re-use Navbar component for readability */}
      <Navbar pageName={"Notes"}
              tabName={"Notes"}
              state={{
                account: {...currentState.account},
                problem: {...currentState.currentProblem}}} />
      <main id={"main-container"}
            style={{display: "block", textAlign: "left", lineHeight: "0.75em"}}>
        <button
                id={"page-button"}
                className={"button-tab"}
                style={{width: "75px", fontSize: "1.15rem", cursor: "pointer", padding: "0.5em"}}
                onClick={() => setCurrentState({
                  ...currentState,
                  toggleButton: !currentState.toggleButton
                })}><i className={"fa-solid fa-arrow-right-arrow-left"}></i></button>

        <div>
          <div style={{paddingTop: "1em", paddingBottom: "1em"}}>
          </div>
          <div>
            {!currentState.toggleButton ?

                <div>
                    <div className={"account-management-container"}
                         style={{textAlign: "left", borderBottom: "2px solid black", paddingBottom: "0.5em", marginBottom: "0.5em"}}>
                        <h3 className={"management-column"}
                            style={{width: "20%", fontSize: "1.15rem", fontWeight: "bold"}}>Note ID</h3>
                        <h3 id={"management-id"}
                            className={"management-column"}
                            style={{width: "20%", fontSize: "1.15rem", fontWeight: "bold"}}>Name</h3>
                        <h3 id={"management-id"}
                            className={"management-column"}
                            style={{width: "40%", fontSize: "1.15rem", fontWeight: "bold"}}>Content</h3>
                        <h3 className={"management-column hide-on-media"}
                            style={{width: "20%", fontSize: "1.15rem", fontWeight: "bold"}}>Date Created</h3>
                    </div>
                    <div className={"account-management-container"} style={{display: "block", textAlign: "left"}}>
                        <DisplayAllNotes allNotes={currentState.allNotes} />
                    </div>

                </div>:
                <div className="create">
                    <form onSubmit={handleSubmit}>
                        {title()}
                        {noteContent()}
                        <input type={"submit"}
                               className={"login-button"}
                               value={"Save Note"} />
                    </form>
                </div>


            }
          </div>
        </div>
        <form className={"user-notes-form"}
              onSubmit={handleSubmit}>

          <div className={"button-container"}>

          </div>
        </form>
        <ToastContainer transition={Zoom} />
        <DisplayLoadingIndicator />
      </main>
    </div>

  );
};

export default NotesPage;
