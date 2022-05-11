<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Log in</title>
</head>
<style>
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

    .input_data {
        outline: 0;
        border-width: 0 0 1px;
        border-color: gray;
        width: 160px;
        height: 28px;
        font-family: system-ui, -apple-system, Helvetica, Arial, sans-serif;
        font-size: 18px;
        margin: 14px 0;
    }

    .input_data:focus {
        border-color: black;
    }

    .table-block {
        margin-top: 340px;
        height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    #reg-form {
        margin-top: 20px;
    }

    .rg-btn {
        background: #6fcf97;
    }
</style>
<body>
<div class="table-block">
    <div>
        <form action="Login" method="post">
            <input class="input_data" type="text" name="login" required minlength="1" placeholder="login"><br>
            <input class="input_data" type="password" name="password" required minlength="5" placeholder="password"><br>
            <input class="btn" type="submit" value="LOG IN">
        </form>
        <form id="reg-form" action="register.jsp">
            <input class="btn rg-btn" type="submit" value="REGISTER">
        </form>
    </div>
</div>
</body>
</html>