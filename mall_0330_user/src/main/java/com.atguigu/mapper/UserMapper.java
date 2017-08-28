package com.atguigu.mapper;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;

public interface UserMapper {
    T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user);

    void register(T_MALL_USER_ACCOUNT user);

    String checkUserIfExist(String yh_mch);
}
