package cn.jbit.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.jbit.biz.easybuy_order_detailBiz;
import cn.jbit.biz.easybuy_productBiz;
import cn.jbit.biz.easybuy_userBiz;
import cn.jbit.bizimpl.easybuy_order_detailBizImpl;
import cn.jbit.bizimpl.easybuy_productBizImpl;
import cn.jbit.bizimpl.easybuy_userBizImpl;
import cn.jbit.dao.easybuy_orderDao;
import cn.jbit.daoimpl.easybuy_orderDaoImpl;
import cn.jbit.entity.easybuy_order;
import cn.jbit.entity.easybuy_order_detail;
import cn.jbit.entity.easybuy_product;
import cn.jbit.entity.easybuy_user;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
@WebServlet("/shoppingServlet")
public class shoppingServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		HttpSession session=request.getSession();
		// 创建用户逻辑层对象
		easybuy_productBiz epb=(easybuy_productBiz) new easybuy_productBizImpl();
		@SuppressWarnings("unchecked")
		Map<easybuy_product, Integer> shop=(Map<easybuy_product, Integer>)session.getAttribute("shop");
		//判断购物车是否为空
		if(session.getAttribute("count")==null){
			session.setAttribute("count", 0);
		}
		//添加商品
		if(action.equals("add")){
			int ep_id=Integer.parseInt(request.getParameter("ep_id"));//获取商品编号
			easybuy_product ep=epb.getEasybuy_product(ep_id);
			//判断商品为空的时候
			if(shop==null){
				shop=new HashMap<easybuy_product, Integer>();
			}
			boolean flag=false;
			//Iterator迭代器遍历
			Iterator<Entry<easybuy_product, Integer>> iter=shop.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<easybuy_product, Integer> entry=(Map.Entry<easybuy_product, Integer>)iter.next();
				if (entry.getKey().getEp_Id()==ep.getEp_Id()) {
					float price=entry.getKey().getEp_Price()/entry.getValue();
					entry.setValue(entry.getValue()+1);
					entry.getKey().setEp_Price(price*entry.getValue());
					session.setAttribute("count",(Integer)session.getAttribute("count")+1);
					flag=true;
					break;
				}
			}
			if(flag==false){
				shop.put(ep, 1);
				session.setAttribute("count",(Integer)session.getAttribute("count")+1);
			}
		}
		//删除商品
		if(action.equals("del")){
			Integer id=Integer.parseInt(request.getParameter("ep"));
			easybuy_product ep=epb.getEasybuy_product(id);
			Iterator<Entry<easybuy_product, Integer>> iter=shop.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<easybuy_product, Integer> entry=(Map.Entry<easybuy_product, Integer>)iter.next();
				if (entry.getKey().getEp_Id()==ep.getEp_Id()) {
					shop.remove(entry.getKey());
					session.setAttribute("count",(Integer)session.getAttribute("count")-entry.getValue());
					break;
				}
			}
		}
		if(action.equals("minus")){
			int ep_id=Integer.parseInt(request.getParameter("ep_id"));//获取商品编号
			easybuy_product ep=epb.getEasybuy_product(ep_id);
			Iterator<Entry<easybuy_product, Integer>> iter=shop.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<easybuy_product, Integer> entry=(Map.Entry<easybuy_product, Integer>)iter.next();
				if (entry.getKey().getEp_Id()==ep.getEp_Id()) {
					if (entry.getValue()==1) {
						shop.remove(entry.getKey());
						break;
					}
					float price=entry.getKey().getEp_Price()/entry.getValue();
					entry.setValue(entry.getValue()-1);
					entry.getKey().setEp_Price(price*entry.getValue());
					session.setAttribute("count", (Integer)session.getAttribute("count")-1);
					break;
				}
			}
		}
		//放到作用域中
		session.setAttribute("shop",shop);
		//request.getRequestDispatcher("shopping.jsp").forward(request, response);
		response.sendRedirect("shopping.jsp");//重定向到shopping.jsp页面
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		easybuy_productBiz epb=(easybuy_productBiz) new easybuy_productBizImpl();
		easybuy_orderDao eod=new easybuy_orderDaoImpl();
		easybuy_order_detailBiz eodd=new easybuy_order_detailBizImpl();
		//判断是否登陆
		if(session.getAttribute("login")==null){
			response.sendRedirect("login.jsp");//重定向到login.jsp页面
		}
		String action=request.getParameter("action");
		//购买商品
		if(action.equals("buy")){
			easybuy_userBiz eub=new easybuy_userBizImpl();
			easybuy_user eu=eub.getEasyBuyUser((String)session.getAttribute("login"));//获取用户名
			easybuy_order eo=new easybuy_order();
			eo.setEo_User_id(eu.getEu_User_id());
			eo.setEo_User_name(eu.getEu_User_name());//获取用户姓名
			eo.setEo_User_address(request.getParameter("address"));//获取收货地址
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取系统当前时间
			eo.setEo_Create_time(sf.format(new Date()));

			//添加地址并且判断addressep_id不能为空
			if(session.getAttribute("addressep_id")!=null){
				easybuy_product ep=epb.getEasybuy_product((Integer) session.getAttribute("addressep_id"));
				eo.setEo_Cost(ep.getEp_Price());
				eo.setEo_Status(1);
				eo.setEo_Score(ep.getEp_Price()/10);
				request.getSession().setAttribute("Price", ep.getEp_Price());
				int result=eod.add(eo);//购买商品
				if(result>0){
					easybuy_order_detail eoo=new easybuy_order_detail();
					eoo.setEp_Id((Integer)session.getAttribute("addressep_id"));
					eoo.setEod_Quantity(1);
					eoo.setEod_Cost(ep.getEp_Price());
					int i=eodd.adddetail(eoo);
					if(i>0){
						request.getRequestDispatcher("pay.jsp").forward(request, response);//转发到pay.jsp页面
					}
				}
			}
			//判断商品不能为空并且addressep_id为空
			if(session.getAttribute("shop")!=null&&session.getAttribute("addressep_id")==null){
				@SuppressWarnings("unchecked")
				Map<easybuy_product, Integer> shop=(Map<easybuy_product, Integer>)session.getAttribute("shop");
				Iterator<Entry<easybuy_product, Integer>> iter=shop.entrySet().iterator();
				float totalPrice=0;
				boolean flag=false;
				while (iter.hasNext()) {
					Map.Entry<easybuy_product, Integer> entry=(Map.Entry<easybuy_product, Integer>)iter.next();
					totalPrice+=entry.getKey().getEp_Price();
					request.getSession().setAttribute("Price", totalPrice);
				}
				eo.setEo_Cost(totalPrice);
				eo.setEo_Status(1);
				//添加商品
				int result=eod.add(eo);
				if(result>0){
					Iterator<Entry<easybuy_product, Integer>> iters=shop.entrySet().iterator();
					//遍历输出信息
					while (iters.hasNext()) {
						Map.Entry<easybuy_product, Integer> entry=(Map.Entry<easybuy_product, Integer>)iters.next();
						easybuy_order_detail eoo=new easybuy_order_detail();
						eoo.setEp_Id(entry.getKey().getEp_Id());
						eoo.setEod_Quantity(entry.getValue());
						eoo.setEod_Cost(entry.getKey().getEp_Price()/entry.getValue());
						int i=eodd.adddetail(eoo);
						if(i>0){
							flag=true;
						}
					}
					if(flag){
						//count
						session.setAttribute("count", 0);
						//放到作用域中存储移除
						session.removeAttribute("shop");
						request.getRequestDispatcher("payConfirm.jsp").forward(request, response);//转发到payConfirm.jsp页面
					}
				}
			}
		}
	}
}
