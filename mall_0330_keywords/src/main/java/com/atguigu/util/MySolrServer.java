package com.atguigu.util;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;

public class MySolrServer {
    public static HttpSolrServer getMySolr(String url){
        HttpSolrServer solr=new HttpSolrServer(url);
        solr.setParser(new XMLResponseParser());
        solr.setMaxRetries(1); //第一次找不到的话，再找几次
        solr.setConnectionTimeout(3000);
        return solr;
    }
}
