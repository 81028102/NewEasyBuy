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
import cn.jbit.biz.easybuy_newsBiz;
import cn.jbit.bizimpl.easybuy_newsBizImpl;
import cn.jbit.entity.easybuy_news;
import cn.jbit.entity.easybuy_news_column;
import cn.jbit.entity.easybuy_news_type;
import cn.jbit.util.JsonUtil;

/**
 * @author 任锯东
 */
@SuppressWarnings("serial")
public class NewsServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//获取系统当前时间 HH:mm:ss
		easybuy_newsBiz news = new easybuy_newsBizImpl(); 
		easybuy_news easy = new easybuy_news(); // 初始化实体类对象
		easybuy_news_type ent=new easybuy_news_type();// 初始化实体类对象
		easybuy_news_column enc = new easybuy_news_column(); // 初始化实体类对象
		// 查看新闻1
		if (action.equals("first")) {
			//接收cpage(当前页)
			String cpage = request.getParameter("cpage");
			if(cpage==null){
				cpage="1";
			}
			//接收模糊查询的条件
			String en_Title = request.getParameter("en_Title");//获取新闻标题
			if(en_Title==null){
				en_Title="";
			}
			//定义一个分页单位(pageSize)
			int pageSize=15;
			//查询总条数
			int counttt=news.getNewsCount(en_Title);
			//调用service  getList
			List<easybuy_news> lists = news.getEasybuy_news(Integer.parseInt(cpage),pageSize,en_Title);
			//计算一个总页数
			int totalPage=(counttt/pageSize)+(counttt%pageSize==0?0:1);
			//放到作用域
			session.setAttribute("lists", lists);
			request.setAttribute("cpage", cpage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("counttt", counttt);
			request.setAttribute("en_Title", en_Title);
			request.getRequestDispatcher("manage/news.jsp").forward(request, response);//转发到manage/news.jsp页面
		}
		// 查看新闻2
		if (action.equals("firsts")) {
			//接收cpage(当前页)
			String cpage = request.getParameter("cpage");
			if(cpage==null){
				cpage="1";
			}
			//接收模糊查询的条件
			String en_Title = request.getParameter("en_Title");//获取新闻标题
			if(en_Title==null){
				en_Title="";
			}
			//定义一个分页单位(pageSize)
			int pageSize=10;
			//查询总条数
			int countn=news.getAfterNewsCount(en_Title);
			//调用service  getList
			List<easybuy_news> lists1 = news.getAfterNew(Integer.parseInt(cpage),pageSize,en_Title);
			//计算一个总页数
			int totalPage=(countn/pageSize)+(countn%pageSize==0?0:1);
			//放到作用域
			session.setAttribute("lists1", lists1);
			request.setAttribute("cpage", cpage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("countn", countn);
			request.setAttribute("en_Title", en_Title);
			request.getRequestDispatcher("news-all.jsp").forward(request, response);//转发到news-all.jsp页面
		}	
		// 查看新闻3
		if (action.equals("firstss")) {
			//接收cpage(当前页)
			String cpage = request.getParameter("cpage");
			if(cpage==null){
				cpage="1";
			}
			//接收模糊查询的条件
			String en_Title = request.getParameter("en_Title");//获取新闻标题
			if(en_Title==null){
				en_Title="";
			}
			//定义一个分页单位(pageSize)
			int pageSize=15;
			//查询总条数
			int countne=news.getNewsCount(en_Title);
			//调用service  getList
			List<easybuy_news> lists2 = news.getEasybuy_Click(Integer.parseInt(cpage),pageSize,en_Title);
			//计算一个总页数
			int totalPage=(countne/pageSize)+(countne%pageSize==0?0:1);
			//放到作用域
			session.setAttribute("lists2", lists2);
			request.setAttribute("cpage", cpage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("countne", countne);
			request.setAttribute("en_Title", en_Title);
			request.getRequestDispatcher("manage/news-top.jsp").forward(request, response);//转发到news-all.jsp页面
		}
		//根据id查询单条新闻
		if (action.equals("two")) {
			String en_id = request.getParameter("en_id");//获取新闻编号
			request.setAttribute("en_id",en_id);
			request.getRequestDispatcher("manage/news-modify.jsp").forward(request, response);//重定向到manage/news-modify.jsp页面
		}
		if(action.equals("twoo")){
			easy = news.getNews(Integer.parseInt(request.getParameter("en_id")));
			String json = JsonUtil.toJsonFromObject(easy);
			response.getWriter().print(json);
		}
		// 根据id删除新闻
		if (action.equals("del")) {
			int d = news.delNew(request.getParameter("en_id"));//获取新闻编号
			// 判断
			if (d >0) {
				List<easybuy_news> lists = news.AllNews();
				session.setAttribute("lists", lists);
				request.getRequestDispatcher("NewsServlet?action=first&message="+d).forward(request, response);
			}else{
				response.sendRedirect("NewsServlet?action=first&message="+d);//重定向到NewsServlet?action=first
			}
		}
		if(action.equals("toAdd1")){
			List<easybuy_news_type> typeList = news.getNews_Type();
			String jsont = JsonUtil.toJson(typeList);
			response.getWriter().print(jsont);
		}
		if(action.equals("toAdd2")){
			List<easybuy_news_column> columnList = news.getNews_Column();
			String jsonc = JsonUtil.toJson(columnList);
			response.getWriter().print(jsonc);
		}
		//添加新闻
		if (action.equals("add")) {
			easy.setEn_Content(request.getParameter("content"));// 获取新闻内容
			easy.setEn_Title(request.getParameter("title"));//获取新闻标题
			easy.setEn_Create_time(sf.format(new Date()));//格式化系统当前时间为yyyy-MM-dd HH:mm:ss 
			easy.setEn_Click_Count(0);//初始点击率为0
			easy.setEnc_Id(Integer.parseInt(request.getParameter("enc_id")));//获取栏目编号
			easy.setEnt_Id(Integer.parseInt(request.getParameter("ent_id")));//获取分类编号
			int a = news.addNew(easy);
			// 判断
			if (a >0) {
				List<easybuy_news> lists = news.AllNews();
				session.setAttribute("lists", lists);
				request.getRequestDispatcher("NewsServlet?action=first&message="+a).forward(request, response);
			} else {
				response.sendRedirect("manage/news-add.jsp");
			}
		}
		//根据id修改新闻
		if (action.equals("update")) {
			easy = new easybuy_news();
			easy.setEn_Title(request.getParameter("title"));//获取新闻标题
			easy.setEn_Content(request.getParameter("content"));//获取新闻内容
			easy.setEn_Create_time(sf.format(new Date()));
			easy.setEnc_Id(Integer.parseInt(request.getParameter("enc_id")));
			easy.setEnt_Id(Integer.parseInt(request.getParameter("ent_id")));
			easy.setEn_Id(Integer.parseInt(request.getParameter("en_id")));//获取新闻编号
			int upd = news.updateNew(easy);
			if (upd > 0) {
				//request.getRequestDispatcher("NewsServlet?action=first&message="+upd).forward(request, response);
				response.getWriter().write("ok");
			}
		}
		//删除全部新闻
		if(action.equals("delNewsAll")){
			int i = news.delNewsAll();
			// 判断
			if (i >0) {
				List<easybuy_news> lists = news.AllNews();
				session.setAttribute("lists", lists);
				request.getRequestDispatcher("NewsServlet?action=first&message="+i).forward(request, response);
			}else{
				request.getRequestDispatcher("NewsServlet?action=first&message="+i).forward(request, response);
			}
		}
		//查看分类
		if(action.equals("types")){
			//接收cpage(当前页)
			String cpage = request.getParameter("cpage");
			if(cpage==null){
				cpage="1";
			}
			//接收模糊查询的条件
			String ent_Name = request.getParameter("ent_Name");//获取新闻标题
			if(ent_Name==null){
				ent_Name="";
			}
			//定义一个分页单位(pageSize)
			int pageSize=15;
			//查询总条数
			int countype=news.getTypesCount(ent_Name);
			List<easybuy_news_type> listype = news.getNews_Type(Integer.parseInt(cpage),pageSize,ent_Name);
			//计算一个总页数
			int totalPage=(countype/pageSize)+(countype%pageSize==0?0:1);
			//放到作用域
			session.setAttribute("listype", listype);
			request.setAttribute("cpage", cpage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("countype", countype);
			request.setAttribute("ent_Name", ent_Name);
			request.getRequestDispatcher("manage/newsTypeClass.jsp").forward(request, response);//转发到manage/newsTypeClass.jsp页面
		}
		//查看栏目
		if(action.equals("columns")){
			//接收cpage(当前页)
			String cpage = request.getParameter("cpage");
			if(cpage==null){
				cpage="1";
			}
			//接收模糊查询的条件
			String enc_Name = request.getParameter("enc_Name");//获取新闻标题
			if(enc_Name==null){
				enc_Name="";
			}
			//定义一个分页单位(pageSize)
			int pageSize=15;
			//查询总条数
			int countColumn=news.getColumnsCount(enc_Name);
			List<easybuy_news_column> listColumn = news.getNews_Column(Integer.parseInt(cpage),pageSize,enc_Name);
			//计算一个总页数
			int totalPage=(countColumn/pageSize)+(countColumn%pageSize==0?0:1);
			//放到作用域
			session.setAttribute("listColumn", listColumn);
			request.setAttribute("cpage", cpage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("countColumn", countColumn);
			request.setAttribute("enc_Name", enc_Name);
			request.getRequestDispatcher("manage/newsColumnClass.jsp").forward(request, response);//转发到manage/newsColumnClass.jsp页面
		}
		// 根据id删除分类
		if (action.equals("delNewsTypeById")) {
			int d = news.delNewsTypeById(request.getParameter("ent_id"));//获取分类编号
			// 判断
			if (d >0) {
				request.getRequestDispatcher("NewsServlet?action=types&message="+d).forward(request, response);
			}
		}
		//删除全部分类
		if(action.equals("delNewsTypeAll")){
			int i = news.delNewsTypeAll();
			// 判断
			if (i >0) {
				request.getRequestDispatcher("NewsServlet?action=types&message="+i).forward(request, response);
			}else{
				request.getRequestDispatcher("NewsServlet?action=types&message="+i).forward(request, response);
			}
		}
		// 根据id删除栏目
		if (action.equals("delNewsColumnById")) {
			int d = news.delNewsColumnById(request.getParameter("enc_id"));//获取栏目编号
			// 判断
			if (d >0) {
				request.getRequestDispatcher("NewsServlet?action=columns&message="+d).forward(request, response);
			}
		}
		//删除全部栏目
		if(action.equals("delNewsColumnAll")){
			int i = news.delNewsColumnAll();
			// 判断
			if (i >0) {
				request.getRequestDispatcher("NewsServlet?action=columns&message="+i).forward(request, response);
			}else{
				request.getRequestDispatcher("NewsServlet?action=columns&message="+i).forward(request, response);
			}
		}
		//添加分类
		if (action.equals("addNewType")) {
			String ent_name = request.getParameter("ent_name");//获取分类名称
			ent.setEnt_Name(ent_name);
			ent.setEnt_Create_time(sf.format(new Date()));//格式化系统当前时间为yyyy-MM-dd HH:mm:ss
			if (news.boolType(ent_name)==false) {//判断是否存在该分类信息
				int a = news.addNewType(ent);
				if (a > 0) {
					session.setAttribute("tmessage", "操作成功!");
					response.sendRedirect("manage/newsTypeClass-add.jsp");//重定向到manage/newsTypeClass-add.jsp页面
				} else {
					session.setAttribute("tsb", "操作失败!");
					response.sendRedirect("manage/newsTypeClass-add.jsp");//重定向到manage/newsTypeClass-add.jsp页面
				}
			}else{
				session.setAttribute("tbool", "已经存在该分类信息,不需要多次添加!");
				response.sendRedirect("manage/newsTypeClass-add.jsp");//重定向到manage/newsTypeClass-add.jsp页面
			}
		}
		//添加栏目
		if (action.equals("addNewColumn")) {
			String enc_name = request.getParameter("enc_name");//获取栏目名称
			enc.setEnc_Name(enc_name);
			enc.setEnc_Create_time(sf.format(new Date()));//格式化系统当前时间为yyyy-MM-dd HH:mm:ss
			if (news.boolColumn(enc_name)==false) {//判断是否存在该栏目信息
				int a = news.addNewColumn(enc);
				if(a>0){
					session.setAttribute("cmessage", "操作成功!");
					response.sendRedirect("manage/newsColumnClass-add.jsp");//重定向到manage/newsColumnClass-add.jsp页面
				} else {
					session.setAttribute("csb", "操作失败!");
					response.sendRedirect("manage/newsColumnClass-add.jsp");//重定向到manage/newsColumnClass-add.jsp页面
				}
			}else{
				session.setAttribute("cbool", "已经存在该栏目信息,不需要多次添加!");
				response.sendRedirect("manage/newsColumnClass-add.jsp");//重定向到manage/newsColumnClass-add.jsp页面
			}
		}
		//根据id回显分类信息
		if(action.equals("getNewsTypeById")){
			easybuy_news_type types = news.getNewsTypeById(Integer.parseInt(request.getParameter("ent_id")));//获取分类编号
			session.setAttribute("types", types);
			request.getRequestDispatcher("manage/newsTypeClass-modify.jsp").forward(request, response);//转发到manage/newsTypeClass-modify.jsp页面
		}
		//根据id回显栏目信息
		if(action.equals("getNewsColumnById")){
			easybuy_news_column columns = news.getNewsColumnById(Integer.parseInt(request.getParameter("enc_id")));//获取栏目编号
			session.setAttribute("columns", columns);
			request.getRequestDispatcher("manage/newsColumnClass-modify.jsp").forward(request, response);//转发到manage/newsColumnClass-modify.jsp页面
		}
		//更新分类信息
		if(action.equals("updateNewType")){
			String oldent_name=request.getParameter("oldent_name");//获取"老"分类名称
			ent.setEnt_Id(Integer.parseInt(request.getParameter("ent_id")));//获取分类编号
			String ent_name = request.getParameter("ent_name");//获取"新"分类名称
			ent.setEnt_Name(ent_name);//获取分类名称
			ent.setEnt_Create_time(sf.format(new Date()));//格式化系统当前时间为yyyy-MM-dd HH:mm:ss
			if (news.boolType(ent_name)==false||oldent_name.equals(ent_name)) {//判断是否存在该分类信息
				int i = news.updateNewType(ent);
				if(i>0){
					session.setAttribute("tmessage", "操作成功!");
					response.sendRedirect("manage/newsTypeClass-modify.jsp");//重定向到manage/newsTypeClass-modify.jsp页面
				} else {
					session.setAttribute("tsb", "操作失败!");
					response.sendRedirect("manage/newsTypeClass-modify.jsp");//重定向到manage/newsTypeClass-modify.jsp页面
				}
			}else{
				session.setAttribute("tbool", "已经存在该分类信息,不需要多次添加!");
				response.sendRedirect("manage/newsTypeClass-modify.jsp");//重定向到manage/newsTypeClass-modify.jsp页面
			}
		}
		//更新栏目信息
		if(action.equals("updateNewColumn")){
			String oldenc_name = request.getParameter("oldenc_name");//获取"老"栏目名称
			enc.setEnc_Id(Integer.parseInt(request.getParameter("enc_id")));//获取栏目编号
			String enc_name = request.getParameter("enc_name");
			enc.setEnc_Name(enc_name);//获取"新"栏目名称
			enc.setEnc_Create_time(sf.format(new Date()));//格式化系统当前时间为yyyy-MM-dd HH:mm:ss
			if (news.boolColumn(enc_name)==false||oldenc_name.equals(enc_name)) {//判断是否存在该栏目信息
				int i = news.updateNewColumn(enc);
				if(i>0){
					session.setAttribute("cmessage", "操作成功!");
					response.sendRedirect("manage/newsColumnClass-modify.jsp");//重定向到manage/newsColumnClass-modify.jsp页面
				} else {
					session.setAttribute("csb", "操作失败!");
					response.sendRedirect("manage/newsColumnClass-modify.jsp");//重定向到manage/newsColumnClass-modify.jsp页面
				}
			}else{
				session.setAttribute("cbool", "已经存在该栏目信息,不需要多次添加!");
				response.sendRedirect("manage/newsColumnClass-modify.jsp");//重定向到manage/newsColumnClass-modify.jsp页面
			}
		}
	}
}