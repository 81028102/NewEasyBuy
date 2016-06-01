<%@page import="cn.jbit.entity.*"%>
<%@page import="cn.jbit.biz.*"%>
<%@page import="cn.jbit.bizimpl.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" type="text/css"></link>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/function.js"></script>
<script type="text/javascript">
	 $(function() {
		$(".navbar li ").hover(function(){
			$(this).addClass("current");
		},
		function(){
			$(this).removeClass("current");
		});
		$("#shoppingBag").hover(function() {
			$(this).removeAttr("style");
		});
	}); 
	function checkSearch() {
		var i = $("#search").val();
		if (i == "") {
			alert("搜索内容不能为空！");
			return false;
		}
		return true;
	}
	function islogin(){
	var login=$(".abc").text();
	if(login=="登录"){
	alert("请先登录");
	return false;
	}else{
	location.href="";
	}
	return true;
	}
</script>
<%
	easybuy_product_categoryBiz epc = new easybuy_product_categoryBizImpl();
	List<easybuy_product_category> headCategory = epc.getCategories(0);//获取所有商品分类
	request.setAttribute("headCategory", headCategory);
%>
<div id="header" class="wrap">
	<div id="logo">
		<img src="<%=request.getContextPath()%>/images/logo.gif" />
	</div>
	<div class="help">
		<a href="<%=request.getContextPath()%>/shopping.jsp" id="shoppingBag"
			class="shopping">${shopping}<c:if test="${count eq null }">0</c:if>${count
			}${numbers}</a>
		<c:if test="${empty login }">
			<a href="<%=request.getContextPath()%>/login.jsp?path=" +null
				class="abc" style="color: red;">[${Login}]</a>
		</c:if>
		<c:if test="${not empty login }">
			<a id="logout" class="abc" href="javascript:void(0)">[${logout}]</a>
		</c:if>
		<a href="<%=request.getContextPath()%>/findQuestionServlet">[${register}]</a>
		<img src="<%=request.getContextPath()%>/images/listScript_ico.gif"/><a href="<%=request.getContextPath()%>/commentServlet?action=first">${leaveamessage}</a>
		<img src="<%=request.getContextPath()%>/images/home_ico.gif"/><a href="<%=request.getContextPath()%>/loginServlet?action=hou">${bgmanage}</a>
		<div align="right" style="padding-top: 11px;">
		<table border="0" bordercolor="#000000">
			<tr>
				<td>
					<form
						action="<%=request.getContextPath()%>/indexServlet?action=search"
						method="post" onsubmit="return checkSearch()">
						<input class="text" type="text" name="sea" id="search"
							placeholder="Search ..." autocomplete="off" /> <label
							class="ui-orange"><input type="submit" class="point" value="${search}" /> </label>
					</form></td>
			</tr>
		</table>
		</div>
	</div>
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a
				href="<%=request.getContextPath()%>/index.jsp">${Home}</a></li>
			<li><a
				href="<%=request.getContextPath()%>/indexServlet?action=category&amp;category=one&amp;epc_id=1&amp;epc=图书">${Book}</a>
			</li>
			<li><a
				href="<%=request.getContextPath()%>/indexServlet?action=category&amp;category=one&amp;epc_id=2&amp;epc=数码">${Digital}</a>
			</li>
			<li><a
				href="<%=request.getContextPath()%>/indexServlet?action=category&amp;category=one&amp;epc_id=3&amp;epc=品牌">${Brand}</a>
			</li>
			<li><a
				href="<%=request.getContextPath()%>/indexServlet?action=category&amp;category=one&amp;epc_id=36&amp;epc=百货">${Department}</a>
			</li>
			<li><a
				href="<%=request.getContextPath()%>/indexServlet?action=category&amp;category=one&amp;epc_id=37&amp;epc=促销">${Salespromotion}</a>
			</li>
		</ul>
	</div>
</div>
<div id="childNav">
	<!--  <p id="music">
	  <embed  src="Donna Lewis/Donna Lewis - I Could Be The One.mp3" type="application/x-mplayer3" loop="-1" ShowStatusBar="true" ShowPositionControls="false" EnableContextMenu="false" ></embed>
      </p> -->
	<div class="wrap">
		<div class="welcome wrap">
			<ul class="clearfix">
				<c:forEach var="one" items="${headCategory }" varStatus="status">
					<li <c:if test="${status.index eq 0 }">class="first"</c:if>><a
						href="<%=request.getContextPath() %>/indexServlet?action=category&category=one&epc_id=${one.epc_Id}&epc=${one.epc_Name }">${one.epc_Name}</a>
					</li>
				</c:forEach>
				<li><a href="<%=request.getContextPath()%>/supersearch.jsp">${Quicktoenter}</a>
				</li>
				<c:if test="${status eq 2 }"><img src="<%=request.getContextPath() %>/images/manage.png"/>&nbsp;${Manage}</c:if>
				<c:if test="${status eq 1 }"><img src="<%=request.getContextPath() %>/images/user.png"/></c:if>&nbsp;
				<c:if test="${not empty login }">
				${login}<%@ include file="hello.jsp"%>,<img src="<%=request.getContextPath() %>/images/date.gif"></img>${Todayis} ${getTime},${Welcometothehomepage}。</c:if>
			</ul>
		</div>
	</div>
</div>