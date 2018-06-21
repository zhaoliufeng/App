<%@ page import="conts.Config" %>
<%@ page import="utils.PropertiesUtils" %>
<%@ page import="exception.PropertiesNotFoundException" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String name = request.getParameter(Config.NAME);
    String password = request.getParameter(Config.PASSWORD);
    String outPath = request.getServletContext().getRealPath(Config.ADMIN_PROPERTIES_PATH);
    String nameInPps = "null";
    String pwInPps = "null";

    try {
        nameInPps = PropertiesUtils.readAdminName(outPath);
        pwInPps = PropertiesUtils.readAdminPassword(outPath);
    } catch (PropertiesNotFoundException e) {
        e.printStackTrace();
    }

    if (nameInPps.equals(name) &&
            pwInPps.equals(password)) {
        session.setAttribute(Config.NAME, name);
        session.setAttribute(Config.APP_NAME, "MeshLife");
        response.sendRedirect("get_app_info.jsp");
    } else {
        out.print("<script>alert('账户或密码错误'); window.location='index.jsp' </script>");
        out.flush();
        out.close();
    }
%>