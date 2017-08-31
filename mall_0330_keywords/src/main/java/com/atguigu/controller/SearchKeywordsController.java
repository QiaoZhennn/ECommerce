package com.atguigu.controller;

import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.util.MyProperty;
import com.atguigu.util.MySolrServer;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchKeywordsController {

    @ResponseBody
    @RequestMapping("/search_keywords/{keywords}")
    public Object search_keywords(@PathVariable("keywords") String keywords){
        List<OBJECT_T_MALL_SKU> list_sku=new ArrayList<>();
        HttpSolrServer mySolr= MySolrServer.getMySolr(MyProperty.getMyProperty("solrServer.properties","url"));

        SolrQuery solrQuery=new SolrQuery();

        solrQuery.setQuery("sku_mch:"+keywords); //对应solr主页中的 *:* 这样的搜索语句，冒号前为字段名，冒号后为字段值
        try {
            QueryResponse query=mySolr.query(solrQuery);
            list_sku=query.getBeans(OBJECT_T_MALL_SKU.class);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }

        return list_sku;
    }






















}
