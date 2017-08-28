package com.atguigu.test;

import com.alibaba.fastjson.JSON;
import com.atguigu.bean.T_MALL_SHOPPINGCART;
import com.atguigu.util.JSONUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FastJsonTest {

    @Test
    public void testJosn(){
        List<T_MALL_SHOPPINGCART> cart0=new ArrayList<>();
        cart0.add(new T_MALL_SHOPPINGCART());
        cart0.get(0).setSku_mch("小米显示器");
        String json0=JSON.toJSONString(cart0);
        String json="[{\"hj\":8888.0,\"id\":0,\"kcdz\":\"北京\",\"shfxz\":\"1\",\"shp_id\":6,\"shp_tp\":\"screen/screen (1).jpg\",\"sku_id\":3,\"sku_jg\":8888.0,\"sku_mch\":\"显示器\",\"tjshl\":1,\"yh_id\":0}]";
        List<T_MALL_SHOPPINGCART> carts = JSON.parseArray(json0, T_MALL_SHOPPINGCART.class);
        System.out.println(carts+"+++++++");
        System.out.println(JSONUtil.Json2List(json,T_MALL_SHOPPINGCART.class).get(0).getSku_mch());
    }
}
