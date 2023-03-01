
import React from 'react';
import styled from 'styled-components';

const StyledButton = styled.button`
  position: fixed;
  left: 0.25%;
  bottom: 0.25%;
  max-width: 4.25rem;
  max-height: 5rem;
  color: #000000;
  background-color: #FFF;
  border-radius: 10px;
  border: 2px dashed var(--light-gray);
  cursor: pointer;
  z-index: 99996;
  transition: 0.1s background-color ease-in-out;
  padding: 1.5em;
  
  :hover {
    background-color: #FFFFBF;
  }
`;

const centeredStyle = {
  position: "fixed",
  top: "0",
  left: "0",
  //width: "100vw",
  backgroundColor: "rgba(0, 0, 0, 0.4)",
  zIndex: "99995",
};

const noteStyle = {
  width: "100%",
  height: "100%",
  overflow: "scroll",
  padding: "1.25em"
}

/**
 * Displays a fixed button which, when placed, displays a centered container listing all the user's notes.
 * @returns {false|JSX.Element}
 * @constructor
 */
class DisplayProblemNotes extends React.Component {
  constructor(props) {
    super(props);
    this.myRef = React.createRef();
    this.state = {
      visible: false,
      scrollTop: 0
    };
    this.handleClick = this.handleClick.bind(this);
    this.onScroll = this.onScroll.bind(this);
  }

  handleClick() {
    this.setState({
    ...this.state,
    visible: !this.state.visible
    });

    //this.myRef.current.scrollTop = this.state.scrollTop;
  }

  onScroll() {
    const elementScrollTop = this.myRef.current.scrollTop;

    this.setState({
      ...this.state,
      scrollTop: elementScrollTop
    });
    //console.log(elementScrollTop);
  }

  render() {
    const DisplayStickyNotes = () => {
      const allNotes = this.props.allNotes;
      const stickyNotes = [];

      for (const index in allNotes) {
        stickyNotes.push(
          <div id={"daily-exercise-preview"}
               style={{border: "none", backgroundColor: "var(--light-yellow)",
                 boxShadow: "var(--box-shadow)", padding: "1.5em", margin: "0 0 1.5em 0"}}>
            <div style={{display: "flex", flexDirection: "row", justifyContent: "space-between", width: "100%"}}>
              <h4 style={{marginBottom: "0"}}>{allNotes[index].noteTitle}</h4>
              <h4 style={{marginBottom: "0"}}>{allNotes[index].dateCreated}</h4>
            </div>
            <hr style={{border: "0.5px solid var(--light-gray)"}}/>
            <p style={{whiteSpace: "pre-wrap"}}>
              {allNotes[index].noteContent}
            </p>
          </div>);
      }
      return (
        <>{stickyNotes}</>
      );
    };

    return (
      <>
        <StyledButton onClick={this.handleClick}>
          {this.state.visible ?
            <i className={"fa-solid fa-x"} style={{fontSize: "2rem"}}></i>:
            <i className={"fa-regular fa-note-sticky"} style={{fontSize: "2rem", backgroundColor: "#FFFFBF"}}></i>}
        </StyledButton>
        {this.state.visible &&
          <div className={"problem-sticky-container"}
               style={centeredStyle}>
            <div style={noteStyle} ref={this.myRef} onScroll={this.onScroll}>
              <DisplayStickyNotes />
            </div>
          </div>}
      </>
    );
  }
}

export default DisplayProblemNotes;
