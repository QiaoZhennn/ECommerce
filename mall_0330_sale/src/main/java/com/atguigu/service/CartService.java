package com.atguigu.service;

import com.atguigu.bean.T_MALL_SHOPPINGCART;

import java.util.List;

public interface CartService {
    List<T_MALL_SHOPPINGCART> get_list_cart_by_user_id(int id);

    void add_cart(T_MALL_SHOPPINGCART t_mall_shoppingcart);

    void update_cart(T_MALL_SHOPPINGCART t_mall_shoppingcart);

    void delete_cart_by_id(int cart_id);
}
