<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/custom.css">
</head>
<body>
<div class="container">
    <nav class="navbar navbar-default" style="transform: rotate(-9deg)">
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
                <li>
                    <a href="search-contact.jsp"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                        Поиск</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="col-sm-6 centered">
        <div class="col make-low">
            <h2>OOPS! YOU BROKE EVERYTHING.</h2>
            <img src="images/error-image.png" alt="Error image"/>
        </div>
    </div>
    <div class="make-low2 col-sm-6">
        <h1>HTTP ERROR:</h1>
        <b><p class="error-code">${ERROR_STATUS}</p></b>
    </div>
</div>
</body>
</html>