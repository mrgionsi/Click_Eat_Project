<!doctype html>
<html lang="en">
    <head>
      	<%@include file="./parts/head.jsp" %>

        <title>Benvenuto _amministratore_</title>
    </head>
    </<body>

        <div class="container">
        <%@include file="./parts/navbar.jsp" %>
	        <p class="text-center">
				<h3 class="welcome_amministrazione">Pagina amministrazione</h3>
			</p>
			<button type="button" class="btn btn-primary" onclick="loadTables()">Tavoli</button>
            <button type="button" class="btn btn-primary" onclick="loadUsers()">Utenti</button>
            <button type="button" class="btn btn-primary" onclick="loadPlates()">Piatti</button>
            <br>
            <br>
			<div class = "row">
				<div class="col">

		            <div class="container" id="canChange">
		            </div>
		
		                <!-- Inserire lista di checkbox generata in automatico con chiamata AJAX (per ogni bottone lista diversa di elementi) -->
		
		
		        </div>
		        <div class="col">
			        <div class="container" id="showForm">
			        	
			        </div>
		        </div>
	        </div>
        </div>
    </body>
</html>
