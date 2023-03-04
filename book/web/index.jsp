<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>
<c:choose>
	<c:when test="${not empty cookie.username.value && not empty cookie.password.value && empty sessionScope.user}">
		<jsp:forward page="/UserServlet">
			<jsp:param name="action" value="login"/>
			<jsp:param name="name" value="${cookie.username.value}"/>
			<jsp:param name="password" value="${cookie.password.value}"/>
			<jsp:param name="qiurl" value="index.jsp"/>
		</jsp:forward>
	</c:when>
	<c:otherwise>
		<jsp:forward page="BookServlet?action=searchPage"></jsp:forward>
	</c:otherwise>
</c:choose>

<jsp:include page="BookServlet?action=searchPage"></jsp:include>
</body>
</html>