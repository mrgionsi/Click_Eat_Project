$(document).ready(function(){
	$.get("ServletOttieniOrdinazione",{
		"numeroTavolo": 1
	}).done(function(data,status){
		console.log(JSON.parse(data));
	})
});