<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>My JSP 'questiontelchange.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" type="text/css"></link>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript"
    src="<%=request.getContextPath()%>/scripts/function.js"></script>
<script type="text/javascript">
	var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 选择方式";}
	setTimeout("title()",1000);
	}
	title();
	$(function() {
		$("#myform").submit(function() {
			if ($("[name='change1']:checked").val() == 1) {
				alert(location.href="<%=request.getContextPath()%>/user-forget.jsp");
			} else if($("[name='change1']:checked").val() == 0){
				alert(location.href = "<%=request.getContextPath()%>/tel-forget.jsp");
			} else {
				alert("请选择一种找回密码方式!");
			}
		});
	});
</script>
</head>

<body>
	<%@ include file="head.jsp"%>
	<div id="register" class="wrap">
		<div class="shadow">
			<h1>${Choosethewaytochangethepassword }</h1>
			<em class="corner lb"></em> <em class="corner rt"></em>
			<div class="box">
				<form method="post" id="myform">
					<table class="form">
						<tr>
							<td class="field"></td>
							<td><input type="radio" name="change1" id="change" value="1" />${Throughencryptedauthenticationproblem}</td>
						</tr>
						<tr>
							<td class="field"></td>
							<td><input type="radio" name="change1" id="change1" value="0" />${Bymobilephoneverification }</td>
						</tr>
						<tr>
							<td class="field"></td>
							<td><label class="ui-blue"><input type="submit" class="point"
									name="submit" value="${Thenextstep}"> </label>&nbsp; <label
								class="ui-blue"><a
									href="<%=request.getContextPath()%>/login.jsp"><input
										type="button" class="point" name="button" value="${returns}" /> </a> </label>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	</div>
	<%@ include file="aop.jsp"%>
	<%@ include file="footer-n.jsp"%>
</body>
</html>
