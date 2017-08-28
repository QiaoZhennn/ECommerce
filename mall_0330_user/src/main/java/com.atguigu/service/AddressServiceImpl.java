package com.atguigu.service;

import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public void add_address(T_MALL_ADDRESS address) {
        addressMapper.insert_address(address);
    }

    @Override
    public List<T_MALL_ADDRESS> get_addresses_by_user_id(T_MALL_USER_ACCOUNT user) {
        return addressMapper.get_addresses_by_user_id(user);
    }

    @Override
    public T_MALL_ADDRESS get_addresses_by_id(int address_id) {
        return addressMapper.get_addresses_by_id(address_id);
    }
}
