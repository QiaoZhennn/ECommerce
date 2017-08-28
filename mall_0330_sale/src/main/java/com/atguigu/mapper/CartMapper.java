package com.atguigu.mapper;

import com.atguigu.bean.T_MALL_SHOPPINGCART;

import java.util.List;

public interface CartMapper {
    List<T_MALL_SHOPPINGCART> get_list_cart_by_user_id(int id);

    void add_cart(T_MALL_SHOPPINGCART cart);

    void update_cart(T_MALL_SHOPPINGCART cart);

    void delete_cart_by_id(int cart_id);

    void delete_cart_by_sku_id(int sku_id);
}
