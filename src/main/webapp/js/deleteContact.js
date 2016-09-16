function delCheckedContacts() {
    var checkedBoxes = document.querySelectorAll('input[type=checkbox]:checked');
    if (checkedBoxes.length != 0) {
        var str = "";
        for (var i = 0; i < checkedBoxes.length; i++) {
            if (checkedBoxes[i].getAttribute("id") != "checkAll") {
                str += checkedBoxes[i].getAttribute("name") + ",";
            }
        }
        window.location.href = 'main?command=deleteContact&items=' + str.substr(0, str.length - 1);
    }
}

/*
 function post(path, method) {
 method = method || "post"; // Set method to post by default if not specified.

 var checkedBoxes = document.querySelectorAll('input[type=checkbox]:checked');
 var str = "";
 if (checkedBoxes.length != 0) {
 for (var i = 0; i < checkedBoxes.length; i++) {
 if (checkedBoxes[i].getAttribute("id") != "checkAll") {
 str += checkedBoxes[i].getAttribute("name") + ",";
 }
 }
 }

 // The rest of this code assumes you are not using a library.
 // It can be made less wordy if you use one.
 var form = document.createElement("form");
 form.setAttribute("method", method);
 form.setAttribute("action", path);

 var hiddenField = document.createElement("input");
 hiddenField.setAttribute("type", "hidden");
 hiddenField.setAttribute("name", "items");
 hiddenField.setAttribute("value", str);

 form.appendChild(hiddenField);

 document.body.appendChild(form);
 form.submit();
 }*/