<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>轻松购 - 公司简介</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/function.js"></script>
<Style type="text/css">
.clearfix {
	margin: 0px auto;
}

#logo {
	text-align: center;
}

#menu {
	height: 29px;
	width: 100%;
	border-bottom: 1px solid #dde3e4;
	overflow: hidden;
	background: #f0f5f6;
}

#logo {
	width: 982px;
	overflow: hidden;
	margin: 0 auto;
	line-height: 29px;
}
</style>
<script type="text/javascript">
	var n=0;
	function title(){
	n++;
	if(n==3){n=1;}
	if(n==1){document.title="EasyBuy";}
	if(n==2){document.title="轻松购 - 公司简介";}
	setTimeout("title()",1000);
	}
	title();
	</script>
</head>
<body bgcolor="#FFFFFF" text="#000000">
	<form action="<%=request.getContextPath() %>/index.jsp"  method="post" >
		<center>
			<div id="top" class="clearfix">
				<div>
					<span class=L><b>服务条款</b>
					</span>
				</div>
			</div>
			<div id=nav class="clearfix">
				<img src="<%=request.getContextPath() %>/images/addScript_ico.gif"/>&nbsp;我的位置：<a href="index.jsp">首页</a>&gt; 公司简介
			</div>
			<div align="center">
				<table border=0 width=760 cellspacing="1" cellpadding="8"
					bgcolor=#000000>
					<tr bgcolor=#FFFFCC>
						<td align=center>中国EasyBuy购物网站公司简介</td>
					</tr>
					<tr bgcolor=ffffff>
						<td align="center"><br />
						<b>了解了解EasyBuy本公司</b>
						<p>
								<font size="2">中国EasyBuy购物网站</font>。<br />
								<textarea rows="30" name="service" cols="110"
									style="font-family: 宋体; font-size: 10pt" readonly="readonly">

　　 公司介绍

    轻松购是名人电脑科技有限公司旗下的电子商务网站之一，致力于电脑及数码产品的电子商务。

    名人电脑科技有限公司经多年的发展已经成为集软件设计开发、电脑硬件销售、网络综合布线、软硬件维修为一体的信息技术的解决方案提供商，惠州地区IT行业的知名企业，是北京市政府采购协议供应商。公司旗下有飓风和创恒实体门店经营部，开设有多种网络电子商务平台。公司的生存的基础是不断地发展我们的员工和领导力，不断创造我们在技术和产品的核心竞争力，不断提高我们客户的满意度，从而为公司带来更好的效益和更长久的发展。

    本公司设备齐全，技术力量雄厚，在为用户创造更多价值的同时，名人电脑科技更是致力于为不同群体的用户提供更高的应用需求，极力推动新产品普及及公司的发展步伐。在品牌的规划及产品导入中，名人电脑科技不断的注入新的元素。经多年的发展，Intel(英特尔)原装CPU三星级合作伙伴；名人电脑科技先后成为ACER（宏碁）电脑、DELL（戴尔）电脑分销商；飞利浦液晶显示器及电脑周边产品总代理；冠捷（AOC）液晶显示器及电脑周边产品银牌代理；三星显示器银牌经销商，华硕全系列、技嘉、映泰主板、影驰显卡核心代理；TP-LINK、水星网络设备特约经销商；长城电源核心经销商；HP、双飞燕键盘鼠标系列核心经销商、SONY 、名人光存储系列核心经销商。在为用户提供高性价比产品的同时，更注重于为用户提供全心全意的服务，把客户当成我们的朋友。名人电脑科技拥有一支强有力的技术服务和销售团体，兢兢业业，勇于创新，抱着与各个厂家和商家团结、合作、开拓、发展的经营宗旨，注重信誉，强调服务，以忘我的敬业精神和精湛的技术为用户提供服务，立志成为首屈一指的专业化IT公司。

    我们一直追求能让您满意的产品品质和服务，相信有您的支持我们会做的更好。

 
发展历程
发展历程
联系我们
  

北京总部：

电 话：+86-020-8888888、66666666、11111111

传 真：85599577

惠州分部：

电 话：+86-0752-6561010、6622990、6299855、6772173、62412473
传 真：0752-6623102

							中国EasyBuy购物网站
							公布日期：2016年10月10日
</textarea>
							<p>
								<br/><label class="ui-green"><a><input name="submit"
										 type="submit" value="确定">
								</a>
								</label>
							</p></td>
					</tr>
				</table>
				<br/>
			</div>
		</center>
	</form>
</body>
</html>
