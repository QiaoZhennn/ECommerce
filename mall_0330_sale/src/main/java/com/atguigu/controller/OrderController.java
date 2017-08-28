package com.atguigu.controller;


import com.atguigu.bean.*;
import com.atguigu.service.AddressService;
import com.atguigu.service.CartService;
import com.atguigu.service.OrderService;
import com.atguigu.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@Controller
//Map中放了key="order"的对象都会被放到session中
@SessionAttributes("order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private AddressService addressService;


    @RequestMapping("/order_pay")
    public String order_pay(@ModelAttribute("order") OBJECT_T_MALL_ORDER order){
        //跳转到支付服务
        orderService.order_pay(order);
        return "redirect:/sale_pay_success.do";
    }

    @RequestMapping("/sale_pay_success")
    public String sale_pay_success(){
        return "sale_pay_success";
    }

    @RequestMapping("/save_order")
    //使用ModelAttribute可以获取Session域中的值
    public ModelAndView save_order(@ModelAttribute("order") OBJECT_T_MALL_ORDER order, int address_id, HttpSession session, ModelMap map) {
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user_login");

        T_MALL_ADDRESS address = addressService.get_addresses_by_id(address_id);
        //调用提交订单的系统。
        orderService.insert_order(order, user, address);

        //已删除数据库中的购物车数据，现在重新指定session中的购物车数据
//        List<T_MALL_SHOPPINGCART> list_cart_session= (List<T_MALL_SHOPPINGCART>) session.getAttribute("list_cart_session");
//        list_cart_session=cartService.get_list_cart_by_user_id(user.getId());
        session.setAttribute("list_cart_session", cartService.get_list_cart_by_user_id(user.getId()));


        ModelAndView modelAndView = new ModelAndView("redirect:/sale_pay.do");
        modelAndView.addObject("zje", order.getZje());
        modelAndView.addObject("shhr", StringUtil.encode(order.getShhr()));
        modelAndView.addObject("dzh_mch", StringUtil.encode(order.getDzh_mch()));

        return modelAndView;
    }

    @RequestMapping("/sale_pay")
    public String sale_pay() {
        return "sale_pay";
    }


    @RequestMapping("/goto_order")
    public String goto_order(HttpSession session, ModelMap map) {
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user_login");
        List<T_MALL_SHOPPINGCART> list_cart = new ArrayList<>();
        list_cart = (List<T_MALL_SHOPPINGCART>) session.getAttribute("list_cart_session");

        //根据库存地址拆单：相同库存地址的产品，放在同一个物流包裹里。
        //先获得购物车中有多少个商品的库存地址
        Set<String> set_kcdz = new HashSet<>();//依靠set来去重，因为商品的库存地址可能会重复
        for (int i = 0; i < list_cart.size(); i++) {
            if("1".equals(list_cart.get(i).getShfxz())){
                //从选中的购物车项中获取他们的库存地址
                String kcdz = list_cart.get(i).getKcdz();
                set_kcdz.add(kcdz);
            }
        }

        //一个订单T_MALL_ORDER对应多个物流T_MALL_FLOW，t_mall_order含有一个list_flow
        OBJECT_T_MALL_ORDER t_mall_order = new OBJECT_T_MALL_ORDER();
        List<OBJECT_T_MALL_FLOW> list_flow = new ArrayList<>();

        BigDecimal zje = new BigDecimal(0); //总金额


        //循环库存地址，按照库存地址生成物流单号
        Iterator<String> iterator = set_kcdz.iterator();
        while (iterator.hasNext()) { //遍历库存地址
            String kcdz = iterator.next();
            OBJECT_T_MALL_FLOW t_mall_flow = new OBJECT_T_MALL_FLOW(); //对于一个库存地址，新建一个物流。
            List<T_MALL_ORDER_INFO> list_order_info = t_mall_flow.getList_info();
            for (int i = 0; i < list_cart.size(); i++) { //遍历购物车项目
                if (list_cart.get(i).getKcdz().equals(kcdz) && list_cart.get(i).getShfxz().equals("1")) {

                    zje = zje.add(new BigDecimal(list_cart.get(i).getHj() + "")); //计算总金额。

                    T_MALL_ORDER_INFO t_mall_order_info = new T_MALL_ORDER_INFO();
                    t_mall_order_info.setGwch_id(list_cart.get(i).getId());
                    t_mall_order_info.setShp_tp(list_cart.get(i).getShp_tp());
                    t_mall_order_info.setSku_id(list_cart.get(i).getSku_id());
                    t_mall_order_info.setSku_jg(list_cart.get(i).getSku_jg());
                    t_mall_order_info.setSku_kcdz(list_cart.get(i).getKcdz());
                    t_mall_order_info.setSku_mch(list_cart.get(i).getSku_mch());
                    t_mall_order_info.setSku_shl(list_cart.get(i).getTjshl());
                    list_order_info.add(t_mall_order_info);
                }
            }
            t_mall_flow.setList_info(list_order_info);
            t_mall_flow.setPsfsh("EMS");
            t_mall_flow.setYh_id(user.getId());
            t_mall_flow.setMqdd(kcdz + ": 商品未出库");
            list_flow.add(t_mall_flow);
        }

        t_mall_order.setJdh(1);
        t_mall_order.setYh_id(user.getId());
        t_mall_order.setZje(zje);
        t_mall_order.setList_flow(list_flow);

        map.put("order", t_mall_order);

        List<T_MALL_ADDRESS> list_address = addressService.get_addresses_by_user_id(user);
        map.put("list_address", list_address);

        return "sale_order";
    }
}
