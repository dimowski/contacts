<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Contacts</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/custom.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="js/tabswitch.js"></script>
    <script src="js/popup.js"></script>
    <script src="js/addPhone.js"></script>
</head>
<body>
<div class="container">

    <!----- Navigation bar ------>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <div class="navbar-brand">Contacts</div>
            </div>
            <ul class="nav navbar-nav">
                <li>
                    <a href="main"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Список
                        контактов</a>
                </li>
                <li>
                    <a href="#"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Поиск</a>
                </li>
            </ul>
        </div>
    </nav>

    <!----- Main content ------>
    <div class="page-header"><h3>${CONTACT.firstName} ${CONTACT.middleName} ${CONTACT.lastName}</h3></div>
    <div class="row">
        <form ectype="multipart/form-data" action="main" method="POST">
            <div class="col-sm-3">
                <div class="image-upload" style="display: inline-block">
                    <label for="file-input">
                        <c:if test="${empty CONTACT.photo}">
                            <img src="images/defaultUserIcon.png" alt="Profile photo"/>
                        </c:if>
                        <c:if test="${not empty CONTACT.photo}">
                            <img class="contact-photo" src="<c:out value="${CONTACT.photo}"/>" alt="Profile photo"/>
                        </c:if>
                    </label>
                    <input type="file" accept="image/*" id="file-input"/>
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
                            Присоединения
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
                                               id="firstName" placeholder="Имя" name="firstName" autocomplete="off"
                                               required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="lastName" class="col-sm-3 control-label">Фамилия</label>
                                    <div class="col-sm-4">
                                        <input type="text" value="${CONTACT.lastName}" class="form-control"
                                               id="lastName" placeholder="Фамилия" name="lastName" autocomplete="off"
                                               required>
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
                                </div>
                                <div class="form-group">
                                    <label for="gender" class="col-sm-3 control-label">Пол</label>
                                    <div class="col-sm-4">
                                        <select id="gender" class="form-control" name="gender">
                                            <c:if test="${CONTACT.gender == 'М'}">
                                                <option selected="selected">М</option>
                                                <option>Ж</option>
                                            </c:if>
                                            <c:if test="${CONTACT.gender == 'Ж'}">
                                                <option>М</option>
                                                <option selected="selected">Ж</option>
                                            </c:if>
                                            <c:if test="${empty CONTACT}">
                                                <option>М</option>
                                                <option>Ж</option>
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
                                            <c:if test="${empty CONTACT}">
                                                <option>женат</option>
                                                <option selected="selected">холост</option>
                                                <option>замужем</option>
                                                <option selected="selected">не замужем</option>
                                            </c:if>
                                            <c:if test="${CONTACT.gender == 'М'}">
                                                <c:if test="${CONTACT.status == 'женат'}">
                                                    <option selected="selected">женат</option>
                                                    <option>холост</option>
                                                </c:if>
                                                <c:if test="${CONTACT.status == 'холост'}">
                                                    <option>женат</option>
                                                    <option selected="selected">холост</option>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${CONTACT.gender == 'Ж'}">
                                                <c:if test="${CONTACT.status == 'замужем'}">
                                                    <option selected="selected">замужем</option>
                                                    <option>не замужем</option>
                                                </c:if>
                                                <c:if test="${CONTACT.status == 'не замужем'}">
                                                    <option>замужем</option>
                                                    <option selected="selected">не замужем</option>
                                                </c:if>
                                            </c:if>
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
                                        <input type="email" value="${CONTACT.email}" class="form-control" id="email"
                                               placeholder="Email" name="email" autocomplete="off">
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
                                    <p class="panel-title">Всего телефонов: ${CONTACT.phones.size()}</p>
                                </div>
                                <div class="col col-xs-8 text-right">
                                    <button onclick="show('phoneEditPopup')" type="button"
                                            class="btn btn-sm btn-primary">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Добавить
                                        телефон
                                    </button>
                                    <button type="button" class="btn btn-sm btn-primary">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Удалить
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="table-responsive">
                            <table id="phoneTable" class="table table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th class="text-center"><input type="checkbox" name="checkAll"></th>
                                    <th>Телефонный номер</th>
                                    <th>Тип</th>
                                    <th>Комментарий</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="tempPhone" items="${CONTACT.phones}">
                                    <tr id="${tempPhone.countryCode}${tempPhone.operatorCode}${tempPhone.phoneNumber}">
                                        <td class="text-center"><input type="checkbox" name="phoneCheck"></td>
                                        <td><input type="hidden" name="phoneId" value="${tempPhone.id}">
                                            <input type="hidden" name="countryCode" value="${tempPhone.countryCode}">
                                            <input type="hidden" name="operatorCode" value="${tempPhone.operatorCode}">
                                            <input type="hidden" name="phoneNumber" value="${tempPhone.phoneNumber}">
                                            <a role="button"
                                               onclick="editPhone(document);show('phoneEditPopup')">+${tempPhone.countryCode} ${tempPhone.operatorCode} ${tempPhone.phoneNumber}</a>
                                        </td>
                                        <td class="text-center">
                                            <input type="hidden" name="phoneType" value="${tempPhone.phoneType}">
                                                ${tempPhone.phoneType}</td>
                                        <td><input type="hidden" name="phoneComments" value="${tempPhone.comments}">
                                                ${tempPhone.comments}</td>
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
                                    <p class="panel-title">Всего файлов: ${CONTACT.attachments.size()}</p>
                                </div>
                                <div class="col col-xs-8 text-right">
                                    <button onclick="show('attachmentEditPopup')" type="button"
                                            class="btn btn-sm btn-primary">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Добавить
                                        файл
                                    </button>
                                    <button type="button" class="btn btn-sm btn-primary">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Удалить
                                    </button>
                                </div>
                            </div>
                        </div>

                        <div class="table-responsive">
                            <table class="table table-hover table-bordered">
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
                                    <tr>
                                        <td><a href="#">${tempAttachment.filename}</a></td>
                                        <td class="text-center">${tempAttachment.uploadDate}</td>
                                        <td>${tempAttachment.comments}</td>
                                        <td class="text-center">
                                            <a><span class="glyphicon glyphicon-download-alt"
                                                     aria-hidden="true"></span></a>
                                        </td>
                                        <td class="text-center">
                                            <a><span class="glyphicon glyphicon-remove red"
                                                     aria-hidden="true"></span></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div> <!-- end of div id="content_4" -->

                <input type="submit" class="btn btn-primary pull-right" value="Сохранить">
            </div>
            <input type="hidden" name="command" value="saveContact">
        </form>
    </div>
</div>
</div>

<!-- Popup for phone editing -->
<div class="panel panel-default popup-phone" id="phoneEditPopup">
    <div class="form-group">
        <label for="countryCode">Код страны</label>
        <input id="countryCode" type="text" class="form-control" aria-describedby="basic-addon1"
               placeholder="Код страны" required>
    </div>
    <div class="form-group">
        <label for="operatorCode">Код оператора</label>
        <input id="operatorCode" type="text" class="form-control" aria-describedby="basic-addon1"
               placeholder="Код оператора">
    </div>
    <div class="form-group">
        <label for="phoneNumber">Номер телефона</label>
        <input id="phoneNumber" type="text" class="form-control" aria-describedby="basic-addon1"
               placeholder="Номер телефона" required>
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
    <button onclick="addPhone();hide('phoneEditPopup')" class="btn btn-primary">Добавить</button>
    <button onclick="hide('phoneEditPopup')" class="btn btn-primary pull-right">Отмена</button>
</div>

<!-- Popup for attachment editing -->
<div class="panel panel-default popup-attachment" id="attachmentEditPopup">
    <form action="main" enctype="multipart/form-data" method="POST">
        <div class="form-group">
            <label for="fileName">Файл</label>
            <input id="fileName" type="file" required>
        </div>
        <div class="form-group">
            <label for="attachmentComments">Комментарий</label>
            <input id="attachmentComments" type="text" class="form-control" aria-describedby="basic-addon1"
                   placeholder="Комментарий">
        </div>
        <input type="hidden" name="command" value="addAttachment">
        <input value="Добавить" type="submit" class="btn btn-primary"/>
        <button onclick="hide('attachmentEditPopup')" class="btn btn-primary pull-right">Отмена</button>
    </form>
</div>
</div>
</body>
</html>