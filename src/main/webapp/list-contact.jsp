<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Contacts</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/custom.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="js/checkall.js"></script>
    <script src="js/itemsAction.js"></script>
    <script src="js/choosePopup.js"></script>
    <script src="js/popup.js"></script>
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
                <li class="active">
                    <a href="main"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Список
                        контактов
                        <span class="sr-only">(current)</span></a>
                </li>
                <li>
                    <a href="search-contact.jsp"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Поиск</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="page-header"><h3>Список контактов</h3></div>
    <!-------- Table -------->
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="row">
                <div class="col col-xs-4">
                    <p class="panel-title">Контактов: ${CONTACTS_COUNT}</p>
                </div>
                <div class="col col-xs-8 text-right">
                    <a href="main?command=createContact" type="button" class="btn btn-sm btn-primary btn-create">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Добавить контакт
                    </a>
                    <a onclick="choosePopUp('e')" class="btn btn-sm btn-primary btn-create">
                        <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> Отправить email
                    </a>
                    <a onclick="choosePopUp()" class="btn btn-sm btn-primary btn-create">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Удалить
                    </a>
                </div>
            </div>
        </div>

        <%@include file="table-contacts.jsp"%>

    </div>
</div>
<!-- Popup windows -->
<div class="panel panel-default popupSmall" id="popupConfirm">
    <h4 class="text-center">Удалить выбранные контакты?</h4>
    <div class="row">
        <div class="col-sm-12">
            <button onclick="hide('popupConfirm');delCheckedContacts()" class="btn btn-default popup-btn">Да
            </button>
            <button onclick="hide('popupConfirm')" class="btn btn-default popup-btn">Нет</button>
        </div>
    </div>
</div>

<div class="popupSmall" id="popupEmptySet">
    <h4 class="text-center">Выберите контакты!</h4><br/>
    <button onclick="hide('popupEmptySet')" class="btn btn-default center-block">Ок</button>
</div>

</div>
</body>
</html>