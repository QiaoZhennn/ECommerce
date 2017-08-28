<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="/css/css.css">

</head>
<body>
<%--<a href="/goto_cart_list.htm" target="_blank" onmouseover="minicart_show()" onmouseout="minicart_hide()">购物车</a>
<hr>
<div id="minicart_cart_list" style="border: orangered 1px solid;display: none;"></div>--%>


    <a href="/goto_cart_list.htm" target="_blank" >购物车<div class="num" id="cart_size">0</div></a>

    <!--购物车商品-->
    <div id="minicart_cart_list" class="cart_pro">

    </div>


<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function minicart_show() {
    $.post("get_minicart.do",function (data) {
        $("#minicart_cart_list").html(data);
    });
    $("#minicart_cart_list").show();

}
function minicart_hide() {
    $("#minicart_cart_list").hide();

}
</script>
</body>
</html>
