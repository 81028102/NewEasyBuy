package cn.jbit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.jbit.bizimpl.easybuy_forgetBizImpl;
import cn.jbit.entity.easybuy_user;
import cn.jbit.util.MD5;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
public class updateforgetServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 创建用户逻辑层对象
		easybuy_forgetBizImpl e = new easybuy_forgetBizImpl();
		easybuy_user user = new easybuy_user();
		//进行MD5加密插入数据库
		MD5 md5=new MD5();
		String password = md5.getkeyBeanofStr(request.getParameter("password"));//获取修改的密码
		System.out.println(password);
		user.setEu_answer(request.getParameter("eu_answer")); //获取提示问题答案1
		user.setEu_answer1(request.getParameter("eu_answer1")); //获取提示问题答案2
		user.setEu_answer2(request.getParameter("eu_answer2")); //获取提示问题答案3
		user.setEu_Password(password);
		PrintWriter out=response.getWriter();
		int num = e.updateForget(user);
		if(num>0){																//判断是否成功修改一条数据(修改密码)
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//获取系统当前时间
			request.getSession().setAttribute("getTime", sdf.format(new Date()));
			request.getSession().setAttribute("login",request.getParameter("password"));//将密码存到session中，修改成功直接登陆
			request.getRequestDispatcher("userforget-success.jsp").forward(request, response);//修改成功跳到userforget-success.jsp页面
		}else{
			out.println("<script>alert('修改失败!');history.back(-1);</script>");
			response.sendRedirect("userforget-modify.jsp");//重定向到userforget-modify.jsp页面
		}
	}
}
