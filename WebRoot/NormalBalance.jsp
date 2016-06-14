<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<TITLE>显示余额</TITLE>
<LINK REL="STYLESHEET" HREF="/bank-support/JSP-Styles.css"
	TYPE="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" type="text/css"></link>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
<style type="text/css">
.STYLE1 {
	font-family: "方正粗倩简体", "方正大黑简体";
	color: #333333;
}
</style>
<script type="text/javascript">
var InterValObj; //timer变量，控制时间
var count = 120; //间隔函数，1秒执行
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
 				async:false,
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
function noMenuOne()
{
alert('禁止右键菜单!');
return false;
}
document.oncontextmenu = noMenuOne; 
function cancel(){
	if(confirm("确认取消支付吗？")){
	 location.href="<%=request.getContextPath()%>/index.jsp";
		}
	}
</script>
</head>

<body>
	<form id="pay"
		action="<%=request.getContextPath()%>/paysServlet?action=getEasyBuyPayProduct&action=getEasyBuyPayCount"
		method="post">
		<table width="1200" border="1" align="center">
			<tr>
				<th colspan="2" scope="col"><img
					src="<%=request.getContextPath()%>/images/banner.jpg" width="1200"
					height="90" />
				</th>
			</tr>
			<tr>
				<td height="22" colspan="2" bgcolor="#CCFFFF">
					<div align="center" class="STYLE1">欢迎使用“我存我惠”网上银行-余额查询系统</div></td>
			</tr>
			<tr>
				<td width="225" height="473" rowspan="3"><img
					src="<%=request.getContextPath()%>/images/id002.jpg" width="223"
					height="473">
				</td>
				<TH width="569" height="25" CLASS="TITLE">您的账户信息！</TH>
			</tr>
			<tr align="center">
				<td height="234" valign="top"><font size="2">&nbsp;&nbsp;
						<br />
					<br />
					<br />
					<br /> <input type="hidden" id="ep_Id" name="ep_Id"
						value="${ep.ep_Id}"> <input type="hidden" id="count"
						name="count" value="${ep.ep_Stock }"> <input type="hidden"
						id="counts" name="counts" value="${ep.ep_Sales }">
						<center>
							用 户 姓 名:<input class="text" type="text" readonly="readonly"
								id="eu_user_name" name="eu_user_name"
								value="${requestScope.eu_user_name}" />
						</center>
						<br />
						<center>
							银行卡账号:<input class="text" type="text" readonly="readonly"
								id="paycardid" name="paycardid"
								value="${requestScope.paycardid}" />
						</center>
						<br /> <input type="hidden" id="paypwd" name="paypwd"
						value="${requestScope.paypwd}" />
						<center>
							卡 上 余 额:<a style="color: red;text-decoration: none">￥<input
								class="text" type="text" style="color: red" readonly="readonly"
								id="money" name="money" value="${requestScope.money}" />
							</a>
						</center>
						<br />
						<%-- <center>
							手        机        号: <input class="text" type="text" id="mobile"
								name="mobile" placeholder="${rpleaseinputphone}"
								autocomplete="off" /><span></span>
						</center>
						<br />
						<center>
								验        证        码: <input type="text" class="text"
								id="mcode" name="mcode" onblur="check()"
								placeholder="${PleaseentertheSMSverificationcode }"
								autocomplete="off" /> <label class="ui-blue"><input type="button" class="point"
								value="免费获取验证码" id="zphone" name="zphone"
								onclick="get_mobile_code()" /></label><span></span>
						</center>
						<br /> --%>
						<center>
							<c:if test="${Price ne null}">
							您 需 支 付:<a style="color: red;text-decoration: none">￥<input
									class="text" type="text" style="color: red" readonly="readonly"
									id="Price" name="Price" value="${Price}" />
								</a>
							</c:if>
						</center> </font></td>
			</tr>
			<tr>
				<td height="45" align="center"><label class="ui-blue"><input
						type="submit" class="point" value="确认支付" />
				</label>&nbsp;&nbsp; <label class="ui-blue"><input type="button"
						value="取消支付" onclick="cancel()">
				</label></td>
			</tr>
		</table>
	</form>
	<%@ include file="mvc.jsp"%>
	<%@ include file="footer-n.jsp"%>
</body>
</html>