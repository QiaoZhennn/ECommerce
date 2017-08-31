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
<select class="easyui-combobox" name="flbh1" id="attr_class_1_select" ></select>
<select class="easyui-combobox" name="flbh2" id="attr_class_2_select" >
    <option>请选择</option>
</select>
<div id="attr_list_inner"></div>
<br>
<div id="add_new_attr_area"></div>

<%--<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>--%>
<script type="text/javascript">
    $.getJSON("js/json/class_1.js", function (data) {
        /*$(data).each(function (i, json) {
            $("#attr_class_1_select").append("<option value=" + json.id + ">" + json.flmch1 + "</option>");
        });*/

        $("#attr_class_1_select").combobox({
            url:'js/json/class_1.js',
            valueField:'id',
            textField:'flmch1',
            width:80,
            value:'请选择',
            onSelect:function attr_get_class_2_by_class_1() {
                var class_1_id=$(this).combobox('getValue');
                $("#attr_class_2_select").combobox({
                    url:"js/json/class_2_" + class_1_id + ".js",
                    valueField:'id',
                    textField:'flmch2',
                    width:100,
                    value:'请选择',
                    onSelect:function attr_get_attr_by_class_2() {
                        var class_2_id=$(this).combobox('getValue');
//                        var class_2_name=$("#attr_class_2_select :selected").text();
                        var class_2_name=$(this).combobox('getText');
                        var encodeStr=encodeURI(class_2_name);
                        var anotherStr=encodeStr.replace(/%/g,'_');
                        var newStr=decodeURIComponent(encodeStr);
                        $.post("get_attr_by_class_2.do",{class_2_id:class_2_id,class_2_name:class_2_name},function (data) {
                            $("#attr_list_inner").html(data);
                            var a='<a href="javascript:index_add_tabs(\'goto_add_attr.htm?class_2_id='+class_2_id+'&class_2_name='+anotherStr+'\',\'添加分类属性\')">添加分类属性</a>';
                            $("#add_new_attr_area").html(a);
                        });

                        $('#attr_list_inner').datagrid({
                            url:'get_attr_by_class_2_json.do', //向服务器发请求，服务器须返回一个集合对象，它自动被转为json。
                            queryParams:{class_2_id:class_2_id}, //向请求传递的参数
                            //field:显示何属性的值，title：列名。
                            columns:[[
                                {field:'shxm_mch',title:'属性名',width:100},
                                {field:'shfqy',title:'是否开启',width:70},
                                {field:'chjshj',title:'创建时间',width:150,
                                    //value是要显示的值，对其进行自定义化的显示。
                                    formatter:function (value) {
                                        //将时间格式化
                                        var datetime=new Date(value);
                                        var date=datetime.toLocaleString();
                                        return date;
                                    }},
                                {field:'list_value',title:'属性值',width:400,
                                    formatter:function (value) {
                                    //在浏览器的控制台打印出value，它是一个JS数组。
                                    console.log(value);
                                    var val="";
                                        $(value).each(function (i, jsonObj) {
                                            val+=" "+jsonObj.shxzh+jsonObj.shxzh_mch;
                                        });
                                        return val;
                                    }}
                            ]]
                        });
                    }
                });
            }
        });
    });

</script>
</body>

</html>