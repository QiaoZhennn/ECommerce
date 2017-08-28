package com.atguigu.controller;

import com.atguigu.bean.*;
import com.atguigu.service.AttrService;
import com.atguigu.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class SkuController {

    @Autowired
    private SkuService skuService;
    @Autowired
    private AttrService attrService;

    @RequestMapping("/goto_sku")
    public String goto_sku(){
        return "manager_sku";
    }

    @RequestMapping("/get_sku_attr_list_by_class_2")
    public String get_sku_attr_list_by_class_2(int class_2_id, ModelMap map){
        List<OBJECT_T_MALL_ATTR> list_attr = attrService.select_attr_by_class_2(class_2_id);
        map.put("list_attr",list_attr);
        return "manager_sku_attr_inner";
    }

    @ResponseBody
    @RequestMapping("/get_spu_by_tm")
    public List<T_MALL_PRODUCT> get_spu_by_tm(int class_1_id,int class_2_id,int pp_id){
        List<T_MALL_PRODUCT> list_spu=skuService.get_spu_by_tm(class_1_id,class_2_id,pp_id);
        return list_spu;
    }

    @RequestMapping("/save_sku")
    public ModelAndView save_sku(OBJECT_T_MALL_PRODUCT spu, MODEL_T_MALL_SKU_ATTR_VALUE list_av,T_MALL_SKU sku){
        skuService.save_sku(spu,list_av,sku);
        //提交成功，重定向到index页面，附件一定的标签信息。
        ModelAndView modelAndView=new ModelAndView("redirect:/index.htm");
        //ModelAndView以UTF-8的格式将参数转换为查询字符串附在重定向地址后。
        modelAndView.addObject("url","goto_sku.htm");
        String encodeStr= null;
        try {
            encodeStr = URLEncoder.encode("库存信息","UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("title",encodeStr);
        return modelAndView;
    }
}
