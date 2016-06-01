<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	src="<%=request.getContextPath()%>/scripts/function.js"></script>
<script type="text/javascript">
		var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 购物车";}
	setTimeout("title()",1000);
	}
	title();
	</script>
</head>

<body>
	<%@ include file="head.jsp"%>
	<div id="position" class="wrap">
		<img src="<%=request.getContextPath() %>/images/addScript_ico.gif"/>&nbsp;${Yourpresentposition }:<a href="<%=request.getContextPath() %>/index.jsp">${easybuy }</a> &gt;
		${Theshoppingcart}
	</div>
	<div id="main" class="wrap">
		<%@ include file="left.jsp"%>
		<div id="shopping">
			<br /> <br /> <br /> <br /> <br />
			<div class="shadow">
				<em class="corner lb"></em> <em class="corner rt"></em>
				<div class="box">
					<div class="msg">
						<p><img src="<%=request.getContextPath() %>/images/addScript_ico.gif"/>&nbsp;${congratulations}:${purchasesucceeded}！</p>
						<p>${Areenteringthehomepage }......</p>
						<script type="text/javascript">
						setTimeout("location.href='index.jsp'", 3000);
					</script>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="aop.jsp"%>
	<%@ include file="footer-n.jsp"%>
</body>
</html>
