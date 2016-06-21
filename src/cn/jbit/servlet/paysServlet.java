package cn.jbit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.jbit.biz.easybuy_payBiz;
import cn.jbit.bizimpl.easybuy_payBizImpl;
import cn.jbit.entity.easybuy_pay;
import cn.jbit.entity.easybuy_product;

/**
 * @author 任锯东 支付系统
 */
@SuppressWarnings("serial")
@WebServlet("/paysServlet")
public class paysServlet extends HttpServlet {


	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int ep_Id=Integer.parseInt(request.getParameter("ep_Id"));//获取商品编号
		int count=Integer.parseInt(request.getParameter("count"));//获取商品总数量
		int counts=Integer.parseInt(request.getParameter("counts"));//获取商品销售数量
		String	eu_user_name = request.getParameter("eu_user_name");//获取用户姓名
		Float Price =Float.parseFloat(request.getParameter("Price"));//获取支付价格
		easybuy_payBiz epb=new easybuy_payBizImpl();
		easybuy_pay epay=new easybuy_pay();
		easybuy_product epdt=new easybuy_product();
		PrintWriter out=response.getWriter();
		//获取卡内金额
		Float money=Float.parseFloat(request.getParameter("money"));//获取卡内余额
		money=money-Price;
		epay.setMoney(money);
		epay.setPaycardid(request.getParameter("paycardid"));//获取卡号
		epay.setPaypwd(request.getParameter("paypwd"));//获取密码
		epdt.setEp_Stock(count-1);
		epdt.setEp_Sales(counts+1);
		epdt.setEp_Price(Price);
		epdt.setEp_Id(ep_Id);
		//获取数量修改
		int ii = epb.getEasyBuyPayCount(epdt);
		//支付
		int i = epb.getEasyBuyPayProduct(epay);
		if(i>0&&ii>0){
			//支付成功 放到作用域中
			request.setAttribute("eu_user_name",eu_user_name);//保存下用户名
			request.setAttribute("money", money);//保存下余额
			request.getRequestDispatcher("HighBalance.jsp").forward(request, response);//转发到HighBalance.jsp页面
		}else if(money<Price){
			//支付失败 卡上余额不足
			out.println("<script>alert('您的卡内余额不足,不能付费!');history.back();</script>");
		}else if(money==0){
			//支付失败 卡上余额不足
			out.println("<script>alert('您的卡内余额不足,不能付费!');history.back();</script>");
		}
	}
}