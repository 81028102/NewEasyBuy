<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>发送成功页面</title>
    
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
	var n = 0;
	function title() {
		n++;
		if (n == 3) {n = 1;};
		if (n == 1) {document.title = "EasyBuy";}
		if (n == 2) {document.title = "轻松购 - 邮件发送";}
		setTimeout("title()", 1000);}
	title();
</script>
  </head>
  
  <body>
  	  <%@ include file="head.jsp"%>
	<div id="position" class="wrap">
		<img src="<%=request.getContextPath() %>/images/addScript_ico.gif"/>&nbsp;${Yourpresentposition}:<a href="<%=request.getContextPath() %>/index.jsp">${easybuy}</a> &gt;
		${SendEmail}
	</div>
	<div id="main" class="wrap">
		<%@ include file="left.jsp"%>
		<div id="shopping">
			<br /> <br /> <br /> <br /> <br />
			<div class="shadow">
				<em class="corner lb"></em> <em class="corner rt"></em>
				<div class="box">
					<div class="msg">
						<p><img src="<%=request.getContextPath() %>/images/addScript_ico.gif"/>&nbsp;${congratulations}:${Mailsentsuccessfully}！</p>
						<p>${Pleasewaitcustomergiveyoureply}......</p>
						<p>${Areenteringthehomepage}......</p>
						<script type="text/javascript">
						setTimeout("location.href='index.jsp'", 5000);
					</script>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="mvc.jsp"%>
	<%@ include file="aop.jsp"%>
	<%@ include file="footer-n.jsp"%>
  </body>
</html>
