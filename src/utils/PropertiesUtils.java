package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import conts.Config;
import exception.PropertiesNotFoundException;

import static conts.Config.*;

/**
 * 配置文件读写
 */
public class PropertiesUtils {

    /**
     * 写配置文件
     * @param outPath 配置文件的路径
     * @param key 写入配置文件的key
     * @param value 写入配置文件的值
     * @throws PropertiesNotFoundException 可能会出现找不到文件的异常
     */
    private static void writeProperties(String outPath, String key, String value)throws PropertiesNotFoundException {
        try{
            Properties pps = new Properties();
            pps.load(new InputStreamReader(
                    new FileInputStream(outPath), "UTF-8"));

            OutputStream out = new FileOutputStream(outPath);
            pps.setProperty(key, value);
            pps.store(out, key);
        }catch (IOException e){
            throw new PropertiesNotFoundException(Config.MISS_VERSION_PROPERTIES);
        }
    }

    //写ios版本号
    public static void updateIOSVersion(String outPath, String iosVersion)throws PropertiesNotFoundException{
        writeProperties(outPath, IOS, iosVersion);
    }

    //写android版本号
    public static void updateAndroidVersion(String outPath, String androidVersion)throws PropertiesNotFoundException{
        writeProperties(outPath, ANDROID, androidVersion);
    }

    //写ios更新描述
    public static void updateIOSDesc(String outPath, String iosDesc)throws PropertiesNotFoundException{
        writeProperties(outPath, DESC_IOS, iosDesc);
    }

    //更新android更新描述
    public static void updateAndroidDesc(String outPath, String androidDesc)throws PropertiesNotFoundException{
        writeProperties(outPath, DESC_ANDROID, androidDesc);
    }

    //更新ios下载地址
    public static void updateIOSUpdatePath(String outPath, String iosUpdatePath)throws PropertiesNotFoundException{
        writeProperties(outPath, DOWNLOAD_IOS, iosUpdatePath);
    }

    //更新android下载地址
    public static void updateAndroidUpdatePath(String outPath, String androidUpdatePath)throws PropertiesNotFoundException{
        writeProperties(outPath, DOWNLOAD_ANDROID, androidUpdatePath);
    }

    //写网关mac地址 mac 存储时的key value 都是mac地址
    public static void updateGatewayMac(String outPath, String mac) throws PropertiesNotFoundException{
        writeProperties(outPath, mac, mac);
    }

    /**
     * 读配置文件
     * @param outPath 配置文件的路径
     * @param key 读取的key值
     * @return 读取到的结果 结果为String类型
     * @throws PropertiesNotFoundException 可能会出现找不到文件的异常
     */
    private static String readProperties(String outPath, String key) throws PropertiesNotFoundException{
        try {
            Properties pps = new Properties();
            pps.load(new InputStreamReader(
                    new FileInputStream(outPath), "UTF-8"));
            return pps.getProperty(key);
        }catch (IOException e){
            throw new PropertiesNotFoundException(Config.MISS_VERSION_PROPERTIES);
        }
    }

    private static List<String> readPropertiesList(String outPath) throws PropertiesNotFoundException{
        List<String> fileList = new ArrayList<>();
        try {
            Properties pps = new Properties();
            pps.load(new InputStreamReader(
                    new FileInputStream(outPath), "UTF-8"));
            Enumeration en = pps.propertyNames();
            while (en.hasMoreElements()){
                String strKey = (String) en.nextElement();
                String strValue = pps.getProperty(strKey);
                System.out.println(strKey + "=" + strValue);
                fileList.add(strValue);
            }
            return fileList;
        }catch (IOException e){
            throw new PropertiesNotFoundException(Config.MISS_VERSION_PROPERTIES);
        }
    }

    //读取ios版本号
    public static String readIOSVersion(String outPath) throws PropertiesNotFoundException{
        return readProperties(outPath, IOS);
    }

    //读取android版本号
    public static String readAndroidVersion(String outPath) throws PropertiesNotFoundException{
        return readProperties(outPath, ANDROID);
    }

    //读取ios下载路径
    public static String readIOSUpdatePath(String outPath) throws PropertiesNotFoundException {
        return readProperties(outPath, DOWNLOAD_IOS);
    }

    //读取android下载路径
    public static String readAndroidUpdatePath(String outPath) throws PropertiesNotFoundException {
        return readProperties(outPath, DOWNLOAD_ANDROID);
    }

    //读取ios更新描述
    public static String readIOSDesc(String outPath) throws PropertiesNotFoundException {
        return readProperties(outPath, DESC_IOS);
    }

    //读取android更新描述
    public static String readAndroidDesc(String outPath) throws PropertiesNotFoundException {
        return readProperties(outPath, DESC_ANDROID);
    }

    //读取用户名
    public static String readAdminName(String outPath) throws PropertiesNotFoundException {
        return readProperties(outPath, NAME);
    }

    //读取密码
    public static String readAdminPassword(String outPath) throws PropertiesNotFoundException {
        return readProperties(outPath, PASSWORD);
    }

    //读取文件列表
    public static List<String> readFileList(String outPath) throws PropertiesNotFoundException {
        return readPropertiesList(outPath);
    }

    //读取mac列表
    public static List<String> readMacList(String outPath)throws PropertiesNotFoundException{
        return readPropertiesList(outPath);
    }
}
