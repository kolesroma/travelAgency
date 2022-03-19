<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>My orders</title>
</head>
<body>
<div>
    <c:forEach var="order" items="${requestScope.orders}">
        <div>
            order number: ${order.id}
            status: ${order.status}
            discount: ${order.discount}%
            <a href="ShowTourServlet?id=${order.tourId}">review your order</a>
        </div>
    </c:forEach>
</div>
</body>
</html>