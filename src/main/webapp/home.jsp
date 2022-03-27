<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>home</title>
    <link rel="stylesheet" href="styles/reset.css">
    <link rel="stylesheet" href="styles/sidebar.css">
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</head>
<body>
<%@include file="WEB-INF/sidebar.jspf"%>
<%--manager section--%>
<c:if test="${sessionScope.loggedUser.role == 'manager' || sessionScope.loggedUser.role == 'admin'}">
    all users:<jsp:include page="ShowAllUsers"/>
</c:if>
<%--manager section--%>
</body>
</html>