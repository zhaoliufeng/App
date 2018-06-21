package servlet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] arg){
        String matchPatt = "^a\\d+[.]\\d+[.]\\d+";
        String s = "a1.3.2";
        String pattern = "[^0-9]";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(s);

        System.out.println( m.replaceAll("").trim());

        if (s.matches(matchPatt)){
            System.out.println("true");
        }else {
            System.out.println("false");
        }
    }
}
