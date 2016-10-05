<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Send emails</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/custom.css">
    <script src="js/setTemplate.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
<div class="container">

    <%@include file="navbar-header.jsp" %>

    <div class="page-header"><h3>Отправка email</h3></div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Новое сообщение</h3>
        </div>
        <div class="panel-body">
            <form method="POST"  class="form-horizontal" id="emailForm" action="main">
                <input type="hidden" name="command" value="sendEmail">
                <div class="form-group">
                    <label for="emails" class="col-sm-1 control-label">Кому</label>
                    <div class="col-sm-11">
                        <input id="emails" type="text" class="form-control" name="emails" autocomplete="off"
                               value="${EMAILS}" required readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="subject" class="col-sm-1 control-label">Тема</label>
                    <div class="col-sm-11">
                        <input id="subject" type="text" class="form-control" name="subject" autocomplete="off" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="template" class="col-sm-1 control-label">Шаблон</label>
                    <div id="template" class="input-group input-group-cust">
                    <span class="input-group-addon">
                        <input type="checkbox" id="templateEnable" onchange="setTemplate(sel)">
                    </span>
                        <select id="sel" class="form-control" onchange="setTemplate(this)" name="template" disabled>
                            <option value="1">Шаблон 1</option>
                            <option value="2">Шаблон 2</option>
                        </select>
                    </div>
                </div>
                <textarea name="emailBody" id="textarea" class="form-control" form="emailForm" placeholder="Сообщение" rows="10"></textarea>
                <div class="row pull-right profile-text">
                    <button type="submit" class="btn btn-primary">Отправить</button>
                    <a href="main" class="btn btn-primary">Отмена</a>
                </div>
            </form>
            <c:set var="i" value="0" scope="page"/>
            <c:forEach items="${TEMPLATES}" var="temp">
                <c:set var="i" value="${i + 1}" scope="page"/>
                <input type="hidden" value="${temp}" id="ST${i}">
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
