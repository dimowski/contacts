function choosePopUp() {
    var checkedBoxes = document.querySelectorAll('input[type=checkbox]:checked');
    if(checkedBoxes.length == 0) {
        show('popup2');
    } else {
        show('popup1');
    }
}