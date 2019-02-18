<!DOCTYPE html>
<html>
<head>
<%@include file="./parts/head.jsp"%>
<!-- </%@include file="./parts/GetCookies.jsp"%> -->

<title>Catalogo Prodotti</title>
</head>
<body>
	<div class="container-fluid">
		<%@include file="./parts/navbar.jsp"%>

		<div class="row">

			<div class="col-12 col-md-8">

				<%
				if (utente == null  || role==null) {
					response.sendRedirect("./login.jsp");
					return;
				}
		%>
				<nav>

					<ul class="nav nav-pills nav-fill justify-content-center mb-5"
						id="pills-tab" role="tablist">

					</ul>
					<div class="tab-content" id="pills-tabContent"></div>
				</nav>

			</div>
			<div class="col-12 col-md-4 col-lg-4" id="right-container">
			
				<div class="row">
					<br>
						<br>
					<div class="col-12 text-center">
				
						<span class="h3 text-center">Ordinazione Tavolo n. <span
							id="ntavolo"> </span></span>
					</div>

				</div>
				<br>
					<br>
				<div id="selected-items"></div>

			</div>
		</div>
		
		<div class="row mt-5">
			<div class="col-12 text-right">
				<button type="button" class="btn btn-lg btn-primary" id="sendOrder">
					Aggiungi ordinazione</button>
					<%if(role.equalsIgnoreCase("amministratore")){ %>
				<button type="button" class="btn btn-lg btn-success"
					id="resoContoTavolo" disabled>Tavolo vuoto</button>
			<%  } %>
			</div>
			
		</div>
	</div>
	<script src="./contents/scripts/utility.js"></script>
	<script src="./contents/scripts/classes/ClassElementToOrder.js"></script>
	<script src="./contents/scripts/classes/ClassTable.js"></script>
	<script src="./contents/scripts/classes/ClassTablewithCrudButtons.js"></script>
	<script src="./contents/scripts/ProdottiOrdinazione.js"></script>
</body>
</html>
