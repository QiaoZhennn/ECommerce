package com.atguigu.service;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_CLASS_1;
import com.atguigu.mapper.AttrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    private AttrMapper attrMapper;

    @Override
    public List<T_MALL_CLASS_1> select_class_1() {
        return attrMapper.select_class_1();
    }

    @Override
    public void save_attr(List<OBJECT_T_MALL_ATTR> list_attr, int class_2_id) {

        for (int i = 0; i < list_attr.size(); i++) {
            HashMap<String,Object> map=new HashMap<>();
            map.put("class_2_id",class_2_id);
            map.put("attr_obj",list_attr.get(i));
            attrMapper.insert_attr(map);

            HashMap<String,Object> valMap=new HashMap<>();
            valMap.put("list_value",list_attr.get(i).getList_value());
            valMap.put("attr_id",list_attr.get(i).getId());
            attrMapper.insert_values(valMap);
        }
    }

    @Override
    public List<OBJECT_T_MALL_ATTR> select_attr_by_class_2(int class_2_id) {
        return attrMapper.select_attr_by_class_2(class_2_id);
    }
}
