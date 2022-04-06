<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="users">
    <c:forEach var="user" items="${requestScope.users}">
        <a href="ShowUser?id=${user.id}">${user.login} - ${user.surname}</a>
    </c:forEach>
</div>