package servlet;

import conts.Config;
import conts.Req;
import exception.PropertiesNotFoundException;
import utils.PropertiesUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        int op = Integer.parseInt(req.getParameter(Config.OP_CODE));
        System.out.println("OpCode : " + op);
        try {
            switch (op) {
                case Req.ADMIN_LOGON:
                    adminLogin(req, resp);
                    break;
            }
        } catch (PropertiesNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    //用户登录
    private void adminLogin(HttpServletRequest req, HttpServletResponse resp) throws PropertiesNotFoundException, ServletException, IOException {
        String nameInPps = PropertiesUtils.readAdminName(req.getServletContext().getRealPath(Config.ADMIN_PROPERTIES_PATH));
        String pwInPps = PropertiesUtils.readAdminPassword(req.getServletContext().getRealPath(Config.ADMIN_PROPERTIES_PATH));
        String name = req.getParameter(Config.NAME);
        String password = req.getParameter(Config.PASSWORD);
        if (nameInPps.equals(name) &&
                pwInPps.equals(password)) {
            req.setAttribute(Config.NAME, name);
            req.getRequestDispatcher("setting.jsp").forward(req,resp);
        } else {
            PrintWriter out = resp.getWriter();
            out.print("<script>alert('账户或密码错误'); window.location='index.jsp' </script>");
            out.flush();
            out.close();
        }
    }
}
