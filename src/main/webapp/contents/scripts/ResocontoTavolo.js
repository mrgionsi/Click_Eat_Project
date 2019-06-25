var ntavolo = localStorage.getItem("tavoloordinazione");
$(document).ready(function(){
	
	$.get("ServletOttieniOrdinazione",{
		"numeroTavolo": ntavolo
	}).done(function(data,status){
		console.log(JSON.parse(data));
		var  ordinazione = JSON.parse(data);
		 setValue(ordinazione);
		 setProducts(ordinazione.listaPiatti);
	})
	
	$("#ntavolo").text(ntavolo);
});



function setValue(ordinazione){
	console.log(ordinazione.dataOrdine);
	$("#dataOrdine").text(ordinazione.dataOrdine);
	$("#tavoloOrdine").text(localStorage.getItem("tavoloordinazione"));
	$("#numeroOrdinazione").text(ordinazione.numeroOrdinazione);
}
var totale = 0;
function setProducts(products){
	if(products.length>0)
		{
		    var i = document.createElement("i");
		    $(i).addClass("fas fa-print");

		    $("#stampaConto").empty("Stampa");
			$("#stampaConto").append(i);
			$("#stampaConto").append(" Stampa");
			$("#stampaConto").prop("disabled", false);
		}
	var body =$("#table-ordering").children("tbody");
	products.forEach(function(p){
		var tr = document.createElement("tr");
		
		var td = document.createElement("td");
		$(td).text(p.nomePiatto);
		$(tr).append(td);
		var td = document.createElement("td");
		$(td).text(p.quantita);
		$(td).addClass("text-center");
		$(tr).append(td);
		var td = document.createElement("td");

		$(td).text(p.prezzoPiatto + " \u20AC");
		$(td).addClass("text-center");
		$(tr).append(td);
		var td = document.createElement("td");
		$(td).text(p.quantita * p.prezzoPiatto + " \u20AC");
		$(td).addClass("text-center");
		$(tr).append(td);
		totale = totale + (p.quantita * p.prezzoPiatto);
		$(body).append(tr);
	})
	var tot = parseFloat(totale);
	$("#totaleConto").text(tot.toFixed(2) + " \u20AC");
}

$("#stampaConto").click(function(e){
	$.get("ServletLiberaTavolo", {
		"numeroTavolo" : ntavolo
	}).done(function(e){
		window.location = "./homepage.jsp";
	})
})