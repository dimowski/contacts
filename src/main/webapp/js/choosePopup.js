function choosePopUp(id) {
    var checkedBoxes = document.querySelectorAll('input[type=checkbox]:checked');
    if (checkedBoxes.length == 0) {
        show('popupEmptySet');
    } else if (id != "e") {
        show('popupConfirm');
    } else sendEmailToContacts();
}