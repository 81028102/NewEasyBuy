package cn.jbit.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
public class telsServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mcode = request.getParameter("mcode");//获取手机验证码
		System.out.println(mcode);
		//String mobile_code = "123456";
		String mobile_code =  request.getSession().getAttribute("mobile_code").toString();
		if(mobile_code.equals(mcode)){
			response.getWriter().write("true");
		}else{
			response.getWriter().write("false");
		}
		System.out.println(mcode+"=="+mobile_code);
	}
}
