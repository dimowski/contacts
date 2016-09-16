function choosePopUp() {
    var checkedBoxes = document.querySelectorAll('input[type=checkbox]:checked');
    if(checkedBoxes.length == 0) {
        show('popupEmptySet');
    } else {
        show('popupConfirm');
    }
}