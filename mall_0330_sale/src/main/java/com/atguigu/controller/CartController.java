package com.atguigu.controller;

import com.atguigu.bean.DETAIL_T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SHOPPINGCART;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.CartService;
import com.atguigu.service.ProductService;
import com.atguigu.util.JSONUtil;
import com.atguigu.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    /**
     * 1、用户未登陆，cookie中没数据，新增cookie
     * 2、用户未登陆，有数据，不重复，新增cookie中的购物车
     * 3、用户未登陆，有数据，重复，更新cookie中的购物车
     * 4、用户已登陆，db无数据，db新增数据
     * 5、用户已登陆，db有数据，重复，db更新购买数量数据
     * 6、用户已登陆，db有数据，不重复，db新增数据
     */
    @ResponseBody
    @RequestMapping("/add_cart")
    public String add_cart(HttpServletRequest request, HttpServletResponse response,
                                 HttpSession session, ModelMap map, T_MALL_SHOPPINGCART cart,
                                 @CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie_param) {
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user_login");
        List<T_MALL_SHOPPINGCART> list_cart = new ArrayList<>();
        if (user == null) {
            //用户未登陆
            if (StringUtil.isEmpty(list_cart_cookie_param)) {
                //cookie中无数据
                list_cart.add(cart);
            } else {
                //cookie中有购物项数据
                list_cart = JSONUtil.Json2List(list_cart_cookie_param, T_MALL_SHOPPINGCART.class);
                boolean flag = is_new_cart(list_cart, cart);
                if (flag) {
                    //需要添加新数据
                    list_cart.add(cart);
                } else {

                    //旧的购物车已经含有数据
                    for (int i = 0; i < list_cart.size(); i++) {
                        if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
                            //更新数量和合计
                            list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + cart.getTjshl());
                            list_cart.get(i).setHj(list_cart.get(i).getHj() + cart.getHj());
                        }
                    }
                }
            }
            String json = JSONUtil.List2Json(list_cart);
            Cookie cookie = new Cookie("list_cart_cookie", json);
            cookie.setMaxAge(60 * 60); //设置cookie一小时过期
            response.addCookie(cookie);
        } else {
            //用户已登陆，操作database
            list_cart = (List<T_MALL_SHOPPINGCART>) session.getAttribute("list_cart_session");
            cart.setYh_id(user.getId());
            if (list_cart == null || list_cart.size() == 0) {
                //session中没有数据
                list_cart = new ArrayList<>();
                list_cart.add(cart);
                cartService.add_cart(cart);
                session.setAttribute("list_cart_session", list_cart);
            } else {
                //session中有数据
                if (is_new_cart(list_cart, cart)) {
                    //该cart是一个新的cart项目。
                    //因为这个list_cart从session中获取，取得的是服务器的session的内存地址，此处操作list_cart，session中的对象也生效。
                    list_cart.add(cart);
                    cartService.add_cart(cart);
                } else {
                    //该cart项目已经存在于session中，只需要更新总价格和总数即可。
                    for (int i = 0; i < list_cart.size(); i++) {
                        if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
                            list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + cart.getTjshl());
                            list_cart.get(i).setHj(list_cart.get(i).getHj() + cart.getHj());
                            cartService.update_cart(list_cart.get(i));
                        }
                    }
                }
            }
        }
        /*String from=request.getHeader("Referer");
        ModelAndView modelAndView=new ModelAndView("forward:/"+from);

        modelAndView.addObject("success","添加成功");*/
        String s = StringUtil.encode("该商品已成功添加到购物车");
        return s;
    }

    private boolean is_new_cart(List<T_MALL_SHOPPINGCART> list_cart, T_MALL_SHOPPINGCART cart) {
        for (int i = 0; i < list_cart.size(); i++) {
            if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
                return false;
            }
        }
        return true;
    }

    @RequestMapping("/cart_success")
    public String cart_success(String success, T_MALL_SHOPPINGCART cart, ModelMap map) {
        map.put("success", success);
        map.put("cart", cart);
        return "sale_cart_success";
    }

    @RequestMapping("/get_minicart")
    public String get_minicart(HttpSession session, ModelMap map,
                               @CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie_param) {

        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user_login");

        List<T_MALL_SHOPPINGCART> list_cart = new ArrayList<>();
        if (user == null) {
            list_cart = JSONUtil.Json2List(list_cart_cookie_param, T_MALL_SHOPPINGCART.class);
        } else {
            list_cart = (List<T_MALL_SHOPPINGCART>) session.getAttribute("list_cart_session");
        }
        map.put("list_cart", list_cart);
        return "sale_minicart_list_inner";
    }

    @RequestMapping("/goto_cart_list")
    public String goto_cart_list(HttpSession session, ModelMap map,
                                 @CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie_param) {
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user_login");
        List<T_MALL_SHOPPINGCART> list_cart = new ArrayList<>();
        if (user == null) {
            list_cart = JSONUtil.Json2List(list_cart_cookie_param, T_MALL_SHOPPINGCART.class);
        } else {
            list_cart = (List<T_MALL_SHOPPINGCART>) session.getAttribute("list_cart_session");
        }
        map.put("list_cart", list_cart);
        return "sale_cart_list";
    }

    @RequestMapping("/change_cart")
    public String change_cart(ModelMap map, String shfxz, int tjshl, int sku_id, HttpSession session, HttpServletResponse response,
                              @CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie_param) {
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user_login");
        List<T_MALL_SHOPPINGCART> list_cart = new ArrayList<>();
        if (user == null) {
            list_cart = JSONUtil.Json2List(list_cart_cookie_param, T_MALL_SHOPPINGCART.class);
            change_cart_by_sku_id(shfxz, tjshl, sku_id, list_cart);
            Cookie cookie = new Cookie("list_cart_cookie", JSONUtil.List2Json(list_cart));
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
            map.put("list_cart", list_cart);
        } else {
            //用户已登陆，操作数据库
            list_cart = (List<T_MALL_SHOPPINGCART>) session.getAttribute("list_cart_session");
            T_MALL_SHOPPINGCART cart = change_cart_by_sku_id(shfxz, tjshl, sku_id, list_cart);
            cartService.update_cart(cart);
            map.put("list_cart", list_cart);
        }
        return "sale_cart_list_inner";
    }

    private T_MALL_SHOPPINGCART change_cart_by_sku_id(String shfxz, int tjshl, int sku_id, List<T_MALL_SHOPPINGCART> list_cart) {
        T_MALL_SHOPPINGCART cart = null;
        for (int i = 0; i < list_cart.size(); i++) {
            if (list_cart.get(i).getSku_id() == sku_id) {
                cart = list_cart.get(i);
                cart.setShfxz(shfxz);
                if (tjshl != -1) {
                    //更改checkbox状态时，传入tjshl=-1;而且，当tjshl减到0时，此方法可以避免出现tjshl为负。
                    cart.setTjshl(tjshl);
                }
                //重新计算总价格。
                BigDecimal price = new BigDecimal(cart.getSku_jg() + "");
                BigDecimal count = new BigDecimal(cart.getTjshl() + "");
                BigDecimal amount = price.multiply(count);
                cart.setHj(amount.doubleValue());
                break;
            }
        }
        return cart;
    }


    @RequestMapping("/delete_cart")
    private String delete_cart(int cart_id, int index,@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie_param,
                               HttpSession session, HttpServletResponse response,ModelMap map) {
        T_MALL_USER_ACCOUNT user_login = (T_MALL_USER_ACCOUNT) session.getAttribute("user_login");
        List<T_MALL_SHOPPINGCART> list_cart = new ArrayList<>();
        if (user_login == null) {
            list_cart = JSONUtil.Json2List(list_cart_cookie_param, T_MALL_SHOPPINGCART.class);
            list_cart.remove(index);
            String json = JSONUtil.List2Json(list_cart);
            Cookie cookie=new Cookie("list_cart_cookie",json);
            cookie.setMaxAge(60*60);
            response.addCookie(cookie);
        } else {
            cartService.delete_cart_by_id(cart_id);
            list_cart = (List<T_MALL_SHOPPINGCART>) session.getAttribute("list_cart_session");
            list_cart.remove(index);
        }
        map.put("list_cart", list_cart);
        return "sale_cart_list_inner";
    }

    @ResponseBody
    @RequestMapping("/add_to_cart_from_search_list")
    public String add_to_cart_from_search_list(int sku_id,HttpServletRequest request,HttpServletResponse response,HttpSession session,
                                               @CookieValue(value = "list_cart_cookie",required = false) String list_cart_cookie_param, ModelMap map){
        DETAIL_T_MALL_PRODUCT sku = productService.select_detail_product_by_sku_id(sku_id);
        T_MALL_SHOPPINGCART cart=new T_MALL_SHOPPINGCART();

        cart.setSku_mch(sku.getSku_mch());
        cart.setSku_jg(sku.getJg().doubleValue());
        cart.setTjshl(1);
        cart.setHj(sku.getJg().doubleValue());
        cart.setShp_id(sku.getShp_id());
        cart.setSku_id(sku.getId());
        cart.setShp_tp(sku.getSpu().getShp_tp());
        cart.setShfxz("1");
        cart.setKcdz(sku.getKcdz());
        String s = add_cart(request, response, session, map, cart, list_cart_cookie_param);
        return s;
    }
}
