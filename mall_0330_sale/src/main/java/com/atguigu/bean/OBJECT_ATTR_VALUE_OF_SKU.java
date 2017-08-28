package com.atguigu.bean;

import java.util.List;

public class OBJECT_ATTR_VALUE_OF_SKU extends T_MALL_VALUE{
    private T_MALL_ATTR sku_attr;

    @Override
    public String toString() {
        return "OBJECT_ATTR_VALUE_OF_SKU{" +
                "sku_attr=" + sku_attr +
                '}';
    }


    public T_MALL_ATTR getSku_attr() {
        return sku_attr;
    }

    public void setSku_attr(T_MALL_ATTR sku_attr) {
        this.sku_attr = sku_attr;
    }

}
