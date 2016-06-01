<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<div class="pager">
		<ul class="clearfix">
			<%
				int currpage = 0;
				if (session.getAttribute("currpage") == null) {
					currpage = 1;
				} else {
					currpage = (Integer) session.getAttribute("currpage");
				}
				if (session.getAttribute("page") != null) {
					int count = (Integer) session.getAttribute("page");
					request.setAttribute("currpage", currpage);
					request.setAttribute("count", count);
			%>
			<c:if test="${count>0 }">
				<c:if test="${currpage ne 1 && count>3 }">
					<li><a
						href="<%=request.getContextPath()%>/indexServlet?action=category&epc_id=${epc_id }&category=${cate }&currPage=1">首页</a>
					</li>
				</c:if>
				<c:if test="${count<4 }">
					<%
						for (int i = 1; i <= count; i++) {
										request.setAttribute("i", i);
					%>
					<li <c:if test="${currpage eq i }">class="current"</c:if>><a
						href="<%=request.getContextPath()%>/indexServlet?action=category&epc_id=${epc_id }&category=${cate }&currPage=${i }">${i
							}</a>
					</li>
					<%
						}
					%>
				</c:if>
				<c:if test="${count>3 }">
					<c:if test="${currpage eq 1 }">
						<li class="current"><a
							href="<%=request.getContextPath()%>/indexServlet?action=category&epc_id=${epc_id }&currPage=${currpage }">${currpage
								}</a>
						</li>
						<li><a
							href="<%=request.getContextPath()%>/indexServlet?action=category&epc_id=${epc_id }&category=${cate }&currPage=${currpage+1 }">${currpage+1
								}</a>
						</li>
						<li><a
							href="<%=request.getContextPath()%>/indexServlet?action=category&epc_id=${epc_id }&category=${cate }&currPage=${currpage+2 }">${currpage+2
								}</a>
						</li>
						<li>...</li>
					</c:if>
					<c:if test="${currpage eq 2 }">
						<li class="current"><a
							href="<%=request.getContextPath()%>/indexServlet?action=category&epc_id=${epc_id }&currPage=${currpage }">${currpage
								}</a>
						</li>
						<li><a
							href="<%=request.getContextPath()%>/indexServlet?action=category&epc_id=${epc_id }&category=${cate }&currPage=${currpage+1 }">${currpage+1
								}</a>
						</li>
						<li><a
							href="<%=request.getContextPath()%>/indexServlet?action=category&epc_id=${epc_id }&category=${cate }&currPage=${currpage+2 }">${currpage+2
								}</a>
						</li>
						<li>...</li>
					</c:if>
					<c:if test="${currpage > 2 && currpage < count-1 }">
						<li>...</li>
						<li><a
							href="<%=request.getContextPath()%>/indexServlet?action=category&epc_id=${epc_id }&category=${cate }&currPage=${currpage-1 }">${currpage-1
								}</a>
						</li>
						<li class="current"><a
							href="<%=request.getContextPath()%>/indexServlet?action=category&epc_id=${epc_id }&category=${cate }&currPage=${currpage }">${currpage
								}</a>
						</li>
						<li><a
							href="<%=request.getContextPath()%>/indexServlet?action=category&epc_id=${epc_id }&category=${cate }&currPage=${currpage+1 }">${currpage+1
								}</a>
						</li>
						<c:if test="${currpage+1 ne count }">
							<li>...</li>
						</c:if>
					</c:if>
					<c:if test="${currpage eq count-1 }">
						<li>...</li>
						<li><a
							href="<%=request.getContextPath()%>/indexServlet?action=category&epc_id=${epc_id }&category=${cate }&currPage=${currpage-1 }">${currpage-1
								}</a>
						</li>
						<li class="current"><a
							href="<%=request.getContextPath()%>/indexServlet?action=category&epc_id=${epc_id }&category=${cate }&currPage=${currpage }">${currpage
								}</a>
						</li>
						<li><a
							href="<%=request.getContextPath()%>/indexServlet?action=category&epc_id=${epc_id }&category=${cate }&currPage=${currpage+1 }">${currpage+1
								}</a>
						</li>
					</c:if>
					<c:if test="${currpage eq count }">
						<li>...</li>
						<li><a
							href="<%=request.getContextPath()%>/indexServlet?action=category&epc_id=${epc_id }&category=${cate }&currPage=${currpage-2 }">${currpage-2
								}</a>
						</li>
						<li><a
							href="<%=request.getContextPath()%>/indexServlet?action=category&epc_id=${epc_id }&category=${cate }&currPage=${currpage-1 }">${currpage-1
								}</a>
						</li>
						<li class="current"><a
							href="<%=request.getContextPath()%>/indexServlet?action=category&epc_id=${epc_id }&category=${cate }&currPage=${currpage }">${currpage
								}</a>
						</li>
					</c:if>
				</c:if>
				<c:if test="${currpage ne count && currpage+1 ne count }">
					<li><a
						href="<%=request.getContextPath()%>/indexServlet?action=category&epc_id=${epc_id }&category=${cate }&currPage=${count }">尾页</a>
					</li>
				</c:if>
			</c:if>
			<%
					}
				 %>
		</ul>
	</div>
</body>
</html>