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
$(function(){
 $.ajax({
 		async:false,
   	 	url:"<%=request.getContextPath()%>/NewsServlet?action=toAdd1",
    	type:"post",
    	dataType:"json",
   		success:function(jsont){
    	//alert(jsont);
    	for(var i in jsont){
    	$("[name='ent_id']").append("<option value='"+jsont[i].ent_Id+"'>"+jsont[i].ent_Name+"</option");
    	}
      }
	});
	$.ajax({
   	 	url:"<%=request.getContextPath()%>/NewsServlet?action=toAdd2",
    	type:"post",
    	dataType:"json",
   		success:function(jsonc){
    	//alert(jsonc);
    	for(var i in jsonc){
    	$("[name='enc_id']").append("<option value='"+jsonc[i].enc_Id+"'>"+jsonc[i].enc_Name+"</option");
    	}
      }
	});
});
</script>
<script type="text/javascript">
		var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 新闻管理";}
	setTimeout("title()",1000);
	}
	title();
	$(function(){
	var text=$("textarea").val();
	var counter=text.length;
	$("#numtj var").text(300-counter);
	$(document).keyup(function() {
		var text=$("textarea").val();
		var counter=text.length;
		$("#numtj var").text(300-counter);
	});
	});
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
					<li class="current"><a href="<%=request.getContextPath()%>/manage/news.jsp">新闻</a></li>
					<li><a href="<%=request.getContextPath()%>/documentServlet?action=first">文档</a></li>
					<li><a href="<%=request.getContextPath()%>/getAllUser?action=firsts">排行</a></li></c:if>
			</ul>
		</div>
	</div>
	<%@ include file="head.jsp"%>
	<div id="main" class="wrap">
		<%@ include file="left.jsp"%>
		<div class="main">
			<h2>新增新闻</h2>
			<div class="manage">
				<form id="news-add"
					action="<%=request.getContextPath()%>/NewsServlet?action=add"
					method="post" onsubmit="return check()">
					<table class="form">
						<tr>
							<td class="field">新闻标题:</td>
							<td><input type="text" class="text" id="title" name="title"
								value="" placeholder="请输入新闻标题" autocomplete="off" />
							</td>
						</tr>
						<tr>
							<td class="field"><img
								src="<%=request.getContextPath()%>/images/write.gif" />新闻内容:</td>
							<td>
								<p id="numtj" align="right">
									<img src="<%=request.getContextPath()%>/images/write.gif"
										style="padding-top: 5px;"></img>你还可以输入
									<var style="color:#C00">--</var>
									个字。
								</p>
								<textarea id="content" class="text" name="content"
									maxlength="300" style="width:605px;height:300px;"
									placeholder="请输入新闻内容" autocomplete="off"></textarea><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">分类名称:</td>
							<td><select name="ent_id" id="ent_id">
									<option value="">——请选择——</option>
								</select></td>
						</tr>
						<tr>
							<td class="field">栏目名称:</td>
							<td><select name=enc_id " id="enc_id">
									<option value="">——请选择——</option>
								</select></td>
						</tr>
						<tr>
							<td></td>
							<td><label class="ui-blue"><input type="submit"
									class="point" name="submit" value="添加新闻" /> </label> <label class="ui-blue"><input type="reset" class="point" value="清空"/></label> <label
								class="ui-blue"><a
									href="<%=request.getContextPath()%>/NewsServlet?action=first"><input
										type="button" name="button" class="point" value="返回" /> </a> </label>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="clear"></div>

	</div>
	<%@ include file="/aop.jsp"%>
</body>
</html>
