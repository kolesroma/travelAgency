<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>error</title>
    <link rel="stylesheet" href="styles/reset.css">
    <link rel="stylesheet" href="styles/error.css">
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</head>
<body>
<div class="wrapper-error">
    <div id="home-container">
        <a id="icon-text" href="home.jsp">
            <span><ion-icon name="home-outline"></ion-icon></span>
            <h3>HOME</h3>
        </a>
        <hr>
    </div>
    <div>
        <h2>OOops, error happened: ${requestScope['javax.servlet.error.status_code']}</h2>
        <h2>Message: ${requestScope['javax.servlet.error.message']}</h2>
    </div>
</div>
</body>
</html>