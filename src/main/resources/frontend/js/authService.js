const BASE_AUTH_API_URL = "http://localhost:8080/auth";

const registerUser = (registerObj) => axios.post(BASE_AUTH_API_URL + "/register", registerObj);

const loginUser = (username, password) => axios.post(BASE_AUTH_API_URL + "/login", {username, password})

const storeToken = (token) => localStorage.setItem("token", token);

const getToken = () => localStorage.getItem("token");

const saveLoggedInUser = (username, role) => {
    sessionStorage.setItem("authenticatedUser", username);
    sessionStorage.setItem("role", role);


}

const getusername = () => {
    const username = sessionStorage.getItem("authenticatedUser");
    return username;
}


const isUserLoggedIn = () => {
    let user = getusername();

    if(user != null){
        return true;
    }else{
        return false;
    }
}

const logout = () =>{
    sessionStorage.clear();
    localStorage.clear();
}

const isAdminUser = () =>{
    const role = sessionStorage.getItem("role");

    if(role != null && role == "ROLE_ADMIN"){
        return true;
    }else{
        return false;
    }
}

