package com.atguigu.service;

import com.atguigu.bean.DETAIL_T_MALL_PRODUCT;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

//    @Override
//    public OBJECT_T_MALL_SKU select_sku_by_sku_id(int sku_id) {
//        return productMapper.select_sku_by_sku_id(sku_id);
//    }

    @Override
    public DETAIL_T_MALL_PRODUCT select_detail_product_by_sku_id(int sku_id) {
        return productMapper.select_detail_product_by_sku_id(sku_id);
    }

    @Override
    public List<DETAIL_T_MALL_PRODUCT> get_list_sku_by_spu_id(int spu_id) {
        return productMapper.get_list_sku_by_spu_id(spu_id);
    }

    @Override
    public int select_spu_id_by_sku_id(int sku_id) {
        return productMapper.select_spu_id_by_sku_id(sku_id);
    }
}
