
import { Link } from "react-router-dom";

// We can define styling in-line, or ahead of time as JSON objects as shown below
const highlightedStyle = {
  backgroundColor: "var(--white)"
};

/**
 * The RightTabs represents the right side of the navbar.
 * @param props the properties passed in from the parent component
 * @returns {JSX.Element} the html elements representing the right tabs of the navbar
 * @constructor `props` contains a pageName - the name of the page passed in by the parent component, and
 *              a tabs - the array of Strings representing the tabs' names
 */
function RightTabs(props) {
  const isAdmin = (props.state.account.role === "ADMIN");
  const redirect = {
    ...props.state,
    loggedOut: true
  };

  return (
    <div id={"navbar-right-tabs"}>
      {props.tabs.map((tabName) => {
        let joinedTabName = tabName.replaceAll(" ", "");
        if (joinedTabName === "Home") {
          joinedTabName = isAdmin ?
            "AdminHome" :
            "UserHome";
        }
        return <Link to={"/" + ((joinedTabName === "Logout") ? "Login" : joinedTabName)}
                     state={(joinedTabName === "Logout") ? redirect : props.state}
                     style={props.pageName === tabName ? highlightedStyle : null}
                     className={"button-tab"}
                     key={tabName}>{tabName}</Link>
      })}
    </div>
  );
}

export default RightTabs;
