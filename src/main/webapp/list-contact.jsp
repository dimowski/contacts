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
                    <p class="panel-title">Всего контактов: ${CONTACTS_COUNT}</p>
                </div>
                <div class="col col-xs-8 text-right">
                    <a href="main?command=createContact" type="button" class="btn btn-sm btn-primary btn-create">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Добавить контакт
                    </a>
                    <a onclick="sendEmailToContacts()" class="btn btn-sm btn-primary btn-create">
                        <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> Отправить email
                    </a>
                    <a onclick="choosePopUp()" class="btn btn-sm btn-primary btn-create">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Удалить
                    </a>
                </div>
            </div>
        </div>
        <div class="table-responsive">
            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th class="text-center"><input type="checkbox" id="checkAll" onclick="checkAll(this)"/></th>
                    <th>Полное имя</th>
                    <th>Дата рождения</th>
                    <th>Адрес</th>
                    <th>Место работы</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="tempContact" items="${CONTACT_LIST.values()}">
                    <tr>
                        <td class="text-center"><input type="checkbox" name="${tempContact.id}"/></td>
                        <td>
                            <c:url var="editLink" value="main">
                                <c:param name="command" value="editContact"/>
                                <c:param name="contactId" value="${tempContact.id}"/>
                            </c:url>
                            <a href="${editLink}">${tempContact.firstName} ${tempContact.middleName} ${tempContact.lastName}</a>
                        </td>
                        <td class="text-center">
                            <span class="glyphicon glyphicon-calendar" style="color:lightgray; font-size: 80%"
                                  aria-hidden="true"></span> ${tempContact.birthday}
                        </td>
                        <td>${tempContact.address}</td>
                        <td>${tempContact.jobCurrent}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <!---- Pagination ---->
            <div class="panel-footer">
                <div class="row center-block">
                    <nav aria-label="Page navigation" class="pull-right">
                        <c:if test="${PAGES_COUNT > 1}">
                            <ul class="pagination">
                                <c:choose>
                                    <c:when test="${CURRENT_PAGE != 1}">
                                        <li>
                                            <a href="main?command=list&targetPage=prev" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                    </c:when>
                                    <c:when test="${CURRENT_PAGE == 1}">
                                        <li class="disabled">
                                            <span aria-hidden="true">&laquo;</span>
                                        </li>
                                    </c:when>
                                </c:choose>
                                <c:forEach var="i" begin="1" end="${PAGES_COUNT}">
                                    <c:url var="targetPage" value="main">
                                        <c:param name="command" value="list"/>
                                        <c:param name="targetPage" value="${i}"/>
                                    </c:url>
                                    <c:choose>
                                        <c:when test="${CURRENT_PAGE == i}">
                                            <li class="active"><a href="${targetPage}">${i}</a></li>
                                        </c:when>
                                        <c:when test="${CURRENT_PAGE != i}">
                                            <li><a href="${targetPage}">${i}</a></li>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${CURRENT_PAGE != PAGES_COUNT}">
                                        <li>
                                            <a href="main?command=list&targetPage=next" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </c:when>
                                    <c:when test="${CURRENT_PAGE == PAGES_COUNT}">
                                        <li class="disabled">
                                            <span aria-hidden="true">&raquo;</span>
                                        </li>
                                    </c:when>
                                </c:choose>
                            </ul>
                        </c:if>
                    </nav>
                </div>
            </div>
        </div>
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
    <h4 class="text-center">Выберите контакты для удаления!</h4><br/>
    <button onclick="hide('popupEmptySet')" class="btn btn-default center-block">Ок</button>
</div>

</div>
</body>
</html>