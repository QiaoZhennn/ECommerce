package com.atguigu.controller;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.service.SpuService;
import com.atguigu.util.MyUploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


@Controller
public class SpuController {

    @Autowired
    private SpuService spuService;

    @RequestMapping("/goto_spu")
    public String goto_spu(){
        return "manager_spu";
    }

    @RequestMapping("/save_spu")
    public ModelAndView save_spu(T_MALL_PRODUCT spu,@RequestParam("files") MultipartFile[] files){
        List<String> listImages = MyUploadFileUtil.uploadImage(files);
        spuService.save_spu(spu,listImages);
        System.out.println("-----------------SprCtrl, spu_id: "+spu.getId());
        ModelAndView modelAndView=new ModelAndView("redirect:/index.htm");
        //ModelAndView以UTF-8的格式将参数转换为查询字符串附在重定向地址后。
        modelAndView.addObject("url","goto_spu.htm");
        String encodeStr= null;
        try {
            encodeStr = URLEncoder.encode("spu信息","UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("title",encodeStr);
        return modelAndView;
    }
}
