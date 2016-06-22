package cn.jbit.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.jbit.bizimpl.easybuy_userBizImpl;
import cn.jbit.entity.easybuy_user;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
@WebServlet("/getAllUser")
public class getAllUser extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		HttpSession session = request.getSession();
		easybuy_userBizImpl e=new easybuy_userBizImpl();
		if(action.equals("first")){//用户管理打开
			easybuy_user eu = e.getEasyBuyUser((String) session.getAttribute("login"));
			if (eu.getEu_Status() == 1) {
				List<easybuy_user> list = e.getMyInfo(eu.getEu_User_id());
				//放到作用域中
				request.getSession().setAttribute("hlist",list);
				request.getRequestDispatcher("manage/user.jsp").forward(request, response);//转发到manage/user.jsp页面
			}
			if (eu.getEu_Status() == 2) {

				//接收cpage(当前页)
				String cpage = request.getParameter("cpage");
				if(cpage==null){
					cpage="1";
				}
				//接收模糊查询的条件
				String eu_user_id = request.getParameter("eu_user_id");//获取用户名
				if(eu_user_id==null){
					eu_user_id="";
				}
				//定义一个分页单位(pageSize)
				int pageSize=12;
				//分页查询用户信息
				//查询用户总条数
				int count=e.getUserCount(eu_user_id);
				//计算一个总页数
				int totalPage=(count/pageSize)+(count%pageSize==0?0:1);
				List<easybuy_user> list=e.getAllUser(Integer.parseInt(cpage),pageSize,eu_user_id);
				//放到作用域中
				request.getSession().setAttribute("hlist",list);
				request.setAttribute("cpage", cpage);
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("count", count);
				request.setAttribute("eu_user_id", eu_user_id);
				request.getRequestDispatcher("manage/user.jsp").forward(request, response);//转发到manage/user.jsp页面
			}
		}
		if(action.equals("firsts")){//用户管理打开
			//接收cpage(当前页)
			String cpage = request.getParameter("cpage");
			if(cpage==null){
				cpage="1";
			}
			//接收模糊查询的条件
			String eu_user_id = request.getParameter("eu_user_id");//获取用户名
			if(eu_user_id==null){
				eu_user_id="";
			}
			//定义一个分页单位(pageSize)
			int pageSize=15;
			//分页查询用户消费信息
			//查询用户消费总条数
			int countc=e.getConsumeCount(eu_user_id);
			//计算一个总页数
			int totalPage=(countc/pageSize)+(countc%pageSize==0?0:1);
			List<easybuy_user> clist=e.getConsumeAllUser(Integer.parseInt(cpage),pageSize,eu_user_id);
			//放到作用域中
			request.getSession().setAttribute("clist",clist);
			request.setAttribute("cpage", cpage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("countc", countc);
			request.setAttribute("eu_user_id", eu_user_id);
			request.getRequestDispatcher("manage/consume-top.jsp").forward(request, response);//转发到manage/consume-top.jsp页面
		}
		if(action.equals("ToUpdateCS")){//用户管理打开
			easybuy_user more = e.getSelectMore(request.getParameter("eu_User_id"));//获取用户名
			request.getSession().setAttribute("more", more);
			request.getRequestDispatcher("manage/consume-more.jsp").forward(request, response);//转发到manage/consume-more.jsp页面
		}if(action.equals("SelectMores")){
			easybuy_user user = new easybuy_user();
			user.setEu_Cost(Float.parseFloat(request.getParameter("eo_Cost")));
			user.setEu_Score(Float.parseFloat(request.getParameter("eo_Score")));
			user.setEu_User_id(request.getParameter("eu_User_id"));
			int i = e.SelectMores(user);
			if(i>0){
				request.getRequestDispatcher("getAllUser?action=firsts").forward(request, response);//转发到getAllUser?action=firsts
			}
		}
	}
}