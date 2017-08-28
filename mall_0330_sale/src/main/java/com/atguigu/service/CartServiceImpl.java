package com.atguigu.service;

import com.atguigu.bean.T_MALL_SHOPPINGCART;
import com.atguigu.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<T_MALL_SHOPPINGCART> get_list_cart_by_user_id(int id) {
        return cartMapper.get_list_cart_by_user_id(id);
    }

    @Override
    public void add_cart(T_MALL_SHOPPINGCART t_mall_shoppingcart) {
        cartMapper.add_cart(t_mall_shoppingcart);
    }

    @Override
    public void update_cart(T_MALL_SHOPPINGCART t_mall_shoppingcart) {
        cartMapper.update_cart(t_mall_shoppingcart);
    }

    @Override
    public void delete_cart_by_id(int cart_id) {
        cartMapper.delete_cart_by_id(cart_id);
    }
}
