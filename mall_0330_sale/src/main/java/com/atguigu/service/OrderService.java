package com.atguigu.service;

import com.atguigu.bean.*;

public interface OrderService {

    void insert_order(OBJECT_T_MALL_ORDER order, T_MALL_USER_ACCOUNT user, T_MALL_ADDRESS address);

    void order_pay(OBJECT_T_MALL_ORDER order);
}
