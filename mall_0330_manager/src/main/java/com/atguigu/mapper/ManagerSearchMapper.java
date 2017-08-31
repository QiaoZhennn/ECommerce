package com.atguigu.mapper;

import com.atguigu.bean.OBJECT_T_MALL_SKU_MANAGER;

import java.util.List;
import java.util.Map;

public interface ManagerSearchMapper {
    List<OBJECT_T_MALL_SKU_MANAGER> select_sku_by_class_2(int class_2_id);
    List<OBJECT_T_MALL_SKU_MANAGER> select_sku_by_attr(Map<String, Object> map);
}
