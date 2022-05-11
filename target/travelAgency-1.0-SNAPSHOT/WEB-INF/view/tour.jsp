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
<div class="tour-container">
    <div class="tour">
        <c:if test="${requestScope.tour.hot}">
            <span class="fire-span"><ion-icon class="fire" name="flame"></ion-icon></span>
        </c:if>
        <img src="img/hotel.jpg" alt="hotel">
        <p>TOUR #${requestScope.tour.id}</p>
        <p>price: ${requestScope.tour.price}</p>
        <p>type: ${requestScope.tour.type}</p>
        <p>size group: ${requestScope.tour.groupSize}</p>
        <p>hotel: ${requestScope.tour.hotelStars}⭐</p>
        <c:if test="${!requestScope.madeOrder}">
            <form action="CreateOrder">
                <input type="hidden" name="tourId" value="${requestScope.tour.id}">
                <input class="btn" type="submit" value="ORDER TOUR">
            </form>
        </c:if>
        <c:if test="${requestScope.madeOrder}">
            <p>You made order for this tour ✔</p>
        </c:if>
    </div>
</div>
<%--manager section--%>
<c:if test="${sessionScope.loggedUser.role == 'manager' || sessionScope.loggedUser.role == 'admin'}">
    <hr>
    <div class="float-admin">
        <form action="SetHotTour" method="post">
            <input type="hidden" name="tourId" value="${requestScope.tour.id}">
            <input class="btn" type="submit" value="change hot">
        </form>
        <div class="participants-users-container">
            <h2>Participants</h2>
            <jsp:include page="/ShowTourUsers">
                <jsp:param name="id" value="${requestScope.tour.id}"/>
            </jsp:include>
        </div>
    </div>
</c:if>
<%--manager section--%>
<%--admin section--%>
<c:if test="${sessionScope.loggedUser.role == 'admin'}">
    <hr>
    <form action="UpdateTour" method="post" class="create-tour-wrapper">
        <label>
            <span>Price</span>
            <input class="input_number span-margin" type="number" min="100" step="100" name="price" placeholder="1000"
                   required>
        </label>
        <label>
            <span>Type</span> <br>
            <select name="type" id="types" class="span-margin">
                <option value="excursion">excursion</option>
                <option value="shopping">shopping</option>
                <option value="vacation">vacation</option>
            </select>
        </label>
        <label>
            Is hot
            <input type="checkbox" name="isHot" class="span-margin">
        </label>
        <label>
            Group size
            <input class="input_number span-margin" type="number" min="1" step="1" name="groupSize" placeholder="10"
                   required>
        </label>
        <label>
            Hotel stars
            <input class="input_number span-margin" type="number" min="1" max="5" step="1" name="hotelStars"
                   placeholder="3" required>
        </label>
        <input type="hidden" name="tourId" value="${requestScope.tour.id}">
        <input class="btn" type="submit" value="update tour">
    </form>
    <div class="float-admin">
        <form action="DeleteTour" method="post">
            <input type="hidden" name="id" value="${requestScope.tour.id}">
            <input class="btn" type="submit" value="delete tour">
        </form>
    </div>
</c:if>
<%--admin section--%>
</body>
</html>