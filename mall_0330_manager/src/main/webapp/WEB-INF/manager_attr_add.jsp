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
${class_2_name}添加页面<br>
${success}<br>
<a href="javascript:;" onclick="attr_add_table()">添加属性选项</a>
<form action="save_attr.do" method="post">
    <input type="hidden" name="class_2_id" value="${class_2_id}">
    <input type="hidden" name="class_2_name" value="${class_2_name}">

<table id="attr_add_table_0" border="1px" width="500px">
    <tr>
        <td>属性名:<input name="list_attr[0].shxm_mch" type="text"></td>
        <td><a href="javascript:;" onclick="attr_add_tr(0)">添加属性值</a></td>
    </tr>
    <tr>
        <td>属性值:<input name="list_attr[0].list_value[0].shxzh" type="text"></td>
        <td>单位:<input name="list_attr[0].list_value[0].shxzh_mch" type="text"></td>
    </tr>
</table>

    <input type="submit" value="Submit">
</form>
<%--<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>--%>
<script type="text/javascript">
    var attr_add_table_index=0;
    function attr_add_table() {
        var dataContent='';
        dataContent+='<hr>';
        dataContent+='<table id="attr_add_table_'+(attr_add_table_index+1)+'" border="1px" width="500px">';
        dataContent+='<tr>';
        dataContent+='    <td>属性名:<input name="list_attr['+(attr_add_table_index+1)+'].shxm_mch" type="text"></td>';
        dataContent+='    <td><a href="javascript:;" onclick="attr_add_tr('+(attr_add_table_index+1)+')">添加属性值</a></td>';
        dataContent+='</tr>';
        dataContent+='<tr>';
        dataContent+='    <td>属性值:<input name="list_attr['+(attr_add_table_index+1)+'].list_value[0].shxzh" type="text"></td>';
        dataContent+='    <td>单位:<input name="list_attr['+(attr_add_table_index+1)+'].list_value[0].shxzh_mch" type="text"></td>';
        dataContent+='</tr>';
        dataContent+='</table>';
        $("#attr_add_table_"+attr_add_table_index).after(dataContent);
        attr_add_table_index++;
    }


    function attr_add_tr(index) {
        var attr_add_tr_index=$("#attr_add_table_"+index+" tr").length-1;
        var dataContent='';
        dataContent+='<tr>';
        dataContent+='    <td>属性值:<input name="list_attr['+index+'].list_value['+attr_add_tr_index+'].shxzh" type="text"></td>';
        dataContent+='    <td>单位:<input name="list_attr['+index+'].list_value['+attr_add_tr_index+'].shxzh_mch" type="text"></td>';
        dataContent+='</tr>';
        $("#attr_add_table_"+index).append(dataContent);
    }
</script>
</body>

</html>