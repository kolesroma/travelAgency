<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<style>

    html, body {
        height: 100%;
    }

    .register-wrapper {
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100%;
    }

    .register-wrapper form {
        display: flex;
        flex-direction: column;
        gap: 12px;

    }


</style>
<body>
<div class="register-wrapper">
    <form action="Register" method="post">
        <label>
            <span>name</span>
            <input type="text" name="name" required placeholder="Daria">
        </label>
        <label>
            <span>surname</span>
            <input type="text" name="surname" required placeholder="Boiko">
        </label>
        <label>
            <span>age</span>
            <input type="number" min="1" step="1" name="age" required placeholder="19">
        </label>
        <label>
            <span>address</span>
            <input type="text" name="address" required placeholder="Yangelia, 19">
        </label>
        <label>
            <span>login</span>
            <input type="text" name="login" required placeholder="BoiDa">
        </label>
        <label>
            <span>password</span>
            <input type="password" name="password" required minlength="5" placeholder="********">
        </label>
        <input class="btn" type="submit" value="register">
    </form>
</div>
</body>
</html>