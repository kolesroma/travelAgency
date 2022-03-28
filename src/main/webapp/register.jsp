<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="Register" method="post">
    name: <input type="text" name="name" required><br>
    surname: <input type="text" name="surname" required><br>
    age: <input type="number" min="1" step="1" name="age" required><br>
    address: <input type="text" name="address" required><br>
    login: <input type="text" name="login" required><br>
    password: <input type="password" name="password" required minlength="5"><br>
    <input type="submit" value="REGISTER">
</form>
</body>
</html>