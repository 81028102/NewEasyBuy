package cn.jbit.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.jbit.biz.easybuy_productBiz;
import cn.jbit.biz.easybuy_userBiz;
import cn.jbit.bizimpl.easybuy_productBizImpl;
import cn.jbit.bizimpl.easybuy_userBizImpl;
import cn.jbit.entity.easybuy_product;
import cn.jbit.entity.easybuy_user;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
public class addressServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		easybuy_productBiz epb = new easybuy_productBizImpl();
		if (action.equals("buy")) {         //购买选择地址                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
			if (session.getAttribute("login") == null) {
				response.sendRedirect("login.jsp?path=" + null);//重定向到login.jsp?path=+null页面
			} else {
				int ep_id = Integer.valueOf(request.getParameter("ep_id"));//获取商品编号
				session.setAttribute("addressep_id", ep_id);
				easybuy_userBiz eub = new easybuy_userBizImpl();
				easybuy_user eu = eub.getEasyBuyUser((String) session.getAttribute("login"));
				String address = eu.getEu_Address();
				String[] listadd = address.split(";");//将地址分割
				easybuy_product ep = epb.getEasybuy_product(ep_id);
				session.setAttribute("address", listadd);
				session.setAttribute("ashop", ep);
				response.sendRedirect("address.jsp"); // 重定向到address.jsp页面
			}
		} else if (action.equals("add")) { //添加地址
			String address = request.getParameter("address");
			address = new String(address.getBytes("ISO-8859-1"),"utf-8");
			easybuy_userBiz eub = new easybuy_userBizImpl();
			String eu_user_id = (String) session.getAttribute("login");
			easybuy_user eu = eub.getEasyBuyUser(eu_user_id);
			easybuy_user euupdate = eu;
			euupdate.setEu_Address(eu.getEu_Address() + address + ";");
			int i = eub.updateUser(euupdate);
			if (i > 0) {
				response.sendRedirect("addressServlet?action=buy&ep_id="
						+ session.getAttribute("addressep_id"));
			}
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		if (action.equals("buy")) {
			if (session.getAttribute("login") == null) {
				String path = request.getHeader("referer");
				response.sendRedirect("login.jsp?path=" + path);//重定向到login.jsp?path=+path页面
			} else {
				easybuy_userBiz eub = new easybuy_userBizImpl();
				easybuy_user eu = eub.getEasyBuyUser((String) session
						.getAttribute("login"));
				String address = eu.getEu_Address();
				String[] listadd = address.split(";");// 将地址分割
				session.setAttribute("address", listadd);
				response.sendRedirect("address.jsp");//重定向到address.jsp页面
			}
		}
	}
}
