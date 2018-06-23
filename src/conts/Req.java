package conts;

public class Req {

    /**
     * 指令请求基值0
     **/
    private static final int OP_BASE = 0;
    //请求当前最新版本
    public static final int VERSION_REQUEST_CODE = OP_BASE;
    //修改版本信息
    public static final int EDIT_VERSION_INFO = OP_BASE + 1;

    /**
     * 用户请求基值 100
     */
    private static final int ADMIN_BASE = 100;
    public static final int ADMIN_LOGON = ADMIN_BASE;

    /**
     * MAC地址记录 1000
     */
    private static final int MAC_BASE = 1000;
    public static final int MAC_SAVE = MAC_BASE;
    public static final int MAC_READ = MAC_BASE + 1;

}
