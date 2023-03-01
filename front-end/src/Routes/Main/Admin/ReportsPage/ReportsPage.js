
import "./style.css";
import 'font-awesome/css/font-awesome.min.css';
import React, {useEffect, useState} from "react";
import { useLocation } from "react-router";
import {ToastContainer, Zoom} from "react-toastify";
import styled from "styled-components";
import Navbar from "../../../../Navigation/Navbar";
import reports from "../../../../Data/Reports";
import DisplayProblem from "../../../../Components/DisplayProblem";
import DisplayCodeEditor from "../../../../Components/DisplayCodeEditor";
import {getCodingProblemData, getReporter, getReports, getSolutionCode} from "../../../../Helpers/Functions";
import {
  handleChangeReport,
  handleCodeChange,
  handlePostSolution,
  handleShowSelection
} from "./ReportsLogic";
import {admins} from "../../../../Data/Accounts";
import {useNavigate} from "react-router-dom";

export const StyledParagraph = styled.p `
  width: fit-content;
  text-decoration: underline;
  cursor: pointer;
  margin: 0;
  transition: 0.1s color ease-in;
  
  &:hover {
    color: var(--light-gray);
  }
`;

export let account;
export let currentState, setCurrentState;
export let code, setCode;
export let navigate;

/**
 * The Reports page allows admins to view reported problems and post new solutions for them.
 */
const ReportsPage = () => {
  account = admins[0];
  navigate = useNavigate();
  [currentState, setCurrentState] = useState({
    contentLoaded: false,
    account: useLocation().state.account,
    currentReport: reports[0],
    currentProblem: getCodingProblemData(reports[0].codingProblemName),
    showSelection: true
  });
  console.log(useLocation().state.account);
  [code, setCode] = useState(currentState.currentProblem.codeTemplate);

  const DisplaySelection = () => {
    return (
      <div id={"management-container"}>
        <h3 style={{fontSize: "1.15rem"}}>Select a Reported Solution:</h3>
        <div className={"account-management-container"}
             style={{textAlign: "left", borderBottom: "2px solid black", paddingTop: "0.5em", paddingBottom: "0.5em",
               marginBottom: "0.5em"}}>
          <h3 className={"management-column"}
              style={{width: "40%", fontSize: "1.15rem", fontWeight: "bold"}}>Name</h3>
          <h3 className={"management-column"}
              style={{width: "20%", fontSize: "1.15rem", fontWeight: "bold"}}>Date Reported</h3>
          <h3 className={"management-column hide-on-media"}
              style={{width: "20%", fontSize: "1.15rem", fontWeight: "bold"}}>User</h3>
          <h3 className={"management-column"}
              style={{width: "20%", fontSize: "1.15rem", fontWeight: "bold"}}>Admin Reviewed</h3>
        </div>
        <div className={"account-management-container"} style={{display: "block", textAlign: "left"}}>
          {/* Render all reports */}
          <DisplayReports />
        </div>
      </div>
    );
  }

  // Display all reports
  const DisplayReports = () => {
    let reports = getReports();
    return reports.map((report) => {
      return (
        <div style={{display: "flex", flexDirection: "row", width: "100%"}}>
          <div className={"management-column"}
               style={{width: "40%"}}>
            <StyledParagraph onClick={() => handleChangeReport(report)}>
              {report.codingProblemName}
            </StyledParagraph>
          </div>
          <p className={"management-column"}
             style={{width: "20%"}}>{report.dateReported}</p>
          <p className={"management-column hide-on-media"}
             style={{width: "20%"}}>
            {getReporter(report)}</p>
          <p style={{width: "20%", margin: "0"}}>
            {report.hasBeenReviewed ?
              "Yes" :
              "No"}
          </p>
        </div>)
    })
  };

  useEffect(() => {
    /*
    async function fetchData() {
      await fetchAccountProblemData(COFFEE_CODER_DB_URL, currentState.account)
        .then((result) => {
          setCurrentState({
            contentLoaded: true,
            account: result.account,
            currentProblem: result.problem
          });
        });
    }

    trackPromise(
      fetchData()
    ).catch();
     */
  }, []);

  return (
    <>
      {/* Re-use Navbar component for readability */}
      <Navbar pageName={"Report Management"}
              tabName={"Reports"}
              state={{account: {...currentState.account}}} />
      <main id={"main-container"} style={{display: "block", paddingTop: "0"}}>
        <button id={"page-button"}
                className={"button-tab"}
                style={{width: "75px", fontSize: "1.15rem", cursor: "pointer", padding: "0.5em", marginTop: "1em"}}
                onClick={handleShowSelection}>
          <i className={"fa-solid fa-arrow-right-arrow-left"}></i></button>
        {currentState.showSelection ?
          <DisplaySelection /> :
          <div id={"problems-container"}
               style={{paddingTop: "1em"}}>
            <DisplayProblem
              headerText={"Coding Problem Selected: " + currentState.currentProblem.name}
              problemDescription={currentState.currentReport.reportedDescription}
              examples={currentState.currentProblem.examples} />
            <DisplayCodeEditor
              programmingLanguage={"Java"}
              handleSubmit={handlePostSolution}
              handleCodeChange={handleCodeChange}
              codeDefaultValue={getSolutionCode(currentState.currentProblem)}
              buttonText={"Post Solution"} />
          </div>}
        <ToastContainer transition={Zoom} />
      </main>
    </>
  );
};

export default ReportsPage;
