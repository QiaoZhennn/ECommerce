package com.atguigu.controller;

import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_SHOPPINGCART;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.AddressService;
import com.atguigu.service.CartService;
import com.atguigu.service.UserService;
import com.atguigu.util.JSONUtil;
import com.atguigu.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CartService cartService;


    @RequestMapping("/login")
    public String login(T_MALL_USER_ACCOUNT user, HttpSession session, HttpServletResponse response
            , @CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie_param) {

        T_MALL_USER_ACCOUNT user_login = userService.login(user);
        if (user_login == null) {
            return "redirect:/login.do";
        } else {
            session.setAttribute("user_login", user_login);

            //合并db与cookie中的购物车项目
            merg_cart(response, session, list_cart_cookie_param);

            //将非英文转义成浏览器地址栏的格式，%E5%91%A8等等。
            String encodeStr = "";
            try {
                encodeStr = URLEncoder.encode(user.getYh_mch(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //向浏览器写一个用于保存用户名的cookie
            Cookie cookie = new Cookie("yh_mch", encodeStr);
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
            return "redirect:/index.htm";
        }
    }

    private void merg_cart(HttpServletResponse response, HttpSession session, String list_cart_cookie_param) {
        T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user_login");
        List<T_MALL_SHOPPINGCART> list_cart_db = cartService.get_list_cart_by_user_id(user.getId());
        if (list_cart_db == null) {
            if (StringUtil.isEmpty(list_cart_cookie_param)) {
                //不必任何操作
            } else {
                //添加db
                List<T_MALL_SHOPPINGCART> list_cart_cookie = JSONUtil.Json2List(list_cart_cookie_param, T_MALL_SHOPPINGCART.class);
                for (int i = 0; i < list_cart_cookie.size(); i++) {
                    list_cart_cookie.get(i).setYh_id(user.getId());
                    cartService.add_cart(list_cart_cookie.get(i));
                }
            }
        } else {
            if (StringUtil.isEmpty(list_cart_cookie_param)) {
                //Cookie中没有购物项，不必向数据库中融合
            } else {
                //Cookie中有购物项
                List<T_MALL_SHOPPINGCART> list_cart_cookie = JSONUtil.Json2List(list_cart_cookie_param, T_MALL_SHOPPINGCART.class);
                for (int i = 0; i < list_cart_cookie.size(); i++) {
                    if (is_new_cart(list_cart_db, list_cart_cookie.get(i))) {
                        list_cart_cookie.get(i).setYh_id(user.getId());
                        cartService.add_cart(list_cart_cookie.get(i));
                    } else {
                        for (int j = 0; j < list_cart_db.size(); j++) {
                            if (list_cart_db.get(j).getSku_id() == list_cart_cookie.get(i).getSku_id()) {
                                list_cart_db.get(j).setTjshl(list_cart_db.get(j).getTjshl() + list_cart_cookie.get(i).getTjshl());
                                list_cart_db.get(j).setHj(list_cart_db.get(j).getHj()+list_cart_cookie.get(i).getHj());
                                cartService.update_cart(list_cart_db.get(j));
                            }
                        }
                    }
                }
            }
        }
        session.setAttribute("list_cart_session", cartService.get_list_cart_by_user_id(user.getId()));
        //清空cookie（用空的cookie覆盖原来的cookie）
        Cookie cookie=new Cookie("list_cart_cookie","");
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);
    }

    private boolean is_new_cart(List<T_MALL_SHOPPINGCART> list_cart_cookie, T_MALL_SHOPPINGCART cart) {
        for (int i = 0; i < list_cart_cookie.size(); i++) {
            if (list_cart_cookie.get(i).getSku_id() == cart.getSku_id()) {
                return false;
            }
        }
        return true;
    }


    @RequestMapping("/goto_login")
    public String goto_login() {
        return "sale_login";
    }

    @RequestMapping("/goto_logout")
    public String goto_logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index.do";
    }

    @RequestMapping("/goto_register")
    public String goto_register(){
        return "sale_register";
    }

    @RequestMapping("/register")
    public String register(T_MALL_USER_ACCOUNT user, HttpSession session, HttpServletResponse response
            , @CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie_param){
        userService.register(user);
        T_MALL_USER_ACCOUNT registerUser=new T_MALL_USER_ACCOUNT();
        registerUser.setYh_mch(user.getYh_mch());
        registerUser.setYh_mm(user.getYh_mm());
        return login(registerUser,session,response,list_cart_cookie_param);
    }

    @ResponseBody
    @RequestMapping("/check_yh_mch")
    public String check_yh_mch(String yh_mch) {
        String username=userService.checkUserIfExist(yh_mch);
        if (StringUtil.isEmpty(username)){
            return "<span style='color: green'>This username is available</span>";
        }else {
            return "<span style='color: red'>This username has been used</span>";
        }
    }

    @RequestMapping("/goto_address")
    public String goto_address(HttpSession session, ModelMap map) {
        T_MALL_USER_ACCOUNT user_login = (T_MALL_USER_ACCOUNT) session.getAttribute("user_login");
        List<T_MALL_ADDRESS> list_address = addressService.get_addresses_by_user_id(user_login);
        map.put("list_address",list_address);
        return "sale_address";
    }

    @RequestMapping("/add_address")
    public String add_address(T_MALL_ADDRESS address,HttpSession session) {
        T_MALL_USER_ACCOUNT user_login = (T_MALL_USER_ACCOUNT) session.getAttribute("user_login");
        address.setYh_id(user_login.getId());
        addressService.add_address(address);
        return "redirect:/goto_address.htm";
    }
}
