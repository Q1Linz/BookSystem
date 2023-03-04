<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
</head>
<body>
<%request.setCharacterEncoding("utf-8");%>
<jsp:include page="header.jsp">
	<jsp:param name="msg" value="图书管理系统"/>
</jsp:include>
	
	<div id="main">

		<form action="BookServlet?action=queryByPrice" method="post">
			书名：<input type="text" name="name" value="${name}">
			作者：<input type="text" name="author" value="${author}">
			价格：<input id="min" type="text" name="min" value="${min}"> 元
			<input id="max" type="text" name="max" value="${max}"> 元
			<input type="submit" value="查询" />
			<input type="hidden" name="action" value="queryByPrice">
		</form>

		<table border="1" style="margin-top: 8px">
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td colspan="2"><a href="pages/manager/book_add.jsp">添加图书</a></td>
			</tr>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td>封面</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><img src="${book.imgPath}" width="50px" height="50px"></td>
					<td><a href="BookServlet?action=queryById&id=${book.id}&pageNo=${page.pageNo}">修改</a></td>
					<td><a href="javascript:void (0)" onclick="mydelete('${book.id}','${book.name}')">删除</a></td>
				</tr>
			</c:forEach>
		</table>

		<jsp:include page="/pages/common/page.jsp"></jsp:include>

	</div>

<jsp:include page="/pages/common/bottom.jsp"></jsp:include>
</body>
</html>
<script>

	function mydelete(id,name){
		if(window.confirm("确定删除" +name +"吗？")){
			window.location.href = "BookServlet?action=delete&id=" + id +"&pageNo=${page.pageNo}";
		}

	}
</script>