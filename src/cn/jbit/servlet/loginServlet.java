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
import cn.jbit.biz.easybuy_userBiz;
import cn.jbit.bizimpl.easybuy_userBizImpl;
import cn.jbit.entity.easybuy_user;
import cn.jbit.util.MD5;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		if (action.equals("exit")) {
			session.invalidate();//session销毁
			//session.removeAttribute("login");
			response.sendRedirect("index.jsp");//重定向到index.jsp页面
		}
		if (action.equals("hou")) {
			if (session.getAttribute("login") == null) {
				response.sendRedirect("login.jsp?path=/hou");
			} else {
				String user_id = (String) session.getAttribute("login");// 判断登陆状态
				easybuy_userBiz eub = new easybuy_userBizImpl();
				easybuy_user eu = eub.getEasyBuyUser(user_id);//根据用户名获取信息
				session.setAttribute("status", eu.getEu_Status());//是否为管理员.."判断"状态
				response.sendRedirect("manage/index.jsp");//重定向到manage/index.jsp页面
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String eu_id = request.getParameter("userId");//获取用户名
		String eu_password = request.getParameter("password");//获取密码
		String code = request.getParameter("code");//获取验证码
		String srand = (String) session.getAttribute("numrand");
		//进行MD5加密
		MD5 md5=new MD5();
		eu_password = md5.getkeyBeanofStr(eu_password);
		System.out.println(eu_password);
		easybuy_userBiz eub = new easybuy_userBizImpl();
		easybuy_user ee = eub.getEasyBuyUser(eu_id);
		boolean result = eub.login(eu_id, eu_password);
		if (result) {
			if (code.equals(srand)) {
				session.setAttribute("login", eu_id);
				session.setAttribute("status", ee.getEu_Status());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//获取系统当前时间
				session.setAttribute("getTime", sdf.format(new Date()));
				if (request.getParameter("path") == null
						|| request.getParameter("path") == ""
						|| request.getParameter("path").lastIndexOf("/") == -1) {
					response.sendRedirect("index.jsp");//重定向到index.jsp页面
				} else {
					String path = request.getParameter("path");
					if (!path.substring(path.lastIndexOf("/")).equals("/login.jsp")) {
						if (path.equals("/hou")) {
							response.sendRedirect("manage/index.jsp");//重定向到manage/index.jsp页面
						} else {
							response.sendRedirect(path);
						}
					} else {
						response.sendRedirect("index.jsp");//重定向到index.jsp页面
					}
				}
			} else {
				session.setAttribute("name", eu_id);
				session.setAttribute("message", "");
				if (code.equals("") || code.equals(null)){
					session.setAttribute("message", "请输入验证码");
				}
				response.sendRedirect("login.jsp");//重定向到login.jsp页面
			}
		} else {
			session.setAttribute("nosuccess", "登录失败");
			response.sendRedirect("login.jsp");//重定向到login.jsp页面
		}
	}
}
