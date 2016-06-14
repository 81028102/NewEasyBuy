<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>轻松购 - 首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/scripts/function.js"></script>
 <script type="text/javascript">
		var n=0;
	function title(){
	n++;
	if(n==3){n=1;};
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 找回密码";}
	setTimeout("title()",1000);
	}
	title();
	</script>
</head>

<body>
	<%@ include file="head.jsp"%>
	<div id="main" class="wrap">
		<div class="shadow">
			<em class="corner lb"></em> <em class="corner rt"></em>
				<div class="box">
				<h1>${Retrieveandmodifythepassword}</h1>
				<ul class="steps clearfix">
					<li class="finished"><em></em>${Enteryouruseraccount}</li>
					<li class="finished"><em></em>${EntertheSMSverificationcode}</li>
					<li class="last-current"><em></em>${Changethepassword}</li>
					<li class="lasts"><em></em>${Modifythesuccess}</li>
				</ul>
			<div class="manage">
				<form id="tel-modify" action="<%=request.getContextPath()%>/updatetelforgetServlet?action=updateTelForget" method="post">
					<table class="form">
						<tr>
							<td class="field"></td><td class="field"></td>
							<td><input type="hidden" class="text" id="eu_mobile"
								name="eu_mobile" value="${requestScope.eu_mobile}" /></td>
						</tr>
						<tr>
							<td class="field"></td><td class="field">${passwords}:</td>
							<td><input type="text" class="text" id="password"
								name="password" value=""  placeholder="${Pleaseenterapasswordtomodify}" autocomplete="off"/><span></span></td>
						</tr>
						<tr>
							<td class="field"></td><td class="field">${Confirmpassword}:</td>
							<td><input type="text" class="text" id="confirmPassword"
								name="confirmPassword" value=""  placeholder="${Pleaseenterthepasswordagain}" autocomplete="off"/><span></span></td>
						</tr>
						<tr>
							<td class="field"></td><td class="field"></td>
							<td><label class="ui-blue"><input type="submit" class="point"
									name="submit" value="${Modify}" /> </label>&nbsp; <label class="ui-blue"><a
									href="<%=request.getContextPath()%>/tel-forgets.jsp"><input
										type="button" class="point" name="button" value="${returns}" /> </a> </label>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="clear"></div>
		</div>
	</div>
	<%@ include file="mvc.jsp"%>
	<%@ include file="aop.jsp"%>
	<%@ include file="footer-n.jsp"%>
</body>
</html>
