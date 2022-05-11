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
<%@include file="sidebar.jspf" %>
<div class="table-block">
    <p>USER</p>
    <p>id: ${requestScope.user.id}</p>
    <p>login: ${requestScope.user.login}</p>
    <p>name: ${requestScope.user.name}</p>
    <p>surname: ${requestScope.user.surname}</p>
    <p>age: ${requestScope.user.age}</p>
    <p>address: ${requestScope.user.address}</p>
    <p>role: ${requestScope.user.role}</p>
    <p>is banned: ${requestScope.user.banned}</p>
    <c:if test="${requestScope.user.banned}">
        <span><ion-icon class="ban" name="ban-outline"></ion-icon></span>
    </c:if>
    <c:if test="${!requestScope.user.banned}">
        <span><ion-icon class="tip" name="finger-print-outline"></ion-icon></span>
    </c:if>
</div>
<%--manager section--%>
<c:if test="${sessionScope.loggedUser.role == 'manager' || sessionScope.loggedUser.role == 'admin'}">
    <hr>
    <div class="float-admin">
        <form action="ShowUserOrders">
            <input type="hidden" name="id" value="${requestScope.user.id}">
            <input class="btn" type="submit" value="order list">
        </form>

        <div class="discount-container">
            <p>current discount step: ${requestScope.user.stepDiscount}</p>
            <p>current max discount: ${requestScope.user.maxDiscount}</p>
            <form action="SetDiscountStepMax" method="post">
                discount step: <input name="step" type="number" min="0" max="100" step="1" value="${requestScope.user.stepDiscount}">
                max discount: <input name="max" type="number" min="0" max="100" step="1" value="${requestScope.user.maxDiscount}">
                <input type="hidden" name="userId" value="${requestScope.user.id}">
                <input class="btn" type="submit" value="set step and max">
            </form>
        </div>
    </div>
</c:if>
<%--manager section--%>
<%--admin section--%>
<c:if test="${sessionScope.loggedUser.role == 'admin'}">
    <hr>
    <div class="float-admin">
        <form action="BanUser" method="post">
            <input type="hidden" name="id" value="${requestScope.user.id}">
            <input class="btn" type="submit" value="change ban">
        </form>
    </div>
</c:if>
<%--admin section--%>
</body>
</html>