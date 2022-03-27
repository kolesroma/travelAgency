<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My info</title>
    <link rel="stylesheet" href="styles/reset.css">
    <link rel="stylesheet" href="styles/sidebar.css">
    <link rel="stylesheet" href="styles/main.css">
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</head>
<body>
<%@include file="WEB-INF/view/sidebar.jspf"%>

id: ${sessionScope.loggedUser.id}<br>
login: ${sessionScope.loggedUser.login}<br>
name: ${sessionScope.loggedUser.name}<br>
surname: ${sessionScope.loggedUser.surname}<br>
age: ${sessionScope.loggedUser.age}<br>
address: ${sessionScope.loggedUser.address}<br>
role: ${sessionScope.loggedUser.role}<br>

</body>
</html>