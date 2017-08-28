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
    <span>商品详情页面</span>
    <hr>
    ${obj_sku.spu.shp_tp}<br>
    ${obj_sku.sku_mch}<br>
    ${obj_sku.jg}<br>
    ${obj_sku.kc}<br>
    <hr>
    <c:forEach items="${list_sku}" var="sku">
        <a href="/get_sku_by_attr.do?sku_id=${sku.id}&spu_id=${sku.shp_id}">${sku.shp_id}</a>
    </c:forEach>
    <hr>
    <c:forEach items="${sku.list_av_name}" var="av">
        ${av.shxm_mch}:${av.shxzh_mch}
    </c:forEach>
    <hr>
    ${sku.spu.shp_msh}<br>
    <c:forEach items="${list_image}" var="img">
        <img src="/upload/image/${sku.spu.shp_tp}" width="180px" style="margin-left: 10px;margin-top: 10px"/><br>
        Price: ${sku.jg}<br>
        Name: ${sku.sku_mch}
    </c:forEach>

<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

</script>
</body>
</html>