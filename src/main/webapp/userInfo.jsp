<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User info</title>
</head>
<body>
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
    <a href="ShowUserOrders?id=${requestScope.user.id}">order list</a>
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