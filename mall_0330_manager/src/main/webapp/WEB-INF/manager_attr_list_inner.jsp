<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>

    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
    ${class_2_name}：属性列表。<hr>
    <c:forEach items="${list_attr}" var="attr">
        <c:forEach items="${attr.list_value}" var="val">
            ${val.shxzh} ${val.shxzh_mch}
        </c:forEach>
        <hr>
    </c:forEach>

    <a href="javascript:index_add_tabs('goto_add_attr.htm?class_2_id=${class_2_id}&class_2_name=${class_2_name}','添加分类属性')">添加分类属性</a>

    <%--<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>--%>
    <script type="text/javascript">

    </script>
</body>

</html>