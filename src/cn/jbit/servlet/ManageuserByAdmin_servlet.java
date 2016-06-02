package cn.jbit.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.jbit.biz.easybuy_user_statusBiz;
import cn.jbit.bizimpl.easybuy_userBizImpl;
import cn.jbit.bizimpl.easybuy_user_statusBizImpl;
import cn.jbit.entity.easybuy_user;
import cn.jbit.entity.easybuy_user_status;
import cn.jbit.util.MD5;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
public class ManageuserByAdmin_servlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 创建用户逻辑层对象
		easybuy_userBizImpl e = new easybuy_userBizImpl();
		// 创建用户逻辑层对象
		easybuy_user_statusBiz eusb=new easybuy_user_statusBizImpl();
		easybuy_user_status eus=new easybuy_user_status();// 初始化实体类对象
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取系统当前时间 HH:mm:ss
		HttpSession session = request.getSession();
		// 创建用户对象
		String action = request.getParameter("action");
		easybuy_user user = new easybuy_user();
		if("add".equals(action)){											//执行添加
			user.setEu_User_id(request.getParameter("userId"));//获取登录用户名
			user.setEu_User_name(request.getParameter("userName"));//获取用户姓名
			//进行MD5加密
			MD5 md5=new MD5();
			String passWord = md5.getkeyBeanofStr(request.getParameter("password"));//获取密码
			System.out.println(passWord);
			user.setEu_Password(passWord);
			user.setEu_Sex(request.getParameter("sex"));//获取性别
			user.setEu_Birthday(request.getParameter("birthday"));//获取生日
			user.setEu_Identity_code(request.getParameter("identityCode"));//获取身份证号
			user.setEu_Email(request.getParameter("email"));//获取邮箱
			user.setEu_Address(request.getParameter("address"));//获取地址
			user.setEu_Mobile(request.getParameter("mobile"));//获取手机号
			user.setEu_Login(0);//获取是否登陆的信息
			user.setEu_Status(1);//状态是否为管理员
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
			// 判断是否存在该用户
			boolean flag = e.getUser(request.getParameter("userId"));//获取用户名
			if (!flag) {
				// 执行添加操作
				int num = e.addUser(user);
				if (num > 0) {
					request.getSession().setAttribute("success", "添加成功");
					response.sendRedirect("manage/user-add.jsp");//重定向到manage/user-add.jsp页面
				}
			} else {
				request.getSession().setAttribute("message", "已经存在该用户,添加失败");
				response.sendRedirect("findQuestion1Servlet");//重定向到findQuestion1Servlet
			}
		}if("del".equals(action)){									//执行删除
			String id=request.getParameter("id");//获取用户名
			int num=e.delUser(id);
			response.sendRedirect("manage/user.jsp?message="+num);//重定向到manage/user.jsp?message=页面
		}if("up".equals(action)){									//检测是否有用户
			String id = request.getParameter("id");//获取用户名
			user = e.getEasyBuyUser(id);
			if (user != null) {
				session.setAttribute("user", user);
				response.sendRedirect("manage/user-modify.jsp");//重定向到manage/user-modify.jsp页面
			}
		}if("update".equals(action)){								//执行修改操作
			user.setEu_User_id(request.getParameter("userId"));//获取用户名称
			user.setEu_User_name(request.getParameter("userName"));//获取用户姓名
			user.setEu_Sex(request.getParameter("sex"));//获取性别
			user.setEu_Email(request.getParameter("email"));//获取邮箱
			user.setEu_Birthday(request.getParameter("birthday"));//获取出生日期
			user.setEu_Address(request.getParameter("address"));//获取收货地址
			int num = e.updateUser(user);
			if(num>0){
				response.sendRedirect("manage/user.jsp?message="+num);//重定向到manage/user.jsp?message=页面
			}else{
				response.sendRedirect("manage/user-modify.jsp");//重定向到manage/user-modify.jsp页面
			}
		}
		//查看全部状态
		if("sstatus".equals(action)){
			//接收cpage(当前页)
			String cpage = request.getParameter("cpage");
			if(cpage==null){
				cpage="1";
			}
			//接收模糊查询的条件
			String eus_StatusName = request.getParameter("eus_StatusName");//获取新闻标题
			if(eus_StatusName==null){
				eus_StatusName="";
			}
			//定义一个分页单位(pageSize)
			int pageSize=10;
			//查询总条数
			int countstatus=eusb.getUser_StatusCount(eus_StatusName);
			List<easybuy_user_status> liststatus = eusb.getUser_Status(Integer.parseInt(cpage),pageSize,eus_StatusName);
			//计算一个总页数
			int totalPage=(countstatus/pageSize)+(countstatus%pageSize==0?0:1);
			//放到作用域
			session.setAttribute("liststatus", liststatus);
			request.setAttribute("cpage", cpage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("countstatus", countstatus);
			request.setAttribute("eus_StatusName", eus_StatusName);
			request.getRequestDispatcher("manage/userStatusClass.jsp").forward(request, response);//转发到manage/userStatusClass.jsp页面
		}
		// 根据id删除状态
		if (action.equals("delUser_StatusById")) {
			int d = eusb.delUser_StatusById(request.getParameter("eus_id"));//获取分类编号
			// 判断
			if (d >0) {
				request.getRequestDispatcher("ManageuserByAdmin_servlet?action=sstatus&message="+d).forward(request, response);
			}
		}
		//删除全部状态
		if(action.equals("delUser_StatusAll")){
			int i = eusb.delUser_StatusAll();
			// 判断
			if (i >0) {
				request.getRequestDispatcher("ManageuserByAdmin_servlet?action=sstatus&message="+i).forward(request, response);
			}else{
				request.getRequestDispatcher("ManageuserByAdmin_servlet?action=sstatus&message="+i).forward(request, response);
			}
		}
		//添加分类
		if (action.equals("addUser_Status")) {
			String eus_statusname = request.getParameter("eus_statusname");//获取分类名称
			eus.setEus_StatusName(eus_statusname);
			eus.setEus_Create_time(sf.format(new Date()));//格式化系统当前时间为yyyy-MM-dd HH:mm:ss
			if (eusb.boolStatus(eus_statusname)==false) {//判断是否存在该分类信息
				int a = eusb.addUser_Status(eus);
				if (a > 0) {
					session.setAttribute("stmessage", "操作成功!");
					response.sendRedirect("manage/userStatusClass-add.jsp");//重定向到manage/userStatusClass-add.jsp页面
				} else {
					session.setAttribute("stsb", "操作失败!");
					response.sendRedirect("manage/userStatusClass-add.jsp");//重定向到manage/userStatusClass-add.jsp页面
				}
			}else{
				session.setAttribute("stbool", "已经存在该状态信息,不需要多次添加!");
				response.sendRedirect("manage/userStatusClass-add.jsp");//重定向到manage/userStatusClass-add.jsp页面
			}
		}	
		//根据id回显状态信息
		if(action.equals("getUser_StatusById")){
			easybuy_user_status statuss = eusb.getUser_StatusById(Integer.parseInt(request.getParameter("eus_id")));//获取分类编号
			session.setAttribute("statuss", statuss);
			request.getRequestDispatcher("manage/userStatusClass-modify.jsp").forward(request, response);//转发到manage/userStatusClass-modify.jsp页面
		}
		//更新状态信息
		if(action.equals("updateUser_Status")){
			String oldeus_statusname=request.getParameter("oldeus_statusname");//获取"老"状态名称
			eus.setEus_Id(Integer.parseInt(request.getParameter("eus_id")));//获取状态编号
			String eus_statusname = request.getParameter("eus_statusname");//获取"新"状态名称
			eus.setEus_StatusName(eus_statusname);//获取分类名称
			eus.setEus_Create_time(sf.format(new Date()));//格式化系统当前时间为yyyy-MM-dd HH:mm:ss
			if (eusb.boolStatus(eus_statusname)==false||oldeus_statusname.equals(eus_statusname)) {//判断是否存在该状态信息
				int i = eusb.updateUser_Status(eus);
				if(i>0){
					session.setAttribute("stmessage", "操作成功!");
					response.sendRedirect("manage/userStatusClass-modify.jsp");//重定向到manage/userStatusClass-modify.jsp页面
				} else {
					session.setAttribute("stsb", "操作失败!");
					response.sendRedirect("manage/userStatusClass-modify.jsp");//重定向到manage/userStatusClass-modify.jsp页面
				}
			}else{
				session.setAttribute("stbool", "已经存在该分类信息,不需要多次添加!");
				response.sendRedirect("manage/userStatusClass-modify.jsp");//重定向到manage/userStatusClass-modify.jsp页面
			}
		}
	}
}