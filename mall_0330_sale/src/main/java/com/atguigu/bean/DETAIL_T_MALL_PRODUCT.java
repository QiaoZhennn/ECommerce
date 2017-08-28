package com.atguigu.bean;

import java.util.List;

public class DETAIL_T_MALL_PRODUCT extends T_MALL_SKU{
    private T_MALL_TRADE_MARK tm;
    private T_MALL_PRODUCT spu;
    private List<T_MALL_PRODUCT_IMAGE> list_spu_img;
    private List<OBJECT_ATTR_VALUE_OF_SKU> list_attr_value;


    public List<T_MALL_PRODUCT_IMAGE> getList_spu_img() {
        return list_spu_img;
    }

    public void setList_spu_img(List<T_MALL_PRODUCT_IMAGE> list_spu_img) {
        this.list_spu_img = list_spu_img;
    }

    public List<OBJECT_ATTR_VALUE_OF_SKU> getList_attr_value() {
        return list_attr_value;
    }

    public void setList_attr_value(List<OBJECT_ATTR_VALUE_OF_SKU> list_attr_value) {
        this.list_attr_value = list_attr_value;
    }

    public T_MALL_TRADE_MARK getTm() {
        return tm;
    }

    public void setTm(T_MALL_TRADE_MARK tm) {
        this.tm = tm;
    }

    public T_MALL_PRODUCT getSpu() {
        return spu;
    }

    public void setSpu(T_MALL_PRODUCT spu) {
        this.spu = spu;
    }


}
