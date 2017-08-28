package com.atguigu.util;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor; //注意不要导错包！
import org.apache.ws.security.handler.WSHandlerConstants;
import org.springframework.beans.factory.FactoryBean;

import java.util.HashMap;

/**
 * 借助Spring容器的FactoryBean自动在池中创建JaxWsProxyFactoryBean的实现类，同时使用了wss4j的安全框架。
 * @param <T>
 */
public class MyInterfaceFactoryBean<T> implements FactoryBean<T> {
    private Class<T> wsInterface;
    private String url;
    public static <T> T getMyWsBean(Class<T> t,String url){
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress(url);
        jaxWsProxyFactoryBean.setServiceClass(t);

        //设置只对UserService的请求进行加密。
        if(t.getSimpleName().equals("UserService")){
            //客户端的安全性配置，设置初始参数。等同于服务器端用Spring在applicationContext.xml中的配置。
            HashMap<String,Object> map=new HashMap<>();
            map.put(WSHandlerConstants.ACTION,WSHandlerConstants.USERNAME_TOKEN);
            map.put(WSHandlerConstants.USER,"user"); //客户端必须配一个user，服务器端可以省略User的配置。
            map.put(WSHandlerConstants.PASSWORD_TYPE,"PasswordText");
            map.put(WSHandlerConstants.PW_CALLBACK_CLASS,MyPasswordCallbackClient.class.getName());
            WSS4JOutInterceptor wss4JOutInterceptor=new WSS4JOutInterceptor(map);
            jaxWsProxyFactoryBean.getOutInterceptors().add(wss4JOutInterceptor);
        }

        T create = (T) jaxWsProxyFactoryBean.create();
        return create;
    }
    @Override
    public T getObject() throws Exception {
        return getMyWsBean(wsInterface,url);
    }
    @Override
    public Class<?> getObjectType() {
        return this.wsInterface;
    }
    @Override
    public boolean isSingleton() {
        return false;
    }
    public Class<T> getWsInterface() {
        return wsInterface;
    }
    public void setWsInterface(Class<T> wsInterface) {
        this.wsInterface = wsInterface;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
