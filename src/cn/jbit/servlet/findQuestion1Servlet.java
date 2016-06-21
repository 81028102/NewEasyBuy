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
import cn.jbit.biz.easybuy_findQuestionBiz;
import cn.jbit.bizimpl.easybuy_findQuestionBizImpl;
import cn.jbit.entity.easybuy_question;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
@WebServlet("/findQuestion1Servlet")
public class findQuestion1Servlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		easybuy_findQuestionBiz fq=new easybuy_findQuestionBizImpl();
		List<easybuy_question>	qList = fq.findQuestion();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//获取系统当前时间
		request.getSession().setAttribute("getTime", sdf.format(new Date()));
		request.setAttribute("qList", qList);
		request.getRequestDispatcher("manage/user-add.jsp").forward(request, response);//转发到/manage/user-add.jsp页面
	}
}