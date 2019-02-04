$( document ).ready(function() {
	
	tablesList();
//	onClickTable();
});

function tablesList() {
		var row = "";
		row = document.createElement("div");
		$(row).addClass("row mx-auto");

		$.get("ServletGetAllTavoli",function(data,status)  {
						var tables =JSON.parse(data);
						$("#tablesList").empty();
						tables.forEach(element =>{

							var col = document.createElement("div");
							$(col).addClass("col-2 pr-4 mb-5 container-table");
							$(col).attr("id","table-container");
							$(col).attr("data-ntavolo",element.numeroTavolo);
							onClickTable(col);
							var btn = document.createElement("div");
							var text = document.createElement("span");
							$(text).addClass("span-centered");
							$(text).text(element.numeroTavolo);
							$(col).append(text);
							 // <object data="./contents/images/Tavolo.svg" type="image/svg+xml"></object>	
							var svg = document.createElement("img");
							$(svg).addClass("table");
							$(svg).attr("id","table-img");
							
							$(btn).attr("id", "table-"+element.numeroTavolo);
							if(element.flagOccupato){
								colorTable(svg,"red");
//								$(btn).text("Tavolo #"+ element.numeroTavolo + " | Occupato | Ordinazione #" + element.numeroOrdinazione);
//								$(btn).addClass("bg-danger");
								
							} else {
								colorTable(svg,"green");
//								$(btn).text("Tavolo #"+ element.numeroTavolo +  " | Libero");
//								$(btn).addClass("bg-success");
							}

							
							$(col).append(svg);
							$(row).append(col);
							$("#tablesList").append(row);
							
							$(col).append(btn);
							$(row).append(col);
							$("#tablesList").append(row);
						});
				});

}


//la funzione colorTable seleziona tutti gli elementi del tavolo e gli assegna una classe che contiene il colore
function colorTable(svg, colorclass)
{
	$(svg).attr("src","./contents/images/table-"+colorclass+".png");
}

function onClickTable(tavolo){
$(tavolo).click(function(){
	var ntavolo = $(this).data("ntavolo") 
	localStorage.setItem("tavoloordinazione",ntavolo);
//	console.log(localStorage.getItem("tavoloordinazione"));
	$("#tablesList").hide();
	
	var prodOrdinazione = document.createElement("div");
	$(prodOrdinazione).attr("id","productOrder");
	$(prodOrdinazione).load("./ProdottiOrdinazione.jsp");
	$(".container-fluid").append(prodOrdinazione);
})
}
