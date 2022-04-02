<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My orders</title>
    <link rel="stylesheet" href="styles/reset.css">
    <link rel="stylesheet" href="styles/sidebar.css">
    <link rel="stylesheet" href="styles/main.css">
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</head>
<body>
<%@include file="sidebar.jspf" %>
<div>
    <c:forEach var="entry" items="${requestScope.orderTour}">
        <c:set var="order" value="${entry.key}"/>
        <c:set var="tour" value="${entry.value}"/>
        <div>
            <img src="img/hotel.jpg" alt="hotel"> <br>
            order number: ${order.id}<br>
            status: ${order.status}<br>
            <p>TOUR #${tour.id}</p>
            <p>hot: ${tour.hot}</p>
            <p>type: ${tour.type}</p>
            <p>size group: ${tour.groupSize}</p>
            <p>hotel: ${tour.hotelStars}*</p>
            full price: ${tour.price}<br>
            discount: ${order.discount}%<br>
            your price: ${tour.price * (100 - order.discount) / 100}<br>
            <a href="ShowTour?id=${order.tourId}">review tour</a>
        </div>
        <%--manager section--%>
        <c:if test="${sessionScope.loggedUser.role == 'manager' || sessionScope.loggedUser.role == 'admin'}">
            <form action="ChangeOrderStatus" method="post">
                status:
                <select name="status">
                    <option value="registered">registered</option>
                    <option value="paid">paid</option>
                    <option value="canceled">canceled</option>
                </select>
                <input type="hidden" name="orderId" value="${order.id}">
                <input type="submit" value="change status">
            </form>
            <form action="SetOrderDiscount" method="post">
                discount: <input name="discount" type="number" min="0" step="1" max="100">
                <input type="hidden" name="id" value="${order.id}">
                <input type="submit" value="set discount">
            </form>
        </c:if>
        <br>
        <%--manager section--%>
    </c:forEach>
</div>
</body>
</html>