package conts;

public class Resp {
    //请求缺失参数
    public static int MISS_PARAMS = 100;
    //请求参数非法
    public static int INVALID_PARAMS = 101;

    //丢失文件
    private static int MISS_FILE = 200;
    //丢失配置文件
    public static int MISS_CONFIG_PROPERTIES = MISS_FILE + 1;
}
