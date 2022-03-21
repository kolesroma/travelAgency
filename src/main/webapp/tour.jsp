<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<style>

</style>
<head>
    <meta charset="UTF-8">
    <title>home</title>
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
        <a href="RegisterOrderServlet?tourId=${requestScope.tour.id}">__ order this tour __</a>
    </c:if>
    <c:if test="${requestScope.madeOrder}">
        You made order for this tour ✔
    </c:if>
</div>
</body>
</html>