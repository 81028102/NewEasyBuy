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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/My97DatePicker/WdatePicker.js"></script>
<script>
		var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 订单管理";}
	setTimeout("title()",1000);
	}
	title();
	function check() {
		var orderid = $("#orderId").val();
		var orderName = $("#orderName").val();
		if (orderid == "" || orderName == "") {
			alert("请填写完整信息");
		}
	}
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
<c:if test="${orderList eq null ||detail eq null }">
	<script>
			location.href = "<%=request.getContextPath()%>/orderServlet?action=first";
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
				<c:if test="${status eq 2 }"><li><a href="<%=request.getContextPath()%>/product_servlet?action=first">商品</a></li></c:if>
				<li class="current"><a href="<%=request.getContextPath()%>/orderServlet?action=first">订单</a></li>
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
			<h2>订单管理</h2>
			<div class="manage">
				<div class="search">
					<c:if test="${heu.eu_Status eq 2 }">
						<form action="<%=request.getContextPath()%>/orderServlet?action=select" method="post"
							onsubmit="return check()">
							订单号:<input type="text" class="text" name="orderId" id="orderId" placeholder="请输入订单号" autocomplete="off"
							 onkeyup="this.value=this.value.replace(/\D/g,'')"
							onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
							订货人:<input type="text" class="text" name="userName"
								id="orderName" placeholder="请输入订货人" autocomplete="off"/> <label class="ui-blue"><input
								type="submit" class="point" name="submit" value="查询" /> </label>
						</form>
					</c:if>
					<c:if test="${heu.eu_Status eq 1 }">
						<form action="<%=request.getContextPath()%>/orderServlet?action=selectDate" method="post">
							购买日期:<input id="date" <c:if test="${date ne null }">  </c:if>
								class="text" type="text" name="date" />&nbsp;-&nbsp; <input
								id="date1" class="text" <c:if test="${date1 ne null }">  </c:if>
								type="text" name="date1" /> <label class="ui-blue"><input
								type="submit" name="submit" class="point" value="查询" /></label>
						</form>
					</c:if>
				</div>
				<div class="spacer"></div>
				<table class="list">
				<c:if test="${not empty orderList }">
					<c:forEach var="one" items="${orderList }">
						<form action="<%=request.getContextPath()%>/orderServlet?action=updateStatus" method="post">
						<tr>
							<th colspan="2">单号:<a style="color:green;text-decoration:none;">${one.eo_Id
								}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img src="<%=request.getContextPath() %>/images/date.gif"/>购买日期:<a style="color:green;text-decoration:none;">${one.eo_Create_time }</a></th>
							<th colspan="2">状态:&nbsp;<c:if test="${one.eo_Status eq 5 }"><a style="color:green;text-decoration:none;">收货确认</a></c:if>
								<c:if test="${one.eo_Status ne 5 }">
									<select name="es_Id">
									<!-- 1、待审核 2、审核通过 3、配货中 4、发货中 5、收货确认 -->
										<c:forEach items="${statusList }" var="statusList">
										<option value="${statusList.es_Id }" ${one.eo_Status==statusList.es_Id?"selected":""}>${statusList.es_StatusName }</option>
										</c:forEach>
									</select>
									<input type="hidden" name="eo_Id" value="${one.eo_Id }">
									<c:if test="${heu.eu_Status eq 2 }">
									<label class="ui-blue"><a class="manageUpd"><input type="submit" class="point" value="修改状态"/></a></label>
									<label class="ui-blue"><a class="manageDel" href="<%=request.getContextPath() %>/orderServlet?action=del&id=${one.eo_Id}"><input type="button" class="point" value="删除订单"/></a></label>
									</c:if>
								</c:if>
							</th>
						</tr>
						<c:forEach var="t" items="${detail }">
							<%!int i = 0;%>
							<c:if test="${t.eo_Id eq one.eo_Id }">
								<%
									i++;
								%>
							</c:if>
						</c:forEach>
						<%
							int s = 0;
						%>
						<c:forEach var="two" items="${detail }">
							<c:if test="${two.eo_Id eq one.eo_Id }">
								<%
									s++;
								%>
								<tr>
									<td class="first w4 c"><a href="#"><img src="<%=request.getContextPath() %>/${two.ep_File_name }" alt="${two.ep_Name}" title="${two.ep_Name}"/></a><a
								href="<%=request.getContextPath()%>/productServlet?action=look&ep_id=${two.ep_Id }"
								target="_blank">${two.ep_Name}</a></td>
									<td>单价:<a style="color:red;text-decoration:none;">￥${two.eod_Cost }</a></td>
									<td>购买数量:<a style="color:red;text-decoration:none;">${two.eod_Quantity }件</a></td>
									<%
										if (s == 1) {
									%>
									<td class="w1 c" rowspan="<%=i%>">总计:<a style="color:red;text-decoration:none;">￥${one.eo_Cost }</a></td>
									<%
										i = 0;
													}
									%>
								</tr>
							</c:if>
						</c:forEach>
						</form>
					</c:forEach>
					</c:if>
				</table>
				<c:if test="${not empty orderList }">
				<%
					session.setAttribute("hpage", "horder");
				%>
				<%@ include file="hpage.jsp"%>
				</c:if>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<%@ include file="/aop.jsp"%>
	<%@ include file="/topbottom.jsp"%>
	<c:if test="${not empty orderList }">
	<%@ include file="/footer-y.jsp"%>
	</c:if>
	<<c:if test="${empty orderList }">
	<%@ include file="/footer-n.jsp"%>
	</c:if>
	<c:if test="${message ne null }">
		<script>
			alert("${message }");
			<%
			session.removeAttribute("message");
			%>
		</script>
	</c:if>
</body>
</html>
