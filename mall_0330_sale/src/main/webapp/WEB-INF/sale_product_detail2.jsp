<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/css.css">
    <title>Title</title>
</head>
<body>
<div class="top">
    <div class="top_text">
        <a href="">用户登录</a>
        <a href="">用户注册</a>
        <a href="">供应商登录</a>
        <a href="">供应商注册</a>
    </div>
</div>
<!--<div class="top_img">
    <img src="./images/top_img.jpg" alt="">
</div>-->
<div class="search searchBac">
    <div class="logo"><img src="/images/logo.png" alt=""></div>
    <!--<div class="search_on">
        <div class="se">
            <input type="text" name="search" class="lf">
            <input type="submit" class="clik" value="搜索">
        </div>
        <div class="se">
            <a href="">取暖神奇</a>
            <a href="">1元秒杀</a>
            <a href="">吹风机</a>
            <a href="">玉兰油</a>
        </div>
    </div>-->
    <div class="card">
        <a href="">购物车<div class="num">0</div></a>

        <!--购物车商品-->
        <div class="cart_pro">
            <h6>最新加入的商品</h6>
            <div class="one">
                <img src="/images/lec1.png"/>
                <span class="one_name">
						商品名称： ${sku.sku_mch}
					</span>
                <span class="one_prece">
						<b>￥${sku.jg}</b><br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除
					</span>
            </div>

            <div class="one border">
                <img src="/images/lec1.png"/>
                <span class="one_name">
						商品名称商品名称商品名称商品名称
					</span>
                <span class="one_prece">
						<b>￥20000</b><br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除
					</span>
            </div>
            <div class="one border">
                <img src="/images/lec1.png"/>
                <span class="one_name">
						商品名称商品名称商品名称商品名称
					</span>
                <span class="one_prece">
						<b>￥20000</b><br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除
					</span>
            </div>


            <div class="gobottom">
                共<span>2</span>件商品&nbsp;&nbsp;&nbsp;&nbsp;
                共计￥<span>20000</span>
                <button class="goprice">去购物车</button>
            </div>


        </div>

    </div>
</div>
<div class="menu">
    <div class="nav">
        <div class="navs">
            <div class="left_nav">
                全部商品分类
                <div class="nav_mini" style="display:none">
                    <ul>
                        <li>
                            <a href="javascript:;">家用电器</a>
                            <div class="two_nav">
                                <a href="javascript:;">11111</a>
                                <a href="javascript:;">11111</a>
                                <a href="javascript:;">11111</a>
                                <a href="javascript:;">11111</a>
                                <a href="javascript:;">11111</a>
                                <a href="javascript:;">11111</a>
                                <a href="javascript:;">11111</a>
                                <a href="javascript:;">11111</a>
                                <a href="javascript:;">11111</a>
                                <a href="javascript:;">11111</a>
                                <a href="javascript:;">11111</a>
                            </div>
                        </li>
                        <li><a href="javascript:;">手机、数码、通信</a></li>
                        <li><a href="javascript:;">电脑、办公</a></li>
                        <li><a href="javascript:;">家具、家居、家装</a></li>
                        <li><a href="javascript:;">男装、女装、内衣</a></li>
                        <li><a href="javascript:;">个户化妆</a></li>
                        <li><a href="javascript:;">鞋靴</a></li>
                        <li><a href="javascript:;">户外运动</a></li>
                        <li><a href="javascript:;">汽车</a></li>
                        <li><a href="javascript:;">母婴</a></li>
                        <li><a href="javascript:;">食品</a></li>
                        <li><a href="javascript:;">营养保健</a></li>
                        <li><a href="javascript:;">图书</a></li>
                        <li><a href="javascript:;">彩票</a></li>
                        <li><a href="javascript:;">理财</a></li>
                    </ul>
                </div>
            </div>
            <ul>
                <li><a href="javascript:;">服装城</a></li>
                <li><a href="javascript:;">美妆馆</a></li>
                <li><a href="javascript:;">超市</a></li>
                <li><a href="javascript:;">全球购</a></li>
                <li><a href="javascript:;">闪购</a></li>
                <li><a href="javascript:;">团购</a></li>
                <li><a href="javascript:;">拍卖</a></li>
                <li><a href="javascript:;">金融</a></li>
                <li><a href="javascript:;">智能</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="Dbox">
    <div class="box">
        <div class="left">
            <div class="timg"><img src="/upload/image/${sku.spu.shp_tp}" alt="" style="width: 320px"></div>
            <div class="timg2">
                <div class="lf"><img src="/images/lf.jpg" alt=""></div>
                <div class="center">
                    <span><img src="/images/icon_2.jpg" alt=""></span>
                    <span><img src="/images/icon_3.jpg" alt=""></span>
                    <span><img src="/images/icon_2.jpg" alt=""></span>
                    <span><img src="/images/icon_3.jpg" alt=""></span>
                    <span><img src="/images/icon_2.jpg" alt=""></span>
                </div>
                <div class="rg"><img src="/images/rg.jpg" alt=""></div>
            </div>
            <div class="goods"><img src="/images/img_6.jpg" alt=""></div>
        </div>
        <div class="cent">
            <div class="title">${sku.sku_mch}</div>
            <div class="bg">
                <p>市场价：<strong>￥${sku.jg}</strong></p>
                <p>促销价：<strong>￥${sku.jg/2}</strong></p>
            </div>
            <div class="clear">
                <div class="min_t">选择版本：</div>
                <div class="min_mx" onclick=func($(this),'0')>55英寸活动中1</div>
                <div class="min_mx" onclick=func($(this),'0')>55英寸活动中2</div>
                <div class="min_mx" onclick=func($(this),'0')>55英寸活动中3</div>
                <div class="min_mx" onclick=func($(this),'0')>55英寸活动中4</div>
                <div class="min_mx" onclick=func($(this),'0')>55英寸活动中5</div>
                <div class="min_mx" onclick=func($(this),'0')>55英寸活动中6</div>
            </div>
            <div class="clear">
                <div class="min_t" onclick=func($(this),'1')>服务：</div>
                <div class="min_mx" onclick=func($(this),'1')>服务1号1</div>
                <div class="min_mx" onclick=func($(this),'1')>服务二号1112</div>
                <div class="min_mx" onclick=func($(this),'1')>55英服务二号1111寸活动中3</div>
                <div class="min_mx" onclick=func($(this),'1')>4</div>
                <div class="min_mx" onclick=func($(this),'1')>呃呃呃5</div>
                <div class="min_mx" onclick=func($(this),'1')>55英寸活动中6</div>
            </div>
            <div class="clear" style="margin-top:20px;">
                <div class="min_t" style="line-height:36px">数量：</div>
                <div class="num_box">
                    <input type="text" name="num" value="1" style="width:40px;height:32px;text-align:center;float:left">
                    <div class="rg">
                        <img src="/images/jia.jpg" id='jia' alt="">
                        <img src="/images/jian.jpg" id='jian' alt="">
                    </div>
                </div>
            </div>
            <div class="clear" style="margin-top:20px;">
                <img src="/images/mai.jpg" alt="立即购买">
                <form id="add_cart_form" action="/add_cart.do" method="post">
                    <input type="hidden" name="sku_mch" value="">
                    <input type="hidden" name="sku_jg" value="">
                    <input type="hidden" name="tjshl" value="">
                    <input type="hidden" name="hj" value="">
                    <input type="hidden" name="shp_id" value="">
                    <input type="hidden" name="sku_id" value="">
                    <input type="hidden" name="shp_tp" value="">
                    <input type="hidden" name="shfxz" value="1"> <%--是否选中--%>
                    <input type="hidden" name="kcdz" value="">
                    <img style="cursor: pointer" onclick="add_cart()" src="/images/shop.jpg" alt="添加购物车">
                </form>
            </div>
        </div>
    </div>
</div>
<div class="Dbox1">
    <div class="boxbottom">
        <div class="top">
            <span>商品详情</span>
            <span>评价</span>
        </div>
        <div class="btm">
            我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我我
        </div>
    </div>
</div>
<div class="footer">
    <div class="top"></div>
    <div class="bottom"><img src="/images/foot.jpg" alt=""></div>
</div>

<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function add_cart() {
    $("#add_cart_form").submit();
}
</script>
</body>
</html>