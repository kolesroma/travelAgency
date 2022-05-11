<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<%@include file="sidebar.jspf" %>
<form class="choose" action="ShowFound">
    <div class="type">
        <h2>TOUR</h2>
        <label><input type="checkbox" name="vacation">vacation</label> <br>
        <label><input type="checkbox" name="excursion">excursion</label> <br>
        <label><input type="checkbox" name="shopping">shopping</label> <br>
    </div>
    <div class="hotel">
        <h2>Hotel</h2>
        <label><input type="checkbox" name="star1">⭐</label> <br>
        <label><input type="checkbox" name="star2">⭐⭐</label> <br>
        <label><input type="checkbox" name="star3">⭐⭐⭐</label> <br>
        <label><input type="checkbox" name="star4">⭐⭐⭐⭐</label> <br>
        <label><input type="checkbox" name="star5">⭐⭐⭐⭐⭐</label> <br>
    </div>
    <div class="price">
        <h2>Price</h2>
        <input type="number" min="0" step="100" name="priceFrom"> -
        <input type="number" min="0" step="100" name="priceTo">
    </div>
    <div class="group-size">
        <h2>Group</h2>
        <input type="number" min="0" step="1" name="groupFrom"> -
        <input type="number" min="0" step="1" name="groupTo">
    </div>
    <div>
        <input type="hidden" name="page" value="1">
        <input class="btn" type="submit" value="FIND">
    </div>
</form>
<div class="tour-container">
    <c:forEach var="tour" items="${requestScope.tours}">
        <div class="tour">
            <c:if test="${tour.hot}">
                <span class="fire-span"><ion-icon class="fire" name="flame"></ion-icon></span>
            </c:if>
            <img src="img/hotel.jpg" alt="hotel">
            <p>TOUR #${tour.id}</p>
            <p>price: ${tour.price}</p>
            <p>type: ${tour.type}</p>
            <p>size group: ${tour.groupSize}</p>
            <p>hotel: ${tour.hotelStars}⭐</p>
            <form action="ShowTour">
                <input type="hidden" value="${tour.id}" name="id">
                <input type="submit" value="WATCH TOUR" onclick="">
            </form>
        </div>
    </c:forEach>
</div>
<div class="page-tab">
    <a id="prev" class="prev" href="${requestScope.path}page=${requestScope.page-1}"> < prev < </a>
    page: ${requestScope.page}
    <a id="next" href="${requestScope.path}page=${requestScope.page+1}"> > next > </a>
</div>
</body>
<script>
    function get(name) {
        if (name = (new RegExp('[?&]' + encodeURIComponent(name) + '=([^&]*)')).exec(location.search))
            return decodeURIComponent(name[1]);
    }

    let name = get("page");
    if (name <= 1) {
        const prev = document.getElementById("prev");
        prev.removeAttribute("href");
    }
    if (name >= ${requestScope.maxPage}) {
        const next = document.getElementById("next");
        next.removeAttribute("href");
    }
</script>
</html>