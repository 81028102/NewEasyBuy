<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	if(n==2){document.title="轻松购 - 后台管理";}
	setTimeout("title()",1000);
	}
	title();
	</script>
	</head>
	<body>
	<div id="header" class="wrap">
		<div id="logo"><img src="<%=request.getContextPath() %>/images/logo.gif" /></div>
		<div class="help"><img src="<%=request.getContextPath()%>/images/home_ico.gif"/><a href="<%=request.getContextPath()%>/index.jsp">${Returntothefrontdeskpage}</a></div>
		<div class="navbar">
			<ul class="clearfix">
				<li class="current"><a href="<%=request.getContextPath() %>/manage/index.jsp">${Homes}</a></li>
				<li><a href="<%=request.getContextPath()%>/getAllUser?action=first">${Theuser}</a></li>
				<c:if test="${status eq 2 }"><li><a href="<%=request.getContextPath()%>/product_servlet?action=first">${Goods}</a></li></c:if>
				<li><a href="<%=request.getContextPath()%>/orderServlet?action=first">${Order }</a></li>
				<c:if test="${status eq 2 }"><li><a href="<%=request.getContextPath()%>/commentServlet?action=first&h=hou">${Message }</a></li>
				<li><a href="<%=request.getContextPath()%>/NewsServlet?action=first">${News}</a></li>
				<li><a href="<%=request.getContextPath()%>/documentServlet?action=first">文档</a></li>
				<li><a href="<%=request.getContextPath()%>/getAllUser?action=firsts">排行</a></li></c:if>
			</ul>
		</div>
	</div>
	<%@ include file="head.jsp" %>
	<div id="main" class="wrap">
		<%@ include file="left.jsp" %>
		<div class="main">
			<h2>${Managementofthehomepage}</h2>
			<div id="welcome" class="manage">
			<br/><br/><br/><br/>
				<div class="shadow">
					<em class="corner lb"></em>
					<em class="corner rt"></em>
					<div class="box">
						<div class="msg">
							<p><img src="<%=request.getContextPath()%>/images/home_ico.gif"/>&nbsp;&nbsp;${Welcomebacktomanagethehomepage}</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<%@ include file="/aop.jsp"%>
	<%@ include file="/footer-n.jsp"%>
	</body>
	</html>
