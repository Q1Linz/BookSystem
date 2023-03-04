<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link type="text/css" rel="stylesheet" href="static/css/style.css">
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" width="230px" height="80px">
    <span class="wel_word">网上书城</span>
    <div>
        <c:if test="${empty user}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
            <a href="pages/user/login.jsp">购物车</a>
        </c:if>
        <c:if test="${not empty user}">
        <span>欢迎<span class="um_span">${user.username}<span/>光临书城<span/>
            <a href="pages/cart/cart.jsp">购物车</a>
            <a href="OrderServlet?action=listOrder">我的订单</a>
            <a href="UserServlet?action=logout">注销</a>
        </c:if>
            <a href="login.jsp">后台管理</a>
    </div>
</div>
<div id="main" style="height: 550px">
    <div id="book">

        <div class="book_cond">
            <form action="BookServlet?action=queryByPrice" method="post">
                书名：<input type="text" name="name">
                作者：<input type="text" name="author">
                价格：<input id="min" type="text" name="min" value=""> 元 -
                <input id="max" type="text" name="max" value=""> 元
                <input type="submit" value="查询"/>
                <input type="hidden" name="action" value="queryByPrice">
            </form>
        </div>


        <div style="text-align: center">
            <div>
                <c:if test="${empty sessionScope.cart.items}">
                    <span style="color:red;">当前购物车为空</span>
                </c:if>
                <c:if test="${not empty sessionScope.cart.items}">
                    <span>您的购物车中有${cart.totalCount}件商品</span><br>
                    您刚刚将<span style="color: red">${cartName}</span>加入到了购物车中
                </c:if>
            </div>
        </div>
        <c:choose>
            <c:when test="${empty page.items}">
                <h1 style=" color: red; text-align: center">没有找到数据！</h1>
            </c:when>
        </c:choose>

        <c:forEach items="${page.items}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.imgPath}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.price}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <input type="button" onclick="addToCart('${book.id}')" value="加入购物车">
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <jsp:include page="/pages/common/page.jsp"></jsp:include>


</div>

<div id="bottom">
		<span>
			网上书城.Copyright &copy;2020
		</span>
</div>
</body>
</html>
<script>
    function jump() {
        var age = "/^[1-9]\d*|0$/";
        var inputobj = document.getElementById("pn_select");
        <%--window.location.href = "${page.url}pageNo=${page.pageNo}"--%>
        window.location.href = "${page.url}pageNo=" + inputobj.value;

    }


    function mydelete(id, name) {
        if (window.confirm("确定删除" + name + "吗？")) {
            window.location.href = "BookServlet?action=delete&id=" + id + "&pageNo=${page.pageNo}";
        }

    }

    function addToCart(bookId){
        if(${not empty user}){
            window.location.href="CartServlet?action=addItem&id="+bookId;
        }else {
            window.location.href="pages/user/login.jsp";
        }

    }

</script>