function editPhone(id) {
    show("phoneEditPopup");
    document.getElementById("phoneGUID").value = id;

    var newCountryCode = document.getElementById("newCountryCode");
    var newOperatorCode = document.getElementById("newOperatorCode");
    var newPhoneNumber = document.getElementById("newPhoneNumber");
    var newPhoneType = document.getElementById("newPhoneType");
    var newPhoneComments = document.getElementById("newPhoneComments");

    var oldCountryCode = document.getElementById("countryCode" + id);
    var oldOperatorCode = document.getElementById("operatorCode" + id);
    var oldPhoneNumber = document.getElementById("phoneNumber" + id);
    var oldPhoneType = document.getElementById("phoneType" + id);
    var oldPhoneComments = document.getElementById("phoneComments" + id);

    newCountryCode.value = oldCountryCode.value;
    newOperatorCode.value = oldOperatorCode.value;
    newPhoneNumber.value = oldPhoneNumber.value;

    for (var i = 0, j = newPhoneType.options.length; i < j; ++i) {
        if (newPhoneType.options[i].innerHTML === oldPhoneType.value) {
            newPhoneType.selectedIndex = i;
            break;
        }
    }
    newPhoneComments.value = oldPhoneComments.value;
}

function update() {
    var id = document.getElementById("phoneGUID").value;

    var countryCode = document.getElementById("newCountryCode").value;
    var operatorCode = document.getElementById("newOperatorCode").value;
    var phoneNumber = document.getElementById("newPhoneNumber").value;

    document.getElementById("countryCode" + id).value = countryCode;
    document.getElementById("operatorCode" + id).value = operatorCode;
    document.getElementById("phoneNumber" + id).value = phoneNumber;
    document.getElementById("phoneType" + id).value = document.getElementById("newPhoneType").value;
    document.getElementById("phoneComments" + id).value = document.getElementById("newPhoneComments").value;

    document.getElementById("label" + id).textContent = countryCode + " " + operatorCode + " " + phoneNumber;

    var sel = document.getElementById("newPhoneType");

    document.getElementById("phoneTypeLabel" + id).textContent = sel.options[sel.selectedIndex].text;
    document.getElementById("phoneCommentsLabel" + id).textContent = document.getElementById("newPhoneComments").value;
}