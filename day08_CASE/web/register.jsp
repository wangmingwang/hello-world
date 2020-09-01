<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/8/3
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户注册</title>
</head>
<body>

<center><h1>用户注册</h1>
    <table >

        <tr ><td></td>
            <td >
                <form action="userServlet?method=register" method="post">
                    姓名:<input type="text" name="username" value="${user.username}" /><br/>
                    密码:<input type="password" name="password" value="${user.password}" /><br/>
                    昵称:<input type="text" name="nickname" value="${user.nickname}" /><br/>
                    地址:<input type="text" name="address" value="${user.address}"  /><br/>
                    邮箱:<input type="text" name="email" value="${user.email}" /><br/>
                    性别:<input type="radio" name="sex" value="女" <c:if test="${user.sex=='女'}"> checked </c:if>/>女
                    <input type="radio" name="sex"  value="男" <c:if test="${user.sex=='男'}"> checked </c:if>/>男<br/>
                    <br/>
                    <center> <input type="submit" value="注册"/></center><br/>
                    <span style="color: red">${error}</span>
                </form>

            </td>
            <td></td>
        </tr>

    </table>
</center>
</body>
</html>
