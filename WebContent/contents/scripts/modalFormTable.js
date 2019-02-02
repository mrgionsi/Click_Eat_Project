class Modal{
	constructor(title,textButton,ntavolo,id_button)
	{
		this.title = title;
		this.textButton = textButton;
		this.ntavolo = ntavolo;
		this.id_button = id_button;

		this.onLoad();
	}
	onLoad(){
		$("#ModalAddtableTitle").text(this.title);
		$(".btn-modal").attr("id",this.id_button);
		$("#"+ this.id_button).text(this.textButton);
		$("#numeroTavolo").val(this.ntavolo);
	}

	caseCreate(tavoli){
		$("#"+ this.id_button).click(function(){
			var numberInput = $("#numeroTavolo").val();
			var f  = checkNumberTables(tavoli,numberInput);
			if(f== 0  && (typeof numberInput != null || typeof numberInput != undefined || numberInput.length != 0))
			{
				$.get({
					url: "ServletAggiungiTavolo",
					data : 'numeroTavolo=' + numberInput
				})
				.done(function(data){
//					$("#ModalAddtable").modal('hide');
					showSuccessText("Tavolo creato con successo",$("#numeroTavolo").parent());
					(location.reload(),3000);
				});
			}
		});
	}

	caseUpdate(tavoli){
		$("#"+this.id_button).click(function(e){
				e.preventDefault();
			var numberInput = $("#numeroTavolo").val();
			var f = checkNumberTables(tavoli,numberInput);
			if(f== 0  && (typeof numberInput != null || typeof numberInput != undefined || numberInput.length != 0))
			{
				$.get({
					url: "ServletModificaTavolo",
					data :{ 
							'numeroTavolo':numberInput,
							'new_val=':  (this.ntavolo)
						  }
				})
				.done(function(data){
////					$("#ModalAddtable").modal('hide');
//					showSuccessText("Tavolo creato con successo",$("#numeroTavolo").parent());
//					(location.reload(),3000);
				});
			}
			e.stopPropagation();

		});
	}
	//aggiungere funzione al click crea/modifica
}

$(document).ready(function(){
	$("#numeroTavolo").click(function(){
		removeErrorText();
	})
	$("#numeroTavolo").on("input",function(){
		removeErrorText();
	})
});

function removeErrorText(){
	$(".text-error").remove();
	$("#numeroTavolo").removeClass("input-fielderror");
}

function checkNumberTables(tavoli,numberInput){
	removeErrorText();

	var f = 0;
	tavoli.forEach(function(element){
		if(numberInput == element.numeroTavolo)
		{
			console.log("tavolo gia' presente");
			showErrorText("Tavolo gia' presente",$("#numeroTavolo").parent());
			f  = 1;
		}
	});
	return f;
};


function showErrorText(textShow, howToAppend){
	if(($(".text-error").length) == 0){
		var span = document.createElement("span");
		$(span).addClass("text-error");
		$(span).text(textShow);
		$(howToAppend).append(span);
		$(howToAppend).children("input").addClass("input-fielderror");
	}
}

function showSuccessText(textShow, howToAppend){
	var span = document.createElement("span");
	$(span).addClass("text-success");
	$(span).text(textShow);
	$(howToAppend).append(span);
}

