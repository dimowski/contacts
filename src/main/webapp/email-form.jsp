<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Send emails</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/custom.css">
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
            <form class="form-horizontal" id="emailForm">
                <div class="form-group">
                    <label for="emails" class="col-sm-1 control-label">Кому</label>
                    <div class="col-sm-11">
                        <input id="emails" type="text" class="form-control" name="emails" autocomplete="off" required>
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
                    <div id="template" class="input-group col-sm-5">
                    <span class="input-group-addon">
                        <input type="checkbox">
                    </span>
                        <select class="form-control " name="template">
                            <option value="1">Шаблон 1</option>
                            <option value="2">Шаблон 2</option>
                        </select>
                    </div>
                </div>
                <textarea class="form-control" form="emailForm" placeholder="Сообщение" rows="5"></textarea>
                <div class="row pull-right profile-text">
                    <button class="btn btn-primary">Отправить</button>
                    <button class="btn btn-primary">Отмена</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
