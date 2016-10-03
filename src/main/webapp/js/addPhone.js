function addPhone() {
    var countryCode = document.getElementById("countryCode").value;
    var operatorCode = document.getElementById("operatorCode").value;
    var phoneNumber = document.getElementById("phoneNumber").value;
    var phoneField = document.getElementById("nph");
    if(phoneNumber.length === 0 || !phoneNumber.trim()) {
        phoneField.className += " has-error";
        return false;
    } else {
        phoneField.className = "form-group"
    }
    var table = document.getElementById("phoneTable");
    var row = table.insertRow(1);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);

    var fullPhone = countryCode + " " + operatorCode + " " + phoneNumber;

    var phoneType = document.getElementById("phoneType").value;
    var comments = document.getElementById("phoneComments").value;

    cell1.className += "text-center";
    cell3.className += "text-center";

    var GUID = guidGenerator();

    row.id = "row" + GUID;
    cell1.innerHTML = "<input type='checkbox' name='" + GUID + "'>";
    cell2.innerHTML = "<a role='button' id='label" + GUID + "' onclick=editPhone('" + GUID + "')>" + fullPhone + "</a>" +
        "<input id='phoneId" + GUID + "' type='hidden' name='phoneId' value='0'>" + /*phoneId = 0 when phone needs to be created*/
        "<input id='countryCode" + GUID + "' type='hidden' name='countryCode' value='" + countryCode + "'>" +
        "<input id='operatorCode" + GUID + "' type='hidden' name='operatorCode' value='" + operatorCode + "'>" +
        "<input id='phoneNumber" + GUID + "' type='hidden' name='phoneNumber' value='" + phoneNumber + "'>";
    cell3.innerHTML = "<div id='phoneTypeLabel" + GUID + "'>" + phoneType + "</div><input id='phoneType" + GUID + "' type='hidden' name='phoneType' value='" + phoneType + "'>";
    cell4.innerHTML ="<div id='phoneCommentsLabel" + GUID + "'>" + comments + "</div><input id='phoneComments" + GUID + "' type='hidden' name='phoneComments' value='" + comments + "'>";

    hide('phoneCreatePopup');

    document.getElementById("countryCode").value = "";
    document.getElementById("operatorCode").value = "";
    document.getElementById("phoneNumber").value = "";
    document.getElementById("phoneComments").value = "";
}