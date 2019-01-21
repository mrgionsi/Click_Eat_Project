<!doctype html>
<html lang="en">
<head>
<%@include file="./parts/head.jsp"%>

<title>Accedi a ClickEat</title>
</head>
<body>
	<div class="container-fluid" style="padding: 0;">
		<div class="img-start">
			<div class="blacktrasparent">
				<div class="row">
					<div class="col-2 mx-auto h2 text-center header-login">
						<span class="header-login"> Login </span>
					</div>
				</div>

				<div class="row  align-items-center h-70">
					<div class="col-4 mx-auto login-form py-4">
						<div class="row">
							<div class="col-4 mx-auto text-center h4">
								<span>Accedi</span>
							</div>
						</div>
						<div class="row">
							<div class="col-8 mx-auto">
								<label for="username">Username</label> <input type="text"
									class="form-control" id="username"
									placeholder="Inserisci username" required>
							</div>
						</div>
						<div class="row">
							<div class="col-8 mx-auto">
								<label for="password">Password</label> <input type="password"
									class="form-control" id="password"
									placeholder="Inserisci password" required>
							</div>
						</div>
						<div class="row mx-auto">
							<div class="col-8 col-md-4 col-lg-4 mx-auto mt-4">
								<a href="shop.html">
									<button class="btn btn-primary btn-accedi">Accedi</button>
								</a>
							</div>
						</div>
						<div class="col-8 mx-auto access-problem mt-4">Problemi di
							accesso?</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
