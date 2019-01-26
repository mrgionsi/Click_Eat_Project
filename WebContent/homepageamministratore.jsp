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
		<nav>
			<div class="nav nav-tabs" id="nav-tab" role="tablist">
				<a class="nav-item nav-link active" id="nav-tavoli-tab" data-toggle="tab" href="#nav-tavoli" role="tab"
					aria-controls="nav-tavoli" aria-selected="true">Tavoli</a>
			   <a class="nav-item nav-link" id="nav-utenti-tab" data-toggle="tab" href="#nav-utenti" role="tab" aria-controls="nav-utenti"
					aria-selected="false">Utenti</a> 
			   <a class="nav-item nav-link" id="nav-piatti-tab" data-toggle="tab" href="#nav-piatti" role="tab"
					aria-controls="nav-piatti" aria-selected="false">Piatti</a>
			</div>
		</nav>
		<div class="tab-content" id="nav-tabContent">
			<div class="tab-pane fade show active" id="nav-tavoli" role="tabpanel"
				aria-labelledby="nav-home-tab">
				<div id="container-tabella" class="mx-auto">
					<table class="table table-hover" id="table-tablelist">
						<thead>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<div class="tab-pane fade" id="nav-utenti" role="tabpanel"
				aria-labelledby="nav-utenti-tab">
				<div id="container-tabella-utenti" class="mx-auto">
					<table class="table table-hover table-bordered" id="table-tablelistuser">
						<thead>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<div class="tab-pane fade" id="nav-piatti" role="tabpanel"
				aria-labelledby="nav-piatti-tab">...</div>
		</div>

	</div>

</body>
</html>
