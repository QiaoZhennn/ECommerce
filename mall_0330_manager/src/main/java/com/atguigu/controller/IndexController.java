package com.atguigu.controller;

import com.atguigu.bean.T_MALL_CLASS_1;
import com.atguigu.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    TestMapper testMapper;

    @RequestMapping("/index")
    public String index(String url, String title, ModelMap map){
        map.put("init_url",url);
        map.put("init_title",title);
        return "manager_index";
    }
}
