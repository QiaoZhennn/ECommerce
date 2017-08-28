package com.atguigu.util;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

/**
 * 客户端的密码回调函数
 */
public class MyPasswordCallbackClient implements CallbackHandler{
    @Override
    //把客户端的PasswordCallback当作入参传入，这个类封装了用户名和密码。
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        //让程序员参与到标识符与密钥的“获取”过程，校验过程由框架完成。我们可以在获取过程中，对密码进行二次加密。
        WSPasswordCallback wsc = (WSPasswordCallback) callbacks[0];
        wsc.setIdentifier("cxf");
        //密钥信息的二次加密：通过一个常量字符串拼接一个时间戳编制密码
        String md5pwd=MD5Util.md5("wss4j"+MyDateUtil.getPasswordDate());
        //设置的密钥在网络传输中是加密的。
        wsc.setPassword(md5pwd);
    }
}
