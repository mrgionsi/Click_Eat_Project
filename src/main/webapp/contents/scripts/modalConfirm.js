class ModalConfirmTable{
	constructor(title,message){
		this.title = title; 
		this.message = message;
		this.onLoad();
	}
	onLoad(){
		$("#modal-confirm-title").text(this.title);
		$("#messageModal").text(this.message).css("font-weight:bold;");
	}
	
	onConfirm(ntavolo){
		$("#delete-button").click(function(){
			$("#delete-button").text("Invio...");
			$("#delete-button").prop("disabled", true);

			
			$.get({
				url: "ServletEliminaTavolo",
				data : "numeroTavolo=" + ntavolo
			})
			.done(function(data){
				$("#messageModal").text("");
				$("#delete-button").text("Salvato");
				$("#delete-button").prop("disabled", true);
				showSuccessText("Tavolo eliminato con successo",$("#messageModal"));
				setTimeout(function () { location.reload(1); }, 2500);
			}).
			fail(function(data){
				

				setTimeout(function () { 			
					$("#delete-button").text("Elimina");
					$("#delete-button").prop("disabled", false); 
				}, 2500);
				
				
				

			});
		});
	}
}

class ModalConfirmUser{
	constructor(title,message){
		this.title = title; 
		this.message = message;
		this.onLoad();
	}
	onLoad(){
		$("#modal-confirm-title").text(this.title);
		$("#messageModal").text(this.message).css("font-weight:bold;");
	}
	
	onConfirm(idLogin){
		$("#delete-button").click(function(){
			$("#delete-button").text("Invio...");
			$("#delete-button").prop("disabled", true);

			console.log("IN CONFIRM");
			
			$.get({
				url: "ServletEliminaUtente",
				data : "idLogin=" + idLogin
			})
			.done(function(data){
				$("#messageModal").text("");
				$("#delete-button").text("Salvato");
				$("#delete-button").prop("disabled", true);
				showSuccessText("Utente eliminato con successo",$("#messageModal"));
				setTimeout(function () { location.reload(1); }, 2500);
			}).
			fail(function(data){
				

				setTimeout(function () { 			
					$("#delete-button").text("Elimina");
					$("#delete-button").prop("disabled", false); 
				}, 2500);
				
				
				

			});
		});
	}
}

class ModalConfirmPlate{
	constructor(title,message){
		this.title = title; 
		this.message = message;
		this.onLoad();
	}
	onLoad(){
		$("#modal-confirm-title").text(this.title);
		$("#messageModal").text(this.message).css("font-weight:bold;");
	}
	
	onConfirm(idPiatto){
		$("#delete-button").click(function(){
			$("#delete-button").text("Invio...");
			$("#delete-button").prop("disabled", true);

			console.log("IN CONFIRM");

			$.get({
				url: "ServletEliminaPiatto",
				data : "idPiatto=" + idPiatto
			})
			.done(function(data){
				$("#messageModal").text("");
				$("#delete-button").text("Salvato");
				$("#delete-button").prop("disabled", true);
				showSuccessText("Piatto eliminato con successo",$("#messageModal"));
				setTimeout(function () { location.reload(1); }, 2500);
			}).
			fail(function(data){
				

				setTimeout(function () { 			
					$("#delete-button").text("Elimina");
					$("#delete-button").prop("disabled", false); 
				}, 2500);
				
				
				

			});
		});
	}
}
