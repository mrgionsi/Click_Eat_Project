<!DOCTYPE html>
<html>
<head>
<%@include file="./parts/head.jsp"%>
<%@include file="./parts/GetCookies.jsp"%>

<title>Catalogo Prodotti</title>
</head>
<body>
<div class="container-fluid">
		<div class="row">
			<div class="col-9">
<%
				if (utente == null  || role==null) {
					response.sendRedirect("./login.jsp");
					return;
				}
		%>
					<nav>

					<ul class="nav nav-pills nav-fill justify-content-center"
						id="pills-tab" role="tablist">

					</ul>
					<div class="tab-content" id="pills-tabContent"></div>
				</nav>

			</div>
		</div>
	</div>
	<script src="./contents/scripts/utility.js"></script>
	<script src="./contents/scripts/classes/ClassTable.js"></script>
	<script src="./contents/scripts/ProdottiOrdinazione.js"></script>
</body>
</html>
