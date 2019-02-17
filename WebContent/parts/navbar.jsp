<%@include file="./GetCookies.jsp"%>
<%@include file="./head.jsp"%>
<div class="row">
	<nav class="nav border-bottom">
	
		<!-- 	if an administrator is logged the navbar should change aspect-->
		<% if(role!=null && role.equalsIgnoreCase("Amministratore")){ %>
			<img src="./contents/images/logo.svg">
		<a class="nav-link">Benvenuto <span class="username-menu"><%=username %>
				| Sei un <span id="ruoloUtente"> <%=role %>
			</span></span>
		</a>
		<!-- if the admin is on homepageamministratore -->
		<%if(request.getRequestURI().contains("homepageamministratore")){ %>
		<!-- show link to the table page -->
		<a class="nav-link" href="./homepage.jsp">Sala in real time</a>
		<% }%>
		<!-- if the admin is not on homepageamministratore -->
		<%if(!request.getRequestURI().contains("homepageamministratore")){ %>
		<!-- show link to the administration page -->
		<a class="nav-link" href="./homepageamministratore.jsp">Pagina
			amministrazione</a>
		<% }%>

		<% }else if(role!=null){%>
		<a class="nav-link">Benvenuto <span class="username-menu"><%=username %>
				| Sei un <span id="ruoloUtente"><%=role %> </span></span>
		</a>
		<% }%>
		<a class="nav-link" href="./login.jsp">Back</a> <a class="nav-link"
			href="ServletLogout">Logout</a>

	</nav>
</div>