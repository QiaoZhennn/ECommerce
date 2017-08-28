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
    <title>Title</title>
</head>
<body>

<h3>购物车列表</h3>
<c:forEach items="${list_cart}" var="cart" varStatus="index">
    <%--如果数据库中该用户有该购物项，则自动check。--%>
    <div style="margin: 20px;padding:10px;width: 50%;height: 90px;background-color: #99cdff;position: relative">
        <div style="float: left">
            <input id="check_status_${index.index}" onclick="sale_change_cart(${index.index},-1 ,${cart.sku_id})" value="${cart.shfxz}" type="checkbox" ${1==cart.shfxz?"checked":""}>
        </div>
        <div style="float: left;margin-right: 20px">
            <img src="/upload/image/spu_imgs/${cart.shp_tp}" style="height: 80px;"/>
        </div>
        <div style="float: left">
            Name: ${cart.sku_mch}<br>
            Price: ${cart.sku_jg}<br>
            Amount: <span id="item_amount">${cart.hj}</span>
        </div>
        <div style="float: right">
            【<a href="javascript:sale_change_cart(${index.index},${cart.tjshl-1},${cart.sku_id})">-</a>
            <span>${cart.tjshl}</span>
            <a href="javascript:sale_change_cart(${index.index},${cart.tjshl+1},${cart.sku_id})">+</a>】
        </div>
        <div style="float:right;">
            <button type="button" onclick="delete_cart(${cart.id},${index.index})">从购物车中删除</button>
        </div>

    </div>

</c:forEach>

<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

/*    $(function () {
        //设置数据库中被选中的元素，在显示时默认选中。此js代码被<input>中的三元晕算法代替，动态添加checked属性。
         $(":checkbox").each(function (i, c) {
             if(c.value=="1") {
                 $(this).attr("checked", true);
             }
         })
    });*/
$(function () {
    get_total_amount();
})

function sale_change_cart(index,tjshl,sku_id) {
    var checked=$("#check_status_"+index).attr("checked");
    if(checked=="checked"){
        checked="1";
    }else{
        checked="0";
    }
    $.post("change_cart.do",{shfxz:checked,tjshl:tjshl,sku_id:sku_id},function (data) {
        //返回内嵌页给自己，刷新自己
        $("#sale_cart_list_inner").html(data);
    });
}

function delete_cart(cart_id,index) {
    $.post("delete_cart.do",{cart_id:cart_id,index:index},function (data) {
        $("#sale_cart_list_inner").html(data);
    });
}
</script>
</body>
</html>