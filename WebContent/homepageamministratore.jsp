<!doctype html>
<html lang="en">
<head>
<%@include file="./parts/head.jsp"%>

<title>Benvenuto _amministratore_</title>
</head>

<body>

	<div class="container-fluid">
		<%@include file="./parts/navbar.jsp"%>
		<p class="text-center">
		<h3 class="welcome_amministrazione">Pagina amministrazione</h3>
		</p>
		<button type="button" class="btn btn-primary btn-lg" id="btn-table">Tavoli</button>
		<button type="button" class="btn btn-primary btn-lg"
			onclick="loadUsers()">Utenti</button>
		<button type="button" class="btn btn-primary btn-lg"
			onclick="loadPlates()">Piatti</button>
		<br> <br>
		<div id="container-tabella">
			<table class="table table-hover" id="table-tablelist">
				<thead>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>
