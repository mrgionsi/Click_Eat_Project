function wait(ms)
{
var d = new Date();
var d2 = null;
do { d2 = new Date(); }
while(d2-d < ms);
}

function loadTables() {
    $(document).ready(function(){
        var tables = null;
        var xhttp = new XMLHttpRequest();
        
    	$("#canChange").empty();
        $("#showForm").empty();
        

        var newSpinner = document.createElement("div");
        $(newSpinner).addClass("spinner-border text-primary");
        $(newSpinner).attr("role", "status");
        
        var newSpan = document.createElement("span");
        $(newSpan).addClass("sr-only");
        $(newSpan).text("Loading...")
        
        $(newSpinner).append(newSpan);
        $("#canChange").append(newSpinner);

        xhttp.onreadystatechange = function () {
            if(xhttp.readyState == 4 && xhttp.status == 200) {

                $.ajax({
                    cache: false,
                    dataType: "json",
                    timeout: 3000,
                    error: function(){
                    	$("#canChange").empty();
                        
                        var newPar = document.createElement("p");
                        $(newPar).addClass("h4");
                        $(newPar).text("Non è stato possibile ottenere i dati")
                        
                        
                        $("#canChange").append(newPar);
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
		
		                            var checkbox = '<form><div class="form-check" id="tableList">\
		                            				<input class="form-check-input" type="radio" name="tableList" value="' + tables[i].numeroTavolo + '" id="table_'+ tables[i].numeroTavolo +'">\
		                            				<label class="form-check-label" for="table_'+ tables[i].numeroTavolo +'">\
		                            				Tavolo '+ tables[i].numeroTavolo +'\
		                            				</label>\
		                            				</div>';
		                            
		                            toAppend+=checkbox;
		                            
		                            i++;
		                        }
		                        buttonAdd='<br><button type="button" class="btn btn-primary btn-lg" id="btn-addtable">Aggiungi</button> ';
		                        buttonDelete='<button type="button" class="btn btn-primary btn-lg" id="btn-deletetables">Elimina</button></form>';
		
		                        toAppend+=buttonAdd;
		                        toAppend+=buttonDelete;
		                        
		                        $("#canChange").append().html(toAppend);
		                        
		                        $("#btn-deletetables").click(function() {
		                    		deleteTables();
		                    	});
		                        $("#btn-addtable").click(function() {
		                    		addTable();
		                    	});
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
        
        $("#canChange").empty();
        $("#showForm").empty();
        

        var newSpinner = document.createElement("div");
        $(newSpinner).addClass("spinner-border text-primary");
        $(newSpinner).attr("role", "status");
        
        var newSpan = document.createElement("span");
        $(newSpan).addClass("sr-only");
        $(newSpan).text("Loading...")
        
        $(newSpinner).append(newSpan);
        
        $("#canChange").append(newSpinner);

        xhttp.onreadystatechange = function () {
            if(xhttp.readyState == 4 && xhttp.status == 200) {

                $.ajax({
                    cache: false,
                    dataType: "json",
                    timeout: 3000,
                    error: function(){
                    	$("#canChange").empty();
                        
                        var newPar = document.createElement("p");
                        $(newPar).addClass("h4");
                        $(newPar).text("Non è stato possibile ottenere i dati")
                        $("#canChange").append(newPar);
                        
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
		                            				<input class="form-check-input" name="usersList" type="radio" value="' + users[i].idUtente + '" id="user_'+ users[i].idUtente +'">\
		                            				<label class="form-check-label" for="user_'+ users[i].idUtente +'">\
		                            				'+ users[i].nomeUtente +' '+ users[i].cognomeUtente +' | Ruolo: '+ users[i].ruoloUtente +'\
		                            				</label>\
		                            				</div>';
		                            
		                            toAppend+=checkbox;
		                            
		                            i++;
		                        }
		                        buttonAdd='<br><button type="button" class="btn btn-primary btn-lg" id="btn-adduser">Aggiungi</button> ';
		                        buttonDelete='<button type="button" class="btn btn-primary btn-lg" id="btn-deleteusers">Elimina</button></form>';
		
		                        toAppend+=buttonAdd;
		                        toAppend+=buttonDelete;
		                        
		                        $("#canChange").append().html(toAppend);
		                        
		                        $("#btn-deleteusers").click(function() {
		                        	deleteUsers();
		                    	});
		                        
		                        $("#btn-adduser").click(function() {
		                        	addUser();
		                    	});
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
        
    	$("#canChange").empty();
        $("#showForm").empty();
        

        var newSpinner = document.createElement("div");
        $(newSpinner).addClass("spinner-border text-primary");
        $(newSpinner).attr("role", "status");
        
        var newSpan = document.createElement("span");
        $(newSpan).addClass("sr-only");
        $(newSpan).text("Loading...")
        
        $(newSpinner).append(newSpan);
        
        $("#canChange").append(newSpinner);

        xhttp.onreadystatechange = function () {
            if(xhttp.readyState == 4 && xhttp.status == 200) {

                $.ajax({
                    cache: false,
                    dataType: "json",
                    timeout: 3000,
                    error: function(){
                   
                    	
                    		
                            $("#canChange").empty();
                            
                            var newPar = document.createElement("p");
                            $(newPar).addClass("h4");
                            $(newPar).text("Non è stato possibile ottenere i dati")
                            
                            
                            $("#canChange").append(newPar);
                    	
 	
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
		                            				<input class="form-check-input" name="platesList" type="radio" value="' + plates[i].idPiatto + '" id="plate_'+ plates[i].idPiatto +'">\
		                            				<label class="form-check-label" for="plate_'+ plates[i].idPiatto +'">\
		                            				'+ plates[i].nomePiatto +' | '+ plates[i].prezzoPiatto + '&euro;' + '\
		                            				</label>\
		                            				</div>';
		                            
		                            toAppend+=checkbox;
		                            
		                            i++;
		                        }
		                        buttonAdd='<br><button type="button" class="btn btn-primary btn-lg" id="btn-addplate">Aggiungi</button> ';
		                        buttonDelete='<button type="button" class="btn btn-primary btn-lg" id="btn-deleteplates">Elimina</button></form>';
		
		                        toAppend+=buttonAdd;
		                        toAppend+=buttonDelete;
		                        
		                        $("#canChange").append().html(toAppend);
		                        
		                        $("#btn-deleteplates").click(function() {
		                    		deletePlates();
		                    	});
		                        
		                        $("#btn-addplate").click(function() {
		                    		addPlate();
		                    	});
		                        
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
	
	$("#canChange").empty();
    $("#showForm").empty();

    addUser
	var newForm = "";

	
	newForm = document.createElement("form");
	$(newForm).attr("id", "addUser");
//	--------------------------------------------------------------------

	var newDiv = document.createElement("div");
	$(newDiv).addClass("form-group");
	
	var newLabel = document.createElement("label");
	$(newLabel).attr("for", "nomeUtente");
	$(newLabel).text("Nome utente");

	var newInput = document.createElement("input");
	$(newInput).attr("type", "text");
	$(newInput).attr("name", "nomeUtente");
	$(newInput).attr("aria-describedby", "nomeUtenteHelp");
	$(newInput).attr("placeholder", "Inserisci il nome dell'utente");
	$(newInput).addClass("form-control");
	
	$(newDiv).append(newLabel);
	$(newDiv).append(newInput);
	
	$(newForm).append(newDiv);

//	--------------------------------------------------------------------
	var newDiv1 = document.createElement("div");
	$(newDiv1).addClass("form-group");
	
	var newLabel1 = document.createElement("label");
	$(newLabel1).attr("for", "cognomeUtente");
	$(newLabel1).text("Cognome utente");

	var newInput1 = document.createElement("input");
	$(newInput1).attr("type", "text");
	$(newInput1).attr("name", "cognomeUtente");
	$(newInput1).attr("aria-describedby", "cognomeUtenteHelp");
	$(newInput1).attr("placeholder", "Inserisci il cognome dell'utente");
	$(newInput1).addClass("form-control");
	
	$(newDiv1).append(newLabel1);
	$(newDiv1).append(newInput1);
	
	$(newForm).append(newDiv1);
//	--------------------------------------------------------------------

	var newDiv2 = document.createElement("div");
	$(newDiv2).addClass("form-group");
	
	var newLabel2 = document.createElement("label");
	$(newLabel2).attr("for", "ruoloUtente");
	$(newLabel2).text("Ruolo utente");

	var newInput2 = document.createElement("input");
	$(newInput2).attr("type", "text");
	$(newInput2).attr("name", "ruoloUtente");
	$(newInput2).attr("aria-describedby", "ruoloUtenteHelp");
	$(newInput2).attr("placeholder", "Inserisci il ruolo dell'utente");
	$(newInput2).addClass("form-control");
	
	$(newDiv2).append(newLabel2);
	$(newDiv2).append(newInput2);
	
	$(newForm).append(newDiv2);
//	--------------------------------------------------------------------

	var newDiv3 = document.createElement("div");
	$(newDiv3).addClass("form-group");
	
	var newLabel3 = document.createElement("label");
	$(newLabel3).attr("for", "userId");
	$(newLabel3).text("Username utente");

	var newInput3 = document.createElement("input");
	$(newInput3).attr("type", "text");
	$(newInput3).attr("name", "userId");
	$(newInput3).attr("aria-describedby", "userIdHelp");
	$(newInput3).attr("placeholder", "Inserisci lo username dell'utente");	
	$(newInput3).addClass("form-control");
	
	
	

	$(newDiv3).append(newLabel3);
	$(newDiv3).append(newInput3);

	$(newForm).append(newDiv3);
//	--------------------------------------------------------------------

	var newDiv4 = document.createElement("div");
	$(newDiv4).addClass("form-group");
	
	var newLabel4 = document.createElement("label");
	$(newLabel4).attr("for", "passwordUtente");
	$(newLabel4).text("Password utente");

	var newInput4 = document.createElement("input");
	$(newInput4).attr("type", "password");
	$(newInput4).attr("name", "passwordUtente");
	$(newInput4).attr("aria-describedby", "passwordUtenteHelp");
	$(newInput4).attr("placeholder", "Inserisci la password dell'utente");	
	$(newInput4).addClass("form-control");
	

	$(newDiv4).append(newLabel4);
	$(newDiv4).append(newInput4);

	$(newForm).append(newDiv4);
	
	
	var newButton = document.createElement("button");
	$(newButton).addClass("btn btn-primary btn-lg");	
	$(newButton).attr("type", "button");
	$(newButton).attr("id", "btn-adduser");
	$(newButton).text("Invia");	


	$(newForm).append(newButton);

	$("#showForm").append(newForm);
    
	$("#btn-adduser").click(function() {
		getAllFieldUser();
	});
}

function getAllFieldUser(){
	var userField = $("#addUser").serializeArray();
	 var urlString="nulla";
	 console.log(urlString);
	 var urlString="";
	 console.log(urlString);

	 
	 userField.forEach(element =>{
		 
		 urlString+="&"+element.name + "=" + element.value;
	 });
	 console.log(urlString);

	 var xhttp = new XMLHttpRequest();
	 xhttp.onreadystatechange = function () {
         if(xhttp.readyState == 4 && xhttp.status == 200) {

             $.ajax({
                 cache: false,
                 timeout: 3000,
                 success: function () {
                	 	var success = document.createElement("span");
                	 	$(success).addClass("h4 text-primary");
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
	$("#canChange").empty();
    $("#showForm").empty();


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
                 success: function () {
                	 	var success = document.createElement("span");
                	 	$(success).addClass("h4 text-primary");
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
	
	$("#canChange").empty();
    $("#showForm").empty();


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
	$(newInput).attr("name", "nomePiatto");
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
	$(newInput1).attr("name", "prezzoPiatto");
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
	$(newInput2).attr("name", "categoriaPiatto");
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
	$(newInput3).attr("name", "listaIngredienti");
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
	 });
	 console.log(urlString);

	 var xhttp = new XMLHttpRequest();
	 xhttp.onreadystatechange = function () {
        if(xhttp.readyState == 4 && xhttp.status == 200) {

            $.ajax({
                cache: false,
                timeout: 3000,
                success: function () {
                   	 	$("#showForm").empty();

               	 	var success = document.createElement("span");
            	 	$(success).addClass("h4 text-primary");
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

function deleteTables() {
		
	console.log("delete chiamata");
	
	var xhttp = new XMLHttpRequest();

	var inputElements = document.getElementsByName('tableList');
	for(var i=0; inputElements[i]; ++i){
	      if(inputElements[i].checked){
	    	  
	    	var urlString="";

	  		urlString+="&numeroTavolo" + "=" + inputElements[i].value;
	  		
	  		console.log(urlString)
	  		
	  		
	  		xhttp.onreadystatechange = function () {
	  			if(xhttp.readyState == 4 && xhttp.status == 200) {
	  	        	
	  				$.ajax({
	  	                cache: false,
	  	                timeout: 3000,
	  	                success: function () {
	  	                   	 	$("#showForm").empty();

	  	               	 	var success = document.createElement("span");
	                	 	$(success).addClass("h4 text-primary");
	  	               	 	$(success).text("Eliminazione avvenuta con successo!")
	  	               	 	$("#showForm").empty();
	  	               	    $("#showForm").append(success);

	  	                },
	  	               
	  	            });
	  	        }
	  		}
	  	        
	  			
	  		xhttp.open("GET", "ControllerTavolo?op=" + 3 + urlString, true);
	  		xhttp.send();
	      }
	}
	

}

function deleteUsers() {
	
	console.log("delete chiamata");
	
	var xhttp = new XMLHttpRequest();

	var inputElements = document.getElementsByName('usersList');
	for(var i=0; inputElements[i]; ++i){
	      if(inputElements[i].checked){
	    	  
	    	var urlString="";

	  		urlString+="&idUtente" + "=" + inputElements[i].value;
	  		
	  		console.log(urlString)
	  		
	  		
	  		xhttp.onreadystatechange = function () {
	  			if(xhttp.readyState == 4 && xhttp.status == 200) {
	  	        	
	  				$.ajax({
	  	                cache: false,
	  	                timeout: 3000,
	  	                success: function () {
	  	                   	 	$("#showForm").empty();

	  	               	 	var success = document.createElement("span");
	                	 	$(success).addClass("h4 text-primary");

	  	               	 	$(success).text("Eliminazione avvenuta con successo!")
	  	               	 	$("#showForm").empty();
	  	               	    $("#showForm").append(success);

	  	                },
	  	               
	  	            });
	  	        }
	  		}
	  	        
	  			
	  		xhttp.open("GET", "ControllerUtente?op=" + 3 + urlString, true);
	  		xhttp.send();
	      }
	}
	

}

function deletePlates() {
	
	console.log("delete chiamata");
	
	var xhttp = new XMLHttpRequest();

	var inputElements = document.getElementsByName('platesList');
	for(var i=0; inputElements[i]; ++i){
	      if(inputElements[i].checked){
	    	  
	    	var urlString="";

	  		urlString+="&idPiatto" + "=" + inputElements[i].value;
	  		
	  		console.log(urlString)
	  		
	  		
	  		xhttp.onreadystatechange = function () {
	  			if(xhttp.readyState == 4 && xhttp.status == 200) {
	  	        	
	  				$.ajax({
	  	                cache: false,
	  	                timeout: 3000,
	  	                success: function () {
	  	                   	 	$("#showForm").empty();

	  	               	 	var success = document.createElement("span");
	                	 	$(success).addClass("h4 text-primary");
	  	               	 	$(success).text("Eliminazione avvenuta con successo!")
	  	               	 	$("#showForm").empty();
	  	               	    $("#showForm").append(success);

	  	                },
	  	               
	  	            });
	  	        }
	  		}
	  	        
	  			
	  		xhttp.open("GET", "ControllerPiatto?op=" + 3 + urlString, true);
	  		xhttp.send();
	      }
	}
}

