
import "./style.css";
import React from "react";
import styled from "styled-components";
import Editor from "react-simple-code-editor";
import { highlight, languages } from "prismjs/components/prism-core";
import "prismjs/components/prism-clike";
import "prismjs/components/prism-javascript";
import "prismjs/themes/prism.css";

const codeEditorStyle = {
  width: "100%",
  height: "100%",
  fontFamily: "'Courier New', monospace, monospace",
  fontSize: "0.95rem",
  fontWeight: "800",
  border: "4px solid #666666",
  zIndex: "20"
};

const FadeIn = styled.div`
  width: 100%;
  height: 100%;
  opacity: 0;
  animation: 0.5s fade-in forwards;
  
  @keyframes fade-in {
    100% { opacity: 1; }
  }
`;

const DisplayCodeEditor = (props) => {
  const highlightWithLineNumbers = (input, language) =>
    highlight(input, language)
      .split("\n")
      .map((line, i) => `<span class='editorLineNumber'>${i + 1}</span>${line}`)
      .join("\n");

  // Return the JSX representing the coding problem's fields
  return (
    <div id={"coding-editor-wrapper"}
         data-color-mode="light">
      <h2 style={{marginTop: "0"}}>{props.programmingLanguage}</h2>
      <form style={{height: "100%"}}
            onSubmit={props.handleSubmit}
            spellCheck={false}>
        {/* Enable a fading effect for solutions if `enableFade` is passed as a boolean prop */}
        {props.enableFade ?
          <FadeIn>
            <Editor
              textareaId={"codeArea"}
              className={"editor"}
              style={codeEditorStyle}
              value={props.codeDefaultValue}
              onValueChange={props.handleCodeChange}
              highlight={code => highlightWithLineNumbers(code, languages.js)}
              disabled={props.isSolution}
              padding={15}
            />
          </FadeIn> :
          // Else, render the student's code editor normally
          <Editor
            textareaId={"codeArea"}
            className={"editor"}
            style={codeEditorStyle}
            value={props.codeDefaultValue}
            onValueChange={props.handleCodeChange}
            highlight={code => highlightWithLineNumbers(code, languages.js)}
            padding={15}
          />
        }
        {props.buttonText &&
          <input type={"submit"}
                 className={"button-tab button-compile-code"}
                 value={props.buttonText}></input>}
      </form>
    </div>
  );
}

// Prevent the child component from re-rendering unless the props changed
export default React.memo(DisplayCodeEditor);
