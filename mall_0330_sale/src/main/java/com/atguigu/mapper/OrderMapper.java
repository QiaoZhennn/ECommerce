package com.atguigu.mapper;

import com.atguigu.bean.T_MALL_FLOW;
import com.atguigu.bean.T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ORDER_INFO;

public interface OrderMapper {
    void insert_order(T_MALL_ORDER order);
    void insert_flow(T_MALL_FLOW flow);
    void insert_order_info(T_MALL_ORDER_INFO info);

    void update_order(T_MALL_ORDER order);

    void update_flow(T_MALL_FLOW flow);
    void update_kc(T_MALL_ORDER_INFO info);

    int select_kc(T_MALL_ORDER_INFO info);
}
