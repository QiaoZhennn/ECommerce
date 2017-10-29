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
    <link rel="shortcut icon" type="image/icon" href="/images/jd.ico">
    <link rel="stylesheet" type="text/css" href="/css/login.css">
    <title>Login</title>
</head>
<body>
<div class="up">
    <%--<img src="/images/logo(2).jpg" height="45px;" class="hy_img"/>--%>
    <div class="hy">
        欢迎登录
    </div>
</div>
<div class="middle">
    <div class="login_banner" style="width: 50%;height: 415px;float: left;margin-left: 100px;margin-top: 30px;">
        <img style="height: 100%" src="/images/login_banner.jpg"/>
    </div>
    <div class="login">
        <div class="l1 ">
            <div class="l1_font_01 ">会员</div>
            <a class="l1_font_02 " href="<%=application.getContextPath() %>/to_regist.do">用户注册</a>
        </div>
        <div class="blank_01"></div>
        <div class="ts">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${err}
        </div>
        <div class="blank_01"></div>
        <form action="/login.do" id="login_form" method="post">
            <div class="input1">
                <input type="text" class="input1_01" value="Lina" name="yh_mch"/>
            </div>
            <div class="blank_01"></div>
            <div class="blank_01"></div>
            <div class="input2">
                <input type="text" class="input1_01" value="123" name="yh_mm"/>
            </div>

            <div class="blank_01"></div>
            <div class="blank_01"></div>
            <div class="box_01">
                <div class="box_01_both">

                    <div class="box_01_both_2">忘记密码？</div>
                </div>
            </div>
            <div class="blank_01"></div>
            <input type="submit" class="red_button" value="登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录">
            <%--<a href="javascript:;" class="aline">
                <div class="red_button" onclick="window.location.href='/login.do'">
                    登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录
                </div>
            </a>--%>
        </form>

        <div class="blank_01"></div>
        <div class="blank_01"></div>
        <div class="box_down">

        </div>
    </div>
</div>

<%--<div class="down">
    <br />
    Copyright©2004-2017 版权所有
</div>--%>
</body>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

</script>
</html>