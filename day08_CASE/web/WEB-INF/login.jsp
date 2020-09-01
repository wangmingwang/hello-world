<%--
  Created by IntelliJ IDEA.
  User: yp
  Date: 11/20
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<center>
    <h1>用户登录</h1>
    <form action="login" method="post">
        姓名：<input type="text" name="username" value="${cookie.username.value}"/><br/>
        密码：<input type="password" name="password"/><br/>
        <input type="checkbox" name="remember"/> 记住用户名
        <br/>
        <input type="submit" value="登录"/>
    </form>
</center>

</body>

</html>