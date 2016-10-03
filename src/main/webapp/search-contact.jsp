<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Search contacts</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/custom.css">

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
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="row">
                <div class="col-sm-4">
                    <h3 class="panel-title">Поиск контактов</h3>
                </div>
            </div>
        </div>
        <div class="panel-body">
            <form class="form-horizontal" method="get" action="main">
                <input type="hidden" name="command" value="search">
                <div class="form-group">
                    <label for="firstName" class="col-sm-2 control-label">Имя</label>
                    <div class="col-sm-10">
                        <input type="text" id="firstName" placeholder="Введите имя" name="first_name"
                               autocomplete="off" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="lastName" class="col-sm-2 control-label">Фамилия</label>
                    <div class="col-sm-10">
                        <input type="text" id="lastName" placeholder="Введите фамилию" name="last_name"
                               autocomplete="off" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="middleName" class="col-sm-2 control-label">Отчество</label>
                    <div class="col-sm-10">
                        <input type="text" id="middleName" placeholder="Введите отчество" name="middle_name"
                               autocomplete="off" class="form-control">
                    </div>
                </div>
                <div class="form-group form-inline">
                    <label for="birthSince" class="col-sm-2 control-label">Дата рождения c</label>
                    <div class="col-sm-4">
                        <input type="date" id="birthSince" name="birthSince" autocomplete="off" class="form-control">
                    </div>
                    <label for="birthUpto" class="col-sm-1 control-label">По</label>
                    <div class="col-sm-4">
                        <input type="date" id="birthUpto" name="birthUpto" autocomplete="off" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="gender" class="col-sm-2 control-label">Пол</label>
                    <div class="col-sm-10">
                        <select id="gender" class="form-control" name="gender">
                            <option value=""></option>
                            <option value="М">мужской</option>
                            <option value="Ж">женский</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="status" class="col-sm-2 control-label">Семейное положение</label>
                    <div class="col-sm-10">
                        <select id="status" class="form-control" name="status">
                            <option value=""></option>
                            <option value="женат">женат</option>
                            <option value="холост">холост</option>
                            <option value="замужем">замужем</option>
                            <option value="не замужем">не замужем</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="citizenship" class="col-sm-2 control-label">Гражданство</label>
                    <div class="col-sm-10">
                        <input class="form-control" type="text" id="citizenship" name="citizenship"
                               placeholder="Введите гражданство">
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
                <div class="form-group">
                    <label for="house" class="col-sm-2 control-label">Номер дома</label>
                    <div class="col-sm-10">
                        <input type="text" id="house" placeholder="Введите номер дома" name="house" autocomplete="off"
                               class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="flat" class="col-sm-2 control-label">Номер квартиры</label>
                    <div class="col-sm-10">
                        <input type="text" id="flat" placeholder="Введите номер квартиры" name="flat" autocomplete="off"
                               class="form-control">
                    </div>
                </div>
                <button type="submit" class="btn btn-primary pull-right">Поиск</button>
            </form>
        </div>
    </div>

</div>
</body>
</html>
