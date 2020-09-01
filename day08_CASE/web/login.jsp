<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/8/3
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>用户登入</title>
    <script>
        function _onclick(){
            code_imgid.src="createImg?x="+new Date();
        }
    </script>
</head>
<body>

<center>
    <h2>用户登录</h2>
    <form action="userServlet?method=login" method="post">
        姓&nbsp;&nbsp;&nbsp;名:<input type="text" name="username" value="${cookie.username.value}"/><br/>
        密&nbsp;&nbsp;&nbsp;码:<input type="password" name="password" value="${password}"/><br/>
        验证码:<input type="text" name="code" value="${code}"/><br/>
        <img id="code_imgid" src="createImg" onclick="_onclick()"><br/>
        <input type="checkbox"  <c:if test="${cookie.username.value!=''}">checked</c:if> name="bear" value="1">记住用户名
        <input type="checkbox" name="show" value="1">分页查询
        <br/>
        <input type="submit" value="登录"/><br/>
        <span style="color: red">${error}</span>
    </form>
</center>
</body>
</html>
