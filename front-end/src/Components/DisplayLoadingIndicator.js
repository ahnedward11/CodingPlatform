
import React from 'react';
import { CirclesWithBar } from 'react-loader-spinner';
import { usePromiseTracker } from "react-promise-tracker";

const corneredStyle = {
  position: "fixed",
  bottom: "1%",
  right: "1%",
  backgroundColor: "rgba(255, 255, 255, 0.6)",
  borderRadius: "50%",
  zIndex: "100000",
};

/**
 * Displays a loading screen during a promise retrieval.
 * @returns {false | JSX.Element}
 * @constructor
 */
const DisplayLoadingIndicator = () => {
  const { promiseInProgress } = usePromiseTracker();

  return (
    promiseInProgress &&
    <div style={corneredStyle}>
      <CirclesWithBar color={"#6082B6"}
                      width={"100px"}
                      height={"100px"}/>
    </div>
  );
};

export default DisplayLoadingIndicator;
