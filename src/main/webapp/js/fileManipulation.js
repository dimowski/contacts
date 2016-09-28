function addFile() {
    var table = document.getElementById("fileTable");
    var row = table.insertRow(1);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    var cell5 = row.insertCell(4);

    cell2.className += "text-center";
    cell4.className += "text-center";
    cell5.className += "text-center";

    var file = document.createElement('input');
    file.type = "file";
    file.name = "attachment";
    var comments = document.getElementById("attachmentCreateComments");
    var GUID = guidGenerator();
    row.id = "row" + GUID;

    var lnk = "confirmationPopup";

    cell1.appendChild(file);
    cell2.innerHTML = "<input id='fileId" + GUID + "' type='hidden' name='fileId' value='0'>" + "не загружен";
    cell3.innerHTML = "<input id='fileComment" + GUID + "' type='hidden' name='newFileComment' value='" + comments.value + "'>" + comments.value;
    cell4.innerHTML = "<span class='glyphicon glyphicon-download-alt grey' aria-hidden='true'></span>";
    cell5.innerHTML = "<a role='button' onclick=show('" + lnk + "');bindRemoveBtn('" + GUID + "')><span class='glyphicon glyphicon-remove red' aria-hidden='true'></span></a>";

    document.getElementById("attachmentCreateComments").value = "";
}

function deleteFile(id) {
    var ids = document.getElementById("filesForDel");
    if (document.getElementById("fileId" + id).value != 0)
        ids.value += id + "/";
    var row = document.getElementById("row" + id);
    row.parentNode.removeChild(row);
}

function editFilePopup(id) {
    show('attachmentEditPopup');
    var btn = document.getElementById('attachmentEditBtn');
    btn.value = id;
}

function updateFile(id) {
    var oldComment = document.getElementById("fileComment" + id);
    var commentContent = document.getElementById("commentContent" + id);
    var newComment = document.getElementById("attachmentEditComments");
    oldComment.value = newComment.value;
    commentContent.textContent = newComment.value;

    document.getElementById("attachmentEditComments").value = "";
}
