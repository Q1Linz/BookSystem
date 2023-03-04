<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link type="text/css" rel="stylesheet" href="static/css/style.css">
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" width="230px" height="80px">
    <span class="wel_word">我的订单</span>
    <div>
        <span>欢迎<span class="um_span">${user.username}</span>光临网上书城</span>
        <a href="OrderServlet?action=listOrder">我的订单</a>
        <a href="UserServlet?action=logout">注销</a>&nbsp;&nbsp;
        <a href="index.jsp">返回</a>
    </div>
</div>
    <div id="main">
        <table>
            <tr>
                <td>订单编号</td>
                <td>日期</td>
                <td>金额</td>
                <td>状态</td>
                <td>详情</td>
            </tr>
            <c:forEach items="${page.items}" var="order">
                <tr>
                    <td>${order.orderId}</td>
                    <td>${order.createTime}</td>
                    <td>${order.price}</td>
                        <c:choose>
                            <c:when test="${order.status == 0}">
                                <td>未发货</td>
                            </c:when>
                            <c:when test="${order.status == 1}">
                                <td>已发货</td>
                            </c:when>
                            <c:otherwise>
                                <td>已签收</td>
                            </c:otherwise>
                        </c:choose>
                    <td><a href="pages/manager/detail.html">查看详情</a></td>
                </tr>
            </c:forEach>
        </table>
        <jsp:include page="/pages/common/page.jsp"></jsp:include>
    </div>

<jsp:include page="/pages/common/bottom.jsp"></jsp:include>
</body>
</html>