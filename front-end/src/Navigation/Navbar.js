
import React from 'react';
import LeftTabs from "./LeftTabs";
import RightTabs from "./RightTabs";

// Separate the left and right tabs of the navbar into arrays
let navBarTabs = {
  leftAdminTabs: ["Problems", "Daily Exercises", "Reports", "Management"],
  leftUserTabs: ["Problems", "Daily Exercises", "View Coding Problems Completed", "Notes"],
  rightTabs: ["Home", "Settings", "Logout"]
};

/**
 * The Navbar represents the navigational bar near the top of the viewport.
 * Since the navbar is a repeated element of our domain, storing the navbar within a functional component
 * allows us to re-use it and improve readability.
 * @returns {*} the html elements representing the navbar
 * @constructor `props` contains a pageName - the name of the page passed in by the parent component
 */
function Navbar(props) {
  let isAdmin = (props.state.account.role === "ADMIN");

  return (
    <nav>
      {/* If the location of the state is within the list of Admins, render the Admin tabs */}
      <LeftTabs pageName={props.pageName}
                tabName={props.tabName}
                tabs={isAdmin ? navBarTabs.leftAdminTabs : navBarTabs.leftUserTabs}
                state={props.state} />
      <RightTabs pageName={props.pageName}
                 tabName={props.tabName}
                 tabs={navBarTabs.rightTabs}
                 state={props.state} />
    </nav>
  );
}

export default Navbar;
