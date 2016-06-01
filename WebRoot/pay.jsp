<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<TITLE>支付页面</TITLE>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<LINK REL="STYLESHEET" HREF="/bank-support/JSP-Styles.css"
	TYPE="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" type="text/css"></link>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/function.js"></script>
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
function returns(){
if(confirm("确认返回吗？")){
	 location.href="<%=request.getContextPath()%>/index.jsp";
		}
	}
</script>
</head>
<body>
	<form name="mainForm" id="pay"
		action="<%=request.getContextPath()%>/payServlet?action=getEasyBuyPay"
		method="post">
		<table width="1200" border="1" align="center">
			<tr>
				<th colspan="2" scope="col"><img
					src="<%=request.getContextPath()%>/images/banner.jpg" width="1200"
					height="90"></img></th>
			</tr>
			<tr>
				<td height="22" colspan="2" bgcolor="#CCFFFF">
					<div align="center" class="STYLE1">欢迎使用"我存我惠"网上银行-支付管理系统</div>
				</td>
			</tr>
			<tr>
				<td width="297" height="473" rowspan="3"><img
					src="<%=request.getContextPath()%>/images/main.jpg" width="297"
					height="473"></td>
				<TH width="574" height="25" CLASS="TITLE">欢迎光临！</TH>
			</tr>

			<tr>
				<td height="234" valign="top"><br> <font size="3">&nbsp;&nbsp;请输入您的姓名,银行卡账号及密码，单击"查询"按钮，查看余额后支付。</font>
					<br> <br> <br> <br> <br> <br>
					<center>
						用 户 姓 名(<a style="color: red;">*</a>):<input type="text"
							class="text" id="eu_user_name" name="eu_user_name"><span></span>
					</center> <br />
					<center>
						银行卡账号(<a style="color: red;">*</a>):<input type="text"
							class="text" id="paycardid" name="paycardid"
							onkeyup="this.value=this.value.replace(/\D/g,'').replace(/....(?!$)/g,'$& ')"><span></span>
					</center> <br />
					<center>
						银行卡密码(<a style="color: red;">*</a>):<input type="password"
							class="text" id="paypwd" name="paypwd"
							onkeyup="this.value=this.value.replace(/\D/g,'')"
							onafterpaste="this.value=this.value.replace(/\D/g,'')"
							maxlength="6"><span></span>
					</center>
					</div>
				</td>
			</tr>
			<tr>
				<td height="45" align="center"><label class="ui-blue"><input
						type="submit" class="point" value="查询"/> </label>&nbsp;&nbsp;&nbsp;<label
					class="ui-blue"><input type="button" value="返回"
						onclick="returns()" /> </label>
				</td>
			</tr>
		</table>
	</form>
	<%@ include file="aop.jsp"%>
	<%@ include file="footer-n.jsp"%>
</body>
</html>
