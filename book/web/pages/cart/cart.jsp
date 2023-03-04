<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link type="text/css" rel="stylesheet" href="static/css/style.css">
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" width="230px" height="80px">
    <span class="wel_word">购物车</span>
    <div>
        <c:if test="${empty user}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
        <c:if test="${not empty user}">
        <span>欢迎<span class="um_span">${user.username}<span/>光临书城<span/>
            <a href="pages/order/order.jsp">我的订单</a>
            <a href="UserServlet?action=logout">注销</a>
        </c:if>
        <a href="index.jsp">返回</a>
    </div>
    <div id="main">

        <table>
            <tr>
                <td>商品名称</td>
                <td>数量</td>
                <td>单价</td>
                <td>金额</td>
                <td>操作</td>
            </tr>
            <c:if test="${empty sessionScope.cart.items}">
                <%--如果购物车空的情况--%>
                <tr>
                    <td colspan="5"><a href="index.jsp">亲，当前购物车为空！快跟小伙伴们去浏览商品吧！！！</a>
                </tr>
            </c:if>
            <c:forEach items="${sessionScope.cart.items}" var="entry">
                <tr>
                    <td>${entry.value.name}</td>
                    <td><input type="number" min="1" value="${entry.value.count}" onchange="changeCount('${entry.value.id}',this)"></td>
                    <td>${entry.value.price}</td>
                    <td>${entry.value.totalPrice}</td>
                    <td><a href="javascript:void(0)" onclick="mydelete('${entry.value.id}','${entry.value.name}')">删除</a></td>
                </tr>
            </c:forEach>
            <c:if test="${not empty sessionScope.cart.items}">
                <tr>
                    <td colspan="5">
                        <div class="cart_info">
                            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
                            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
                            <span class="cart_span"><a href="javascript:void (0)" onclick="myclear()">清空购物车</a></span>
                            <span class="cart_span"><a href="OrderServlet?action=createOrder">去结账</a></span>
                        </div>
                    </td>
                </tr>
            </c:if>
        </table>


    </div>
</div>


<jsp:include page="/pages/common/bottom.jsp"></jsp:include>
</body>
</html>
<script>
    function mydelete(id,name){
        if(window.confirm("你确定删除["+name+"]吗？")){
            window.location.href = "CartServlet?action=deleteItem&id="+id;
        }
    }
    function myclear(){
        if(window.confirm("确定清空购物车？")){
            window.location.href="CartServlet?action=clearItem";
        }
    }
    function changeCount(id,obj){
        if(obj.value > 0){
            window.location.href="CartServlet?action=updateCount&id="+id+"&count="+obj.value;
        }else {
            obj.value = 1;
            window.location.href="CartServlet?action=updateCount&id="+id+"&count="+obj.value;
        }

    }
</script>