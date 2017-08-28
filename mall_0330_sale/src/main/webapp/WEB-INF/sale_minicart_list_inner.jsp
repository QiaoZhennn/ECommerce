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
<h6>购物车中的商品</h6>
<c:forEach items="${list_cart}" var="cart">

    <div class="one border">
        <img onclick="sale_product_detail(${cart.sku_id})" style="cursor: pointer;width: 62px;height: 61px" src="/upload/image/spu_imgs/${cart.shp_tp}"/>
        <span class="one_name">
                ${cart.sku_mch}
        </span>
        <span class="one_prece">
						<b>￥${cart.sku_jg}</b><br>
		</span>
    </div>
</c:forEach>

<div class="gobottom">
    共<span>${list_cart.size()}</span>种商品<br>
    <button class="goprice" onclick="goto_cart_list()">去购物车</button>
</div>


<%--<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>--%>
<script type="text/javascript">
    $(function () {
        $("#cart_size").html("${list_cart.size()}");
    });

    function goto_cart_list() {
    window.location.href="/goto_cart_list.htm";
    }

    function sale_product_detail(sku_id) {
    window.location.href="/sale_product_detail.htm?sku_id="+sku_id;
    }
</script>
</body>
</html>