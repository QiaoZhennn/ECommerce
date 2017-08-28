package com.atguigu.service;

import com.atguigu.bean.*;
import com.atguigu.mapper.CartMapper;
import com.atguigu.mapper.OrderMapper;
import com.atguigu.util.MyDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartMapper cartMapper;

    @Override
    public void insert_order(OBJECT_T_MALL_ORDER order, T_MALL_USER_ACCOUNT user, T_MALL_ADDRESS address) {
        //保存订单，生成订单id和订单号201708261158+orderId
        T_MALL_ORDER thisOrder=(T_MALL_ORDER) order;
        thisOrder.setDzh_id(address.getId());
        thisOrder.setDzh_mch(address.getYh_dz());
        thisOrder.setShhr(address.getShjr());
        thisOrder.setYh_id(user.getId());
        orderMapper.insert_order(thisOrder);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("ddHHmm");
        String dd_id_str=simpleDateFormat.format(new Date())+thisOrder.getId();
        int dd_id=Integer.parseInt(dd_id_str);

        //根据订单号，生成物流信息包裹，生成物流id，不生成物流单号。循环物流信息集合，单条插入
        List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
        for (OBJECT_T_MALL_FLOW flow : list_flow) {
            T_MALL_FLOW thisFlow=(T_MALL_FLOW)flow;
            thisFlow.setDd_id(dd_id);
            thisFlow.setMdd(address.getYh_dz());
            orderMapper.insert_flow(thisFlow);

            //根据物流id，批量插入订单信息表T_MALL_ORDER_INFO
            for (T_MALL_ORDER_INFO info : flow.getList_info()) {
                info.setDd_id(dd_id);
                info.setFlow_id(thisFlow.getId());
                orderMapper.insert_order_info(info);

                //删除已经提交订单的购物车信息。
                cartMapper.delete_cart_by_sku_id(info.getSku_id());
            }
        }
    }


    @Override
    public void order_pay(OBJECT_T_MALL_ORDER order) {
        //修改订单状态
        //预计两天之后送达
        order.setYjsdshj(MyDateUtil.getMyFlowDate(3));
        order.setJdh(2);
        orderMapper.update_order((T_MALL_ORDER)order);
        //修改物流信息
        List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
        for (OBJECT_T_MALL_FLOW flow : list_flow) {
            //真实情况下，从物流接口调用。
            flow.setPsmsh("硅谷快递");
            flow.setPsshj(MyDateUtil.getMyFlowDate(1));
            flow.setYwy("小扎");
            flow.setLxfsh("13122340000");
            orderMapper.update_flow(flow);

            for (T_MALL_ORDER_INFO info : flow.getList_info()) {
                //确认库存数量，需要加一个select的显示锁，行级锁。因为多线程访问时，如果没有锁，
                boolean b=if_can_buy(info);
                if(b){
                    //更新库存和销量信息。
                    orderMapper.update_kc(info);
                }
            }
        }
    }

    private boolean if_can_buy(T_MALL_ORDER_INFO info) {
        boolean b=false;
        int stock=orderMapper.select_kc(info);
        if(stock>=0){
            b=true;
        }
        return b;
    }


}
