<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Contacts</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/custom.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="js/tabswitch.js"></script>
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
    <div class="page-header"><h3>Иванов Иван Иванович</h3></div>
    <div class="row">
        <div class="col-sm-3">
            <div class="image-upload" style="display: inline-block">
                <label for="file-input">
                    <img src="images/defaultUserIcon.png"/>
                </label>
                <input type="file" accept="image/*" id="file-input"/>
            </div>
        </div>

        <div class="col-sm-9">
            <ul class="nav nav-tabs">
                <li role="presentation" class="tabmenu active" id="tab_1">
                    <a href="#" onclick="switchTab('profile_tab', 'content_1', 'tab_1');return false;" id="profile_tab">
                        Профиль
                    </a>
                </li>
                <li role="presentation" class="tabmenu" id="tab_2">
                    <a href="#" onclick="switchTab('phone_tab', 'content_2', 'tab_2');return false;" id="phone_tab">
                        Контактные телефоны
                    </a>
                </li>
                <li role="presentation" class="tabmenu" id="tab_3">
                    <a href="#" onclick="switchTab('atchment_tab','content_3','tab_3');return false;" id="atcment_tab">
                        Присоединения
                    </a>
                </li>
            </ul>

            <div id="content_1" class="tabcontent" style="padding-top: 20px">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Персональная информация</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-sm-4 text-muted">
                                <div class="profile-text">Имя:</div>
                                <div class="profile-text">Фамилия:</div>
                                <div class="profile-text">Отчество:</div>
                                <div class="profile-text">Дата рождения:</div>
                                <div class="profile-text">Пол:</div>
                                <div class="profile-text">Гражданство:</div>
                                <div class="profile-text">Семейное положение:</div>
                                <div class="profile-text">Web site:</div>
                                <div class="profile-text">Email:</div>
                                <div class="profile-text">Место работы:</div>
                            </div>
                            <div class="col-sm-8">
                                <div class="profile-text">Иван</div>
                                <div class="profile-text">Иванов</div>
                                <div class="profile-text">Иванович</div>
                                <div class="profile-text">06/06/1986</div>
                                <div class="profile-text">М</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="content_2" class="tabcontent" style="display:none;">
                2
            </div>

            <div id="content_3" class="tabcontent" style="display:none;">
                3
            </div>
        </div>
    </div>
</div>
</body>
</html>
