package com.ming.ppsg.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2019/11/12 0012.
 */
public class StringUtils {


    public static void main(String[] args) {
        String str = "<td><tr>http://12.31.0.28:8080/Report/drill_full.jsp?<tr>" +
                "https://www.taobao.com/Report/drill_full.jsp?</tr></td>";
        //String pattern = "http://.+.com{1}";
        String pattern = "(https?://)?[^/\\\\s]*/Report/drill_full.jsp";
        String result = str.replaceAll(pattern, "/webapi/njreport");
        System.out.println("result:"+result);
        replace(str,pattern);
    }

    public static String replace(String str,String pattern){
        Pattern p = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        while (m.find()){
            String s = m.group();
            System.out.println(s);
        }
        return "";
    }

}
