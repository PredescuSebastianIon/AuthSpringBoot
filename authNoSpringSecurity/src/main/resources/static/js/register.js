const password = document.getElementById("password");
const repeatPassword = document.getElementById("repeatPassword");

function passwordShow() {
    if (password.type === "password")
        password.type = "text";
    else
        password.type = "password";
}

function repeatPasswordShow() {
    if (repeatPassword.type === "password")
        repeatPassword.type = "text";
    else
        repeatPassword.type = "password";
}

const form = document.getElementById("registerForm");
const error = document.getElementById("frontend-error");
form.addEventListener("submit", function (event) {
    if (password.value !== repeatPassword.value) {
        event.preventDefault();
        error.textContent = "Passwords does not coincide"
    }
    else {
        error.textContent = "";
    }
})