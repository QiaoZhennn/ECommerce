<%--
  Created by IntelliJ IDEA.
  User: qiaoz
  Date: 2017/8/18
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<div class="top">
    <div class="top_text">
        <c:if test="${empty user_login}">
            <a href="/goto_login.htm">用户登录</a>
            <a href="/goto_register.htm">用户注册</a>
            <a href="javascript:;">供应商登录</a>
            <a href="javascript:;">供应商注册</a>
        </c:if>
        <c:if test="${not empty user_login}">
            <a>欢迎: <span id="user_name"></span></a>
            <a href="/goto_address.htm">我的订单</a>
            <a href="/goto_logout.do">注销</a>
            <a href="javascript:;"></a>
        </c:if>
    </div>
</div>
<%--<div style="position: fixed;left: 0;top: 0;z-index: 10;height: 30px;width: 100%;background-color:#a6e1ec;">
    <div style="float: right;margin: 5px">
<c:if test="${empty user_login}">
    <a href="/goto_register.htm">Register</a> <a href="/goto_login.htm">Login</a>
</c:if>
<c:if test="${not empty user_login}">
    <a href="javascript:window.location.href='goto_address.htm'">My Order</a> Welcome: <span id="user_name"></span> <a href="/goto_logout.do">Logout</a>
</c:if>
    </div>
</div>--%>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

    //浏览器端处理cookie
    $(function () {
        var loginUser=get_cookie_value(" yh_mch");
        $("#user_name").text(loginUser);
    });
    function get_cookie_value(key) {
        var val="";
        var cookies=document.cookie;
        cookies=decodeURIComponent(cookies);
        cookies=cookies.replace(/\s/,""); //用空字符串代替空格。Cookie由数组保存，各元素之间由“分号空格”分开。此步先去掉空格
        var cookie_array = cookies.split(";"); //通过分号分开各元素。
        for(i=0;i<cookie_array.length;i++){
            var cookie_key=cookie_array[i].split("=");
            if(key==cookie_key[0]){
                val=cookie_key[1];
            }
        }
        return val;
    }

</script>

