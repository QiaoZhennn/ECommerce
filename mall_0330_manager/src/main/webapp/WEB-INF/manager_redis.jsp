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
    <title>商品缓存</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true" style="height: 690px;">
    <div data-options="region:'north',split:true" style="height:60px">
        <h3>缓存检索</h3>
    </div>
    <div data-options="region:'west',split:true" style="width:200px">
        <select class="easyui-combobox" name="flbh1" id="attr_class_1_select_redis"></select><br>
        <select class="easyui-combobox" name="flbh2" id="attr_class_2_select_redis">
            <option>请选择</option>
        </select>
    </div>
    <div data-options="region:'center',split:true">
        <div id="attr_list_inner_redis"></div><br>
        <button onclick="refresh_sku_by_class_2()">刷新二级分类的商品信息</button><br><br>
        <button onclick="refresh_sku_by_attrs()">刷新分类属性的商品集合</button><br>
    </div>

</div>

<script type="text/javascript">

    $("#attr_class_1_select_redis").combobox({
        url: 'js/json/class_1.js',
        valueField: 'id',
        textField: 'flmch1',
        width: 80,
        value: '请选择',
        onSelect: function attr_get_class_2_by_class_1() {
            var class_1_id = $(this).combobox('getValue');
            $("#attr_class_2_select_redis").combobox({
                url: 'js/json/class_2_' + class_1_id + '.js',
                valueField: 'id',
                textField: 'flmch2',
                width: 80,
                value: '请选择',
                onSelect: function attr_get_attr_by_class_2() {
                    var class_2_id = $(this).combobox('getValue');
                    var class_2_name = $(this).combobox('getText');
                    $("#attr_list_inner_redis").datagrid({
                        url: 'get_attr_list.do',
                        queryParams: {class_2_id: class_2_id,class_2_name:class_2_name},
                        columns: [[
                            {field: 'id', title: 'id', width: 100, checkbox: true},
                            {field: 'shxm_mch', title: '属性名名称', width: 100},
                            {field: 'shfqy', title: '是否开启', width: 70},
                            {
                                field: 'chjshj', title: '创建时间', width: 150,
                                //value是要显示的值，对其进行自定义化的显示。
                                formatter: function (value) {
                                    //将时间格式化
                                    var datetime = new Date(value);
                                    var date = datetime.toLocaleString();
                                    return date;
                                }
                            },
                            {
                                field: 'list_value', title: '属性值', width: 400,
                                formatter: function (value) {
                                    //在浏览器的控制台打印出value，它是一个JS数组。
                                    console.log(value);
                                    var val = "";
                                    $(value).each(function (i, jsonObj) {
                                        val += " " + jsonObj.shxzh + jsonObj.shxzh_mch;
                                    });
                                    return val;
                                }
                            }
                        ]]
                    });
                }
            })
        }
    });


    function refresh_sku_by_class_2() {
        var class_2_id=$("#attr_class_2_select_redis").combobox("getValue");
        $.post("refresh_sku_by_class_2.do",{class_2_id:class_2_id},function (result) {
            alert("已更新Redis中"+result+"条数据")
        });
    }

    function refresh_sku_by_attrs() {
        var class_2_id=$("#attr_class_2_select_redis").combobox("getValue");
        var attr=$("#attr_list_inner_redis").datagrid("getChecked");
        var attr_array=new Array();
        $(attr).each(function (i, c) {
            attr_array[i]=c.id;
        });
        $.post("refresh_sku_by_attrs.do",{class_2_id:class_2_id,attr_array:attr_array},function (result) {
            alert("已更新Redis中"+result+"条数据")
        })
    }
</script>
</body>
</html>