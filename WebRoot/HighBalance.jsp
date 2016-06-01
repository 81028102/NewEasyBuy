<%@ page language="java" pageEncoding="utf-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<TITLE>VIP 客户</TITLE>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<LINK REL="STYLESHEET" HREF="/bank-support/JSP-Styles.css" TYPE="text/css">
<script type="text/javascript" 
		src="<%=request.getContextPath() %>/scripts/jquery-1.8.3.js"></script>
<style type="text/css">

.STYLE1 {
	font-family: "方正粗倩简体", "方正大黑简体";
	color: #333333;
}
</style>
<script type="text/javascript">
function noMenuOne()
{
alert('禁止右键菜单!');
return false;
}
document.oncontextmenu = noMenuOne; 
</script>
</head>

<body>
<form action="<%=request.getContextPath() %>/shopping-result.jsp" method="post">
<table width="1200" border="1" align="center">
	<tr>
		<th colspan="2" scope="col"><img src="<%=request.getContextPath() %>/images/banner.jpg"
			width="1200" height="90" /></th>
	</tr>
	<tr>
		<td height="22" colspan="2" bgcolor="#CCFFFF">
		<div align="center" class="STYLE1">欢迎使用"我存我惠"网上银行-支付管理系统</div>
		</td>
	</tr>
	<tr>
		<td width="442" height="473" rowspan="3"><img
			src="<%=request.getContextPath()%>/images/id003.jpg" width="441" height="473"></td>
		<TH width="352" height="25" CLASS="TITLE">感谢您的光临！</TH>
	</tr>
	<tr>
		<td height="234" valign="top"><font size="2">
		<br>
		&nbsp;为您服务是我们的荣幸,<a style="color: green;text-decoration: none">${requestScope.eu_user_name}</a>&nbsp;先生 !
		<P>&nbsp;一直以来,您都是我们的尊贵顾客, 我们有幸为您提供更多的服务和优惠政策。</P>
		<br/>支付成功!您的余额为&nbsp;<a style="color:red;text-decoration:none">￥:${requestScope.money}</a><br/>我们会尽快为您发货,请妥善保管好您的卡号和密码</font></td>
	</tr>
	<tr>
		<td height="45" align="center">
		<label class="ui-blue"><input name="return" type="submit" class="point"
			value="确定" /></label>
		</td>
	</tr>
</table>
</form>
<%@ include file="aop.jsp" %>
<%@ include file="footer-n.jsp"%>
</body>
</html>
