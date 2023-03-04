<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
	<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
<%request.setCharacterEncoding("utf-8");%>
<jsp:include page="header.jsp">
	<jsp:param name="msg" value="后台管理系统"/>
</jsp:include>
	
	<div id="main">
		<h1>欢迎管理员进入后台管理系统</h1>
	</div>

<jsp:include page="/pages/common/bottom.jsp"></jsp:include>
</body>
</html>