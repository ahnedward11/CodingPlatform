
import React from 'react';
import styled from 'styled-components';
import { Link } from "react-router-dom";

const highlightedStyle = {
  backgroundColor: "#0C3649"
};

const Ul = styled.div`
  display: none;
  list-style: none;
  flex-flow: row nowrap;
  
  @media (max-width: 1050px) {
    display: flex;
    position: fixed;
    flex-flow: column nowrap;
    top: -5%;
    left: 0;
    width: 300px;
    height: 105vh;
    background-color: var(--black);
    opacity: 0.95;
    padding: 7em 0 0 0;
    transform: ${({ open }) => open ? 'translateX(0)' : 'translateX(-100%)'};
    transition: 0.3s transform ease-in-out;
`;

const StyledLink = styled(Link)`
  color: #fff;
  cursor: pointer;
  user-select: none;
  text-decoration: none;
  padding: 1em;
  transition: 0.1s background-color ease-out;
  
  :hover {
    background-color: #0C3649;
  }
`;

const SlideNav = (props) => {
  const isAdmin = (props.state.account.role === "ADMIN");

  return (
    <Ul open={props.open}>
      {props.tabs.map((tabName) => {
        let joinedTabName;
        if (tabName === "Problems") {
          joinedTabName = isAdmin ?
            "EditProblems": // Admins only
            "CurrentCodingProblem"; // Users only
        } else if (tabName === "Daily Exercises") {
          joinedTabName = isAdmin ?
            "EditDailyExercises": // Admins only
            "DailyExercises"; // Users only
        } else if (tabName === "Home") {
          joinedTabName = isAdmin ?
            "AdminHome" :
            "UserHome";
        } else {
          joinedTabName = tabName.replaceAll(" ", "");
        }
        return <StyledLink to={"/" + joinedTabName}
                           state={props.state}
                           style={props.tabName === tabName ? highlightedStyle : null}
                           key={tabName}>{tabName}</StyledLink>
      })}
    </Ul>
  )
}

export default SlideNav;