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
    <title>轻松购 - 商品</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/function.js"></script>
	<script type="text/javascript">
	var n=0;
	function title(){
	n++;
	if(n==3){n=1;};
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 商品展示";}
	setTimeout("title()",1000);
	}
	title();
	</script>
  </head>
  
  <body>
   <%@ include file="head.jsp" %>
	<div id="position" class="wrap">
		<img src="<%=request.getContextPath() %>/images/addScript_ico.gif"/>&nbsp;${Yourpresentposition}:<a href="index.jsp">${easybuy}</a> &gt;<c:if test="${epc ne '' }"> <a href="indexServlet?action=category&category=one&epc_id=${id}&epc_name=${epc}">${epc}</a> &gt;</c:if> ${name}
	</div>
	<div id="main" class="wrap">
	<%@ include file="left.jsp" %>
		<div class="main">
			<div class="product-list">
							<iframe src="http://tianqi.5ikfc.com:90/plugin-code/?style=3&dy=7" allowTransparency="true" frameborder="0" scrolling="no" width="350" height="40" id="weather_frame" align="right"></iframe>
				<h2>${Allthegoods}</h2>			
				<div class="clear"></div>
				<ul class="product clearfix">
					<c:if test="${not empty product }">
					<c:forEach var="pro" items="${product}">
					<li>
						<dl>
							<dt><a href="<%=request.getContextPath()%>/productServlet?action=look&ep_id=${pro.ep_Id }" target="_self"><img src="${pro.ep_File_name }" /></a></dt>
							<dd style="padding-left: 40px;" class="title"><a href="<%=request.getContextPath()%>/productServlet?action=look&ep_id=${pro.ep_Id }" target="_self">${pro.ep_Name }</a></dd>
							<dd style="padding-left: 15px;" class="price"><c:if test="${pro.ep_Price gt 500}"><img title="包邮" src="<%=request.getContextPath() %>/images/gg.gif"/></c:if><c:if test="${pro.ep_Id gt 105}"><img title="新品" src="<%=request.getContextPath() %>/images/ff.gif"/></c:if><c:if test="${pro.ep_Sales gt 1500}"><img title="热销" src="<%=request.getContextPath() %>/images/hh.gif"/></c:if>￥${pro.ep_Price}</dd>
							<%-- <dd class="address">产地:${pro.ep_Address}</dd> --%>
							<p style="border-bottom:dashed 1px #999"></p>
						</dl>
					</li>
					</c:forEach>
					</c:if>
				</ul>
				<div class="clear"></div>
				<%@ include file="listPage.jsp" %>
			</div>
		</div>
		<div class="clear"></div>
		<%@ include file="mvc.jsp"%>
		<%@ include file="aop.jsp"%>
		<%@ include file="services.jsp" %>
		<%@ include file="qrcode.jsp" %>
		<%@ include file="topbottom.jsp" %>
	</div>
  </body>
</html>
