<%--
  Created by IntelliJ IDEA.
  User: zhaol
  Date: 2018/6/20
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    System.out.println("logout");
    session.invalidate();
    request.getRequestDispatcher("../index.jsp").forward(request, response);
%>
