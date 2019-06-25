<!doctype html>
<html lang="en">
<head>
<%@include file="./parts/head.jsp"%>
<%@include file="./parts/GetCookies.jsp"%>

<title>Accedi a ClickEat</title>
</head>
<body>
<%
		
		if (utente != null && role.equalsIgnoreCase("Amministratore")) {
			response.sendRedirect("./homepageamministratore.jsp");
			return;
		}else if (utente != null){
			response.sendRedirect("./homepage.jsp");
			return;
		}
		
		
		%>
	<div class="container-fluid" style="padding: 0;">
		<div class="img-start">
			<div class="blacktrasparent">
				<div class="row">
					<div class="col-2 mx-auto h2 text-center header-login">
						<span class="header-login"> Login </span>
					</div>
				</div>

				<div class="row  align-items-center mt-5">
					<div
						class="col-lg-4 col-sm-6 col-xs-12 mx-auto login-form py-4 mt-5">
						<div class="row">
							<div class="col-4 mx-auto text-center h4">
								<span>Accedi a ClickEat</span>
							</div>
						</div>
						<div class="row">
							<div class="col-8 mx-auto">
								<label for="username">Username</label> <input type="text"
									class="form-control" id="username" name="idLogin"
									placeholder="Inserisci username" required>
									
							</div>
						</div>
						<div class="row">
							<div class="col-8 mx-auto">
								<label for="password">Password</label> <input type="password"
									class="form-control" id="password" name="passwordUtente"
									placeholder="Inserisci password" required>
							</div>
						</div>
						<div class="row mx-auto">
							<div class="col-8 col-md-4 col-lg-4 mx-auto mt-4">
								<button type="button" class="btn btn-primary btn-accedi" id="btn-login">Accedi</button>
							</div>
						</div>
						<div class="col-12 text-center access-problem mt-4">Problemi
							di accesso?</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script src="./contents/scripts/login.js"></script>

</html>


