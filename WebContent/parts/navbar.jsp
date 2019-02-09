
 


<nav class="nav border-bottom">
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
</nav>