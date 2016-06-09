<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>轻松购 - 首页</title>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/function.js"></script>
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
	<%session.removeAttribute("ashop"); %>
	<%@ include file="head.jsp" %>
	<div id="position" class="wrap">
		<img src="<%=request.getContextPath() %>/images/addScript_ico.gif"/>&nbsp;${Yourpresentposition }:<a href="index.jsp">${easybuy}</a> &gt; ${Theshoppingcart }
	</div>
	<div class="wrap">
		<div id="shopping">
		<c:if test="${shop eq null }">
		<br/>
		<br/>
		<p>${Yourshoppingcartisempty},<a href="index.jsp">${Visitthegoods}${Visitthegoods}~~~~~~</a></p>
		</c:if>
		<br/>
		<c:if test="${shop ne null }">
			<form action="<%=request.getContextPath()%>/addressServlet?action=buy" method="post">
				<table>
					<tr>
						<th>${Nameofcommodity}</th>
						<th>${Commodityprice}</th>
						<th>${Commodityprices}</th>
						<th>${Purchasequantity}</th>
						<th>${Operation}</th>
					</tr>
					<c:forEach var="m" items="${shop.keySet() }" varStatus="s">
					<tr id="product_id_0">
						<td class="thumb"><img src="${m.ep_File_name}" /><a href="<%=request.getContextPath()%>/productServlet?action=look&ep_id=${m.ep_Id}">${m.ep_Name}</a></td>
						<td class="price" style="color:black;">￥${m.ep_Price/shop.get(m) }</td>
						<td class="price" id="price_id_0">
							<span>￥${m.ep_Price}</span>
							<input type="hidden" value="${m.ep_Price/shop.get(m) }" />
						</td>
						<td class="number">
                        	<span name="del"><a href="<%=request.getContextPath()%>/shoppingServlet?action=minus&ep_id=${m.ep_Id}" style="text-decoration:none;">-</a></span>
                        	<input id="number_id_0" type="text" name="number" value="${shop.get(m) }" />
                        	<span name="add"><a href="<%=request.getContextPath()%>/shoppingServlet?action=add&ep_id=${m.ep_Id}" style="text-decoration:none;">+</a></span>
						</td>
						<td class="delete"><a href="<%=request.getContextPath()%>/shoppingServlet?action=del&ep=${m.ep_Id}">${Delete}</a></td>
					</tr>
					</c:forEach>
				</table>
            	<div class="total"><a style="color: red;text-decoration: none;" ><span id="total">${Atotalof}:￥0</span></a></div>
				<div class="button"><input type="submit" value="" /></div>
			</form>
			</c:if>
		</div>
	</div>
	<%@ include file="aop.jsp"%>
	<%@ include file="qrcode.jsp" %>
	<%@ include file="footer-n.jsp"%>
  </body>
</html>
