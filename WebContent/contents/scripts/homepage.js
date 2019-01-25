$( document ).ready(function() {
	tablesList();
});

function tablesList() {
		var row = "";
		row = document.createElement("div");
		$(row).addClass("row");

		$.get("ControllerTavolo?op=1",function(data,status)  {
						var tables =JSON.parse(data);
						$("#tablesList").empty();
						tables.forEach(element =>{

							var col = document.createElement("div");
							$(col).addClass("col-3 pr-3 mb-5");

							var btn = document.createElement("div");
							
							 // <object data="./contents/images/Tavolo.svg" type="image/svg+xml"></object>	
							var svg = document.createElement("img");
							$(svg).addClass("table");
							$(svg).attr("id","table-img");
							
							

							
							$(btn).attr("id", "table-"+element.numeroTavolo);
							if(element.flagOccupato){
								colorTable(svg,"red");
								$(btn).text("Tavolo #"+ element.numeroTavolo + " | Occupato | Ordinazione #" + element.numeroOrdinazione);
								$(btn).addClass("bg-danger");
								
							} else {
								colorTable(svg,"green");
								$(btn).text("Tavolo #"+ element.numeroTavolo +  " | Libero");
								$(btn).addClass("bg-success");
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


