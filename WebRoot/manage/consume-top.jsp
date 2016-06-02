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
	if(n==2){document.title="轻松购 - 消费排行";}
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
			<h2>消费排行信息</h2>
			<div class="manage">
			<c:if test="${status eq 2 }">
			<form action="<%=request.getContextPath()%>/getAllUser?action=firsts" method="post">
			<table style="margin-left: -11px;"><tr align="right"><td>
  			<input type="hidden" name="cpage" id="cpage" value="${cpage}" />
  			用户名: <input type="text" class="text" name="eu_user_id" id="eu_user_id" value="${eu_user_id}" placeholder="请输入用户名(支持模糊查询)" autocomplete="off"/>
  			<label class="ui-blue"><input type="submit" class="point" value="搜索"/></label>
  			</td></tr></table>
  			</form>
  			</c:if>
				<table class="list">
					<tr>
						<th>用户名</th>
						<th>真实姓名</th>
						<th>性别</th>
						<th>手机号</th>
						<th><img src="<%=request.getContextPath() %>/images/date.gif"/>出生日期</th>
						<th>消费金额</th>
						<th><img src="<%=request.getContextPath() %>/images/8.gif"/>消费积分</th>
						<th>会员等级</th>
						<th>操作</th>
					</tr>
					<c:if test="${not empty clist }">
					<c:forEach var="clist" items="${clist}">
						<tr>
							<td class="first w4 c">${clist.eu_User_id}</td>
							<td class="w1 c">${clist.eu_User_name}</td>
							<td class="w2 c">${clist.eu_Sex=="T"?"男":"女"}</td>
							<td class="w2 c">${clist.eu_Mobile}</td>
							<td class="w2 c">${clist.eu_Birthday}</td>
							<td class="w4 c"><a style="color:red;text-decoration: none;">￥${clist.eo_Cost}</a></td>
							<td class="w4 c"><a style="color:red;text-decoration: none;">积分:${clist.eo_Score}</a></td>
							<c:if test="${clist.eo_Score lt 1000}">
							<td class="w4 c"><a style="color:orange;text-decoration: none;">VIP1</a></td>
							</c:if>
							<c:if test="${clist.eo_Score eq 1000}">
							<td class="w4 c"><a style="color:orange;text-decoration: none;">VIP2</a></td>
							</c:if>
							<c:if test="${clist.eo_Score gt 1000}">
								<c:if test="${clist.eo_Score lt 3000}">
								<td class="w4 c"><a style="color:orange;text-decoration: none;">VIP2</a></td>
								</c:if>
							</c:if>
							<c:if test="${clist.eo_Score eq 3000}">
								<td class="w4 c"><a style="color:orange;text-decoration: none;">VIP3</a></td>
							</c:if>
							<c:if test="${clist.eo_Score gt 3000}">
								<c:if test="${clist.eo_Score lt 7000}">
								<td class="w4 c"><a style="color:orange;text-decoration: none;">VIP3</a></td>
								</c:if>
							</c:if>
								<c:if test="${clist.eo_Score eq 7000}">
								<td class="w4 c"><a style="color:orange;text-decoration: none;">VIP4</a></td>
							</c:if>
							<c:if test="${clist.eo_Score gt 7000}">
								<c:if test="${clist.eo_Score lt 15000}">
								<td class="w4 c"><a style="color:orange;text-decoration: none;">VIP4</a></td>
								</c:if>
							</c:if>
							<c:if test="${clist.eo_Score eq 15000}">
								<td class="w4 c"><a style="color:orange;text-decoration: none;">VIP5</a></td>
							</c:if>
							<c:if test="${clist.eo_Score gt 15000}">
								<c:if test="${clist.eo_Score lt 36000}">
								<td class="w4 c"><a style="color:orange;text-decoration: none;">VIP5</a></td>
								</c:if>
							</c:if>
							<c:if test="${clist.eo_Score eq 36000}">
								<td class="w4 c"><a style="color:orange;text-decoration: none;">VIP6</a></td>
							</c:if>
							<c:if test="${clist.eo_Score gt 36000}">
								<c:if test="${clist.eo_Score lt 55000}">
								<td class="w4 c"><a style="color:orange;text-decoration: none;">VIP6</a></td>
								</c:if>
							</c:if>
							<c:if test="${clist.eo_Score eq 55000}">
								<td class="w4 c"><a style="color:yellow;text-decoration: none;">VIP7</a></td>
							</c:if>
						<td><label class="ui-blue"><a href="<%=request.getContextPath()%>/getAllUser?action=ToUpdateCS&eu_User_id=${clist.eu_User_id}"><input type="button" class="point" value="更多信息"/></label></a></td>
						</tr>
					</c:forEach>
			<tr>
    		<td colspan="10" align="right">
    		当前是<a style="color: blue;text-decoration:none" >${cpage}</a>/<a style="color: blue;text-decoration:none" >${totalPage}</a>页   共<a style="color: blue;text-decoration:none" >${countc}</a>条
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
