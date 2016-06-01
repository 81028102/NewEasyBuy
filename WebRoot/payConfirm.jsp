<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付确认页面</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/scripts/function.js"></script>
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
	var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 支付确认页面";}
	setTimeout("title()",1000);
	}
	title();	
</script>
</head>
<body>
<%@ include file="head.jsp"%>
	<div id="position0" class="wrap">
		<img src="<%=request.getContextPath() %>/images/addScript_ico.gif"/>&nbsp;${Yourpresentposition}:<a
			href="<%=request.getContextPath()%>/index.jsp">${easybuy}</a> &gt;${Theshoppingcart}
	</div>
	<div id="main" class="wrap">
		<%@ include file="left.jsp"%>
		<div class="main">
		<h2>购物结算</h2>
		</div>
	<!-- 在线支付 ： 需要将第三方公司，提供一个处理数据的URL -->
	<br/>
	<br/>
	<form action="<%=request.getContextPath() %>/payProductServlet" method="post">
		<table align="center" class="form">
			<input type="hidden" id="count" name="count" value="${ep.ep_Stock }">
			<input type="hidden" id="counts" name="counts" value="${ep.ep_Sales }">
			<tr>
				<td class="field">商品名称:</td>
				<td><input type="text" class="text" style="border: none;" readonly="readonly" id="ep_Name" name="ep_Name" value="${ep.ep_Name}"/></td><!-- value只能是字母或者数字 -->
			</tr>
			<tr>
				<td class="field">订  单  号:</td>
				<td><input type="text" class="text" style="border: none;" readonly="readonly" id="ep_Id" name="ep_Id" value="${ep.ep_Id }"/></td>
			</tr>
			<tr>
				<td class="field">价        格:</td>
				<td>
				<c:if test="${Price ne null}">
				<a style="color: red;text-decoration: none" >￥<input type="text" class="text" style="color: red;border: none;" readonly="readonly" id="Price" name="Price" value="${Price}"/></a>
				</c:if></td>
			</tr>
			<tr>
				<td class="field">请选择在线支付银行:</td>
			</tr>
			<tr>
				<td><input type="radio" name="frpId" value="ICBC-NET"/><img src="<%=request.getContextPath() %>/Pay_files/ICBC-NET.jpg" style="padding-top: 10px;"></img></td>&nbsp;&nbsp;&nbsp;&nbsp;
				<td><input type="radio" name="frpId" value="ABC-NET"/><img src="<%=request.getContextPath() %>/Pay_files/ABC-NET.jpg" style="padding-top: 10px;"/></td>&nbsp;&nbsp;&nbsp;&nbsp;
				<td><input type="radio" name="frpId" value="CMBCHINA-NET"/><img src="<%=request.getContextPath() %>/Pay_files/CMBCHINA-NET.jpg" style="padding-top: 10px;"/></td>&nbsp;&nbsp;	
			</tr>
			<tr>
				<td><input type="radio" name="frpId" value="BOC-NET"/><img src="<%=request.getContextPath() %>/Pay_files/BOC-NET.jpg" style="padding-top: 10px;"/><br/></td>&nbsp;&nbsp;&nbsp;&nbsp;
				<td><input type="radio" name="frpId" value="CCB-NET"/><img src="<%=request.getContextPath() %>/Pay_files/CCB-NET.jpg" style="padding-top: 10px;"/></td>&nbsp;&nbsp;&nbsp;&nbsp;
				<td><input type="radio" name="frpId" value="BCCB-NET"/><img src="<%=request.getContextPath() %>/Pay_files/BCCB-NET.jpg" style="padding-top: 10px;"/></td>&nbsp;&nbsp;
			</tr>
			<tr>
				<td><input type="radio" name="frpId" value="CMBC-NET"/><img src="<%=request.getContextPath() %>/Pay_files/CMBC-NET.jpg" style="padding-top: 10px;"/></td>&nbsp;&nbsp;&nbsp;&nbsp;
				<td><input type="radio" name="frpId" value="HXB-NET"/><img src="<%=request.getContextPath() %>/Pay_files/HXB-NET.jpg" style="padding-top: 10px;"/></td>&nbsp;&nbsp;&nbsp;&nbsp;
				<td><input type="radio" name="frpId" value="POST-NET"/><img src="<%=request.getContextPath() %>/Pay_files/POST-NET.jpg" style="padding-top: 10px;"/></td>&nbsp;&nbsp;
			</tr>
			<tr>
				<td><input type="radio" name="frpId" value="ECITIC-NET"/><img src="<%=request.getContextPath() %>/Pay_files/ECITIC-NET.jpg" style="padding-top: 10px;"/></td>&nbsp;&nbsp;&nbsp;&nbsp;
				<td><input type="radio" name="frpId" value="BOCO-NET"/><img src="<%=request.getContextPath() %>/Pay_files/BOCO-NET.jpg" style="padding-top: 10px;"/></td>&nbsp;&nbsp;&nbsp;&nbsp;
				<td><input type="radio" name="frpId" value="CEB-NET"/><img src="<%=request.getContextPath() %>/Pay_files/CEB-NET.jpg" style="padding-top: 10px;"/></td>&nbsp;&nbsp;
			</tr>
			<tr>
			<p></p>
				<td height="45" align="center">
				<td colspan="4"><label class="ui-blue"><input class="point" type="submit" value="付款"/></label>&nbsp;&nbsp;<label class="ui-blue">
				<input type="button" name="button" value="返回" onclick="returns()"/></label></td>
			</tr>
		</table>
	</form>
	</div>
	<div class="clear"></div>
	<%@ include file="topbottom.jsp" %>
</body>
</html>