<!DOCTYPE html>
<html>
<head>
<%@include file="./parts/GetCookies.jsp"%>
<%@include file="./parts/head.jsp"%>
<title>Catalogo Prodotti</title>
</head>
<body>
<%
			if(role != null && (role != "cameriere" || role!="Cameriere" || role!="Cassiere" || role!="cassiere" || role!="Amministratore" || role!="amministratore")){
				request.getRequestDispatcher("/login.jsp").forward(request, response);	   
		    }
		%>
	<div class="container-fluid">	
		<nav>
			<ul class="nav nav-pills nav-fill justify-content-center"
				id="pills-tab" role="tablist">
			</ul>
			<div class="tab-content" id="pills-tabContent">			
			</div>
		</nav>
	</div>
	<script src="./contents/scripts/ProdottiOrdinazione.js"></script>
</body>
</html>
