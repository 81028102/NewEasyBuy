<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
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
$(function(){
	//反选
	$("#fx").click(function(){
	  $(".cks").each(function(){
	    if($(this).attr("checked")){
		  $(this).attr("checked",false);
	    }else{
	      $(this).attr("checked",true);
	    }
	    });
	  });
	  //全选/全不选
	  $("#x").toggle(function(){
	    $(".cks").attr("checked",true);
	  },
	  function(){
	   $(".cks").attr("checked",false);
	  });
	  //checked按钮
	   $("#ck").click(function(){
  			if($("#ck").attr("checked")){
  				$(".cks").attr("checked",true);
  			}else{
  				$(".cks").attr("checked",false);
  			}
  		});
  		//批量删除
	$("#delete").click(function(){
		var id="";
		$(".cks:checked").each(function(){
			id+=","+$(this).val();
		});
		enc_Id=id.substring(1);
		if(enc_Id!=""){
			if(confirm("确定要删除吗?请谨慎删除哦!")){
				location.href="<%=request.getContextPath() %>/NewsServlet?action=delNewsColumnById&enc_id="+enc_Id;
			}
		}else{
			alert("对不起,请指定删除!");
		}
		});
	});
		//分页跳转
  	function paging(cpage){
  		$("#cpage").val(cpage);
  		$("form").submit();
  	}
		var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 栏目管理";}
	setTimeout("title()",1000);
	}
	title();
	</script>
<c:if test="${param.message eq 1}">
	<script>
		alert("操作成功！");
	</script>
</c:if>
<c:if test="${param.message eq 0}">
	<script>
		alert("操作失败！");
	</script>
</c:if>
</head>
<body>
<%session.removeAttribute("smessage"); %>
<%session.removeAttribute("ssb"); %>
<%session.removeAttribute("sbool"); %>
<div id="header" class="wrap">
	<div id="logo"><img src="<%=request.getContextPath() %>/images/logo.gif" /></div>
	<div class="help"><img src="<%=request.getContextPath()%>/images/home_ico.gif"/><a href="<%=request.getContextPath()%>/index.jsp">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="<%=request.getContextPath()%>/manage/index.jsp">首页</a></li>
			<li class="current"><a href="<%=request.getContextPath()%>/getAllUser?action=first">用户</a></li>
			<c:if test="${status eq 2 }"><li><a href="<%=request.getContextPath()%>/product_servlet?action=first">商品</a></li></c:if>
			<li><a href="<%=request.getContextPath()%>/orderServlet?action=first" >订单</a></li>
			<c:if test="${status eq 2 }"><li><a href="<%=request.getContextPath()%>/commentServlet?action=first&h=hou" >留言</a></li>
			<li><a href="<%=request.getContextPath()%>/NewsServlet?action=first">新闻</a></li>
			<li><a href="<%=request.getContextPath()%>/documentServlet?action=first">文档</a></li>
			<li><a href="<%=request.getContextPath()%>/getAllUser?action=firsts">排行</a></li></c:if>
		</ul>
	</div>
</div>
<%@ include file="head.jsp" %>
<div id="main" class="wrap">
	<%@ include file="left.jsp" %>
	
	<div class="main">
		<h2>用户管理<a style="color: red;text-decoration: none;margin-left: 410px;">温馨提示:请谨慎删除,删除后不可恢复哦!</a></h2>
		<div class="manage">
		<form action="<%=request.getContextPath()%>/ManageuserByAdmin_servlet?action=sstatus" method="post">
			<table style="margin-left: -11px;"><tr align="right"><td>
  			<input type="hidden" name="cpage" id="cpage" value="${cpage}" />
  			状态名称:<input type="text" class="text" name="eus_StatusName" id="eus_StatusName" value="${eus_StatusName}" placeholder="请输入状态名称(支持模糊查询)" autocomplete="off"/>
  			<label class="ui-blue"><input type="submit" class="point" value="搜索"/></label>
  			</td></tr></table></form>
  			<c:if test="${not empty liststatus }">
				<table><tr  align="left"><td><label class="ui-blue"><input type="button" value="反选" id="fx" class="point"/></lable></td>  <td><label class="ui-blue"><input type="button"
				value="全选/全不选" id="x" class="point"/></label></td>  <td><label class="ui-blue"><input type="button" value="批量删除"
				id="delete" class="point"/></label></td> <td><label class="ui-blue"><a class="manageDel" href="<%=request.getContextPath() %>/ManageuserByAdmin_servlet?action=delNewsColumnAll" style="text-decoration:none;"><input type="button" style="color:orange;" value="删除全部" class="point"/></a></label></td></tr></table>
				</c:if>
			<table class="list">
			<tr>	
					<th align="left" style="padding-left: 5px;"><input type="checkbox" id="ck"></th>
					<th>状态ID</th>
					<th>状态名称</th>
					<th><img src="<%=request.getContextPath() %>/images/date.gif"/>创建时间</th>
					<th>操作</th>
				</tr>
			<c:if test="${not empty liststatus }">
			<c:forEach var="i" items="${liststatus }">
				<tr>
					<td><input type="checkbox" class="cks" value="${i.eus_Id }"></td>
					<td style="text-align:center;">${i.eus_Id }</td>
					<td style="text-align:center;">${i.eus_StatusName }</td>
					<td style="text-align:center;">${i.eus_Create_time }</td>
					<td style="text-align:center;">
					<label class="ui-blue"><a class="manageUpd" href="<%=request.getContextPath()%>/ManageuserByAdmin_servlet?action=getNewsColumnById&eus_id=${i.eus_Id }"><input type="button" class="point" value="修改"/></a></label>
					<label class="ui-blue"><a class="manageDel" href="<%=request.getContextPath()%>/ManageuserByAdmin_servlet?action=delNewsColumnById&eus_id=${i.eus_Id }"><input type="button" class="point" value="删除"/></a></label></td>
				</tr>
				</c:forEach>
    	 <tr>
    		<td colspan="10" align="right">
    		当前是<a style="color: blue;text-decoration:none" >${cpage}</a>/<a style="color: blue;text-decoration:none" >${totalPage}</a>页   共<a style="color: blue;text-decoration:none" >${countstatus}</a>条
    		<a class="point" style="color: blue;" onclick="paging(1)">首页</a>
    		<a class="point" style="color: blue;" onclick="paging(${cpage==1?1:cpage-1})">上一页</a>
    		<a class="point" style="color: blue;" onclick="paging(${cpage==totalPage?(totalPage):cpage+1})">下一页</a>
    		<a class="point" style="color: blue;" onclick="paging(${totalPage})">尾页</a>
    		</td>
    	</tr>
				</c:if>
			</table>
		</div>
	</div>
	<div class="clear"></div>
</div>
<%@ include file="/aop.jsp"%> 
</body>
</html>