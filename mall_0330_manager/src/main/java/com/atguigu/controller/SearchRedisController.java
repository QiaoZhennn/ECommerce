package com.atguigu.controller;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU_MANAGER;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.service.AttrService;
import com.atguigu.service.ManagerSearchService;
import com.atguigu.util.JSONUtil;
import com.atguigu.util.JedisPoolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchRedisController {

    @Autowired
    private ManagerSearchService searchService;
    @Autowired
    private AttrService attrService;

    @RequestMapping("/goto_redis")
    public String goto_redis(){
        return "manager_redis";
    }

    @ResponseBody
    @RequestMapping("/get_attr_list")
    public Object get_attr_list(int class_2_id){
        List<OBJECT_T_MALL_ATTR> list_attr = attrService.select_attr_by_class_2(class_2_id);
        return list_attr;
    }

    @ResponseBody
    @RequestMapping("/refresh_sku_by_class_2")
    public long refresh_sku_by_class_2(int class_2_id){
        //查询二级分类对应的商品sku集合
        List<OBJECT_T_MALL_SKU_MANAGER> list_sku = searchService.search_sku_by_class_2(class_2_id);
        //获取redis客户端连接
        Jedis jedis=JedisPoolUtils.getJedis();
        //调用redis的api将sku商品集合放入redis
        String key="class_2_"+class_2_id;
        //向redis插入新的数据前，先删除旧数据，相当于更新操作
        jedis.del("class_2_"+class_2_id);
        for (int i = 0; i < list_sku.size(); i++) {
            jedis.zadd(key, i, JSONUtil.Object2Json(list_sku.get(i)));
        }
        return list_sku.size();
    }

/*    public String class_2_sku_search(int class_2_id, String class_2_name, ModelMap map){
        List<OBJECT_T_MALL_SKU> list_sku=new ArrayList<>();
        //查询redis缓存数据
        Jedis jedis= JedisPoolUtils.getJedis();
        Set<String> zrange=jedis.zrange("class_2_"+class_2_id,0,-1);
        if (zrange != null && zrange.size() > 0) {

            list_sku = MyRedisDataUtil.get_list_by_redis(zrange, OBJECT_T_MALL_SKU.class);
        } else {
            //如果redis中没有，则去mysql查找，并更新redis。
            list_sku=searchService.search_sku_by_class_2(class_2_id);
        }
        return "";
    }*/

    @ResponseBody
    @RequestMapping("/refresh_sku_by_attrs")
    public long refresh_sku_by_attrs(int class_2_id,@RequestParam("attr_array[]") int[] attr_array){
        //从前端传来的数组自动封装到入参，需要显示指明参数名称，带中括号。
        Jedis jedis=JedisPoolUtils.getJedis();
        long sum=0L;
        //循环分类属性id的数组
        for (int i = 0; i < attr_array.length; i++) {
            List<Integer> list_value_id= searchService.select_value_by_attr_id(attr_array[i]);
            //根据分类属性的id查询，分类属性值的集合
            for (int j = 0; j < list_value_id.size(); j++) {
                String key="av_"+class_2_id+"_"+attr_array[i]+"_"+list_value_id.get(j);
                //构造一个List，作为SearchService的入参，根据attr_id和attr_value查找sku
                List<T_MALL_SKU_ATTR_VALUE> list_av = new ArrayList<>();
                T_MALL_SKU_ATTR_VALUE t_mall_sku_attr_value=new T_MALL_SKU_ATTR_VALUE();
                t_mall_sku_attr_value.setShxm_id(attr_array[i]);
                t_mall_sku_attr_value.setShxzh_id(list_value_id.get(j));
                list_av.add(t_mall_sku_attr_value);
                List<OBJECT_T_MALL_SKU_MANAGER> list_sku = searchService.search_sku_by_attr(class_2_id,list_av,"");

                //循环sku集合，插入redis
                jedis.del(key);
                for (int k = 0; k < list_sku.size(); k++) {
                    jedis.zadd(key,k, JSONUtil.Object2Json(list_sku.get(k)));
                }
                sum+=list_sku.size();
            }
        }
        return sum;
    }
}
