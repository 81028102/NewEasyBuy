<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>轻松购 - 注册</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/function.js"></script>
<script type="text/javascript">
	var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 注册";}
	setTimeout("title()",1000);
	}
	title();
	</script>
</head>

<body>
	<%@ include file="head.jsp"%>
	<div id="register" class="wrap">
		<div class="shadow">
			<em class="corner lb"></em> <em class="corner rt"></em>
			<div class="box">
				<h1>${rwelcome1}</h1>
				<ul class="steps clearfix">
					<li class="finished"><em></em>${fillintheregistrationinformation}</li>
					<li class="last-current"><em></em>${loginwassuccessful}</li>
				</ul>
				<div class="msg">
					<p><img src="<%=request.getContextPath() %>/images/addScript_ico.gif"/>&nbsp;${congratulations}:${loginwassuccessful}！</p>
					<p>${Areenteringthehomepage }......</p>
				<script type="text/javascript">
					setTimeout("location.href='index.jsp'", 3000);
				</script>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="mvc.jsp"%>
	<%@ include file="aop.jsp"%>
	<%@ include file="footer-n.jsp"%>
</body>
</html>
