package com.atguigu.bean;

import java.util.List;

public class OBJECT_T_MALL_SKU extends T_MALL_SKU {
    private T_MALL_PRODUCT spu;
    private T_MALL_TRADE_MARK tm;
    private String shp_tp;

    public String getShp_tp() {
        return shp_tp;
    }

    public void setShp_tp(String shp_tp) {
        this.shp_tp = shp_tp;
    }

    public T_MALL_PRODUCT getSpu() {
        return spu;
    }

    public void setSpu(T_MALL_PRODUCT spu) {
        this.spu = spu;
    }

    public T_MALL_TRADE_MARK getTm() {
        return tm;
    }

    public void setTm(T_MALL_TRADE_MARK tm) {
        this.tm = tm;
    }

}
