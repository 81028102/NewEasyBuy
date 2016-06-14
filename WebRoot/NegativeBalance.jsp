<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<TITLE>请尽快还款</TITLE>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<LINK REL="STYLESHEET" HREF="/bank-support/JSP-Styles.css"
	TYPE="text/css">
<style type="text/css">
.STYLE1 {
	font-family: "方正粗倩简体", "方正大黑简体";
	color: #333333;
}
</style>
</head>
<body>
	<table width="800" border="1" align="center">
		<tr>
			<th colspan="2" scope="col"><img
				src="<%=request.getContextPath()%>/images/banner.jpg" width="800"
				height="90" />
			</th>
		</tr>
		<tr>
			<td height="22" colspan="2" bgcolor="#CCFFFF">
				<div align="center" class="STYLE1">欢迎使用“我存我惠”网上银行-余额查询系统</div></td>
		</tr>
		<tr>
			<td width="225" height="212" rowspan="3"><img
				src="./images/id001.jpg" width="208" height="249">
			</td>
			<TH width="569" height="25" CLASS="TITLE">请您尽快还款：</TH>
		</tr>
		<tr>
			<td height="234" valign="top"><font size="2"> <br>
					&nbsp;请注意,${requestScope.eu_user_name}先生, 我们知道您的家庭住址.
					<P>&nbsp;请您还款 ￥！否则我们将动用法律程序!</P>
			</font>
			</td>
		</tr>
		<tr>
			<td height="45" align="center">
				<form action="<%=request.getContextPath()%>/index.jsp">
					<input name="return" type="submit" value="返回首页" />
				</form></td>
		</tr>
	</table>
	<%@ include file="mvc.jsp"%>
	<%@ include file="aop.jsp"%>
	<%@ include file="footer-n.jsp"%>
</body>
</html>
