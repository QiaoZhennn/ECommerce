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
    <title>购物车</title>
    <link rel="stylesheet" type="text/css" href="/css/css.css">
</head>
<body>
<%--<jsp:include page="sale_header.jsp"/>--%>
<%@include file="sale_header.jsp" %>

<form id="cartlist" action="goto_order.htm" method="post">
<div id="sale_cart_list_inner">
    <jsp:include page="sale_cart_list_inner.jsp"/>
</div>
</form>

<%--底部结账栏。--%>
<div style="background-color: #31b0d5;width: 100%;height: 50px;position: fixed;left: 0px;bottom: 0px">
    <div style="margin: 10px;float: left">Amount: <span id="total_amount"></span></div>
    <div style="margin: 5px;float: left;">
        <button style="left: 50%;width: 60px;height: 40px" onclick="submit_order()">结算</button>
    </div>
    <div style="width: 100px;height: 50px;float: right;background-color: #5cb85c"><span id="pay_result"></span></div>
</div>

<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

    $(function () {
        get_total_amount();
    });

    function get_total_amount() {
        var total_amount = 0.0;
        $(":checked").each(function (i, c) {
            var item= $(c).parent().next().next().children("span").eq(0);
            total_amount += parseFloat($(item).text());
        });
        $("#total_amount").text(total_amount);
    }

    function submit_order() {
        var user_login="${sessionScope.user_login}";
        if(user_login!=""){
            $("#cartlist").submit();
        }else{
            alert("请先登陆");
        }
    }
</script>
</body>
</html>