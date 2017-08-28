package com.atguigu.mapper;

import com.atguigu.bean.DETAIL_T_MALL_PRODUCT;
import com.atguigu.bean.OBJECT_T_MALL_SKU;

import java.util.List;

public interface ProductMapper {

//    OBJECT_T_MALL_SKU select_sku_by_sku_id(int sku_id);
    DETAIL_T_MALL_PRODUCT select_detail_product_by_sku_id(int sku_id);

    List<DETAIL_T_MALL_PRODUCT> get_list_sku_by_spu_id(int spu_id);

    int select_spu_id_by_sku_id(int sku_id);
}
