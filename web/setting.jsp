<%@ page import="conts.Config" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: zhaol
  Date: 2018/6/19
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/setting.css">
    <title>设置</title>
</head>
<body onpageshow="onShow()">

<script>
    function onShow() {
        <%
           String name = String.valueOf(session.getAttribute(Config.NAME));

           if (name.equals("null")) {
              request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        %>
    }
</script>
<%
    String androidVersion = String.valueOf(session.getAttribute(Config.VERSION_ANDROID));
    String iosVersion = String.valueOf(session.getAttribute(Config.VERSION_IOS));
    String androidDesc = String.valueOf(session.getAttribute(Config.DESC_ANDROID));
    String iosDesc = String.valueOf(session.getAttribute(Config.DESC_IOS));
    String androidUpdatePath = String.valueOf(session.getAttribute(Config.DOWNLOAD_ANDROID));
    String iosUpdatePath = String.valueOf(session.getAttribute(Config.DOWNLOAD_IOS));
    String appName = String.valueOf(session.getAttribute(Config.APP_NAME));
    @SuppressWarnings("unchecked")
    List<String> files = (List<String>) session.getAttribute(Config.FILE_LIST);
%>

<div id="div_header_title">
    <div id="div_title">
        <span id="span_title"><%=appName%></span>
    </div>
    <div id="div_exit">
        <img src="img/exit.png" onclick="location.href='manage/logout.jsp'"/>
    </div>
</div>

<form action="manage/get_app_info.jsp" method="post">
    <input type="hidden" value="0" name="isUpdate">
    <input type="hidden" value="<%=name%>" name="<%=Config.NAME%>"/>
    <%
        for (String fileName : files) {
    %>
    <input type="submit" value="<%=fileName%>" name="<%=Config.APP_NAME%>" class="appInput"/>
    <%
        }
    %>
</form>

<div id="div_app_info">
    <div id="div_android_info">
        <form action="version" method="post">
            <h3>Android</h3>
            <input type="hidden" value="1" name="isUpdate">
            <input type="hidden" value="<%=appName%>" name="<%=Config.APP_NAME%>">
            <input type="hidden" value="com.ws.mesh.mesh_life" name="<%=Config.APP_ID%>">
            <input type="hidden" value="1" name="<%=Config.OP_CODE%>">
            <input type="hidden" value="1" name="<%=Config.SYSTEM_TYPE%>">
            版本号<br><input name="<%=Config.VERSION%>" value="<%=androidVersion%>" placeholder="1.0.0"/><br>
            更新地址<br><input name="<%=Config.UPDATE_PATH%>" value="<%=androidUpdatePath%>"
                           placeholder="http://we-smart.."/><br>
            更新描述<br><input name="<%=Config.DESC%>" value="<%=androidDesc%>" placeholder="更新描述"
                           class="longTextInput"/><br>
            <input class="submitButton" value="提交" type="submit"/>
        </form>
    </div>
    <hr/>
    <div id="div_ios_info">
        <form action="version" method="post">
            <h3>IOS</h3>
            <input type="hidden" value="1" name="isUpdate">
            <input type="hidden" value="<%=appName%>" name="<%=Config.APP_NAME%>">
            <input type="hidden" value="com.ws.mesh.mesh_life" name="<%=Config.APP_ID%>">
            <input type="hidden" value="1" name="<%=Config.OP_CODE%>">
            <input type="hidden" value="0" name="<%=Config.SYSTEM_TYPE%>">
            版本<br><input name="<%=Config.VERSION%>" value="<%=iosVersion%>" placeholder="1.0.0"/><br>
            更新地址<br><input name="<%=Config.UPDATE_PATH%>" value="<%=iosUpdatePath%>"
                           placeholder="http://we-smart.."/><br>
            更新描述<br><input name="<%=Config.DESC%>" value="<%=iosDesc%>" placeholder="更新描述"
                           class="longTextInput"/><br>
            <input class="submitButton" value="提交" type="submit"/>
        </form>
    </div>
</div>
</body>
</html>
