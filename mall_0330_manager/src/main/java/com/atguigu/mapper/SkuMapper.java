package com.atguigu.mapper;

import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.T_MALL_PRODUCT;

import java.util.List;
import java.util.Map;

public interface SkuMapper {
    List<T_MALL_PRODUCT> select_spu_by_tm(Map<String,Object> paramMap);

    void insert_sku(Map<String, Object> paramMap);

    void insert_sku_av(Map<String, Object> paramMap2);
}
