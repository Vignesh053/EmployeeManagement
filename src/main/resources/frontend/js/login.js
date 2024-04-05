const registerButton = document.getElementById("registerbtn");

const handleRegister = () => {
    location.href = "register.html";
}

registerButton.addEventListener("click", handleRegister);


const loginForm = document.getElementById("loginform");
const usernameText = document.getElementById("username");
const passwordText = document.getElementById("password");

const handleLogin = async (e)=>{
    
    e.preventDefault();

    const username = usernameText.value;
    const password = passwordText.value;

    const response = await loginUser(username, password);

    console.log(response);
    const role = response.data.role;
    const jwtToken = "Bearer " + response.data.accessToken;

    storeToken(jwtToken);

    saveLoggedInUser(username, role);

    location.href = "index.html";
}

loginForm.addEventListener('submit', (e) => handleLogin(e));