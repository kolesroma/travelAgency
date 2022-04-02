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
<%@include file="sidebar.jspf"%>
<div>
    <c:forEach var="order" items="${requestScope.orders}">
        <div>
            order number: ${order.id}
            tour#${order.tourId}
            status: ${order.status}
            discount: ${order.discount}%
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