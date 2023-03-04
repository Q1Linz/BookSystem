<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Qilin_
  Date: 2022/10/11
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty cookie.username.value && not empty cookie.password.value && empty sessionScope.user}">
        <jsp:forward page="/CookieServlet">
            <jsp:param name="action" value="test"/>
            <jsp:param name="username" value="${cookie.username.value}"/>
            <jsp:param name="password" value="${cookie.password.value}"/>
        </jsp:forward>
    </c:when>
</c:choose>
<form action="CookieServlet?action=test" method="post">
    用户名：<input type="text" name="username" value="${cookie.username.value}"> <br>
    密码：<input type="password" name="password" value="${cookie.password.value}"> <br>
    <input type="submit" value="登录">
</form>
</body>
</html>
