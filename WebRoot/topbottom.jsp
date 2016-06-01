<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/function.js"></script>
<style type="text/css">
.go{width:47px;height:106px;position:fixed;_position:absolute;_top:expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight-this.offsetHeight-(parseInt(this.currentStyle.marginTop,10)||200)-(parseInt(this.currentStyle.marginBottom,10)||0)));right:12px;bottom:25%; background-image:url("images/tobg.png"); background-repeat:no-repeat;}
.go a{background:url(images/a.png) no-repeat;display:block;text-indent:999em;width:37px;margin:5px;border:0;overflow:hidden;float:left; cursor:pointer;}
.go .top{background-position:0 0px;height:22px}
.go .feedback{background-position:0 -22px;height:32px}
.go .bottom{background-position:0 -55px;height:22px}
.go .top:hover{background-position:-38px -0px}
.go .feedback:hover{background-position:-38px -22px}
.go .bottom:hover{background-position:-38px -55px}
</style>
<script type="text/javascript">
 $(function () {
  $(".top").click(//定义返回顶部点击向上滚动的动画
  function () {
   $("html,body").animate({ scrollTop: 0 }, 700);
  });
  $(".bottom").click(//定义返回顶部点击向上滚动的动画
  function () {
   $("html,body").animate({ scrollTop: document.body.clientHeight }, 700);
  });
 });
</script>
</head>
<body>
<div id="main">
<div class="go">
  <a title="${Returntothetop}" class="top"></a>
  <a title="${Ifyouhaveanyconcern},${Pleasefeedbacktous}!" class="feedback" href="<%=request.getContextPath() %>/sendemail.jsp"></a>
  <a title="${Atthebottomoftheback}" class="bottom" ></a>
</div>
</div>
</body>
</html>
