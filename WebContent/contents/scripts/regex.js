const loginRegex = new RegExp(/^[a-z]{1}\.{1}[a-z]{4,34}$/, "g");
const nomeRegex = new RegExp(/^[A-Za-z\s]{3,35}$/, "g");
const passwordRegex	= new RegExp(/^[A-Za-z0-9]{8,36}$/, "g");
const ruoloRegex = new RegExp(/^((\b(Cameriere)\b)*(\b(Amministratore)\b)*(\b(Cassiere)\b)*)+$/, "g");
const nTavoloRegex = new RegExp(/^[0-9]{1,3}$/, "g");
const nomePiatto = new RegExp(/^[A-Za-z\s]{4,36}$/, "g");
const categoriaPiatto = new RegExp(/^((\b(Primi)\b)*(\b(Secondi)\b)*(\b(Contorni)\b)*)+$/, "g");
const prezzoPiatto = new RegExp(/^[0-9]{1,5},[0-9]{2}$/, "g");
const listaIngredienti = new RegExp(/^((,{0,1})([A-Za-z\s]{4,36})(,{0,1}))+$/, "g");

var nur = false;
var cur = false;
var rur = false;
var ilr = false;
var pur = false;

var npr = false;
var cpr = false;
var lir = false;
var ppr = false;

  $("#nomeUtente").keyup(function(event, status){
	
	
	var value = this.value;
	
	
	
	if(!value.match(nomeRegex)){
		$(this).addClass("input-fielderror");
		$(".btn-modal").prop( "disabled", true );
		nur =  false;
	}else{
		$(this).removeClass("input-fielderror");
		//$(".btn-modal").prop( "disabled", false);
		nur = true;
		checkFormUser();
		
	}
});

  $("#cognomeUtente").keyup(function(event, status){
	
	
	var value = this.value;
	
	
	
	if(!value.match(nomeRegex)){
		$(this).addClass("input-fielderror");
		$(".btn-modal").prop( "disabled", true );
		cur = false;
	}else{
		$(this).removeClass("input-fielderror");
		//$(".btn-modal").prop( "disabled", false);
		cur = true;

		checkFormUser();
		}
});

  $("#ruoloUtente").keyup(function(event, status){
	
	
	var value = this.value;
	
	
	
	if(!value.match(ruoloRegex)){
		$(this).addClass("input-fielderror");
		$(".btn-modal").prop( "disabled", true );
		rur= false;
	}else{
		$(this).removeClass("input-fielderror");
		//$(".btn-modal").prop( "disabled", false);
		rur = true;

		checkFormUser();
		}
});

  $("#idLogin").keyup(function(event, status){
	
	
	
	var value = this.value;

	
	if(!value.match(loginRegex)){
		$(this).addClass("input-fielderror");
		$(".btn-modal").prop( "disabled", true );
		ilr = false;
	}else{
		$(this).removeClass("input-fielderror");
		//$(".btn-modal").prop( "disabled", false);
		ilr = true;

		checkFormUser();
		}
});

 $("#passwordUtente").keyup(function(event, status){
	
	var value = this.value;
	
	if(!value.match(passwordRegex)){
		$(this).addClass("input-fielderror");
		$(".btn-modal").prop( "disabled", true );
		 pur = false;

	}else{
		$(this).removeClass("input-fielderror");
		//$(".btn-modal").prop( "disabled", false);
		 pur = true;

		checkFormUser();
	}
});

function checkFormUser(){
	if(pur && nur && cur && rur && lir){
		$(".btn-modal").prop( "disabled", false);
		console.log("tutto ok, abilito il bottnone");
	}
}

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
			npr = false;
		}else{
			$(this).removeClass("input-fielderror");
			//$(".btn-modal").prop( "disabled", false);
			npr = true;
			checkFormPlate();

		}
	});

	  $("#categoriaPiatto").keyup(function(event, status){
		
		
		var value = this.value;
		
		
		
		if(!value.match(categoriaPiatto)){
			$(this).addClass("input-fielderror");
			$(".btn-modal").prop( "disabled", true );
			cpr = false;

		}else{
			$(this).removeClass("input-fielderror");
			//$(".btn-modal").prop( "disabled", false);
			cpr = true;
			checkFormPlate();

		}
	});

	  $("#listaIngredienti").keyup(function(event, status){
		
		
		var value = this.value;
		
		
		
		if(!value.match(listaIngredienti)){
			$(this).addClass("input-fielderror");
			$(".btn-modal").prop( "disabled", true );
			lir = false;

		}else{
			$(this).removeClass("input-fielderror");
			//$(".btn-modal").prop( "disabled", false);
			lir = true;
			checkFormPlate();

		}
	});

	  $("#prezzoPiatto").keyup(function(event, status){
		
		
		var value = this.value;
		
		
		
		if(!value.match(prezzoPiatto)){
			$(this).addClass("input-fielderror");
			$(".btn-modal").prop( "disabled", true );
			ppr = false;

		}else{
			$(this).removeClass("input-fielderror");
			//$(".btn-modal").prop( "disabled", false);
			ppr = true;
			checkFormPlate();
		}
	});
	
	function checkFormPlate(){
		if(npr && cpr && ppr && lir){
			$(".btn-modal").prop( "disabled", false);
		}
		
	}


