<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Log in</title>
</head>
<body>
<form action="LoginServlet" method="post">
    <input type="text" name="login"><br>
    <input type="password" name="password"><br>
    <input type="submit" value="LOG IN">
</form>
<a href="register.jsp">register</a>
</body>
</html>