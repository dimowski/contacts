function setTemplate(t) {
    var sel = document.getElementById("sel");
    if (document.getElementById("templateEnable").checked == true) {
        sel.removeAttribute("disabled");
        var optionValue = t.value;
        switch (optionValue) {
            case "1":
                document.getElementById("textarea").value = document.getElementById("ST1").value;
                break;
            case "2":
                document.getElementById("textarea").value = document.getElementById("ST2").value;
                break;
            default:
                document.getElementById("textarea").value = "";
                break;
        }
    } else {
        document.getElementById("textarea").value = "";
        sel.setAttribute("disabled", "");
    }
}