<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Contacts</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/custom.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

</body>
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
    <div class="row">
        <div class="col-md-4">
            <h3>Vasya Pupkin</h3>
            <div class="image-upload" style="display: inline-block">
                <label for="file-input">
                    <img src="images/defaultUserIcon.png"/>
                </label>
                <input type="file" accept="image/*" id="file-input"/>
            </div>
        </div>

        <div class="col-md-8">

        </div>
    </div>

</div>
</html>
