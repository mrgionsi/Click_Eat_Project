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
			console.log("IN CONFIRM");

			$.get({
				url: "ServletEliminaTavolo",
				data : "numeroTavolo=" + ntavolo
			})
			.done(function(data){
				$("#messageModal").text("");
				showSuccessText("Tavolo eliminato con successo",$("#messageModal"));
				(location.reload(),4000);
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
			console.log("IN CONFIRM");

			$.get({
				url: "ServletEliminaUtente",
				data : "idLogin=" + idLogin
			})
			.done(function(data){
				$("#messageModal").text("");
				showSuccessText("Utente eliminato con successo",$("#messageModal"));
				(location.reload(),4000);
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
			console.log("IN CONFIRM");

			$.get({
				url: "ServletEliminaPiatto",
				data : "idPiatto=" + idPiatto
			})
			.done(function(data){
				$("#messageModal").text("");
				showSuccessText("Piatto eliminato con successo",$("#messageModal"));
				(location.reload(),4000);
			});
		});
	}
}
