$('html').bind('keypress', function(e)
{
   if(e.keyCode == 13)
   {
      return false;
   }
});

$(document).ready(function(){
	loadTables();
	loadUsers();
	loadPlates();
	
});

function loadTables() {
	$.get("ServletGetAllTavoli",function(data,status){
		var tavoli = JSON.parse(data);
		if(tavoli)
			{
				showTable(tavoli);
				//console.log(tavoli);
				$("#table-tablelist").DataTable({
						"paging": false,
						"bInfo" : false, 
						"columnDefs": [ {
					          "targets": 'no-sort',
					          "orderable": false,
					    } ]
				});
				$('.dataTables_length').addClass('bs-select');

			}
	})
}

function showTable(tavoli){
	
	//thead ---- intestazione start
	var thead = $("#table-tablelist > thead");
	
	var tr = document.createElement("tr");
	
	var th = document.createElement("th");
	$(th).text("N. Tavoli");

	var th1 = document.createElement("th");
	$(th1).text("Stato(Libero/Occupato)");
	
	var th2 = document.createElement("th");
	$(th2).text("Numero Ordinazione");
	
	var th3 = document.createElement("th");
	$(th3).addClass("no-sort");
	
	var divimg = document.createElement("div");
	$(divimg).attr("id","btn-add-table");
	
	var add = document.createElement("img");
	$(add).attr("id","add-btn-table");
	$(add).addClass("btn-rowtable btn-add");
	//data attribute for modal
	$(add).attr("data-toggle","modal");
	$(add).attr("data-target","#ModalAddtable");
	
	$(add).attr("src","./contents/images/add-button.png");
	$(divimg).append(add);
	$(th3).append(divimg);
	
	$(divimg).click(function(){
		removeErrorText();
		var createModal = new Modal("Crea nuovo tavolo", "Aggiungi","","btn-createtable");
		createModal.caseCreate(tavoli);
		
	});
	
	$(tr).append(th);
	$(tr).append(th1);
	$(tr).append(th2);
	$(tr).append(th3);
	$(thead).append(tr);

	//thead ---- intestazione end
	
	// tbody --- corpo start 
	var tbody = $("#table-tablelist > tbody");
	tavoli.forEach(function(element){
		var tr = document.createElement("tr");
		var th = document.createElement("td");
		$(th).attr("scope","row");
		$(th).text(element.numeroTavolo);
		
		var th1 = document.createElement("td");
		$(th1).text(element.flagOccupato);
		
		var th2 = document.createElement("td");
		$(th2).text(element.numeroOrdinazione);
		
		var th3 = document.createElement("td");
		var divmodify = document.createElement("div");
		$(divmodify).attr("id","removerow-" +  + element.numeroTavolo);
			var modify = document.createElement("img");
				$(modify).attr("id","modifyrow-" + element.numeroTavolo);
				$(modify).addClass("btn-rowtable btn-edit");
				$(modify).attr("src","./contents/images/edit-button.png");
				$(divmodify).append(modify);
				$(divmodify).attr("data-toggle","modal");
				$(divmodify).attr("data-target","#ModalAddtable");
				//$(divmodify).attr("data-ntavolo",element.numeroTavolo);

			$(divmodify).click(function(){
				removeErrorText();
				var modifyModal = new Modal("Modifica numero tavolo", "Modifica",element.numeroTavolo,"btn-modifytable");
				modifyModal.caseUpdate(tavoli);

				
			});
			
			var remove = document.createElement("img");
				$(remove).attr("id","removerow-" +  + element.numeroTavolo);
				$(remove).attr("src","./contents/images/remove-button.png");
				$(remove).addClass("btn-rowtable");
				$(remove).attr("data-toggle","modal");
				$(remove).attr("data-target","#ConfirmModal");
				$(remove).click(function(){
					console.log("CIAOO");
					var removeModal = new ModalConfirmTable("Elimina", "Eliminare tavolo "+ element.numeroTavolo+ "?");
					removeModal.onConfirm(element.numeroTavolo);
	
				});
				
				$(th3).append(divmodify);
				$(th3).append(remove);
		
		$(tr).append(th);
		$(tr).append(th1);
		$(tr).append(th2);
		$(tr).append(th3);
		$(tbody).append(tr);

	})


}


function loadUsers() {
	$.get("ServletGetAllUtenti",function(data,status){
		var utenti = JSON.parse(data);
		if(utenti)
			{
				showUsers(utenti);
				$("#table-tablelistuser").DataTable({
					"paging": false,
					"bInfo" : false, 
					 "columnDefs": [ {
				          "targets": 'no-sort',
				          "orderable": false,
				    } ]
				});
				$('.dataTables_length').addClass('bs-select');
				console.log(utenti);
			}
	})
}

function showUsers(utenti){
	//thead ---- intestazione start
	var thead = $("#table-tablelistuser > thead");
	
	var tr = document.createElement("tr");
	
	var th = document.createElement("th");
	$(th).attr("scope","col");
	$(th).text("Nome");

	var th1 = document.createElement("th");
	$(th1).attr("scope","col");
	$(th1).text("Cognome");
	

	var th2 = document.createElement("th");
	$(th2).attr("scope","col");
	$(th2).text("Ruolo");
		
	var th3 = document.createElement("th");
	$(th3).attr("scope","col");
	$(th3).text("Username");
	
	var th4 = document.createElement("th");
	$(th4).attr("scope","col");
	$(th4).text("Password");
	
	var th5 = document.createElement("th");
	$(th5).addClass("no-sort");

	$(th5).attr("scope","col");
	var add = document.createElement("img");
	$(add).attr("id","add-btn-table");
	$(add).addClass("btn-rowtable btn-add");
	$(add).attr("src","./contents/images/add-button.png");
	
	//data attribute for modal
	$(add).attr("data-toggle","modal");
	$(add).attr("data-target","#modalAddUser");
	
	$(add).click(function(){
		removeErrorText();
		var createModal = new ModalUser("Aggiungi nuovo utente", "Aggiungi","btn-createuser", "", "", "", "", "");
		createModal.caseCreate(utenti);
		
	
	});
	$(th5).append(add);
	
	$(tr).append(th);
	$(tr).append(th1);
	$(tr).append(th2);
	$(tr).append(th3);
	$(tr).append(th4);
	$(tr).append(th5);
	$(thead).append(tr);

	//thead ---- intestazione end
	
	// tbody --- corpo start 
	var tbody = $("#table-tablelistuser > tbody");
	utenti.forEach(function(element){
		var tr = document.createElement("tr");
		var th = document.createElement("th");
		$(th).attr("scope","row");
		$(th).text(element.nomeUtente);
		
		var th1 = document.createElement("th");
		$(th1).text(element.cognomeUtente);
		
		var th2 = document.createElement("th");
		$(th2).text(element.ruoloUtente);
		
		var th3 = document.createElement("th");
		$(th3).text(element.idLogin);
		
		var th4 = document.createElement("th");
		$(th4).text(element.passwordUtente);
		
		var th5 = document.createElement("th");
			var modify = document.createElement("img");
				$(modify).attr("id","modifyrow-" + element.idLogin);
				$(modify).addClass("btn-rowtable btn-edit");
				$(modify).attr("src","./contents/images/edit-button.png");
				
				$(modify).click(function(){
					removeErrorText();
				
					var modifyModal = new ModalUser("Modifica utente", "Modifica","btn-modifyuser-"+ element.idLogin ,element.nomeUtente, element.cognomeUtente, element.passworUtente, element.idLogin, element.ruoloUtente);
					modifyModal.caseUpdate(utenti);
					
				});


				var remove = document.createElement("img");
				$(remove).attr("id","removerow-" +  + element.idLogin);
				$(remove).attr("src","./contents/images/remove-button.png");
				$(remove).addClass("btn-rowtable");
				$(remove).attr("data-toggle","modal");
				$(remove).attr("data-target","#ConfirmModal");
				$(remove).click(function(){
					console.log("CIAOO UTENTE");
					var removeModal = new ModalConfirmUser("Elimina", "Eliminare utente "+ element.idLogin+ "?");
					removeModal.onConfirm(element.idLogin);
	
				});
				
				$(th3).append(modify);
				$(th3).append(remove);
				
				
		
		$(tr).append(th);
		$(tr).append(th1);
		$(tr).append(th2);
		$(tr).append(th3);
		$(tr).append(th4);
		$(tr).append(th5);
		$(tbody).append(tr);

	});

}


function loadPlates(){
	$.get("ServletGetAllPiatti",function(data,status){
		var piatti = JSON.parse(data);
		if(piatti)
			{
				showPlates(piatti);
				console.log(piatti);
				$("#table-tablelistpiatti").DataTable({
					"paging": false,
					"bInfo" : false, 
					 "columnDefs": [ {
				          "targets": 'no-sort',
				          "orderable": false,
				    } ]
				});
				$('.dataTables_length').addClass('bs-select');
			}
	})
}

function showPlates(piatti){
	//thead ---- intestazione start
	var thead = $("#table-tablelistpiatti > thead");
	
	var tr = document.createElement("tr");
	
	var th = document.createElement("th");
	$(th).attr("scope","col");
	$(th).text("Nome Piatto");

	var th1 = document.createElement("th");
	$(th1).attr("scope","col");
	$(th1).text("Categoria");
	

	var th2 = document.createElement("th");
	$(th2).attr("scope","col");
	$(th2).text("Prezzo");
		
	var th3 = document.createElement("th");
	$(th3).attr("scope","col");
	$(th3).text("Lista Ingredienti");
	
	
	var th5 = document.createElement("th");
	$(th5).attr("scope","col");
	$(th5).addClass("no-sort");

	var add = document.createElement("img");
	$(add).attr("id","add-btn-table");
	$(add).addClass("btn-rowtable btn-add");
	$(add).attr("src","./contents/images/add-button.png");
	
	$(th5).append(add);
	
	$(tr).append(th);
	$(tr).append(th1);
	$(tr).append(th2);
	$(tr).append(th3);

	$(tr).append(th5);
	$(thead).append(tr);

	//thead ---- intestazione end
	
	// tbody --- corpo start 
	var tbody = $("#table-tablelistpiatti > tbody");
	piatti.forEach(function(element){
		var tr = document.createElement("tr");
		var th = document.createElement("th");
		$(th).attr("scope","row");
		$(th).text(element.nomePiatto);
		
		var th1 = document.createElement("th");
		$(th1).text(element.categoriaPiatto);
		
		var th2 = document.createElement("th");
		$(th2).text(element.prezzoPiatto);
		
		var th3 = document.createElement("th");
		$(th3).text(concatIngredienti(element.listaIngredienti));
		
		
		var th5 = document.createElement("th");
			var modify = document.createElement("img");
				$(modify).attr("id","modifyrow-" + element.idPiatto);
				$(modify).addClass("btn-rowtable btn-edit");
				$(modify).attr("src","./contents/images/edit-button.png");
			var remove = document.createElement("img");
				$(remove).attr("id","removerow-" +  + element.idPiatto);
				$(remove).attr("src","./contents/images/remove-button.png");
				$(remove).addClass("btn-rowtable");
				$(th5).append(modify);
				$(th5).append(remove);
		
		$(tr).append(th);
		$(tr).append(th1);
		$(tr).append(th2);
		$(tr).append(th3);
		$(tr).append(th5);
		$(tbody).append(tr);
		
	})
}


function concatIngredienti(lista){
	var result = "";
	lista.forEach(item =>{
		result += item.nomeIngrediente +",";
	});
	return result;
}

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

