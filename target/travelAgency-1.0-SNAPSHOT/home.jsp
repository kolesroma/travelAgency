<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>tours</title>
</head>
<body>
sweet home <br>
<a href="myInfo.jsp">my info</a>
<form action="LogoutServlet" method="post">
    <input type="submit" value="LOG OUT">
</form>
<form action="ShowToursServlet?page=1" method="get">
    <input type="submit" value="GO TO TOURS">
</form>

</body>
</html>