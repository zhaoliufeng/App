<%@ page import="conts.Req" %><%--
  Created by IntelliJ IDEA.
  User: zhaol
  Date: 2018/6/13
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/login.css">
    <title>管理员登录</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/manage/login_validate.jsp">
    <div id="mainFormDiv">
        <!--操作码 请求版本号-->
        <span id="loginText">登陆</span><br>
        <hr/>
        <input type="hidden" name="op" value="<%=Req.ADMIN_LOGON%>">
        <input type="hidden" name="isUpdate" value="0">
        <div id="inputDiv">
            <input name="name" class="inputBox" placeholder="用户名"><br/>
            <input type="password" name="password" class="inputBox" placeholder="密码"><br/>
            <div id="buttonDiv">
                <input type="submit" value="确定" class="submitButton">
            </div>
        </div>
    </div>
</form>
</body>
</html>
