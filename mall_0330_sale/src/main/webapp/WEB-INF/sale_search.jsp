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
    <title>产品列表</title>
    <link rel="stylesheet" type="text/css" href="/css/css.css">
</head>
<body>
<%@include file="sale_header.jsp" %>
<br>
<h2>检索列表</h2>
<hr>
${class_2_name}的检索列表<br>
<div id="search_attr_show"></div>
<br>
<input type="text" id="search_order_show"><br><br>
<div id="attr_area" style="width: 700px">
    <c:forEach items="${list_attr}" var="attr">
        <div id="search_attr_${attr.id}">${attr.shxm_mch}:
            <c:forEach items="${attr.list_value}" var="val">
                <input attr_id="${attr.id}" val_id="${val.id}" type="checkbox">
                <a href="javascript:search_attr_up(${attr.id},${val.id},'${attr.shxm_mch}')"> ${val.shxzh} ${val.shxzh_mch}</a>
            </c:forEach>
            <button style="float: right" id="btn_${attr.id}" onclick="confirm_select(${attr.id})">
                确认多选${attr.shxm_mch}属性
            </button>
        </div>
        <br>
    </c:forEach>
</div>
<hr>
<a href="javascript:search_change_order(' ORDER BY jg ');">价格</a>
<a href="javascript:search_change_order(' ORDER BY sku_xl ');">销量</a>
<a href="javascript:search_change_order(' ORDER BY sku.chjshj ');">上架时间</a>
<hr>
<div id="search_result"></div>

</body>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
    $.post("/get_search_result.do", {class_2_id: "${class_2_id}"}, function (data) {
        $("#search_result").html(data);
    });

    function search_attr_up(shxm_id, shxzh_id, shxm_mch) {
        $("#search_attr_show").append('<div id="search_attr_show_id' + shxm_id + '">' +
            '<a shxm_id=' + shxm_id + ' shxzh_id=' + shxzh_id + '  href="javascript:search_attr_down(' + shxm_id + ')">' + shxm_mch + '</a>' +
            '<input type="text" value="' + shxm_id + '_' + shxzh_id + '" name="search_attr_id_arry">' +
            //                '<input type="text" value="{\"shxm_id\":'+shxm_id+',\"shxzh_id\"'+shxzh_id+'}" name="search_attr_id_arry">'+ JSON格式
            '</div>');
        $("#search_attr_" + shxm_id).hide();
        search_get_sku_by_attr();
    }

    function search_attr_down(shxm_id) {
        $("#search_attr_show_id" + shxm_id).remove();
        $("#search_attr_" + shxm_id).show();
        search_get_sku_by_attr();
    }

    function search_get_sku_by_attr() {
        var list_av = $("input[name='search_attr_id_arry']");
        //数组方式
        var list_av_array = new Array();
        $(list_av).each(function (i, jsonObj) {
            list_av_array[i] = jsonObj.value;
        });
        /*$(list_av).each(function (i, jsonObj) {
            var json_obj=$.parseJSON(jsonObj.value);

        });*/
        var class_2_id = "${class_2_id}";
        var order = $("#search_order_show").val();
        $.post("get_sku_by_attr.do", {class_2_id: class_2_id, list_av: list_av_array, order: order}, function (data) {
            $("#search_result").html(data);
        });

    }


    function search_change_order(new_order) {
        var old_order = $("#search_order_show").val();
        //判断用户是否重复点击同一个排序关键字
        if (new_order == old_order) {
            new_order += " DESC ";
        }
        $("#search_order_show").val(new_order);
        search_get_sku_by_attr();
    }

    function confirm_select(attr_id) {
        var select_box = $(":checked[attr_id=" + attr_id + "]");
//        alert(select_box.length);

        $(select_box).each(function (i, c) {
//            alert("attr_id"+$(c).attr("attr_id")+" , val_id"+$(c).attr("val_id")) ;
            //TODO
            var multi_attr = $(c).attr("attr_id") + "_" + $(c).attr("val_id");
        });

    }
</script>
</html>