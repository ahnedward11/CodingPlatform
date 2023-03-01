
import React from "react";

const DisplayExerciseStickyNote = (props) => {
  return (
    <div id={"daily-exercise-preview"}
       className={"sticky-container"}
       style={{width: "auto", height: "fit-content", border: "none", backgroundColor: "var(--light-yellow)",
         boxShadow: "var(--box-shadow)", padding: "1.5em"}}>
    <p>{props.headerText}</p>
    <ul>
      {props.problemCategories.map((exercise) => {
        return (
          <li key={exercise.category}>{exercise.category}</li>
        );
      })}
    </ul>
    </div>
  );
};

export default DisplayExerciseStickyNote;
