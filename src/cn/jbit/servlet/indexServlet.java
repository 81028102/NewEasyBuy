package cn.jbit.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.jbit.biz.easybuy_newsBiz;
import cn.jbit.biz.easybuy_productBiz;
import cn.jbit.biz.easybuy_product_categoryBiz;
import cn.jbit.bizimpl.easybuy_newsBizImpl;
import cn.jbit.bizimpl.easybuy_productBizImpl;
import cn.jbit.bizimpl.easybuy_product_categoryBizImpl;
import cn.jbit.entity.easybuy_news;
import cn.jbit.entity.easybuy_product;
import cn.jbit.entity.easybuy_product_category;
import cn.jbit.util.Page;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
public class indexServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		// 创建各个对象
		easybuy_productBiz epb = (easybuy_productBiz) new easybuy_productBizImpl(); // 商品基本信息表
		easybuy_newsBiz enb = new easybuy_newsBizImpl(); // 新闻表
		easybuy_product_categoryBiz epc = new easybuy_product_categoryBizImpl(); // 商品分类表
		Page p = new Page();
		// 首次进入
		if (action.equals("first")) {
			//设置分页
			p.setPageSize(24); // 每一页记录数
			p.setTotalCount(epb.getProductCount()); // 总记录数
			if (request.getParameter("currPage") == null) {
				p.setCurrPageNo(1); // 设置当前页码为1
			} else {
				int curr = Integer.parseInt(request.getParameter("currPage")); // 获取当前页码数
				if (curr < 0) { // 如果当前页码小于0
					curr = 1; // 则赋值为1
				}
				if (curr > p.getTotalPageCount()) { // 如果当前页码大于总页数
					curr = p.getTotalPageCount(); // 则等于总页数
				}
				p.setCurrPageNo(curr); // 设置当前页数为curr
			}
			// 创建集合对象
			List<easybuy_product> shopps = epb.getEasybuy_products(
					p.getPageSize(), p.getCurrPageNo(), "", 0); // 创建一个商品集合
			List<easybuy_news> news = enb.getTen(); // 获取新闻
			//保存页面信息
			/*	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			session.setAttribute("Time", sdf.format(new Date()));//获取系统当前时间
			 */
			session.setAttribute("shopps", shopps); // session对象保存查询出来的所有商品
			session.setAttribute("news", news); // session 对象保存查询出来的所有新闻
			session.setAttribute("page", p.getTotalPageCount()); // session对象保存总页数
			session.setAttribute("currpage", p.getCurrPageNo()); // session对象保存当前页码
			request.getRequestDispatcher("index.jsp").forward(request, response);//转发到index.jsp页面
			/*response.sendRedirect("index.jsp"); // 重定向到index.jsp页面*/		
		}
		if (action.equals("news")) { // 如果是新闻点击 每次点击率增加1
			easybuy_news en = enb.getNews(Integer.parseInt(request.getParameter("en_id"))); //点击的新闻的id创建一个
			en.setEn_Id(Integer.parseInt(request.getParameter("en_id")));//获取新闻编号
			enb.updateNewCount(en);
			request.setAttribute("en", en);
			request.getRequestDispatcher("news-view.jsp").forward(request,
					response);//转发到news-view.jsp页面
		}
		if (action.equals("category")) {
			p.setPageSize(12); // 设置页面显示内容的数量
			int parent_id = 0; // 定义变量parent_id=0;
			int id = 0; // 定义id=0;
			String name = ""; // 定义name
			String epcc = ""; // 商品分类名称
			String category = request.getParameter("category"); // 定义category分类
			if (request.getParameter("parent_id") != null) { // 如果点击了id分类
				parent_id = Integer.parseInt(request.getParameter("parent_id"));
				easybuy_product_category eppcc = epc.getCategory(parent_id); // 获取指定的id分类
				epcc = eppcc.getEpc_Name(); // 商品分类名称
			}
			if (request.getParameter("epc_id") != null) {//如果点击左导航id
				id = Integer.parseInt(request.getParameter("epc_id"));//获取到分类编号
				String type = "";
				if (category.equals("one")) {//一级分类
					type = "0";
					easybuy_product_category eppcc = epc.getCategory(id);
					name = eppcc.getEpc_Name();
				} else {//二级分类
					type = "1";
					easybuy_product_category eppcc = epc.getCategory(id);
					name = eppcc.getEpc_Name();
				}
				p.setTotalCount(epb.getTypeCount(id, type));//获取商品数量实现分页
				if (epb.getTypeCount(id, "0") == 0) {
					p.setTotalCount(0);
				}
			}
			if (request.getParameter("currPage") == null) {//初始化分页
				p.setCurrPageNo(1);//设置当前页码为1
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
			easybuy_product_category epccc=new easybuy_product_category();
			epccc.setEpc_Id(Integer.parseInt(request.getParameter("epc_id")));//获取到分类编号;
			epc.updateCategoryCount(epccc);
			//实现分页查询显示保存页面切换top内容及页面信息
			List<easybuy_product> shop = epb.getEasybuy_products(
					p.getPageSize(), p.getCurrPageNo(), category, id);
			request.setAttribute("product", shop);
			request.setAttribute("name", name);
			request.setAttribute("epc", epcc);
			request.setAttribute("id", parent_id);
			request.setAttribute("cate", category);
			if (p.getTotalPageCount() != 0){
				session.setAttribute("page", p.getTotalPageCount());
				session.setAttribute("epc_id", id);
				session.setAttribute("currpage", p.getCurrPageNo());
				request.getRequestDispatcher("product-list.jsp").forward(request,
						response);//转发到product-list.jsp页面
			}
		}
		easybuy_productBizImpl pro=new easybuy_productBizImpl();
		List<easybuy_product> products=new ArrayList<easybuy_product>();
		if ("search".equals(action)) {   //模糊子查询搜索商品
			String info = request.getParameter("sea");//六种查询方式
			String infos = request.getParameter("sea");
			String infoss = request.getParameter("sea");
			String ep_price = request.getParameter("sea");
			String ep_address= request.getParameter("sea");
			String ep_description= request.getParameter("sea");
			products = pro.getInfo(info, infos,infoss,ep_price,ep_address,ep_description);
			request.setAttribute("product", products);//保存商品
			request.getRequestDispatcher("product-list.jsp").forward(request,response);//转发到product-list.jsp页面
		}
		if("resemble".equals(action)){ //模糊子查询相似商品
			String ep_name1 = request.getParameter("ep_name");//六种相似方式
			CharSequence ep_name=ep_name1.subSequence(0,1);
			ep_name=ep_name1.subSequence(0,2);
			ep_name=ep_name1.subSequence(0,3);
			ep_name=ep_name1.subSequence(0,4);
			ep_name=ep_name1.subSequence(0,5);
			ep_name=ep_name1.subSequence(1,2);
			ep_name=ep_name1.subSequence(1,3);
			ep_name=ep_name1.subSequence(1,4);
			ep_name=ep_name1.subSequence(1,5);
			ep_name=ep_name1.subSequence(2,3);
			ep_name=ep_name1.subSequence(2,4);
			ep_name=ep_name1.subSequence(2,5);
			ep_name=ep_name1.subSequence(3,4);
			ep_name=ep_name1.subSequence(3,5);
			ep_name=ep_name1.subSequence(4,5);
			float ep_price =Float.parseFloat(request.getParameter("ep_price"));//获取到商品价格
			String ep_address= request.getParameter("ep_address");//获取商品产地
			String ep_description= request.getParameter("ep_description");//获取商品描述
			products=pro.getResemble(ep_name,ep_price,ep_address,ep_description);
			session.setAttribute("epc_id", 0);
			request.setAttribute("product", products);//保存商品
			request.getRequestDispatcher("product-list.jsp").forward(request,response);//转发到product-list.jsp页面
		}
	}
}