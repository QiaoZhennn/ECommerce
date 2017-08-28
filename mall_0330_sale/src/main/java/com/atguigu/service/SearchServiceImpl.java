package com.atguigu.service;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mapper.AttrMapper;
import com.atguigu.mapper.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchMapper searchMapper;
    @Autowired
    private AttrMapper attrMapper;

    public List<OBJECT_T_MALL_ATTR> select_attr_by_class_2(int class_2_id){
        return attrMapper.select_attr_by_class_2(class_2_id);
    }

    public List<OBJECT_T_MALL_SKU> search_sku_by_class_2(int class_2_id){
        return searchMapper.select_sku_by_class_2(class_2_id);
    }

    @Override
    public List<OBJECT_T_MALL_SKU> search_sku_by_attr(int class_2_id, List<T_MALL_SKU_ATTR_VALUE> list_av,String order) {

        StringBuffer sql=new StringBuffer("");
        if(list_av!=null && list_av.size()>0){
            //分类属性查询的动态拼接语句
            //SQL语句拼接时，前面后一定加空格。
            sql.append(" AND sku.Id IN");
            sql.append(" (SELECT sku0.sku_id FROM ");
            for (int i = 0; i < list_av.size(); i++) {
                sql.append("(SELECT sku_id FROM t_mall_sku_attr_value WHERE shxm_id="+list_av.get(i).getShxm_id()+
                        " AND shxzh_id="+list_av.get(i).getShxzh_id()+") sku"+i+" ");
                if(i<list_av.size()-1){
                    sql.append(" , ");
                }
            }
            if(list_av.size()>1){
                sql.append(" where ");
                for (int i = 0; i < list_av.size()-1; i++) {
                    sql.append("sku"+i+".sku_id=sku"+(i+1)+".sku_id");
                    if (list_av.size()>2&&i<list_av.size()-2){
                        sql.append(" AND ");
                    }
                }
            }
            sql.append(" ) ");
        }
        sql.append(order);
        Map<String,Object> map=new HashMap<>();
        map.put("class_2_id",class_2_id);
        map.put("sql",sql.toString());
        List<OBJECT_T_MALL_SKU> skus = searchMapper.select_sku_by_attr(map);
        return skus;
    }
}
