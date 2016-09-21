<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Search contacts</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">

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
                        контактов
                        <span class="sr-only"></span></a>
                </li>
                <li class="active">
                    <a href="search-contact.jsp"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                        Поиск</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="page-header"><h3>Поиск контактов</h3></div>
    <!-- Search form -->
    <form method="get">
        <div class="form-horizontal">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="form-group">
                        <label for="firstName" class="col-sm-2 control-label">Имя</label>
                        <div class="col-sm-10">
                            <input type="text" id="firstName" placeholder="Введите имя" name="firstName"
                                   autocomplete="off" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lastName" class="col-sm-2 control-label">Фамилия</label>
                        <div class="col-sm-10">
                            <input type="text" id="lastName" placeholder="Введите фамилию" name="lastName"
                                   autocomplete="off" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="middleName" class="col-sm-2 control-label">Отчество</label>
                        <div class="col-sm-10">
                            <input type="text" id="middleName" placeholder="Введите отчество" name="firstName"
                                   autocomplete="off" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="birthday" class="col-sm-2 control-label">Дата рождения</label>
                        <div class="col-sm-10">
                            <input type="text" id="birthday" name="birthday" autocomplete="off" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="gender" class="col-sm-2 control-label">Пол</label>
                        <div class="col-sm-10">
                            <select id="gender" class="form-control">
                                <option value="male">мужской</option>
                                <option value="female">женский</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="status" class="col-sm-2 control-label">Семейное положение</label>
                        <div class="col-sm-10">
                            <select id="status" class="form-control">
                                <option value="1">женат</option>
                                <option value="2">холост</option>
                                <option value="3">замужем</option>
                                <option value="4">не замужем</option>
                            </select>
                        </div>
                    </div>
                    <label class="control-label">Адрес</label>
                    <div class="form-group">
                        <label for="country" class="col-sm-2 control-label">Страна</label>
                        <div class="col-sm-10">
                            <input type="text" id="country" placeholder="Введите страну" name="country"
                                   autocomplete="off" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="city" class="col-sm-2 control-label">Город</label>
                        <div class="col-sm-10">
                            <input type="text" id="city" placeholder="Введите город" name="city" autocomplete="off"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="street" class="col-sm-2 control-label">Улица</label>
                        <div class="col-sm-10">
                            <input type="text" id="street" placeholder="Введите улицу" name="street" autocomplete="off"
                                   class="form-control">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>


</div>
</body>
</html>
