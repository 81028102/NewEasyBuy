package cn.jbit.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.jbit.biz.easybuy_orderBiz;
import cn.jbit.biz.easybuy_order_detailBiz;
import cn.jbit.biz.easybuy_userBiz;
import cn.jbit.bizimpl.easybuy_orderBizImpl;
import cn.jbit.bizimpl.easybuy_order_detailBizImpl;
import cn.jbit.bizimpl.easybuy_userBizImpl;
import cn.jbit.dao.easybuy_orderDao;
import cn.jbit.daoimpl.easybuy_orderDaoImpl;
import cn.jbit.entity.easybuy_order;
import cn.jbit.entity.easybuy_order_detail;
import cn.jbit.entity.easybuy_order_status;
import cn.jbit.entity.easybuy_user;
import cn.jbit.util.Page;
/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
@WebServlet("/orderServlet")
public class orderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		easybuy_order_detailBiz eodb=new easybuy_order_detailBizImpl();
		easybuy_orderDao eod = new easybuy_orderDaoImpl();
		easybuy_orderBizImpl eobi=new easybuy_orderBizImpl();
		if (session.getAttribute("login") == null) {
			response.sendRedirect("login.jsp");//重定向到login.jsp页面
		}
		if (session.getAttribute("detail") != null
				|| session.getAttribute("orderList") != null) {
			session.removeAttribute("detail");
			session.removeAttribute("orderList");
		}
		Page p = new Page();
		p.setPageSize(4);
		if (action.equals("first")) {
			p.setCurrPageNo(1);
			p.setTotalCount(eod.countNums(0, null));
			if (request.getParameter("currPage") == null) {
				p.setCurrPageNo(1);
			} else {
				int curr = Integer.parseInt(request.getParameter("currPage"));
				if (curr < 0) {
					curr = 1;
				}
				if (curr > p.getTotalPageCount()) {
					curr = p.getTotalPageCount();
				}
				p.setCurrPageNo(curr);
			}
			easybuy_userBiz eub = new easybuy_userBizImpl();
			easybuy_user eu = eub.getEasyBuyUser((String) session
					.getAttribute("login"));
			List<easybuy_order_status> statusList = eobi.getOrder_Status();
			List<easybuy_order> orderList = new ArrayList<easybuy_order>();
			if (eu.getEu_Status() == 1) {
				orderList = eod.getUserOrders(p.getPageSize(), p.getCurrPageNo(), eu.getEu_User_id());
			}
			if (eu.getEu_Status() == 2) {
				orderList = eod.getEasybuy_orders(p.getPageSize(), p.getCurrPageNo(), 0, null);
			}
			List<easybuy_order_detail> detail = eod.getDetail();
			session.setAttribute("page", p.getTotalPageCount());
			session.setAttribute("currpage", p.getCurrPageNo());
			session.setAttribute("detail", detail);
			session.setAttribute("orderList", orderList);
			session.setAttribute("statusList", statusList);
			session.setAttribute("heu", eu);
			response.sendRedirect("manage/order.jsp");//重定向到manage/order.jsp页面
		}if("del".equals(action)){
			int num;
			int id =Integer.parseInt(request.getParameter("id"));
			System.out.println(request.getParameter("id"));
			num=eobi.delOrderById(id);
			num=eodb.delOrderDetailById(id);
			response.sendRedirect("manage/order.jsp?message="+num);//重定向到manage/order.jsp页面
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		easybuy_orderBiz eobi=new easybuy_orderBizImpl();
		if (session.getAttribute("detail") != null
				|| session.getAttribute("orderList") != null) {
			session.removeAttribute("detail");
			session.removeAttribute("orderList");
		}
		String action = request.getParameter("action");
		if (action.equals("select")) {
			easybuy_orderDao eod = new easybuy_orderDaoImpl();
			List<easybuy_order_detail> detail = eod.getDetail();
			int id = 0;
			String user_id = null;
			if (request.getParameter("orderId") != null) {
				id = Integer.parseInt(request.getParameter("orderId"));
			}
			if (request.getParameter("userName") != null) {
				user_id = request.getParameter("userName");
			}
			List<easybuy_order> orderList = eod.getEasybuy_orders(3, 1, id,user_id);
			session.setAttribute("detail", detail);
			session.setAttribute("orderList", orderList);
			response.sendRedirect("manage/order.jsp");//重定向到manage/order.jsp页面
		}
		if(action.equals("selectDate")){
			if(session.getAttribute("date")!=null||session.getAttribute("date1")!=null){
				session.removeAttribute("date");
				session.removeAttribute("date1");
			}
			easybuy_orderDao eod = new easybuy_orderDaoImpl();
			List<easybuy_order_detail> detail = eod.getDetail();
			String date=null;
			String date1 = null;
			if (request.getParameter("date") != null&&request.getParameter("date") != "") {
				date= request.getParameter("date");
			}
			if (request.getParameter("date1") != null&&request.getParameter("date1") != "") {
				date1 = request.getParameter("date1");
			}
			List<easybuy_order> orderList = eod.getDateOrder(3, 1, date,date1,(String)session.getAttribute("login"));
			session.setAttribute("date", date);
			session.setAttribute("date1", date1);
			session.setAttribute("detail", detail);
			session.setAttribute("orderList", orderList);
			response.sendRedirect("manage/order.jsp");//重定向到manage/order.jsp页面
		}
		if("updateStatus".equals(action)){
			easybuy_order eo=new easybuy_order();
			eo.setEo_Status(Integer.parseInt(request.getParameter("es_Id")));//获取订单状态
			System.out.println(request.getParameter("es_Id"));
			System.out.println("修改---!");
			eo.setEo_Id(Integer.parseInt(request.getParameter("eo_Id")));//获取订单号
			System.out.println(request.getParameter("eo_Id"));
			int i=eobi.updateStatus(eo);
			if(i>0){
				System.out.println("修改成功!");
				/*request.getRequestDispatcher("orderServlet?action=first").forward(request, response); */	
				response.sendRedirect("manage/order.jsp?message="+i);//重定向到manage/order.jsp页面
			}else{
				System.out.println("修改失败!");
				response.sendRedirect("manage/order.jsp?message="+i);//重定向到manage/order.jsp页面
			}
		}
	}
}
