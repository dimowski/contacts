$ = function (id) {
    return document.getElementById(id);
}

var show = function (id) {
    $(id).style.display = 'block';
}
var hide = function (id) {
    $(id).style.display = 'none';
}

function bindRemoveBtn(id) {
    var btn = document.getElementById('confirmDelete');
    btn.value = id;
}