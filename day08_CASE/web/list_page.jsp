<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap模板</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>

    <script>
        function deleteLinkMan(id){
            if(confirm("确定删除该联系人吗?")){
                //alert("要去删除联系人了");
                //如果真的确定删除了，那么就要去请求删除的路径。
                //指定浏览器的地址栏变成这个地址， 变成这个地址，那么页面也就跳转到这来了。
                location.href="linkManServlet?method=delete&id="+id;
            }
        }
    </script>

</head>
<body>
<div class="container">
    <h3 style="text-align: center">欢迎用户<b>${user.username}</b>,使用联系人管理系统</h3>
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">

            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${page.pageList}" var="bean" varStatus="stat">
            <tr>
                <td>${stat.count}</td>
                <td>${bean.name}</td>
                <td>${bean.sex}</td>
                <td>${bean.age}</td>
                <td>${bean.address}</td>
                <td>${bean.qq}</td>
                <td>${bean.email}</td>
                <td><a class="btn btn-default btn-sm" href="linkManServlet?method=findById&id=${bean.id}">修改</a>&nbsp;
                    <a class="btn btn-default btn-sm" href="javascript:deleteLinkMan(${bean.id})">删除</a></td>
            </tr>
        </c:forEach>


        <tr>
            <td colspan="8" align="center">
                <ul class="pagination success">

                    <c:if test="${page.currentpage==1}" >
                         <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                    </c:if>
                    <c:if test="${page.currentpage!=1}" >
                        <li ><a href="linkManServlet?method=findAllPage&currentpage=${page.currentpage-1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                    </c:if>


                    <c:forEach begin="1" end="${page.totalpage}" var="i">

                        <c:if test="${page.currentpage==i}">
                            <li class="active"><a href="#">${i}</a></li>
                        </c:if>
                        <c:if test="${page.currentpage!=i}">
                            <li ><a href="linkManServlet?method=findAllPage&currentpage=${i}">${i}</a></li>
                        </c:if>


                    </c:forEach>


                    <c:if test="${page.currentpage==page.totalpage}" >
                        <li  class="disabled"><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                    </c:if>
                    <c:if test="${page.currentpage<page.totalpage}" >
                        <li><a href="linkManServlet?method=findAllPage&currentpage=${page.currentpage+1}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                    </c:if>

				</ul>
            </td>
        </tr>
        <tr>
            <td colspan="8" align="center"><a class="btn btn-primary" href="${pageContext.request.contextPath }/add.jsp">添加联系人</a></td>
        </tr>
    </table>
</div>
</body>
</html>
