
$(document).ready(function(){
	$.get("ServletOttieniOrdinazione",{
		"numeroTavolo": localStorage.getItem("tavoloordinazione")
	}).done(function(data,status){
		console.log(JSON.parse(data));
		var  ordinazione = JSON.parse(data);
		 setValue(ordinazione);
		 setProducts(ordinazione.listaPiatti);
	})
	
	$("#ntavolo").text(localStorage.getItem("tavoloordinazione"));
});



function setValue(ordinazione){
	console.log(ordinazione.dataOrdine);
	$("#dataOrdine").text(ordinazione.dataOrdine);
	$("#tavoloOrdine").text(localStorage.getItem("tavoloordinazione"));
	$("#numeroOrdinazione").text(ordinazione.numeroOrdinazione);
}
var totale = 0;
function setProducts(products){
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
	$("#totaleConto").text(totale + " \u20AC");
}
