<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>home</title>
</head>
<body>
sweet home <a href="ShowMyOrders">my orders</a> <br>
<a href="myInfo.jsp">my info</a>
<form action="LogoutServlet" method="post">
    <input type="submit" value="LOG OUT">
</form>
<form action="ShowToursServlet" method="get">
    <input type="hidden" name="page" value="1">
    <input type="submit" value="GO TO TOURS">
</form>
<%--manager section--%>
<c:if test="${sessionScope.loggedUser.role == 'manager' || sessionScope.loggedUser.role == 'admin'}">
    all users:<jsp:include page="ShowAllUsers"/>
</c:if>
<%--manager section--%>
</body>
</html>