<%@include file="./GetCookies.jsp"%>
<nav class="nav border-bottom">

  <% if(role!=null && role.equalsIgnoreCase("Amministratore")){ %>
	  <a class="nav-link">Benvenuto <span class="username-menu"><%=username %> | Sei un <%=role %> </span> </a>
	   <%if(request.getRequestURI().contains("homepageamministratore")){ %>
	   <a class="nav-link" href="./homepage.jsp">Sala in real time</a>
	   <% }%>
	   	   <%if(!request.getRequestURI().contains("homepageamministratore")){ %>
	   
	   <a class="nav-link" href="./homepageamministratore.jsp">Pagina amministrazione</a>
	   <% }%>
	 <a class="nav-link" href="ServletLogout">Logout</a>
 
 <% }else if(role!=null){%>
  <a class="nav-link">Benvenuto <span class="username-menu"><%=username %> | Sei un <%=role %> </span> </a>
 <a class="nav-link" href="ServletLogout">Logout</a>
 <% }%>
</nav>