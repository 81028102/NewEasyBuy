package cn.jbit.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.jbit.bizimpl.easybuy_userBizImpl;
import cn.jbit.entity.easybuy_user;
import cn.jbit.util.MD5;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
@WebServlet("/AddUser_servlet")
public class AddUser_servlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		easybuy_userBizImpl e = new easybuy_userBizImpl();
		easybuy_user user = new easybuy_user();
		user.setEu_User_id(request.getParameter("userId"));//获取用户名
		user.setEu_User_name(request.getParameter("userName"));//获取用户姓名
		//进行MD5加密
		MD5 md5=new MD5();
		String	password = md5.getkeyBeanofStr(request.getParameter("password"));//获取密码
		System.out.println(password);
		user.setEu_Password(password);
		user.setEu_Sex(request.getParameter("sex"));//获取性别
		user.setEu_Birthday(request.getParameter("birthday"));//获取出生日期
		user.setEu_Identity_code(request.getParameter("identityCode"));//获取身份证号
		user.setEu_Email(request.getParameter("email"));//获取邮箱
		user.setEu_Mobile(request.getParameter("mobile"));//获取手机号
		user.setEu_Address(request.getParameter("address")+";");//获取收货地址
		user.setEu_Login(0);//获取是否登陆的信息
		user.setEu_Status(1);//是否为管理员(状态)
		user.setEu_question(request.getParameter("eu_question"));//获取问题1
		user.setEu_answer(request.getParameter("eu_answer"));//获取答案1
		user.setEu_question1(request.getParameter("eu_question1"));//获取问题2
		user.setEu_answer1(request.getParameter("eu_answer1"));//获取答案2
		user.setEu_question2(request.getParameter("eu_question2"));//获取问题3
		user.setEu_answer2(request.getParameter("eu_answer2"));//获取答案3
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取系统当前时间
		user.setEu_Create_time(sdf.format(new Date()));
		user.setEu_Cost(0);//初始消费为0
		user.setEu_Score(0);//初始积分为0
		boolean flag = e.getUser(request.getParameter("userId"));//获取用户名
		if(flag){
			session.setAttribute("nosuccess", "该用户名已经存在！注册失败!");
			response.sendRedirect("findQuestionServlet");//重定向到findQuestionServlet
		}else{
			int num = e.addUser(user);
			if(num>0){
				//request.getRequestDispatcher("reg-result.jsp").forward(request, response);
				session.setAttribute("login",request.getParameter("userId"));//获取用户名并保存登陆
				response.sendRedirect("reg-result.jsp");//重定向到reg-result.jsp页面
			}else{
				session.setAttribute("nosuccess", "注册失败！");
				response.sendRedirect("findQuestionServlet");//重定向到register.jsp页面
			}
		}
	}
}