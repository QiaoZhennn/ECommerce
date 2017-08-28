package com.atguigu.service;

import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.OBJECT_T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.mapper.SkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public List<T_MALL_PRODUCT> get_spu_by_tm(int class_1_id, int class_2_id, int pp_id) {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("class_1_id",class_1_id);
        paramMap.put("class_2_id",class_2_id);
        paramMap.put("pp_id",pp_id);
        return skuMapper.select_spu_by_tm(paramMap);
    }

    @Override
    public void save_sku(OBJECT_T_MALL_PRODUCT spu, MODEL_T_MALL_SKU_ATTR_VALUE list_av, T_MALL_SKU sku) {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("sku",sku);
        paramMap.put("spu",spu);
        skuMapper.insert_sku(paramMap);
        Map<String,Object> paramMap2=new HashMap<>();
        paramMap2.put("list_av",list_av.getList_av());
        paramMap2.put("sku_id",sku.getId());
        paramMap2.put("spu_id",spu.getSpu_id());
        skuMapper.insert_sku_av(paramMap2);
    }
}
