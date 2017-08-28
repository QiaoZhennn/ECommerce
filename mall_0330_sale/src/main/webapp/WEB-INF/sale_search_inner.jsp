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
<c:forEach items="${list_sku}" var="sku">
    <div style="border: grey 1px solid;width: 200px;height: 300px;float: left;margin-left: 20px;margin-top: 20px;position: relative">
        <div onclick="goto_detail_page(${sku.id})" style="cursor: pointer;">
        <img src="/upload/image/spu_imgs/${sku.spu.shp_tp}" width="180px" style="margin-left: 10px;margin-top: 10px"/><br>
            商品名称: ${sku.sku_mch}
            价格: ${sku.jg}<br>
        </div>
        <div style="float: right">
            <button id="add_cart_button" onclick="add_to_cart_from_search_list(${sku.id})">加到购物车</button>
        </div>
    </div>
</c:forEach>

<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/layer/layer.js"></script>
<script type="text/javascript">
    function goto_detail_page(sku_id) {
        window.location.href="/sale_product_detail.htm?sku_id="+sku_id;
    }

    function add_to_cart_from_search_list(sku_id) {
        $.ajax({
            url:"/add_to_cart_from_search_list.do",
            type:"POST",
//            dataType:"json",
            data:{
                sku_id:sku_id
            },
            success:function (result) {
                result=decodeURIComponent(result);
                alert(result);
            },
            error:function () {
                alert("请求失败");
            }
        });
    }
</script>
</body>
</html>