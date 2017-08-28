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
    <title>收银台</title>
</head>
<body>
收货人：<span id="shhr"></span><br>
收获地址：<span id="dzh_mch"></span><br>
总金额：${param.zje}<br>

<hr>
<form action="order_pay.do" method="post">
    <input type="submit" value="点击支付">
</form>
<a href="javascript:pay_the_bill(${param.zje});">点击付款</a>
<div style="height: 40px;" id="amount"></div>
<a href="/index.do">返回首页</a>

<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
    $(function () {
        var shhr=decodeURIComponent("${param.shhr}");
        $("#shhr").text(shhr);
        var dzh_mch=decodeURIComponent("${param.dzh_mch}");
        $("#dzh_mch").text(dzh_mch);
    });
function pay_the_bill(amount) {
    $("#amount").html("<span style='color: #5cb85c;'>已付款："+amount+"</span>");
}
</script>
</body>
</html>