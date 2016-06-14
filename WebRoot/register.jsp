<%@page import="cn.jbit.util.Page"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>轻松购 - 注册</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
<link rel="stylesheet" 
	href="<%=request.getContextPath()%>/css/completer.css" type="text/css"></link>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/password_streng.css" type="text/css"></link>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/password_strength_plugin.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/function.js"></script>
<script type="text/javascript" 
	src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/city.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/completer.min.js"></script>
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
	if(n==2){document.title="轻松购 - 注册";}
	setTimeout("title()",1000);
	}
	title();
	$(function (){
			//三级联动
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
	  				$("#ads").val(province);
  				}else{
  					//若省选中的为"——请选择——"，则市和县都发生变化，地址文本框没有值
  					$("#ads").val("");
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
						$("#ads").val(province + city);
					} else {
						//若市选中的为"——请选择——"，则县发生变化，地址文本框只显示省
						$("#ads").val(province);
					}
				});

		//县下拉列表内容改变事件
		$("#country").change(function() {
			//获取下拉框选中选项的value值（即id）和文本内容（即市名）
			var countryId = $("#country option:selected").val();
			country = $("#country option:selected").text();
			//若选中的不是"——请选择——"，把获得的省名、市名和县名赋值给地址文本框
			if (countryId != "-1") {
				$("#ads").val(province + city + country);
			} else {
				//若选中的是"——请选择——"，地址文本框只显示省和市
				$("#ads").val(province + city);
			}
		});
		
 		$("#sure").click(function(){
    	   if($("#yes").attr("checked")){
         	  location.href="<%=request.getContextPath()%>/register.jsp";
			} else {
				alert("对不起哦,您没有同意服务条款哦,亲");
			}
		});
	});
</script>
</head>
<body>
	<%@ include file="head.jsp"%>
	<div id="register" class="wrap">
		<div class="shadow">
			<em class="corner lb"></em> <em class="corner rt"></em>
			<div class="box">
				<h1>${rwelcome1}</h1>
				<ul class="steps clearfix">
					<li class="current"><em></em>${fillintheregistrationinformation}</li>
					<li class="last"><em></em>${loginwassuccessful}</li>
				</ul>
				<form id="regForm" method="post"
					action="<%=request.getContextPath()%>/AddUser_servlet">
					<table>
						<tr>
							<td class="field">${rname}(<a style="color: red;">*</a>):</td>
							<td><input class="text" type="text" name="userId"
								id="userId" maxlength="10" placeholder="${rpleaseinputname}" autocomplete="off"/> <span></span></td>
						</tr>
						<tr>
							<td class="field">${rrealname}(<a style="color: red;">*</a>):</td>
							<td><input class="text" type="text" name="userName" placeholder="${rpleaseinputrealname}" autocomplete="off"/><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">${rpassword}(<a style="color: red;">*</a>):</td>
							<td><input class="text" type="password" id="password"
								name="password" placeholder="${rpleaseinputpassword}" autocomplete="off"/><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">${raffirmpassword}(<a style="color: red;">*</a>):</td>
							<td><input class="text" type="password"
								name="confirmPassword" placeholder="${rpleaseinputaffirmpassword}" autocomplete="off"/><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">${rsex}(<a style="color: red;">*</a>):</td>
							<td><input class="radio" type="radio" name="sex" value="T"
								checked="checked"> ${rman } <input class="radio" type="radio"
								name="sex" value="F">${rwoman}</td>
						</tr>
						<tr>
							<td class="field"><img src="<%=request.getContextPath() %>/images/date.gif"/>${rbirthday}:</td>
							<td><input type="text" name="birthday"
								onclick="WdatePicker({skin:'whyGreen'})" id="birthday"
								class="text" class="Wdate" placeholder="${rpleaseinputbirthday}" autocomplete="off"/> <span></span>
							</td>
						</tr>
						<tr>
							<td class="field" id="identity">${ridentitycard }(<a style="color: red;">*</a>):</td>
							<td><input class="text" type="text" name="identityCode" placeholder="${rpleaseinputidentitycard}" autocomplete="off"/><span></span>
							</td>
						</tr>
						<tr>
							<td class="field"><img src="<%=request.getContextPath() %>/images/email.gif"/>${remail}(<a style="color: red;">*</a>):</td>
							<td><input class="text" type="text" id="auto-complete-email" name="email" placeholder="${rpleaseinputemail}" autocomplete="off"/><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">${rphone}(<a style="color: red;">*</a>):</td>
							<td><input class="text" type="text" id="mobile" name="mobile" placeholder="${rpleaseinputphone}" autocomplete="off" /><span></span>
							</td>
						</tr>
							<tr>
								<td class="field">${SMSverificationcodes}:</td>
								<td><input type="text" class="text" id="mcode"
									name="mcode" onblur="check()" placeholder="${PleaseentertheSMSverificationcode }"
									autocomplete="off" />&nbsp;<label class="ui-blue"> <input type="button" class="point" value="免费获取验证码"
									id="zphone" name="zphone" onclick="get_mobile_code()" /></label><span></span></td>
							</tr>
						<tr>
							<td class="field">${rchooseaddress}:</td>
							<td><select id="province">
									<option value="-1">${rpleasechoose}</option>
							</select> <select id="city">
									<option value="-1">${rpleasechoose}</option>
							</select> <select id="country">
									<option value="-1">${rpleasechoose}</option>
							</select></td>
						</tr>
						<tr>
							<td class="field">${raddress}(<a style="color: red;">*</a>):</td>
							<td><input type="text" class="text" id="ads" name="address" placeholder="${rpleaseinputaddress}" autocomplete="off"/><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">${ronequestion}(<a style="color: red;">*</a>):</td>
							<td><select id="eu_question" name="eu_question">
									<option value="">${rpleasechoose1}</option>
							<c:forEach items="${qList}" var="q">
									<option value="${q.eu_question}">${q.eu_question}</option>
							</c:forEach>
							</select><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">${ranswer}(<a style="color: red;">*</a>):</td>
							<td><input class="text" type="text" id="eu_answer" name="eu_answer" placeholder="请输入提示答案" autocomplete="off"/><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">${rtwoquestion}(<a style="color: red;">*</a>):</td>
							<td><select id="eu_question1" name="eu_question1">
									<option value="">${rpleasechoose1}</option>
							<c:forEach items="${qList}" var="q">
									<option value="${q.eu_question}">${q.eu_question}</option>
							</c:forEach>
							</select><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">${ranswer}(<a style="color: red;">*</a>):</td>
							<td><input class="text" type="text" id="eu_answer1" name="eu_answer1" placeholder="${rpleaseinputhintanswer}" autocomplete="off"/><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">${rthreequestion}(<a style="color: red;">*</a>):</td>
							<td><select id="eu_question2" name="eu_question2">
									<option value="">${rpleasechoose1}</option>
							<c:forEach items="${qList}" var="q">
									<option value="${q.eu_question}">${q.eu_question}</option>
							</c:forEach>
							</select><span></span>
							</td>
						</tr>
						<tr>
							<td class="field">${ranswer}(<a style="color: red;">*</a>):</td>
							<td><input class="text" type="text" id="eu_answer2" name="eu_answer2" placeholder="${rpleaseinputhintanswer}" autocomplete="off"/><span></span>
							</td>
						</tr>
						<tr>
							<td></td>
							<td><input id="yes" name="yes" type="checkbox">${rreadagree}<a
								href="<%=request.getContextPath()%>/companyProfile.jsp"
								style="color: blue;">《${rregistertreaty}》</a></td>
						</tr>
						<tr>
							<td></td>
							<td><label
								class="ui-blue"><input id="sure" type="submit" class="point"
									name="submit" value="${Register}" /> </label>&nbsp;&nbsp;&nbsp;<label class="ui-blue"><a
								href="<%=request.getContextPath()%>/index.jsp"><input
								type="button" class="point" name="button" value="${returns2}" /> </a> </label>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<%@ include file="topbottom.jsp"%>
	<%@ include file="footer-y.jsp"%>
	<c:if test="${nosuccess ne null }">
		<script>
			alert("${nosuccess}");
		</script>
		<%
			session.removeAttribute("nosuccess");
		%>
	</c:if>
</body>
</html>
