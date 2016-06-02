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
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/css/style.css"></link>
<script type="text/javascript" 
	src="<%=request.getContextPath() %>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript">
	//分页跳转
  	function paging(cpage){
  		$("#cpage").val(cpage);
  		$("form").submit();
  	}
	function del(fid) {
		if (confirm("确定要删除吗？请谨慎删除哦!")) {
			location.href="<%=request.getContextPath()%>/documentServlet?action=upd&fid="+fid;
		}
	}
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
<c:if test="${param.message eq 1}">
	<script>
		alert("操作成功！");
	</script>
</c:if>
<c:if test="${param.message eq 0}">
	<script>
		alert("操作失败！");
	</script>
</c:if>
</head>
<body>
<%session.removeAttribute("fbool"); %>
<%session.removeAttribute("fobool"); %>
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
				<c:if test="${status eq 2 }"><li><a href="<%=request.getContextPath()%>/commentServlet?action=first&h=hou">留言</a></li>
				<li><a href="<%=request.getContextPath()%>/NewsServlet?action=first">新闻</a></li>
				<li class="current"><a href="<%=request.getContextPath()%>/documentServlet?action=first">文档</a></li>
				<li><a href="<%=request.getContextPath()%>/getAllUser?action=firsts">排行</a></li></c:if>
			</ul>
		</div>
	</div>
	<%@ include file="head.jsp"%>

	<div id="main" class="wrap">
		<%@ include file="left.jsp"%>
		<div class="main">
			<h2>文档管理<a style="color: red;text-decoration: none;margin-left: 390px;">温馨提示:请谨慎删除,删除后可在回收站恢复哦!</a></h2>
			<div class="manage">
			<form action="<%=request.getContextPath()%>/documentServlet?action=first" method="post">
			<table style="margin-left: -11px;"><tr align="right"><td>
  			<input type="hidden" name="cpage" id="cpage" value="${cpage}" />
  			文件夹名称: <input type="text" class="text" name="fname" id="fname" value="${fname}" placeholder="请输入文件夹名称(支持模糊查询)" autocomplete="off"/>
  			<label class="ui-blue"><input type="submit" class="point" value="搜索"/></label>
  			</td></tr></table>
  			</form>
				<table class="list">
					<tr>
						<th>文件夹名称</th>
						<th>创建人</th>
						<th><img src="<%=request.getContextPath() %>/images/date.gif"/>创建时间</th>
						<th>文件地址</th>
						<th><img src="<%=request.getContextPath() %>/images/date.gif"/>修改时间</th>
						<th colspan="2">操作</th>
					</tr>
					<c:if test="${not empty list }">
					<c:forEach var="documentlist" items="${list}">
						<tr>
							<td class="first w4 c">${documentlist.fname }</td>
							<td class="w1 c">${documentlist.fusername }</td>
							<td class="w2 c">${documentlist.ftime }</td>
							<td>${documentlist.fpath }</td>
							<td class="w2 c">${documentlist.fuptime }</td>
							<td class="w1 c"><label class="ui-blue"><a
									class="manageUpd" href="<%=request.getContextPath()%>/documentServlet?action=selectdocumentById&fid=${documentlist.fid}"> <input type="button"
										class="point" value="修改" /> </a> </label> <label class="ui-blue">
									<input type="button" value="删除" class="point"
									onclick="del(${documentlist.fid})" /> </label>
							</td>
						</tr>
					</c:forEach>
					<tr>
    		<td colspan="10" align="right">
    		当前是<a style="color: blue;text-decoration:none" >${cpage}</a>/<a style="color: blue;text-decoration:none" >${totalPage}</a>页   共<a style="color: blue;text-decoration:none" >${countd}</a>条
    		<a class="point" style="color: blue;" onclick="paging(1)">首页</a>
    		<a class="point" style="color: blue;" onclick="paging(${cpage==1?1:cpage-1})">上一页</a>
    		<a class="point" style="color: blue;" onclick="paging(${cpage==totalPage?(totalPage):cpage+1})">下一页</a>
    		<a class="point" style="color: blue;" onclick="paging(${totalPage})">尾页</a>
    		</td>
    	</tr>
    	</c:if>
				</table>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<%@ include file="/aop.jsp"%>
</body>
</html>