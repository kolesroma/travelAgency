<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create tour</title>
    <link rel="stylesheet" href="styles/reset.css">
    <link rel="stylesheet" href="styles/sidebar.css">
    <link rel="stylesheet" href="styles/main.css">
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</head>
<body>
<%@include file="WEB-INF/view/sidebar.jspf" %>
<%--admin section--%>
<form action="CreateTour" method="post">
    price: <input type="number" min="100" step="100" name="price"><br>
    is hot: <input type="checkbox" name="isHot"><br>
    group size: <input type="number" min="1" step="1" name="groupSize"><br>
    type: <select name="type" id="types">
    <option value="excursion">excursion</option>
    <option value="shopping">shopping</option>
    <option value="vacation">vacation</option>
</select>
    hotel stars: <input type="number" min="1" max="5" step="1" name="hotelStars"><br>
    <input type="submit" value="CREATE">
</form>
<%--admin section--%>
</body>
</html>