function loadTables() {
    $(document).ready(function(){
        var tables = null;
        var xhttp = new XMLHttpRequest();
        
        var newForm = "";
    	
        $("#showForm").append().html(newForm);
        
        var toAppend = "";

        toAppend='<div class="spinner-grow spinner-grow" role="status">\
	  				<span class="sr-only">Loading...</span>\
	  				</div>';
        $("#canChange").append().html(toAppend);

        xhttp.onreadystatechange = function () {
            if(xhttp.readyState == 4 && xhttp.status == 200) {

                $.ajax({
                    cache: false,
                    dataType: "json",
                    timeout: 3000,
                    error: function(){
                    	toAppend = "";
                        $("#canChange").append().html(toAppend);
                        
                    	toAppend = '<p><h4>Non è stato possibile ottenere i dati</h4></p>';
                        $("#canChange").append().html(toAppend);
                        },
                    success: function (tables) {
                        toAppend = "";

                        $("#canChange").append().html(toAppend);
                        var i = 0;
                        console.log(tables.length);
                        if(tables.length == 0){
                        	toAppend='<p><h4>Non sono presenti elementi.</h4></p>\
                        		<br><button type="button" class="btn btn-primary btn-lg" onclick="addTable()">Aggiungi</button>';
                            $("#canChange").append().html(toAppend);

                        	} else {
		                        while(i < tables.length ){
		
		                            var checkbox = '<form><div class="form-check">\
		                            				<input class="form-check-input" type="checkbox" value="' + tables[i].numeroTavolo + '" id="table_'+ tables[i].numeroTavolo +'">\
		                            				<label class="form-check-label" for="table_'+ tables[i].numeroTavolo +'">\
		                            				Tavolo '+ tables[i].numeroTavolo +'\
		                            				</label>\
		                            				</div>';
		                            
		                            toAppend+=checkbox;
		                            
		                            i++;
		                        }
		                        buttonAdd='<br><button type="button" class="btn btn-primary btn-lg" onclick="addTable()">Aggiungi</button> ';
		                        buttonDelete='<button type="submit" class="btn btn-primary btn-lg">Elimina</button></form>';
		
		                        toAppend+=buttonAdd;
		                        toAppend+=buttonDelete;
		                        
		                        $("#canChange").append().html(toAppend);
		                       }
                    },
                    url: 'jsonfiles/listaTavoli.json',
                });
            }
        }

        xhttp.open("GET", "ControllerTavolo?op=" + 1 , true);
        xhttp.send();
    });
}

function loadUsers() {
    $(document).ready(function(){
        var users = null;
        var xhttp = new XMLHttpRequest();
        
        var newForm = "";
    	
        $("#showForm").append().html(newForm);
        
        var toAppend = "";

        toAppend='<div class="spinner-grow spinner-grow" role="status">\
        	  		<span class="sr-only">Loading...</span>\
        	  		</div>';
        $("#canChange").append().html(toAppend);

        xhttp.onreadystatechange = function () {
            if(xhttp.readyState == 4 && xhttp.status == 200) {

                $.ajax({
                    cache: false,
                    dataType: "json",
                    timeout: 3000,
                    error: function(){
                    	toAppend = "";
                        $("#canChange").append().html(toAppend);
                        
                    	toAppend = '<p><h4>Non è stato possibile ottenere i dati</h4></p>';
                        $("#canChange").append().html(toAppend);
                        },
                    success: function (users) {
                        toAppend = "";
                        $("#canChange").append().html(toAppend);
                        var i = 0;
                        if(users.length<0){
                        	toAppend='<p><h4>Non sono presenti elementi.</h4></p>\
                        				<br><button type="button" class="btn btn-primary btn-lg" onclick="addUser()">Aggiungi</button>';
                        
                            $("#canChange").append().html(toAppend);

                        	} else {
                        
		                        while(i < users.length ){
		                            var checkbox = '<form><div class="form-check">\
		                            				<input class="form-check-input" type="checkbox" value="' + users[i].idUtente + '" id="user_'+ users[i].idUtente +'">\
		                            				<label class="form-check-label" for="user_'+ users[i].idUtente +'">\
		                            				'+ users[i].nomeUtente +' '+ users[i].cognomeUtente +' | Ruolo: '+ users[i].ruoloUtente +'\
		                            				</label>\
		                            				</div>';
		                            
		                            toAppend+=checkbox;
		                            
		                            i++;
		                        }
		                        buttonAdd='<br><button type="button" class="btn btn-primary btn-lg" onclick="addUser()">Aggiungi</button> ';
		                        buttonDelete='<button type="submit" class="btn btn-primary btn-lg">Elimina</button></form>';
		
		                        toAppend+=buttonAdd;
		                        toAppend+=buttonDelete;
		                        
		                        $("#canChange").append().html(toAppend);
                        }
                    },
                    url: 'jsonfiles/listaUtenti.json',
                });
            }
        }

        xhttp.open("GET", "ControllerUtente?op=" + 1, true);
        xhttp.send();
    });
}

function loadPlates() {
    $(document).ready(function(){
        var plates = null;
        var xhttp = new XMLHttpRequest();
        
        var newForm = "";
    	
        $("#showForm").append().html(newForm);
        
        var toAppend = "";

        toAppend='<div class="spinner-grow spinner-grow" role="status">\
        	  		<span class="sr-only">Loading...</span>\
        	  		</div>';
        $("#canChange").append().html(toAppend);

        xhttp.onreadystatechange = function () {
            if(xhttp.readyState == 4 && xhttp.status == 200) {

                $.ajax({
                    cache: false,
                    dataType: "json",
                    timeout: 3000,
                    error: function(){
                   
                    	
                    		toAppend = "";
                            $("#canChange").append().html(toAppend);
                            
                            toAppend = '<p><h4>Non è stato possibile ottenere i dati</h4></p>';
                            $("#canChange").append().html(toAppend);
                    	
 	
                    },
                    success: function (plates) {
                        toAppend = "";
                        $("#canChange").append().html(toAppend);
                        var i = 0;
                        if(plates.length<0){
                        	toAppend='<p><h4>Non sono presenti elementi.</h4></p>\
                        				<br><button type="button" class="btn btn-primary btn-lg" onclick="addPlate()">Aggiungi</button>';
                        
                            $("#canChange").append().html(toAppend);

                        	} else {
                        
		                        while(i < plates.length ){
		                            var checkbox = '<form><div class="form-check">\
		                            				<input class="form-check-input" type="checkbox" value="' + plates[i].idPiatto + '" id="plate_'+ plates[i].idPiatto +'">\
		                            				<label class="form-check-label" for="plate_'+ plates[i].idPiatto +'">\
		                            				'+ plates[i].nomePiatto +' | '+ plates[i].prezzoPiatto +'\
		                            				</label>\
		                            				</div>';
		                            
		                            toAppend+=checkbox;
		                            
		                            i++;
		                        }
		                        buttonAdd='<br><button type="button" class="btn btn-primary btn-lg" onclick="addPlate()">Aggiungi</button> ';
		                        buttonDelete='<button type="submit" class="btn btn-primary btn-lg">Elimina</button></form>';
		
		                        toAppend+=buttonAdd;
		                        toAppend+=buttonDelete;
		                        
		                        $("#canChange").append().html(toAppend);
                        }
                    },
                    url: 'jsonfiles/listaPiatti.json',
                });
            }
        }

        xhttp.open("GET", "ControllerPiatto?op=" + 1, true);
        xhttp.send();
    });}

function addUser() {
	var newForm = "";
	
    $("#showForm").append().html(newForm);

	newForm = `<form id="addUser" >
				<input type="hidden" name="op" value="2" /> 
				  <div class="form-group">
				    <label for="nomeUtente">Nome </label>
				    <input type="text" class="form-control" id="nomeUtente" name="nomeUtente" aria-describedby="nomeUtenteHelp" placeholder="Inserisci il nome">
				  </div>
				  <div class="form-group">
				    <label for="cognomeUtente">Cognome </label>
				    <input type="text" class="form-control" id="cognomeUtente" name="cognomeUtente" aria-describedby="cognomeUtenteHelp" placeholder="Inserisci il cognome">
				  </div>
				  <div class="form-group">
				    <label for="ruoloUtente">Ruolo </label>
				    <input type="text" class="form-control" name="ruoloUtente" aria-describedby="ruoloUtenteHelo" placeholder="Inserisci il ruolo">
				  </div>
				  <div class="form-group">
				    <label for="userId">Username </label>
				    <input type="text" class="form-control" name="userId" aria-describedby="userIdHelp" placeholder="Inserisci lo username">
				  </div>
				  <div class="form-group">
				    <label for="passwordUtente">Password </label>
				    <input type="text" class="form-control" name="passwordUtente" aria-describedby="passwordUtenteHelp" placeholder="Inserisci la password">
				  </div>
				  <div type="button" class="btn btn-primary btn-lg" id="btn-adduser" >Submit</div>
				</div>`;
	
    $("#showForm").append().html(newForm);
    
	$("#btn-adduser").click(function() {
		getAllFieldUser();
	});
}

function getAllFieldUser(){
	var userField = $("#addUser").serializeArray();
	 var urlString="";

	 
	 userField.forEach(element =>{
		 
		 urlString+="&"+element.name + "=" + element.value;
	 });
	 
	 var xhttp = new XMLHttpRequest();
	 
	 xhttp.open("GET", "ControllerUtente?op=" + 2 + urlString, true);
     xhttp.send();
	 
	 
	 
	 
}



function addTable() {
	var newForm = "";
	
    $("#showForm").append().html(newForm);

	newForm = `<form>
				  <div class="form-group">
				    <label for="numeroTavolo">Numero tavolo </label>
				    <input type="text" class="form-control" id="numeroTavolo" aria-describedby="numeroTavoloHelp" placeholder="Inserisci il numero del tavolo">
				  </div>
				  <button type="submit" class="btn btn-primary btn-lg">Submit</button>
				</form>`;
    $("#showForm").append().html(newForm);

}

function addPlate() {
	var newForm = "";
	
    $("#showForm").append().html(newForm);

	newForm = `<form>
				  <div class="form-group">
				    <label for="nomePiatto">Nome piatto</label>
				    <input type="text" class="form-control" id="nomePiatto" aria-describedby="nomePiattoHelp" placeholder="Inserisci il nome del piatto">
				  </div>
				  div class="form-group">
				    <label for="prezzoPiatto">Prezzo piatto </label>
				    <input type="text" class="form-control" id="prezzoPiatto" aria-describedby="prezzoPiattoHelp" placeholder="Inserisci il prezzo del piatto">
				  </div>
				  div class="form-group">
				    <label for="categoriaPiatto">Categoria </label>
				    <input type="text" class="form-control" id="categoriaPiatto" aria-describedby="categoriaPiattoHelp" placeholder="Inserisci la categoria del piatto">
				  </div>
				  div class="form-group">
				    <label for="listaIngredienti">Lista ingredienti </label>
				    <input type="text" class="form-control" id="listaIngredienti" aria-describedby="listaIngredientiHelp" placeholder="Inserisci la lista degli ingredienti">
				    <small id="listaIngredientiHelp" class="form-text text-muted">Inserisci gli ingredienti separati da una ','. Ad esempio: Alici, Capperi, Olive</small>
				  </div>
				  <button type="submit" class="btn btn-primary btn-lg">Submit</button>
				</form>`;
    $("#showForm").append().html(newForm);

}
