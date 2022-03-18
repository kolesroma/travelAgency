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
        <a href="ShowTourServlet?id=${tour.id}">
            <div class="tour">
                <img src="img/hotel.jpg" alt="hotel">
                <p>first</p>
                <p>hot: ${tour.hot}</p>
                <p>price: ${tour.price}</p>
                <p>type: ${tour.type}</p>
                <p>size group: ${tour.groupSize}</p>
                <p>hotel: ${tour.hotelStars}*</p>
            </div>
        </a>
    </c:forEach>
</div>
<a id="prev" class="prev" href="ShowToursServlet?page=${requestScope.page-1}">previous</a> ~~
<a id="next" href="ShowToursServlet?page=${requestScope.page+1}">next</a>
page: ${requestScope.page}
</body>

<script>
    function get(name) {
        if (name = (new RegExp('[?&]' + encodeURIComponent(name) + '=([^&]*)')).exec(location.search))
            return decodeURIComponent(name[1]);
    }
    let name = get("page");
    if (name <= "1") {
        const prev = document.getElementById("prev");
        prev.removeAttribute("href");
    }
    else if (name >= "20") {
        const next = document.getElementById("next");
        next.removeAttribute("href");
    }
</script>
</html>