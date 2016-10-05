function delPhoto(btn) {
    var photo = document.getElementById("userPhoto");
    photo.setAttribute("src", "images/defaultUserIcon.png");
    btn.style.display = 'none';
    var deletedPhoto = document.getElementById("deletedPhoto");
    deletedPhoto.value = "1";
}