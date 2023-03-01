
import React from 'react';

/**
 * Returns a truncated String.
 * The shortened string will be abbreviated with an ellipses.
 * @param string the String to shorten
 * @param maxLength the maximum length the String can be before being abbreviated
 * @returns {*} the shortened String
 */
function truncateString(string, maxLength) {
  // Return the String if it's already below the indicated length
  if (string.length <= maxLength) {
    return string;
  }

  return string.substring(0, maxLength) + "...";
}

/**
 * Displays a fixed button which, when placed, displays a centered container listing all the user's notes.
 * @returns {false|JSX.Element}
 * @constructor
 */
class DisplayAllNotes extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      visible: false,
      scrollTop: 0
    };
  }

  render() {
    const DisplayStickyNotes = () => {
      const allNotes = this.props.allNotes;
      const rowNotes = [];

      for (const index in allNotes) {
        rowNotes.push(
          <div style={{display: "flex", flexDirection: "row", lineHeight: "1.15rem", width: "100%"}}>
            <p id={"management-id"}
               className={"management-column"}
               style={{width: "20%"}}>{allNotes[index].noteId}</p>
            <p id={"management-id"}
               className={"management-column"}
               style={{width: "20%"}}>{allNotes[index].noteTitle}</p>
            <p id={"management-id"}
               className={"management-column"}
               style={{width: "40%"}}>{truncateString(allNotes[index].noteContent, 50)}</p>
            <p className={"management-column hide-on-media"}
               style={{width: "20%"}}>{allNotes[index].dateCreated}</p>
          </div>);
      }
      return (
        <>{rowNotes}</>
      );
    };

    return (
      <>
        <div>
          <DisplayStickyNotes />
        </div>
      </>
    );
  }
}

export default DisplayAllNotes;
