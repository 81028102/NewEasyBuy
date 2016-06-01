<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/function.js"></script>
<%
	//做国际化
		String lang = request.getParameter("lang");//zh_CN   en_US
		Locale locale=null;
		if(lang==null){
			locale=request.getLocale();
		}else{
			locale=new Locale(lang.substring(0,2),lang.substring(3));
		}
		//国际化的资源包
		ResourceBundle rb = ResourceBundle.getBundle("message", locale);
		//登陆页面
		String welcome=rb.getString("welcome");
		String userId=rb.getString("userId");
		String password1=rb.getString("password1");
		String codes=rb.getString("codes");
		String change=rb.getString("change");
		String submit=rb.getString("submit");
		String forget=rb.getString("forget");
		String pleaseinputname=rb.getString("pleaseinputname");
		String pleaseinputpassword=rb.getString("pleaseinputpassword");
		String codess=rb.getString("codess");
		String companyprofile=rb.getString("companyprofile");//底部
		String recruittalents=rb.getString("recruittalents");
		String websitealliance=rb.getString("websitealliance");
		String chinamerchants=rb.getString("chinamerchants");
		String serviceprovision=rb.getString("serviceprovision");
		String easybuy=rb.getString("easybuy");
		String Beijing=rb.getString("Beijing");
		String card=rb.getString("card");
		String number=rb.getString("number");
		String site=rb.getString("site");
		String operatepermit=rb.getString("operatepermit");
		String shopping=rb.getString("shopping");//头部
		String Theshoppingcart=rb.getString("Theshoppingcart");
		String numbers=rb.getString("numbers");
		String Login=rb.getString("Login");
		String logout=rb.getString("logout");
		String register=rb.getString("register");
		String leaveamessage=rb.getString("leaveamessage");
		String bgmanage=rb.getString("bgmanage");
		String search=rb.getString("search");
		String Home=rb.getString("Home");
		String Book=rb.getString("Book");
		String Digital=rb.getString("Digital");
		String Brand=rb.getString("Brand");
		String Department=rb.getString("Department");
		String Salespromotion=rb.getString("Salespromotion");
		String Quicktoenter=rb.getString("Quicktoenter");
		String Manage=rb.getString("Manage");
		String Todayis=rb.getString("Todayis");
		String Welcometothehomepage=rb.getString("Welcometothehomepage");
		String Goodmorning=rb.getString("Goodmorning");
		String Goodmorning1=rb.getString("Goodmorning1");
		String Goodmorning2=rb.getString("Goodmorning2");
		String Goodafternoon=rb.getString("Goodafternoon");
		String Goodafternoon1=rb.getString("Goodafternoon1");
		String Goodevening=rb.getString("Goodevening");
		String Goodevening1=rb.getString("Goodevening1");
		String Goodnight=rb.getString("Goodnight");
		String rwelcome1 = rb.getString("rwelcome1");//注册页面
		String fillintheregistrationinformation = rb.getString("fillintheregistrationinformation");
		String loginwassuccessful = rb.getString("loginwassuccessful");
		String Areenteringthehomepage=rb.getString("Areenteringthehomepage");	
		String rname = rb.getString("rname");
		String rrealname = rb.getString("rrealname");
		String rpassword = rb.getString("rpassword");
		String raffirmpassword = rb.getString("raffirmpassword");
		String rsex = rb.getString("rsex");
		String rman = rb.getString("rman");
		String rwoman = rb.getString("rwoman");
		String rbirthday = rb.getString("rbirthday");
		String ridentitycard = rb.getString("ridentitycard");
		String remail = rb.getString("remail");
		String rphone = rb.getString("rphone");
		String rchooseaddress = rb.getString("rchooseaddress");
		String raddress = rb.getString("raddress");
		String ronequestion = rb.getString("ronequestion");
		String rtwoquestion = rb.getString("rtwoquestion");
		String rthreequestion = rb.getString("rthreequestion");
		String ranswer = rb.getString("ranswer");
		String rpleasechoose = rb.getString("rpleasechoose");
		String rpleasechoose1 = rb.getString("rpleasechoose1");
		String rreadagree = rb.getString("rreadagree");
		String rregistertreaty = rb.getString("rregistertreaty");
		String Register = rb.getString("Register");
		String rpleaseinputname = rb.getString("rpleaseinputname");
		String rpleaseinputrealname = rb.getString("rpleaseinputrealname");
		String rpleaseinputpassword = rb.getString("rpleaseinputpassword");
		String rpleaseinputaffirmpassword = rb.getString("rpleaseinputaffirmpassword");
		String rpleaseinputbirthday = rb.getString("rpleaseinputbirthday");
		String rpleaseinputidentitycard = rb.getString("rpleaseinputidentitycard");
		String rpleaseinputemail = rb.getString("rpleaseinputemail");
		String rpleaseinputphone = rb.getString("rpleaseinputphone");
		String rpleaseinputaddress = rb.getString("rpleaseinputaddress");
		String rpleaseinputhintanswer = rb.getString("rpleaseinputhintanswer");
		String returns2 = rb.getString("returns2");			
		String workday=rb.getString("workday");//右侧服务
		String service=rb.getString("service");
		String servicetime=rb.getString("servicetime");
		String services = rb.getString("services");
		String services1 = rb.getString("services1");
		String Clickheretosendamessagetome = rb.getString("Clickheretosendamessagetome");
		String TheofficialWeChatselfhelpcustomerservice = rb.getString("TheofficialWeChatselfhelpcustomerservice");
		String Isthereaspecialratescanattentionoh=rb.getString("Isthereaspecialratescanattentionoh");
		String Returntothetop = rb.getString("Returntothetop");//返回顶部底部
		String Ifyouhaveanyconcern = rb.getString("Ifyouhaveanyconcern");
		String Pleasefeedbacktous = rb.getString("Pleasefeedbacktous");
		String Atthebottomoftheback = rb.getString("Atthebottomoftheback");
		String Onlinemessage = rb.getString("Onlinemessage");//留言
		String Allmessages = rb.getString("Allmessages");
		String Thenetfriend = rb.getString("Thenetfriend");
		String nickname = rb.getString("nickname");
		String Messagecontent = rb.getString("Messagecontent");
		String Pleaseenterthemessagecontent = rb.getString("Pleaseenterthemessagecontent");
		String Submitamessage = rb.getString("Submitamessage");
		String Youcanalsoinput = rb.getString("Youcanalsoinput");
		String aword = rb.getString("aword");
		String Yourpresentposition = rb.getString("Yourpresentposition");
		String returns1 = rb.getString("returns1");
		String Havetoreply = rb.getString("Havetoreply");
		String Didnotreturn = rb.getString("Didnotreturn");
		String Readingthenews = rb.getString("Readingthenews");//新闻
		String Newsdetails = rb.getString("Newsdetails");
		String Newsheadlines = rb.getString("Newsheadlines");
		String Newscontent = rb.getString("Newscontent");
		String newstrends = rb.getString("newstrends");
		String Welcomebacktomanagementbackground = rb.getString("Welcomebacktomanagementbackground");//后台页面
		String Welcomebacktomanagethehomepage = rb.getString("Welcomebacktomanagethehomepage");
		String Managementofthehomepage = rb.getString("Managementofthehomepage");
		String Managementbackground = rb.getString("Managementbackground");
		String Returntothefrontdeskpage = rb.getString("Returntothefrontdeskpage");
		String Homes = rb.getString("Homes");
		String Theuser = rb.getString("Theuser");
		String Goods = rb.getString("Goods");
		String Order = rb.getString("Order");
		String Message = rb.getString("Message");
		String News = rb.getString("News");
		//
		String goodslisting = rb.getString("goodslisting");
		String Classificationofgoods = rb.getString("Classificationofgoods");
		String Recentbrowse = rb.getString("Recentbrowse");
		String Yourshoppingcartisempty = rb.getString("Yourshoppingcartisempty");
		String Visitthegoods = rb.getString("Visitthegoods");
		String Nameofcommodity = rb.getString("Nameofcommodity");
		String Commodityprice = rb.getString("Commodityprice");
		String Commodityprices = rb.getString("Commodityprices");
		String Purchasequantity = rb.getString("Purchasequantity");
		String Operation = rb.getString("Operation");
		String Atotalof = rb.getString("Atotalof");
		String Delete = rb.getString("Delete");
		String Theinvoicing = rb.getString("Theinvoicing");//购买页面
		String Mallprice = rb.getString("Mallprice");
		String inventory = rb.getString("inventory");
		String count1 = rb.getString("count1");
		String Addtocart = rb.getString("Addtocart");
		String Goodsdetails = rb.getString("Goodsdetails");
		String Pleaseclickonthefollowingshippingaddress = rb.getString("Pleaseclickonthefollowingshippingaddress");
		String Pro = rb.getString("Pro");
		String Canalsoaddnewaddress = rb.getString("Canalsoaddnewaddress");
		String Allthegoods = rb.getString("Allthegoods");
		String purchasesucceeded = rb.getString("purchasesucceeded");
		String Pleasewaitcustomergiveyoureply = rb.getString("Pleasewaitcustomergiveyoureply");
		String Mailsentsuccessfully = rb.getString("Mailsentsuccessfully");
		String SendEmail = rb.getString("SendEmail");
		String Pleaseenterapasswordtomodify = rb.getString("Pleaseenterapasswordtomodify");
		String SMSverificationcodes = rb.getString("SMSverificationcodes");
		String PleaseentertheSMSverificationcode = rb.getString("PleaseentertheSMSverificationcode");
		
	
		//放到作用域中
		//登陆页面
		request.getSession().setAttribute("welcome",welcome);
		request.getSession().setAttribute("userId",userId);
		request.getSession().setAttribute("password1",password1);
		request.getSession().setAttribute("codes",codes);
		request.getSession().setAttribute("change",change);
		request.getSession().setAttribute("submit",submit);
		request.getSession().setAttribute("forget",forget);
		request.getSession().setAttribute("pleaseinputname",pleaseinputname);
		request.getSession().setAttribute("pleaseinputpassword",pleaseinputpassword);
		request.getSession().setAttribute("codess",codess);
		request.getSession().setAttribute("companyprofile",companyprofile);//底部
		request.getSession().setAttribute("recruittalents",recruittalents);
		request.getSession().setAttribute("websitealliance",websitealliance);
		request.getSession().setAttribute("chinamerchants",chinamerchants);
		request.getSession().setAttribute("serviceprovision",serviceprovision);
		request.getSession().setAttribute("easybuy",easybuy);
		request.getSession().setAttribute("Beijing",Beijing);
		request.getSession().setAttribute("card",card);
		request.getSession().setAttribute("number",number);
		request.getSession().setAttribute("site",site);
		request.getSession().setAttribute("operatepermit",operatepermit);
		request.getSession().setAttribute("shopping",shopping);//头部
		request.getSession().setAttribute("Theshoppingcart",Theshoppingcart);
		request.getSession().setAttribute("numbers",numbers);
		request.getSession().setAttribute("Login",Login);
		request.getSession().setAttribute("logout",logout);
		request.getSession().setAttribute("register",register);
		request.getSession().setAttribute("leaveamessage",leaveamessage);
		request.getSession().setAttribute("bgmanage",bgmanage);
		request.getSession().setAttribute("search",search);
		request.getSession().setAttribute("Home",Home);
		request.getSession().setAttribute("Book",Book);
		request.getSession().setAttribute("Digital",Digital);
		request.getSession().setAttribute("Brand",Brand);
		request.getSession().setAttribute("Department",Department);
		request.getSession().setAttribute("Salespromotion",Salespromotion);
		request.getSession().setAttribute("Quicktoenter",Quicktoenter);
		request.getSession().setAttribute("Manage",Manage);
		request.getSession().setAttribute("Todayis",Todayis);
		request.getSession().setAttribute("Welcometothehomepage",Welcometothehomepage);
		request.getSession().setAttribute("Goodmorning",Goodmorning);
		request.getSession().setAttribute("Goodmorning1",Goodmorning1);
		request.getSession().setAttribute("Goodmorning2",Goodmorning2);
		request.getSession().setAttribute("Goodafternoon",Goodafternoon);
		request.getSession().setAttribute("Goodafternoon1",Goodafternoon1);
		request.getSession().setAttribute("Goodevening",Goodevening);
		request.getSession().setAttribute("Goodevening1",Goodevening1);
		request.getSession().setAttribute("Goodnight",Goodnight);
		request.getSession().setAttribute("rwelcome1",rwelcome1);	//注册页面
		request.getSession().setAttribute("fillintheregistrationinformation",fillintheregistrationinformation);
		request.getSession().setAttribute("loginwassuccessful",loginwassuccessful);
		request.getSession().setAttribute("Areenteringthehomepage",Areenteringthehomepage);
		request.getSession().setAttribute("rname",rname);
		request.getSession().setAttribute("rrealname",rrealname);
		request.getSession().setAttribute("rpassword",rpassword);
		request.getSession().setAttribute("raffirmpassword",raffirmpassword);
		request.getSession().setAttribute("rsex",rsex);
		request.getSession().setAttribute("rman",rman);
		request.getSession().setAttribute("rwoman",rwoman);
		request.getSession().setAttribute("rbirthday",rbirthday);
		request.getSession().setAttribute("ridentitycard",ridentitycard);
		request.getSession().setAttribute("remail",remail);
		request.getSession().setAttribute("rphone",rphone);
		request.getSession().setAttribute("rchooseaddress",rchooseaddress);
		request.getSession().setAttribute("raddress",raddress);
		request.getSession().setAttribute("ronequestion",ronequestion);
		request.getSession().setAttribute("rtwoquestion",rtwoquestion);
		request.getSession().setAttribute("rthreequestion",rthreequestion);
		request.getSession().setAttribute("ranswer",ranswer);
		request.getSession().setAttribute("rpleasechoose",rpleasechoose);
		request.getSession().setAttribute("rpleasechoose1",rpleasechoose1);
		request.getSession().setAttribute("rreadagree",rreadagree);
		request.getSession().setAttribute("rregistertreaty",rregistertreaty);
		request.getSession().setAttribute("Register",Register);
		request.getSession().setAttribute("rpleaseinputname",rpleaseinputname);
		request.getSession().setAttribute("rpleaseinputrealname",rpleaseinputrealname);
		request.getSession().setAttribute("rpleaseinputpassword",rpleaseinputpassword);
		request.getSession().setAttribute("rpleaseinputaffirmpassword",rpleaseinputaffirmpassword);
		request.getSession().setAttribute("rpleaseinputbirthday",rpleaseinputbirthday);
		request.getSession().setAttribute("rpleaseinputidentitycard",rpleaseinputidentitycard);
		request.getSession().setAttribute("rpleaseinputemail",rpleaseinputemail);
		request.getSession().setAttribute("rpleaseinputphone",rpleaseinputphone);
		request.getSession().setAttribute("rpleaseinputaddress",rpleaseinputaddress);
		request.getSession().setAttribute("rpleaseinputhintanswer",rpleaseinputhintanswer);
		request.getSession().setAttribute("returns2",returns2);
		request.getSession().setAttribute("workday",workday);//右侧服务
		request.getSession().setAttribute("service",service);
		request.getSession().setAttribute("servicetime",servicetime);
		request.getSession().setAttribute("services", services);
		request.getSession().setAttribute("services1", services1);
		request.getSession().setAttribute("Clickheretosendamessagetome", Clickheretosendamessagetome);
		request.getSession().setAttribute("TheofficialWeChatselfhelpcustomerservice", TheofficialWeChatselfhelpcustomerservice);
		request.getSession().setAttribute("Isthereaspecialratescanattentionoh",Isthereaspecialratescanattentionoh);
		request.getSession().setAttribute("Returntothetop", Returntothetop);
		request.getSession().setAttribute("Ifyouhaveanyconcern", Ifyouhaveanyconcern);
		request.getSession().setAttribute("Pleasefeedbacktous", Pleasefeedbacktous);
		request.getSession().setAttribute("Atthebottomoftheback", Atthebottomoftheback);
		request.getSession().setAttribute("Allmessages", Allmessages);
		request.getSession().setAttribute("Onlinemessage", Onlinemessage);//留言
		request.getSession().setAttribute("Thenetfriend", Thenetfriend);
		request.getSession().setAttribute("nickname", nickname);
		request.getSession().setAttribute("Messagecontent", Messagecontent);
		request.getSession().setAttribute("Pleaseenterthemessagecontent", Pleaseenterthemessagecontent);
		request.getSession().setAttribute("Submitamessage", Submitamessage);
		request.getSession().setAttribute("Youcanalsoinput", Youcanalsoinput);
		request.getSession().setAttribute("aword", aword);
		request.getSession().setAttribute("Yourpresentposition",Yourpresentposition);
		request.getSession().setAttribute("returns1", returns1);
		request.getSession().setAttribute("Havetoreply", Havetoreply);
		request.getSession().setAttribute("Didnotreturn", Didnotreturn);
		request.getSession().setAttribute("Readingthenews", Readingthenews);//新闻
		request.getSession().setAttribute("Newsdetails", Newsdetails);
		request.getSession().setAttribute("Newsheadlines", Newsheadlines);
		request.getSession().setAttribute("Newscontent", Newscontent);
		request.getSession().setAttribute("newstrends", newstrends);
		request.getSession().setAttribute("Welcomebacktomanagementbackground",Welcomebacktomanagementbackground);//后台页面
		request.getSession().setAttribute("Welcomebacktomanagethehomepage",Welcomebacktomanagethehomepage);
		request.getSession().setAttribute("Managementofthehomepage",Managementofthehomepage);
		request.getSession().setAttribute("Managementbackground",Managementbackground);
		request.getSession().setAttribute("Returntothefrontdeskpage",Returntothefrontdeskpage);
		request.getSession().setAttribute("Homes", Homes);
		request.getSession().setAttribute("Theuser", Theuser);
		request.getSession().setAttribute("Goods", Goods);
		request.getSession().setAttribute("Order", Order);
		request.getSession().setAttribute("Message", Message);
		request.getSession().setAttribute("News", News);
		//
		request.getSession().setAttribute("goodslisting", goodslisting);
		request.getSession().setAttribute("Classificationofgoods", Classificationofgoods);
		request.getSession().setAttribute("Recentbrowse", Recentbrowse);
		request.getSession().setAttribute("Yourshoppingcartisempty", Yourshoppingcartisempty);
		request.getSession().setAttribute("Visitthegoods", Visitthegoods);
		request.getSession().setAttribute("Nameofcommodity", Nameofcommodity);
		request.getSession().setAttribute("Commodityprice", Commodityprice);
		request.getSession().setAttribute("Commodityprices", Commodityprices);
		request.getSession().setAttribute("Purchasequantity", Purchasequantity);
		request.getSession().setAttribute("Operation", Operation);
		request.getSession().setAttribute("Atotalof", Atotalof);
		request.getSession().setAttribute("Delete", Delete);
		request.getSession().setAttribute("Theinvoicing", Theinvoicing);//购买页面
		request.getSession().setAttribute("Mallprice", Mallprice);
		request.getSession().setAttribute("inventory", inventory);
		request.getSession().setAttribute("count1", count1);
		request.getSession().setAttribute("Addtocart", Addtocart);
		request.getSession().setAttribute("Goodsdetails", Goodsdetails);
		request.getSession().setAttribute("Pleaseclickonthefollowingshippingaddress", Pleaseclickonthefollowingshippingaddress);
		request.getSession().setAttribute("Pro", Pro);
		request.getSession().setAttribute("Canalsoaddnewaddress", Canalsoaddnewaddress);
		request.getSession().setAttribute("Allthegoods", Allthegoods);
		request.getSession().setAttribute("purchasesucceeded", purchasesucceeded);
		request.getSession().setAttribute("Pleasewaitcustomergiveyoureply", Pleasewaitcustomergiveyoureply);
		request.getSession().setAttribute("Mailsentsuccessfully", Mailsentsuccessfully);
		request.getSession().setAttribute("SendEmail", SendEmail);
		request.getSession().setAttribute("Pleaseenterapasswordtomodify", Pleaseenterapasswordtomodify);
		request.getSession().setAttribute("SMSverificationcodes", SMSverificationcodes);
		request.getSession().setAttribute("PleaseentertheSMSverificationcode", PleaseentertheSMSverificationcode);
		
		
	
		
		
 %>
  <div id="footers">
		<div class="footer_tops">
			<a href="<%=request.getContextPath() %>/companyabstruct.jsp" target="_parent" class="footer_dull_red">${companyprofile }</a> | <a
				href="#" target="_parent" class="footer_dull_red">${recruittalents }</a> | <a
				href="#" target="_parent" class="footer_dull_red">${websitealliance }</a> | <a
				href="#" target="_parent" class="footer_dull_red">${chinamerchants }</a> | <a
				href="<%=request.getContextPath() %>/companyProfile.jsp" target="_parent" class="footer_dull_red">${serviceprovision }</a>
		</div>
			Copyright (C) 2016 ${easybuy},All Rights Reserved<img
			src="<%=request.getContextPath()%>/images/validate.gif" alt="版权" style="vertical-align:middle;" />
		${Beijing}ICP证100000${number} ${easybuy}${site}${operatepermit}${easybuy}8${number}
	</div>
