
import "./style.css";
import React, {useEffect, useRef, useState} from "react";
import { useLocation } from "react-router";
import {Link, useNavigate} from "react-router-dom";
import {trackPromise} from "react-promise-tracker";
import styled from 'styled-components';

import DisplayLoadingIndicator from "../../../../../Components/DisplayLoadingIndicator";
import {COFFEE_CODER_DB_URL} from "../../../../../Data/Urls";
import {fetchUserData, getUsers} from "../../../../../Helpers/Functions";
import Navbar from "../../../../../Navigation/Navbar";
import {getAllUsers, handleTextChange, showDeletionSuccessToast} from "./ManagementLogic";
import {ToastContainer, Zoom} from "react-toastify";


// We can customize our styling of Links, including hover effects, without altering our css file
const StyledLink = styled(Link) `
  color: var(--black);
  transition: 0.1s color ease-in;
  
  &:hover {
    color: var(--light-gray);
  }
`;

export let admin;
export let allUsers;
export let selectUsers, setSelectUsers
export let searchText, setSearchText;
export let navigate;

/**
 * The Management page allows the admin to delete user accounts.
 */
const ManagementPage = () => {
    let account = useLocation().state;
    navigate = useNavigate();
    const [currentState, setCurrentState] = useState({
        contentLoaded: false,
        account: useLocation().state.account,
        showSelectedUser: false,
        accountDeleted: false
    });
    allUsers = useRef([]);
    [selectUsers, setSelectUsers] = useState(allUsers.current);
    [searchText, setSearchText] = useState("");

    const DisplayUsers = () => {
        // Map out the selected users' account info that we need to return and render to the page
        // Filter only the account info containing the search text (ignoring case)
        return (
            getUsers(searchText, allUsers.current).map((user) => {
                return (
                    <div style={{display: "flex", flexDirection: "row", width: "100%"}}>
                        <div className={"management-column"}
                             style={{width: "30%"}}>
                            {/* We need to pass in two accounts to our state object so that the page's account has admin privileges
              while also being able to display the selected user account */}
                            <StyledLink to={"/DeleteAccount"}
                                        state={{account: {...currentState.account}, user: {...user}}}>{user.profileName}</StyledLink>
                        </div>
                        <p id={"management-id"}
                           className={"management-column"}
                           style={{width: "20%"}}>{user.csulbId}</p>
                        <p className={"management-column"}
                           style={{width: "50%"}}>{user.email}</p>
                    </div>
                );
            }))
    }

    const DisplaySearchResults = () => {
        return (
            <>
                <div className={"account-management-container"}
                     style={{textAlign: "left", borderBottom: "2px solid black", paddingBottom: "0.5em", marginBottom: "0.5em"}}>
                    <h3 className={"management-column"}
                        style={{width: "30%", fontSize: "1.15rem", fontWeight: "bold"}}>Name</h3>
                    <h3 id={"management-id"}
                        className={"management-column"}
                        style={{width: "20%", fontSize: "1.15rem", fontWeight: "bold"}}>CSULB ID</h3>
                    <h3 className={"management-column"}
                        style={{width: "50%", fontSize: "1.15rem", fontWeight: "bold"}}>E-mail</h3>
                </div>
                <DisplayUsers />
            </>
        );
    }

    useEffect(() => {
        // Show the toast if the user came from another page route, the other page passed in a `deletedAccount` field,
        // and this page has not shown the toast before
        if (account.accountDeleted && !currentState.accountDeleted) {
            showDeletionSuccessToast();
        }

        async function fetchData() {
            await fetchUserData(COFFEE_CODER_DB_URL, currentState.account)
                .then((result) => {
                    setCurrentState({
                        contentLoaded: true,
                        account: result.account,
                        currentProblem: result.problem
                    });
                });
            let result = await getAllUsers();
            allUsers.current = result;
            setSelectUsers(result);
        }

        trackPromise(
            fetchData()
        ).catch();

    }, []);

    return (
        <>
            {/* Re-use Navbar component for readability */}
            <Navbar pageName={"Management"}
                    tabName={"Management"}
                    state={{account: {...currentState.account}}} />
            <main id={"main-container"} style={{display: "block", width: "100%", paddingTop: "0"}}>
                <div id={"problems-container"}
                     style={{paddingTop: "2em"}}>
                    <div id={"management-container"}
                         style={{width: "130%", paddingTop: "0"}}>
                        <div className={"account-management-container"} style={{marginBottom: "1em"}}>
                            <label style={{width: "60%", fontSize: "1.15rem", fontWeight: "bolder"}}>User Accounts:</label>
                            <input
                                className={"input-field"}
                                style={{width: "40%", border: "3px solid #666666", borderRadius: "0", outline: "none", fontSize: "1rem"}}
                                onChange={handleTextChange}
                                value={searchText}
                                placeholder={"User Search"} />
                        </div>
                        <div className={"account-management-container"} style={{width: "100%", display: "block", textAlign: "left"}}>
                            {/* Flexibly render all accounts that match our search criteria */}
                            <DisplaySearchResults />
                        </div>
                    </div>
                </div>
            </main>
            <DisplayLoadingIndicator />
            <ToastContainer transition={Zoom} />
        </>
    );
};

export default ManagementPage;