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
<%@include file="WEB-INF/view/sidebar.jspf" %>
<div class="table-block">
    <span id="user-icon"><ion-icon name="person-circle-outline"></ion-icon></span>
    <p>id: ${sessionScope.loggedUser.id}</p>
    <p>login: ${sessionScope.loggedUser.login}</p>
    <p>name: ${sessionScope.loggedUser.name}</p>
    <p>surname: ${sessionScope.loggedUser.surname}</p>
    <p>age: ${sessionScope.loggedUser.age}</p>
    <p>address: ${sessionScope.loggedUser.address}</p>
    <p>role: ${sessionScope.loggedUser.role}</p>
</div>
</body>
</html>