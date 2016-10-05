<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Contacts</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/custom.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="js/validate.js"></script>
    <script src="js/tabswitch.js"></script>
    <script src="js/guid.js"></script>
    <script src="js/checkall.js"></script>
    <script src="js/choosePopup.js"></script>
    <script src="js/popup.js"></script>
    <script src="js/addPhone.js"></script>
    <script src="js/editPhone.js"></script>
    <script src="js/deletePhoto.js"></script>
    <script src="js/fileManipulation.js"></script>
    <script src="js/itemsAction.js" defer></script>
</head>
<body>
<div class="container">

    <%@include file="navbar-header.jsp" %>

    <!----- Main content ------>
    <div class="page-header"><h3>${CONTACT.firstName} ${CONTACT.middleName} ${CONTACT.lastName}</h3></div>
    <div class="row">
        <form method="POST" enctype="multipart/form-data" action="main">
            <input type="hidden" name="command" value="saveContact">
            <div class="col-sm-3">
                <div class="image-upload" style="display: inline-block">
                    <label for="file-input">
                        <c:if test="${empty CONTACT.photo}">
                            <img id="userPhoto" src="images/defaultUserIcon.png" class="img-rounded"
                                 alt="Profile photo"/>
                        </c:if>
                        <c:if test="${not empty CONTACT.photo}">
                            <img id="userPhoto" class="contact-photo img-rounded"
                                 onerror="this.src='images/defaultUserIcon.png'"
                                 src="/contactapp/getFile/${CONTACT.photo}" alt="Profile photo"/>
                        </c:if>
                    </label>
                    <input type="file" accept="image/*" id="file-input" name="profilePhoto"/>
                    <c:if test="${not empty CONTACT.photo}">
                        <a onclick="delPhoto(this)" class="btn btn-default btn-block">Удалить фото</a>
                    </c:if>
                </div>
                <div>
                    <input type="hidden" id="deletedPhoto" name="deletedPhoto" value="0">
                </div>
            </div>
            <div class="col-sm-9">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="tabmenu active" id="tab_1">
                        <a href="#" onclick="switchTab('profile_tab', 'content_1', 'tab_1');return false;"
                           id="profile_tab">
                            Профиль
                        </a>
                    </li>
                    <li role="presentation" class="tabmenu" id="tab_2">
                        <a href="#" onclick="switchTab('profile_tab', 'content_2', 'tab_2');return false;"
                           id="address_tab">
                            Адрес
                        </a>
                    </li>
                    <li role="presentation" class="tabmenu" id="tab_3">
                        <a href="#" onclick="switchTab('phone_tab', 'content_3', 'tab_3');return false;" id="phone_tab">
                            Контактные телефоны
                        </a>
                    </li>
                    <li role="presentation" class="tabmenu" id="tab_4">
                        <a href="#" onclick="switchTab('atchment_tab','content_4','tab_4');return false;"
                           id="atcment_tab">
                            Файлы
                        </a>
                    </li>
                </ul>

                <!-- Personal info -->
                <div class="form-horizontal">
                    <div id="content_1" class="tabcontent" style="padding-top: 20px">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Персональная информация</h3>
                            </div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label for="firstName" class="col-sm-3 control-label">Имя</label>
                                    <div class="col-sm-4">
                                        <input type="text" value="${CONTACT.firstName}" class="form-control"
                                               id="firstName" placeholder="Имя" name="firstName" autocomplete="off">
                                    </div>
                                    <div class="col-sm-5 alert-danger" style="display: none" id="alertFirstName">
                                        Проверьте имя
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="lastName" class="col-sm-3 control-label">Фамилия</label>
                                    <div class="col-sm-4">
                                        <input type="text" value="${CONTACT.lastName}" class="form-control"
                                               id="lastName" placeholder="Фамилия" name="lastName" autocomplete="off">
                                    </div>
                                    <div class="col-sm-5 alert-danger" style="display: none" id="alertLastName">
                                        Проверьте фамилию
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="middleName" class="col-sm-3 control-label">Отчество</label>
                                    <div class="col-sm-4">
                                        <input type="text" value="${CONTACT.middleName}" class="form-control"
                                               id="middleName" name="middleName" placeholder="Отчество"
                                               autocomplete="off">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="birthday" class="col-sm-3 control-label">Дата рождения</label>
                                    <div class="col-sm-4">
                                        <input type="date" value="${CONTACT.birthday}" class="form-control"
                                               id="birthday" placeholder="Дата рождения" name="birthday">
                                    </div>
                                    <div class="col-sm-5 alert-danger" style="display: none" id="alertDate">Проверьте
                                        дату рождения
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="gender" class="col-sm-3 control-label">Пол</label>
                                    <div class="col-sm-4">
                                        <select id="gender" class="form-control" name="gender">
                                            <c:if test="${CONTACT.gender == 'М'}">
                                                <option selected="selected" value="М">мужской</option>
                                                <option VALUE="Ж">женский</option>
                                            </c:if>
                                            <c:if test="${CONTACT.gender == 'Ж'}">
                                                <option value="М">мужской</option>
                                                <option selected="selected" value="Ж">женский</option>
                                            </c:if>
                                            <c:if test="${empty CONTACT}">
                                                <option value="М">мужской</option>
                                                <option value="Ж">женский</option>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="citizenship" class="col-sm-3 control-label">Гражданство</label>
                                    <div class="col-sm-4">
                                        <input type="text" value="${CONTACT.citizenship}" class="form-control"
                                               id="citizenship" name="citizenship" autocomplete="off"
                                               placeholder="Гражданство">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="status" class="col-sm-3 control-label">Семейное положение</label>
                                    <div class="col-sm-4">
                                        <select id="status" class="form-control" name="status">
                                            <c:choose>
                                                <c:when test="${CONTACT.gender eq 'М'}">
                                                    <c:choose>
                                                        <c:when test="${CONTACT.status eq 'женат'}">
                                                            <option selected="selected">женат</option>
                                                            <option>холост</option>
                                                        </c:when>
                                                        <c:when test="${(CONTACT.status eq 'холост')}">
                                                            <option>женат</option>
                                                            <option selected="selected">холост</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option>женат</option>
                                                            <option selected="selected">холост</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:when>
                                                <c:when test="${CONTACT.gender eq 'Ж'}">
                                                    <c:choose>
                                                        <c:when test="${CONTACT.status eq 'замужем'}">
                                                            <option selected="selected">замужем</option>
                                                            <option>не замужем</option>
                                                        </c:when>
                                                        <c:when test="${(CONTACT.status eq 'не замужем')}">
                                                            <option>замужем</option>
                                                            <option selected="selected">не замужем</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option>замужем</option>
                                                            <option selected="selected">не замужем</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:when>
                                                <c:otherwise>
                                                    <option>женат</option>
                                                    <option>холост</option>
                                                    <option>замужем</option>
                                                    <option>не замужем</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="webSite" class="col-sm-3 control-label">Web Site</label>
                                    <div class="col-sm-4">
                                        <input type="text" value="${CONTACT.webSite}" class="form-control" id="webSite"
                                               placeholder="Web Site" name="webSite" autocomplete="off">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-3 control-label">Email</label>
                                    <div class="col-sm-4">
                                        <input type="text" value="${CONTACT.email}" class="form-control" id="email"
                                               placeholder="Email" name="email" autocomplete="off">
                                    </div>
                                    <div class="col-sm-5 alert-danger" style="display: none" id="alertEmail">Проверьте
                                        Email
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="jobCurrent" class="col-sm-3 control-label">Место работы</label>
                                    <div class="col-sm-4">
                                        <input type="text" value="${CONTACT.jobCurrent}" class="form-control"
                                               name="jobCurrent" id="jobCurrent" autocomplete="off"
                                               placeholder="Место работы">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> <!-- end of div id="content_1" -->

                <!-- Address tab -->
                <div class="form-horizontal">
                    <div id="content_2" class="tabcontent" style="display:none; padding-top: 20px">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Адрес</h3>
                            </div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label for="country" class="col-sm-3 control-label">Страна</label>
                                    <div class="col-sm-4">
                                        <input type="text" value="${CONTACT.address.country}" class="form-control"
                                               id="country" name="country" placeholder="Страна">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="city" class="col-sm-3 control-label">Город</label>
                                    <div class="col-sm-4">
                                        <input type="text" value="${CONTACT.address.city}" class="form-control"
                                               id="city" name="city" placeholder="Город">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="street" class="col-sm-3 control-label">Улица</label>
                                    <div class="col-sm-4">
                                        <input type="text" value="${CONTACT.address.street}" class="form-control"
                                               id="street" name="street" placeholder="Улица">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="house" class="col-sm-3 control-label">Дом</label>
                                    <div class="col-sm-4">
                                        <input type="text" value="${CONTACT.address.house}" class="form-control"
                                               id="house" name="house" autocomplete="off" placeholder="Дом">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="flat" class="col-sm-3 control-label">Квартира</label>
                                    <div class="col-sm-4">
                                        <input type="text" value="${CONTACT.address.flat}" class="form-control"
                                               id="flat" name="flat" autocomplete="off" placeholder="Квартира">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="zipCode" class="col-sm-3 control-label">Индекс</label>
                                    <div class="col-sm-4">
                                        <input type="text" value="${CONTACT.address.zipCode}" class="form-control"
                                               id="zipCode" name="zipCode" placeholder="Почтовый индекс">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> <!-- end of div id="content_2" -->
                </div>
                <!-- Phone table tab -->
                <div id="content_3" class="tabcontent" style="display:none; padding-top: 20px">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col col-xs-4">
                                    <p class="panel-title">Телефонов: ${CONTACT.phones.size()}</p>
                                </div>
                                <div class="col col-xs-8 text-right">
                                    <button onclick="show('phoneCreatePopup')" type="button"
                                            class="btn btn-sm btn-primary">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Добавить
                                        телефон
                                    </button>
                                    <button onclick="choosePopUp()" type="button" class="btn btn-sm btn-primary">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Удалить
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="table-responsive">
                            <table id="phoneTable" class="table table-hover table-bordered">
                                <input type="hidden" id="idsForDel" name="idsForDel">
                                <thead>
                                <tr>
                                    <th class="text-center"><input type="checkbox" id="All" onclick="checkAll(this)"/>
                                    </th>
                                    <th>Телефонный номер</th>
                                    <th>Тип</th>
                                    <th>Комментарий</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="tempPhone" items="${CONTACT.phones}">
                                    <tr id="row${tempPhone.id}">
                                        <td class="text-center"><input type="checkbox" name="${tempPhone.id}"></td>
                                        <td><a role="button" id="label${tempPhone.id}"
                                               onclick="editPhone(${tempPhone.id});">${tempPhone.countryCode} ${tempPhone.operatorCode} ${tempPhone.phoneNumber}</a>
                                            <input id="phoneId${tempPhone.id}" type="hidden" name="phoneId"
                                                   value="${tempPhone.id}">
                                            <input id="countryCode${tempPhone.id}" type="hidden" name="countryCode"
                                                   value="${tempPhone.countryCode}">
                                            <input id="operatorCode${tempPhone.id}" type="hidden" name="operatorCode"
                                                   value="${tempPhone.operatorCode}">
                                            <input id="phoneNumber${tempPhone.id}" type="hidden" name="phoneNumber"
                                                   value="${tempPhone.phoneNumber}">
                                        </td>
                                        <td class="text-center">
                                            <input id="phoneType${tempPhone.id}" type="hidden" name="phoneType"
                                                   value="${tempPhone.phoneType}">
                                            <div id="phoneTypeLabel${tempPhone.id}">${tempPhone.phoneType}</div>
                                        </td>
                                        <td><input id="phoneComments${tempPhone.id}" type="hidden" name="phoneComments"
                                                   value="${tempPhone.comments}">
                                            <div id="phoneCommentsLabel${tempPhone.id}">${tempPhone.comments}</div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div> <!-- end of div id="content_3" -->

                <!-- Attachments table tab -->
                <div id="content_4" class="tabcontent" style="display:none; padding-top: 20px">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col col-xs-4">
                                    <p class="panel-title">Файлов: ${CONTACT.attachments.size()}</p>
                                </div>
                                <div class="col col-xs-8 text-right">
                                    <button onclick="show('attachmentCreatePopup')" type="button"
                                            class="btn btn-sm btn-primary">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Добавить
                                        файл
                                    </button>
                                </div>
                            </div>
                        </div>

                        <div class="table-responsive">
                            <table id="fileTable" class="table table-hover table-bordered">
                                <input type="hidden" id="filesForDel" name="filesForDel">
                                <thead>
                                <tr>
                                    <th>Имя файла</th>
                                    <th>Дата загрузки</th>
                                    <th>Комментарий</th>
                                    <th>Скачать</th>
                                    <th>Удалить</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="tempAttachment" items="${CONTACT.attachments}">
                                    <tr id="row${tempAttachment.id}">
                                        <td><input id="fileId${tempAttachment.id}" name="fileId"
                                                   value="${tempAttachment.id}" type="hidden">
                                            <input type="hidden" value="${tempAttachment.comments}"
                                                   id="fileComment${tempAttachment.id}" name="oldFileComment"><a
                                                    role="button"
                                                    onclick="editFilePopup(${tempAttachment.id})">${tempAttachment.filename}</a>
                                        </td>
                                        <td class="text-center">
                                            <fmt:formatDate value="${tempAttachment.uploadDate}"
                                                            pattern="yyyy-MM-dd HH:mm:ss"/>
                                        </td>
                                        <td id="commentContent${tempAttachment.id}">${tempAttachment.comments}</td>
                                        <td class="text-center">
                                            <a href="getAttachment?fileName=${CONTACT.id}/${tempAttachment.filename}"><span
                                                    class="glyphicon glyphicon-download-alt blue"
                                                    aria-hidden="true"></span></a>
                                        </td>
                                        <td class="text-center">
                                            <a role="button"
                                               onclick="show('confirmationPopup'); bindRemoveBtn(${tempAttachment.id})">
                                                <span class="glyphicon glyphicon-remove red" aria-hidden="true"></span></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div> <!-- end of div id="content_4" -->
                <a class="btn btn-primary pull-right" type="button" href="main?command=list&targetPage=${CURRENT_PAGE}">Отмена</a>
                <input type="submit" onclick="return validateSave()" class="btn btn-primary pull-right"
                       value="Сохранить" style="margin-right: 10px">
            </div>
        </form>
    </div>
</div>
</div>

<!-- Popup for phone creating -->
<div class="panel panel-default popup-phone" id="phoneCreatePopup">
    <div class="form-group" id="ncc">
        <label for="countryCode">Код страны</label>
        <input id="countryCode" type="text" class="form-control" aria-describedby="basic-addon1"
               placeholder="Код страны" onkeypress='return event.charCode >= 48 && event.charCode <= 57'>
    </div>
    <div class="form-group" id="noc">
        <label for="operatorCode">Код оператора</label>
        <input id="operatorCode" type="text" class="form-control" aria-describedby="basic-addon1"
               placeholder="Код оператора" onkeypress='return event.charCode >= 48 && event.charCode <= 57'>
    </div>
    <div class="form-group" id="nph">
        <label for="phoneNumber">Номер телефона</label>
        <input id="phoneNumber" type="text" class="form-control" aria-describedby="basic-addon1"
               placeholder="Номер телефона" onkeypress='return event.charCode >= 48 && event.charCode <= 57'>
    </div>
    <div class="form-group">
        <label for="phoneType">Тип телефона</label>
        <select id="phoneType" class="form-control">
            <option>мобильный</option>
            <option>домашний</option>
        </select>
    </div>
    <div class="form-group">
        <label for="phoneComments">Комментарий</label>
        <input id="phoneComments" type="text" class="form-control" aria-describedby="basic-addon1"
               placeholder="Комментарий"><br/>
    </div>
    <button onclick="addPhone()" class="btn btn-primary">Добавить</button>
    <button onclick="hide('phoneCreatePopup')" class="btn btn-primary pull-right">Отмена</button>
</div>

<!-- Popup for phone editing -->
<div class="panel panel-default popup-phone" id="phoneEditPopup">
    <div class="form-group" id="occ">
        <label for="countryCode">Код страны</label>
        <input id="newCountryCode" type="text" class="form-control" aria-describedby="basic-addon1"
               placeholder="Код страны" onkeypress='return event.charCode >= 48 && event.charCode <= 57'>
    </div>
    <div class="form-group" id="ooc">
        <label for="operatorCode">Код оператора</label>
        <input id="newOperatorCode" type="text" class="form-control" aria-describedby="basic-addon1"
               placeholder="Код оператора" onkeypress='return event.charCode >= 48 && event.charCode <= 57'>
    </div>
    <div class="form-group" id="opn">
        <label for="phoneNumber">Номер телефона</label>
        <input id="newPhoneNumber" type="text" class="form-control" aria-describedby="basic-addon1"
               placeholder="Номер телефона" onkeypress='return event.charCode >= 48 && event.charCode <= 57'>
    </div>
    <div class="form-group">
        <label for="phoneType">Тип телефона</label>
        <select id="newPhoneType" class="form-control">
            <option value="1" selected="selected">мобильный</option>
            <option value="2">домашний</option>
        </select>
    </div>
    <div class="form-group">
        <label for="phoneComments">Комментарий</label>
        <input id="newPhoneComments" type="text" class="form-control" aria-describedby="basic-addon1"
               placeholder="Комментарий"><br/>
    </div>
    <input type="hidden" id="phoneGUID">
    <button onclick="update()" class="btn btn-primary">Сохранить</button>
    <button onclick="hide('phoneEditPopup')" class="btn btn-primary pull-right">Отмена</button>
</div>

<!-- Popup for attachment creating -->
<div class="panel panel-default popup-attachment" id="attachmentCreatePopup">
    <div class="form-group">
        <label for="attachmentCreateComments">Комментарий</label>
        <input id="attachmentCreateComments" type="text" class="form-control" name="comments"
               aria-describedby="basic-addon1"
               placeholder="Комментарий">
    </div>
    <button onclick="addFile(); hide('attachmentCreatePopup')" class="btn btn-primary">Сохранить</button>
    <button type="button" onclick="hide('attachmentCreatePopup')" class="btn btn-primary pull-right">Отмена</button>
</div>

<!-- Popup for attachment editing -->
<div class="panel panel-default popup-attachment" id="attachmentEditPopup">
    <div class="form-group">
        <label for="attachmentEditComments">Комментарий</label>
        <input id="attachmentEditComments" type="text" class="form-control" name="comments"
               aria-describedby="basic-addon1"
               placeholder="Комментарий">
    </div>
    <button id="attachmentEditBtn" onclick="updateFile(this.value); hide('attachmentEditPopup')"
            class="btn btn-primary">Сохранить
    </button>
    <button type="button" onclick="hide('attachmentEditPopup')" class="btn btn-primary pull-right">Отмена</button>
</div>

<div class="panel panel-default popupSmall" id="confirmationPopup">
    <h4 class="text-center">Удалить файл?</h4>
    <div class="row">
        <div class="col-sm-12">
            <button class="btn btn-default popup-btn" id="confirmDelete"
                    onclick="hide('confirmationPopup'); deleteFile(this.value)">Да
            </button>
            <button class="btn btn-default popup-btn pull-right" onclick="hide('confirmationPopup');">Нет</button>
        </div>
    </div>
</div>

<div class="panel panel-default popupSmall" id="popupConfirm">
    <h4 class="text-center">Удалить выбранные телефоны?</h4>
    <div class="row">
        <div class="col-sm-12">
            <button onclick="hide('popupConfirm');delCheckedPhones()" class="btn btn-default popup-btn">Да
            </button>
            <button onclick="hide('popupConfirm')" class="btn btn-default popup-btn pull-right">Нет</button>
        </div>
    </div>
</div>

<div class="popupSmall" id="popupEmptySet">
    <h4 class="text-center">Выберите телефоны для удаления!</h4><br/>
    <button onclick="hide('popupEmptySet')" class="btn btn-default center-block">Ок</button>
</div>

<div class="popupSmall" id="alertValidation">
    <h4 class="text-center">Проверьте введенные данные!</h4><br/>
    <button onclick="hide('alertValidation')" class="btn btn-default center-block">Ок</button>
</div>
</body>
</html>