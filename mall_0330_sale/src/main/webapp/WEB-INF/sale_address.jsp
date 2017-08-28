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
    <title>Address</title>
</head>
<body>
<c:forEach items="${list_address}" var="address" varStatus="index">
    地址${index.index+1}：${address.yh_dz}<br>
</c:forEach>
<hr>
<form action="add_address.do" method="post">
    输入地址：<input type="text" name="yh_dz"><br><br>
    输入收件人：<input type="text" name="shjr"><br><br>
    输入收件人手机号：<input type="text" name="lxfsh"><br><br>
    保存： <input type="submit" name="保存"><br>
</form>

<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

</script>
</body>
</html>