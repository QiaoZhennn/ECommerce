package com.atguigu.controller;

import com.atguigu.bean.*;
import com.atguigu.service.SearchService;
import com.atguigu.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/search_keywords")
    public String search_keywords(String keywords,ModelMap map){
        keywords.replaceAll(" ",""); //把搜索关键字的空格替换成空串，如果不替换，空格也会成为搜索关键字的一部分。
        List<OBJECT_T_MALL_SKU> list_sku=new ArrayList<>();
        //访问关键字检索服务，输入关键字。
        try {
            String doGet=MyHttpGetUtil.doGet(MyProperty.getMyProperty("solrServer.properties","url")+keywords);
            list_sku=JSONUtil.Json2List(doGet,OBJECT_T_MALL_SKU.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("keywords",keywords);
        map.put("list_sku","");
        return "sale_keywords";
    }


    @RequestMapping("/class_2_sku_search")
    public String class_2_sku_search(int class_2_id,String class_2_name, ModelMap map){
        List<OBJECT_T_MALL_ATTR> list_attr = searchService.select_attr_by_class_2(class_2_id);
        map.put("list_attr",list_attr);
        map.put("class_2_name", StringUtil.decode(class_2_name));
        map.put("class_2_id", class_2_id);
        return "sale_search";
    }

    @RequestMapping("/get_search_result")
    public String get_search_result( ModelMap map,int class_2_id){
        List<OBJECT_T_MALL_SKU> list_sku=new ArrayList<>();
        //查询redis缓存数据
        Jedis jedis= JedisPoolUtils.getJedis();
        String key="class_2_"+class_2_id;
        if (jedis != null) {
            Set<String> zrange=jedis.zrange(key,0,-1);
            if (zrange != null && zrange.size() > 0) {
                list_sku = MyRedisDataUtil.get_list_by_redis(zrange, OBJECT_T_MALL_SKU.class);
            } else {
                //如果redis中没有，则去mysql查找，并更新redis。
                list_sku=searchService.search_sku_by_class_2(class_2_id);
                //将数据库的数据同步到redis
                for (int i = 0; i < list_sku.size(); i++) {
                    jedis.zadd(key, i, JSONUtil.Object2Json(list_sku.get(i)));
                }
            }
        }else {
            //没有开Redis，获取不到连接。从Mysql中直接读取数据。
            list_sku=searchService.search_sku_by_class_2(class_2_id);

        }

        map.put("list_sku",list_sku);
        return "sale_search_inner";
    }

    @RequestMapping("/get_sku_by_attr")
    public String get_sku_by_attr(int class_2_id, MODEL_T_MALL_SKU_ATTR_VALUE list_av,String order,  ModelMap map){
        List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<>();
        //查询redis的缓存数据
        Jedis jedis = JedisPoolUtils.getJedis();

        if (jedis != null) {
            String[] keys = new String[0];
            if (list_av.getList_av()!=null&&list_av.getList_av().size()>0){
                keys = new String[list_av.getList_av().size()];
                for (int i = 0; i < list_av.getList_av().size(); i++) {
                    keys[i]="av_"+class_2_id+"_"+list_av.getList_av().get(i).getShxm_id()+"_"+list_av.getList_av().get(i).getShxzh_id();
                }
            }
            //此处的out不能写死，不同的线程可能用不同的属性值key作为查询条件。
            String out="out";
            for (int i = 0; i < keys.length; i++) {
                //动态拼接一个key，作为zinterstore的输出数组，这个key，包含了特定的属性交叉组合的sku集合。
                out+="_"+keys[i];
            }
            Boolean exists = jedis.exists(out);
            if(!exists){
                //如果原来交叉检索的组合条件没有出现过，则进行第一次交叉检索，同时记录该交叉检索的结果。否则直接取已存在的交叉检索结果。
                if(keys.length>0){
                    jedis.zinterstore(out, keys);
                }
            }
            Set<String> zrange=jedis.zrange(out,0,-1);
            if (zrange != null && zrange.size() > 0) {
                //从redis中获取数据
                list_sku = MyRedisDataUtil.get_list_by_redis(zrange, OBJECT_T_MALL_SKU.class);
            } else {
                //从mysql中获取数据
                list_sku = searchService.search_sku_by_attr(class_2_id, list_av.getList_av(), order);
            }
        }else {
            //没有开Redis，从Mysql中直接读取数据
            list_sku = searchService.search_sku_by_attr(class_2_id, list_av.getList_av(), order);
        }

        map.put("list_sku",list_sku);
        return "sale_search_inner";
    }

}
