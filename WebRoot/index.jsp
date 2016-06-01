<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>轻松购 - 首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
<%-- <link rel="stylesheet" 
	href="<%=request.getContextPath() %>/css/bootstrap.min.css" type="text/css"></link> --%>
<link rel="stylesheet" 
	href="<%=request.getContextPath() %>/css/global.css" type="text/css"></link>
<link rel="stylesheet" 
	href="<%=request.getContextPath() %>/css/layout.css" type="text/css"></link>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
<%-- <script type="text/javascript" 
	src="<%=request.getContextPath()%>/scripts/bootstrap.min.js"></script> --%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/function.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/photo.js"></script>
<script type="text/javascript">
	var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 首页";}
	setTimeout("title()",1000);
	}
	title();
	</script>
</head>
<body>
	<div class="block"></div>
	<c:if test="${shopps eq null || news eq null}">
		<script type="text/javascript">
			location.href = "<%=request.getContextPath()%>/indexServlet?action=first";
		</script>
	</c:if>
	<%-- <!--随滚动条滚动可关闭广告-->
	<div id="right" class="right">
		<div class="dd_close" id="dd_close">
			<a href="javascript:void(0);" style="color: orange">关闭</a>
		</div>
		<img src="<%=request.getContextPath()%>/images/dd_scroll.jpg"
			id="right1" />
	</div> --%>
	<%-- <div id="welcomeImage">
		<img width="100%" height="76"
			src="<%=request.getContextPath()%>/images/banners.jpg" alt="welcome" />
	</div> --%>
	<%@ include file="head.jsp"%>
	<div id="main" class="wrap">
		<%@ include file="left.jsp"%>
		<div class="main">
			<div class="price-off">
				<!--轮换显示的横幅广告图片-->
				<div class="scroll_top"></div>
				<div class="scroll_mid">
					<ul id="scroll_img">
						<li><a href="#"><img
							src="<%=request.getContextPath()%>/images/dd_scroll_1.jpg" /></a>
						</li>
						<li><a href="#"><img
							src="<%=request.getContextPath()%>/images/dd_scroll_2.jpg" /></a>
						</li>
						<li><a href="#"><img
							src="<%=request.getContextPath()%>/images/dd_scroll_3.jpg" /></a>
						</li>
						<li><a href="#"><img
							src="<%=request.getContextPath()%>/images/dd_scroll_4.jpg" /></a>
						</li>
						<li><a href="#"><img
							src="<%=request.getContextPath()%>/images/dd_scroll_5.jpg" /></a>
						</li>
						<li><a href="#"><img
							src="<%=request.getContextPath()%>/images/dd_scroll_6.jpg" /></a>
						</li>
					</ul>
					<ul id="scroll_number">
						<li class="scroll_number_over">1</li>
						<li class="scroll_number_over">2</li>
						<li class="scroll_number_over">3</li>
						<li class="scroll_number_over">4</li>
						<li class="scroll_number_over">5</li>
						<li class="scroll_number_over">6</li>
					</ul>
				</div>
				<div class="scroll_end"></div>
				<h2>${goodslisting }</h2>
				<ul class="product clearfix">
					<c:if test="${not empty shopps }">
						<c:forEach var="shopps" items="${shopps}">
							<li><!-- style="border:solid 1px orange;" -->
								<dl>
									<dt>
										<a href="productServlet?action=look&ep_id=${shopps.ep_Id }"
											target="_self"><img src="<%=request.getContextPath() %>/${shopps.ep_File_name }" title="${shopps.ep_Name}"/> </a>
									</dt>
									<dd style="padding-left: 20px;" class="title">
										<a href="productServlet?action=look&ep_id=${shopps.ep_Id }"
											target="_self">${shopps.ep_Name }</a>
									</dd>
									<dd class="price"><c:if test="${shopps.ep_Price gt 500}"><img title="包邮" src="<%=request.getContextPath() %>/images/gg.gif"/></c:if><c:if test="${shopps.ep_Id gt 112}"><img title="新品" src="<%=request.getContextPath() %>/images/ff.gif"/></c:if><c:if test="${shopps.ep_Sales gt 1500}"><img title="热销" src="<%=request.getContextPath() %>/images/hh.gif"/></c:if>&nbsp;￥${shopps.ep_Price }</dd>
									<dd class="sales"><a href="<%=request.getContextPath()%>/indexServlet?action=resemble&ep_name=${shopps.ep_Name }&ep_description=${shopps.ep_Description}&ep_price=${shopps.ep_Price}&ep_address=${shopps.ep_Address}" style="text-decoration: none;color: orange;">查看相似</a>&nbsp;<a style="color: gray;text-decoration: none;">销量:${shopps.ep_Sales }</a></dd>
									<dd class="address">产地:${shopps.ep_Address }</dd>
								</dl>
								<p style="border-bottom:dashed 1px #999"></p>
							</li>
						</c:forEach>
					</c:if>
				</ul>
				<div class="clear"></div>
				<%
					session.setAttribute("pages", "index");
				%>
				<%@ include file="page.jsp"%>
			</div>
			<div class="spacer"></div>
			<!--右侧部分开始-->
			<div class="side">
				<div class="news-list">
					<h4>
						<img src="<%=request.getContextPath()%>/images/dd_mess.gif"
							alt="News" style=" vertical-align:text-bottom;" />&nbsp${newstrends}<a href="<%=request.getContextPath() %>/NewsServlet?action=firsts" style="margin-left: 85px;color: #fc7e31;">更多</a>
					</h4>
					<ul id="express">
						<c:if test="${not empty news }">
							<c:forEach var="news" items="${news }">
								<li>
								<a href="<%=request.getContextPath() %>/indexServlet?action=news&en_id=${news.en_Id }"
									target="_self">${news.en_Title } <c:if
											test="${news.en_Id%2==0 }">
											<img src="<%=request.getContextPath()%>/images/hot_gif.gif"></img>
										</c:if> </a></li>
									<input type="hidden" name="en_click_count" id="en_click_count" value="${news.en_Click_Count }"/>
							</c:forEach>
						</c:if>
					</ul>
				</div>
				<div class="express_avder">
					<img src="<%=request.getContextPath()%>/images/dd_right_adver1.jpg"
						alt="adver" style="margin-bottom:2px;" /> <img
						src="<%=request.getContextPath()%>/images/dd_right_adver2.gif"
						alt="adver" />
				</div>
			</div>
    <div id="silder">
        <!--近7日畅销榜-->
        <div class="book_sort">
            <div class="book_seven_title">近7日畅销榜 <img src="<%=request.getContextPath() %>/images/dd_bang.gif" alt="bang" style="vertical-align:top;"/></div>
            <div class="book_seven_border">
                <div class="book_seven_top">
                    <ul id="book_seven_cate">
                        <li>促销</li>
                        <li>数码</li>
                        <li>百货</li>
                        <li>品牌</li>
                        <li>图书</li>
                    </ul>
                </div>
                <div class="book_seven_content">
                    <div class="book_seven_content_left">
                        <dl id="book_seven_number">
                            <dt><img src="<%=request.getContextPath() %>/images/dd_book_no_01.gif" alt="book"/></dt>
                            <dt><img src="<%=request.getContextPath() %>/images/dd_book_no_02.gif" alt="book"/></dt>
                            <dt><img src="<%=request.getContextPath() %>/images/dd_book_no_03.gif" alt="book"/></dt>
                            <dd><img src="<%=request.getContextPath() %>/images/dd_book_no_04.gif" alt="book"/></dd>
                            <dd><img src="<%=request.getContextPath() %>/images/dd_book_no_05.gif" alt="book"/></dd>
                            <dd><img src="<%=request.getContextPath() %>/images/dd_book_no_06.gif" alt="book"/></dd>
                            <dd><img src="<%=request.getContextPath() %>/images/dd_book_no_07.gif" alt="book"/></dd>
                            <dd><img src="<%=request.getContextPath() %>/images/dd_book_no_08.gif" alt="book"/></dd>
                        </dl>
                    </div>
                    <div class="book_seven_content_right">
                        <!--励志开始-->
                        <div id="book_seven_hearten">
                            <dl>
                                <dt><img src="<%=request.getContextPath() %>/images/dd_seven_hearten_01.jpg" alt="hearten" class="point" /></dt>
                                <dd>
                                    <a href="#" style="color:orange;">不抱怨的世界</a><br />
												                                    作者：（美）鲍温<br />
												                                    出版社：陕西师范<br />
												                                    出版时间：2009年
									<p style="border-bottom:dashed 1px #999"></p>
                                </dd>
                                <dt><img src="<%=request.getContextPath() %>/images/dd_seven_hearten_02.jpg" alt="hearten" class="point" /></dt>
                                <dd>
                                    <a href="#" style="color:orange;">遇见未知的自己</a><br />
												                                    作者：张德芬 <br />
												                                    出版社：华夏出版<br />
												                                    出版时间：2008年
									<p style="border-bottom:dashed 1px #999"></p>
                                </dd>
                                <dt><img src="<%=request.getContextPath() %>/images/dd_seven_hearten_03.jpg" alt="hearten" class="point" /></dt>
                                <dd>
                                    <a href="#" style="color:orange;">活法</a><br />
												                                    作者：（日）稻盛<br />
												                                    出版社：东方出版<br />
												                                    出版时间：2005年
									<p style="border-bottom:dashed 1px #999"></p>
                                </dd>
                            </dl>
                            <ul>
                                <li><a href="#" style="color:orange;">高效能人士的七个习惯</a></li>
                                <li><a href="#" style="color:orange;">被迫强大</a></li>
                                <li><a href="#" style="color:orange;">遇见心想事成的自己</a></li>
                                <li><a href="#" style="color:orange;">世界上最伟大的推销员</a></li>
                                <li><a href="#" style="color:orange;">我的成功可以复制</a></li>
                            </ul>
                        </div>
                        <!--结束-->
                    </div>
                </div>
            </div>
        </div>
    </div>
<!--右侧部分结束-->
</div>
		<div class="clear"></div>
</div>
<!-- 	<div id="daiyanbao_com_content" closerate="1" playrate="0" style="position: fixed;_position: absolute;text-align: left;overflow: visible;bottom :0px;left:0px;display:block; z-index:999;"><script src="//res.daiyanbao.com/freevideojs/hy2014_5/1/15139769986.js"></script></div>
 -->	
 	<%@ include file="aop.jsp"%>
	<%@ include file="qrcode.jsp" %>
	<%@ include file="services.jsp" %>
	<%@ include file="topbottom.jsp"%>
	<%@ include file="footer-y.jsp"%>
	<%
		session.removeAttribute("shopps");
		session.removeAttribute("news");
	%>
</body>
</html>
