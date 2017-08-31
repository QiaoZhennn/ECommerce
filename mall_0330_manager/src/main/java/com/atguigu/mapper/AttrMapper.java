package com.atguigu.mapper;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_CLASS_1;

import java.util.HashMap;
import java.util.List;

public interface AttrMapper {
    List<T_MALL_CLASS_1> select_class_1();

    List<OBJECT_T_MALL_ATTR> select_attr_by_class_2(int class_2_id);

    void insert_attr(HashMap<String,Object> map);
    void insert_values(HashMap<String,Object> map);

    List<Integer> select_value_by_attr_id(int attr_id);
}
