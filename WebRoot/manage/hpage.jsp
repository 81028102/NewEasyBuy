<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<div class="pager">
				<ul class="clearfix">
				<%	
					String name="";
					String ses=(String)session.getAttribute("hpage");
					if(ses.equals("huser"))
						name="getAllUser";
					if(ses.equals("hcomment"))
						name="commentServlet";
					if(ses.equals("hnews"))
						name="NewsServlet";
					if(ses.equals("horder"))
						name="orderServlet";
					if(ses.equals("hproduct"))
						name="product_servlet";
					request.setAttribute("name", name);
					int currpage=0;
					if(session.getAttribute("currpage")==null){
						currpage=1;
					}else{
					currpage=(Integer)session.getAttribute("currpage");
					}
					if(session.getAttribute("page")!=null){
					int count=(Integer)session.getAttribute("page");
					request.setAttribute("currpage", currpage);
					request.setAttribute("count", count);
				%>
				<c:if test="${count>0 }">
				<c:if test="${currpage ne 1 && count>3 }">
					<li><a href="../${name }?action=first&h=hou&currPage=1">首页</a></li>
				</c:if>
				<c:if test="${count<4 }">
					<%
						for(int i=1;i<=count;i++){
						request.setAttribute("i", i);
					%>
					<li <c:if test="${currpage eq i }">class="current"</c:if> ><a href="../${name }?action=first&h=hou&currPage=${i }">${i }</a></li>
					<%
						}
					 %>
				</c:if>
				<c:if test="${count>3 }">
				 <c:if test="${currpage eq 1 }">
				 	<li class="current" ><a href="../${name }?action=first&h=hou&currPage=${currpage }">${currpage }</a></li>
					<li><a href="../${name }?action=first&h=hou&currPage=${currpage+1 }">${currpage+1 }</a></li>
					<li><a href="../${name }?action=first&h=hou&currPage=${currpage+2 }">${currpage+2 }</a></li>
				 	<li>...</li>
				 </c:if>
				 <c:if test="${currpage eq 2 }">
					<li class="current" ><a href="../${name }?action=first&h=hou&currPage=${currpage }">${currpage }</a></li>
					<li><a href="../${name }?action=first&h=hou&currPage=${currpage+1 }">${currpage+1 }</a></li>
					<li><a href="../${name }?action=first&h=hou&currPage=${currpage+2 }">${currpage+2 }</a></li>
				 	<li>...</li>
				 </c:if>
				 <c:if test="${currpage > 2 && currpage < count-1 }">
				 	<li>...</li>
				 	<li><a href="../${name }?action=first&h=hou&currPage=${currpage-1 }">${currpage-1 }</a></li>
					<li class="current" ><a href="../${name }?action=first&h=hou&currPage=${currpage }">${currpage }</a></li>
					<li><a href="../${name }?action=first&h=hou&currPage=${currpage+1 }">${currpage+1 }</a></li>
					<c:if test="${currpage+1 ne count }">
				 	<li>...</li>
				 	</c:if>
				 </c:if>
				 <c:if test="${currpage eq count-1 }">
				 	<li>...</li>
				 	<li><a href="../${name }?action=first&h=hou&currPage=${currpage-1 }">${currpage-1 }</a></li>
					<li class="current" ><a href="../${name }?action=first&h=hou&currPage=${currpage }">${currpage }</a></li>
					<li><a href="../${name }?action=first&h=hou&currPage=${currpage+1 }">${currpage+1 }</a></li>
				 </c:if>
				  <c:if test="${currpage eq count }">
				 	<li>...</li>
				 	<li><a href="../${name }?action=first&h=hou&currPage=${currpage-2 }">${currpage-2 }</a></li>
					<li><a href="../${name }?action=first&h=hou&currPage=${currpage-1 }">${currpage-1 }</a></li>
					<li class="current" ><a href="../${name }?action=first&h=hou&currPage=${currpage }">${currpage }</a></li>
				 </c:if>
				 </c:if>
				 <c:if test="${currpage ne count && currpage+1 ne count }">
				 	<li><a href="../${name }?action=first&h=hou&currPage=${count }">尾页</a></li>
				 </c:if>
				 </c:if>
				 <%
					}
				 %>
				</ul>
			</div>
</body>
</html>