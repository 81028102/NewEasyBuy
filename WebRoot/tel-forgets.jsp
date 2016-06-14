<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/scripts/function.js"></script>
<script type="text/javascript">
var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount;//当前剩余秒数
//timer处理函数
function SetRemainTime() {
            if (curCount == 0) {                
                window.clearInterval(InterValObj);//停止计时器
                $("#zphone").removeAttr("disabled");//启用按钮
                $("#zphone").val("重新发送验证码");
            }
            else {
                curCount--;
                $("#zphone").val("请在" + curCount + "秒内输入验证码");
            }
        }
 	function get_mobile_code(){
 		var mobile = $("#mobile").val();
 		alert($("#mobile").val());
		//向后台发送处理数据
        $.ajax({
        	url:"<%=request.getContextPath()%>/telServlet",
        	type:"post",
        	data:{mobile:mobile},
        	dataType:"text",
        	success:function(data){
        		if(data=="true"){
	        		$("#zphone").val("已发送");
	        		 		curCount = count;
 				//设置button效果，开始计时
  	  		 $("#zphone").attr("disabled", "true");
       		 $("#zphone").val("请在" + curCount + "秒内输入验证码");
      		 InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
        		}
        	}
        });
	};
 		function check(){
 			var mcode = $("#mcode").val();
 			//alert(mcode);
 			$.ajax({
 				url:"<%=request.getContextPath()%>/telsServlet",
				type : "post",
				data : {mcode : mcode},
				success : function(data) {
				data = eval(data);
				//alert(data);
				if (data == true) {
					alert("验证码正确");
					$("#xForm").submit();
				} else if (data == false) {
					alert("验证码错误");
				}
			}
		});
	};
	var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";};
	if(n==2){document.title="轻松购 - 找回密码";}
	setTimeout("title()",1000);
	}
	title();
</script>
</head>
<body>
	<%@ include file="head.jsp"%>
	<div id="main" class="wrap">
		<div class="shadow">
			<em class="corner lb"></em> <em class="corner rt"></em>
			<div class="box">
				<h1>${Retrieveandmodifythepassword}</h1>
				<ul class="steps clearfix">
					<li class="finished"><em></em>${Enteryouruseraccount}</li>
					<li class="center-current"><em></em>${EntertheSMSverificationcode}</li>
					<li class="last"><em></em>${Changethepassword}</li>
					<li class="lasts"><em></em>${Modifythesuccess}</li>
				</ul>
				<div class="manage">
					<form name="tel-forget" action="<%=request.getContextPath()%>/forgetssServlet?action=getMobiles" method="post" id="xForm">
						<table class="form">
							<tr>
								<td class="field"></td>
								<td class="field"></td>
								<td><input type="hidden" class="text" id="eu_user_id"
									name="eu_user_id" value="${requestScope.eu_user_id }" />
								<td>
							</tr>
							<tr>
								<td class="field"></td>
								<td class="field">${Yourmobilephonenumber}:</td>
								<td><input type="text" readonly="readonly" class="text"
									id="mobile" name="mobile" value="${requestScope.eu_mobile}" />
								<span></span></td>
							</tr>
							<tr>
								<td class="field"></td>
								<td class="field">${SMSverificationcode }:</td>
								<td><input type="text" class="text" id="mcode"
									name="mcode" onblur="check()" placeholder="${PleaseentertheSMSverificationcode }"
									autocomplete="off" />&nbsp;<label class="ui-blue"> <input type="button" class="point" value="免费获取验证码"
									id="zphone" name="zphone" onclick="get_mobile_code()" /></label><span></span></td>
							</tr>
							<tr>
								<td class="field"></td>
								<td class="field"></td>
								<td><label class="ui-blue"><input type="submit" class="point"
										value="${Thenextstep }"> </label>&nbsp; <label class="ui-blue"><a
										href="<%=request.getContextPath()%>/tel-forget.jsp"><input
											type="button" class="point" name="button" value="${returns}" /> </a> </label>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<%@ include file="mvc.jsp"%>
	<%@ include file="footer-n.jsp"%>
</body>
</html>