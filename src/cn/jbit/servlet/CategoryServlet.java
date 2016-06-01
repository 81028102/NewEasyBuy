package cn.jbit.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.jbit.biz.easybuy_product_categoryBiz;
import cn.jbit.bizimpl.easybuy_product_categoryBizImpl;
import cn.jbit.entity.easybuy_product_category;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
public class CategoryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		easybuy_product_categoryBiz epcb = new easybuy_product_categoryBizImpl();
		if(session.getAttribute("cateOne")!=null){//清空一级分类
			session.removeAttribute("cateOne");
		}
		if(session.getAttribute("cateTwo")!=null){//清空二级分类
			session.removeAttribute("cateTwo");
		}
		if (action.equals("all")) {					//分类管理
			List<easybuy_product_category> cateOne = epcb.getCategories(0);
			List<easybuy_product_category> cateTwo = epcb.getCategories(1);
			session.setAttribute("cateOne", cateOne);//保存一级分类
			session.setAttribute("cateTwo", cateTwo);//保存二级分类
			response.sendRedirect("manage/productClass.jsp");//重定向到manage/productClass.jsp页面
		}
		if (action.equals("alls")) {	
			//接收cpage(当前页)
			String cpage = request.getParameter("cpage");
			if(cpage==null){
				cpage="1";
			}
			//接收模糊查询的条件
			String epc_name = request.getParameter("epc_name");//获取分类名称
			if(epc_name==null){
				epc_name="";
			}
			//定义一个分页单位(pageSize)
			int pageSize=12;
			//分页查询分类信息
			//查询分类总条数
			int countepc=epcb.getCategoryCount(epc_name);
			//计算一个总页数
			int totalPage=(countepc/pageSize)+(countepc%pageSize==0?0:1);
			List<easybuy_product_category> epcList=epcb.getAlls(Integer.parseInt(cpage),pageSize,epc_name);
			//放到作用域中
			request.getSession().setAttribute("epcList",epcList);
			request.setAttribute("cpage", cpage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("countepc", countepc);
			request.setAttribute("epc_name", epc_name);
			request.getRequestDispatcher("manage/productClass-top.jsp").forward(request, response);
		}
		if (action.equals("add")) {					//新增
			if(session.getAttribute("hmessage")!=null){
				session.removeAttribute("hmessage");
			}
			if(session.getAttribute("sb")!=null){
				session.removeAttribute("sb");
			}
			if(session.getAttribute("hbool")!=null){
				session.removeAttribute("hbool");
			}
			if(session.getAttribute("hname")!=null){
				session.removeAttribute("hname");
			}
			List<easybuy_product_category> cateOne = epcb.getCategories(0);
			session.setAttribute("cateOne", cateOne);
			response.sendRedirect("manage/productClass-add.jsp");//重定向到manage/productClass-add.jsp页面
		}
		if(action.equals("update")){				//修改
			int epc_id=Integer.parseInt(request.getParameter("epc_id"));
			easybuy_product_category e=epcb.getCategory(epc_id);
			List<easybuy_product_category> cateOne = epcb.getCategories(0);
			session.setAttribute("cateOne", cateOne);//保存一级分类
			session.setAttribute("cate", e);
			response.sendRedirect("manage/productClass-modify.jsp");//重定向到manage/productClass-modify.jsp页面
		}
		if(action.equals("del")){					//删除
			int epc_id=Integer.parseInt(request.getParameter("epc_id"));//获取商品分类编号
			int result=epcb.delCategory(epc_id);
			if(result>0){
				response.sendRedirect("manage/productClass.jsp");//重定向到manage/productClass.jsp页面
			}else{
				response.sendRedirect("manage/productClass.jsp");//重定向到manage/productClass.jsp页面
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//表单提交
		String action = request.getParameter("action");	
		HttpSession session = request.getSession();
		easybuy_product_categoryBiz epcb = new easybuy_product_categoryBizImpl();
		if(action.equals("add")){					//新增
			easybuy_product_category epc = new easybuy_product_category();
			String epc_name = request.getParameter("className");//获取商品分类名称
			epc.setEpc_Name(epc_name);//分类名称
			epc.setEpc_Click_Count(0);//初始分类点击量为0
			epc.setEpc_Parent_id(Integer.parseInt(request.getParameter("parentId")));//获取商品分类父级编号
			if (epcb.boolcate(epc_name)==false) {
				int result = epcb.addCategory(epc);
				if (result > 0) {
					session.setAttribute("hmessage", "操作成功!");
					response.sendRedirect("manage/productClass-add.jsp");//重定向到manage/productClass-add.jsp页面
				} else {
					session.setAttribute("sb", "操作失败!");
					response.sendRedirect("manage/productClass-add.jsp");//重定向到manage/productClass-add.jsp页面
				}
			}else{
				session.setAttribute("hbool", "已经存在该分类信息,不需要多次添加!");
				session.setAttribute("hname", epc_name);
				response.sendRedirect("manage/productClass-add.jsp");//重定向到manage/productClass-add.jsp页面
			}
		}
		if (action.equals("alls")) {	
			//接收cpage(当前页)
			String cpage = request.getParameter("cpage");
			if(cpage==null){
				cpage="1";
			}
			//接收模糊查询的条件
			String epc_name = request.getParameter("epc_name");//获取分类名称
			if(epc_name==null){
				epc_name="";
			}
			//定义一个分页单位(pageSize)
			int pageSize=12;
			//分页查询分类信息
			//查询分类总条数
			int countepc=epcb.getCategoryCount(epc_name);
			//计算一个总页数
			int totalPage=(countepc/pageSize)+(countepc%pageSize==0?0:1);
			List<easybuy_product_category> epcList=epcb.getAlls(Integer.parseInt(cpage),pageSize,epc_name);
			//放到作用域中
			request.getSession().setAttribute("epcList",epcList);
			request.setAttribute("cpage", cpage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("countepc", countepc);
			request.setAttribute("epc_name", epc_name);
			request.getRequestDispatcher("manage/productClass-top.jsp").forward(request, response);
		}
		if(action.equals("update")){
			easybuy_product_category epc=new easybuy_product_category();
			String oldclassName = request.getParameter("oldclassName");//获取商品"老"分类名称
			String epc_name = request.getParameter("className");//获取商品"新"分类名称
			epc.setEpc_Id(Integer.parseInt(request.getParameter("epc_id")));//获取商品分类编号
			epc.setEpc_Name(epc_name);
			epc.setEpc_Parent_id(Integer.parseInt(request.getParameter("parentId")));//获取商品父分类编号
			if (epcb.boolcate(epc_name)==false||oldclassName.equals(epc_name)) {
				int result=epcb.updateCategory(epc);
				if(result>0){
					session.setAttribute("amessage", "操作成功!");
					response.sendRedirect("manage/productClass-modify.jsp");//重定向到manage/productClass-modify.jsp页面
				}else{
					session.setAttribute("sbs", "操作失败!");
					response.sendRedirect("manage/productClass-modify.jsp");//重定向到manage/productClass-modify.jsp页面
				}
			}else{
				session.setAttribute("abool", "已经存在该分类信息,不需要多次添加!");
				response.sendRedirect("manage/productClass-modify.jsp");//重定向到manage/productClass-modify.jsp页面
			}
		}
	}
}