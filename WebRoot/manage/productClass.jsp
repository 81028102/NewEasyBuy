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
	if(n==2){document.title="轻松购 - 商品分类管理";}
	setTimeout("title()",1000);
	}
	title();
</script>
</head>
<body>
<%session.removeAttribute("amessage"); %>
<%session.removeAttribute("sbs"); %>
<%session.removeAttribute("abool"); %>
	<c:if test="${cateOne eq null || cateTwo eq null }">
		<script>
			location.href = "<%=request.getContextPath() %>/CategoryServlet?action=all";
		</script>
	</c:if>
	<div id="header" class="wrap">
		<div id="logo">
			<img src="<%=request.getContextPath() %>/images/logo.gif" />
		</div>
		<div class="help">
		<img src="<%=request.getContextPath()%>/images/home_ico.gif"/><a href="<%=request.getContextPath()%>/index.jsp">返回前台页面</a>
		</div>
		<div class="navbar">
			<ul class="clearfix">
				<li><a href="<%=request.getContextPath()%>/manage/index.jsp">首页</a></li>
				<li><a href="<%=request.getContextPath()%>/getAllUser?action=first">用户</a></li>
				<c:if test="${status eq 2 }"><li class="current"><a href="<%=request.getContextPath()%>/product_servlet?action=first">商品</a></li></c:if>
				<li><a href="<%=request.getContextPath()%>/orderServlet?action=first">订单</a></li>
				<c:if test="${status eq 2 }"><li><a href="<%=request.getContextPath()%>/manage/guestbook.jsp">留言</a></li>
				<li><a href="<%=request.getContextPath()%>/manage/news.jsp">新闻</a></li>
				<li><a href="<%=request.getContextPath()%>/documentServlet?action=first">文档</a></li>
				<li><a href="<%=request.getContextPath()%>/getAllUser?action=firsts">排行</a></li></c:if>
			</ul>
		</div>
	</div>
	<%@ include file="head.jsp"%>
	<div id="main" class="wrap">
		<%@ include file="left.jsp"%>
		<div class="main">
			<h2>分类管理<a style="color: red;text-decoration: none;margin-left: 410px;">温馨提示:请谨慎删除,删除后不可恢复哦!</a></h2>
			<div class="manage">
				<table class="list">
					<tr>
						<th>分类ID</th>
						<th>分类名称</th>
						<th>操作</th>
					</tr>
					<c:forEach var="one" items="${cateOne }">
						<tr>
							<td class="first w4 c"><a style="color:red;text-decoration:none;">${one.epc_Id }</a></td>
							<td><a style="color:red;text-decoration:none;">${one.epc_Name }</a></td>
							<td class="w1 c"><label class="ui-blue"><a class="manageUpd"
								href="<%=request.getContextPath()%>/CategoryServlet?action=update&epc_id=${one.epc_Id }"><input type="button" class="point" value="修改"/></a></label>
								<label class="ui-blue"><a class="manageDel"
								href="<%=request.getContextPath()%>/CategoryServlet?action=del&epc_id=${one.epc_Id }"><input type="button" class="point" value="删除"/></a></label>
							</td>
						</tr>
						<c:forEach var="two" items="${cateTwo }">
							<c:if test="${two.epc_Parent_id eq one.epc_Id }">
								<tr>
									<td class="first w4 c">${two.epc_Id }</td>
									<td class="childClass">${two.epc_Name }</td>
									<td class="w1 c"><label class="ui-blue">
									<a  class="manageUpd"  href="<%=request.getContextPath()%>/CategoryServlet?action=update&epc_id=${two.epc_Id }"><input type="button" class="point" value="修改"/></a></label>
								<label class="ui-blue">	<a class="manageDel" href="<%=request.getContextPath()%>/CategoryServlet?action=del&epc_id=${two.epc_Id }"><input type="button" class="point" value="删除"/></a></label>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<%@ include file="/aop.jsp"%>
	<%@ include file="/topbottom.jsp"%>
	<%
		session.removeAttribute("cateOne");
		session.removeAttribute("cateTwo");
	%>
</body>
</html>
