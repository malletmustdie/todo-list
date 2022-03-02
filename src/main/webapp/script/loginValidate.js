function validate() {
    let result = true;
    let email = $("#email");
    let password = $("#password");
    if (email.val() === '') {
        alert(email.attr('placeholder'));
        result = false;
    }
    if (password.val() === '') {
        alert(password.attr('placeholder'));
        result = false;
    }
    return result;
}