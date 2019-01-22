$( document ).ready(function() {
	tablesList();
});

function tablesList() {
	$(document).ready(function(){
		var tables = null;
		var xhttp = new XMLHttpRequest();

		var row = "";
		row = document.createElement("div");
		$(row).addClass("row mx-auto");

		xhttp.onreadystatechange = function () {
			if(xhttp.readyState == 4 && xhttp.status == 200) {

				$.ajax({
					cache: false,
					dataType: "json",
					error: function(){
						console.log("json not found");
					},
					success: function (tables) {
						console.log("success");
						
						$("#tablesList").empty();
						tables.forEach(element =>{

							var col = document.createElement("div");
							$(col).addClass("col-3 pr-3 table");

							var btn = document.createElement("div");
							



							$(btn).attr("id", "table-"+element.numeroTavolo);
							if(element.flagOccupato){
								$(btn).text("Tavolo #"+ element.numeroTavolo + " | Occupato | Ordinazione #" + element.numeroOrdinazione);
								$(btn).addClass("bg-danger");
							} else {
								$(btn).text("Tavolo #"+ element.numeroTavolo +  " | Libero");
								$(btn).addClass("bg-success");
							}

							$(col).append(btn);
							$(row).append(col);
							$("#tablesList").append(row);
						});





					},
					url: 'jsonfiles/listaTavoli.json',
				});
			}
		}


		xhttp.open("GET", "ControllerTavolo?op=" + 1, true);
		xhttp.send();
	});
}