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
<%--<form id="sku_form" method="post" action="/save_sku.do" >--%>
<c:forEach items="${list_attr}" var="attr" varStatus="index">
    <input type="checkbox" value="${attr.id}" name="list_av[${index.index}].shxm_id"
           onclick="sku_attr_show(${attr.id},this.checked)"><span>${attr.shxm_mch}</span>
</c:forEach>
<hr>
<c:forEach items="${list_attr}" var="attr" varStatus="index">
    <div id="sku_inner_${attr.id}" style="display: none">
        <c:forEach items="${attr.list_value}" var="val">
            <input type="radio" value="${val.id}" name="list_av[${index.index}].shxzh_id">${val.shxzh} ${val.shxzh_mch}
        </c:forEach>
    </div>
    <br>
</c:forEach>
<hr>
库存名称：<input type="text" name="sku_mch"><br>
库存数量：<input type="text" name="kc"><br>
库存价格：<input type="text" name="jg"><br>
库存地址：<input type="text" name="kcdz"><br>
<br>
<button type="button" onclick="submit_check()">提交</button>
<%--</form>--%>
<%--<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>--%>
<script type="text/javascript">

    function submit_check() {
        var checkVar=$("input[name='list_av[0].shxm_id']");
        var val=checkVar.val();
        if(val==undefined){
            alert("该分类无法提交");
        }else{
            $("#sku_form").submit();
        }
    }

    function sku_attr_show(attr_id, check) {
        var sku_inner = $("#sku_inner_" + attr_id);
        if (check) {
            sku_inner.show();
        } else {
            sku_inner.hide();
        }

    }
</script>
</body>

</html>