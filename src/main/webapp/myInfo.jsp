<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>My info</title>
</head>
<body>
id: ${sessionScope.loggedUser.id}<br>
login: ${sessionScope.loggedUser.login}<br>
name: ${sessionScope.loggedUser.name}<br>
surname: ${sessionScope.loggedUser.surname}<br>
age: ${sessionScope.loggedUser.age}<br>
address: ${sessionScope.loggedUser.address}<br>
role: ${sessionScope.loggedUser.role}<br>

</body>
</html>