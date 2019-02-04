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
		//
		$(".btn-modal").attr("id",this.id_button);
		$("#nomeUtente").val(this.name);
		$("#cognomeUtente").val(this.surname);
		$("#passwordUtente").val(this.password);
		$("#idLogin").val(this.login);
		$("#ruoloUtente").val(this.role);
		$("#"+ this.id_button).text(this.textButton);
		console.log("loaded");
		console.log($("#"+ this.id_button));


	}
	caseCreate(utenti){
		console.log(this.id_button);
		$("#"+ this.id_button).on("click",function(){
			
			console.log($("#"+ this.id_button));
//			var nameInput = $("#nomeUtente").val();
//			var surnameInput = $("#cognomeUtente").val();
//			var roleInput = $("#ruoloUtente").val();
//			var passwordInput = $("#passwordUtente").val();
//			var loginInput = $("#idLogin").val();
//			
////funzione controllo lato server
//
//				$.get({
//					url: "ServletAggiungiUtente",
//					data : {'nomeUtente=' : nameInput ,
//							'cognomeUtente': surnameInput ,
//							'userId' : loginInput ,
//							'passwordUtente' : passwordInput , 
//							'ruoloUtente' : roleInput }
//				})
//				.done(function(data){
////					$("#ModalAddUser").modal('hide');
//					showSuccessText("Utente agiunto con successo",$("#nomeUtente").parent());
//					(location.reload(),3000);
//				});
		});
	}
}
