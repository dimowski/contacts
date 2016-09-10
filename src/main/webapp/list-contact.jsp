<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Contacts</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
                    <a href="#"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Список контактов
                        <span class="sr-only">(current)</span></a>
                </li>
                <li>
                    <a href="#"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Поиск</a>
                </li>
            </ul>
        </div>
    </nav>

    <!-------- Table -------->
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="row">
                <div class="col col-xs-4">
                    <p class="panel-title">Всего контактов: 23</p>
                </div>
                <div class="col col-xs-8 text-right">
                    <button type="button" class="btn btn-sm btn-primary btn-create">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Добавить контакт
                    </button>
                    <button type="button" class="btn btn-sm btn-primary btn-create">
                        <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> Отправить email
                    </button>
                    <button type="button" class="btn btn-sm btn-primary btn-create">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Удалить
                    </button>
                </div>
            </div>
        </div>
        <div class="table-responsive">
            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th><input type="checkbox" name="checkAll"/></th>
                    <th>Полное имя</th>
                    <th>Дата рождения</th>
                    <th>Адрес</th>
                    <th>Место работы</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="tempContact" items="${CONTACT_LIST}">
                    <tr>
                        <td><input type="checkbox" name="myTextEditBox"/></td>
                        <td><a href="edit-contact.jsp">${tempContact.firstName} ${tempContact.middleName} ${tempContact.lastName}</a>
                        </td>
                        <td>
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
                        <ul class="pagination">
                            <li>
                                <a href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li><a href="#">6</a></li>
                            <li><a href="#">7</a></li>
                            <li><a href="#">8</a></li>
                            <li>
                                <a href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>