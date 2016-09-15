function delCheckedContacts() {
    var checkedBoxes = document.querySelectorAll('input[type=checkbox]:checked');
    if (checkedBoxes.length != 0) {
        var str = "";
        for (var i = 0; i < checkedBoxes.length; i++) {
            if (checkedBoxes[i].getAttribute("id") != "checkAll") {
                str += checkedBoxes[i].getAttribute("name") + "-";
            }
        }
        window.location.href = 'main?command=deleteContact&items=' + str.substr(0, str.length-1);
    }
}