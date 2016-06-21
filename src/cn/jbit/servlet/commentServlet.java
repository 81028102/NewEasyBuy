package cn.jbit.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.jbit.biz.easybuy_commentBiz;
import cn.jbit.biz.easybuy_userBiz;
import cn.jbit.bizimpl.easybuy_commentBizImpl;
import cn.jbit.bizimpl.easybuy_userBizImpl;
import cn.jbit.entity.easybuy_comment;
import cn.jbit.entity.easybuy_user;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
@WebServlet("/commentServlet")
public class commentServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取系统当前时间
		easybuy_commentBiz ecb = new easybuy_commentBizImpl();
		//如果第一次打开或者获取全部留言信息
		if (action.equals("first")) {
			//接收cpage(当前页)
			String cpage = request.getParameter("cpage");
			if(cpage==null){
				cpage="1";
			}
			//接收模糊查询的条件
			String ec_nick_name = request.getParameter("ec_nick_name");//获取留言人姓名
			if(ec_nick_name==null){
				ec_nick_name="";
			}
			//定义一个分页单位(pageSize)
			int pageSize=10;
			//查询总条数
			int countt=ecb.countcomments(ec_nick_name);
			//分页查询留言信息
			List<easybuy_comment> list = ecb.getComments(Integer.parseInt(cpage),pageSize,ec_nick_name);
			//计算一个总页数
			int totalPage=(countt/pageSize)+(countt%pageSize==0?0:1);
			//放到作用域中
			session.setAttribute("comments", list);
			request.setAttribute("cpage", cpage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("countt", countt);
			request.setAttribute("ec_nick_name", ec_nick_name);
			if (request.getParameter("h")!=null&&request.getParameter("h").equals("hou")) {
				request.getRequestDispatcher("manage/guestbook.jsp").forward(request, response);//转发到manage/guestbook.jsp页面
			} else {
				if(session.getAttribute("login") != null){ //已经登陆
					String user_id = (String) session.getAttribute("login");// 获取用户ID
					easybuy_userBiz eub = new easybuy_userBizImpl();
					easybuy_user eu = eub.getEasyBuyUser(user_id);//根据id查询用户信息
					String name = eu.getEu_User_name();
					request.getRequestDispatcher("guestbook.jsp?name="+name).forward(request, response);//转发到guestbook.jsp?name=+name页面
				}else if (session.getAttribute("login") == null) {//判断是否登陆
					response.sendRedirect("login.jsp?path="+null);//重定向到ogin.jsp?path=页面
				}else{
					response.sendRedirect("guestbook.jsp");//重定向到guestbook.jsp页面
				}
			}
		}
		//根据id修改留言信息
		if (action.equals("updates")) {
			easybuy_comment eccs = ecb.getComment(Integer.parseInt(request.getParameter("ec_id")));//获取留言编号
			session.setAttribute("eccomment", eccs);
			request.getRequestDispatcher("manage/guestbook-modify.jsp").forward(request, response);//转发到manage/guestbook-modify.jsp页面

		}
		//根据id删除留言信息
		if(action.equals("del")){
			int i=ecb.delComment(request.getParameter("ec_id"));
			if(i>0){
				request.getRequestDispatcher("commentServlet?action=first&h=hou&message="+i).forward(request, response);//转发到commentServlet?action=first&h=hou
			}else{
				response.sendRedirect("commentServlet?action=first&h=hou&message="+i);//重定向到commentServlet?action=first&h=hou
			}
		}
		//添加留言信息
		if (action.equals("add")) {
			//判断是否登陆
			if (session.getAttribute("login") == null) {
				String path = request.getHeader("referer");
				response.sendRedirect("login.jsp?path=" + path);//重定向到ogin.jsp?path=页面
			} else {
				easybuy_comment ec = new easybuy_comment();
				ec.setEc_Create_time(sf.format(new Date()));
				ec.setEc_Nick_name((String) session.getAttribute("login"));
				ec.setEc_Content(request.getParameter("guestContent"));
				int result = ecb.addComment(ec);
				if (result > 0) {
					response.sendRedirect("commentServlet?action=first");//重定向到commentServlet?action=first页面
				} else {
					response.sendRedirect("guestbook.jsp");//重定向到guestbook.jsp页面
				}
			}
		}
		//修改留言信息
		if(action.equals("update")){
			easybuy_comment ecs=ecb.getComment(Integer.parseInt(request.getParameter("ec_id")));//获取留言编号
			ecs.setEc_Reply(request.getParameter("replyContent"));
			ecs.setEc_Reply_time(sf.format(new Date()));
			int i=ecb.updateComment(ecs);
			if(i>0){
				response.sendRedirect("commentServlet?action=first&h=hou&message="+i);//重定向到commentServlet?action=first&h=hou页面
			}else{
				response.sendRedirect("manage/guestbook-modify.jsp");//重定向到manage/guestbook-modify.jsp页面
			}
		}
		//删除全部留言
		if(action.equals("delCommentsAll")){
			int i = ecb.delCommentsAll();
			if(i>0){
				request.getRequestDispatcher("commentServlet?action=first&h=hou&message="+i).forward(request, response);//转发到commentServlet?action=first&h=hou
			}else{
				response.sendRedirect("commentServlet?action=first&h=hou&message="+i);//重定向到manage/guestbook.jsp页面
			}
		}
	}
}