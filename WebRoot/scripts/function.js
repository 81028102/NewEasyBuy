//cookie相关操作，不需要dom加载完成后运行，因此单独写
function setCookie(name, value) {
	if (!name || !value)
		return;
	var Days = 30;// 默认30天
	var exp = new Date();
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + encodeURIComponent(value) + ";expires="
	+ exp.toUTCString();
}
function getCookie(name) {
	var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
	if (arr != null){
		return decodeURIComponent(arr[2]);
	}
	return null;
}
function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if (!cval)
		document.cookie = name + "=" + cval + ";expires=" + exp.toUTCString();
}
//gobuy
function inArray(array, str) {
	for (a in array) {
		if (array[a] == str)
			return true;
	}
	return false;
}
function goBuy(id, price) {
	var newCookie = "";
	var oldCookie = getCookie("product");
	if (oldCookie) {
		if (inArray(oldCookie.split(","), id)) {
			newCookie = oldCookie;
		} else {
			newCookie = id + "," + oldCookie;
		}
	} else {
		newCookie = id;
	}
	setCookie("product", newCookie);
	location.href = "Cart?action=add&entityId=" + id;
}
function goBuy2(id) {
	var newCookie = "";
	var oldCookie = getCookie("product");
	if (oldCookie) {
		if (inArray(oldCookie.split(","), id)) {
			newCookie = oldCookie;
		} else {
			newCookie = id + "," + oldCookie;
		}
	} else {
		newCookie = id;
	}
	setCookie("product", newCookie);
	location.href = "Cart?action=addBuy&entityId=" + id;
}
//dom加载完后运行
$(function() {
	// 获取焦点方法
	function focusItem(dom) {
		$(dom).parent().parent().addClass("current");
		var eBox = $(dom).parent().find("span");
		eBox.removeClass("error").text("");
	}
	// 失去焦点消除背景，且验证信息方法
	function checkItem(dom) {
		$(dom).parent().parent().removeClass("current");
		var eBox = $(dom).parent().find("span");
		var name = $(dom).attr("name");
		var value = $(dom).val();
		var errorMessage = "", isError = true;
		switch (name) {
		//用户名
		case "userId":
			if (value == "") {
				errorMessage = "用户名不能为空";
			} else if (!(/^[a-zA-Z0-9]{4,6}$/.test(value))) {
				errorMessage = "用户名长度为4-6位";
			} else {
				isError = false;
			}
			break;
			//真实姓名
		case "userName":
			if (value == "") {
				errorMessage = "姓名不能为空";
			} else if (value.length > 15) {
				errorMessage = "姓名长度最长为15个字";
			} else {
				isError = false;
			}
			break;
			//密码
		case "password":
			if (value == "") {
				errorMessage = "密码不能为空";
			} else if(!(/^[a-zA-Z0-9]{4,8}$/).test(value)){
				errorMessage = "字母及数字为4-8位";
			}else {
				isError = false;
			}
			break;
			//修改密码
		case "passWord":
			if (value == "") {
				errorMessage = "密码不能为空";
			}else if(!(/^[a-zA-Z0-9]{4,8}$/).test(value)){
				errorMessage = "字母及数字为4-8位";
			}else {
				isError = false;
			}
			break;
			//确认密码
		case "confirmPassword":
			if (value == "") {
				errorMessage = "确认密码不能为空";
			} else if (value != $("#password").val()) {
				errorMessage = "两次输入的密码不相同";
			} else {
				isError = false;
			}
			break;
			//收货地址
		case "address":
			if (value == "") {
				errorMessage = "地址不能为空";
			} else {
				isError = false;
			}
			break;
			//身份证号
		case "identityCode":
			if (value == "")  {
				errorMessage = "身份证号不能为空";
			} else if(!(/^([1-9]\d{14}|\d{17}[0-9X])$/i.test(value))){
				errorMessage = "身份证号码格式不正确";
			}else {
				isError = false;
			}
			break;
			//出生日期
		case "birthday":			
			if (value == "")   {
				errorMessage = "出生日期不能为空";
			}else if (!(/^((19\d{2})|(200\d)|(201\d))-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/.test(value))){
				errorMessage = "(yyyy-MM-dd)格式";
			}else {
				isError = false;
			}
			break;
			//邮件地址
		case "email":
			if (value == "") {
				errorMessage = "邮件地址不能为空";
			}else if(!(/^\w+@\w+.[a-zA-Z]{2,3}(.[a-zA-Z]{2,3})?$/.test(value))){
				errorMessage = "邮件地址格式不正确";
			}
			else {
				isError = false;
			}
			break;
			//手机号码
		case "mobile":
			if (value == "")  {
				errorMessage = "手机号码不能为空";
			}else if(!(/^1\d{10}$/.test(value))){
				errorMessage = "手机号码格式不正确";
			}	else {
				isError = false;
			}
			break;
			//手机验证码
		case "mcode":
			if (value == "")  {
				errorMessage = "验证码不能为空";
			}else {
				isError = false;
			}
			break;
			//商品名称
		case "productName":
			if (value == "") {
				errorMessage = "商品名称不能为空";
			}else if (value.length>16) {
				errorMessage = "商品名称长度最长为16个字";
			} else {
				isError = false;
			}
			break;
			//商品价格
		case "productPrice":
			if (value == "")  {
				errorMessage = "商品价格不能为空";
			}else if(!(/^\d+(\.\d+)?$/.test(value))){
				errorMessage = "商品价格只能为正数";
			}else {
				isError = false;
			}
			break;
		case "productNumber":
			if ((value == "") || !(/^[0-9]*[1-9][0-9]*$/.test(value))) {
				errorMessage = "不能为空且只能为正整数";
			} else {
				isError = false;
			}
			break;
			//上传图片jpg。gif等
		case "photo":
			if (value == "") {
				errorMessage = "未选择文件";
			} else {
				isError = false;
			}
			break;
			//分类名称
		case "className":
			if (value == "") {
				errorMessage = "商品分类名称不能为空";
			} else if (value.length>8) {
				errorMessage = "商品分类名称长度最长为8个字";
			}else {
				isError = false;
			}
			break;
			//商品描述
		case "productdesc":
			if (value == "") {
				errorMessage = "商品描述不能为空";
			} else if (value.length>30) {
				errorMessage = "商品描述长度最长为30个字";
			}else {
				isError = false;
			}
			break;
			//商品库存
		case "productCount":
			if (value == "") {
				errorMessage = "商品库存不能为空";
			} else if(!(/^\d+(\.\d+)?$/.test(value))){
				errorMessage = "商品库存只能为正数";
			}else {
				isError = false;
			}
			break;   
			//找回密码问题
		case "eu_question":
			if (value == "") {
				errorMessage = "请选择找回密码问题";
			}else {
				isError = false;
			}
			break; 
			//找回密码问题答案
		case "eu_answer":
			if (value == "") {
				errorMessage = "输入提示的答案不能为空";
			} else if(value.length>10){
				errorMessage = "答案长度最长为10个字";
			}else {
				isError = false;
			}
			break; 
			//找回密码问题1
		case "eu_question1":
			if (value == "") {
				errorMessage = "请选择找回密码问题";
			}else if (value== $("#eu_question").val()) {
				errorMessage = "三次找回密码问题不能相同";
			}else if (value== $("#eu_question2").val()) {
				errorMessage = "三次找回密码问题不能相同";
			} else {
				isError = false;
			}
			break; 
			//找回密码问题答案
		case "eu_answer1":
			if (value == "") {
				errorMessage = "输入提示的答案不能为空";
			} else if(value.length>10){
				errorMessage = "答案长度最长为10个字";
			}else {
				isError = false;
			}
			break; 
			//找回密码问题2
		case "eu_question2":
			if (value == "") {
				errorMessage = "请选择找回密码问题";
			}else if (value== $("#eu_question").val()) {
				errorMessage = "三次找回密码问题不能相同";
			} else if (value== $("#eu_question1").val()) {
				errorMessage = "三次找回密码问题不能相同";
			}else {
				isError = false;
			}
			break; 
			//找回密码问题答案2
		case "eu_answer2":
			if (value == "") {
				errorMessage = "输入提示的答案不能为空";
			} else if(value.length>10){
				errorMessage = "答案长度最长为10个字";
			}else {
				isError = false;
			}
			break; 
			//选择附件
		case "upload":
			if (value == "") {
				errorMessage = "未选择文件";
			} else {
				isError = false;
			}
			break;
			//用户姓名
		case "eu_user_name":
			if (value == "") {
				errorMessage = "姓名不能为空";
			} else {
				isError = false;
			}
			break;
			//银行卡号
		case "paycardid":
			if (value == "") {
				errorMessage = "卡号不能为空";
			} else if(value.length>23||value.length<23){
				errorMessage = "银行卡号为19位数字";
			} else {
				isError = false;
			}
			break;
			//银行卡密码
		case "paypwd":
			if (value == "") {
				errorMessage = "银行卡密码不能为空";
			}else if(value.length>6||value.length<6){
				errorMessage = "银行卡密码为6位数字";
			}else {
				isError = false;
			}
			break;
			//文件夹名称
		case "newfname":
			if (value == "") {
				errorMessage = "文件夹名称不能为空";
			}else if(value.length>10){
				errorMessage = "文件夹名称长度最长为10个字";
			}else {
				isError = false;
			}
			break;
			//创建人
		case "fusername":
			if (value == "") {
				errorMessage = "创建人不能为空";
			}else if(value.length>8){
				errorMessage = "创建人长度最长为8个字";
			}else {
				isError = false;
			}
			break;
			//备注
		case "fremark":
			if (value == "") {
				errorMessage = "备注不能为空";
			}else if(value.length>15){
				errorMessage = "备注长度最长为15个字";
			}else {
				isError = false;
			}
			break;
			//新闻栏目
		case "enc_id":
			if (value == "") {
				errorMessage = "请选择新闻栏目";
			}else {
				isError = false;
			}
			break; 
			//新闻类别
		case "ent_id":
			if (value == "") {
				errorMessage = "请选择新闻类别";
			}else {
				isError = false;
			}
			break; 
			//新闻分类
		case "ent_name":
			if (value == "") {
				errorMessage = "分类名称不能为空";
			}else if(value.length>6||value.length<2){
				errorMessage = "分类名称长度为2~6个字";
			}else {
				isError = false;
			}
			break; 
			//新闻栏目
		case "enc_name":
			if (value == "") {
				errorMessage = "栏目名称不能为空";
			}else if(value.length>6||value.length<2){
				errorMessage = "栏目名称长度为2~6个字";
			}else {
				isError = false;
			}
			break; 
		default:
			isError = false;
		break;
		}
		if (isError) {
			eBox.text(errorMessage);
			eBox.addClass("error");
			return false;
		}
		return true;
	}
	// 提交表单方法
	function checkForm() {
		var flag = true;
		$(this).find("input[class='text']").each(function(i, dom) {
			if (!checkItem($(dom)))
				flag = false;
		});
		if (!checkItem($(this).find("input[name='productPrice']")))
			flag = false;
		if (!checkItem($(this).find("input[name='productNumber']")))
			flag = false;
		return flag;
	}
	// 添加商品
	$("#productAdd").find("input[class!='submit']").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});

	// dom调用聚焦，失焦事件
	//用户名
	$("#userId").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//真实姓名
	$("#userName").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//密码
	$("#password").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//修改用户密码
	$("#passWord").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//验证密码
	$("#confirmPassword").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//出生日期
	$("#birthday").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//手机号
	$("#mobile").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//验证码
	$("#mcode").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//地址
	$("#address").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//头像
	$("#photo").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//分类名称
	$("#className").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//商品名称
	$("#productName").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//商品描述
	$("#productdesc").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//商品价格
	$("#productPrice").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//商品库存
	$("#productCount").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//找回密码问题
	$("#eu_question").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//提示答案
	$("#eu_answer").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//找回密码问题1
	$("#eu_question1").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//提示答案1
	$("#eu_answer1").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//找回密码问题2
	$("#eu_question2").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//提示答案2
	$("#eu_answer2").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//选择附件
	$("#upload").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//用户姓名
	$("#eu_user_name").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//银行卡号
	$("#paycardid").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//银行卡密码
	$("#paypwd").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//文件夹名称
	$("#fname").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//创建人
	$("#fusername").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//备注
	$("#fremark").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//新闻栏目
	$("#enc_id").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//新闻类别
	$("#ent_id").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//新闻分类
	$("#ent_name").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});
	//新闻栏目
	$("#enc_name").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});


	//安全
	$("#safe").bind({
		focus : function() {
			$("spans").removeClass("errors").text("");
		},
		blur : function() {
			checkItem(this);
		}
	});

	// 日历
	$("#birthday").click(function() {
		WdatePicker({
			highLineWeekDay : true // 周末高亮
			,
			readOnly : true // 只读，只可用控件input中 修改内容
			,
			dateFmt : 'yyyy-MM-dd'
		});
	});
	$("#date").click(function() {
		WdatePicker({
			highLineWeekDay : true // 周末高亮
			,
			readOnly : true // 只读，只可用控件input中 修改内容
			,
			dateFmt : 'yyyy-MM-dd'
		});
	});
	$("#date1").click(function() {
		WdatePicker({
			highLineWeekDay : true // 周末高亮
			,
			readOnly : true // 只读，只可用控件input中 修改内容
			,
			dateFmt : 'yyyy-MM-dd'
		});
	});
	// 点击换验证码
	$("#changeCode").click(function() {
		$("#safeCode").attr("src", "Number.jsp?id=" + Math.random());
	});
	// 注册页面验证
	$("#regForm").find("input[class='text']").bind({
		focus : function() {
			focusItem(this);
		},
		blur : function() {
			checkItem(this);
		}
	});

	// 表单提交事件
	$("#loginForm").submit(checkForm);
	//注册用户验证
	$("#regForm").submit(checkForm);
	//添加用户验证
	$("#myforms").submit(checkForm);
	//用户修改问题验证
	$("#user-modify").submit(checkForm);
	//用户修改手机验证
	$("#tel-modify").submit(checkForm);
	//商品分类修改
	$("#productClass-modify").submit(checkForm);
	//商品分类新增
	$("#productClass-add").submit(checkForm);
	//商品添加验证
	$("#product-add").submit(checkForm);
	//商品修改修改
	$("#product-modify").submit(checkForm);
	//通过问题修改密码验证答案
	$("#user-forgets").submit(checkForm);
	//通过手机修改密码验证答案
	$("#xForm").submit(checkForm);
	//邮箱发送
	$("#sendemail").submit(checkForm);
	//支付管理
	$("#pay").submit(checkForm);
	//文件管理
	$("#document").submit(checkForm);
	//新闻添加
	$("#news-add").submit(checkForm);
	//新闻分类
	$("#newsTypeClass-modify").submit(checkForm);
	//新闻栏目
	$("#newsColumnClass-modify").submit(checkForm);

	// 添加新地址事件
	$("#addr").click(function() {
		var val = $(this).val();
		if (val == "添加") {
			var addr = $("#addAddr").val();
			if (addr == "") {
				alert("地址不能为空");
				return;
			}
			var reg = new RegExp(/;|；/gi);
			if (reg.test(addr)) {
				alert("地址不能有分号");
				return;
			}
			var flag = true;
			$(this).parent().find("input[name='address']")
			.each(function(i, d) {
				if ($(d).next().text() == addr) {
					flag = false;
				}
			});
			if (!flag) {
				alert("地址不能相同");
				return;
			}
			$("#span").append(
					'<br/><input name="address" type="radio" id="address0"/><span>'
					+ addr + '</span>');
			location.href = "addressServlet?action=add&address="
				+ addr;
		} else {
			var span = $("#span")
			.html(
			'<input type="text" name="addAddr" id="addAddr"/>');
			$(this).val("添加");
		}
	});
	// 删除商品
	$(".delete").find("a").click(function() {
		var tr = $(this).parent().parent();
		if (confirm("确定要删除吗？")) {
			var oldCookie = getCookie("product");
			if (oldCookie) {
				var oldCookieArr = oldCookie.split(",");
				var newCookieArr = new Array();
				for (c in oldCookieArr) {
					var cookie = parseInt(oldCookieArr[c]);
					if (cookie != id)
						newCookieArr.push(cookie);
				}
				var newCookie = newCookieArr.join(",");
				setCookie("product", newCookie);
			}
			if (tr)
				tr.remove();
		} else {
			return false;
		}
	});
	// 后台删除
	$(".manageDel").click(function() {
		var $trs = $(this).parent().parent();
		if (confirm("确定要删除吗?请谨慎删除哦!")) {
			location.herf = "";
		} else {
			return false;
		}
	});
	// 后台修改
	$(".manageUpd").click(function() {
		var $trs = $(this).parent().parent();
		if (confirm("确定要修改吗?请谨慎修改哦!")) {
			location.herf = "";
		} else {
			return false;
		}
	});
	// 修改
	$(".number").find("span").click(function() {
		var $tds = $(this).parent().parent().children("td");
		var $price = $($tds[1]);
		var $number = $($tds[2]);
		var price = parseFloat($price.find("input[type='hidden']").val());// 存值
		var $priceBox = $price.find("span");// 现实价钱
		var $number = $number.find("input");// 得到存储input对象
		var opr = $(this).attr("name");// 判断增减
		var number = $number.val();// 数量
		if (opr == "del") {
			number--;
			/*if (number <= 0) {
				if (confirm("确定要删除吗？")) {
					$price.parent().remove();
				} else {
					number = 1;
				}
			}*/
		} else if (opr == "add") {
			number++;
		}
		$number.val(number);
		$priceBox.text("￥" + price * number);
		$("#shopping").find("#total").text("总计：￥" + parseFloat(totalPrice()));
	});
	// 计算总价
	function totalPrice() {
		var totalPrice = 0;
		$("#shopping").find(".price").find("input[type='hidden']").each(
				function(i, d) {
					var p = parseFloat($(d).val());
					var n = $(d).parent().parent().find("input[name='number']")
					.val();
					var s = p * n;
					totalPrice = totalPrice + s;
				});
		return totalPrice;
	}
	$("#shopping").find("input[name='number']").change(function() {
		var v = $(this).val();
		if (!(/^[0-9]*[1-9][0-9]*$/.test(v))) {
			alert("请输入正整数");
			$(this).val(1);
		}
		var $price = $($(this).parent().parent().children("td")[1]);
		var p = $price.find("input").val();
		$price.find("span").text(p * $(this).val());
		$("#shopping").find("#total").text("总计：￥" + totalPrice());
	});
	$("#shopping").find("#total").text("总计：￥" + totalPrice());
	// 注销
	$("#logout").click(function() {
		if (confirm("确定注销吗?")) {
			location.href ="loginServlet?action=exit";
		} else {
			return false;
		}
	});
	// 轮换广告
	var index = 0;
	setInterval(function() {
		index++;
		var $li = $("#slideBox").children("li");
		if (index >= $li.length) {
			index = 0;
		}
		$li.eq(index).stop(true, true).fadeIn().siblings().fadeOut();
	}, 3000);
	// 欢迎图片
	if (document.referrer == "") {
		$("#welcomeImage").slideDown(4000).delay(1000).slideUp(4000);
	}
	// 订单号验证，只能为数字
	$("#orderForm").submit(function() {
		var flag = !isNaN($("#entityId").val());
		if (!flag) {
			alert("订单号只能是数字！");
		}
		return flag;
	});
	// 出现大图标
	$("#product .thumb img").mouseover(
			function() {
				var src = $(this).attr("src").split("/");
				var name = src[src.length - 1].split(".")[0];
				$("#product").append(
						'<img id="bigImg" src="images/product/' + name
						+ '_big.jpg"/>');
				$("#bigImg").show().error(function() {
					$(this).remove();
				});
			}).mouseleave(function() {
				$("#bigImg").hide();
			});
	// 商品分类,默认第一个展开
	$($("#main .box dl dt")[0]).nextUntil('dt').show();
	$("#main .box dl dt").click(function() {
		$(this).nextUntil('dt').toggle();
	});
	// 购物车
	$("#shoppingBag").mouseover(
			function() {
				var o = $(this).offset();
				var $table = $('<table id="floatBag" border="1" style="left:'
						+ o.left + 'px;top:' + (o.top + 20) + 'px;"></table>');
				$("#header").append($table);
				var html = [ "","" ];
				$table.html(html.join(""));
				$("#floatBag").show();
			}).mouseleave(function() {
				$("#floatBag").hide();
			});
	// 验证留言
	$("#guestbook-modify").submit(function() {
		if ($(this).find("textarea").val().length <= 100&&$(this).find("textarea").val().length>0) {
			return true;
		}
		$(this).find("span").addClass("error").html("留言内容不能为空并且不得多于100字");
		return false;
	});
	// 验证评价
	$("#assess-add").submit(function() {
		if ($(this).find("textarea").val().length <= 100&&$(this).find("textarea").val().length>0) {
			return true;
		}
		$(this).find("span").addClass("error").html("评价内容不能为空并且不得多于100字");
		return false;
	});
	// 验证邮件
	$("#sendemail").submit(function() {
		if ($(this).find("textarea").val().length <= 365&&$(this).find("textarea").val().length>0&&$(this).find("input").val().length<=30&&$(this).find("input").val().length>0) {
			return true;
		}
		$(this).find("span").addClass("error").html("邮件标题内容附件不能为空并且标题不得多于30字内容不得多于365字");
		return false;
	});
	// 验证新闻添加
	$("#news-add").submit(function() {
		if ($(this).find("textarea").val().length <= 300&&$(this).find("textarea").val().length>0&&$(this).find("input").val().length<=20&&$(this).find("input").val().length>0) {
			return true;
		}else{
			$(this).find("span").addClass("error").html("新闻标题和内容都不能为空且标题不得多于20字内容不得多于300字");
			return false;
		}
	});
	// 验证新闻内容修改
	$("#news-modify").submit(function() {
		if ($(this).find("textarea").val().length <= 300&&$(this).find("textarea").val().length>0&&$(this).find("input").val().length<=20&&$(this).find("input").val().length>0) {
			return true;
		}else{
			$(this).find("span").addClass("error").html("新闻标题和内容都不能为空且标题不得多于20字内容不得多于300字");
			return false;
		}
	});
	//随滚动条滚动的可关闭广告窗口
	$(window).scroll(function(){
		var st = $(this).scrollTop()+50;
		$("#right").css("top",st);
	});
	$("#right").find("a").click(function(){
		$("#right").hide();
	});
	//轮换图片
	function changeImg(){
		var index=0;
		var stop=false;
		var $li=$(".price-off").find("#scroll_img").children("li");
		var $page = $(".price-off").find("#scroll_number").children("li");
		$page.eq(index).addClass("scroll_number_over").stop(true,true).siblings().removeClass("scroll_number_over");
		$page.mouseover(function(){
			stop=true;
			index=$page.index($(this));
			$li.eq(index).stop(true,true).fadeIn().siblings().fadeOut();
			$(this).addClass("scroll_number_over").stop(true,true).siblings().removeClass("scroll_number_over");
		}).mouseout(function(){
			stop=false;
		});
		setInterval(function(){
			if(stop) return;
			index++;
			if(index>=$li.length){
				index=0;
			}
			$li.eq(index).stop(true,true).fadeIn().siblings().fadeOut();
			$page.eq(index).addClass("scroll_number_over").stop(true,true).siblings().removeClass("scroll_number_over");
		},3000);
	}
	changeImg();
	//新闻循环垂直向上滚动
	function movedome(){
		var marginTop=0;
		var stop=false;
		var interval=setInterval(function(){
			if(stop) return;
			$("#express").children("li").first().animate({"margin-top":marginTop--},0,function(){
				var $first=$(this);
				if(!$first.is(":animated")){
					if((-marginTop)>$first.height()){
						$first.css({"margin-top":0}).appendTo($("#express"));
						marginTop=0;
					}
				}
			});
		},50);
		$("#express").mouseover(function(){
			stop=true;
		}).mouseout(function(){
			stop=false;
		});
	}
	movedome();
	/*$("#auto-complete-email").completer({
		separator: "@",
		source: ["163.com", "qq.com", "126.com", "139.com", "gmail.com", "hotmail.com", "icloud.com"]
	});*/
	startList = function() {
		if (document.all && document.getElementById) {
			navRoot = document.getElementById("searchNav");
			for (i = 0; i < navRoot.childNodes.length; i++) {
				node = navRoot.childNodes[i];
				if (node.nodeName == "LI") {
					node.onmouseover = function() {
						this.className += " over";
					};
					node.onmouseout = function() {
						this.className = this.className.replace(" over", "");
					};
				}
			}
		}
	};
	window.onload = startList;
});