package cn.jbit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.jbit.biz.easybuy_forgetBiz;
import cn.jbit.bizimpl.easybuy_forgetBizImpl;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
@WebServlet("/forgetsServlet")
public class forgetsServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String eu_user_id=request.getParameter("eu_user_id");		 //获取用户名
		easybuy_forgetBiz	fbi = new easybuy_forgetBizImpl();
		String	eu_mobile = fbi.getMobile(eu_user_id);
		PrintWriter out=response.getWriter();
		if("".equals(eu_mobile)){                          	 //判断提示密码是否为空
			out.println("<script>alert('您没有设置手机号,不能找回密码!');history.back();</script>");
		}else if("您输入的用户名不存在!".equals(eu_mobile)){
			out.println("<script>alert('您输入的用户名不存在!');history.back();</script>");
		}else{  										   		   //获取提示问题成功
			request.setAttribute("eu_mobile",eu_mobile);    	  //保存手机号
			request.setAttribute("eu_user_id", eu_user_id);      //保存用户名
			request.getRequestDispatcher("tel-forgets.jsp").forward(request, response);//转发到tel-forgets.jsp页面
		}
	}
}