<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="static/css/bootstrap.css">
    <link rel="stylesheet" href="static/css/misc-pages.css">

    <title>登录-员工OA系统</title>

</head>
<body class="simple-page">


<div class="simple-page-wrap">

    <div class="simple-page-logo ">
        <a href="">
            <span></span>
            <span>图书后台管理系统</span>
        </a>
    </div><!-- logo -->

    <div class="simple-page-form" id="login-form">

        <div class="msg_cont">
            <h4 class="errorMsg">
                <c:out value="${msg}">请输入用户名和密码</c:out>
            </h4>
        </div>
        <form action="ManagerServlet" method="post">
            <input type="hidden" name="action" value="login">
            <div class="form-group">
                <input type="text" name="mname" class="form-control" placeholder="用户名" value="${manager.password}">
            </div>

            <div class="form-group">
                <input type="password" name="password" class="form-control" placeholder="密码" value="${manager.password}">
            </div>

            <div class="form-group m-b-xl">

                <input name="remenberme" value="remenberme" type="checkbox" id="keep_me_logged_in"
                       style="height:auto;margin:0 0 0 10px;"/>
                <label for="keep_me_logged_in">记住我</label>

            </div>

            <input type="submit" class="btn btn-primary" value="登录">
        </form>
    </div>

    <div class="simple-page-footer">
        <p>
            <small>新员工 ?</small>
            <a href="">创建账号</a>
        </p>
    </div>


</div>

</body>
</html>