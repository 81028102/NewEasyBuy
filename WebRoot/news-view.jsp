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
	<title>轻松购 - 新闻</title>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/function.js"></script>
<script type="text/javascript">
	var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 新闻";}
	setTimeout("title()",1000);
	}
	title();
	</script>
  </head>
  
  <body>
	<%@ include file="head.jsp"%>
	<div id="position" class="wrap">
		<img src="<%=request.getContextPath() %>/images/addScript_ico.gif"/>&nbsp;${Yourpresentposition}:<a href="<%=request.getContextPath() %>/index.jsp">${easybuy}</a> &gt; ${Readingthenews}
	</div>
	<div id="main" class="wrap">
		<%@ include file="left.jsp"%>
		<div class="main">
		<h2>${Newsdetails}<a style="margin-left: 440px;text-decoration: none;">发布时间:${en.en_Create_time }</a></h2>
		<c:if test="${not empty en }">
		<div id="news" class="right-main">
			<h1>${Newsheadlines}:${en.en_Title }</h1>
			<div class="content">${Newscontent }:<textarea rows="6" cols="50" readonly="readonly">${en.en_Content }</textarea></div>
		<br/>
		<div align="center"><label class="ui-blue"><a
				href="<%=request.getContextPath()%>/index.jsp"><input class="point"
				type="button" name="button" value="${returns1}" /> </a> </label></div>
		</div>
		</c:if>
		</div>
	</div>
	<div class="clear"></div>
	<%@ include file="mvc.jsp"%>
	<%@ include file="aop.jsp"%>
	<%@ include file="topbottom.jsp"%>
  </body>
</html>
