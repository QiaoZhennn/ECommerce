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
    <title>信息管理</title>
</head>
<body>

<form action="save_spu.do" method="post" enctype="multipart/form-data">
    <div class="easyui-layout" data-options="fit:true" style="height: 690px;">
        <div data-options="region:'north',split:true" style="height:100px">
            <h2>SPU信息管理页面</h2>
            <select name="flbh1" id="spu_class_1_select" onchange="spu_get_class_2_by_class_1(this.value)"></select>
            <select name="flbh2" id="spu_class_2_select"></select>
            <select name="pp_id" id="spu_tm_select"></select>
        </div>
        <div data-options="region:'west',split:true" style="width:200px">
            <span>商品名称</span><br>
            <input type="text" name="shp_mch"><br>
            <span>商品描述</span><br>
            <textarea name="shp_msh" rows="10" cols="23"></textarea>
        </div>
        <div data-options="region:'center'">
            spu图片<br>
            <div id="spu_images">
                <img id="spu_img_0" onclick="spu_click_image(0)" style="cursor: pointer" src="/image/upload_hover.png"
                     width="70px;"/>
                <img id="spu_img_1" onclick="spu_click_image(1)" style="cursor: pointer" src="/image/upload_hover.png"
                     width="70px;"/>
                <img id="spu_img_2" onclick="spu_click_image(2)" style="cursor: pointer" src="/image/upload_hover.png"
                     width="70px;"/>
                <input id="spu_file_0" onchange="spu_change_image(0)" style="display: none" type="file"
                       name="files"><br>
                <input id="spu_file_1" onchange="spu_change_image(1)" style="display: none" type="file"
                       name="files"><br>
                <input id="spu_file_2" onchange="spu_change_image(2)" style="display: none" type="file"
                       name="files"><br>
            </div>
            <input type="submit" value="Submit">
        </div>
    </div>
</form>
<%--避免与主页面的EasyUI框架的核心jquery冲突，注释掉子页面的jquery引用。--%>
<%--<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>--%>
<script type="text/javascript">

    $.getJSON("js/json/class_1.js", function (data) {
        $(data).each(function (i, json) {
            $("#spu_class_1_select").append("<option value=" + json.id + ">" + json.flmch1 + "</option>");
        });
    });

    function spu_get_class_2_by_class_1(class_1_id) {
        //var class_1_id = $("#spu_class_1_select").val();
        //$("spu_class_1_select option:selected").val();
        $.getJSON("js/json/class_2_" + class_1_id + ".js", function (data) {
            $("#spu_class_2_select").empty();
            $(data).each(function (i, json) {
                $("#spu_class_2_select").append("<option value=" + json.id + ">" + json.flmch2 + "</option>");
            });
        });
        spu_get_tm_by_class_1(class_1_id);
    }

    function spu_get_tm_by_class_1(class_1_id) {
        $.getJSON("js/json/tm_class_1_" + class_1_id + ".js", function (data) {
            $("#spu_tm_select").empty();
            $(data).each(function (i, json) {
                $("#spu_tm_select").append("<option value=" + json.id + ">" + json.ppmch + "</option>");
            });
        });
    }

    function spu_click_image(index) {
        $("#spu_file_" + index).click();
    }

    function spu_change_image(index) {
        //HTML5的新技术。DOM中的file对象，有一个files属性，获得第一个file对象，是BinaryLargeObject类的对象，即为我需要的缩略图
        var file = $("#spu_file_" + index)[0].files[0];
        //HTML5的新技术。上文得到的file被存在缓存中，通过如下方法获取该file的缓存路径。
        var url = window.URL.createObjectURL(file);
        $("#spu_img_" + index).attr("src", url);
        //追加新的增加图片按钮
        if (index >= 2 && (index + 1) == $("#spu_images img").length) {
            spu_append_image(index + 1);
        }
    }

    function spu_append_image(index) {
        var img = '<img id="spu_img_' + index + '" onclick="spu_click_image(' + index + ')" style="cursor: pointer" src="/image/upload_hover.png" width="70px;"/>';
        var input = '<input id="spu_file_' + index + '" onchange="spu_change_image(' + index + ')" style="display: none" type="file" name="files">';
        $("#spu_images").append(img + input);
    }
</script>
</body>
</html>