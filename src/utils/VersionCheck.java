package utils;

import java.util.regex.Pattern;

import static conts.Config.VERSION_INVALID;

public class VersionCheck {

    /**
     * 解析版本号字符串 "1.0.1" return 101
     * @param version 版本号字符串
     * @return 版本号整型
     */
    private static int parseVersionString(String version){
        //校验版本号格式 1.0.0
        String checkPattern = "\\d+[.]\\d+[.]\\d+";
        if (version.matches(checkPattern)){
            String nonNumPattern = "[^0-9]";
            Pattern pattern = Pattern.compile(nonNumPattern);
            //除去版本字符串中的非数字字符 返回整型判断版本号大小
            return Integer.valueOf(
                    pattern.matcher(version).replaceAll("").trim());
        }
        return 0;
    }

    /**
     * 根据版本号判断是否需要更新升级
     * @param verStr 当前客户端版本号
     * @param newestVerStr 当前市场最新版本号
     * @return true 需要更新 false 不需要更新
     */
    public static boolean checkVersion(String verStr, String newestVerStr){
        int version = parseVersionString(verStr);
        int newestVersion = parseVersionString(newestVerStr);
        if (version == 0 || newestVersion == 0){
            throw new NumberFormatException(VERSION_INVALID);
        }
        return newestVersion > version;
    }
}
