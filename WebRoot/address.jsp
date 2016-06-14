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

<title>轻松购 - 首页</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/function.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery.validate.js"></script>
<script>
		var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 添加地址";}
	setTimeout("title()",1000);
	}
	title();
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
	  				$("#addAddr").val(province);
  				}else{
  					//若省选中的为"——请选择——"，则市和县都发生变化，地址文本框没有值
  					$("#addAddr").val("");
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
						$("#addAddr").val(province + city);
					} else {
						//若市选中的为"——请选择——"，则县发生变化，地址文本框只显示省
						$("#addAddr").val(province);
					}
				});

		//县下拉列表内容改变事件
		$("#country").change(function() {
			//获取下拉框选中选项的value值（即id）和文本内容（即市名）
			var countryId = $("#country option:selected").val();
			country = $("#country option:selected").text();
			//若选中的不是"——请选择——"，把获得的省名、市名和县名赋值给地址文本框
			if (countryId != "-1") {
				$("#addAddr").val(province + city + country);
			} else {
				//若选中的是"——请选择——"，地址文本框只显示省和市
				$("#addAddr").val(province + city);
			}
		});
	});
</script>
</head>

<body>
	<%@ include file="head.jsp"%>
	<div id="position0" class="wrap">
		<img src="<%=request.getContextPath() %>/images/addScript_ico.gif"/>&nbsp;${Yourpresentposition}:<a
			href="<%=request.getContextPath()%>/index.jsp">${easybuy}</a> &gt;
		${Theshoppingcart}
	</div>
	<div id="main" class="wrap">
		<%@ include file="left.jsp"%>
		<div class="main">
		<h2>购物结算</h2>
		</div>
	</div><br/><br/>
	<div id="news" class="right-main" align="center">
		<div class="content">
		<form action="<%=request.getContextPath()%>/shoppingServlet?action=buy" method="post">
		<table align="center">
		<tr><td>
			<img src="<%=request.getContextPath() %>/images/smile.gif"/>&nbsp;&nbsp;&nbsp;${Pleaseclickonthefollowingshippingaddress},${Pro}:<select
				id="province">
				<option value="-1">${rpleasechoose}</option>
			</select> <select id="city">
				<option value="-1">${rpleasechoose}</option>
			</select> <select id="country">
				<option value="-1">${rpleasechoose}</option>
			</select> <br/><br/><img src="images/light.gif"/>&nbsp;&nbsp;&nbsp;${raddress}:(${Canalsoaddnewaddress},${Pro})<input
				name="addr" id="addr" type="button" value="添加新地址" /><span id="span"></span>
			</td></tr>
				<tr><td><c:if test="${address ne null }">
								<c:forEach var="i" items="${address}" varStatus="status">
									&nbsp;<img src="<%=request.getContextPath() %>/images/house.gif"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
										<c:if test="${status.index eq 0 }">checked="checked"</c:if>
										name="address" id="address${status.index}" value="${i}"
										type="radio" />
									<span>${i}</span>
									<br />
								</c:forEach>
							</c:if></td>
					</tr><br/>
					<tr>
						<td><br/><br/>
							<div class="button">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="image" src="<%=request.getContextPath()%>/images/product/bt.png" value="" />
							</div></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
	<div id="position1" class="wrap"></div>
	<div class="wrap">
		<div id="shopping"></div>
	</div>
	<%@ include file="mvc.jsp"%>
	<%@ include file="aop.jsp"%>
	<%@ include file="qrcode.jsp" %>
	<%@ include file="topbottom.jsp" %>
</body>
</html>
