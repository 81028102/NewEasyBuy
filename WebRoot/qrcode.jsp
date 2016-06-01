<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.8.2.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/index.css" type="text/css"></link>
</head>
<body>
	<div class="service_box">
		<ul class="tab_nav">
			<li><a href="tencent://message/?uin=981028102&Site=121ask.com&Menu=yes"><i class="icon icon_1"></i>${service}</a>
			</li>
			<li><a href="https://wx.qq.com/"><i class="icon icon_3"></i>
			</a>
			</li>
		</ul>
		<div class="tab_content">
			<div class="tab_plan">
				<p class="fs14">
					${services}1:<br /> <a 
						href="tencent://message/?uin=981028102&Site=121ask.com&Menu=yes"
						class="qq_link"><img border="0"
						src="http://wpa.qq.com/pa?p=2:2833026848:51" alt="${Clickheretosendamessagetome }"
						title="${Clickheretosendamessagetome }">
					</a>
				</p>
				<p class="fs14 mt10">
					${services}2:<br /> <a 
						href="tencent://message/?uin=321987358&Site=121ask.com&Menu=yes"
						class="qq_link"><img border="0"
						src="http://wpa.qq.com/pa?p=2:123456789:51" alt="${Clickheretosendamessagetome }"
						title="${Clickheretosendamessagetome }">
					</a>
				</p>
				<p class="fs14 mt10">
					${services1 }:<br /> <a 
						href="tencent://message/?uin=15714638&Site=121ask.com&Menu=yes"
						class="qq_link"><img border="0"
						src="http://wpa.qq.com/pa?p=2:2833026848:51" alt="${Clickheretosendamessagetome }"
						title="${Clickheretosendamessagetome }">
					</a>
				</p>
				<p class="mt10">
					${servicetime}:<br/> 9:00-18:00(${workday})<br />
				</p>
			</div>
			<div class="tab_plan" style="display: none;">
				<img src="<%=request.getContextPath() %>/images/qrcode.png" alt="${TheofficialWeChatselfhelpcustomerservice}" title="${TheofficialWeChatselfhelpcustomerservice}" width="130" height="130">
				<p style="color: red;font-size:15px;" >${Isthereaspecialratescanattentionoh }</p>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(".service_box .tab_nav li").hover(function() {
			var i = $(this).index();
			$(this).addClass("active").siblings().removeClass("active");
			$(".service_box .tab_plan:eq(" + i + ")").show().siblings().hide();
		});
		$(function() {
			var t;
			$(".service_box").hover(
					function() {
						$(".service_box").animate({
							right : 0
						}, 300);
						clearTimeout(t);
					},
					function() {
						t = setTimeout(function(e) {
							$(".service_box").animate({
								right : "-146px"
							}, 300);
							$(".service_box").find(".tab_nav li").removeClass(
									"active");
						}, 1000);
					});
		});
		var isTransition = true;
		$(".m_logo")
				.hover(
						function() {
							var r = 0;
							if (isTransition) {
								animateTime = setInterval(
										function() {
											if (r >= 153) {
												clearInterval(animateTime);
												isTransition = true;
											} else {
												isTransition = false;
												r++;
												$(".m_logo a")
														.attr(
																"style",
																"-webkit-mask:-webkit-gradient(radial, 45 25, "
																		+ r
																		+ ", 45 25, "
																		+ (r + 15)
																		+ ", from(rgb(0, 0, 0)), color-stop(0.5, rgba(0, 0, 0, 0.2)), to(rgb(0, 0, 0)));");
											}
											;
										}, 5);
							}
						}, function() {
							return;
						});
	</script>
	<!--[if lt IE 7]>
<script type="text/javascript">
	window.onscroll = function(){
		doc_scroll();
	};
	var doc_scroll = function(){
		var height = document.documentElement.clientHeight - 90 ;
		var scroll_top = $(document).scrollTop() ;
		$(".service_box").css({
			"top" : height + scroll_top - 74
		});
	};
	doc_scroll();
</script> 
<![endif]-->
</body>
</html>