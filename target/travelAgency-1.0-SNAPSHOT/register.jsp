<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<style>
    html,
    input{
        font-family: system-ui, -apple-system, Helvetica, Arial, sans-serif;
        font-size: 18px;
    }

    .register-wrapper {
        margin-top: 340px;
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

    .btn {
        font-weight: bold;
        font-size: 18px;
        line-height: 20px;
        color: #FFFFFF;
        padding: 12px 28px;
        background: #2F80ED;
        border-radius: 24px;
        border: none;
        display: block;
        cursor: pointer;
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
            <input type="password" name="password" required minlength="5" placeholder="password">
        </label>
        <input class="btn" type="submit" value="REGISTER">
    </form>
</div>
</body>
</html>