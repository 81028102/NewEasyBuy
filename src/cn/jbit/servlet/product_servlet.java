package cn.jbit.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import cn.jbit.biz.easybuy_productBiz;
import cn.jbit.biz.easybuy_product_categoryBiz;
import cn.jbit.bizimpl.easybuy_productBizImpl;
import cn.jbit.bizimpl.easybuy_product_categoryBizImpl;
import cn.jbit.daoimpl.easybuy_productDaoImpl;
import cn.jbit.entity.easybuy_product;
import cn.jbit.entity.easybuy_product_category;
import cn.jbit.util.Page;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
public class product_servlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		if(session.getAttribute("hone")!=null||session.getAttribute("htwo")!=null||session.getAttribute("list")!=null){
			session.removeAttribute("hone");
			session.removeAttribute("htwo");
			session.removeAttribute("list");
		}
		if (action.equals("first")) {
			easybuy_productDaoImpl e = new easybuy_productDaoImpl();
			Page p = new Page();
			p.setPageSize(3);//展示数量为3
			p.setCurrPageNo(1);
			p.setTotalCount(e.getProductCount());
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
			List<easybuy_product> list = new ArrayList<easybuy_product>();
			list = e.getEasybuy_products(p.getPageSize(), p.getCurrPageNo(), "", 0);
			request.getSession().setAttribute("list", list);
			session.setAttribute("page", p.getTotalPageCount());
			session.setAttribute("currpage", p.getCurrPageNo());
			response.sendRedirect("manage/product.jsp");//重定向到manage/product.jsp页面
		}else if(action.equals("firsts")){
			easybuy_productDaoImpl e = new easybuy_productDaoImpl();
			//接收cpage(当前页)
			String cpage = request.getParameter("cpage");
			if(cpage==null){
				cpage="1";
			}
			//接收模糊查询的条件
			String ep_name = request.getParameter("ep_name");//获取商品名称
			if(ep_name==null){
				ep_name="";
			}
			//定义一个分页单位(pageSize)
			int pageSize=3;
			//分页查询商品销量信息
			//查询商品销量总条数
			int countp=e.getSalesCount(ep_name);
			//计算一个总页数
			int totalPage=(countp/pageSize)+(countp%pageSize==0?0:1);
			List<easybuy_product> nlist=e.getSalesAllProduct(Integer.parseInt(cpage),pageSize,ep_name);
			//放到作用域中
			request.getSession().setAttribute("nlist", nlist);
			request.setAttribute("cpage", cpage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("countp", countp);
			request.setAttribute("ep_name", ep_name);
			request.getRequestDispatcher("manage/product-top.jsp").forward(request, response);//转发到manage/product-top.jsp页面
			/*	response.sendRedirect("manage/product-top.jsp");//重定向到manage/product-top.jsp页面
			 */		
		} else if (action.equals("del")) {
			easybuy_productDaoImpl e = new easybuy_productDaoImpl();
			int num = e.delProduct(request.getParameter("id"));//获取商品编号
			if (num > 0) {
				response.sendRedirect("manage/product.jsp?message="+num);//重定向到manage/product.jsp?message=页面
			}else{
				response.sendRedirect("manage/product.jsp");//重定向到manage/product.jsp页面
			}
		} else if (action.equals("update")) {//修改
			easybuy_productDaoImpl e = new easybuy_productDaoImpl();
			Integer id = Integer.parseInt(request.getParameter("id"));//获取商品编号
			easybuy_product product = e.getEasybuy_product(id);
			if (product != null) {
				easybuy_product_categoryBiz e1 = new easybuy_product_categoryBizImpl();
				List<easybuy_product_category> hone = e1.getCategories(0);
				List<easybuy_product_category> htwo = e1.getCategories(1);
				session.setAttribute("hone", hone);
				session.setAttribute("htwo", htwo);
				session.setAttribute("hproduct", product);
				response.sendRedirect("manage/product-modify.jsp");//重定向到manage/product-modify.jsp页面
			}
		}else if(action.equals("add")){//添加
			easybuy_product_categoryBiz e1 = new easybuy_product_categoryBizImpl();
			List<easybuy_product_category> hone = e1.getCategories(0);
			List<easybuy_product_category> htwo = e1.getCategories(1);
			session.setAttribute("hone", hone);
			session.setAttribute("htwo", htwo);
			response.sendRedirect("manage/product-add.jsp");//重定向到manage/product-add.jsp页面
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8;");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		String action = request.getParameter("action");
		easybuy_productBiz biz=(easybuy_productBiz) new easybuy_productBizImpl();
		easybuy_product p=new easybuy_product();
		// 文件上传
		String fieldName = "";  //表单字段元素的name属性值	
		String type="";//标识是否修改商品图片
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		//上传文件的存储路径（服务器文件系统上的绝对文件路径）
		String uploadFilePath = getServletContext().getRealPath("images/product");
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(100*100*5);			
			//解析form表单中所有文件
			try{
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while(iter.hasNext()) {   //依次处理每个文件
					FileItem item = (FileItem) iter.next();
					if (item.isFormField()){  //普通表单字段
						fieldName = item.getFieldName();  //表单字段的name属性值							
						if (fieldName.equals("productName")){   //商品名称   
							p.setEp_Name(item.getString("UTF-8"));
						}else if(fieldName.equals("productdesc")){ //商品描述               	 
							p.setEp_Description(item.getString("UTF-8"));
						}else if(fieldName.equals("parentId")){//所属二级分类ID       
							int child=Integer.parseInt(item.getString("UTF-8"));
							p.setEpc_Child_id(child);
							easybuy_product_categoryBiz epcb=new easybuy_product_categoryBizImpl();
							int parentId=epcb.getparent(child);
							p.setEpc_id(parentId);
						}else if(fieldName.equals("productPrice")){ //商品价格             	 
							p.setEp_Price(Float.parseFloat(item.getString("UTF-8")));
						}else if(fieldName.equals("productCount")){ //库存
							p.setEp_Stock(Integer.parseInt(item.getString("UTF-8")));
						}else if(fieldName.equals("address")){ //原产地
							p.setEp_Address(item.getString("UTF-8"));
						}else if(fieldName.equals("ep_Create_time")){ //创建商品时间
							SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取系统当前时间
							p.setEp_Create_time(sf.format(new Date()));
						}else if(fieldName.equals("ep_Sales")){ //销量
							p.setEp_Sales(0);
						}
					}else{   //文件表单字段
						String fileName = item.getName();
						List<String> filType=Arrays.asList("gif","jpg","jpeg","png");
						String ext=fileName.substring(fileName.lastIndexOf(".")+1);
						if(fileName!=null&&!fileName.equals("")&&!filType.contains(ext)){  //判断文件类型是否在允许范围内
							out.println("<script type='text/javascript'>");
							out.println("	alert('上传失败，文件类型只能是gif、jpg、jpeg、png');");
							out.println("	location.href = '../manage/news-add.jsp';");
							out.println("</script>");
						}else if (fileName != null && !fileName.equals("")) {
							type="file";
							File fullFile = new File(item.getName());
							File saveFile = new File(uploadFilePath, fullFile.getName());
							try {
								item.write(saveFile);
							} catch (Exception e) {
								e.printStackTrace();
							}						
							p.setEp_File_name("images/product/"+fullFile.getName());	
						}else if(fileName == null||fileName.equals("")){
							type="nofile";
						}
					}		
				}
			}catch(FileUploadBase.SizeLimitExceededException ex){
				out.println("<script type='text/javascript'>");
				out.println("	alert('上传失败，文件太大，单个文件的最大限制是：5MB');");
				out.println("	location.href = '../manage/news-add.jsp';");
				out.println("</script>");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}	    
		if(action.equals("firsts")){
			easybuy_productDaoImpl e = new easybuy_productDaoImpl();
			//接收cpage(当前页)
			String cpage = request.getParameter("cpage");
			if(cpage==null){
				cpage="1";
			}
			//接收模糊查询的条件
			String ep_name = request.getParameter("ep_name");//获取商品名称
			if(ep_name==null){
				ep_name="";
			}
			//定义一个分页单位(pageSize)
			int pageSize=3;
			//分页查询商品销量信息
			//查询商品销量总条数
			int countp=e.getSalesCount(ep_name);
			//计算一个总页数
			int totalPage=(countp/pageSize)+(countp%pageSize==0?0:1);
			List<easybuy_product> nlist=e.getSalesAllProduct(Integer.parseInt(cpage),pageSize,ep_name);
			//放到作用域中
			request.getSession().setAttribute("nlist", nlist);
			request.setAttribute("cpage", cpage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("countp", countp);
			request.setAttribute("ep_name", ep_name);
			request.getRequestDispatcher("manage/product-top.jsp").forward(request, response);//转发到manage/product-top.jsp页面
			/*	response.sendRedirect("manage/product-top.jsp");//重定向到manage/product-top.jsp页面
			 */		
		}
		if (action.equals("add")) {
			int row = biz.addProduct(p);
			if (row > 0) {		 //是否添加成功
				response.sendRedirect("manage/product.jsp?message="+row);//重定向到manage/product.jsp?message=页面
			} else {
				response.sendRedirect("manage/product-add.jsp?message="+row);//重定向到manage/product-add.jsp?message=页面
			}
		}
		if(action.equals("update")){
			int ep_id=Integer.parseInt(request.getParameter("ep_id"));
			p.setEp_Id(ep_id);
			int result=biz.updateProduct(p,type);
			if(result>0){		//是否修改成功
				response.sendRedirect("manage/product.jsp?message="+result);//重定向到manage/product.jsp?message=页面
			} else {
				response.sendRedirect("manage/product-add.jsp?message="+result);//重定向到manage/product-add.jsp?message=页面
			}
		}
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
}
