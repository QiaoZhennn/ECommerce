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
    <title>订单确认页面</title>
</head>
<body>
<form action="/save_order.do" method="post">
<h4>地址集合</h4>
<c:forEach items="${list_address}" var="address" varStatus="i">
<input type="radio" name="address_id" value="${address.id}" ${i.index==0?"checked":""}>用户地址：${address.yh_dz}；&nbsp;&nbsp;&nbsp;收件人${address.shjr}<br>
</c:forEach>
<hr>
    订单阶段号：${order.jdh}<br>
    ￥：${order.zje}<br>
<c:forEach items="${order.list_flow}" var="flow">
配送方式：${flow.psfsh}&nbsp;&nbsp;&nbsp;商品状态：${flow.mqdd}<br>
    <div style="border: dodgerblue 3px double;margin: 20px;padding: 5px">
    <c:forEach items="${flow.list_info}" var="info">
        <div style="margin: 5px;padding: 10px;background-color: #99cdff">
        <img src="/upload/image/spu_imgs/${info.shp_tp}" width="70px"/>
        ${info.sku_mch},${info.sku_jg}
        </div>
    </c:forEach>
    </div>
</c:forEach>
<br>
<input type="submit" value="提交订单">
</form>

<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

</script>
</body>
</html>