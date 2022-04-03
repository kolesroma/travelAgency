<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User info</title>
    <link rel="stylesheet" href="styles/reset.css">
    <link rel="stylesheet" href="styles/sidebar.css">
    <link rel="stylesheet" href="styles/main.css">
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</head>
<body>
<%@include file="sidebar.jspf"%>
USER<br>
id: ${requestScope.user.id}<br>
login: ${requestScope.user.login}<br>
name: ${requestScope.user.name}<br>
surname: ${requestScope.user.surname}<br>
age: ${requestScope.user.age}<br>
address: ${requestScope.user.address}<br>
role: ${requestScope.user.role}<br>
is banned: ${requestScope.user.banned}<br>
<%--manager section--%>
<c:if test="${sessionScope.loggedUser.role == 'manager' || sessionScope.loggedUser.role == 'admin'}">
    <hr>
    <a href="ShowUserOrders?id=${requestScope.user.id}">order list</a> <br>
    current discount step: ${requestScope.user.stepDiscount} <br>
    current max discount: ${requestScope.user.maxDiscount}
    <form action="SetDiscountStepMax" method="post">
        discount step: <input name="step" type="number" min="0" max="100" step="1">
        max discount: <input name="max" type="number" min="0" max="100" step="1">
        <input type="hidden" name="userId" value="${requestScope.user.id}">
        <input type="submit" value="set step and max">
    </form>
</c:if>
<%--manager section--%>
<%--admin section--%>
<c:if test="${sessionScope.loggedUser.role == 'admin'}">
    <hr>
    <form action="BanUser" method="post">
        <input type="hidden" name="id" value="${requestScope.user.id}">
        <input type="submit" value="change ban">
    </form>
</c:if>
<%--admin section--%>
</body>
</html>