<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create tour</title>
</head>
<body>
<form action="CreateTour" method="post">
    price: <input type="text" name="price"><br>
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
</body>
</html>