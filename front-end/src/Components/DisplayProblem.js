
import React from "react";
import parse from "html-react-parser";
import { voteState } from "../Routes/Main/User/CodingProblemPageU/CPSolutionPage/CPSolutionPage";

const optionsStyle = {
  display: "flex",
  padding: "0.5em",
  textAlign: "center"
};

const DisplayProblem = (props) => {
  return (
    <div id={"coding-problem-container"}>
      <div style={{display: "flex", justifyContent: "space-between"}}>
        <p style={{margin: "0"}}><strong>{props.headerText}</strong></p>
        {/* Only render this container if `handleUpvote` or `handleDownvote` was passed as a prop */}
        {(props.handleUpvote || props.handleDownvote) &&
          <div style={{display: "flex", flexDirection: "row", justifyContent: "flex-end"}}>
            <div className={"vote-container"}
                 style={{display: "flex", flexDirection: "row", color: props.voteState.upvoteColor, cursor: "pointer",
                   userSelect: "none", marginRight: "1.5em"}}
                 onClick={props.handleUpvote}>
              <i className="fa-solid fa-thumbs-up" style={{transform: "translateY(10%)", marginRight: "0.5em"}}></i>
              <p style={{display: "flex", flexDirection: "row",margin: "0"}}>
                {voteState.upvoteCount}
              </p>
            </div>
            <div className={"vote-container"}
                 style={{display: "flex", flexDirection: "row", color: props.voteState.downvoteColor, cursor: "pointer",
                   userSelect: "none"}}
                 onClick={props.handleDownvote}>
              <i className="fa-solid fa-thumbs-down" style={{transform: "translateY(10%)", marginRight: "0.5em"}}></i>
              <p style={{margin: "0"}}>
                {voteState.downvoteCount}
              </p>
            </div>
          </div>}
      </div>
      {/* The problem descriptions are already of type JSX */}
      {parse(props.problemDescription)}
      <p>Examples:</p>
      <ul>
        {/* To prevent warnings from appearing in the console, also use the indexes to assign unique keys
              to each `li` element */}
        {props.examples.map((example, index) => {
          return (
            <li style={{listStyleType: "alpha"}}
                key={example.name + index.toString()}>
              {/* The input/output fields are already of type JSX */}
              <div style={{margin: "0.5em 0"}}><strong>Input: </strong>{parse(example.exampleInput)}</div>
              <div style={{margin: "0.5em 0"}}><strong>Output: </strong>{parse(example.exampleOutput)}</div>
            </li>)
        })}
      </ul>
      {/* Include any additional button containers of type JSX that may have been passed as a prop */}
      {props.options &&
        <div style={optionsStyle}>
          {props.options.map((option) => {return option})}
        </div>}
    </div>
  );
};

export default DisplayProblem;
