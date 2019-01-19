function loadTables() {
    $(document).ready(function(){
        var tables = null;
        var xhttp = new XMLHttpRequest();
        
        var newForm = "";
    	
        $("#showForm").append().html(newForm);
        
        var toAppend = "";

        toAppend='<div class="spinner-border text-primary" role="status">\
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

        toAppend='<div class="spinner-border text-primary" role="status">\
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

        toAppend='<div class="spinner-border text-primary" role="status">\
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
		                            				'+ plates[i].nomePiatto +' | '+ plates[i].prezzoPiatto + '&euro;' + '\
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
				    <input type="text" class="form-control" name="nomeUtente" aria-describedby="nomeUtenteHelp" placeholder="Inserisci il nome">
				  </div>
				  <div class="form-group">
				    <label for="cognomeUtente">Cognome </label>
				    <input type="text" class="form-control" name="cognomeUtente" aria-describedby="cognomeUtenteHelp" placeholder="Inserisci il cognome">
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
				  <button type="button" class="btn btn-primary btn-lg" id="btn-adduser" >Submit</button>
				</form>`;
	
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
	 xhttp.onreadystatechange = function () {
         if(xhttp.readyState == 4 && xhttp.status == 200) {

             $.ajax({
                 cache: false,
                 timeout: 3000,
                 success: function (data) {
                     	console.log(data);
                	 	var success = document.createElement("span");
                	 	$(success).text("Utente aggiunto con successo!")
                	 	$("#showForm").empty();
                	    $("#showForm").append(success);

                 },
                
             });
         }
     }
	 xhttp.open("GET", "ControllerUtente?op=" + 2 + urlString, true);
     xhttp.send();
	  
}



function addTable() {
	var newForm = document.createElement("form");
	$(newForm).attr("id", "addTable");
	
	var newDiv = document.createElement("div");
	$(newDiv).addClass("form-group");

	var newLabel = document.createElement("label");
	$(newLabel).attr("for", "numeroTavolo");
	$(newLabel).text("Numero tavolo");
	
	var newInput = document.createElement("input");
	$(newInput).addClass("form-control");
	$(newInput).attr("type", "text");
	$(newInput).attr("name", "numeroTavolo");
	$(newInput).attr("aria-describedby", "numeroTavoloHelp");
	$(newInput).attr("placeholder", "Inserisci il numero del tavolo");
	
	var newButton = document.createElement("button");
	$(newButton).attr("type", "button");
	$(newButton).attr("id", "btn-addtable");
	$(newButton).addClass("btn btn-primary btn-lg");
	$(newButton).text("Invia");

	$(newDiv).append(newLabel);
	$(newDiv).append(newInput);

	$(newForm).append(newDiv);
	$(newForm).append(newButton);

    $("#showForm").append(newForm);
    
    $("#btn-addtable").click(function() {
		getAllFieldTable();
	});

}

function getAllFieldTable(){
	
	var userField = $("#addTable").serializeArray();
	 var urlString="";

	 
	 userField.forEach(element =>{
		 
		 urlString+="&"+element.name + "=" + element.value;
		 console.log(urlString);
	 });
	 
	 var xhttp = new XMLHttpRequest();
	 xhttp.onreadystatechange = function () {
         if(xhttp.readyState == 4 && xhttp.status == 200) {

             $.ajax({
                 cache: false,
                 timeout: 3000,
                 success: function (data) {
                     	console.log(data);
                	 	var success = document.createElement("span");
                	 	$(success).text("Tavolo aggiunto con successo!")
                	 	$("#showForm").empty();
                	    $("#showForm").append(success);

                 },
                
             });
         }
     }
	 xhttp.open("GET", "ControllerTavolo?op=" + 2 + urlString, true);
     xhttp.send();
	  
}

function addPlate() {
	var newForm = "";
	
	newForm = document.createElement("form");
	$(newForm).attr("id", "addPlate");
//	--------------------------------------------------------------------

	var newDiv = document.createElement("div");
	$(newDiv).addClass("form-group");
	
	var newLabel = document.createElement("label");
	$(newLabel).attr("for", "nomePiatto");
	$(newLabel).text("Nome piatto");

	var newInput = document.createElement("input");
	$(newInput).attr("type", "text");
	$(newInput).attr("id", "nomePiatto");
	$(newInput).attr("aria-describedby", "nomePiattoHelp");
	$(newInput).attr("placeholder", "Inserisci il nome del piatto");
	$(newInput).addClass("form-control");
	
	$(newDiv).append(newLabel);
	$(newDiv).append(newInput);
	
	$(newForm).append(newDiv);

//	--------------------------------------------------------------------
	var newDiv1 = document.createElement("div");
	$(newDiv1).addClass("form-group");
	
	var newLabel1 = document.createElement("label");
	$(newLabel1).attr("for", "prezzoPiatto");
	$(newLabel1).text("Prezzo piatto");

	var newInput1 = document.createElement("input");
	$(newInput1).attr("type", "number");
	$(newInput1).attr("id", "prezzoPiatto");
	$(newInput1).attr("aria-describedby", "prezzoPiattoHelp");
	$(newInput1).attr("placeholder", "Inserisci il prezzo del piatto");
	$(newInput1).addClass("form-control");
	
	$(newDiv1).append(newLabel1);
	$(newDiv1).append(newInput1);
	
	$(newForm).append(newDiv1);
//	--------------------------------------------------------------------

	var newDiv2 = document.createElement("div");
	$(newDiv2).addClass("form-group");
	
	var newLabel2 = document.createElement("label");
	$(newLabel2).attr("for", "categoriaPiatto");
	$(newLabel2).text("Categoria piatto");

	var newInput2 = document.createElement("input");
	$(newInput2).attr("type", "text");
	$(newInput2).attr("id", "categoriaPiatto");
	$(newInput2).attr("aria-describedby", "categoriaPiattoHelp");
	$(newInput2).attr("placeholder", "Inserisci la categoria del piatto");
	$(newInput2).addClass("form-control");
	
	$(newDiv2).append(newLabel2);
	$(newDiv2).append(newInput2);
	
	$(newForm).append(newDiv2);
//	--------------------------------------------------------------------

	var newDiv3 = document.createElement("div");
	$(newDiv3).addClass("form-group");
	
	var newLabel3 = document.createElement("label");
	$(newLabel3).attr("for", "listaIngredienti");
	$(newLabel3).text("Lista ingredienti");

	var newInput3 = document.createElement("input");
	$(newInput3).attr("type", "text");
	$(newInput3).attr("id", "listaIngredienti");
	$(newInput3).attr("aria-describedby", "listaIngredientiHelp");
	$(newInput3).attr("placeholder", "Inserisci la lista degli ingredienti del piatto");	
	$(newInput3).addClass("form-control");
	
	var newSmall3 = document.createElement("small");
	$(newSmall3).addClass("form-text text-muted");
	$(newSmall3).attr("id", "listaIngredientiHelp");
	$(newSmall3).text("Inserisci gli ingredienti separati da una virgola. Ad esempio: Alici, Capperi, Olive.");
	

	$(newDiv3).append(newLabel3);
	$(newDiv3).append(newInput3);
	$(newDiv3).append(newSmall3);

	$(newForm).append(newDiv3);
	
	
	var newButton = document.createElement("button");
	$(newButton).addClass("btn btn-primary btn-lg");	
	$(newButton).attr("type", "button");
	$(newButton).attr("id", "btn-addplate");
	$(newButton).text("Invia");	


	$(newForm).append(newButton);

	$("#showForm").append(newForm);
    
    $("#btn-addplate").click(function() {
		getAllFieldPlate();
	});

}

function getAllFieldPlate() {
	
	var userField = $("#addPlate").serializeArray();
	 var urlString="";

	 
	 userField.forEach(element =>{
		 
		 urlString+="&"+element.name + "=" + element.value;
		 console.log(urlString);
	 });
	 
	 var xhttp = new XMLHttpRequest();
	 xhttp.onreadystatechange = function () {
        if(xhttp.readyState == 4 && xhttp.status == 200) {

            $.ajax({
                cache: false,
                timeout: 3000,
                success: function (data) {
                    	console.log(data);
               	 	var success = document.createElement("span");
               	 	$(success).text("Piatto aggiunto con successo!")
               	 	$("#showForm").empty();
               	    $("#showForm").append(success);

                },
               
            });
        }
    }
	 xhttp.open("GET", "ControllerPiatto?op=" + 2 + urlString, true);
    xhttp.send();
}
