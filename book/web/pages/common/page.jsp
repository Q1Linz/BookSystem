<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>


<div id="page_nav" style="margin-top: 20px">
    <c:choose>
        <c:when test="${page.pageNo>1}">
            <a href="${page.url}pageNo=1">首页</a>
            <a href="${page.url}pageNo=${page.pageNo-1}">上一页</a>
        </c:when>
        <c:otherwise>
            <a>首页</a>
            <a>上一页</a>
        </c:otherwise>
    </c:choose>


    <c:choose>
        <%--情况1：如果总页码小于等于5 的情况，页码的范围是：1-总页码--%>
        <c:when test="${ requestScope.page.pageTotal <= 5 }">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>
        <%--情况2：总页码大于5 的情况--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--小情况1：当前页码为前面3 个：1，2，3 的情况，页码范围是：1-5.--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%--小情况2：当前页码为最后3 个，8，9，10，页码范围是：总页码减4 - 总页码--%>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>
                <%--小情况3：4，5，6，7，页码范围是：当前页码减2 - 当前页码加2--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i != requestScope.page.pageNo}">
            <a href="${page.url}pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <c:choose>
        <c:when test="${page.pageNo<page.pageTotal}">
            <a href="${page.url}pageNo=${page.pageNo+1}">下一页</a>
            <a href="${page.url}pageNo=${requestScope.page.pageTotal}">末页</a>
        </c:when>
        <c:otherwise>
            <a>下一页</a>
            <a>末页</a>
        </c:otherwise>
    </c:choose>


    共${ requestScope.page.pageTotal }页，${ requestScope.page.pageTotalCount }条记录

    <%--			到第<input value="${page.pageNo}" name="pn" id="pn_input"/>页--%>
    <%--			<input type="button" value="确定" onclick="jump()">--%>

    到第<select id="pn_select" onchange="jump()">
    <option value="" selected="selected"></option>
    <script>
        for (var i = 1; i <=${page.pageTotal}; i++) {
            document.write("<option value='" + i + "'>" + i + "</option>");
        }
    </script>
</select>页


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
</script>