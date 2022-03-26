<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<style>

</style>
<head>
    <meta charset="UTF-8">
    <title>tour</title>
</head>
<body>
<div class="tour">
    <img src="img/hotel.jpg" alt="hotel">
    <p>TOUR #${requestScope.tour.id}</p>
    <p>hot: ${requestScope.tour.hot}</p>
    <p>price: ${requestScope.tour.price}</p>
    <p>type: ${requestScope.tour.type}</p>
    <p>size group: ${requestScope.tour.groupSize}</p>
    <p>hotel: ${requestScope.tour.hotelStars}*</p>
    <c:if test="${!requestScope.madeOrder}">
        <a href="CreateOrder?tourId=${requestScope.tour.id}">__ order this tour __</a>
    </c:if>
    <c:if test="${requestScope.madeOrder}">
        You made order for this tour ✔
    </c:if>
    <%--manager section--%>
    <hr>
    <c:if test="${sessionScope.loggedUser.role == 'manager' || sessionScope.loggedUser.role == 'admin'}">
        <form action="SetHotTour" method="post">
            <input type="hidden" name="tourId" value="${requestScope.tour.id}">
            <input type="submit" value="change hot">
        </form>
        <form action="#">
            <select name="status">
                <option value="registered">excursion</option>
                <option value="paid">shopping</option>
                <option value="canceled">vacation</option>
            </select>
            <input type="submit" value="change status">
        </form>
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
    </c:if>
    <%--admin section--%>
</div>
</body>
</html>