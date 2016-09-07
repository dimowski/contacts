<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Список контактов</title>
</head>
<body>
    <h3>Список контактов</h3>
    <div>
        <table>
            <tr>
                <th>Полное имя</th>
                <th>Дата рождения</th>
                <th>Место работы</th>
            </tr>
            <c:forEach var="tempContact" items="${CONTACT_LIST}">
                <tr>
                    <td>${tempContact.firstName} ${tempContact.middleName} ${tempContact.lastName}</td>
                    <td>${tempContact.birthday}</td>
                    <td>${tempContact.jobCurrent}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>