class ModalUser{
	constructor(title,textButton,id_button, name, surname, password, login, role)
	{
		this.title = title;
		this.textButton = textButton;
		this.name = name;
		this.surname = surname;
		this.role = role;
		this.login = login;
		this.password = password;
		this.id_button = id_button;

		this.onLoad();
	}
	
	onLoad(){
		$("#ModalAddUserTitle").text(this.title);
		$(".btn-modal[data-type='"+ this.id_button+"']").attr("id",this.id_button);
		$("#nomeUtente").val(this.name);
		$("#cognomeUtente").val(this.surname);
		$("#passwordUtente").val(this.password);
		$("#idLogin").val(this.login);
		$("#ruoloUtente").val(this.role);
		$("#"+ this.id_button).text(this.textButton);
		
		$("#"+ this.id_button).click(function(){
			$("#spinner-loading").remove();
			$("#fail").remove();

			var spinner = document.createElement("button");			
			$(spinner).addClass("btn btn-primary");
			$(spinner).prop("disabled");
			$(spinner).attr("type", "button");
			$(spinner).attr("id", "spinner-loading");

			
			var span = document.createElement("span");
			$(span).addClass("spinner-border spinner-border-sm");
			$(span).attr("role","status");
			$(span).attr("aria-hidden","true");
			$(spinner).append(span);
			
			var span1 = document.createElement("span");
			$(span1).addClass("sr-only");
			$(span1).text("Loading...");
			$(spinner).append(span1);	
			
			$("#btn-close").remove();
			$(".modal-footer").append(spinner);
			
			
		});


	}
	caseCreate(utenti){
		$("#"+ this.id_button).click(function(){
			var nameInput = $("#nomeUtente").val();
			var surnameInput = $("#cognomeUtente").val();
			var roleInput = $("#ruoloUtente").val();
			var passwordInput = $("#passwordUtente").val();
			var loginInput = $("#idLogin").val();
			
//funzione controllo lato server

				$.get({
					url: "ServletAggiungiUtente",
					data : {'nomeUtente' : nameInput ,
							'cognomeUtente': surnameInput ,
							'userId' : loginInput ,
							'passwordUtente' : passwordInput , 
							'ruoloUtente' : roleInput }
				})
				.done(function(data){
//					$("#ModalAddUser").modal('hide');
					showSuccessText("Utente aggiunto con successo",$("#nomeUtente").parent());
					setTimeout(function () { location.reload(1); }, 2500);
				}).
				fail(function(data){
				
					var button = document.createElement("button");
					$(button).addClass("btn btn-danger");
					$(button).attr("id", "fail");
					$("#spinner-loading").remove();

					$(button).text("Fallito");
					
					$(".modal-footer").append(button);
					setTimeout(function () { location.reload(1); }, 2500);
					
					
					

				});
		});
	}
	
	caseUpdate(utenti){
		$("#"+this.id_button).click(function(e){
			var nameInput = $("#nomeUtente").val();
			var surnameInput = $("#cognomeUtente").val();
			var roleInput = $("#ruoloUtente").val();
			var passwordInput = $("#passwordUtente").val();
			var loginInput = $("#idLogin").val();
				e.preventDefault();
			
				$.get({
					url: "ServletModificaUtente",
					data : {'nomeUtente' : nameInput ,
							'cognomeUtente': surnameInput ,
							'userId' : loginInput ,
							'passwordUtente' : passwordInput , 
							'ruoloUtente' : roleInput }
				})
				.done(function(data){
//					$("#ModalAddUser").modal('hide');
					showSuccessText("Utente modificato con successo",$("#nomeUtente").parent());
					(location.reload(),3000);
				}).
				fail(function(data){
				
					var button = document.createElement("button");
					$(button).addClass("btn btn-danger");
					$(button).attr("id", "fail");
					$("#spinner-loading").remove();

					$(button).text("Fallito");
					
					$(".modal-footer").append(button);
					setTimeout(function () { location.reload(1); }, 2500);
					
					
					

				});
			
			e.stopPropagation();

		});
	}
}
