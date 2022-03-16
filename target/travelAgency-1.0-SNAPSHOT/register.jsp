<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="RegisterServlet" method="post">
    name: <input type="text" name="name"><br>
    surname: <input type="text" name="surname"><br>
    age: <input type="text" name="age"><br>
    address: <input type="text" name="address"><br>
    login: <input type="text" name="login"><br>
    password: <input type="password" name="password"><br>
    <input type="submit" value="REGISTER">
</form>
</body>
</html>