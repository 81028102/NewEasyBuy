<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" type="text/css"></link>
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/jquery-1.8.3.js"></script>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${login eq null }">
	<script type="text/javascript">
		location.href="<%=request.getContextPath()%>/login.jsp";
	</script>
</c:if>
<div id="childNav">
	<div class="welcome wrap">
		<c:if test="${status eq 2 }"><img src="<%=request.getContextPath() %>/images/manage.png" style="padding-top: 5px;"/>&nbsp;${Manage}</c:if>
		<c:if test="${status eq 1 }"><img src="<%=request.getContextPath() %>/images/user.png" style="padding-top: 5px;"/></c:if>&nbsp;
		<c:if test="${not empty login }"><iframe src="http://tianqi.5ikfc.com:90/plugin-code/?style=3&dy=7" allowTransparency="true" frameborder="0" scrolling="no" width="350" height="40" id="weather_frame" align="left"></iframe>${login }<%@ include file="/hello.jsp"%>,<img src="<%=request.getContextPath() %>/images/date.gif"></img>${Todayis} ${getTime },${Welcomebacktomanagementbackground }。</c:if>
	</div>
</div>
<div id="position" class="wrap">
	<img src="<%=request.getContextPath() %>/images/addScript_ico.gif"/>&nbsp;${Yourpresentposition }:<a href="<%=request.getContextPath()%>/index.jsp">${easybuy}</a> &gt;<img src="<%=request.getContextPath() %>/images/control.gif"></img>${Managementbackground }
</div>
