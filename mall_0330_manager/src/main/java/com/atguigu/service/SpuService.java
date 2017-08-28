package com.atguigu.service;

import com.atguigu.bean.T_MALL_PRODUCT;

import java.util.List;

public interface SpuService {
    void save_spu(T_MALL_PRODUCT spu, List<String> listImage);
}
