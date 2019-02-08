class ModalTable{
	constructor(title,textButton,id_button, name, category, price, list)
	{
		this.title = title;
		this.textButton = textButton;
		this.name = name;
		this.category = category;
		this.price = price;
		this.list = list;
		this.id_button = id_button;

		this.onLoad();
	}
	
	onLoad(){
		$("#ModalAddPlateTitle").text(this.title);
		$(".btn-modal[data-type='"+ this.id_button+"']").attr("id",this.id_button);
		$("#nomePiatto").val(this.name);
		$("#categoriaPiatto").val(this.surname);
		$("#prezzoPiatto").val(this.password);
		$("#listaIngredienti").val(this.login);
		$("#"+ this.id_button).text(this.textButton);
		console.log("plate on load");
		console.log($("#"+ this.id_button));


	}
	caseCreate(piatti){
		console.log($("#"+this.id_button));
		$("#"+ this.id_button).click(function(){
			console.log($("#"+ this.id_button));
			var nameInput = $("#nomePiatto").val();
			var categoryInput = $("#categoriaPiatto").val();
			var priceInput = $("#prezzoPiatto").val();
			var listInput = $("#listaIngredienti").val();
			
//funzione controllo lato server

				$.get({
					url: "ServletAggiungiPiatto",
					data : {'nomePiatto' : nameInput ,
							'categoriaPiatto': categoryInput ,
							'prezzoPiatto' : priceInput ,
							'listaIngredienti' : listInput }
				})
				.done(function(data){
//					$("#ModalAddUser").modal('hide');
					showSuccessText("Piatto aggiunto con successo",$("#nomePiatto").parent());
					(location.reload(),3000);
				});
		});
	}
	
	caseUpdate(piatti){
		$("#"+this.id_button).click(function(e){
				e.preventDefault();
			
				$.get({
					url: "ServletModificaPiatto",
					data : {'nomePiatto' : nameInput ,
							'categoriaPiatto': categoryInput ,
							'prezzoPiatto' : priceInput ,
							'listaIngredienti' : listInput }
				})
				.done(function(data){
//					$("#ModalAddUser").modal('hide');
					showSuccessText("Piatto modificato con successo",$("#nomePiatto").parent());
					(location.reload(),3000);
				});
			
			e.stopPropagation();

		});
	}
}
