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
		//反选
		$("#fx").click(function(){
		  $(".cks").each(function(){
	    if($(this).attr("checked")){
		  $(this).attr("checked",false);
	    }else{
	      $(this).attr("checked",true);
	    }
	    });
	  });
	  //全选/全不选
	  $("#x").toggle(function(){
	    $(".cks").attr("checked",true);
	  },
	  function(){
	   $(".cks").attr("checked",false);
	  });
	  //checked按钮
	   $("#ck").click(function(){
  			if($("#ck").attr("checked")){
  				$(".cks").attr("checked",true);
  			}else{
  				$(".cks").attr("checked",false);
  			}
  		});
  	//批量删除
	$("#delete").click(function(){
		var id="";
		$(".cks:checked").each(function(){
			id+=","+$(this).val();
		});
		ep_Id=id.substring(1);
		if(ep_Id!=""){
			if(confirm("确定要删除吗?请谨慎删除哦!")){
				location.href="<%=request.getContextPath() %>/product_servlet?action=del&id="+ep_Id;
			}
		}else{
				alert("对不起,请指定删除!");
		}
		});
	});
		var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 商品管理";}
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
<c:if test="${list eq null}">
	<script>
		location.href = "<%=request.getContextPath()%>/product_servlet?action=first";
	</script>
</c:if>
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
				<c:if test="${status eq 2 }"><li class="current"><a href="<%=request.getContextPath()%>/product_servlet?action=first">商品</a></li></c:if>
				<li><a href="<%=request.getContextPath()%>/orderServlet?action=first">订单</a></li>
				<c:if test="${status eq 2 }"><li><a href="<%=request.getContextPath()%>/commentServlet?action=first&h=hou">留言</a></li>
				<li><a href="<%=request.getContextPath()%>/NewsServlet?action=first">新闻</a></li>
				<li><a href="<%=request.getContextPath()%>/documentServlet?action=first">文档</a></li>
				<li><a href="<%=request.getContextPath()%>/getAllUser?action=firsts">排行</a></li></c:if>
			</ul>
		</div>
	</div>
	<%@ include file="head.jsp"%>

	<div id="main" class="wrap">
		<%@ include file="left.jsp"%>
		<div class="main">
			<h2>商品管理<a style="color: red;text-decoration: none;margin-left: 410px;">温馨提示:请谨慎删除,删除后不可恢复哦!</a></h2>
			<div class="manage">
			<c:if test="${not empty list }">
			<table align="right"><tr><td><label class="ui-blue"><input type="button" value="反选" id="fx" class="point"/></lable></td>  <td><label class="ui-blue"><input type="button"
				value="全选/全不选" id="x" class="point"/></label></td>  <td><label class="ui-blue"><input type="button" value="批量删除"
				id="delete" class="point"/></label></td></tr></table>
				</c:if>
				<table class="list">
					<tr>
						<th align="left" style="padding-left: 5px;"><input type="checkbox" id="ck"/></th>
						<th>商品ID</th>
						<th>商品名称</th>
						<th><img src="<%=request.getContextPath() %>/images/date.gif"/>上架时间</th>
						<th>商品单价</th>
						<th>操作</th>
					</tr>
					<c:if test="${not empty list }">
					<c:forEach var="product" items="${list }">
						<tr>
							<td><input type="checkbox" class="cks" value="${product.ep_Id}"/></td>
							<td style="text-align:center;">${product.ep_Id}</td>
							<td class="thumb"><a href="#"><img src="<%=request.getContextPath() %>/${product.ep_File_name}" title="${product.ep_Name}"/></a><a
								href="<%=request.getContextPath()%>/productServlet?action=look&ep_id=${product.ep_Id }"
								target="_blank">${product.ep_Name}</a></td>
							<td class="w2 c">${product.ep_Create_time }</td>
							<td style="text-align:center;"><a style="color:red;text-decoration:none;">￥:${product.ep_Price }</a></td>
							<td class="w1 c"><label class="ui-blue"><a class="manageUpd"
									href="<%=request.getContextPath()%>/product_servlet?action=update&id=${product.ep_Id}"><input
										type="button" class="point" value="修改" /> </a> </label> <label class="ui-blue"><a  class="manageDel"
									href="<%=request.getContextPath() %>/product_servlet?action=del&id=${product.ep_Id}"><input
										type="button" class="point" value="删除" /> </a> </label></td>
						</tr>
					</c:forEach>
					</c:if>
				</table>
			</div>
		</div>
		<div class="clear"></div>
		<c:if test="${not empty list }">
		<%
			session.setAttribute("hpage", "hproduct");
		%>
		<%@ include file="hpage.jsp"%>
		</c:if>
	</div>
	<%@ include file="/mvc.jsp"%>
	<%@ include file="/aop.jsp"%>
</body>
</html>
