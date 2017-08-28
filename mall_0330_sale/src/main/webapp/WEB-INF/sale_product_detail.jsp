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
    <title>产品详情</title>
    <link rel="stylesheet" type="text/css" href="/css/css.css">
</head>
<body>
<%@include file="sale_header.jsp" %>
<br>
<div style="width: 600px;border: cadetblue 5px dashed;padding-left: 10px;padding-right: 10px;float: left">
    <img src="/upload/image/spu_imgs/${sku.spu.shp_tp}" alt="image for ${sku.spu.shp_mch}" style="width: 400px"/><br>
    <span>Price: ${sku.jg}</span><br>
    <span>Stock: ${sku.kc}</span><br>
    <div>
        <c:forEach items="${sku.list_spu_img}" var="spu_img">
            <img src="/upload/image/spu_imgs/${spu_img.url}"
                 style="width: 80px;margin-left: 10px;border: cornflowerblue 1px solid"/>
        </c:forEach>
    </div>
    <div style="height: 50px"></div>
    <div>
        <h3>background information</h3>
        <form id="add_cart_form" action="/add_cart.do" method="post">
            sku_mch:<input type="text" name="sku_mch" value="${sku.sku_mch}"><br>
            sku_jg:<input type="text" name="sku_jg" value="${sku.jg}"><br>
            tjshl:<input type="text" name="tjshl" value="1"><br>
            hj:<input type="text" name="hj" value="${sku.jg}"><br>
            shp_id:<input type="text" name="shp_id" value="${sku.shp_id}"><br>
            sku_id:<input type="text" name="sku_id" value="${sku.id}"><br>
            shp_tp:<input type="text" name="shp_tp" value="${sku.spu.shp_tp}"><br>
            shfxz:<input type="text" name="shfxz" value="1"><br> <%--是否选中--%>
            kcdz:<input type="text" name="kcdz" value="${sku.kcdz}"><br>
            <%--<input type="submit" value="添加到购物车" style="background: mediumturquoise;width: 100px;height: 50px"><br>--%>
        </form>
        <button id="submit_form" style="background: mediumturquoise;width: 100px;height: 50px">添加到购物车</button>
        <div id="is_success"></div>
    </div>
</div>
<div style="width: 600px;border: cadetblue 5px dashed;padding: 20px;float: right">
    <h4>SKUs in one SPU</h4>
        <c:forEach items="${list_sku}" var="single_sku">
            <div style="width: 400px;height: 150px;border: cadetblue 3px inset;margin:10px;float: left">
            <c:forEach items="${single_sku.list_attr_value}" var="single_sku_attr">
                <span>${single_sku_attr.sku_attr.shxm_mch}: ${single_sku_attr.shxzh_mch}: ${single_sku_attr.shxzh}</span><br>
            </c:forEach>
            </div>
        </c:forEach>

</div>

<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/form/jquery-form.min.js"></script>
<script type="text/javascript">

    $("#submit_form").click(function () {
        $("#add_cart_form").ajaxSubmit({
            success:function (result) {
                result=decodeURIComponent(result);
                $("#is_success").html("<span style='color: green'>"+result+"</span>");
            },
            timeout:3000
        });
    });

</script>
</body>
</html>