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

<title>My JSP 'user-forget.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
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
				<h1>${Retrieveandmodifythepassword }</h1>
				<ul class="steps clearfix">
					<li class="finished"><em></em>${Enteryouruseraccount }</li>
					<li class="center-current"><em></em>${Entertheanswerretrievepassword }</li>
					<li class="last"><em></em>${Changethepassword }</li>
					<li class="lasts"><em></em>${Modifythesuccess }</li>
				</ul>
			<div class="manage">
				<form  id="user-forgets" name="user-forget"
					action="<%=request.getContextPath()%>/forgetServlet?action=getEasyBuyAnswer"
					method="post">
					<table class="form">
						<tr>
							<td class="field"></td><td class="field"></td>
							<td><input type="hidden" class="text" id="eu_user_id"
								name="eu_user_id" value="${requestScope.eu_user_id }" />
							<td>
						</tr>
						<tr>
							<td class="field"></td><td class="field">${ronequestion }:</td>
							<td><input type="text" readonly="readonly" class="text" id="eu_question"
								name="eu_question" value="${requestScope.eu_question }" />
							</td>
						</tr>
						<tr>
						<td class="field"></td><td class="field">${answer }:</td>
							<td><input type="text" class="text" id="eu_answer" name="eu_answer" value="" placeholder="${answers }" autocomplete="off"/>
							<span></span></td>
						</tr>
						<tr>
							<td class="field"></td><td class="field">${rtwoquestion }:</td>
							<td><input type="text" readonly="readonly" class="text" id="eu_question1"
								name="eu_question1" value="${requestScope.eu_question1 }" />
							</td>
						</tr>
						<tr>
						<td class="field"></td><td class="field">${answer }:</td>
							<td><input type="text" class="text" id="eu_answer1" name="eu_answer1" value="" placeholder="${answers }" autocomplete="off"/>
							<span></span></td>
						</tr>
						<tr>
							<td class="field"></td><td class="field">${rthreequestion }:</td>
							<td><input type="text" readonly="readonly" class="text" id="eu_question2"
								name="eu_question2" value="${requestScope.eu_question2 }" />
							</td>
						</tr>
						<tr>
						<td class="field"></td><td class="field">${answer }:</td>
							<td><input type="text" class="text" id="eu_answer2" name="eu_answer2" value=""  placeholder="${answers }" autocomplete="off"/>
							<span></span></td>
						</tr>
						<tr><td class="field"></td><td class="field"></td>
							<td><label class="ui-blue"><input type="submit" class="point" value="${Thenextstep }"></label>&nbsp;
							<label class="ui-blue"><a
								href="<%=request.getContextPath()%>/user-forget.jsp"><input 
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
		<%@ include file="footer-y.jsp"%>
</body>
</html>
