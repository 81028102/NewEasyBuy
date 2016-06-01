<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="pager">
	<ul class="clearfix">
		<%
			String name = "";
			String ses = (String) session.getAttribute("pages");
			if (ses.equals("index"))
				name = "indexServlet";
			if (ses.equals("comment"))
				name = "commentServlet";
			request.setAttribute("name", name);
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
				<li><a href="${name }?action=first&currPage=1">首页</a></li>
			</c:if>
			<c:if test="${count<4 }">
				<%
					for (int i = 1; i <= count; i++) {
									request.setAttribute("i", i);
				%>
				<li <c:if test="${currpage eq i }">class="current"</c:if>><a
					href="${name }?action=first&currPage=${i }">${i }</a></li>
				<%
					}
				%>
			</c:if>
			<c:if test="${count>3 }">
				<c:if test="${currpage eq 1 }">
					<li class="current"><a
						href="${name }?action=first&currPage=${currpage }">${currpage
							}</a></li>
					<li><a href="${name }?action=first&currPage=${currpage+1 }">${currpage+1
							}</a></li>
					<li><a href="${name }?action=first&currPage=${currpage+2 }">${currpage+2
							}</a></li>
					<li>...</li>
				</c:if>
				<c:if test="${currpage eq 2 }">
					<li class="current"><a
						href="${name }?action=first&currPage=${currpage }">${currpage
							}</a></li>
					<li><a href="${name }?action=first&currPage=${currpage+1 }">${currpage+1
							}</a></li>
					<li><a href="${name }?action=first&currPage=${currpage+2 }">${currpage+2
							}</a></li>
					<li>...</li>
				</c:if>
				<c:if test="${currpage > 2 && currpage < count-1 }">
					<li>...</li>
					<li><a href="${name }?action=first&currPage=${currpage-1 }">${currpage-1
							}</a></li>
					<li class="current"><a
						href="${name }?action=first&currPage=${currpage }">${currpage
							}</a></li>
					<li><a href="${name }?action=first&currPage=${currpage+1 }">${currpage+1
							}</a></li>
					<c:if test="${currpage+1 ne count }">
						<li>...</li>
					</c:if>
				</c:if>
				<c:if test="${currpage eq count-1 }">
					<li>...</li>
					<li><a href="${name }?action=first&currPage=${currpage-1 }">${currpage-1
							}</a></li>
					<li class="current"><a
						href="${name }?action=first&currPage=${currpage }">${currpage
							}</a></li>
					<li><a href="${name }?action=first&currPage=${currpage+1 }">${currpage+1
							}</a></li>
				</c:if>
				<c:if test="${currpage eq count }">
					<li>...</li>
					<li><a href="${name }?action=first&currPage=${currpage-2 }">${currpage-2
							}</a></li>
					<li><a href="${name }?action=first&currPage=${currpage-1 }">${currpage-1
							}</a></li>
					<li class="current"><a
						href="${name }?action=first&currPage=${currpage }">${currpage
							}</a></li>
				</c:if>
			</c:if>
			<c:if test="${currpage ne count && currpage+1 ne count }">
				<li><a href="${name }?action=first&currPage=${count }">尾页</a></li>
			</c:if>
		</c:if>
		<%
			}
		%>
	</ul>
</div>
