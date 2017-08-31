package com.atguigu.controller;

import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.util.*;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(HttpServletRequest request, ModelMap map){
        HttpSolrServer solr = MySolrServer.getMySolr(MyProperty.getMyProperty("solrServer.properties", "url"));

        //往solr中放置数据
        List<OBJECT_T_MALL_SKU> list_sku=new ArrayList<>();
        //查询redis缓存数据
        Jedis jedis= JedisPoolUtils.getJedis();
        String key="class_2_"+28;
        Set<String> zrange=jedis.zrange(key,0,-1);
            list_sku = MyRedisDataUtil.get_list_by_redis(zrange, OBJECT_T_MALL_SKU.class);
        try {
            solr.deleteByQuery("*:*"); //solr中的全部删除，冒号前为字段名，冒号后为字段值。
            solr.addBeans(list_sku); //从redis中查询数据，放到solr中。
            solr.commit();

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "test";
    }
}
