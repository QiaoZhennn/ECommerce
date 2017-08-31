package com.atguigu.util;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

public class JSONUtil {
    public static String Object2Json(Object object){
        String s = JSON.toJSONString(object);
        try {
            s= URLEncoder.encode(s,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }
    public static <T> T Json2Object(String json,Class<T> clazz){
        try {
            json= URLDecoder.decode(json,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        T object = JSON.parseObject(json, clazz);
        return object;
    }
    public static <T> String List2Json(List<T> list){
        String s = JSON.toJSONString(list);
        try {
            s=URLEncoder.encode(s,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }
    public static <T> List<T> Json2List(String json,Class<T> clazz){
        try {
            json=URLDecoder.decode(json,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<T> ts = JSON.parseArray(json, clazz);
        return ts;
    }
}
