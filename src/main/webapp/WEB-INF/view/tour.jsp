<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>tour</title>
    <link rel="stylesheet" href="styles/reset.css">
    <link rel="stylesheet" href="styles/sidebar.css">
    <link rel="stylesheet" href="styles/main.css">
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</head>
<body>
<%@include file="sidebar.jspf" %>
<div class="tour">
    <img src="img/hotel.jpg" alt="hotel">
    <p>TOUR #${requestScope.tour.id}</p>
    <p>hot: ${requestScope.tour.hot}</p>
    <p>price: ${requestScope.tour.price}</p>
    <p>type: ${requestScope.tour.type}</p>
    <p>size group: ${requestScope.tour.groupSize}</p>
    <p>hotel: ${requestScope.tour.hotelStars}*</p>
    <c:if test="${!requestScope.madeOrder}">
        <a href="CreateOrder?tourId=${requestScope.tour.id}">order this tour</a>
    </c:if>
    <c:if test="${requestScope.madeOrder}">
        You made order for this tour ✔
    </c:if>
</div>
<%--manager section--%>
<hr>
<c:if test="${sessionScope.loggedUser.role == 'manager' || sessionScope.loggedUser.role == 'admin'}">
    <form action="SetHotTour" method="post">
        <input type="hidden" name="tourId" value="${requestScope.tour.id}">
        <input type="submit" value="change hot">
    </form>
    participants:
    <jsp:include page="/ShowTourUsers">
        <jsp:param name="id" value="${requestScope.tour.id}"/>
    </jsp:include>
</c:if>
<%--manager section--%>
<%--admin section--%>
<c:if test="${sessionScope.loggedUser.role == 'admin'}">
    <hr>
    <form action="UpdateTour" method="post">
        price: <input type="number" min="100" step="100" name="price"><br>
        is hot: <input type="checkbox" name="isHot"><br>
        group size: <input type="number" min="1" step="1" name="groupSize"><br>
        type:
        <select name="type" id="types">
            <option value="excursion">excursion</option>
            <option value="shopping">shopping</option>
            <option value="vacation">vacation</option>
        </select>
        hotel stars: <input type="number" min="1" max="5" step="1" name="hotelStars"><br>
        <input type="hidden" name="tourId" value="${requestScope.tour.id}">
        <input type="submit" value="update tour">
    </form>
    <form action="DeleteTour" method="post">
        <input type="hidden" name="id" value="${requestScope.tour.id}">
        <input type="submit" value="delete tour">
    </form>
</c:if>
<%--admin section--%>
</body>
</html>