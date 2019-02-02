class Modal{
	constructor(title,textButton,ntavolo)
	{
		this.title = title;
		this.textButton = textButton;
		this.ntavolo = ntavolo;
	}
	onLoad(){
		$("#ModalAddtableTitle").text(this.title);
		$("#btn-creatavolo").text(this.textButton);
		$("#numeroTavolo").val(this.ntavolo);
	}
	caseCreate(){
		
	}
	
	caseUpdate(){
		
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

function checkNumberTables(tavoli){
	$("#btn-creatavolo").click(function(){
		var f = 0;
		var numberInput = $("#numeroTavolo").val();
		tavoli.forEach(function(element){
			if(numberInput == element.numeroTavolo)
			{
				console.log("tavolo gia' presente");
				showErrorText("Tavolo gia' presente",$("#numeroTavolo").parent());
				f  = 1;
			}
		});
		if(f== 0  && (typeof numberInput != null || typeof numberInput != undefined || numberInput.length != 0))
		{
			$.get({
				url: "ServletAggiungiTavolo",
				data : 'numeroTavolo=' + numberInput
			})
			.done(function(data){
//				$("#ModalAddtable").modal('hide');
				showSuccessText("Tavolo creato con successo",$("#numeroTavolo").parent());
				 (location.reload(),3000);
			});
		}
	});
};


function showErrorText(textShow, howToAppend){
	var span = document.createElement("span");
	$(span).addClass("text-error");
	$(span).text(textShow);
	$(howToAppend).append(span);
	$(howToAppend).children("input").addClass("input-fielderror");
}

function showSuccessText(textShow, howToAppend){
	var span = document.createElement("span");
	$(span).addClass("text-success");
	$(span).text(textShow);
	$(howToAppend).append(span);
}