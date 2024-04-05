const loginButton = document.getElementById("loginbtn");

const handleLogin = () =>{
    location.href = "login.html";
}

loginButton.addEventListener("click", handleLogin);


const registerForm = document.getElementById("registerform");
const usernameText = document.getElementById("username");
const emailText = document.getElementById("email");
const passwordText = document.getElementById("password");

const handleRegister = async(e) => {
    e.preventDefault();

    let username = usernameText.value;
    let email = emailText.value;
    let password = passwordText.value;

    const registerObj = {username, email, password};

    try {
        const response = await registerUser(registerObj);
        alert(response.data);
        location.href = "login.html"
    } catch (error) {
        console.error(error);
        
    }
}

registerForm.addEventListener('submit', (e) => handleRegister(e));