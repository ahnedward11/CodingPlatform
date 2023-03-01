
import styled from 'styled-components';
import { Link } from "react-router-dom";
import { useState } from "react";
import SlideNav from "./SlideNav";

// We can define styling in-line, or ahead of time as JSON objects as shown below
const headerStyle = {
  fontSize: "2rem",
  margin: "0"
};

const highlightedStyle = {
  backgroundColor: "var(--white)"
};

const StyledBurger = styled.div`
  display: none;
  position: relative;
  width: 2em;
  height: 2em;
  z-index: 20;
  
  @media (max-width: 1050px) {
    display: flex;
    justify-content: space-around;
    flex-flow: column nowrap;
    cursor: pointer;
    margin: 1.5em;
  }
  
  div {
    width: 2em;
    height: 0.25rem;
    background-color: ${({ open }) => open ? '#ccc' : '#333'};
    border-radius: 10px;
    transform-origin: 1px;
    transition: all 0.3s linear;
    
    &:nth-child(1) {
      transform: ${({ open }) => open ? 'rotate(45deg)' : 'rotate(0)'};
    }
    
    &:nth-child(2) {
      opacity: ${({ open }) => open ? 0 : 1};
    }
    
    &:nth-child(3) {
      transform: ${({ open }) => open ? 'rotate(-45deg)' : 'rotate(0)'};
    }
  }
`;

/**
 * The LeftTabs represents the left side of the navbar.
 * On desktop devices, LeftTabs displays all the tabs without collapsing them. On mobile devices,
 * LeftTabs should display a hamburger icon which, when pressed, will extend to show a condensed menu.
 * @param props the properties passed in from the parent component
 * @returns {JSX.Element} the html elements representing the left tabs of the navbar
 * @constructor `props` contains a tabName - the name of the page passed in by the parent component, and
 *              a tabs - the array of Strings representing the tabs' names
 */
function LeftTabs(props) {
  const [open, setOpen] = useState(false);
  const isAdmin = (props.state.account.role === "ADMIN");

  return (
    <>
      <>
        <StyledBurger open={open} onClick={() => setOpen(!open)}>
          {/* Render the three lines of the hamburger icon */}
          <div />
          <div />
          <div />
        </StyledBurger>
        <SlideNav open={open} tabs={props.tabs} tabName={props.tabName} state={props.state} />
      </>
      <div id={"navbar-left-tabs"}>
        <div id={"navbar-left-top"}>
          <h1 style={headerStyle}>{props.pageName}</h1>
        </div>
        <div id={"navbar-left-bottom"}>
          {props.tabs.map((tabName) => {
            let joinedTabName;
            if (tabName === "Problems") {
              joinedTabName = isAdmin ?
                "EditProblems" : // Admins only
                "CurrentCodingProblem"; // Users only
            } else if (tabName === "Daily Exercises") {
              joinedTabName = isAdmin ?
                "EditDailyExercises" : // Admins only
                "DailyExercises"; // Users only
            } else {
              joinedTabName = tabName.replaceAll(" ", "");
            }
            return <Link to={"/" + joinedTabName}
                         state={props.state}
                         style={props.tabName === tabName ? highlightedStyle : null}
                         className={"button-tab"}
                         key={tabName}>{tabName}</Link>
          })}
        </div>
      </div>
    </>
  );
}

export default LeftTabs;
