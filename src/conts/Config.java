package conts;

public class Config {
    public final static String MESHLIFE_CONFIG_PROPERTIES_PATH = "/properties/%s/appconfig.properties";
    public final static String ADMIN_PROPERTIES_PATH = "/properties/admin.properties";
    public final static String FILES_PROPERTIES_PATH = "/properties/files.properties";

    public final static String MISS_VERSION_PROPERTIES = "找不到配置文件";

    public final static String VERSION_INVALID = "版本格式不合法";

    //请求码
    public static final String OP_CODE = "op";
    //系统类型
    public static final String SYSTEM_TYPE = "st";

    public static final int ST_IOS = 0;
    public static final int ST_ANDROID = 1;
    public static final String IOS = "ios";
    public static final String ANDROID = "android";

    public static final String DOWNLOAD_IOS = "update_path_ios";
    public static final String DOWNLOAD_ANDROID = "update_path_android";

    public static final String DESC_IOS = "desc_ios";
    public static final String DESC_ANDROID = "desc_android";

    public static final String VERSION_IOS = "version_ios";
    public static final String VERSION_ANDROID = "version_android";



    //当前客户端版本号
    public static final String VERSION = "ver";

    //当前客户端应用appId
    public static final String APP_ID = "apid";

    //更新描述
    public static final String DESC = "ds";
    //更新路径
    public static final String UPDATE_PATH = "up";

    //android mesh_life 应用的appId
    public static final String APPID_ANDROID = "com.ws.mesh.mesh_life";
    //ios mesh_life 应用的appId
    public static final String APPID_IOS = "com.ws.mesh.mesh-life";

    //返回的错误码key
    public static final String ERROR_CODE = "ErrCode";
    //返回结果码
    public static final String CODE = "code";

    //修改返回结果
    public static final String UPDATE_RES = "updateRes";

    //修改成功
    public static final int UPDATE_SUCCESS = 1;
    //修改失败
    public static final int UPDATE_FAIL = 0;

    /*用户登录*/
    public static final String NAME = "name";
    public static final String PASSWORD = "password";

    public static final String APP_NAME = "apn";
    public static final String FILE_LIST = "file_list";

    public static final String IS_UPDATE = "isUpdate";
}
