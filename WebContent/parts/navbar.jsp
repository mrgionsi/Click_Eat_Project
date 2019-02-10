<%@include file="./GetCookies.jsp"%>
<nav class="nav border-bottom">
<!-- 	if an administrator is logged the navbar should change aspect-->
  <% if(role!=null && role.equalsIgnoreCase("Amministratore")){ %>
	  <a class="nav-link">Benvenuto <span class="username-menu"><%=username %> | Sei un <%=role %> </span> </a>
	  <!-- if the admin is on homepageamministratore -->
	   <%if(request.getRequestURI().contains("homepageamministratore")){ %>
	   <!-- show link to the table page -->
	   <a class="nav-link" href="./homepage.jsp">Sala in real time</a>
	   <% }%>
	   <!-- if the admin is not on homepageamministratore -->
	   	   <%if(!request.getRequestURI().contains("homepageamministratore")){ %>
	   	   <!-- show link to the administration page -->
	   <a class="nav-link" href="./homepageamministratore.jsp">Pagina amministrazione</a>
	   <% }%>
 
 <% }else if(role!=null){%>
  <a class="nav-link">Benvenuto <span class="username-menu"><%=username %> | Sei un <%=role %> </span> </a>
 <% }%>
  <a class="nav-link" href="ServletLogout">Logout</a>
 
</nav> 