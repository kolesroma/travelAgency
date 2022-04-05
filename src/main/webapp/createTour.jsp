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
<div class="all-space">
<form action="CreateTour" method="post" class="create-tour-wrapper">
    <label>
        <span>Price</span>
        <input class="input_number span-margin" type="number" min="100" step="100" name="price" placeholder="1000" required>
    </label>
    <label>
        <span>Type</span> <br>
        <select name="type" id="types"class="span-margin">
            <option value="excursion">excursion</option>
            <option value="shopping">shopping</option>
            <option value="vacation">vacation</option>
        </select>
    </label>
    <label>
        Is hot
        <input type="checkbox" name="isHot" class="span-margin">
    </label>
    <label>
        Group size
        <input class="input_number span-margin" type="number" min="1" step="1" name="groupSize" placeholder="10" required>
    </label>
    <label>
        Hotel stars
        <input class="input_number span-margin" type="number" min="1" max="5" step="1" name="hotelStars" placeholder="3" required>
    </label>
    <input class="btn" type="submit" value="CREATE">
</form>
</div>
<%--admin section--%>
</body>
</html>