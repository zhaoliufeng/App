package servlet;

import bean.NetDataBean;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import conts.Config;
import conts.Req;
import conts.Resp;
import exception.MissParamException;
import exception.PropertiesNotFoundException;
import net.sf.json.JSONObject;
import utils.OutPrinter;
import utils.PropertiesUtils;
import utils.VersionCheck;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static conts.Config.UPDATE_FAIL;
import static conts.Config.UPDATE_RES;
import static conts.Config.UPDATE_SUCCESS;

public class VersionServlet extends HttpServlet {

    private HttpServletResponse mResp;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        if (mResp == null) {
            mResp = resp;
        }

        try {
            int opcode = Integer.valueOf(req.getParameter(Config.OP_CODE));
            //根据包名获取软件名称
            String appName = "MeshLife";
            switch (opcode) {
                case Req.VERSION_REQUEST_CODE: {
                    //查询版本信息
                    int st = Integer.valueOf(req.getParameter(Config.SYSTEM_TYPE));
                    String ver = req.getParameter(Config.VERSION);
                    String appid = req.getParameter(Config.APP_ID);
                    //校验参数完整性
                    if (ver == null || appid == null) {
                        throw new MissParamException();
                    }
                    getNewestVersion(
                            req.getServletContext().getRealPath(String.format(Config.MESHLIFE_CONFIG_PROPERTIES_PATH, appName).toLowerCase()),
                            st, ver);
                }
                break;
                case Req.EDIT_VERSION_INFO:
                    //修改版本信息
                    appName = String.valueOf(req.getParameter(Config.APP_NAME));
                    int st = Integer.valueOf(req.getParameter(Config.SYSTEM_TYPE));
                    String ver = req.getParameter(Config.VERSION);
                    String appid = req.getParameter(Config.APP_ID);
                    String updatePath = req.getParameter(Config.UPDATE_PATH);
                    String desc = req.getParameter(Config.DESC);
                    //校验参数完整性
                    if (ver == null || appid == null) {
                        throw new MissParamException();
                    }

                    updateVersion(
                            st, ver, updatePath, desc, appName, req, resp);
                    break;
            }
        } catch (MissParamException e) {
            //找不到op参数
            OutPrinter.getInstance(resp.getWriter()).printErr(
                    Resp.MISS_PARAMS);
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /*
        请求最新的版本信息
    */
    private void getNewestVersion(String ppsPath, int systemType, String version) throws PropertiesNotFoundException, IOException {
        NetDataBean netDataBean = null;
        switch (systemType) {
            case Config.ST_ANDROID:
                netDataBean = getAndroidVersion(ppsPath, version);
                break;
            case Config.ST_IOS:
                netDataBean = getIOSVersion(ppsPath, version);
                break;
        }
        if (netDataBean != null) {
            netDataBean.setCode(0);
        }

        OutPrinter.getInstance(mResp.getWriter()).print(netDataBean);
    }

    /*
        获取Android的版本信息
     */
    private NetDataBean getAndroidVersion(String ppsPath, String version) throws PropertiesNotFoundException {
        NetDataBean bean = new NetDataBean();
        bean.setNewestVersion(PropertiesUtils.readAndroidVersion(ppsPath));
        bean.setNeedUpdate(VersionCheck.checkVersion(version, bean.getNewestVersion()));
        bean.setUpdatePath(PropertiesUtils.readAndroidUpdatePath(ppsPath));
        bean.setDesc(PropertiesUtils.readAndroidDesc(ppsPath));
        return bean;
    }

    /*
      获取IOS的版本信息
   */
    private NetDataBean getIOSVersion(String ppsPath, String version) throws PropertiesNotFoundException {
        NetDataBean bean = new NetDataBean();
        bean.setNewestVersion(PropertiesUtils.readIOSVersion(ppsPath));
        bean.setNeedUpdate(VersionCheck.checkVersion(version, bean.getNewestVersion()));
        bean.setUpdatePath(PropertiesUtils.readIOSUpdatePath(ppsPath));
        bean.setDesc(PropertiesUtils.readIOSDesc(ppsPath));
        return bean;
    }

    /*
      修改版本号
    */
    private void updateVersion(int systemType, String version, String updatePath, String desc, String appName, HttpServletRequest req, HttpServletResponse resp) throws PropertiesNotFoundException, IOException, ServletException {
        String path = String.format(Config.MESHLIFE_CONFIG_PROPERTIES_PATH, appName).toLowerCase();
        String ppsPath = req.getServletContext().getRealPath(path);

        switch (systemType) {
            case Config.ST_ANDROID:
                updateAndroidVersion(
                        ppsPath, version, updatePath, desc);
                break;
            case Config.ST_IOS:
                updateIOSVersion(
                        ppsPath, version, updatePath, desc);
                break;
        }
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put(UPDATE_RES, updateRes);
//        OutPrinter.getInstance(mResp.getWriter()).print(jsonObject);
        req.getSession().setAttribute("isUpdate", 1);
        req.getRequestDispatcher("manage/get_app_info.jsp").forward(req, resp);

    }

    private int updateAndroidVersion(String ppsPath, String version, String updatePath, String desc) throws PropertiesNotFoundException {
        PropertiesUtils.updateAndroidVersion(ppsPath, version);
        if (updatePath != null) {
            PropertiesUtils.updateAndroidUpdatePath(ppsPath, updatePath);
        }
        if (desc != null) {
            PropertiesUtils.updateAndroidDesc(ppsPath, desc);
        }

        return UPDATE_SUCCESS;
    }

    private int updateIOSVersion(String ppsPath, String version, String updatePath, String desc) throws PropertiesNotFoundException {
        PropertiesUtils.updateIOSVersion(ppsPath, version);
        if (updatePath != null) {
            PropertiesUtils.updateIOSUpdatePath(ppsPath, updatePath);
        }
        if (desc != null) {
            PropertiesUtils.updateIOSDesc(ppsPath, desc);
        }

        return UPDATE_SUCCESS;
    }
}
