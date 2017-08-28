package com.atguigu.service;

import com.alibaba.fastjson.JSON;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mapper.UserMapper;
import com.atguigu.util.JSONUtil;
import com.atguigu.util.MyDataSourceSwitch;
import com.atguigu.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user) {
        MyDataSourceSwitch.setKey("1"); //将key存在一个ThreadLocal中，避免多线程共用同一个key。
        return userMapper.login(user);
    }

    //RESTful的webservice配置
    @GET
    @Produces("application/xml")
    @Consumes("application/x-www-form-urlencoded") //万维网表单编码格式
    @Path("/loginTest")
    public T_MALL_USER_ACCOUNT loginTest(@BeanParam T_MALL_USER_ACCOUNT user) {
        MyDataSourceSwitch.setKey("1"); //将key存在一个ThreadLocal中，避免多线程共用同一个key。
        T_MALL_USER_ACCOUNT login = userMapper.login(user);
        return login;
    }

    @Override
    public void register(T_MALL_USER_ACCOUNT user) {
        userMapper.register(user);
    }

    @Override
    public String checkUserIfExist(String yh_mch) {
        return userMapper.checkUserIfExist(yh_mch);
    }
}
