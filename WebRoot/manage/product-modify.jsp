<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<script type="text/javascript">
		var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 商品管理";}
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
		<img src="<%=request.getContextPath()%>/images/home_ico.gif"/><a href="<%=request.getContextPath()%>/index.jsp">返回前台页面</a>
		</div>
		<div class="navbar">
			<ul class="clearfix">
				<li><a href="<%=request.getContextPath()%>/manage/index.jsp">首页</a></li>
				<li><a href="<%=request.getContextPath()%>/getAllUser?bs=all">用户</a></li>
				<c:if test="${status eq 2 }"><li class="current"><a href="<%=request.getContextPath()%>/product_servlet?action=first">商品</a></li></c:if>
				<li><a href="<%=request.getContextPath()%>/orderServlet?action=getAllorder">订单</a></li>
				<c:if test="${status eq 2 }"><li><a href="<%=request.getContextPath()%>/manage/guestbook.jsp">留言</a></li>
				<li><a href="<%=request.getContextPath()%>/manage/news.jsp">新闻</a></li>
				<li><a href="<%=request.getContextPath()%>/documentServlet?action=first">文档</a></li>
				<li><a href="<%=request.getContextPath()%>/getAllUser?action=firsts">排行</a></li></c:if>
			</ul>
		</div>
	</div>
	<%@ include file="head.jsp"%>

	<div id="main" class="wrap">
		<%@ include file="left.jsp"%>
		<div class="main">
			<h2>修改商品</h2>
			<div class="manage">
				<c:if test="${hproduct ne null }">
					<form id="product-modify"
						action="<%=request.getContextPath()%>/product_servlet?action=update&ep_id=${hproduct.ep_Id}"
						enctype="multipart/form-data" method="post">
						<table class="form">
							<tr>
								<td class="field">商品名称(<a style="color: red;">*</a>):</td>
								<td><input type="text" class="text" id="productName"
									name="productName" value="${hproduct.ep_Name}" placeholder="请输入商品名称" autocomplete="off"/><span></span>
								</td>
							</tr>
							<tr>
								<td class="field">描述(<a style="color: red;">*</a>):</td>
								<td><input type="text" class="text" id="productdesc"
									name="productdesc" value="${hproduct.ep_Description }" placeholder="请输入商品描述" autocomplete="off"/><span></span>
								</td>
							</tr>
							<tr>
								<td class="field">所属分类(<a style="color: red;">*</a>):</td>
								<td><select name="parentId">
										<c:forEach var="one" items="${hone }">
											<option value="${one.epc_Id }">${one.epc_Name }</option>
											<c:forEach var="two" items="${htwo }">
												<c:if test="${two.epc_Parent_id eq one.epc_Id }">
													<option value="${two.epc_Id }"
														<c:if test="${two.epc_Id eq hproduct.epc_Child_id }">selected="selected"</c:if>>└${two.epc_Name
														}</option>
												</c:if>
											</c:forEach>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td class="field">商品价格(<a style="color: red;">*</a>):</td>
								<td><input type="text" class="text tiny" id="productPrice"
									name="productPrice" value="${hproduct.ep_Price}" placeholder="请输入商品价格" autocomplete="off"/> 元<span></span>
								</td>
							</tr>

							<tr>
								<td class="field">库存(<a style="color: red;">*</a>):</td>
								<td><input type="text" class="text tiny" id="productCount"
									name="productCount" value="${hproduct.ep_Stock}" placeholder="请输入库存数量" autocomplete="off" onkeyup="this.value=this.value.replace(/\D/g,'')"
							onafterpaste="this.value=this.value.replace(/\D/g,'')"/><span></span>
								</td>
							</tr>
							<tr>
								<td class="field">商品图片(<a style="color: red;">*</a>):</td>
								<td><input type="file" value="${hproduct.ep_File_name}"  class="text" id="photo" name="photo" /><span></span>
								</td>
							</tr>
							<tr>
							<td class="field">请选择产地:</td>
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
							<td class="field">原产地(<a style="color: red;">*</a>):</td>
							<td><input type="text" class="text" id="address" name="address" value="${hproduct.ep_Address}" placeholder="请输入原产地" autocomplete="off"/>
							<span></span></td>
							</tr>
							<tr>
								<td></td>
								<td><label class="ui-blue"><input type="submit" class="point"
										name="submit" value="更新商品" />
								</label> <label class="ui-blue"><a href="<%=request.getContextPath()%>/manage/product.jsp"><input
											type="button" class="point" name="button" value="返回" />
									</a> </label></td>
							</tr>
						</table>
					</form>
				</c:if>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<%@ include file="/mvc.jsp"%>
	<%@ include file="/aop.jsp"%>
</body>
</html>
