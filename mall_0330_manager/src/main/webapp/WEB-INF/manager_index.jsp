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
    <title>后台管理系统</title>
    <script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="/js/easyui/jquery.easyui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/easyui/themes/icon.css">
</head>
<body class="easyui-layout">


    <div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;"><h2>电商网站后台管理系统</h2></div>
    <div data-options="region:'west',split:true,title:'West'" style="width:150px;padding:10px;">
        <ul class="easyui-tree">
            <li>
                <span>系统管理</span>
                <ul>
                    <li><a href="javascript:index_add_tabs('goto_spu.htm','spu信息')" style="text-decoration:none;">spu信息</a></li>
                    <li><a href="javascript:index_add_tabs('goto_attr.htm','分类属性')" style="text-decoration:none;">分类属性</a></li>
                    <li><a href="javascript:index_add_tabs('goto_sku.htm','库存信息')" style="text-decoration:none;">库存信息</a></li>
                    <li>
                        <span>接口管理</span>
                        <ul>
                            <li>仓库管理</li>
                            <li>物流管理</li>
                            <li><a href="javascript:;" onclick="index_add_tabs()">测试管理</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li>
                <span>缓存管理</span>
                <ul>
                    <li><a href="javascript:index_add_tabs('goto_redis.htm','商品缓存')">商品缓存</a></li>
                    <li>用户缓存</li>
                </ul>
            </li>
        </ul>

    </div>
    <div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">其他信息</div>
    <div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">@Copyright</div>
    <div data-options="region:'center',title:'Center',iconCls:'icon-ok'">
        <div id="index_tt" class="easyui-tabs">

        </div>
    </div>
</body>

<script type="text/javascript">
    $(function () {
        var url="${init_url}";
        var title="${init_title}";
        title=decodeURIComponent(title);
        if(url!="" && title!=""){
            index_add_tabs(url,title);
        }
    });

    /*打开tab的另一种方式
      function index_add_tabs(url,title) {
            $.post(url,function (data) {
                $("#index_tt").tabs('add',{
                    title:title,
                    href:data,
                    closable:true
                })
            });
        }*/
    function index_add_tabs(url,title) {
        var isExist = $("#index_tt").tabs('exists',title);
        if(isExist) {
            $("#index_tt").tabs('select',title);
        }else{
            $("#index_tt").tabs('add', {
                title: title,
                href: url,
                closable: true
            })
        }
    }
</script>
</html>
