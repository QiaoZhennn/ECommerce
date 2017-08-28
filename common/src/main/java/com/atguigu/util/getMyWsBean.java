package com.atguigu.util;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class getMyWsBean {
    public static <T> T getMyWsBean(Class<T> t,String url){
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress(url);
        jaxWsProxyFactoryBean.setServiceClass(t);
        T create = (T) jaxWsProxyFactoryBean.create();
        return create;
    }
}
