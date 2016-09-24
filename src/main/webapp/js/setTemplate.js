function setTemplate(t) {
    if (document.getElementById("templateEnable").checked == true) {
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
    }
}