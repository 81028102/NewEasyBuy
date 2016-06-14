<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>轻松购 - 邮件</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/function.js"></script>
<%-- <style>
.barline{position:absolute;width:200px;background:#FFF;border:2px solid #4DBF7D;height:12px;display:inline-block;border-radius:8px;display:none;}
.barline #percent{position:absolute;top:-30px;left:-100px;display:inline-block;color:#4DBF7D;font-size:16px;}
.barline #line{float:left;height:12px;overflow:hidden;background:#4DBF7D;border-radius:8px;}
.barline #msg{display:inline-block;color:#4DBF7D;font-size:14px;}
</style> --%>
<script type="text/javascript">
		var n = 0;
	function title() {
		n++;
		if (n == 3) {n = 1;};
		if (n == 1) {document.title = "EasyBuy";}
		if (n == 2) {document.title = "轻松购 - 邮件发送";}
		setTimeout("title()", 1000);}
	title();
	$(function() {
		var text = $("#content").val();
		var counter = text.length;
		$("#numtj var").text(365 - counter);
		$(document).keyup(function() {
			var text = $("#content").val();
			var counter = text.length;
			$("#numtj var").text(365 - counter);
		});
	});
	/* function processerbar(time){
	if(document.getElementById("upload").value!=null||document.getElementById("upload").value!=""){
     document.getElementById('probar').style.display="block";
	$("#line").each(function(i,item){
		var a=parseInt($(item).attr("w"));
		$(item).animate({
			width: a+"%"
		},time);
	});
   var si = window.setInterval(
	function(){
		a=$("#line").width();
		b=(a/200*100).toFixed(0);
		document.getElementById('percent').innerHTML=b+"%";
		document.getElementById('percent').style.left=a-10+"px";
		document.getElementById('msg').innerHTML="上传中";
		if(document.getElementById('percent').innerHTML=="100%") {
		clearInterval(si);
		document.getElementById('msg').innerHTML="&nbsp;&nbsp;成功";
		}
	},70);
	}
} */
</script>
</head>

<body>
	<%@ include file="head.jsp"%>
	<div id="position" class="wrap">
		<img src="<%=request.getContextPath() %>/images/addScript_ico.gif"/>&nbsp;${Yourpresentposition}:<a href="index.jsp">${easybuy}</a>
		&gt;${SendEmail}
	</div>
	<div id="main" class="wrap">
		<%@ include file="left.jsp"%>
		<div class="main">
				<iframe src="http://tianqi.5ikfc.com:90/plugin-code/?style=3&dy=7" allowTransparency="true" frameborder="0" scrolling="no" width="350" height="40" id="weather_frame" align="right"></iframe>
			<h2>${SendEmail}</h2>
			<%-- 	<s:form action="sendmailAction.action" enctype="multipart/form-data"
				method="post">
				<s:token></s:token>
				<s:textfield id="from" name="from" label="发件人邮箱"
					value="981028102@qq.com" readonly="true" />
				<s:textfield id="to" name="to" label="客 服 邮 箱"
					value="15714638@qq.com" readonly="true" />
				<s:textfield id="subject" name="subject" label="邮 箱 主 题"
					placeholder="请输入邮箱发送主题" autocomplete="off" />
				<span></span>
				<p id="numtj" align="right">
					${Youcanalsoinput}
					<var style="color:#C00">--</var>
					${aword }。
				</p>
				<s:textarea id="content" name="content" label="邮 箱 内 容"
					placeholder="请输入邮箱发送内容" autocomplete="off" rows="15px" cols="70px"
					maxlength="360" />
				<span></span>
				<s:file id="upload" name="upload" lable="选择附件" />
				<span></span>
				<s:submit name="submit" value="发送邮件" align="center" />
				<s:token></s:token>
			</s:form> --%>
			<form id="sendemail" action="SendMailAction.action" enctype="multipart/form-data" method="post">
				<s:token></s:token>
				<table class="form">
					<tr>
						<td class="field">发件人邮箱:</td>
						<td><input type="text" class="text" id="from" name="from"
							value="981028102@qq.com" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td class="field">客 服 邮 箱:</td>
						<td><input type="text" class="text" id="to" name="to"
							value="15714638@qq.com" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td class="field">邮 箱 主 题:</td>
						<td><input type="text" class="text" id="subject"
							name="subject" placeholder="请输入邮箱发送主题" autocomplete="off" />
						</td>
					</tr>
					<tr>
						<td class="field">邮 箱 内 容:</td>
						<td><p id="numtj" align="right">
						<img src="<%=request.getContextPath() %>/images/write.gif" style="padding-top: 5px;"></img>${Youcanalsoinput}<var style="color:#C00">--</var>${aword}。</p><textarea rows=15px " cols="70px" class="text"
								id="content" name="content" maxlength="365" placeholder="1987年9月14日21时07分                                                  

                                         中国第一封电子邮件

从北京发往德国

                                                                        “越过长城，走向世界”"
								autocomplete="off"></textarea>
						</td>
					</tr>
					<tr>
							<td class="field">选 择 附 件<img src="<%=request.getContextPath() %>/images/email.gif" style="padding-top: 3px;"></img>:</td>
							<td><input type="file" class="text" value="" id="upload" name="upload"/><span></span></td>
								<%-- <input type="button" value="上 传" onclick="processerbar(3000)"> <span></span></td> --%>
						</tr>
				<!-- 		<tr><td><div class="barline" id="probar" align="right">
						<div id="percent"></div>
						<div id="line" w="100" style="width:0px;"></div>	
						<div id="msg" style=""></div>			
						</div></td></tr> -->
					<tr>
						<td></td>
						<td><br/><label class="ui-blue"><input type="submit" class="point"
								name="submit" value="发送邮件"/> </label>&nbsp;&nbsp;  <label class="ui-blue"><a
								href="<%=request.getContextPath()%>/index.jsp"><input
									type="button" name="button" value="取消" /> </a> </label>
						</td>
					</tr>
				</table>
				<s:token></s:token>
			</form>
			<%@ include file="mvc.jsp"%>
			<%@ include file="qrcode.jsp"%>
			<%@ include file="topbottom.jsp"%>
		</div>
		<div class="clear"></div>
	</div>
</body>
</html>
