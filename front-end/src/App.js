
import './App.css';
import LoginPage from "./Routes/Login/LoginPage/LoginPage";
import DisplayLoadingIndicator from "./Components/DisplayLoadingIndicator";

function App() {
  return (
    <>
      <LoginPage />
      <DisplayLoadingIndicator/>
    </>
  );
}

export default App;
