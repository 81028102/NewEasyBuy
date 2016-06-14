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
<title>轻松购</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link type="text/css" rel="stylesheet" 
	  href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript"
	  src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript" 
	  src="<%=request.getContextPath()%>/scripts/Xslider.js"></script>
<script type="text/javascript" 
	  src="<%=request.getContextPath()%>/scripts/function.js"></script>
<style type="text/css">
.tejia_con a img{ width:50px; height:50px;border:#fff solid 1px;}
.tejia_con a:hover img{ border:#c00 solid 1px;}
.tejia_con ul li{ width:70px; height:53px;overflow:hidden;float:left;}
.tejia_con ul{width:9999px; position:absolute;margin: 0 auto;}
.tejia_con{position: relative; width:275px; height:53px;overflow:hidden;}
.tejia_cc{position:relative; height:53px; width:275px; border:#ddd solid 1px; overflow:hidden;}
.bbtnn{ display:block; width:25px; height:53px;}
.aleft{ background:url(images/arrow_left.png) no-repeat center center; position:absolute;left:0; top:0; display:block;}
.aleft:hover {background:url(images/arrow2_left.png) no-repeat center center #F5F5F5;}
.aright{background:url(images/arrow_right.png) no-repeat center center; position:absolute; right:0; top:0; display:block;}
.aright:hover {background:url(images/arrow2_right.png) no-repeat center center #F5F5F5;}
	  
	  </style>
<script type="text/javascript">
	var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 商品详情";}
	setTimeout("title()",1000);
	}
	title();
	$(function(){
	var text=$("textarea").val();
	var counter=text.length;
	$("#numtj var").text(100-counter);
	$(document).keyup(function() {
		var text=$("textarea").val();
		var counter=text.length;
		$("#numtj var").text(100-counter);
	});
	});
	</script>
</head>

<body>
	<%
		session.removeAttribute("addressep_id");
	%>
	<%@ include file="head.jsp"%>
	<div id="position" class="wrap">
		<img src="<%=request.getContextPath() %>/images/addScript_ico.gif"/>&nbsp;${Yourpresentposition}:<a href="<%=request.getContextPath() %>/index.jsp">${easybuy }</a>&nbsp;&gt;${Theinvoicing }
		<c:if test="${epc != null }">
			<a
				href="<%=request.getContextPath()%>/indexServlet?action=category&category=one&epc_id=${id}&epc_name=${epc}">${epc}</a>&nbsp;&gt;</c:if>${name }
	</div>
	<div id="main" class="wrap">
		<%@ include file="left.jsp"%>
		<div id="product" class="main">
			<c:if test="${not empty ep }">
			<br/>
			<h2>
				<strong>商品名称:</strong>&nbsp;<a style="font-size: 20px;text-decoration: none">${ep.ep_Name }</a>
			</h2>
				<div class="infos">
					<div class="thumb">
						<br/><br/>
						<img class="magnify" title="放大展示" src="${ep.ep_File_name }" width="110" height="106" />
						<%-- <div style="padding-top: 10px;" align="center">
						<img title="该商品支持货到付款" src="<%=request.getContextPath()%>/images/mess.png"></img>
					</div> --%>
					</div>
					<div class="buy">
						${Mallprice}:<span class="price">￥${ep.ep_Price}</span><br />  ${inventory} : ${ep.ep_Stock } ${count1 }
						<br/>生产地址 : ${ep.ep_Address}
						<br/><img title="消费者保障" src="<%=request.getContextPath() %>/images/aa.gif"/>&nbsp;<img title="正品保障" src="<%=request.getContextPath() %>/images/bb.gif"/>&nbsp;<img title="品牌授权" src="<%=request.getContextPath() %>/images/cc.gif"/>&nbsp;<img title="假一赔三" src="<%=request.getContextPath() %>/images/dd.gif"></img>&nbsp;<img title="可银行卡支付" src="<%=request.getContextPath() %>/images/ee.gif" style="padding-top: 2px;"/>
						<div class="button">
							<input type="button" name="button" value=""
								onclick="location.href = 'addressServlet?action=buy&ep_id=${ep.ep_Id }'" /><a
								href="<%=request.getContextPath()%>/shoppingServlet?action=add&ep_id=${ep.ep_Id }">${Addtocart}</a>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="introduce">
					<h2>
						<strong>${Goodsdetails}</strong>
					</h2>
					<div class="text">
						<h3>${ep.ep_Description }</h3><br /> ......<br />
				</div>
			<div class="tejia_cc">
            <div class="tejia_con">
              <ul>
                <li> <a title="心之眷恋" target="_blank" class="point"><img src="<%=request.getContextPath() %>/images/dong1.gif" alt="心之眷恋"/></a>
                </li>
                <li> <a title="只爱你一人" target="_blank" class="point"><img src="<%=request.getContextPath() %>/images/dong2.gif" alt="只爱你一人"/></a>
                </li>
                <li> <a title="相信我，爱永恒" target="_blank" class="point"><img src="<%=request.getContextPath() %>/images/dong3.gif" alt="相信我，爱永恒"/></a>
                </li>
                <li> <a title="痴情不悔" target="_blank" class="point"><img src="<%=request.getContextPath() %>/images/dong4.gif" alt="痴情不悔"/></a>
                </li>
                <li> <a title="倾城之恋" target="_blank" class="point"><img src="<%=request.getContextPath() %>/images/dong1.gif" alt="倾城之恋"/></a>
                </li>
              </ul>
            </div>
            <a class="bbtnn aleft" href="product-view.jsp#left"></a> <a class="bbtnn aright" href="product-view.jsp#right"></a> </div>
       <script type="text/javascript">
		$(function(){
		$(".tejia_cc").Xslider({
			unitdisplayed:4,
			numtoMove:4,
			loop:"cycle"
		});
	});
</script> 
					</div>
				<div class="introduce">
					<h2>
						<strong>商品评价<a style="color: red;text-decoration:none;margin-left:400px;">(请文明评价,脏话一经发现,冻结账户30天!)</a></strong>
					</h2>
					<br/>
					<c:if test="${assesslist ne null }">
					<ul>
						<c:forEach var="assesslist" items="${assesslist }">
							<li>
								<dl>
									<dt><img src="<%=request.getContextPath() %>/images/message.gif" style="padding-top:2px;">&nbsp;</img><strong style="font-size: 16px;">评价内容:</strong>&nbsp;&nbsp;<a style="font-size: 16px; text-decoration: none">${assesslist.ea_Assess }</a></dt>
									<br/>
									<dd class="author">
										<img src="<%=request.getContextPath() %>/images/thenetfriend.gif" style="padding-top: 2px;"></img>${Thenetfriend}:<a style="color: blue;text-decoration:none" >${assesslist.ea_Nike_name } </a>&nbsp;&nbsp;<span class="timer"><img src="<%=request.getContextPath() %>/images/exchangetime.gif" style="padding-top:2px;"></img> 留言时间:<a style="color: blue;text-decoration:none" >${assesslist.ea_Create_time}</a></span>
									</dd>
									<p style="border-bottom:dashed 1px #999"></p>
								</dl>
							</li>
						</c:forEach>
				</ul>
				<table align="right">
			<tr>
    		<td colspan="10">
    		当前是<a style="color: blue;text-decoration:none" >${cpage}</a>/<a style="color: blue;text-decoration:none" >${totalPage}</a>页   共<a style="color: blue;text-decoration:none" >${counta}</a>条
    		<a href="<%=request.getContextPath() %>/productServlet?action=look&ep_id=${ep.ep_Id }&cpage=1"><img src="<%=request.getContextPath() %>/images/first.gif" style="padding-top: 5px;"/></a>
    		<a href="<%=request.getContextPath() %>/productServlet?action=look&ep_id=${ep.ep_Id }&cpage=${cpage==1?1:cpage-1}"><img src="<%=request.getContextPath() %>/images/prev.gif" style="padding-top: 5px;"/></a>
    		<a href="<%=request.getContextPath() %>/productServlet?action=look&ep_id=${ep.ep_Id }&cpage=${cpage==totalPage?(totalPage):cpage+1}"><img src="<%=request.getContextPath() %>/images/next.gif" style="padding-top: 5px;"/></a>
    		<a href="<%=request.getContextPath() %>/productServlet?action=look&ep_id=${ep.ep_Id }&cpage=${totalPage}"><img src="<%=request.getContextPath() %>/images/end.gif" style="padding-top: 5px;"/></a>
    		</td>
  		  	</tr>
  		  	</table>
  		  	<div id="reply-box">
					<form id="assess-add" action="<%=request.getContextPath()%>/productServlet?action=add&ep_id=${ep.ep_Id }" method="post">
						<table align="left">
							<tr>
								<td class="field"><a style="color: blue;text-decoration:none" >${nickname }:</a></td>
								<td><input class="text" type="text" name="ea_nike_name"
									disabled="disabled" value="${login}" style="color: blue;text-decoration: none" /></td>
							</tr>
							<tr>
								<td class="field"><a style="color: blue;text-decoration:none" >评价内容:</a></td>
								<td><p id="numtj" align="right"><img src="<%=request.getContextPath() %>/images/write.gif" style="padding-top: 5px;"></img>${Youcanalsoinput} <var style="color:#C00">--</var> ${aword }。</p>
								<textarea class="text" id="ea_assess"
										name="ea_assess" maxlength="100" placeholder="请输入评价内容" autocomplete="off"></textarea><span></span></td>
							</tr>
							<tr>
								<td></td>
								<td><label class="ui-blue"><input type="submit" class="point"
										name="submit" value="${Submitamessage }" /> </label>&nbsp;&nbsp;&nbsp;&nbsp;<label class="ui-blue"><a
										href="<%=request.getContextPath()%>/index.jsp" ><input
										type="button" name="button" value="${returns1}" class="point" /> </a> </td>
							</tr>
						</table>
					</form>
				</div>
				</c:if>
				</div>
			</c:if>
		</div>
		<div class="clear"></div>
	</div>
	<%@ include file="mvc.jsp"%>
	<%@ include file="aop.jsp"%>
	<%@ include file="services.jsp"%>
	<%@ include file="qrcode.jsp" %>
	<%@ include file="topbottom.jsp" %>
</body>
</html>
