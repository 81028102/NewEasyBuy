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
import cn.jbit.biz.easybuy_folderBiz;
import cn.jbit.bizimpl.easybuy_folderBizImpl;
import cn.jbit.entity.easybuy_folder;
import cn.jbit.util.FileUtil;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
public class documentServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String action = request.getParameter("action");
		easybuy_folderBiz ef=new easybuy_folderBizImpl();
		/*	if (session.getAttribute("login") == null) {
			response.sendRedirect("login.jsp");//重定向到login.jsp页面
		}*/
		if("first".equals(action)){ //文档列表
			//接收cpage(当前页)
			String cpage = request.getParameter("cpage");
			if(cpage==null){
				cpage="1";
			}
			//接收模糊查询的条件
			String fname = request.getParameter("fname");//获取文档名称
			if(fname==null){
				fname="";
			}
			//定义一个分页单位(pageSize)
			int pageSize=10;
			//分页查询文档信息
			//查询文档总条数
			int count=ef.getFolderCount(1,fname);
			//计算一个总页数
			int totalPage=(count/pageSize)+(count%pageSize==0?0:1);
			List<easybuy_folder> list = ef.querylist(Integer.parseInt(cpage),pageSize,fname);
			//放到作用域中
			session.setAttribute("list",list);
			request.setAttribute("cpage", cpage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("countd", count);
			request.setAttribute("fname", fname);
			request.getRequestDispatcher("manage/documentone.jsp").forward(request, response);//转发到manage/documentone.jsp页面
		}else if("deldocumentById".equals(action)){//删除文档
			int dele = ef.dele(Integer.parseInt(request.getParameter("fid")));//获取文档编号
			String newfname = request.getParameter("newfname");//获取文档新名称
			newfname=new String(newfname.getBytes("ISO-8859-1"),"UTF-8");//将商品名称转为UTF-8格式处理乱码
			FileUtil.deleDir("C:/work/"+newfname);
			System.out.println("C:/work/"+newfname);
			if(dele>0){
				request.getRequestDispatcher("documentServlet?action=queryrecycle&message="+dele).forward(request, response);//转发到documentServlet?action=first
			}else{
				request.getRequestDispatcher("documentServlet?action=queryrecycle&message="+dele).forward(request, response);//转发到documentServlet?action=first
			}
		}else if("selectdocumentById".equals(action)){ //根据Id查询文档信息
			easybuy_folder querybyid = ef.querybyid(Integer.parseInt(request.getParameter("fid")));//获取文档编号
			session.setAttribute("querybyid", querybyid);
			request.getRequestDispatcher("manage/document-modify.jsp").forward(request, response);//转发到manage/document-modify.jsp页面
		}else if("update".equals(action)){ //修改文档信息
			easybuy_folder folder=new easybuy_folder();
			String oldfname= request.getParameter("oldfname");//获取老文档名称
			String newfname = request.getParameter("newfname");//获取新文档名称
			String dirName = "C:/work/"+newfname; 
			//System.out.println("新名字"+folder.getFname()+"老名字"+folder.getOldname());
			FileUtil.updateDir("C:/work/"+request.getParameter("oldfname"), dirName);//获取老文档名称，新文档名称对换
			folder.setFname(newfname);//获取文档名称
			folder.setFremark(request.getParameter("fremark"));//获取文档备注
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取系统当前时间
			folder.setFuptime(sf.format(new Date()));//最后一次修改时间
			folder.setFid(Integer.parseInt(request.getParameter("fid")));//获取文档编号
			if (ef.boolfolder(newfname)==false||oldfname.equals(newfname)) {
				int i = ef.update(folder);
				if(i>0){
					request.getRequestDispatcher("documentServlet?action=first&message="+i).forward(request,response);//转发到documentServlet?action=first
				}else{
					response.sendRedirect("manage/document-modify.jsp");//重定向到manage/document-modify.jsp页面
				}
			}else{
				session.setAttribute("fbool", "已经存在该文档信息,不需要多次添加!");
				response.sendRedirect("manage/document-modify.jsp");//重定向到manage/document-modify.jsp页面
			}
		}else if("add".equals(action)){ //添加文档信息
			easybuy_folder folder=new easybuy_folder();
			String newfname=request.getParameter("newfname"); //获取文档名称
			String dirName = "C:/work/"+newfname;
			FileUtil.createDir(dirName);//创建文件夹
			folder.setFname(newfname);//获取文档名称
			folder.setFusername(request.getParameter("fusername"));//获取创建人
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取系统当前时间
			folder.setFtime(sf.format(new Date()));
			folder.setFremark(request.getParameter("fremark"));//获取备注
			folder.setFpath(dirName);//获取文件地址
			folder.setFuptime(sf.format(new Date()));//最后一次修改时间
			if (ef.boolfolder(newfname)==false) {
				int i = ef.save(folder);
				if(i>0){
					request.getRequestDispatcher("documentServlet?action=first&message="+i).forward(request,response);//转发到documentServlet?action=first
				}else{
					response.sendRedirect("manage/document-add.jsp");//重定向到manage/document-modify.jsp页面
				}
			}else{
				session.setAttribute("fobool", "已经存在该文档信息,不需要多次添加!");
				response.sendRedirect("manage/document-add.jsp");//重定向到manage/document-add.jsp页面
			}
		}else if("upd".equals(action)){ //删除文档信息
			easybuy_folder folder=new easybuy_folder();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取系统当前时间
			folder.setFtime(sf.format(new Date()));//删除时间
			folder.setFdele(2);//状态(是否删除或存在) 2代表删除
			folder.setFuptime(sf.format(new Date()));//删除改动时间
			folder.setFid(Integer.parseInt(request.getParameter("fid")));//获取文档编号
			int i = ef.upd(folder);
			if(i>0){
				request.getRequestDispatcher("documentServlet?action=first&message="+i).forward(request,response);//转发到documentServlet?action=first
			}else{
				request.getRequestDispatcher("documentServlet?action=first&message="+i).forward(request,response);//转发到documentServlet?action=first
			}
		}else if("queryrecycle".equals(action)){ //查询回收站信息
			//接收cpage(当前页)
			String cpage = request.getParameter("cpage");
			if(cpage==null){
				cpage="1";
			}
			//接收模糊查询的条件
			String fname = request.getParameter("fname");//获取文档名称
			if(fname==null){
				fname="";
			}
			//定义一个分页单位(pageSize)
			int pageSize=10;
			//分页查询文档回收站信息
			//查询回收站总条数
			int count=ef.getFolderCount(2,fname);
			//计算一个总页数
			int totalPage=(count/pageSize)+(count%pageSize==0?0:1);
			List<easybuy_folder> rlist = ef.queryrecycle(Integer.parseInt(cpage),pageSize,fname);
			//放到作用域中
			session.setAttribute("rlist",rlist);
			request.setAttribute("cpage", cpage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("countr", count);
			request.setAttribute("fname", fname);
			request.getRequestDispatcher("manage/documenttwo.jsp").forward(request, response);//转发到manage/documentone.jsp页面
		}else if("updr".equals(action)){ //还原文档信息
			easybuy_folder folder=new easybuy_folder();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取系统当前时间
			folder.setFtime(sf.format(new Date()));//还原时间
			folder.setFdele(1);//状态(是否删除或存在) 1代表还原
			folder.setFuptime(sf.format(new Date()));//还原(改动时间)
			folder.setFid(Integer.parseInt(request.getParameter("fid")));//获取文档编号
			int i = ef.upd(folder);
			if(i>0){
				request.getRequestDispatcher("documentServlet?action=queryrecycle&message="+i).forward(request,response);//转发到documentServlet?action=first
			}else{
				request.getRequestDispatcher("documentServlet?action=queryrecycle&message="+i).forward(request,response);//转发到documentServlet?action=first
			}
		}
	}
}