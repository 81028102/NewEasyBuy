package cn.jbit.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.jbit.biz.easybuy_productBiz;
import cn.jbit.biz.easybuy_product_Biz;
import cn.jbit.bizimpl.easybuy_productBizImpl;
import cn.jbit.bizimpl.easybuy_product_BizImpl;
import cn.jbit.entity.easybuy_assess;
import cn.jbit.entity.easybuy_product;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
public class productServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		HttpSession session=request.getSession();
		//创建各个对象
		easybuy_productBiz epb=(easybuy_productBiz) new easybuy_productBizImpl();
		easybuy_product_Biz epbs = new easybuy_product_BizImpl();
		//前台查看商品信息及评价信息
		if(action.equals("look")){
			//获取最新浏览信息
			@SuppressWarnings("unchecked")
			List<easybuy_product> looklist=(List<easybuy_product>) session.getAttribute("looklist");
			//如果为空初始化list
			if(looklist==null){
				looklist=new ArrayList<easybuy_product>();
			}
			int ep_id=Integer.parseInt(request.getParameter("ep_id"));//获取商品编号
			easybuy_product ep=epb.getEasybuy_product(ep_id);//查询商品
			boolean flag=true;
			if(looklist!=null){
				//循环list查询是否有该商品
				for (int i = 0; i < looklist.size(); i++) {
					if(looklist.get(i).getEp_Id()==ep.getEp_Id()){
						flag=false;
					}
				}
			}
			if(flag){//如果集合中不存在该商品的信息
				//添加该商品信息同时固定list保存数量大小
				looklist.add(ep);
				if(looklist.size()>5){
					looklist.remove(0);
				}
			}
			//清除session内保存的商品信息
			if(session.getAttribute("ep")!=null){
				session.removeAttribute("ep");
			}
			//接收cpage(当前页)
			String cpage = request.getParameter("cpage");
			if(cpage==null){
				cpage="1";
			}
			//定义一个分页单位(pageSize)
			int pageSize=10;
			//分页查询评价信息
			//查询评价总条数
			int counta = epbs.getAssessCount(ep_id);
			//计算一个总页数
			int totalPage=(counta/pageSize)+(counta%pageSize==0?0:1);
			//如果第一次打开或者获取全部留言信息
			List<easybuy_assess> list = epbs.getAssess(Integer.parseInt(cpage),pageSize,ep_id);
			request.setAttribute("cpage", cpage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("counta", counta);
			session.setAttribute("assesslist", list);
			//保存新的looklist集合同时把转到商品详细信息
			session.setAttribute("looklist", looklist);
			session.setAttribute("ep", ep);
			request.getRequestDispatcher("product-view.jsp").forward(request, response);//转发到product-view.jsp页面

		}
		//添加评价信息
		if (action.equals("add")) {
			//判断是否登陆
			if (session.getAttribute("login") == null) {
				response.sendRedirect("login.jsp?path="+null);//重定向到login.jsp?path=页面
			} else {
				easybuy_assess ea = new easybuy_assess();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取系统当前时间
				ea.setEa_Create_time(sf.format(new Date()));
				ea.setEa_Nike_name((String) session.getAttribute("login"));
				ea.setEa_Assess(request.getParameter("ea_assess"));
				ea.setEa_Assessid(Integer.parseInt(request.getParameter("ep_id")));//获取商品编号
				int result = epbs.addAssess(ea);
				if (result > 0) {
					//获取最新浏览信息
					@SuppressWarnings("unchecked")
					List<easybuy_product> looklist=(List<easybuy_product>) session.getAttribute("looklist");
					//如果为空初始化list
					if(looklist==null){
						looklist=new ArrayList<easybuy_product>();
					}
					int ep_id=Integer.parseInt(request.getParameter("ep_id"));//获取商品id
					easybuy_product ep=epb.getEasybuy_product(ep_id);//查询商品
					boolean flag=true;
					if(looklist!=null){
						//循环list查询是否有该商品
						for (int i = 0; i < looklist.size(); i++) {
							if(looklist.get(i).getEp_Id()==ep.getEp_Id()){
								flag=false;
							}
						}
					}
					if(flag){//如果集合中不存在该商品的信息
						//添加该商品信息同时固定list保存数量大小
						looklist.add(ep);
						if(looklist.size()>5){
							looklist.remove(0);
						}
					}
					//清除session内保存的商品信息
					if(session.getAttribute("ep")!=null){
						session.removeAttribute("ep");
					}
					//接收cpage(当前页)
					String cpage = request.getParameter("cpage");
					if(cpage==null){
						cpage="1";
					}
					//定义一个分页单位(pageSize)
					int pageSize=10;
					//分页查询评价信息
					//查询评价总条数
					int counta = epbs.getAssessCount(ep_id);
					//计算一个总页数
					int totalPage=(counta/pageSize)+(counta%pageSize==0?0:1);
					//如果第一次打开或者获取全部留言信息
					List<easybuy_assess> list = epbs.getAssess(Integer.parseInt(cpage),pageSize,ep_id);
					request.setAttribute("cpage", cpage);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("counta", counta);
					session.setAttribute("assesslist", list);
					//保存新的looklist集合同时把转到商品详细信息
					session.setAttribute("looklist", looklist);
					session.setAttribute("ep", ep);
					request.getRequestDispatcher("product-view.jsp").forward(request, response);//转发到product-view.jsp页面
				} else {
					response.sendRedirect("product-view.jsp");//重定向到product-view.jsp页面
				}
			}
		}
		//后台查看评价信息
		if(action.equals("looks")){ //后台管理
			//判断是否登陆
			if (session.getAttribute("login") == null) {
				response.sendRedirect("login.jsp?path="+null);//重定向到login.jsp?path=页面
			}
			//接收cpage(当前页)
			String cpage = request.getParameter("cpage");
			if(cpage==null){
				cpage="1";
			}
			//接收模糊查询的条件
			String ea_assess = request.getParameter("ea_assess");//获取评价内容
			if(ea_assess==null){
				ea_assess="";
			}
			//定义一个分页单位(pageSize)
			int pageSize=10;
			//分页查询评价信息
			//查询评价总条数
			int countass = epbs.getAssessCount(ea_assess);
			//计算一个总页数
			int totalPage=(countass/pageSize)+(countass%pageSize==0?0:1);
			List<easybuy_assess> looksList = epbs.getAssessProduct(Integer.parseInt(cpage),pageSize,ea_assess);
			session.setAttribute("looksList", looksList);
			request.setAttribute("cpage", cpage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("countass", countass);
			request.getRequestDispatcher("manage/assess.jsp").forward(request, response);//转发到manage/assess.jsp页面
		}
		//根据id删除评价信息
		if(action.equals("del")){
			int i=epbs.delAssessById(request.getParameter("ea_upid"));//获取主键评价编号
			if(i>0){
				request.getRequestDispatcher("productServlet?action=looks&message="+i).forward(request, response);//转发到productServlet?action=looks
			}else{   
				response.sendRedirect("productServlet?action=looks&message="+i);//重定向到productServlet?action=looks
			}
		}
		//删除全部评价信息
		if(action.equals("delAssessAll")){
			int i=epbs.delAssessAll();//获取主键评价编号
			if(i>0){
				request.getRequestDispatcher("productServlet?action=looks&message="+i).forward(request, response);//转发到productServlet?action=looks
			}else{
				request.getRequestDispatcher("productServlet?action=looks&message="+i).forward(request, response);//转发到productServlet?action=looks
			}
		}
	}
}