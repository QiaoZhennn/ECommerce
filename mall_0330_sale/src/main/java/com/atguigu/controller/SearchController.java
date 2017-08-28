package com.atguigu.controller;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.service.SearchService;
import com.atguigu.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/class_2_sku_search")
    public String class_2_sku_search(int class_2_id,String class_2_name, ModelMap map){

        List<OBJECT_T_MALL_ATTR> list_attr = searchService.select_attr_by_class_2(class_2_id);
        map.put("list_attr",list_attr);
        List<OBJECT_T_MALL_SKU> list_sku=searchService.search_sku_by_class_2(class_2_id);
        map.put("list_sku",list_sku);
        map.put("class_2_name", StringUtil.decode(class_2_name));
        map.put("class_2_id", class_2_id);
        return "sale_search";
    }

    @RequestMapping("/get_search_result")
    public String get_search_result( ModelMap map,int class_2_id){
        List<OBJECT_T_MALL_SKU> list_sku=searchService.search_sku_by_class_2(class_2_id);
        map.put("list_sku",list_sku);
        return "sale_search_inner";
    }

    @RequestMapping("/get_sku_by_attr")
    public String get_sku_by_attr(int class_2_id, @RequestParam(value = "list_av[]",required = false) String[] list_av,String order, ModelMap map){
//        searchService.search_sku_by_attr(class_2_id,list_av);
        //必须指定接收的参数为数组"list_av[]"，参数才可以顺利传过来。
        List<T_MALL_SKU_ATTR_VALUE> arrayList=new ArrayList<>();
        if(list_av!=null&&list_av.length!=0) {
            for (int i = 0; i < list_av.length; i++) {
                T_MALL_SKU_ATTR_VALUE attr_value = new T_MALL_SKU_ATTR_VALUE();
                String[] args = list_av[i].split("_");
                attr_value.setShxm_id(Integer.parseInt(args[0]));
                attr_value.setShxzh_id(Integer.parseInt(args[1]));
                arrayList.add(attr_value);
            }
        }
        List<OBJECT_T_MALL_SKU> list_sku = searchService.search_sku_by_attr(class_2_id, arrayList,order);
        map.put("list_sku",list_sku);
        return "sale_search_inner";
    }


}
