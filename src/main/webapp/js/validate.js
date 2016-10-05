function validateSave() {
    var isOk = true;
    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var email = document.getElementById("email").value;
    var files = document.getElementsByName("attachment");

    if (hasNumber(firstName) || firstName.length === 0 || !firstName.trim()) {
        show("alertFirstName");
        isOk = false;
    } else {
        hide("alertFirstName");
    }

    if (hasNumber(lastName) || lastName.length === 0 || !lastName.trim()) {
        show("alertLastName");
        isOk = false;
    } else {
        hide("alertLastName");
    }

    if (!validateEmail(email)) {
        show("alertEmail");
        isOk = false;
    } else {
        hide("alertEmail");
    }

    var birthday = document.getElementById("birthday");
    var today = new Date();
    if (Date.parse(birthday.value) > today) {
        show("alertDate");
        isOk = false;
    } else {
        hide("alertDate");
    }

    for (var index in files) {
        if (files[index].value == "")
            isOk = false
    }
    if (!isOk) {
        show("alertValidation");
    }
    return isOk;
}

function hasNumber(myString) {
    return (
        /\d/.test(
            myString));
}

function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}