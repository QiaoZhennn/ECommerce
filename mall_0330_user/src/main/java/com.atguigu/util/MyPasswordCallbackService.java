package com.atguigu.util;

import org.apache.wss4j.common.ext.WSPasswordCallback; //注意不要导错包，不要导入ws.security包

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

/**
 * 服务器端的密码回调函数
 */
public class MyPasswordCallbackService implements CallbackHandler{
    @Override
    //把客户端的PasswordCallback当作入参传入，这个类封装了用户名和密码。
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        //让程序员参与到用户名与密码的“获取”过程，校验过程由框架完成。我们可以在获取过程中，对密码进行二次加密。
        WSPasswordCallback wsc = (WSPasswordCallback) callbacks[0];
        //从客户端的请求中，拿到标识符--identifier
        String identifier = wsc.getIdentifier();
        //此处，原则上要查数据库，根据标识符identifier获取数据库的密钥passwordDB。
        // 然后将从客户端MyPasswordCallbackClient这个回调函数中获取的identifier对应的password与
        // wsc.setPassword(passwordDB)的入参进行对比，相同，则有登陆权限。
        //此处为了简略，从配置文件读取identifier对应的值，作为密钥的一部分。
        String pwd = MyProperty.getMyProperty("wsPassword.properties",identifier);
        String md5pwd=MD5Util.md5(pwd+MyDateUtil.getPasswordDate());
        wsc.setPassword(md5pwd);
    }
}
