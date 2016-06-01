package cn.jbit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.jbit.biz.easybuy_forgetBiz;
import cn.jbit.biz.easybuy_userBiz;
import cn.jbit.bizimpl.easybuy_forgetBizImpl;
import cn.jbit.bizimpl.easybuy_userBizImpl;
import cn.jbit.entity.easybuy_user;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
public class forgetServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String eu_user_id=request.getParameter("eu_user_id");		 //获取用户名
		easybuy_forgetBiz	fbi = new easybuy_forgetBizImpl();
		String	eu_question = fbi.getEasyBuyQuestion(eu_user_id);	//获取到3个问题
		String	eu_question1 = fbi.getEasyBuyQuestion1(eu_user_id);
		String	eu_question2 = fbi.getEasyBuyQuestion2(eu_user_id);
		PrintWriter out=response.getWriter();
		if("".equals(eu_question)||"".equals(eu_question1)||"".equals(eu_question2)){                          	 //判断提示密码是否为空
			out.println("<script>alert('您没有设置修改密码提示问题,不能找回密码!');history.back();</script>");
		}else if("您输入的用户名不存在!".equals(eu_question)){
			out.println("<script>alert('您输入的用户名不存在!');history.back();</script>");
		}else{  										   		 //获取提示问题成功
			request.setAttribute("eu_question", eu_question);    //保存提示问题1
			request.setAttribute("eu_question1", eu_question1);    //保存提示问题2
			request.setAttribute("eu_question2", eu_question2);    //保存提示问题3
			request.setAttribute("eu_user_id", eu_user_id);      //保存用户名
			request.getRequestDispatcher("user-forgets.jsp").forward(request, response);//转发到user-forgets.jsp页面       
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String eu_user_id=request.getParameter("eu_user_id");//获取用户名
		String eu_question=request.getParameter("eu_question");//获取密码提示问题
		String eu_answer=request.getParameter("eu_answer");//获取提示问题密码答案
		String eu_question1=request.getParameter("eu_question1");//获取密码提示问题
		String eu_answer1=request.getParameter("eu_answer1");//获取提示问题密码答案
		String eu_question2=request.getParameter("eu_question2");//获取密码提示问题
		String eu_answer2=request.getParameter("eu_answer2");//获取提示问题密码答案
		easybuy_forgetBiz	fbi = new easybuy_forgetBizImpl();
		//执行找回密码第二步的方法判断提示问题答案是否正确
		String	eu_password = fbi.getEasyBuyAnswer(eu_user_id,eu_question,eu_answer,eu_question1,eu_answer1,eu_question2,eu_answer2);
		PrintWriter out=response.getWriter();
		if("您输入的提示问题答案错误!".equals(eu_password)){                           //提示问题答案错误
			out.println("<script>alert('您输入的提示问题答案错误!');history.back();</script>");
		}else{                    												 //提示问题答案正确,修改密码
			easybuy_userBiz eub = new easybuy_userBizImpl();
			easybuy_user ee = eub.getEasyBuyUser(eu_user_id);//根据用户名获取是否为管理员..状态
			request.getSession().setAttribute("status", ee.getEu_Status());
			request.setAttribute("eu_answer", eu_answer);
			request.setAttribute("eu_answer1", eu_answer1);
			request.setAttribute("eu_answer2", eu_answer2);
			request.getRequestDispatcher("userforget-modify.jsp").forward(request,
					response);//转发到userforget-modify.jsp页面               
		}
	}
}