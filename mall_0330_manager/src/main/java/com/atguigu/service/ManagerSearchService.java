package com.atguigu.service;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU_MANAGER;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;

import java.util.List;

public interface ManagerSearchService {
    List<OBJECT_T_MALL_ATTR> select_attr_by_class_2(int class_2_id);
    List<OBJECT_T_MALL_SKU_MANAGER> search_sku_by_class_2(int class_2_id);
    List<OBJECT_T_MALL_SKU_MANAGER> search_sku_by_attr(int class_2_id, List<T_MALL_SKU_ATTR_VALUE> list_av, String order);

    List<Integer> select_value_by_attr_id(int attr_id);
}
