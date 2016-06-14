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
//分页跳转
  	function paging(cpage){
  		$("#cpage").val(cpage);
  		$("form").submit();
  	}
		var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 销量排行";}
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
		<img src="<%=request.getContextPath()%>/images/home_ico.gif"/><a href="<%=request.getContextPath()%>/index.jsp">返回前台页面</a>
		</div>
		<div class="navbar">
			<ul class="clearfix">
				<li><a href="<%=request.getContextPath()%>/manage/index.jsp">首页</a></li>
				<li><a href="<%=request.getContextPath()%>/getAllUser?action=first">用户</a></li>
				<c:if test="${status eq 2 }"><li><a href="<%=request.getContextPath()%>/product_servlet?action=first">商品</a></li></c:if>
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
			<h2>销量排行信息</h2>
			<div class="manage">
			<c:if test="${status eq 2 }">
			<form action="<%=request.getContextPath()%>/product_servlet?action=firsts" method="post">
			<table style="margin-left: -11px;"><tr align="right"><td>
  			<input type="hidden" name="cpage" id="cpage" value="${cpage}" />
  			商品名称: <input type="text" class="text" name="ep_name" id="ep_name" value="${ep_name}" placeholder="请输入商品名称(支持模糊查询)" autocomplete="off"/>
  			<label class="ui-blue"><input type="submit" class="point" value="搜索"/></label>
  			</td></tr></table>
  			</form>
  			</c:if>
				<table class="list">
					<tr>
						<th>商品ID</th>
						<th>商品名称</th>
						<th>商品单价</th>
						<th>原产地</th>
						<th>商品销量</th>
					</tr>
					<c:if test="${not empty nlist }">
					<c:forEach var="nlist" items="${nlist }">
						<tr>
							<td style="text-align:center;">${nlist.ep_Id}</td>
							<td class="thumb"><a href="#"><img src="<%=request.getContextPath() %>/${nlist.ep_File_name}" alt="${nlist.ep_Name}" title="${nlist.ep_Name}"/></a><a
								href="<%=request.getContextPath()%>/productServlet?action=look&ep_id=${nlist.ep_Id }"
								target="_blank">${nlist.ep_Name}</a></td>
							<td style="text-align:center;"><a style="color:red;text-decoration:none;">￥:${nlist.ep_Price }</a></td>
							<td>${nlist.ep_Address }</td>
							<td class="w1 c"><a style="color: red;text-decoration: none;">${nlist.ep_Sales}件</a></td>
						</tr>
					</c:forEach>
				<tr>
	    		<td colspan="10" align="right">
	    		当前是<a style="color: blue;text-decoration:none" >${cpage}</a>/<a style="color: blue;text-decoration:none" >${totalPage}</a>页   共<a style="color: blue;text-decoration:none" >${countp}</a>条
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
	<%@ include file="/mvc.jsp"%>
	<%@ include file="/aop.jsp"%>
</body>
</html>
