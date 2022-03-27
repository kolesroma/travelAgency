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
<%@include file="WEB-INF/sidebar.jspf"%>
<form class="choose" action="ShowFound">
    <div class="type">
        TOUR<br>
        <label><input type="checkbox" name="vacation">vacation</label> <br>
        <label><input type="checkbox" name="excursion">excursion</label> <br>
        <label><input type="checkbox" name="shopping">shopping</label> <br>
    </div>
    <div class="hotel">
        Hotel<br>
        <label><input type="checkbox" name="star1">*</label> <br>
        <label><input type="checkbox" name="star2">**</label> <br>
        <label><input type="checkbox" name="star3">***</label> <br>
        <label><input type="checkbox" name="star4">****</label> <br>
        <label><input type="checkbox" name="star5">*****</label> <br>
    </div>
    <div class="price">
        price<br>
        <input type="number" min="0" step="100" name="priceFrom"> -
        <input type="number" min="0" step="100" name="priceTo">
    </div>
    <div class="group-size">
        group <br>
        <input type="number" min="0" step="1" name="groupFrom"> -
        <input type="number" min="0" step="1" name="groupTo">
    </div>
    <div>
        <input type="hidden" name="page" value="1">
        <input type="submit" value="FIND">
    </div>
</form>
<div class="tour-container">
    <c:forEach var="tour" items="${requestScope.tours}">
        <a href="ShowTour?id=${tour.id}">
            <div class="tour">
                <c:if test="${tour.hot}">
                    <span class="fire-span"><ion-icon class="fire" name="flame"></ion-icon></span>
                </c:if>
                <img src="img/hotel.jpg" alt="hotel">
                <p>TOUR #${tour.id}</p>
                <p>price: ${tour.price}</p>
                <p>type: ${tour.type}</p>
                <p>size group: ${tour.groupSize}</p>
                <p>hotel: ${tour.hotelStars}*</p>
            </div>
        </a>
    </c:forEach>
</div>
<a id="prev" class="prev" href="${requestScope.path}page=${requestScope.page-1}">previous</a> ~~
<a id="next" href="${requestScope.path}page=${requestScope.page+1}">next</a>
page: ${requestScope.page}
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