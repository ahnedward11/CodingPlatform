
import {setSearchText} from "./ManagementPage";
import {fetchFromDatabase} from "../../../../../Helpers/Functions";
import {COFFEE_CODER_DB_URL} from "../../../../../Data/Urls";
import {toast} from "react-toastify";

// onChange handler for the user search bar
export const handleTextChange = (event) => {
  setSearchText(event.target.value);
}

export async function getAllUsers() {
  return await fetchFromDatabase(COFFEE_CODER_DB_URL + "/user/getAll");
}

export const showDeletionSuccessToast = () => {
  toast.success("User account deleted successfully.", {
    toastId: "toast-success",
    position: "top-center",
    theme: "colored",
    autoClose: 5000,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true,
    hideProgressBar: true
  });
}