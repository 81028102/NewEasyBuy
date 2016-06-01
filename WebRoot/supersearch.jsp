<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/sharejs.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/supersearch.js"></script>
<script type="text/javascript">
            var _gaq = _gaq || [];
            _gaq.push(['_setAccount', 'UA-17073626-1']);
            _gaq.push(['_trackPageview']);
            (function () {
                var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
                ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
                var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
            })();
        </script>
<script type="text/javascript">
            (function ($) {
                var Vector = function (x, y) {
                    this.x = x || 0;
                    this.y = y || 0;
                };
                Vector.prototype = {
                    add: function (v) {
                        this.x += v.x;
                        this.y += v.y;
                        return this;
                    },
                    length: function () {
                        return Math.sqrt(this.x * this.x + this.y * this.y);
                    },
                    rotate: function (theta) {
                        var x = this.x;
                        var y = this.y;
                        this.x = Math.cos(theta) * this.x - Math.sin(theta) * this.y;
                        this.y = Math.sin(theta) * this.x + Math.cos(theta) * this.y;
                        //this.x = Math.cos(theta) * x - Math.sin(theta) * y;
                        //this.y = Math.sin(theta) * x + Math.cos(theta) * y;
                        return this;
                    },
                    mult: function (f) {
                        this.x *= f;
                        this.y *= f;
                        return this;
                    }
                };
                var Leaf = function (p, r, c, ctx) {
                    this.p = p || null;
                    this.r = r || 0;
                    this.c = c || 'rgba(255,255,255,1.0)';
                    this.ctx = ctx;
                }
                Leaf.prototype = {
                    render: function () {
                        var that = this;
                        var ctx = this.ctx;
                        var f = Branch.random(1, 2)
                        for (var i = 0; i < 5; i++) {
                            (function (r) {
                                setTimeout(function () {
                                    ctx.beginPath();
                                    ctx.fillStyle = that.color;
                                    ctx.moveTo(that.p.x, that.p.y);
                                    ctx.arc(that.p.x, that.p.y, r, 0, Branch.circle, true);
                                    ctx.fill();
                                }, r * 60);
                            })(i);
                        }
                    }
                }
                var Branch = function (p, v, r, c, t) {
                    this.p = p || null;
                    this.v = v || null;
                    this.r = r || 0;
                    this.length = 0;
                    this.generation = 1;
                    this.tree = t || null;
                    this.color = c || 'rgba(255,255,255,1.0)';
                    this.register();
                };
                Branch.prototype = {
                    register: function () {
                        this.tree.addBranch(this);
                    },
                    draw: function () {
                        var ctx = this.tree.ctx;
                        ctx.beginPath();
                        ctx.fillStyle = this.color;
                        ctx.moveTo(this.p.x, this.p.y);
                        ctx.arc(this.p.x, this.p.y, this.r, 0, Branch.circle, true);
                        ctx.fill();
                    },
                    modify: function () {

                        var angle = 0.18 - (0.10 / this.generation);

                        this.p.add(this.v);

                        this.length += this.v.length();

                        this.r *= 0.99;

                        this.v.rotate(Branch.random(-angle, angle)); //.mult(0.996);

                        if (this.r < 0.8 || this.generation > 10) {

                            this.tree.removeBranch(this);

                            var l = new Leaf(this.p, 10, this.color, this.tree.ctx);

                            l.render();

                        }

                    },
                    grow: function () {
                        this.draw();
                        this.modify();
                        this.fork();
                    },
                    fork: function () {
                        var p = this.length - Branch.random(100, 200); // + (this.generation * 10);
                        if (p > 0) {
                            var n = Math.round(Branch.random(1, 3));
                            this.tree.stat.fork += n - 1;
                            for (var i = 0; i < n; i++) {
                                Branch.clone(this);
                            }

                            this.tree.removeBranch(this);

                        }

                    }

                };
                Branch.circle = 2 * Math.PI;

                Branch.random = function (min, max) {

                    return Math.random() * (max - min) + min;

                };

                Branch.clone = function (b) {

                    var r = new Branch(new Vector(b.p.x, b.p.y), new Vector(b.v.x, b.v.y), b.r, b.color, b.tree);

                    r.generation = b.generation + 1;

                    return r;
                };
                Branch.rgba = function (r, g, b, a) {
                    return 'rgba(' + r + ',' + g + ',' + b + ',' + a + ')';
                };
                Branch.randomrgba = function (min, max, a) {

                    return Branch.rgba(Math.round(Branch.random(min, max)), Math.round(Branch.random(min, max)), Math.round(Branch.random(min, max)), a);

                };
                var Tree = function () {
                    var branches = [];
                    var timer;
                    this.stat = {
                        fork: 0,
                        length: 0
                    };
                    this.addBranch = function (b) {
                        branches.push(b);
                    };
                    this.removeBranch = function (b) {

                        for (var i = 0; i < branches.length; i++) {

                            if (branches[i] === b) {

                                branches.splice(i, 1);

                                return;

                            }

                        }

                    };

                    this.render = function (fn) {

                        var that = this;

                        timer = setInterval(function () {

                            fn.apply(that, arguments);

                            if (branches.length > 0) {

                                for (var i = 0; i < branches.length; i++) {

                                    branches[i].grow();

                                }

                            }

                            else {

                                //clearInterval(timer);

                            }

                        }, 1000 / 30);

                    };

                    this.init = function (ctx) {

                        this.ctx = ctx;

                    };

                    this.abort = function () {

                        branches = [];

                        this.stat = {

                            fork: 0,

                            length: 0

                        };

                    };

                };





                function init() {



                    // init



                    var $window = $(window);

                    var $body = $("body");

                    var canvas_width = $window.width();

                    var canvas_height = $window.height() - 30;

                    var center_x = canvas_width / 2;

                    var stretch_factor = 600 / canvas_height;

                    var y_speed = 3 / stretch_factor;

                    var $statMsg = $("#statMsg");
                    // tx
                    var canvas = $('#canvas')[0];
                    canvas.width = canvas_width;
                    canvas.height = canvas_height;
                    var ctx = canvas.getContext("2d");
                    ctx.globalCompositeOperation = "lighter";
                    // tree
                    var t = new Tree();
                    t.init(ctx);

                    for (var i = 0; i < 3; i++) {

                        new Branch(new Vector(center_x, canvas_height), new Vector(Math.random(-1, 1), -y_speed), 15 / stretch_factor, Branch.randomrgba(0, 255, 0.3), t);

                    }

                    t.render(function () {

                        $statMsg.html(this.stat.fork);

                    });



                    // events



                    $("#drawArea").click(function (e) {

                        //e.preventDefault();

                        var x, y;

                        x = e.pageX - this.offsetLeft;

                        y = e.pageY - this.offsetTop;

                        new Branch(new Vector(x, canvas_height), new Vector(0, -y_speed), 15 / stretch_factor, Branch.randomrgba(0, 255, 0.3), t);

                    });

                    $("#btnClear").click(function (e) {

                        e.stopPropagation();

                        t.abort();

                        ctx.clearRect(0, 0, canvas_width, canvas_height);

                        $statMsg.html("0");

                    });

                    $("#btnReload").click(function (e) {
                        e.stopPropagation();
                        window.location.reload();
                    });
                    $("#btnNewExperiment").click(function (e) {
                        window.location = "http://www.openrise.com/lab/FlowerPower";
                    });
                }
                $(function () {
                    init();
                });
            })(jQuery);

        </script>
<style type="text/css">
body {
	padding: 0;
	margin: 0;
	overflow: hidden;
	background: #fff;
	font-family: Courier;
}

canvas {
	background-color: #000;
	cursor: pointer;
}

#statArea {
	float: right;
}

#configArea {
	padding: 0px 10px;
}

#buttonArea {
	position: absolute;
	top: 20px;
	left: 10px;
	z-index: 1000;
}

#buttonArea a {
	color: #fff;
	padding: 5px 10px;
	border: 1px solid #3d3d3d;
	text-decoration: none;
}

#buttonArea a:hover {
	background-color: #333;
}

#badgeArea {
	position: absolute;
	top: 10px;
	right: 10px;
	text-decoration: none;
}

#badgeArea img {
	border: 0;
}

#div1 {
	height: 400px;
	width: 450px;
	position: absolute;
	top: 180px;
	left: 580px;
}

#div1 a {
	position: absolute;
	top: 0px;
	left: 0px;
	color: #fff;
	font-weight: bold;
	padding: 3px 6px;
}

#div1 a:hover {
	border: 1px solid #eee;
	background: #000;
	border-radius: 5px;
}
</style>
</head>
<body>
	<div id="div1">
		<a href="<%=request.getContextPath()%>/index.jsp">返回首页</a> <a
			href="http://www.baidu.com" target="_blank">百度</a> <a
			href="http://weibo.com" target="_blank">新浪微博</a> <a
			href="http://t.qq.com" target="_blank">腾讯微博</a> <a
			href="http://qzone.qq.com" target="_blank">QQ空间</a> <a
			href="https://mail.qq.com/cgi-bin/loginpage" target="_blank">QQ邮箱</a>
		<a href="<%=request.getContextPath()%>/loginServlet?action=hou">后台管理</a>
		<a
			href="<%=request.getContextPath()%>/tencent://message/?uin=981028102&Site=121ask.com&Menu=yes"
			target="_blank">QQ交谈</a> <a
			href="<%=request.getContextPath()%>/findQuestionServlet">免费注册</a> <a
			href="<%=request.getContextPath()%>/questiontelchange.jsp"
			target="_blank">修改密码</a> <a href="http://mail.sina.com.cn"
			target="_blank">新浪邮箱</a> <a
			href="<%=request.getContextPath()%>/indexServlet?action=category&amp;category=one&amp;epc_id=1&amp;epc=图书">图书购买</a>
		<a
			href="<%=request.getContextPath()%>/indexServlet?action=category&amp;category=one&amp;epc_id=2&amp;epc=数码">数码产品</a>
		<a href="http://www.16sucai.com" target="_blank">水果</a> <a
			href="http://www.16sucai.com" target="_blank">蔬菜</a> <a
			href="http://book.qq.com" target="_blank">腾讯文学</a> <a
			href="http://ebook.qq.com" target="_blank">小说阅读</a> <a
			href="http://www.daman.cc" target="_blank">大漫网</a> <a
			href="http://y.qq.com/#type=index" target="_blank">QQ音乐</a> <a
			href="http://www.jikexueyuan.com" target="_blank">极客学院</a> <a
			href="http://www.16sucai.com" target="_blank">网店推广</a> <a
			href="http://www.16sucai.com" target="_blank">微博营销</a> <a
			href="http://www.16sucai.com" target="_blank">网站开发</a> <a
			href="http://www.16sucai.com" target="_blank">宣传品设计</a> <a
			href="http://www.16sucai.com" target="_blank">配音配词</a> <a
			href="http://www.16sucai.com" target="_blank">产品推广</a> <a
			href="http://www.16sucai.com" target="_blank">网络营销</a> <a
			href="http://www.16sucai.com" target="_blank">动漫设计</a> <a
			href="http://www.16sucai.com" target="_blank">招聘求职</a> <a
			href="http://www.16sucai.com" target="_blank">家居装修</a> <a
			href="http://www.16sucai.com" target="_blank">影视创作</a> <a
			href="http://www.16sucai.com" target="_blank">照片美化</a>
	</div>
	<div>
		<canvas id='canvas'></canvas>
	</div>
	<br> <br>
			<div
				style="height:90px; width:728px; padding-top:10px; margin:0px auto; ">
				<script type="text/javascript">google_ad_client = "ca-pub-6110696270771103";
/* 全站大横幅 */
google_ad_slot = "4968847295";
google_ad_width = 728; 
google_ad_height = 90;
</script>
				<script type="text/javascript"
					src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
			</div>
</body>
</html>