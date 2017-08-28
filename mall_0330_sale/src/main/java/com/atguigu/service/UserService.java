package com.atguigu.service;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;

import javax.jws.WebService;

@WebService
public interface UserService {
    T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user);
    void register(T_MALL_USER_ACCOUNT user);
    String checkUserIfExist(String yh_mch);
}
