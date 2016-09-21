$ = function (id) {
    return document.getElementById(id);
}

var show = function (id) {
    $(id).style.display = 'block';
}
var hide = function (id) {
    $(id).style.display = 'none';
}

function bindRemoveBtn(url) {
    var btn = document.getElementById('confirmDelete');
    btn.setAttribute('href', url);
}