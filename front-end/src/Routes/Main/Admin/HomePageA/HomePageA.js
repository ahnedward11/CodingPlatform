import "./style.css";
import 'font-awesome/css/font-awesome.min.css';
import React, {useEffect, useState} from "react";
import {useLocation} from "react-router";
import {useNavigate} from "react-router-dom";
import Navbar from "../../../../Navigation/Navbar";
import {fetchAdminData, fetchFromDatabase, getSelectedProblems} from "../../../../Helpers/Functions";
import {COFFEE_CODER_DB_URL} from "../../../../Data/Urls";
import {trackPromise} from "react-promise-tracker";
import DisplayLoadingIndicator from "../../../../Components/DisplayLoadingIndicator";
import DisplayRadialBarChart from "../../../../Components/DisplayRadialBarChart";
import {
  getPercentUsersAttempted,
  getPercentUsersNotAttempted,
  getPercentUsersSolved
} from "./HomeLogicA";

export let navigate;
export let currentState, setCurrentState;
export let allProblems, setAllProblems;
export let numOfUsers, setNumOfUsers;

/**
 * The Home page allows the admin to view available problems and hover over for stats.
 */
const HomePageA = () => {
  navigate = useNavigate();
  [currentState, setCurrentState] = useState({
    contentLoaded: false,
    account: useLocation().state.account,
  });
  [numOfUsers, setNumOfUsers] = useState(0);
  [allProblems, setAllProblems] = useState([]);
  const [rerender, setRerender] = useState(false);

  const DisplayProblems = (props) => {
    const selectProblems = allProblems.filter((problem) => problem.difficultyCategory === props.difficulty);

    // Filter only the problems matching the chosen difficulty
    return selectProblems.map((problem) => {
      let cssDifficulty;

      switch (problem.difficultyCategory) {
        case "Easy":
          cssDifficulty = " problem-tile-easy";
          break;
        case "Medium":
          cssDifficulty = " problem-tile-medium";
          break;
        case "Hard":
          cssDifficulty = " problem-tile-hard";
          break;
      }

      return (
        <div className={"admin-problem-tile" + cssDifficulty}>
          <div className={"tile-absolute tile-problem-title"}
               onMouseEnter={() => setRerender(!rerender)}
               onMouseOut={() => setRerender(!rerender)}>
            {problem.problemName}</div>
          <div className={"tile-pie-chart"}
               onMouseOver={() => setRerender(rerender)}
               onMouseOut={() => setRerender(!rerender)}>
            <DisplayRadialBarChart problemName={problem.problemName}
                                   percentUsersSolved={getPercentUsersSolved(problem)}
                                   percentUsersAttempted={getPercentUsersAttempted(problem)}
                                   percentUsersNotAttempted={getPercentUsersNotAttempted(problem)} />
          </div>
        </div>)
    })
  };

    useEffect(() => {
      async function fetchData() {
        await fetchAdminData(COFFEE_CODER_DB_URL, currentState.account)
          .then((result) => {
            setCurrentState({
              contentLoaded: true,
              account: result.account
            });
          });
        setNumOfUsers(await fetchFromDatabase(COFFEE_CODER_DB_URL + "/user/getNumOfUsers"));
        setAllProblems(await getSelectedProblems("All problems"));
      }

      trackPromise(
        fetchData()
      ).catch();
    }, []);

  return (
    <>
      <Navbar pageName={"Home"}
              tabName={"Home"}
              state={{account: {...currentState.account}}} />
      <main id={"main-container"} style={{display: "flex", flexDirection: "row", paddingTop: "0", margin: "0"}}>
        <div id={"admin-home-container"}>
          <div className={"user-greeting-label"}
               style={{width: "100%"}}>Hello, {currentState.account.profileName}!</div>
          <div className={"account-management-container"}
               style={{marginBottom: "0"}}>
            <label htmlFor={"select-difficulty"} style={{width: "100%", fontSize: "1.15rem", fontWeight: "bolder"}}>
              Coding Problem Statistics:
            </label>
          </div>
          <div className={"admin-problem-row"}
               style={{borderBottom: "2px solid black", textAlign: "center", paddingTop: "0.5em"}}>
            <h3 className={"management-column hide-on-media"}
                style={{
                  width: "33.33%",
                  fontSize: "1.15rem",
                  fontWeight: "bold",
                  padding: "0"}}>Easy</h3>
            <h3 className={"management-column hide-on-media"}
                style={{
                  width: "30%",
                  fontSize: "1.15rem",
                  fontWeight: "bold",
                  padding: "0"}}>Medium</h3>
            <h3 className={"management-column hide-on-media"}
                style={{
                  width: "33.33%",
                  fontSize: "1.15rem",
                  fontWeight: "bold",
                  padding: "0"}}>Hard</h3>
          </div>
          {currentState.contentLoaded &&
            <>
              <div id={"management-container"} className={"admin-problem-row"}
                   style={{textAlign: "center", paddingTop: "1em"}}>
                <div className={"admin-problem-column"} >
                  <DisplayProblems difficulty={"Easy"} />
                </div>
                <div className={"admin-problem-column"}>
                  <DisplayProblems difficulty={"Medium"} />
                </div>
                <div className={"admin-problem-column"}>
                  <DisplayProblems difficulty={"Hard"} />
                </div>
              </div>
            </>}
        </div>
        <DisplayLoadingIndicator />
      </main>
    </>
  );
}

export default HomePageA;
