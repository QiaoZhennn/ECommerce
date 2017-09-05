package com.atguigu.controller;

import com.atguigu.bean.T_MALL_SKU_SOLR;
import com.atguigu.util.MyProperty;
import com.atguigu.util.MySolrServer;
import com.atguigu.util.StringUtil;
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
    public List<T_MALL_SKU_SOLR> search_keywords(@PathVariable("keywords") String keywords){
        List<T_MALL_SKU_SOLR> list_sku=new ArrayList<>();
//        keywords= StringUtil.decode(keywords);
        HttpSolrServer mySolr= MySolrServer.getMySolr(MyProperty.getMyProperty("solrServer.properties","url"));
        SolrQuery solrQuery=new SolrQuery();
        String encode=keywords.replaceAll("_","%");
        keywords=StringUtil.decode(encode);
        solrQuery.setQuery("sku_mch:"+keywords); //对应solr主页中的 *:* 这样的搜索语句，冒号前为字段名，冒号后为字段值
        try {
            QueryResponse query=mySolr.query(solrQuery);
            list_sku=query.getBeans(T_MALL_SKU_SOLR.class);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return list_sku;
    }
}
