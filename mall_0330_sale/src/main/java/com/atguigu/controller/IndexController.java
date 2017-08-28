package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String toIndex(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
//        String yh_mch=cookies;
        return "sale_index";
    }
}
