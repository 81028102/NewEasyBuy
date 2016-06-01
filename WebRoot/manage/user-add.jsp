<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 轻松购</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/function.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery.validate.js"></script>
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
		var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 用户管理";}
	setTimeout("title()",1000);
	}
	title();
</script>
<script>
  	$(function (){
  			//创建全局变量，接收省名、市名、县名
  			var province;
  			var city;
  			var country;
  			//使用Ajax初始化数据，将省显示在省下拉列表框中
  			$.ajax({
  				type:"post",
  				url:"<%=request.getContextPath()%>/cityServlet",
  				dataType:"json",
  				data:{pid:1},//根据pid查询所有的省
  				success:function (obj){
  					//解析json数据
  					for(var i in obj){
  						var json = obj[i];
  						//将省的id赋值给option的value值，cityname（省名）作为文本内容显示，将创建好的option元素动态追加的省下拉框中
  						$("#province").append("<option value="+json.id+">"+json.cityname+"</option>");
  						}
  				}
  			});
  			
  			//省下拉列表内容改变事件，市和县发生相应变化
  			$("#province").change(function (){
  				//将市和县下拉列表框的长度固定为1，即只显示"——请选择——"
  				$("#city").get(0).length = 1;
  				$("#country").get(0).length = 1;
  				//获取下拉框选中选项的value值（即id）和文本内容（即省名）
  				//var provinceId = $("#province option:selected").val();
  				//获取下拉框的value属性值，其实就是获取的选中的option的value属性值
  				var provinceId = $("#province").val();
  				province = $("#province option:selected").text();
  				//若选中的不是"——请选择——"，则使用Ajax加载市
  				if(provinceId != "-1"){
	  				$.ajax({
	  					type:"post",
		  				url:"<%=request.getContextPath()%>/cityServlet",
		  				dataType:"json",
		  				data:{pid:provinceId},
		  				success:function (obj){
			  				//解析json数据
		  					for(var i in obj){
		  						var json = obj[i];
		  						//将市的id赋值给option的value值，cityname（市名）作为文本内容显示，将创建好的option元素动态追加的市下拉框中
		  						$("#city").append("<option value="+json.id+">"+json.cityname+"</option>");
		  					}
		  				}
	  				});
	  				//把获得的省名赋值给地址文本框
	  				$("#address").val(province);
  				}else{
  					//若省选中的为"——请选择——"，则市和县都发生变化，地址文本框没有值
  					$("#address").val("");
  				}
  			});
  			
  			//市下拉列表内容改变事件，县发生相应变化
  			$("#city").change(function (){
  				//将县下拉列表框的长度固定为1，即只显示"——请选择——"
  				$("#country").get(0).length = 1;
  				//获取下拉框选中选项的value值（即id）和文本内容（即市名）
  				var cityId = $("#city option:selected").val();
  				city = $("#city option:selected").text();
  				//若选中的不是"——请选择——"，则使用Ajax加载县
  				if(cityId != "-1"){
  					$.ajax({
	  					type:"post",
		  				url:"<%=request.getContextPath()%>/cityServlet",
							dataType : "json",
							data : {
								pid : cityId
							},
							success : function(obj) {
								//解析json数据
								for ( var i in obj) {
									var json = obj[i];
									//将县的id赋值给option的value值，cityname（县名）作为文本内容显示，将创建好的option元素动态追加的县下拉框中
									$("#country").append(
											"<option value="+json.id+">"
													+ json.cityname
													+ "</option>");
								}
							}
						});
						//把获得的省名和市名赋值给地址文本框
						$("#address").val(province + city);
					} else {
						//若市选中的为"——请选择——"，则县发生变化，地址文本框只显示省
						$("#address").val(province);
					}
				});

		//县下拉列表内容改变事件
		$("#country").change(function() {
			//获取下拉框选中选项的value值（即id）和文本内容（即市名）
			var countryId = $("#country option:selected").val();
			country = $("#country option:selected").text();
			//若选中的不是"——请选择——"，把获得的省名、市名和县名赋值给地址文本框
			if (countryId != "-1") {
				$("#address").val(province + city + country);
			} else {
				//若选中的是"——请选择——"，地址文本框只显示省和市
				$("#address").val(province + city);
			}
		});
	});
</script>
</head>
<body>
	<div id="header" class="wrap">
		<div id="logo">
			<img src="<%=request.getContextPath() %>/images/logo.gif" />
		</div>
		<div class="help">
		<img src="<%=request.getContextPath()%>/images/home_ico.gif"/><a href="<%=request.getContextPath() %>/index.jsp">返回前台页面</a>
		</div>
		<div class="navbar">
			<ul class="clearfix">
				<li><a href="<%=request.getContextPath() %>/manage/index.jsp" onclick="selectCategory(1)">首页</a></li>
				<li class="current"><a href="<%=request.getContextPath() %>/getAllUser?action=first">用户</a></li>
				<c:if test="${status eq 2 }"><li><a href="<%=request.getContextPath() %>/product_servlet?action=first">商品</a></li></c:if>
				<li><a href="<%=request.getContextPath() %>/orderServlet?action=first">订单</a></li>
				<c:if test="${status eq 2 }"><li><a href="<%=request.getContextPath() %>/manage/guestbook.jsp">留言</a></li>
				<li><a href="<%=request.getContextPath() %>/manage/news.jsp">新闻</a></li>
				<li><a href="<%=request.getContextPath()%>/documentServlet?action=first">文档</a></li>
				<li><a href="<%=request.getContextPath()%>/getAllUser?action=firsts">排行</a></li></c:if>
			</ul>
		</div>
	</div>
	<%@ include file="head.jsp"%>
	<div id="main" class="wrap">
		<%@ include file="left.jsp"%>
		<div class="main">
			<h2>新增用户</h2>
			<div class="manage">
				<form id="myforms" action="<%=request.getContextPath() %>/ManageuserByAdmin_servlet?action=add" method="post">
				<!-- onsubmit="return check()" -->
					<table class="form" >
						<tr>
							<td class="field">用户名:</td>
							<td><input type="text" class="text" id="userId"
								name="userId" placeholder="请输入用户名" autocomplete="off"/> <span></span>
							</td>
						</tr>
						<tr>
							<td class="field">真实姓名:</td>
							<td><input type="text" class="text" id="userName"
								name="userName" placeholder="请输入真实姓名" autocomplete="off"/> <span></span>
							</td>
						</tr>
						<tr>
							<td class="field">密码:</td>
							<td><input type="password" class="text" id="password"
								name="password" placeholder="请输入密码" autocomplete="off"/> <span></span>
							</td>
						</tr>
						<tr>
							<td class="field">确认密码:</td>
							<td><input type="password" class="text" id="confirmPassword"
								name="confirmPassword" placeholder="请输入确认密码" autocomplete="off"/> <span></span>
							</td>
						</tr>
						<tr>
							<td class="field">性别:</td>
							<td><input type="radio" name="sex" value="T"
								checked="checked" />男 <input type="radio" name="sex" value="F" />女</td>
						</tr>
						<tr>
							<td class="field"><img src="<%=request.getContextPath() %>/images/date.gif"/>出生日期:</td>
							<td><input type="text" name="birthday"
								onclick="WdatePicker({skin:'whyGreen'})" id="birthday"
								class="text" class="Wdate" placeholder="请输入出生日期" autocomplete="off"/> <span></span>
							</td>
						</tr>
						<tr>
							<td class="field" id="identity">身份证号:</td>
							<td><input class="text" type="text"  id="identityCode" name="identityCode" placeholder="请输入身份证号" autocomplete="off"/><span></span>
							</td>
						</tr>
						<tr>
							<td class="field"><img src="<%=request.getContextPath() %>/images/email.gif"/>Email邮箱:</td>
							<td><input class="text" type="text" id="auto-complete-email" name="email" placeholder="请输入Email邮箱" autocomplete="off"/><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">手机号码:</td>
							<td><input type="text" class="text" id="mobile"
								name="mobile" placeholder="请输入手机号码" autocomplete="off"/> <span></span>
							</td>
						</tr>
							<tr>
								<td class="field">手机注册验证码:</td>
								<td><input type="text" class="text" id="mcode"
									name="mcode" onblur="check()" placeholder="请输入手机短信注册码"
									autocomplete="off" /> <label class="ui-blue"><input type="button" class="point" value="免费获取验证码"
									id="zphone" name="zphone" onclick="get_mobile_code()" /></label><span></span></td>
							</tr>
						<tr>
							<td class="field">请选择送货地址:</td>
							<td><select id="province">
									<option value="-1">——请选择——</option>
							</select> <select id="city">
									<option value="-1">——请选择——</option>
							</select> <select id="country">
									<option value="-1">——请选择——</option>
							</select>
							</td>
						</tr>
						<tr>
							<td class="field">送货地址:</td>
							<td><input type="text" class="text" name="address" value=""
								id="address" placeholder="请选择送货地址" autocomplete="off"/> <span></span>
							</td>
						</tr>
						<tr>
							<td class="field">找回密码问题一:</td>
							<td><select id="eu_question" name="eu_question">
									<option value="">——请选择——</option>
							<c:forEach items="${qList}" var="q">
									<option value="${q.eu_question}">${q.eu_question}</option>
							</c:forEach>
							</select><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">请输入提示答案:</td>
							<td><input class="text" type="text" id="eu_answer" name="eu_answer" placeholder="请输入提示答案" autocomplete="off"/><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">找回密码问题二:</td>
							<td><select id="eu_question1" name="eu_question1">
									<option value="">——请选择——</option>
							<c:forEach items="${qList}" var="q">
									<option value="${q.eu_question}">${q.eu_question}</option>
							</c:forEach>
							</select><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">请输入提示答案:</td>
							<td><input class="text" type="text" id="eu_answer1" name="eu_answer1" placeholder="请输入提示答案" autocomplete="off"/><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">找回密码问题三:</td>
							<td><select id="eu_question2" name="eu_question2">
									<option value="">——请选择——</option>
							<c:forEach items="${qList}" var="q">
									<option value="${q.eu_question}">${q.eu_question}</option>
							</c:forEach>
							</select><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">请输入提示答案:</td>
							<td><input class="text" type="text" id="eu_answer2" name="eu_answer2" placeholder="请输入提示答案" autocomplete="off"/><span></span>
							</td>
						</tr>
						<tr>
							<td></td>
							<td><label class="ui-blue"><input type="submit" class="point"
									name="submit" value="添加用户" /> </label> <label class="ui-blue"><input type="reset" class="point" value="清空"/></label> <label class="ui-blue"><a
									href="<%=request.getContextPath() %>/manage/user.jsp"><input type="button"
										name="button" class="point" value="返回" />
								</a> </label>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<%@ include file="/topbottom.jsp"%>
	<%@ include file="/footer-y.jsp"%>
	<c:if test="${success ne null }">
		<script>
			alert("${success }");
		</script>
		<%
			session.removeAttribute("success");
		%>
		<script>
			location.href = "user.jsp";
		</script>
	</c:if>
	<c:if test="${message ne null }">
		<script>
			alert("${message }");
		</script>
	</c:if>
</body>
</html>
<%
	session.removeAttribute("message");
%>