function addPhone() {
    var table = document.getElementById("phoneTable");
    var row = table.insertRow(1);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);

    var countryCode = document.getElementById("countryCode").value + " ";
    var operatorCode = document.getElementById("operatorCode").value + " ";
    var phoneNumber = document.getElementById("phoneNumber").value;
    var uniqueId = countryCode + operatorCode + phoneNumber;

    cell1.className += "text-center";
    cell3.className += "text-center";

    cell1.innerHTML = "<input type='checkbox' name='" + uniqueId + "'>";
    cell2.innerHTML = "<a href='#'>" + uniqueId + "</a>";
    cell3.innerHTML = document.getElementById("phoneType").value;
    cell4.innerHTML = document.getElementById("phoneComments").value;
}