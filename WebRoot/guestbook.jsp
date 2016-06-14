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

<title>轻松购 - 留言</title>
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
	src="<%=request.getContextPath()%>/scripts/function.js"></script>
<script type="text/javascript" 
	src="<%=request.getContextPath()%>/ueditor/ueditor.config.js"></script>
<script type="text/javascript" 
	src="<%=request.getContextPath()%>/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	var ue=UE.getEditor('guestContent');
	window.UEDITOR_HOME_URL = "<%=request.getContextPath()%>/ueditor/";
</script>
<script type="text/javascript">
	var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 留言";}
	setTimeout("title()",1000);
	}
	title();
/* 	$(function(){
	var text=$("textarea").val();
	var counter=text.length;
	$("#numtj var").text(100-counter);
	$(document).keyup(function() {
		var text=$("textarea").val();
		var counter=text.length;
		$("#numtj var").text(100-counter);
	});
	}); */
	</script>
</head>

<body>
	<c:if test="${empty comments }">
		<script>
			loction.href="<%=request.getContextPath()%>/commentServlet?action=first";
		</script>
	</c:if>
	<%@ include file="head.jsp"%>
	<div id="position" class="wrap">
		<img src="<%=request.getContextPath() %>/images/addScript_ico.gif"/>&nbsp;${Yourpresentposition}:<a href="<%=request.getContextPath()%>/index.jsp">${easybuy }</a> &gt;
		${Onlinemessage}
	</div>
	<div id="main" class="wrap">
		<%@ include file="left.jsp"%>
		<div class="main">
			<div class="guestbook">
				<iframe src="http://tianqi.5ikfc.com:90/plugin-code/?style=3&dy=7" allowTransparency="true" frameborder="0" scrolling="no" width="350" height="40" id="weather_frame" align="right"></iframe>
				<h2>${Allmessages }<a style="color: red;text-decoration:none;margin-left: 10px;">(请文明留言,脏话一经发现,冻结账户30天!)</a>
				</h2>
				<ul>
					<c:if test="${not empty comments }">
						<c:forEach var="comm" items="${comments }">
							<li> 
								<dl>
									<dt><img src="<%=request.getContextPath() %>/images/message.gif" style="padding-top:5px;">&nbsp;</img>${Messagecontent}:&nbsp;&nbsp;${comm.ec_Content }</dt>
									<c:if test="${comm.ec_Reply ne null}">
									<dd><img src="<%=request.getContextPath() %>/images/exchange.gif" style="padding-top: 2px;"></img> ${Havetoreply}:${comm.ec_Reply}</dd>
									</c:if>
									<c:if test="${comm.ec_Reply eq null}">
									<dd><img src="<%=request.getContextPath() %>/images/time.gif" style="padding-top: 2px;"></img>${Didnotreturn}${comm.ec_Reply}</dd>
									</c:if>
									<dd class="author">
										<img src="<%=request.getContextPath() %>/images/thenetfriend.gif" style="padding-top: 5px;"></img>${Thenetfriend}:<a style="color: blue;text-decoration:none" >${comm.ec_Nick_name }</a> <span class="timer"><img src="<%=request.getContextPath() %>/images/exchangetime.gif" style="padding-top:5px;"></img> 留言时间:<a style="color: blue;text-decoration:none" >${comm.ec_Create_time
											}</a></span>
									</dd>
									<p style="border-bottom:dashed 1px #999"></p>
								</dl>
							</li>
						</c:forEach>
					</c:if>
				</ul>
			<table align="right">
			<tr>
    		<td colspan="10">
    		当前是<a style="color: blue;text-decoration:none" >${cpage}</a>/<a style="color: blue;text-decoration:none" >${totalPage}</a>页   共<a style="color: blue;text-decoration:none" >${countt}</a>条
    		<a href="<%=request.getContextPath() %>/commentServlet?action=first&cpage=1"><img src="<%=request.getContextPath() %>/images/first.gif" style="padding-top: 5px;"></img></a>
    		<a href="<%=request.getContextPath() %>/commentServlet?action=first&cpage=${cpage==1?1:cpage-1}"><img src="<%=request.getContextPath() %>/images/prev.gif" style="padding-top: 5px;"></img></a>
    		<a href="<%=request.getContextPath() %>/commentServlet?action=first&cpage=${cpage==totalPage?(totalPage):cpage+1}"><img src="<%=request.getContextPath() %>/images/next.gif" style="padding-top: 5px;"></img></a>
    		<a href="<%=request.getContextPath() %>/commentServlet?action=first&cpage=${totalPage}"><img src="<%=request.getContextPath() %>/images/end.gif" style="padding-top: 5px;"></img></a>
    		</td>
  		  	</tr>
  		  	</table>
				<div class="clear"></div>
				<div id="reply-box">
					<form id="guestbook-modify"
						action="<%=request.getContextPath()%>/commentServlet?action=add"
						method="post">
						<table>
							<tr>
								<td class="field"><a style="color: blue;text-decoration:none" >${nickname }:</a></td>
								<td><input class="text" type="text" name="guestName"
									disabled="disabled" value="${param.name }" style="color: blue;text-decoration: none"/></td>
							</tr>
							<tr>
								<td class="field"><a style="color: blue;text-decoration:none" >${Messagecontent }:</a></td>
								<td><%-- <p id="numtj" align="right"><img src="<%=request.getContextPath() %>/images/write.gif" style="padding-top: 5px;"></img>${Youcanalsoinput} <var style="color:#C00">--</var> ${aword }。</p> --%>
								<textarea class="text" id="guestContent"
										name="guestContent" maxlength="100" placeholder="${Pleaseenterthemessagecontent }" autocomplete="off"></textarea><!-- <span></span> --></td>
							</tr>
							<tr>
								<td></td>
								<td><label class="ui-blue"><input type="submit" class="point"
										name="submit" value="${Submitamessage }" /> </label>&nbsp;&nbsp;&nbsp;&nbsp;<label class="ui-blue"><a
										href="<%=request.getContextPath()%>/index.jsp"><input
										type="button" class="point" name="button" value="${returns1}" /> </a> </td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<%@ include file="mvc.jsp"%>
	<%@ include file="aop.jsp"%>
	<%@ include file="topbottom.jsp"%>
	<%@ include file="qrcode.jsp"%>
	<%@ include file="footer-y.jsp"%>
</body>
</html>
