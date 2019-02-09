<!doctype html>
<html lang="en">
<head>
<%@include file="./parts/head.jsp"%>

  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
<!-- MDBootstrap Datatables  -->
  <link href="./contents/library/MDB-Free_4.7.0/css/bootstrap.min.css" rel="stylesheet">
  <!-- Material Design Bootstrap -->
  <link href="./contents/library/MDB-Free_4.7.0/css/mdb.min.css" rel="stylesheet">
  <!-- Your custom styles (optional) -->
  <link href="./contents/library/MDB-Free_4.7.0/css/style.css" rel="stylesheet">
  <!-- MDBootstrap Datatables  -->
<link href="./contents/library/MDB-Free_4.7.0/css/addons/datatables.min.css" rel="stylesheet">



<title>
Amministrazione
</title>
</head>

<body>
	<div class="container-fluid">
			<%@include file="./parts/navbar.jsp"%>
		<%
			if (utente == null  || !role.equalsIgnoreCase("Amministratore")) {
				response.sendRedirect("./homepage.jsp");
				return;
			}
			
		
		%>
		<p class="text-center">
		<h3 class="welcome_amministrazione">Pagina amministrazione</h3>
		</p>
		<nav>
			<div class="nav nav-tabs" id="nav-tab" role="tablist">
				<a class="nav-item nav-link active" id="nav-tavoli-tab"
					data-toggle="tab" href="#nav-tavoli" role="tab"
					aria-controls="nav-tavoli" aria-selected="true">Tavoli</a> <a
					class="nav-item nav-link" id="nav-utenti-tab" data-toggle="tab"
					href="#nav-utenti" role="tab" aria-controls="nav-utenti"
					aria-selected="false">Utenti</a> <a class="nav-item nav-link"
					id="nav-piatti-tab" data-toggle="tab" href="#nav-piatti" role="tab"
					aria-controls="nav-piatti" aria-selected="false">Piatti</a>
			</div>
		</nav>
		<div class="tab-content" id="nav-tabContent">
			<div class="tab-pane fade show active" id="nav-tavoli"
				role="tabpanel" aria-labelledby="nav-home-tab">
				<div id="container-tabella" class="mx-auto">
					<table class="table table-striped table-bordered" id="table-tablelist" class="display">
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
					<table class="table table-striped table-bordered" id="table-tablelistuser">
						<thead>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<div class="tab-pane fade" id="nav-piatti" role="tabpanel"
				aria-labelledby="nav-piatti-tab">
				<div id="container-tabella-piatti" class="mx-auto">
					<table class="table table-striped table-bordered"
						id="table-tablelistpiatti">
						<thead>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>

	<div class="modal fade" id="ModalAddtable" tabindex="-1"
		role="dialog" aria-labelledby="ModalAddtable"
		aria-hidden="true">
		<%@include file="./parts/modalAddTable.jsp"%>
	</div>
<div class="modal fade" id="ConfirmModal" tabindex="-1" role="dialog" aria-labelledby="ConfirmModalLabel" aria-hidden="true">
		<%@include file="./parts/modalConfirmDelete.jsp"%>

</div>
	<div class="modal fade" id="modalAddUser" tabindex="-1"
		role="dialog" aria-labelledby="modalAddUser"
		aria-hidden="true">
		<%@include file="./parts/modalAddUser.jsp"%>
	</div>
</body>
<script type="text/javascript"
	src="./contents/library/MDB-Free_4.7.0/js/jquery-3.3.1.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript"
	src="./contents/library/MDB-Free_4.7.0/js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript"
	src="./contents/library/MDB-Free_4.7.0/js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript"src="./contents/library/MDB-Free_4.7.0/js/mdb.js"></script>

<script type="text/javascript"
	src="./contents/library/MDB-Free_4.7.0/js/addons/datatables.min.js"></script>
	<script src="./contents/scripts/utility.js"></script>
	
<script src="./contents/scripts/homepageamministratore.js"></script>

  <script src="./contents/scripts/modalFormTable.js"></script>
  <script src="./contents/scripts/modalFormUser.js"></script>

  <script src="./contents/scripts/modalConfirm.js"></script>
</html>
