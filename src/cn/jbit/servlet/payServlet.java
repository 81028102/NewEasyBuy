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
import cn.jbit.util.MD5;

/**
 * @author 任锯东 支付系统
 */
@SuppressWarnings("serial")
@WebServlet("/payServlet")
public class payServlet extends HttpServlet {

	private String eu_user_name;
	private String paycardid;
	private String money;
	private String paypwd;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取用户名和卡号和密码
		eu_user_name = request.getParameter("eu_user_name");//获取用户姓名
		paycardid = request.getParameter("paycardid");//获取卡号
		paypwd = request.getParameter("paypwd");//获取密码
		easybuy_payBiz epz=new easybuy_payBizImpl();
		//MD5加密银行卡密码
		MD5 md5=new MD5();
		paypwd = md5.getkeyBeanofStr(paypwd);
		System.out.println(paypwd);
		//获取钱数
		money = epz.getEasyBuyPay(eu_user_name, paycardid,paypwd);
		PrintWriter out=response.getWriter();
		if(money==""||money==null||money.equals("0")||money.equals("0.00")){           //判断金钱是否为空
			System.out.println(money);
			out.println("<script>alert('您的卡内余额不足,不能付费!');history.back();</script>");
		}else if("您输入的姓名和银行卡号不对应!".equals(money)){
			request.getRequestDispatcher("UnknownCustomer.jsp").forward(request, response);//转发到UnknownCustomer.jsp页面
		}else{  										   		 //获取余额成功
			request.setAttribute("money",money);     //保存金钱余额
			request.setAttribute("eu_user_name", eu_user_name);      //保存姓名
			request.setAttribute("paycardid",paycardid);		//保存卡号
			request.setAttribute("paypwd",paypwd);		//保存密码
			request.getRequestDispatcher("NormalBalance.jsp").forward(request, response);//转发到NormalBalance.jsp页面
		}
	}
}
