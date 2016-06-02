<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 轻松购</title>
<link type="text/css" rel="stylesheet" 
	href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript" 
	src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript" 
	src="<%=request.getContextPath()%>/scripts/function.js"></script>
<script type="text/javascript">
		var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 更多信息";}
	setTimeout("title()",1000);
	}
	title();
</script>
</head>
<body>
	<div id="header" class="wrap">
		<div id="logo">
			<img src="<%=request.getContextPath() %>/images/logo.gif" />
		</div>
		<div class="help">
		<img src="<%=request.getContextPath()%>/images/home_ico.gif"/><a href="<%=request.getContextPath() %>/index.jsp">返回前台页面</a>
		</div>
		<div class="navbar">
			<ul class="clearfix">
				<li><a href="<%=request.getContextPath() %>/manage/index.jsp">首页</a></li>
				<li><a href="<%=request.getContextPath()%>/getAllUser?action=first">用户</a></li>
				<c:if test="${status eq 2 }"><li><a href="<%=request.getContextPath() %>/product_servlet?action=first">商品</a></li></c:if>
				<li><a href="<%=request.getContextPath()%>/orderServlet?action=first">订单</a></li>
				<c:if test="${status eq 2 }"><li><a href="<%=request.getContextPath()%>/commentServlet?action=first&h=hou">留言</a></li>
				<li><a href="<%=request.getContextPath()%>/NewsServlet?action=first">新闻</a></li>
				<li><a href="<%=request.getContextPath()%>/documentServlet?action=first">文档</a></li>
				<li class="current"><a href="<%=request.getContextPath()%>/getAllUser?action=firsts">排行</a></li></c:if>
			</ul>
		</div>
	</div>
	<%@ include file="head.jsp"%>
	<div id="main" class="wrap">
		<%@ include file="left.jsp"%>
		<div class="main">
			<h2>更多信息</h2>
			<div class="manage">
					<c:if test="${more ne null }">
					<form id="product-modify" action="<%=request.getContextPath()%>/getAllUser?action=SelectMores&eu_User_id=${more.eu_User_id}" method="post">
				<table class="list">
					<input type="hidden" name="eu_User_id" id="eu_User_id" value="${more.eu_User_id }"/>
					<input type="hidden" name="eo_Cost" id="eo_Cost" value="${more.eo_Cost }"/>
					<input type="hidden" name="eo_Score" id="eo_Score" value="${more.eo_Score }"/>
					<tr>
						<th><img src="<%=request.getContextPath() %>/images/email.gif"/>Email邮箱</th>
						<th>收货地址</th>
						<th><img src="<%=request.getContextPath() %>/images/date.gif"/>创建时间</th>
						<th>操作</th>
					</tr>
					<tr>
							<td class="first w4 c"><input type="text" id="eu_Email" style="border: none;background-color: white;"
									name="eu_Email" value="${more.eu_Email}" readonly="readonly"/></td>
							<td class="w1 c"><input type="text" id="eu_Address" style="border: none;background-color: white;"
									name="eu_Address" value="${more.eu_Address }" readonly="readonly"/></td>
							<td class="w2 c"><input type="text" id="eu_Create_time"
									name="eu_Create_time" style="border: none;background-color: white;" value="${more.eu_Create_time }" readonly="readonly"/></td>
						<td class="w1 c"><label class="ui-blue"><input type="submit" class="point" value="确定""/></label></td>
    				</tr>
			</table>
    		</c:if>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<%@ include file="/aop.jsp"%>
</body>
</html>
