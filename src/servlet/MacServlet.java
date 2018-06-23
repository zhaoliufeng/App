package servlet;

import conts.Config;
import conts.Req;
import conts.Resp;
import exception.PropertiesNotFoundException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.OutPrinter;
import utils.PropertiesUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static conts.Config.UPDATE_RES;

public class MacServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        try {
            int opcode = Integer.parseInt(req.getParameter(Config.OP_CODE));
            switch (opcode) {
                case Req.MAC_SAVE:
                    String ppsPath = req.getServletContext().getRealPath(Config.MAC_PROPERTIES_PATH);
                    saveGatewayMac(ppsPath, req.getParameter(Config.MAC), resp);
                    break;
                case Req.MAC_READ:
                    ppsPath = req.getServletContext().getRealPath(Config.MAC_PROPERTIES_PATH);
                    readGatewayMac(ppsPath, resp);
                    break;
            }
        } catch (PropertiesNotFoundException e) {
            //找不到配置文件 可能配置文件被删除
            switch (e.getMessage()) {
                case Config.MISS_VERSION_PROPERTIES:
                    OutPrinter.getInstance(resp.getWriter()).printErr(
                            Resp.MISS_CONFIG_PROPERTIES);
                    break;
            }
        } catch (NumberFormatException e) {
            //参数格式异常
            OutPrinter.getInstance(resp.getWriter()).printErr(
                    Resp.INVALID_PARAMS);
        }
    }

    private void readGatewayMac(String ppsPath, HttpServletResponse resp) throws PropertiesNotFoundException, IOException {
       List<String> macList = PropertiesUtils.readMacList(ppsPath);
       JSONObject jsonObject = new JSONObject();
       jsonObject.put(Config.CODE, 0);
       jsonObject.put(Config.MAC_LIST, JSONArray.fromObject(macList));
       OutPrinter.getInstance(resp.getWriter()).printArray(jsonObject);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    private void saveGatewayMac(String ppsPath, String mac, HttpServletResponse resp) throws PropertiesNotFoundException, IOException {
        PropertiesUtils.updateGatewayMac(ppsPath, mac);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Config.CODE, 0);
        jsonObject.put(UPDATE_RES, 1);
        OutPrinter.getInstance(resp.getWriter()).print(jsonObject);
    }
}
