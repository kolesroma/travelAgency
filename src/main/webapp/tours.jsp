<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<style>
    .tour-container {
        display: flex;
        flex-direction: row;
        justify-content: center;
        gap: 40px;
    }

    img {
        max-width: 300px;
    }

    .tour {
        display: flex;
        flex-direction: column;
    }
</style>
<head>
    <meta charset="UTF-8">
    <title>home</title>
</head>
<body>
tours <br>
<a href="myInfo.jsp">my info</a>
<form action="LogoutServlet" method="post">
    <input type="submit" value="LOG OUT">
</form>
<div class="tour-container">
    <c:forEach var="tour" items="${requestScope.tours}">
        <div class="tour">
            <img src="img/hotel.jpg" alt="hotel">
            <p>first</p>
            <p>hot: ${tour.hot}</p>
            <p>price: ${tour.price}</p>
            <p>type: ${tour.type}</p>
            <p>size group: ${tour.groupSize}</p>
            <p>hotel: ${tour.hotelStars}*</p>
        </div>
    </c:forEach>
</div>
<a href="ShowToursServlet?page=${requestScope.page-1}">previous</a> ~~
<a href="ShowToursServlet?page=${requestScope.page+1}">next</a>
</body>
</html>