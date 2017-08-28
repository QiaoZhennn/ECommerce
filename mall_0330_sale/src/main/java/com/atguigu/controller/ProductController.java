package com.atguigu.controller;

import com.atguigu.bean.DETAIL_T_MALL_PRODUCT;
import com.atguigu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/sale_product_detail")
    public String sale_product_detail(int sku_id, ModelMap map){
        DETAIL_T_MALL_PRODUCT sku = productService.select_detail_product_by_sku_id(sku_id);
        int spu_id=productService.select_spu_id_by_sku_id(sku_id);
        List<DETAIL_T_MALL_PRODUCT> list_spu = productService.get_list_sku_by_spu_id(spu_id);
        map.put("list_sku",list_spu);
        map.put("sku",sku);
        return "sale_product_detail";
    }

    @RequestMapping("/get_list_sku_by_spu_id")
    public String get_list_sku_by_spu_id(int spu_id,int sku_id,ModelMap map){
        List<DETAIL_T_MALL_PRODUCT> list_spu = productService.get_list_sku_by_spu_id(spu_id);
        map.put("list_sku",list_spu);
        return "";
    }


}
