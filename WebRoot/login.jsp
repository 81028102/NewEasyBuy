<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>轻松购 - 登陆</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/function.js"></script>
<script src="<%=request.getContextPath() %>/vendor/modernizr.custom.js"></script>
<style>
  .hideShowPassword-toggle {
    background-image: url(img/wink.svg);
    background-position: 0 center;
    background-repeat: no-repeat;
    cursor: pointer;
    height: 100%;
    overflow: hidden;
    text-indent: -9999em;
    width: 44px;
  }
 </style>
<script type="text/javascript">
	var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 登陆";}
	setTimeout("title()",1000);
	}
	title();
	</script>
</head>

<body>
	<%
		path="";
	   if(request.getParameter("path")!=null){
	   	path=request.getParameter("path");
	   }else{
	   	path=request.getHeader("referer");
	   }
	%>
	<%@ include file="head.jsp"%>
	<br/><br/><%-- <img src="<%=request.getContextPath() %>/images/zy.gif"/> --%>
	<div id="register" class="wrap">
		<div class="shadow">
			<em class="corner lb"></em> <em class="corner rt"></em>
			<div class="box">
				<h1>${welcome}</h1>
				<form id="loginForm" method="post"
					action="<%=request.getContextPath()%>/loginServlet?path=<%=path%>">
					<table>
						<tr>
							<td class="field">${userId}:</td>
							<td><input
								<c:if test="${name ne null }">value="${name }"</c:if>
								class="text" type="text" id="userId" name="userId" placeholder="${pleaseinputname }" autocomplete="off"/> <span></span>
							</td>
						</tr>
						<tr>
							<td class="field">${password1}:</td>
							<td><input
								<c:if test="${pwd ne null }">value="${pwd }"</c:if> class="text"
								type="password" id="password" name="password" placeholder="${pleaseinputpassword }" autocomplete="off"/> <span></span>
							</td>
						</tr>
						<tr>
							<td class="field">${codes}:</td>
							<td>
								<input type="text" class="text" name="code" id="safe"
								<c:if test="${message ne null }"></c:if> placeholder="${codess}" autocomplete="off"> <span id="span"
								<c:if test="${message ne null }"> class="errors"</c:if>><c:if
									  test="${message ne null }">${message}</c:if> </span>
									  <img src="Number.jsp" id="safeCode" /><a
								id="changeCode" href="javascript:flushValidataCode();">&nbsp;${change}</a><br>
							</td>
						</tr>
						<tr>
						<td></td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<label class="ui-blue"><input type="submit" class="point"
									name="submit" value="${submit}" /> </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath() %>/questiontelchange.jsp">${forget}？</a></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<c:if test="${nosuccess ne null }">
		<script>
			alert("${nosuccess }");
		</script>
		<%
			session.removeAttribute("nosuccess");
		%>
	</c:if>
	<%@ include file="mvc.jsp"%>
	<%@ include file="aop.jsp"%>
	<%@ include file="footer-n.jsp"%>
<script>
document.write('<script src=vendor/' +
('__proto__' in {} ? 'zepto.custom' : 'jquery-1.8.3') +
'.js><\/script>');
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/vendor/hideShowPassword.min.js"></script>
<script>

$("#password").hideShowPassword({
  innerToggle: true,
  touchSupport: Modernizr.touch
});
</script>

</body>
</html>