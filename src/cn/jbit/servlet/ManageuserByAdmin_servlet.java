package cn.jbit.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
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
public class ManageuserByAdmin_servlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 创建用户逻辑层对象
		easybuy_userBizImpl e = new easybuy_userBizImpl();
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
			HttpSession session = request.getSession();
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
	}
}