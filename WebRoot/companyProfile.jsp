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

<title>轻松购 - 服务条款</title>
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
	if(n==2){document.title="轻松购 - 服务条款";}
	setTimeout("title()",1000);
	}
	title();
	</script>
</head>
<body bgcolor="#FFFFFF" text="#000000">
	<form action="<%=request.getContextPath() %>/register.jsp"  method="post" >
		<center>
			<div id="top" class="clearfix">
				<div>
					<span class=L><b>服务条款</b>
					</span>
				</div>
			</div>
			<div id=nav class="clearfix">
				<img src="<%=request.getContextPath() %>/images/addScript_ico.gif"/>&nbsp;我的位置：<a href="index.jsp">首页</a>&gt; 服务条款
			</div>
			<div align="center">
				<table border=0 width=760 cellspacing="1" cellpadding="8"
					bgcolor=#000000>
					<tr bgcolor=#FFFFCC>
						<td align=center>中国EasyBuy购物网站服务条款</td>
					</tr>
					<tr bgcolor=ffffff>
						<td align="center"><br />
						<b>请仔细阅读本协议</b>
						<p>
								<font size="2">中国EasyBuy购物网站</font>依据以下条件和条款提供您所享有的服务。<br />
								<textarea rows="30" name="service" cols="110"
									style="font-family: 宋体; font-size: 10pt" readonly="readonly">

　　1. 本服务条款是用户（您）与EasyBuy之间的协议。EasyBuy由EasyBuy（www.EasyBuy.com.cn）或其关联公司所运行的各个网站和网页（合称EasyBuy）构成。
　　1.1 重要须知：EasyBuy在此特别提醒，用户（您）欲访问和使用EasyBuy，必须事先认真阅读本服务条款中各条款，包括免除或者限制EasyBuy责任的免责条款及对用户的权利限制。请您审阅并接受或不接受本服务条款（未成年人审阅时应得到法定临护人的陪同。）。如您不同意本服务条款及/或随时对其的修改，您应不使用或主动取消EasyBuy提供的服务。您的使用行为将被视为您对本服务条款全部的完全接受，包括接受EasyBuy对服务条款随时所做的任何修改。
　　1.2 这些条款可由EasyBuy随时更新，且毋须另行通知。EasyBuy服务条款(以下简称"服务条款")一旦发生变更,EasyBuy将在网页上公布修改内容。修改后的服务一旦在网页上公布即有效代替原来的服务条款。您可随时登陆EasyBuy查阅最新版服务条款。
　　1.3 EasyBuy目前经由其产品服务组合，向用户提供丰富的网上及线下资源及诸多产品与服务，包括但不限于各种信息通讯工具、即时通信软件或服务、电子邮件、网上论坛、聊天室、线上游戏、电信增值服务等（以下简称"服务"或"本服务"）。本服务条款适用于EasyBuy提供的各种服务，但当您使用EasyBuy某一特定服务时，如该服务另有单独的服务条款、指引或规则，您应遵守本服务条款及EasyBuy随时公布的与该服务相关的服务条款、指引或规则等。前述所有的指引和规则，均构成本服务条款的一部分。除非本服务条款另有其他明示规定，新推出的产品或服务、增加或强化目前本服务的任何新功能，均受到本服务条款之规范。

2. 用户使用规则
　　"服务"仅供能够根据相关法律订立具有法律约束力的合约的个人或公司使用。因此，您的年龄必须在十八周岁或以上，才可使用本公司提供的服务。如不符合本项条件，请勿使用"服务"。EasyBuy可随时自行全权决定拒绝向任何人士提供"服务"。"服务"不会提供给被暂时或永久中止资格的EasyBuy会员。

3. 收费
　　本公司保留在根据第1条通知您后，收取"服务"费用的权利。因进行交易、向本公司获取有偿服务或接触本公司服务器而发生的所有应纳税赋，以及一切硬件、软件、服务及其他方面的费用均由您本人负责支付。本公司保留在无须发出通知的情况下，暂时或永久地更改或停止部份或全部"服务"的权利。

4.EasyBuy网站仅作为交易地点
　　本公司网站仅作为用户物色交易对象、交易协商，以及获取各类与贸易相关的服务的站点。但是，本公司不能控制交易所涉及的物品的质量、安全或合法性，商贸信息的真实性或准确性，以及交易方履行其在贸易协议项下的各项义务的能力。本公司不能也不会控制交易各方能否履行协议义务。此外，还应注意到，与外国国民、未成年人或以欺诈手段行事的人进行交易的风险是客观存在的。

5.您的资料和供买卖的相关物品
　　"您的资料"，包括您在注册、交易或列举物品过程中、在任何公开信息场合或通过任何电子邮件形式，向本公司或其他用户提供的任何资料，包括数据、文本、软件、音乐、声响、照片、图画、影像、词句或其他材料。您应对"您的资料"负全部责任，而本公司仅作为您在网上发布和刊登"您的资料"的被动渠道。倘若本公司认为"您的资料"可能使本公司承担任何法律或道义上的责任，或可能使本公司 (全部或部份地) 失去本公司的互联网服务供应商或其他供应商的服务，或您未在EasyBuy规定的期限内登录或再次登录网站，则本公司可自行全权决定对"您的资料"采取本公司认为必要或适当的任何行动，包括但不限于删除该类资料。您特此保证，您对提交给EasyBuy的"您的资料"拥有全部权利，包括全部版权。您确认，EasyBuy没有责任去认定或决定您提交给本公司的资料哪些是应当受到保护的，对享有"服务"的其他用户使用"您的资料"，本公司也不必负责。

6. 注册义务
　　如您在EasyBuy网站注册，您同意网址所刊载的会员资料表格的要求，提供关于您或贵公司的真实、准确、完整和反映当前情况的资料； 维持并及时更新会员资料，使其保持真实、准确、完整和反映当前情况。倘若您提供任何不真实、不准确、不完整或不能反映当前情况的资料，或EasyBuy有合理理由怀疑该等资料不真实、不准确、不完整或不能反映当前情况，EasyBuy有权暂停或终止您的注册身份及资料，并拒绝您在目前或将来对"服务"(或其任何部份) 以任何形式使用。如您代表一家公司或其他法律主体在本公司登记，则您声明和保证，您有权使该公司或其他法律主体受本协议"条款"约束。

7. 会员注册名、密码和保密
　　在登记过程中，您将选择会员注册名和密码。您须自行负责对您的会员注册名和密码保密，且须对您在会员注册名和密码下发生的所有活动承担责任。您同意：(1) 如发现任何人未经授权使用您的会员注册名或密码，或发生违反保密规定的任何其他情况，您会立即通知EasyBuy；及 (2) 确保您在每个上网时段结束时，以正确步骤离开网站。EasyBuy不能也不会对因您未能遵守本款规定而发生的任何损失或损毁负责。

8. 关于您的资料的规则
　　您同意，"您的资料"和您提供在EasyBuy网站上交易的任何"物品"（泛指一切可供依法交易的、有形的或无形的、以各种形态存在的某种具体的物品，或某种权利或利益，或某种票据或证券，或某种服务或行为。本协议中"物品"一词均含此义）
a. 不会有欺诈成份，与售卖伪造或盗窃无涉；
b. 不会侵犯任何第三者对该物品享有的物权，或版权、专利、商标、商业秘密或其他知识产权，或隐私权、名誉权；
c. 不会违反任何法律、法规、条例或规章 (包括但不限于关于规范出口管理、贸易配额、保护消费者、不正当竞争或虚假广告的法律、法规、条例或规章)；
d. 不会含有诽谤（包括商业诽谤）、非法恐吓或非法骚扰的内容；
e. 不会含有淫秽、或包含任何儿童色情内容；
f. 不会含有蓄意毁坏、恶意干扰、秘密地截取或侵占任何系统、数据或个人资料的任何病毒、伪装破坏程序、电脑蠕虫、定时程序炸弹或其他电脑程序；
g. 不会直接或间接与下述各项货物或服务连接，或包含对下述各项货物或服务的描述：(1) 本协议项下禁止的货物或服务；或 (2) 您无权连接或包含的货物或服务。此外，您同意不会：(3) 在与任何连锁信件、大量胡乱邮寄的电子邮件、滥发电子邮件或任何复制或多余的信息有关的方面使用"服务"；(4) 未经其他人士同意，利用"服务"收集其他人士的电子邮件地址及其他资料；或 () 利用"服务"制作虚假的电子邮件地址，或以其他形式试图在发送人的身份或信息的来源方面误导其他人士。

9. 被禁止物品
　　您不得在本公司网站公布或通过本公司网站买卖：(a) 可能使本公司违反任何相关法律、法规、条例或规章的任何物品；或 (b) 目前列入EasyBuy被禁止物品清单的任何物品。EasyBuy被禁止物品清单经在此提及而被纳入本协议，并可随时加以更新。

10. 您授予本公司的许可使用权
　　您授予本公司独家的、全球通用的、永久的、免费的许可使用权利 (并有权在多个曾面对该权利进行再授权)，使本公司有权(全部或部份地) 使用、复制、修订、改写、发布、翻译、分发、执行和展示"您的资料"或制作其派生作品，和/或以现在已知或日后开发的任何形式、媒体或技术，将"您的资料"纳入其他作品内。

11.隐私
　　尽管有第10条所规定的许可使用权，EasyBuy将仅根据本公司的隐私声明使用"您的资料"。本公司隐私声明的全部条款属于本协议的一部份，因此，您必须仔细阅读。请注意，您一旦自愿地在EasyBuy交易地点披露"您的资料"，该等资料即可能被其他人士获取和使用。

12.交易程序

12.1 添加产品描述条目
　　产品描述是由您提供的在EasyBuy网站上展示的文字描述、图画和/或照片，可以是(a) 对您拥有而您希望出售的产品的描述；或 (b) 对您正寻找的产品的描述。您可在EasyBuy网站发布任一类产品描述，或两种类型同时发布，条件是，您必须将该等产品描述归入正确的类目内。EasyBuy不对产品描述的准确性或内容负责。

12.2 就交易进行协商
　　交易各方通过在EasyBuy网站上明确描述报盘和回盘，进行相互协商。所有各方接纳报盘或回盘将使所涉及的EasyBuy用户有义务完成交易。除非在特殊情况下，诸如用户在您提出报盘后实质性地更改对物品的描述或澄清任何文字输入错误，或您未能证实交易所涉及的用户的身份等，报盘和承诺均不得撤回。

12.3 处理交易争议
　　本公司不会且不能牵涉进交易各方的交易当中。倘若您与一名或一名以上用户，或与您通过本公司网站获取其服务的第三者服务供应商发生争议，您免除EasyBuy (及本公司代理人和雇员) 在因该等争议而引起的，或在任何方面与该等争议有关的不同种类和性质的任何 (实际和后果性的) 权利主张、要求和损害赔偿等方面的责任。

12.4 运用常识
　　本公司不能亦不试图对其他用户通过"服务"提供的资料进行控制。就其本质而言，其他用户的资料，可能是令人反感的、有害的或不准确的，且在某些情况下可能带有错误的标识说明或以欺诈方式加上标识说明。本公司希望您在使用本公司网站时，小心谨慎并运用常识。

13.交易系统

13.1 不得操纵交易
　　您同意不利用帮助实现蒙蔽或欺骗意图的同伙(下属的客户或第三方)，操纵与另一交易方所进行的商业谈判的结果。

13.2 系统完整性
　　您同意，您不得使用任何装置、软件或例行程序干预或试图干预www.fus.com.cn网站的正常运作或正在本公司网站上进行的任何交易。您不得采取对任何将不合理或不合比例的庞大负载加诸本公司网络结构的行动。您不得向任何第三者披露您的密码，或与任何第三者共用您的密码，或为任何未经批准的目的使用您的密码。

13.3 反馈
　　您不得采取任何可能损害信息反馈系统的完整性的行动，诸如：利用第二会员身份标识或第三者为您本身留下正面反馈；利用第二会员身份标识或第三者为其他用户留下负面反馈 (反馈数据轰炸)；或在用户未能履行交易范围以外的某些行动时，留下负面的反馈 (反馈恶意强加)。

13.4 不作商业性利用
　　您同意，您不得对任何资料作商业性利用，包括但不限於在未经EasyBuy授权高层管理人员事先书面批准的情况下，复制在EasyBuy网站上展示的任何资料。

14. 终止或访问限制
　　您同意，EasyBuy可自行全权决定以任何理由 (包括但不限于EasyBuy认为您已违反本协议的字面意义和精神，或以不符合本协议的字面意义和精神的方式行事，或您在超过90天的时间内未以您的帐号及密码登录网站) 终止您的"服务"密码、帐户 (或其任何部份) 或您对"服务"的使用，并删除和丢弃您在使用"服务"中提交的的"您的资料"。EasyBuy同时可自行全权决定，在发出通知或不发出通知的情况下，随时停止提供"服务"或其任何部份。您同意，根据本协议的任何规定终止您使用"服务"之措施可在不发出事先通知的情况下实施，并承认和同意，EasyBuy可立即使您的帐户无效，或撤销您的帐户以及在您的帐户内的所有相关资料和档案，和/或禁止您进一步接入该等档案或"服务"。帐号终止后，EasyBuy没有义务为您保留原帐号中或与之相关的任何信息，或转发任何未曾阅读或发送的信息给您或第三方。此外，您同意，EasyBuy不会就终止您接入"服务"而对您或任何第三者承担任何责任。第16、17、18和26各条应在本协议终止后继续有效。

15. 违反规则会有什么后果
　　在不限制其他补救措施的前提下，发生下述任一情况，本公司可立即发出警告，暂时中止、永久中止或终止您的会员资格，删除您的任何现有产品信息，以及您在网站上展示的任何其他资料：(i) 您违反本协议；(ii) 本公司无法核实或鉴定您向本公司提供的任何资料；或 (iii) 本公司相信您的行为可能会使您、本公司用户或通过本公司或本公司网站提供服务的第三者服务供应商发生任何法律责任。在不限制任何其他补救措施的前提下，倘若发现您从事涉及本公司网站的诈骗活动，EasyBuy可暂停或终止您的帐户。

16. 服务"按现状"提供
　　本公司会尽一切努力使您在使用EasyBuy网站的过程中得到乐趣。遗憾的是，本公司不能随时预见到任何技术上的问题或其他困难。该等困难可能会导致数据损失或其他服务中断。为此，您明确理解和同意，您使用"服务"的风险由您自行承担。"服务"以"按现状"和"按可得到"的基础提供。EasyBuy明确声明不作出任何种类的所有明示或暗示的保证，包括但不限于关於适销性、适用於某一特定用途和无侵权行为等方面的保证。EasyBuy对下述内容不作保证：(i)"服务"会符合您的要求；(ii)"服务"不会中断，且适时、安全和不带任何错误；(iii) 通过使用"服务"而可能获取的结果将是准确或可信赖的；及 (iv) 您通过"服务"而购买或获取的任何产品、服务、资料或其他材料的质量将符合您的预期。通过使用"服务"而下载或以其他形式获取任何材料是由您自行全权决定进行的，且与此有关的风险由您自行承担，对于因您下载任何该等材料而发生的您的电脑系统的任何损毁或任何数据损失，您将自行承担责任。您从EasyBuy或通过或从"服务"获取的任何口头或书面意见或资料，均不产生未在本协议内明确载明的任何保证。

17. 责任范围
　　您明确理解和同意，EasyBuy不对因下述任一情况而发生的任何损害赔偿承担责任，包括但不限於利润、商誉、使用、数据等方面的损失或其他无形损失的损害赔偿 (无论EasyBuy是否已被告知该等损害赔偿的可能性)：(i) 使用或未能使用"服务"；(ii) 因通过或从"服务"购买或获取任何货物、样品、数据、资料或服务，或通过或从"服务"接收任何信息或缔结任何交易所产生的获取替代货物和服务的费用；(iii) 未经批准接入或更改您的传输资料或数据；(iv) 任何第三者对"服务"的声明或关於"服务"的行为；或 (v) 因任何原因而引起的与"服务"有关的任何其他事宜，包括疏忽。

18. 赔偿
　　您同意，因您违反本协议或经在此提及而纳入本协议的其他文件，或因您违反了法律或侵害了第三方的权利，而使第三方对EasyBuy及其子公司、分公司、董事、职员、代理人提出索赔要求（包括司法费用和其他专业人士的费用），您必须赔偿给EasyBuy及其子公司、分公司、董事、职员、代理人，使其等免遭损失。

19. 遵守法律
　　您应遵守与您使用"服务"，以及与您竞买、购买和销售任何物品以及提供商贸信息有关的所有相关的法律、法规、条例和规章。

20. 无代理关系
　　您与EasyBuy仅为独立订约人关系。本协议无意结成或创设任何代理、合伙、合营、雇用与被雇用或特许权授予与被授予关系。

21. 广告和金融服务
　　您与在"服务"上或通过"服务"物色的刊登广告人士通讯或进行业务往来或参与其推广活动，包括就相关货物或服务付款和交付相关货物或服务，以及与该等业务往来相关的任何其他条款、条件、保证或声明，仅限于在您和该刊登广告人士之间发生。您同意，对于因任何该等业务往来或因在"服务"上出现该等刊登广告人士而发生的任何种类的任何损失或损毁，EasyBuy无需负责或承担任何责任。您如打算通过"服务"创设或参与与任何公司、股票行情、投资或证券有关的任何服务，或通过"服务"收取或要求与任何公司、股票行情、投资或证券有关的任何新闻信息、警戒性信息或其他资料，敬请注意，EasyBuy不会就通过"服务"传送的任何该等资料的准确性、有用性或可用性、可获利性负责或承担任何责任，且不会对根据该等资料而作出的任何交易或投资决策负责或承担任何责任。

22. 链接
　　"服务"或第三者均可提供与其他万维网网站或资源的链接。由于EasyBuy并不控制该等网站和资源，您承认并同意，EasyBuy并不对该等外在网站或资源的可用性负责，且不认可该等网站或资源上或可从该等网站或资源获取的任何内容、宣传、产品、服务或其他材料，也不对其等负责或承担任何责任。您进一步承认和同意，对于任何因使用或信赖从此类网站或资源上获取的此类内容、宣传、产品、服务或其他材料而造成（或声称造成）的任何直接或间接损失，EasyBuy均不承担责任。

23. 通知。
　　除非另有明确规定，任何通知应以电子邮件形式发送，发送到您在登记过程中向EasyBuy提供的电子邮件地址，或有关方指明的该等其他地址。在电子邮件发出二十四 (24) 小时后，通知应被视为已送达，除非发送人被告知相关电子邮件地址已作废。或者，本公司可通过邮资预付挂号邮件并要求回执的方式，将通知发到您在登记过程中向EasyBuy提供的地址。在该情况下，在付邮当日三 (3) 天后通知被视为已送达。

24. 不可抗力
　　对于因本公司合理控制范围以外的原因，包括但不限於自然灾害、罢工或骚乱、物质短缺或定量配给、暴动、战争行为、政府行为、通讯或其他设施故障或严重伤亡事故等，致使本公司延迟或未能履约的，EasyBuy不对您承担任何责任。

25. 转让
　　EasyBuy转让本协议无需经您同意。

26. 其他规定
　　本协议构成您和EasyBuy之间的全部协议，规定了您对"服务"的使用，并取代您和EasyBuy先前订立的任何书面或口头协议。本协议各方面应受中华人民共和国大陆地区法律的管辖。倘若本协议任何规定被裁定为无效或不可强制执行，该项规定应被撤销，而其余规定应予执行。条款标题仅为方便参阅而设，并不以任何方式界定、限制、解释或描述该条款的范围或限度。本公司未就您或其他人士的某项违约行为采取行动，并不表明本公司撤回就任何继后或类似的违约事件采取行动的权利。

27. 仲裁
　　因本协议或本公司服务所引起或与其有关的任何争议应提交中国北京市仲裁委员会并根据其适用的仲裁规则进行仲裁裁决。任何该等争议应单独地仲裁，不得与任何其他方的争议在任何仲裁中合并处理。仲裁应在中国北京市进行，而仲裁裁决可提交对其有管辖权的任何法院予以强制执行。您或EasyBuy在仲裁进行期间，可向中国北京地区内具有管辖权的法院寻求为保护您或EasyBuy的权利或财产所需的任何临时或初步补救措施，包括但不限于财产保全或证据保全措施。

　　本协议各方面应受中华人民共和国大陆地区法律的管辖。倘若本协议任何规定被裁定为无效或不可强制执行，该项规定应被撤销，而其余规定应予执行。条款标题仅为方便参阅而设，并不以任何方式界定、限制、解释或描述该条款的范围或限度。 本公司未就您或其他人士的某项违约行为采取行动，并不表明本公司撤回就任何继后或类似的违约事件采取行动的权利。

　　&copy; 2016 中国购物网站 保留本服务条款之解释权。

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
