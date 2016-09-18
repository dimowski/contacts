function addPhone() {
    var table = document.getElementById("phoneTable");
    var row = table.insertRow(1);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);

    var countryCode = document.getElementById("countryCode").value;
    var operatorCode = document.getElementById("operatorCode").value;
    var phoneNumber = document.getElementById("phoneNumber").value;
    var fullPhone = countryCode + " " + operatorCode + " " + phoneNumber;

    var phoneType = document.getElementById("phoneType").value;
    var comments = document.getElementById("phoneComments").value;

    cell1.className += "text-center";
    cell3.className += "text-center";

    cell1.innerHTML = "<input type='checkbox' name='" + fullPhone + "'>";
    cell2.innerHTML = "<a role='button'>" + fullPhone + "</a>" +
        "<input type='hidden' name='phoneId' value='0'>" + /*phoneId = 0 when phone needs to be created*/
        "<input type='hidden' name='countryCode' value='" + countryCode + "'>" +
        "<input type='hidden' name='operatorCode' value='" + operatorCode + "'>" +
        "<input type='hidden' name='phoneNumber' value='" + phoneNumber + "'>";
    cell3.innerHTML = phoneType + "<input type='hidden' name='phoneType' value='" + phoneType + "'>";
    cell4.innerHTML = comments + "<input type='hidden' name='phoneComments' value='" + comments + "'>";
}