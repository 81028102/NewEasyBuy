<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<div id="menu-mng" class="lefter">
		<div class="box">
			<dl>
				<dt>用户信息</dt>
				<dd>
					<em><a href="<%=request.getContextPath()%>/findQuestion1Servlet"><img src="<%=request.getContextPath() %>/images/iadd.gif"/> 新增</a>
					</em><a href="<%=request.getContextPath()%>/getAllUser?action=first">用户管理</a>
				</dd>
				<c:if test="${status eq 2 }">
				<dd>
					<em><a href="<%=request.getContextPath() %>/manage/userStatusClass-add.jsp"><img src="<%=request.getContextPath() %>/images/iadd.gif"/> 新增</a>
					</em> <a href="<%=request.getContextPath()%>/ManageuserByAdmin_servlet?action=sstatus">状态管理</a>
				</dd>
				</c:if>
				<c:if test="${status eq 2 }">
				<dt>商品信息</dt>
				<dd>
					<em><a href="<%=request.getContextPath()%>/product_servlet?action=add"><img src="<%=request.getContextPath() %>/images/iadd.gif"/> 新增</a>
					</em><a href="<%=request.getContextPath()%>/product_servlet?action=first">商品管理</a>
				</dd>
				<dd>
					<em><a href="<%=request.getContextPath()%>/CategoryServlet?action=add"><img src="<%=request.getContextPath() %>/images/iadd.gif"/> 新增</a>
					</em><a href="<%=request.getContextPath()%>/CategoryServlet?action=all">分类管理</a>
				</dd>
				</c:if>
				<dt>订单信息</dt>
				<dd>
					<a href="<%=request.getContextPath()%>/orderServlet?action=first">订单管理</a>
				</dd>
				<c:if test="${status eq 2 }">
				<dt>留言信息</dt>
				<dd>
					<a href="<%=request.getContextPath()%>/commentServlet?action=first&h=hou">留言管理</a>
				</dd>
				<dd>
					<a href="<%=request.getContextPath()%>/productServlet?action=looks">评价管理</a>
				</dd>
				<dt>新闻信息</dt>
				<dd>
					<em><a href="<%=request.getContextPath()%>/manage/news-add.jsp"><img src="<%=request.getContextPath() %>/images/iadd.gif"/> 新增</a>
					</em> <a href="<%=request.getContextPath()%>/NewsServlet?action=first">新闻管理</a>
				</dd>
				<dd>
					<em><a href="<%=request.getContextPath() %>/manage/newsTypeClass-add.jsp"><img src="<%=request.getContextPath() %>/images/iadd.gif"/> 新增</a>
					</em> <a href="<%=request.getContextPath()%>/NewsServlet?action=types">分类管理</a>
				</dd>
					<dd>
					<em><a href="<%=request.getContextPath() %>/manage/newsColumnClass-add.jsp"><img src="<%=request.getContextPath() %>/images/iadd.gif"/> 新增</a>
					</em> <a href="<%=request.getContextPath()%>/NewsServlet?action=columns">栏目管理</a>
				</dd>
				 <dt>文档信息</dt>
				 <dd>
                     <em><a href="<%=request.getContextPath()%>/manage/document-add.jsp"/><img src="<%=request.getContextPath() %>/images/iadd.gif"/> 新增</a>
                    </em><a href="<%=request.getContextPath()%>/documentServlet?action=first">文档管理</a>
                </dd>
                <dd>
					<a href="<%=request.getContextPath()%>/documentServlet?action=queryrecycle">文档回收站</a>
				</dd>
				 <dt>排行信息</dt>
				 <dd>
				    <a href="<%=request.getContextPath()%>/getAllUser?action=firsts">消费排行</a>
				 </dd>
				 <dd>
					<a href="<%=request.getContextPath()%>/CategoryServlet?action=alls">分类热率</a>
				</dd>
				 <dd>
					<a href="<%=request.getContextPath()%>/product_servlet?action=firsts">销量排行</a>
				</dd>
				 <dd>
					<a href="<%=request.getContextPath()%>/NewsServlet?action=firstss">新闻热率</a>
				</dd>
				</c:if>
			</dl>
	</div>
</div>