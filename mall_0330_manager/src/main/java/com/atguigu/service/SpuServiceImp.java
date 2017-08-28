package com.atguigu.service;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.mapper.SpuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpuServiceImp implements SpuService{

    @Autowired
    private SpuMapper spuMapper;

    public void save_spu(T_MALL_PRODUCT spu,List<String> listImage){
        spu.setShp_tp(listImage.get(0));
        spuMapper.insertSpu(spu);
        spuMapper.insertImages(spu.getId(),listImage);
    }
}
