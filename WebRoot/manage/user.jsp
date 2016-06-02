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
	if(n==2){document.title="轻松购 - 用户管理";}
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
<c:if test="${hlist eq null}">
	<script type="text/javascript">
		location.href = "<%=request.getContextPath()%>/getAllUser?action=first";
	</script>
</c:if>
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
				<li class="current"><a href="<%=request.getContextPath()%>/getAllUser?action=first">用户</a></li>
				<c:if test="${status eq 2 }"><li><a href="<%=request.getContextPath() %>/product_servlet?action=first">商品</a></li></c:if>
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
			<h2>用户管理<a style="color: red;text-decoration: none;margin-left: 410px;">温馨提示:请谨慎删除,删除后不可恢复哦!</a></h2>
			<div class="manage">
			<c:if test="${status eq 2 }">
			<form action="<%=request.getContextPath()%>/getAllUser?action=first" method="post">
			<table style="margin-left: -11px;"><tr align="right"><td>
  			<input type="hidden" name="cpage" id="cpage" value="${cpage}" />
  			用户名: <input type="text" class="text" name="eu_user_id" id="eu_user_id" value="${eu_user_id}" placeholder="请输入用户名(支持模糊查询)" autocomplete="off"/>
  			<label class="ui-blue"><input type="submit" class="point" value="搜索"/></label>
  			</td></tr></table>
  			</form>
  			</c:if>
				<table class="list">
					<tr>
						<th>头像</th>
						<th>用户名</th>
						<th>真实姓名</th>
						<th>性别</th>
						<th><img src="<%=request.getContextPath() %>/images/email.gif"/>Email邮箱</th>
						<th>手机号</th>
						<th>等级</th>
						<th>操作</th>
					</tr>
					<%-- <c:if test="${empty hlist }">
					<tr><th align="right">对不起没有找到该用户</th></tr>
					</c:if> --%>
					<c:if test="${not empty hlist }">
					<c:forEach var="user" items="${hlist}">
						<tr>
							<td><a href="#"><img src="<%=request.getContextPath()%>/images/user.jpg" title="头像" alt="头像"/></a></td>
							<td class="first w4 c">${user.eu_User_id}</td>
							<td class="w1 c">${user.eu_User_name}</td>
							<td class="w2 c">${user.eu_Sex=="T"?"男":"女"}</td>
							<td>${user.eu_Email}</td> 
							<td class="w4 c">${user.eu_Mobile}</td>
							<%-- <td style="text-align:center;">${user.eu_Score}</td> --%>
							<c:if test="${user.eu_Score lt 1000}">
							<td class="w2 c"><a style="color:orange;text-decoration: none;">VIP1</a></td>
							</c:if>
							<c:if test="${user.eu_Score eq 1000}">
							<td class="w2 c"><a style="color:orange;text-decoration: none;">VIP2</a></td>
							</c:if>
							<c:if test="${user.eu_Score gt 1000}">
								<c:if test="${user.eu_Score lt 3000}">
								<td class="w2 c"><a style="color:orange;text-decoration: none;">VIP2</a></td>
								</c:if>
							</c:if>
							<c:if test="${user.eu_Score eq 3000}">
								<td class="w2 c"><a style="color:orange;text-decoration: none;">VIP3</a></td>
							</c:if>
							<c:if test="${user.eu_Score gt 3000}">
								<c:if test="${user.eu_Score lt 7000}">
								<td class="w2 c"><a style="color:orange;text-decoration: none;">VIP3</a></td>
								</c:if>
							</c:if>
								<c:if test="${user.eu_Score eq 7000}">
								<td class="w2 c"><a style="color:orange;text-decoration: none;">VIP4</a></td>
							</c:if>
							<c:if test="${user.eu_Score gt 7000}">
								<c:if test="${user.eu_Score lt 15000}">
								<td class="w2 c"><a style="color:orange;text-decoration: none;">VIP4</a></td>
								</c:if>
							</c:if>
							<c:if test="${user.eu_Score eq 15000}">
								<td class="w2 c"><a style="color:orange;text-decoration: none;">VIP5</a></td>
							</c:if>
							<c:if test="${user.eu_Score gt 15000}">
								<c:if test="${user.eu_Score lt 36000}">
								<td class="w2 c"><a style="color:orange;text-decoration: none;">VIP5</a></td>
								</c:if>
							</c:if>
							<c:if test="${user.eu_Score eq 36000}">
								<td class="w2 c"><a style="color:orange;text-decoration: none;">VIP6</a></td>
							</c:if>
							<c:if test="${user.eu_Score gt 36000}">
								<c:if test="${user.eu_Score lt 55000}">
								<td class="w2 c"><a style="color:orange;text-decoration: none;">VIP6</a></td>
								</c:if>
							</c:if>
							<c:if test="${user.eu_Score eq 55000}">
								<td class="w2 c"><a style="color:yellow;text-decoration: none;">VIP7</a></td>
							</c:if>
							<%-- <c:if test="${user.eu_File_name ne null}">
  								<td class="thumb"><img alt="头像" src="<%=request.getContextPath() %>/${user.eu_File_name}" width="40" height="30"></td>
  							</c:if> --%>
							<c:choose>
								<c:when test="${status eq 2 }">
									<td class="w1 c"><label class="ui-blue">
									<a class="manageUpd" href="<%=request.getContextPath() %>/ManageuserByAdmin_servlet?action=up&id=${user.eu_User_id}"><input type="button" class="point" value="修改"/></a></label>
									<label class="ui-blue"><a class="manageDel"
										href="<%=request.getContextPath() %>/ManageuserByAdmin_servlet?action=del&id=${user.eu_User_id}"><input type="button" class="point" value="删除"/></a></label>
									</td>
								</c:when>
								<c:when test="${user.eu_User_id eq login and status eq 1 }">
									<td class="w1 c"><label class="ui-blue">
									 <a class="manageUpd" href="<%=request.getContextPath() %>/ManageuserByAdmin_servlet?action=up&id=${user.eu_User_id}" ><input type="button" class="point" value="修改"/></a></label>
									</td>
								</c:when>
							</c:choose>
						</tr>
					</c:forEach>
			<c:if test="${status eq 2 }">
			<tr>
    		<td colspan="10" align="right">
    		当前是<a style="color: blue;text-decoration:none" >${cpage}</a>/<a style="color: blue;text-decoration:none" >${totalPage}</a>页   共<a style="color: blue;text-decoration:none" >${count}</a>条
    		<a class="point" style="color: blue;" onclick="paging(1)">首页</a>
    		<a class="point" style="color: blue;" onclick="paging(${cpage==1?1:cpage-1})">上一页</a>
    		<a class="point" style="color: blue;" onclick="paging(${cpage==totalPage?(totalPage):cpage+1})">下一页</a>
    		<a class="point" style="color: blue;" onclick="paging(${totalPage})">尾页</a>
    		</td>
    	</tr>
    	</c:if>
    	</c:if>
				</table>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<%@ include file="/aop.jsp"%>
	<%
		session.removeAttribute("hlist");
	%>

</body>
</html>
