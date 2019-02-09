<!DOCTYPE html>
<html>
<head>
<%@include file="./parts/GetCookies.jsp"%>
<%@include file="./parts/head.jsp"%>

<title>Catalogo Prodotti</title>
</head>
<body>
<<<<<<< HEAD
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
=======

	<div class="container-fluid">
		<div class="row">
			<div class="col-9">
				<%@include file="./parts/navbar.jsp"%>
				<nav>
					<ul class="nav nav-pills nav-fill justify-content-center"
						id="pills-tab" role="tablist">

					</ul>
					<div class="tab-content" id="pills-tabContent"></div>
				</nav>
>>>>>>> 2bdd352e9ba11c526f2f4b2ede212c34305b168f
			</div>
		</div>
	</div>
<<<<<<< HEAD
=======
	<script src="./contents/scripts/utility.js"></script>

	<script src="./contents/scripts/classes/ClassTable.js"></script>
>>>>>>> 2bdd352e9ba11c526f2f4b2ede212c34305b168f
	<script src="./contents/scripts/ProdottiOrdinazione.js"></script>
</body>
</html>
