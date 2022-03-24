<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>User info</title>
</head>
<body>
USER<br>
id: ${requestScope.user.id}<br>
login: ${requestScope.user.login}<br>
name: ${requestScope.user.name}<br>
surname: ${requestScope.user.surname}<br>
age: ${requestScope.user.age}<br>
address: ${requestScope.user.address}<br>
role: ${requestScope.user.role}<br>
is banned: ${requestScope.user.banned}<br>
</body>
</html>