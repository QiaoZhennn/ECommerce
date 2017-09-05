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
<%@include file="sale_header.jsp" %>
<%--<jsp:include page="sale_header.jsp"/>--%>
<br><br>
<div class="top_img">

</div>
<div class="search">
    <div class="logo"><img src="/images/logo.jpg" alt=""></div>
    <div class="search_on">
        <div class="se">
            <form id="keywords_id" action="/search_keywords.do" method="post">
            <input type="text" name="keywords" class="lf">
            <input type="button" class="clik" style="cursor: pointer" onclick="keywords_submit()" value="搜索">
            </form>
        </div>
        <div class="se">
            <%--            <a href="">取暖神奇</a>
                        <a href="">1元秒杀</a>
                        <a href="">吹风机</a>
                        <a href="">玉兰油</a>--%>
        </div>
    </div>
    <div class="card" onmouseover="minicart_show()" onmouseout="minicart_hide()">
        <jsp:include page="sale_minicart.jsp"/>
        <%--<%@include file="sale_minicart.jsp"%>--%>
    </div>
</div>


<div class="menu">
    <div class="nav">
        <div class="navs">
            <div class="left_nav">
                全部商品分类
                <div class="nav_mini">
                    <ul id="index_class_1_ul">

                    </ul>
                </div>
            </div>
            <ul>

            </ul>
        </div>
    </div>
</div>

<div class="banner">
    <div class="ban">
        <img src="/images/index_banner.jpg" width="980" alt="">
    </div>
</div>

</body>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

    $(function () {
        $('.nav_mini ul li').hover(function () {
            $(this).find('.two_nav').show(100);
            alert($(this).find('.two_nav'));
        }, function () {
            $(this).find('.two_nav').hide(100);
        })
    });

    function onHover() {
        $('.nav_mini ul li').hover(function () {
            $(this).find('.two_nav').show(100);
        }, function () {
            $(this).find('.two_nav').hide(100);
        })
    }

    $.getJSON("js/json/class_1.js", function (data) {
        $(data).each(function (i, json) {
            index_get_class_2_by_class_1(json.id);
            $("#index_class_1_ul").append("<li value=" + json.id + "><a href='javascript:;'>" + json.flmch1 + "</a><div class='two_nav' id='index_class_2_" + json.id + "'></div></li>");
        });
    });

    function index_get_class_2_by_class_1(class_1_id) {
        onHover();
        $.getJSON("js/json/class_2_" + class_1_id + ".js", function (data) {
            $(data).each(function (i, json) {
                var flmch2=encodeURIComponent(json.flmch2);
                var str=flmch2.replace(/%/g,'_');
                $("#index_class_2_" + class_1_id).append("<a  href='/class_2_sku_search.htm?class_2_id=" + json.id + "&class_2_name=" + str + "'>" + json.flmch2 + "</a>");
            });
        });
    }

    function keywords_submit() {
        $("#keywords_id").submit();
    }

</script>
</html>