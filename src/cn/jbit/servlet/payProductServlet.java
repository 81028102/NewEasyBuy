package cn.jbit.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.jbit.biz.easybuy_payBiz;
import cn.jbit.bizimpl.easybuy_payBizImpl;
import cn.jbit.entity.easybuy_product;
import cn.jbit.util.PaymentUtil;

/**
 * @author 任锯东 支付系统
 */
@SuppressWarnings("serial")
@WebServlet("/payProductServlet")
public class payProductServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO 1. 为在线支付，设置基本信息
		String frpId = request.getParameter("frpId");//获取银行编号
		String ep_Id =request.getParameter("ep_Id");//获取商品编号
		String ep_Name = request.getParameter("ep_Name");//获取商品名称
		ep_Name=new String(ep_Name.getBytes("ISO-8859-1"),"UTF-8");//将商品名称转为UTF-8格式处理乱码
		String Price= request.getParameter("Price");//获取商品价格

		// 1 业务类型
		String p0_Cmd = "Buy";
		// 2 商户编号(唯一身份标识)
		String p1_MerId = "10001126856";
		// 3 商户订单号
		String p2_Order = ep_Id ;
		// 4 支付金额,单位:元
		String p3_Amt = Price;
		// 5 交易币种
		String p4_Cur = "CNY" ;
		// 6 商品名称
		String p5_Pid = ep_Name;
		// 7 商品种类
		String p6_Pcat = "";
		// 8 商品描述
		String p7_Pdesc = "";
		// 9 商户接收支付成功数据的地址
		String p8_Url = "http://localhost:8080/NewEasyBuy/index.jsp";
		// 10 送货地址
		String p9_SAF = "0";
		// 11 商户扩展信息
		String pa_MP = "";
		// 12 支付通道编码- 第三方规定的银行的编号
		String pd_FrpId = frpId;
		// 13 应答机制 
		String pr_NeedResponse = "1";

		// 14 密钥
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";

		// 一共14条 签名数据
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);

		easybuy_payBiz epb=new easybuy_payBizImpl();
		easybuy_product epdt=new easybuy_product();
		int count=Integer.parseInt(request.getParameter("count"));//获取商品总数量
		int counts=Integer.parseInt(request.getParameter("counts"));//获取商品销售数量
		epdt.setEp_Stock(count-1);//库存减去1
		epdt.setEp_Sales(counts+1);//销量加上1
		epdt.setEp_Price(Float.parseFloat(Price));
		epdt.setEp_Id(Integer.parseInt(ep_Id));
		epb.getEasyBuyPayCount(epdt);

		//TODO 3.将准备的数据发送给“在线支付”--易宝
		String url = "https://www.yeepay.com/app-merchant-proxy/node" +
				"?p0_Cmd=" + p0_Cmd +
				"&p1_MerId=" + p1_MerId + 
				"&p2_Order=" + p2_Order + 
				"&p3_Amt=" + p3_Amt + 
				"&p4_Cur=" + p4_Cur + 
				"&p5_Pid=" + p5_Pid + 
				"&p6_Pcat=" + p6_Pcat + 
				"&p7_Pdesc=" + p7_Pdesc + 
				"&p8_Url=" + p8_Url + 
				"&p9_SAF=" + p9_SAF + 
				"&pa_MP=" + pa_MP + 
				"&pd_FrpId=" + pd_FrpId + 
				"&pr_NeedResponse=" + pr_NeedResponse + 
				"&hmac=" + hmac ; 

		//重定向到易宝
		response.sendRedirect(url);
	}
}
