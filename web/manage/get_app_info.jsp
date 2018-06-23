<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="conts.Config" %>
<%@ page import="utils.PropertiesUtils" %>
<%@ page import="exception.PropertiesNotFoundException" %><%--
  Created by IntelliJ IDEA.
  User: zhaol
  Date: 2018/6/20
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String appName = String.valueOf(session.getAttribute(Config.APP_NAME));
    String name = String.valueOf(session.getAttribute(Config.NAME));
    try {
        String pName = request.getParameter(Config.NAME);
        String pAppName = request.getParameter(Config.APP_NAME);
        if (pName != null && !pName.equals("null")) {
            name = pName;
            appName = pAppName;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    System.out.println(appName);

    String appOutPath;
    String filesOutPath;
    String androidVersion = "null";
    String iosVersion = "null";
    String androidDesc = "null";
    String iosDesc = "null";
    String androidUpdatePath = "null";
    String iosUpdatePath = "null";
    List<String> files = new ArrayList<>();

    appOutPath = request.getServletContext().getRealPath(
            String.format(Config.MESHLIFE_CONFIG_PROPERTIES_PATH, appName.toLowerCase()));
    filesOutPath = request.getServletContext().getRealPath(Config.FILES_PROPERTIES_PATH);
    try {
        androidVersion = PropertiesUtils.readAndroidVersion(appOutPath);
        iosVersion = PropertiesUtils.readIOSVersion(appOutPath);
        androidDesc = PropertiesUtils.readAndroidDesc(appOutPath);
        iosDesc = PropertiesUtils.readIOSDesc(appOutPath);
        androidUpdatePath = PropertiesUtils.readAndroidUpdatePath(appOutPath);
        iosUpdatePath = PropertiesUtils.readIOSUpdatePath(appOutPath);
        files = PropertiesUtils.readFileList(filesOutPath);
    } catch (PropertiesNotFoundException e) {
        e.printStackTrace();
    }

    session.setAttribute(Config.NAME, name);
    session.setAttribute(Config.DESC_ANDROID, androidDesc);
    session.setAttribute(Config.VERSION_ANDROID, androidVersion);
    session.setAttribute(Config.DOWNLOAD_ANDROID, androidUpdatePath);
    session.setAttribute(Config.DESC_IOS, iosDesc);
    session.setAttribute(Config.VERSION_IOS, iosVersion);
    session.setAttribute(Config.DOWNLOAD_IOS, iosUpdatePath);
    session.setAttribute(Config.APP_NAME, appName);
    session.setAttribute(Config.FILE_LIST, files);

    //判断是否需要更新
    String isUpdate = String.valueOf(session.getAttribute(Config.IS_UPDATE));
    try {
        //获取从表单提交的更新flag 如果存在且等于0 则更新flag
        String pIsUpdate = request.getParameter(Config.IS_UPDATE);
        if (pIsUpdate != null && pIsUpdate.equals("0") ) {
            isUpdate = pIsUpdate;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    if (isUpdate.equals("1")) {
        out.print("<script>alert('软件信息更新成功'); window.location='setting.jsp' </script>");
    } else {
        response.sendRedirect("../setting.jsp");
    }
%>
