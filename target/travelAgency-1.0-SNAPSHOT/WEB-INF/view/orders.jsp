<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
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
    <c:if test="${requestScope.orderTour.size() == 0}">No orders yet :(</c:if>
    <c:forEach var="entry" items="${requestScope.orderTour}">
        <c:set var="order" value="${entry.key}"/>
        <c:set var="tour" value="${entry.value}"/>
        <div class="item-js">
            <div class="tour-container">
                <div class="tour">
                    <img src="img/hotel.jpg" alt="hotel">
                    <c:if test="${tour.hot}">
                        <span class="fire-span"><ion-icon class="fire" name="flame"></ion-icon></span>
                    </c:if>
                    <p>order number: ${order.id}</p>
                    <p>status: ${order.status}</p>
                    <p>TOUR #${tour.id}</p>
                    <p>hot: ${tour.hot}</p>
                    <p>type: ${tour.type}</p>
                    <p>size group: ${tour.groupSize}</p>
                    <p>hotel: ${tour.hotelStars}⭐</p>
                    <p>full price: ${tour.price}</p>
                    <p>discount: ${order.discount}%</p>
                    <p>your price: <my:calc originalPrice="${tour.price}" disount="${order.discount}"/></p>
                    <form action="ShowTour">
                        <input type="hidden" name="id" value="${order.tourId}">
                        <input class="btn" type="submit" value="review tour">
                    </form>
                </div>
            </div>
            <%--manager section--%>
            <c:if test="${sessionScope.loggedUser.role == 'manager' || sessionScope.loggedUser.role == 'admin'}">
                <div class="status-discount-container float-admin">
                    <form action="ChangeOrderStatus" method="post" class="form-set">
                        <select name="status" id="types">
                            <option value="registered">registered</option>
                            <option value="paid">paid</option>
                            <option value="canceled">canceled</option>
                        </select>
                        <input type="hidden" name="orderId" value="${order.id}">
                        <input class="btn" type="submit" value="change status">
                    </form>
                    <form action="SetOrderDiscount" method="post" class="form-set">
                        <input name="discount" type="number" min="0" step="1" max="100" placeholder="20">
                        <input type="hidden" name="id" value="${order.id}">
                        <input class="btn" type="submit" value="set discount">
                    </form>
                </div>
            </c:if>
            <%--manager section--%>
        </div>
    </c:forEach>
</div>
</body>
</html>