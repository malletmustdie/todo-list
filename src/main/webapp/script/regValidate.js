function validate() {
    let result = true;
    let name = $("#name");
    let email = $("#email");
    let password = $("#password");
    if (name.val() === '') {
        alert(name.attr('placeholder'));
        result = false;
    }
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