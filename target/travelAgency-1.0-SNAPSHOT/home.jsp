<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>home</title>
    <link rel="stylesheet" href="styles/reset.css">
    <link rel="stylesheet" href="styles/sidebar.css">
    <link rel="stylesheet" href="styles/main.css">
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</head>
<body>
<%@include file="WEB-INF/view/sidebar.jspf" %>
<section class="content">
    <div class="tour-button float-admin">
        <span><ion-icon class name="newspaper-outline"></ion-icon></span>
        <form action="createTour.jsp">
            <input class="btn" type="submit" value="Create tour">
        </form>
    </div>
    <%--manager section--%>
    <c:if test="${sessionScope.loggedUser.role == 'manager' || sessionScope.loggedUser.role == 'admin'}">
        <div class="users-container">
        <h1 id="h1-all-users">All users</h1>
        <jsp:include page="ShowAllUsers"/>
        </div>
    </c:if>
    <%--manager section--%>
</section>
</body>
</html>