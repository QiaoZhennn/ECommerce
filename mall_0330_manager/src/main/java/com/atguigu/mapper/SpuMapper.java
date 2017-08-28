package com.atguigu.mapper;

import com.atguigu.bean.T_MALL_PRODUCT;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpuMapper {

    void insertSpu(T_MALL_PRODUCT spu);

    void insertImages(@Param("id") int id,@Param("listImage") List<String> listImage);
}
