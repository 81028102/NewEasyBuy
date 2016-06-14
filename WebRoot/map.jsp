<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>小东地图</title>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2"></script>
</head>
<body style="padding-left: 220px;">
<a href="<%=request.getContextPath()%>/indexServlet?action=first">返回首页</a>
<div style="width:1000px;height:700px;border:1px solid gray" id="container"></div>
</body>
</html>
<script type="text/javascript">
var map =new BMap.Map("container");
var point =new BMap.Point(116.404, 39.915);
map.centerAndZoom(point, 14);
var myIcon =new BMap.Icon("<%=request.getContextPath() %>/images/github.gif", new BMap.Size(32, 70), {    //小车图片
    offset: new BMap.Size(0, -5),    //相当于CSS精灵
    imageOffset: new BMap.Size(0, 0)    //图片的偏移量。为了是图片底部中心对准坐标点。
  });

var points = [new BMap.Point(116.411776,39.942833),new BMap.Point(116.320791,40.003682),new BMap.Point(116.275186,39.896095),new BMap.Point(116.425098,39.946249),new BMap.Point(116.359823,39.984761),new BMap.Point(116.316479,39.98323),new BMap.Point(116.385986,39.946124),new BMap.Point(116.427545,40.00796),new BMap.Point(116.446965,39.911603),new BMap.Point(116.454579,39.946652)];   //10个坐标点

var marker1 =new BMap.Marker(points[1],{icon:myIcon});  // 创建10个标注
var marker2 =new BMap.Marker(points[2],{icon:myIcon});
var marker3 =new BMap.Marker(points[3],{icon:myIcon});
var marker4 =new BMap.Marker(points[4],{icon:myIcon});
var marker5 =new BMap.Marker(points[5],{icon:myIcon});
var marker6 =new BMap.Marker(points[6],{icon:myIcon});
var marker7 =new BMap.Marker(points[7],{icon:myIcon});
var marker8 =new BMap.Marker(points[8],{icon:myIcon});
var marker9 =new BMap.Marker(points[9],{icon:myIcon});
var marker0 =new BMap.Marker(points[0],{icon:myIcon});

map.addOverlay(marker1);              // 将标注添加到地图中
map.addOverlay(marker2);
map.addOverlay(marker3);
map.addOverlay(marker4);
map.addOverlay(marker5);
map.addOverlay(marker6);
map.addOverlay(marker7);
map.addOverlay(marker8);
map.addOverlay(marker9);
map.addOverlay(marker0);

map.setViewport(points);         //调整地图的最佳视野为显示标注数组point

var opts1 = {title : '<span style="font-size:14px;color:#0A8021">如家快捷酒店</span>'};
var opts2 = {title : '<span style="font-size:14px;color:#0A8021">昆仑酒店</span>'};
var opts3 = {title : '<span style="font-size:14px;color:#0A8021">华夏小吃</span>'};
var opts4 = {title : '<span style="font-size:14px;color:#0A8021">成都小吃</span>'};
var opts5 = {title : '<span style="font-size:14px;color:#0A8021">锦绣大饭店</span>'};
var opts6 = {title : '<span style="font-size:14px;color:#0A8021">七天快捷酒店</span>'};
var opts7 = {title : '<span style="font-size:14px;color:#0A8021">中央民族大学</span>'};
var opts8 = {title : '<span style="font-size:14px;color:#0A8021">哇哈哈路鲜鱼</span>'};
var opts9 = {title : '<span style="font-size:14px;color:#0A8021">百度酒店</span>'};
var opts0 = {title : '<span style="font-size:14px;color:#0A8021">驴肉火烧</span>'};

var infoWindow1 =new BMap.InfoWindow("<div style='line-height:1.8em;font-size:12px;'><b>地址:</b>北京市朝阳区高碑店小学旁</br><b>电话:</b>010-59921010</br><b>口碑：</b><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><a style='text-decoration:none;color:#2679BA;float:right' href='#'>详情>></a></div>", opts1);  // 创建信息窗口对象，引号里可以书写任意的html语句。
var infoWindow2 =new BMap.InfoWindow("<div style='line-height:1.8em;font-size:12px;'><b>地址:</b>北京市海淀区紫竹院123号</br><b>电话:</b>010-59921010</br><b>口碑：</b><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><a style='text-decoration:none;color:#2679BA;float:right' href='#'>详情>></a></div>", opts2);
var infoWindow3 =new BMap.InfoWindow("<div style='line-height:1.8em;font-size:12px;'><b>地址:</b>北京市海淀区紫竹院123号</br><b>电话:</b>010-59921010</br><b>口碑：</b><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><a style='text-decoration:none;color:#2679BA;float:right' href='#'>详情>></a></div>", opts3);
var infoWindow4 =new BMap.InfoWindow("<div style='line-height:1.8em;font-size:12px;'><b>地址:</b>北京市海淀区紫竹院123号</br><b>电话:</b>010-59921010</br><b>口碑：</b><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><a style='text-decoration:none;color:#2679BA;float:right' href='#'>详情>></a></div>", opts4);
var infoWindow5 =new BMap.InfoWindow("<div style='line-height:1.8em;font-size:12px;'><b>地址:</b>北京市朝阳区高碑店小学旁</br><b>电话:</b>010-59921010</br><b>口碑：</b><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><a style='text-decoration:none;color:#2679BA;float:right' href='#'>详情>></a></div>", opts5);
var infoWindow6 =new BMap.InfoWindow("<div style='line-height:1.8em;font-size:12px;'><b>地址:</b>北京市大钟寺沧澜大厦</br><b>电话:</b>010-59921010</br><b>口碑：</b><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><a style='text-decoration:none;color:#2679BA;float:right' href='#'>详情>></a></div>", opts6);
var infoWindow7 =new BMap.InfoWindow("<div style='line-height:1.8em;font-size:12px;'><b>地址:</b>北京市朝阳区高碑店小学旁</br><b>电话:</b>010-59921010</br><b>口碑：</b><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><a style='text-decoration:none;color:#2679BA;float:right' href='#'>详情>></a></div>", opts7);
var infoWindow8 =new BMap.InfoWindow("<div style='line-height:1.8em;font-size:12px;'><b>地址:</b>北京市哇哈哈路鲜鱼一条街</br><b>电话:</b>010-59921010</br><b>口碑：</b><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><a style='text-decoration:none;color:#2679BA;float:right' href='#'>详情>></a></div>", opts8);
var infoWindow9 =new BMap.InfoWindow("<div style='line-height:1.8em;font-size:12px;'><b>地址:</b>北京市朝阳区高碑店小学旁</br><b>电话:</b>010-59921010</br><b>口碑：</b><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><a style='text-decoration:none;color:#2679BA;float:right' href='#'>详情>></a></div>", opts9);
var infoWindow0 =new BMap.InfoWindow("<div style='line-height:1.8em;font-size:12px;'><b>地址:</b>北京市朝阳区高碑店小学旁</br><b>电话:</b>010-59921010</br><b>口碑：</b><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><img src='http://cdn2.iconfinder.com/data/icons/diagona/icon/16/031.png' /><a style='text-decoration:none;color:#2679BA;float:right' href='#'>详情>></a></div>", opts0);

marker1.addEventListener("mouseover", function(){this.openInfoWindow(infoWindow1);});
marker2.addEventListener("mouseover", function(){this.openInfoWindow(infoWindow2);});
marker3.addEventListener("mouseover", function(){this.openInfoWindow(infoWindow3);});
marker4.addEventListener("mouseover", function(){this.openInfoWindow(infoWindow4);});
marker5.addEventListener("mouseover", function(){this.openInfoWindow(infoWindow5);});
marker6.addEventListener("mouseover", function(){this.openInfoWindow(infoWindow6);});
marker7.addEventListener("mouseover", function(){this.openInfoWindow(infoWindow7);});
marker8.addEventListener("mouseover", function(){this.openInfoWindow(infoWindow8);});
marker9.addEventListener("mouseover", function(){this.openInfoWindow(infoWindow9);});
marker0.addEventListener("mouseover", function(){this.openInfoWindow(infoWindow0);});
</script>