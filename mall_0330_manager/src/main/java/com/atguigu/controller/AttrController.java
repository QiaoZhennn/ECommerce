package com.atguigu.controller;

import com.atguigu.bean.MODEL_OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_CLASS_1;
import com.atguigu.service.AttrService;
import com.atguigu.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class AttrController {

    @Autowired
    private AttrService attrService;

    @RequestMapping("/goto_attr")
    public String goto_attr(){
        return "manager_attr";
    }

    @RequestMapping("/get_attr_by_class_2")
    public String get_attr_by_class_2(int class_2_id,String class_2_name ,ModelMap map){
        List<OBJECT_T_MALL_ATTR> list_attr = attrService.select_attr_by_class_2(class_2_id);
        map.put("list_attr",list_attr);
        map.put("class_2_id",class_2_id);
        map.put("class_2_name",class_2_name);
        return "manager_attr_list_inner";
    }

    @ResponseBody
    @RequestMapping("/get_attr_by_class_2_json")
    public Object get_attr_by_class_2_json(int class_2_id){
        List<OBJECT_T_MALL_ATTR> list_attr = attrService.select_attr_by_class_2(class_2_id);
        return list_attr;
    }

    @RequestMapping("/goto_add_attr")
    public String goto_add_attr(int class_2_id, String class_2_name ,ModelMap map){

        map.put("class_2_id",class_2_id);
        map.put("class_2_name", StringUtil.decode(class_2_name));
        return "manager_attr_add";
    }

    @RequestMapping("/save_attr")
    public ModelAndView save_attr(MODEL_OBJECT_T_MALL_ATTR list_attr, int class_2_id){
        attrService.save_attr(list_attr.getList_attr(),class_2_id);
        ModelAndView modelAndView=new ModelAndView("redirect:/index.htm");
        //ModelAndView以UTF-8的格式将参数转换为查询字符串附在重定向地址后。
        modelAndView.addObject("url","goto_attr.htm");
        String encodeStr= null;
        try {
            encodeStr = URLEncoder.encode("分类属性","UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("title",encodeStr);
        return modelAndView;
    }
}
