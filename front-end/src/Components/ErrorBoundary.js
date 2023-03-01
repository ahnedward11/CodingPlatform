
import React from 'react'
import { errorBackgroundSrc } from "../Data/Urls";

const buttonStyle = {
  width: "auto",
  fontFamily: "monospace",
  fontSize: "0.75em",
  background: "none",
  padding: "1em 2em",
  margin: "0"
};

class ErrorBoundary extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      hasError: false,
      error: '',
      info: '',
      stack: '' }
    this.handleClick = this.handleClick.bind(this);
  }

  static getDerivedStateFromError(error) {
    // Update state so the next render will show the fallback UI.
    return { hasError: true };
  }

  componentDidCatch(error, errorInfo) {
    // You can also log the error to an error reporting service
    // logErrorToMyService(error, errorInfo)
    this.setState({error, info: errorInfo, stack: error.stack})
  }

  handleClick() {
    window.location.assign("./Login");
  }

  render() {
    if (this.state.hasError) {
      // You can render any custom fallback UI
      return (
        <div style={{
          display: "flex",
          flexFlow: "column nowrap",
          fontFamily: "monospace",
          fontSize: "2.5em",
          textAlign: "center",
          width: "100vw",
          height: "100vh",
          alignItems: "center"}}>
          {errorBackgroundSrc}
          <h4 style={{margin: "30vh 0 0 0"}}>Something went wrong.</h4>
          <button className={"user-profile-upload"}
                  style={buttonStyle}
                  onClick={this.handleClick}>Go back to Login page</button>
        </div>
      );
    }

    return this.props.children;
  }
}

export default ErrorBoundary;
