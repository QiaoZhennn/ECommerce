package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(String url, String title, ModelMap map){
        map.put("init_url",url);
        map.put("init_title",title);
        return "manager_index";
    }
}
