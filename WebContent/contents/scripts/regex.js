const loginRegex = new RegExp(/^[a-z]{1}\.{1}[a-z]{4,34}$/, "g");
const nomeRegex = new RegExp(/^[A-Za-z\s]{3,35}$/, "g");
const passwordRegex	= new RegExp(/^[A-Za-z0-9]{8,36}$/, "g");
const ruoloRegex = new RegExp(/^((\b(Cameriere)\b)*(\b(Amministratore)\b)*(\b(Cassiere)\b)*)+$/, "g");
const nTavoloRegex = new RegExp(/^[0-9]{1,3}$/, "g");
const nomePiatto = new RegExp(/^[A-Za-z\s]{4,36}$/, "g");
const categoriaPiatto = new RegExp(/^((\b(Primi)\b)*(\b(Secondi)\b)*(\b(Contorni)\b)*)+$/, "g");
const prezzoPiatto = new RegExp(/^[0-9]{1,5},[0-9]{2}$/, "g");
const listaIngredienti = new RegExp(/^((,{0,1})([A-Za-z\s]{4,36})(,{0,1}))+$/, "g");
$("#nomeUtente").keyup(function(event, status){
	
	
	var value = this.value;
	
	
	
	if(!value.match(nomeRegex)){
		$(this).addClass("input-fielderror");
		$(".btn-modal").prop( "disabled", true );
	}else{
		$(this).removeClass("input-fielderror");
		$(".btn-modal").prop( "disabled", true );

	}
});

$("#cognomeUtente").keyup(function(event, status){
	
	
	var value = this.value;
	
	
	
	if(!value.match(nomeRegex)){
		$(this).addClass("input-fielderror");
		$(".btn-modal").prop( "disabled", true );
	}else{
		$(this).removeClass("input-fielderror");
		$(".btn-modal").prop( "disabled", false);
	}
});

$("#ruoloUtente").keyup(function(event, status){
	
	
	var value = this.value;
	
	
	
	if(!value.match(ruoloRegex)){
		$(this).addClass("input-fielderror");
		$(".btn-modal").prop( "disabled", true );
	}else{
		$(this).removeClass("input-fielderror");
		$(".btn-modal").prop( "disabled", false);
	}
});

$("#idLogin").keyup(function(event, status){
	
	
	
	var value = this.value;

	
	if(!value.match(loginRegex)){
		$(this).addClass("input-fielderror");
		$(".btn-modal").prop( "disabled", true );
	}else{
		$(this).removeClass("input-fielderror");
		$(".btn-modal").prop( "disabled", false);
	}
});

$("#passwordUtente").keyup(function(event, status){
	
	var value = this.value;
	
	if(!value.match(passwordRegex)){
		$(this).addClass("input-fielderror");
		$(".btn-modal").prop( "disabled", true );

	}else{
		$(this).removeClass("input-fielderror");
		$(".btn-modal").prop( "disabled", false);
	}
});

$("#numeroTavolo").keyup(function(event, status){
	
	
	var value = this.value;
	
	
	
	if(!value.match(nTavoloRegex)){
		$(this).addClass("input-fielderror");
		$(".btn-modal").prop( "disabled", true );
	}else{
		$(this).removeClass("input-fielderror");
		$(".btn-modal").prop( "disabled", false);
	}
});

$("#nomePiatto").keyup(function(event, status){
	
	
	var value = this.value;
	
	
	
	if(!value.match(nomePiatto)){
		$(this).addClass("input-fielderror");
		$(".btn-modal").prop( "disabled", true );
	}else{
		$(this).removeClass("input-fielderror");
		$(".btn-modal").prop( "disabled", false);
	}
});

$("#categoriaPiatto").keyup(function(event, status){
	
	
	var value = this.value;
	
	
	
	if(!value.match(categoriaPiatto)){
		$(this).addClass("input-fielderror");
		$(".btn-modal").prop( "disabled", true );

	}else{
		$(this).removeClass("input-fielderror");
		$(".btn-modal").prop( "disabled", false);
	}
});

$("#listaIngredienti").keyup(function(event, status){
	
	
	var value = this.value;
	
	
	
	if(!value.match(listaIngredienti)){
		$(this).addClass("input-fielderror");
		$(".btn-modal").prop( "disabled", true );

	}else{
		$(this).removeClass("input-fielderror");
		$(".btn-modal").prop( "disabled", false);
	}
});

$("#prezzoPiatto").keyup(function(event, status){
	
	
	var value = this.value;
	
	
	
	if(!value.match(prezzoPiatto)){
		$(this).addClass("input-fielderror");
		$(".btn-modal").prop( "disabled", true );

	}else{
		$(this).removeClass("input-fielderror");
		$(".btn-modal").prop( "disabled", false);
	}
});

