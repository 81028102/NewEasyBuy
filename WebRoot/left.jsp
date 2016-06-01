<%@page import="cn.jbit.entity.*"%>
<%@page import="cn.jbit.biz.*"%>
<%@page import="cn.jbit.bizimpl.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/jquery-1.8.3.js"></script>

<%
	easybuy_product_categoryBiz epcb = new easybuy_product_categoryBizImpl();
	List<easybuy_product_category> two = epcb.getCategories(1);//获取所有商品分类
	request.setAttribute("two", two);
%>
<div class="lefter">
	<div class="box">
		<h2>${Classificationofgoods}</h2>
		<dl>		<!-- 双循环添加左导航信息 -->
			<c:forEach var="one" items="${headCategory}">
				<c:if test="${one.epc_Parent_id eq 0 }">
					<dt>${one.epc_Name }</dt>
				</c:if>
				<c:forEach var="two" items="${two}">
					<c:if test="${two.epc_Parent_id eq one.epc_Id }">
						<dd>
							<a href="<%=request.getContextPath()%>/indexServlet?action=category&category=two&epc_id=${two.epc_Id}&epc=${one.epc_Name }&parent_id=${one.epc_Id }">${two.epc_Name}
							</a>
						</dd>
					</c:if>
				</c:forEach>
			</c:forEach>
		</dl>
	</div>
	<div class="spacer"></div>
	<div class="last-view">
		<h2>${Recentbrowse}</h2>
		<dl class="clearfix">
			<c:if test="${not empty looklist }">
				<c:forEach var="cook" items="${looklist }">
					<dt>
						<img src="${cook.ep_File_name }" width="55" height="55" />
					</dt>
					<dd style="padding-top: 10px;">
						<a
							href="<%=request.getContextPath()%>/productServlet?action=look&ep_id=${cook.ep_Id }&ep_name=${cook.ep_Name }"
							target="_self">${cook.ep_Name }</a><a href="<%=request.getContextPath()%>/product-view.jsp"></a>
					<br/>
					<span style="color: red">￥${cook.ep_Price }</span>
					<br/>
						${cook.ep_Address }
					</dd>
					<p style="border-bottom:dashed 1px #999"></p>
				</c:forEach>
			</c:if>
		</dl>
	</div>
</div>	
