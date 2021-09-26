window.onload = function () {
    document.getElementById("email").onchange = validateEmail;
    document.getElementById("password").onchange = validatePassword;
    document.getElementById("repassword").onchange = validatePassword;
}
function validatePassword() {
    var pass = document.getElementById("password").value;
    var rePass = document.getElementById("repassword").value;
    if (pass == rePass) {
        document.getElementById("repassword").setCustomValidity('');
    } else {
        document.getElementById("repassword").setCustomValidity("Passwords are not the same!");
    }
}
function validateEmail() {
    var email = document.getElementById("email").value;
    if (email.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) {
        document.getElementById("email").setCustomValidity('');
    } else {
        document.getElementById("email").setCustomValidity("Wrong email!");
    }
}