<%@include file="./GetCookies.jsp"%>
<nav class="nav border-bottom">
<<<<<<< HEAD
  <% if(role!=null && username!=null){ %>
  <a class="nav-link">Benvenuto <span class="username-menu"><%=username %> | Sei un <%=role %> </span> </a>
 <a class="nav-link" href="ServletLogout">Logout</a>
 <% }else{%>
 <a class="nav-link" href="/login.jsp">Accedi</a>
 <% }%>
 
=======
  <a class="nav-link">Benvenuto <span class="username-menu">
<% 	 
	if(request.getParameter("cookie") != null  && request.getParameter("cookierole") != null)
	{
		username = request.getParameter("cookie");
		role = request.getParameter("cookierole");
	}
%>
<%=username %> <%=role %>
 </span> </a>
  <a class="nav-link" href="ServletLogout">Logout</a>
>>>>>>> 2bdd352e9ba11c526f2f4b2ede212c34305b168f
</nav>