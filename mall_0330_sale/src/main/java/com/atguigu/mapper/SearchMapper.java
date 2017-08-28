package com.atguigu.mapper;

import com.atguigu.bean.OBJECT_T_MALL_SKU;

import java.util.List;
import java.util.Map;

public interface SearchMapper {
    List<OBJECT_T_MALL_SKU> select_sku_by_class_2(int class_2_id);
    List<OBJECT_T_MALL_SKU> select_sku_by_attr(Map<String,Object> map);
}
