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
    <title>Register</title>
</head>
<body>
<form action="/register.do" method="post">
    用户名称：<input id="yh_mch" type="text" name="yh_mch" value="Lina1"><span id="message"></span><br><br>
    用户昵称：<input type="text" name="yh_nch" value="莉娜1"><br><br>
    用户密码：<input type="text" name="yh_mm" value="123123"><br><br>
    用户姓名：<input type="text" name="yh_xm" value="Lina"><br><br>
    用户手机号：<input type="text" name="yh_shjh" value="13112341234"><br><br>
    用户邮箱：<input type="text" name="yh_yx" value="Lina@dota.com"><br><br>
    <input type="submit" value="提交">
</form>

<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
$("#yh_mch").change(function () {
    var yh_mch=$("#yh_mch").val();
    $.post("/check_yh_mch.do",{yh_mch:yh_mch},function (data) {
        $("#message").html(data);
    })
});
</script>
</body>
</html>