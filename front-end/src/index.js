
import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import LoginPage from "./Routes/Login/LoginPage/LoginPage";
import RegistrationPage from "./Routes/Login/RegistrationPage/RegistrationPage";
import HomePageU from "./Routes/Main/User/HomePageU/HomePageU";
import HomePageA from "./Routes/Main/Admin/HomePageA/HomePageA";
import SettingsPage from "./Routes/Main/UserAdmin/SettingsPage/SettingsPage";
import CodingProblemPageU from "./Routes/Main/User/CodingProblemPageU/CodingProblemPageU/CodingProblemPageU";
import CPSelectionPage from "./Routes/Main/User/CodingProblemPageU/CPSelectionPage/CPSelectionPage";
import CPSolutionPage from "./Routes/Main/User/CodingProblemPageU/CPSolutionPage/CPSolutionPage";
import CodingProblemPageA from "./Routes/Main/Admin/CodingProblemPageA/CodingProblemPageA";
import DailyExercisesPageU from "./Routes/Main/User/DailyExercisesPageU/DailyExercisesPageU";
import DailyExercisesPageA from "./Routes/Main/Admin/DailyExercisesPageA/DailyExercisesPageA";
import ViewCPCompletedPage from "./Routes/Main/User/ViewCPCompletedPage/ViewCPCompletedPage";
import NotesPage from "./Routes/Main/User/NotesPage/NotesPage";
import ManagementPage from "./Routes/Main/Admin/ManagementPage/ManagementPage/ManagementPage";
import ReportsPage from "./Routes/Main/Admin/ReportsPage/ReportsPage";
import DeleteAccountPage from "./Routes/Main/Admin/ManagementPage/DeleteAccountPage/DeleteAccountPage";
import ErrorBoundary from "./Components/ErrorBoundary";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  // `React.StrictMode` performs extra checks and warns of potential problems of any descendant containers
  //<React.StrictMode>
    // Display an error screen when an exception is caught from a child component
    <ErrorBoundary>
      {/* BrowserRouter defines the possible Routes between pages within this context */}
      <BrowserRouter>
        <Routes>
          <Route exact path={"/"} element={<App />} />
          <Route path={"Login"} element={<LoginPage />} />
          <Route path={"Registration"} element={<RegistrationPage />} />
          <Route path={"UserHome"} element={<HomePageU />} />
          <Route path={"AdminHome"} element={<HomePageA />} />
          <Route path={"Settings"} element={<SettingsPage />} />
          <Route path={"CurrentCodingProblem"} element={<CodingProblemPageU />} />
          <Route path={"Problems"} element={<CPSelectionPage />} />
          <Route path={"CodingProblemSolution"} element={<CPSolutionPage />} />
          <Route path={"EditProblems"} element={<CodingProblemPageA />} />
          <Route path={"DailyExercises"} element={<DailyExercisesPageU />} />
          <Route path={"EditDailyExercises"} element={<DailyExercisesPageA />} />
          <Route path={"ViewCodingProblemsCompleted"} element={<ViewCPCompletedPage />} />
          <Route path={"Notes"} element={<NotesPage />} />
          <Route path={"Reports"} element={<ReportsPage />} />
          <Route path={"Management"} element={<ManagementPage />} />
          <Route path={"DeleteAccount"} element={<DeleteAccountPage />} />
        </Routes>
      </BrowserRouter>
    </ErrorBoundary>
  //</React.StrictMode>,
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
