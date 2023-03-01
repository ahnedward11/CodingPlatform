
import React from 'react';
import styled from 'styled-components';

const StyledButton = styled.button`
  position: fixed;
  right: 0.25%;
  bottom: 0.25%;
  max-width: 4.25rem;
  max-height: 5rem;
  color: #000000;
  background-color: #FFF;
  border-radius: 10px;
  border: 2px dashed var(--light-gray);
  cursor: pointer;
  z-index: 99998;
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
  zIndex: "99997",
};

const noteStyle = {
  width: "100%",
  height: "100%",
  overflow: "scroll",
  padding: "1em 2em 3em 2em"
}

/**
 * Displays a fixed button which, when placed, displays a centered container listing all the user's notes.
 * @returns {false|JSX.Element}
 * @constructor
 */
class DisplayCodeConsole extends React.Component {
  constructor(props) {
    super(props);
    this.myRef = React.createRef();
    this.state = {
      visible: this.props.showConsole,
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
    const DisplayConsole = () => {
      return (
        <>
          <p style={{
            color: "#EEE",
            backgroundColor: "#333",
            width: "100%",
            height: "100%",
            resize: "none",
            fontFamily: "monospace, system-ui",
            border: "4px solid #555",
            borderRadius: "5px",
            whiteSpace: "pre-wrap",
            padding: "1em"}}>
            {this.props.content}
          </p>
        </>
      );
    };

    return (
      <>
        <StyledButton onClick={this.handleClick}>
          {this.state.visible ?
            <i className={"fa-solid fa-x"} style={{fontSize: "2rem"}}></i>:
            <i className={"fa-solid fa-terminal"} style={{fontSize: "2rem"}}></i>}
        </StyledButton>
        {this.state.visible &&
          <div className={"problem-sticky-container"}
               style={centeredStyle}>
            <div style={noteStyle} ref={this.myRef} onScroll={this.onScroll}>
              <DisplayConsole />
            </div>
          </div>}
      </>
    );
  }
}

export default DisplayCodeConsole;
