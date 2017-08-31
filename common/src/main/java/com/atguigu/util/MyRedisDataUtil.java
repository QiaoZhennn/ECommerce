package com.atguigu.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MyRedisDataUtil {
    public static <T> List<T> get_list_by_redis(Collection<String> strings,Class<T> tClass){
        List<T> list = new ArrayList<>();
        Iterator<String> iterator=strings.iterator();
        while (iterator.hasNext()) {
            String json=iterator.next();
            T obj=JSONUtil.Json2Object(json,tClass);
            list.add(obj);
        }
        return list;
    }
}
