package com.atguigu.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class StringUtil {
    public static boolean isEmpty(String content){
        if(content==null||"".equals(content)||content.length()==0){
            return true;
        }
            return false;
    }

    public static String encode(String s){
        try {
            s= URLEncoder.encode(s,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String decode(String s){
        /*饶了地球一圈，终于实现了JSP向后台传递中文。
        1、JSP获取中文，编码成%AB%CD这样的URL格式：var encodeStr=encodeURI(class_2_name);
        2、JS代码，使用正则表达式，将%替换为_：var anotherStr=encodeStr.replace(/%/g,'_'); g表示全部匹配
        3、后台接收被替换的字符串
        4、java将_替换为%：String replaceBack = class_2_name.replaceAll("_","%");
        5、java进行URL解码decode = URLDecoder.decode(replaceBack, "UTF-8");
        6、这个decode即为前端传来的中文。
         */
        String replace_ = s.replaceAll("_","%");
        String decode=null;
        try {
            decode = URLDecoder.decode(replace_, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decode;
    }
}
