<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@include file="./parts/head.jsp"%>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">


<title>Ordinazioni</title>
</head>
<style>
.container-fluid>.row {
	font-family: monospace;
}
</style>
<body>
	<div class="container-fluid">
		<%@include file="./parts/navbar.jsp"%>
		<%
			if (utente == null || role == null) {
				response.sendRedirect("./login.jsp");
				return;
			}
		%>

		<div class="row">
			<div class="col-12">
				<div class="h1 text-center title">
					Conto tavolo n. <span id="ntavolo"> 0</span>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-6 mx-auto">
				<div class="container-conto">
					<div class="row" id="">
						<div class="col-5 mx-auto mt-2">
							<img src="./contents/images/logo.svg">

						</div>
						<div class="col-8 mx-auto">
							<p class="h2 text-center">ClickEat</p>
							<p class="h4 text-center">Via Roma 17 - 84084 Fisciano</p>
							<p class="h4 text-center">P.Iva 12345678901</p>
						</div>
					</div>
					<div class="row" id="container-infoConto" style="margin: 0px;">
						<div class="col-12 ml-5">
							<p class="data ">
								Data: <span id="dataOrdine" class="data">17/02/2019</span>
							</p>
							<p class="tavolo ">
								Tavolo : <span id="tavoloOrdine" class="tavolo"> 0</span>
							</p>
							<p class="ordinazione ">
								Ordinazione n. : <span id="numeroOrdinazione"
									class="ordinazione"> 0</span>
							</p>
						</div>
					</div>
					<div class="row hidden-xs" id="ProductsOrdered" style="margin: 0px;">
						<div class="col-10 mx-auto">
							<table class="table" id="table-ordering" class="display">
								<thead>
									<tr>
										<th>Prodotto</th>
										<th>Quantit&agrave;</th>
										<th>Prezzo prodotto(&euro;)</th>
										<th>Sub-Totale(&euro;)</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>

						</div>
					</div>
					<div class="row row-totale mt-4">
						<div class="col-10 col-xs-12  mx-auto">
							<span class="">Totale </span> <span
								class="text-right float-right mr-5" id="totaleConto">0</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row mx-auto">
			<div class="col-auto mx-auto mt-5 mb-5">
				<button class="btn-lg btn-info" id="stampaConto">
					<i class="fas fa-print"> </i> Stampa
				</button>
			</div>
		</div>
	</div>
	<script src="./contents/scripts/ResocontoTavolo.js"></script>
</body>
</html>