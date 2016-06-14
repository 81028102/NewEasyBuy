<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" 
	href="<%=request.getContextPath() %>/css/style.css" type="text/css"></link>
<script type="text/javascript" 
	src="<%=request.getContextPath() %>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript" 
	src="<%=request.getContextPath() %>/scripts/function.js"></script>
<script type="text/javascript">
/* 	//验证
	function check() {
		var fname = $("#fname").val();
		if (fname == "") {
			alert("文件夹名不能为空");
			$("#fname").focus();
			return false;
		}
 
		 var userrname=$("#userrname").val();
		if(userrname==""){
			alert("创建人不能为空");
			$("#userrname").focus();
			return false;
			} 

		return true;
	}
	$(function() {
		//验证
		$("#myform").submit(function() {
			return check();
		});
	});

	$(function() {
		//获取当前时间
		var d = new Date();
		var str = d.getFullYear() + "-" + (d.getMonth() + 1) + "-"
				+ d.getDate();
		var lfime = "<label>"
				+ str
				+ "</label>"
				+ " <input type=\"hidden\" name=\"folder.ftime\" value=\""+str+"\"/>";
		$("#ftime").append(lfime);
	}); */
		var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 文档管理";}
	setTimeout("title()",1000);
	}
	title();
</script>
</head>
<body>
	<div id="header" class="wrap">
		<div id="logo">
			<img src="<%=request.getContextPath()%>/images/logo.gif" />
		</div>
		<div class="help">
			<img src="<%=request.getContextPath()%>/images/home_ico.gif" /><a
				href="<%=request.getContextPath()%>/index.jsp">返回前台页面</a>
		</div>
		<div class="navbar">
			<ul class="clearfix">
				<li><a href="<%=request.getContextPath()%>/manage/index.jsp">首页</a></li>
				<li><a href="<%=request.getContextPath()%>/getAllUser?action=first">用户</a></li>
				<c:if test="${status eq 2 }"><li><a href="<%=request.getContextPath()%>/product_servlet?action=first">商品</a></li></c:if>
				<li><a href="<%=request.getContextPath()%>/orderServlet?action=first">订单</a></li>
				<c:if test="${status eq 2 }"><li><a href="<%=request.getContextPath()%>/manage/guestbook.jsp">留言</a></li>
				<li><a href="<%=request.getContextPath()%>/manage/news.jsp">新闻</a></li>
				<li class="current"><a href="<%=request.getContextPath()%>/documentServlet?action=first">文档</a></li>
				<li><a href="<%=request.getContextPath()%>/getAllUser?action=firsts">排行</a></li></c:if>
			</ul>
		</div>
	</div>
	<%@ include file="head.jsp"%>
	<div id="main" class="wrap">
		<%@ include file="left.jsp"%>
		<div class="main">
			<h2>新增文档</h2>
			<div class="manage">
				<form id="document" action="<%=request.getContextPath()%>/documentServlet?action=add"
					method="post">
					<table class="form">
						<tr>
							<td class="field">文件夹名称:</td>
							<td><input type="text" class="text" id="fname"
								name="newfname" placeholder="例文件夹名:学习资料" autocomplete="off"/><span></span></td>
						</tr>
						<tr>
							<td class="field">创建人:</td>
							<td><input type="text" class="text" name="fusername" id="fuserrname" placeholder="请输入创建人" autocomplete="off"/>
							<span></span></td>
						</tr>
						<tr>
							<td class="field">备注信息:</td>
							<td><input type="text" class="text" id="fremark" name="fremark" placeholder="请输入备注信息" autocomplete="off">
							<span></span></td>
						</tr>
						<tr>
							<td></td>
							<td><label class="ui-blue"> <input type="submit"
									class="point" name="submit" value="添加文件夹" /> </label> <label class="ui-blue"><input type="reset" class="point" value="清空"/></label> <label
								class="ui-blue"><a
									href="<%=request.getContextPath()%>/documentServlet?action=first"><input
										type="button" class="point" name="button" value="返回" /> </a> </label></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<%@ include file="/mvc.jsp"%>
	<%@ include file="/aop.jsp"%>
		<c:if test="${fobool ne null }">
		<script>
		alert("${fobool }");
		</script>
		</c:if>
</body>
</html>