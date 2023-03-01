
import parse from "html-react-parser";
import "./style.css";
import React, { useEffect, useState } from "react";
import Navbar from "../../../../Navigation/Navbar";
import { useLocation } from "react-router";
import DisplayLoadingIndicator from "../../../../Components/DisplayLoadingIndicator";
import styled from "styled-components";
import { Link } from "react-router-dom";
import { fetchUserData } from "../../../../Helpers/Functions";
import { COFFEE_CODER_DB_URL } from "../../../../Data/Urls";
import { trackPromise } from "react-promise-tracker";

const StyledLink = styled(Link) `
width: fit-content;
text-decoration: underline;
color: #000000;
cursor: pointer;
margin-left: 0.5em;
transition: 0.1s color ease-in;

&:hover {
  color: var(--light-gray);
}
`;

/**
* The Home page allows the user to quickly view their overall progress.
*/
const HomePageU = () => {
  const [currentState, setCurrentState] = useState({
    contentLoaded: false,
    account: useLocation().state.account,
    currentProblem: useLocation().state.problem
  });

  const DisplayHomePage = () => {
    //console.log(currentProblem);
    return (
      <div id={"problems-container"} style={{paddingTop: "1em"}}>
        <div id={"coding-problem-container"}>
          <p style={{margin: "0"}}><strong>
            Current Coding Problem:
            <StyledLink to={"/CurrentCodingProblem"}
                        state={{
                            account: {...currentState.account},
                            problem: {...currentState.currentProblem}}}>
              {currentState.currentProblem.problemName}
            </StyledLink>
          </strong></p>
          {parse(currentState.currentProblem.problemDescription)}
        </div>
      </div>
    );
  }

  /*
    useEffect has three possible configurations:
      - useEffect({...code...}) - this renders the parent component indefinitely
      - useEffect({...code...}, []) - this renders the parent component exactly once with no dependencies ([])
      - useEffect({...code...}, [dep1, dep2]) - this renders the parent component once at component mount, and
          then renders an additional time for each dependency that is updated from a side effect
   */
  useEffect(() => {
    async function fetchData() {
      await fetchUserData(COFFEE_CODER_DB_URL, currentState.account)
        .then((result) => {
          setCurrentState({
            contentLoaded: true,
            account: result.account,
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
      <Navbar pageName={"Home"}
              tabName={"Home"}
              state={{
                account: {...currentState.account},
                problem: {...currentState.currentProblem}}} />
      <main id={"main-container"} style={{display: "block", paddingTop: "0"}}>
        <div className={"user-greeting-label"}>Hello, {currentState.account.profileName}!</div>
        <DisplayHomePage />
      </main>
      <DisplayLoadingIndicator />
    </>
  );
}

export default HomePageU;