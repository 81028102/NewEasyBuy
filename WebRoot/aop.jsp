<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript" src="scripts/jquery-1.8.3.js"></script>
<script type="text/javascript">
	function around(obj, prop, advice) {
		var exist = obj[prop];
		var previous = function() {
			return exist.apply(obj, arguments);
		};
		var advised = advice(previous);
		obj[prop] = function() {
			//当调用remove后，advised为空
			//利用闭包的作用域链中可以访问到advised跟previous变量，根据advised是否为空可以来决定调用谁
			return advised ? advised.apply(obj, arguments) : previous.apply(
					obj, arguments);
		};
		return {
			remove : function() {
				//利用闭包的作用域链，在remove时将advised置空，这样执行过程中不会进入本次around
				//这几个不能删
				//obj[prop] = exist;
				advised = null;
				advice = null;
				//previous = null;
				//exist = null;
				//obj = null;
			}
		}
	}
	var count = 1;
	advice = function(originalFunc) {
		var current = count++;
		return function() {
			console.log("before function " + current);
			originalFunc.apply(this, arguments);
			console.log("after function " + current);
		}
	}
	var obj = {
		foo : function(arg) {
			console.log(this.name + " and " + arg);
		},
		name : "obj"
	}
	h1 = around(obj, "foo", advice);
	h2 = around(obj, "foo", advice);
	obj.foo("aop");
	h1.remove();
	obj.foo("aop");
	h2.remove();
	obj.foo("aop");
</script>
</head>
<body>
</body>
</html>
