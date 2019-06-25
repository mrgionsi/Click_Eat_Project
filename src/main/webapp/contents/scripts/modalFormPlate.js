class ModalPlate{
	constructor(title,textButton,id_button, idPiatto, name, category, price, list)

	{
		this.idPiatto = idPiatto;
		this.title = title;
		this.textButton = textButton;
		this.name = name;
		this.category = category;
		this.price = price;
		this.list = function(){
			var result= "";
			if(list!=null){
				list.forEach(function(e){
					result += e.nomeIngrediente + ", ";
				});
				return result;
			}
		};


		this.id_button = id_button;

		this.onLoad();
	}

	onLoad(){
		$("#ModalAddPlateTitle").text(this.title);
		$(".btn-modal[data-type='"+ this.id_button+"']").attr("id",this.id_button);
		$("#nomePiatto").val(this.name);
		$("#categoriaPiatto").val(this.category);
		$("#prezzoPiatto").val(this.price);
		$("#listaIngredienti").val(this.list);
		$("#"+ this.id_button).text(this.textButton);
		
		var that = this;
		
		$("#"+ this.id_button ).click(function(){
			$("#" + that.id_button).text("Invio...");
			$("#"+ that.id_button).prop("disabled", true);
		});

	}
	caseCreate(piatti){
		$("#"+ this.id_button).click(function(){


			
		var nameInput = $("#nomePiatto").val();
		var categoryInput = $("#categoriaPiatto").val();
		var priceInput = $("#prezzoPiatto").val();
		var listInput = $("#listaIngredienti").val();
	
			
//			funzione controllo lato server

			$.get({
				url: "ServletAggiungiPiatto",
				data : {'nomePiatto' : nameInput ,
					'categoriaPiatto': categoryInput ,
					'prezzoPiatto' : priceInput ,
					'listaIngredienti' : listInput }
			})
			.done(function(data){
//				$("#ModalAddPlate").modal('hide');
				$("#"+this.id_button).text("Salvato");
				$("#"+this.id_button).prop("disabled", true);
				showSuccessText("Piatto aggiunto con successo",$("#nomePiatto").parent());
				setTimeout(function () { location.reload(1); }, 2500);
			}).
			fail(function(data){
				
				$("#"+this.id_button).text(textButton);
				$("#"+this.id_button).prop("disabled", false);
				showErrorText("Piatto NON aggiunto",$("#nomePiatto").parent());
				
				
				

			});
		});
	}

	caseUpdate(piatti){
		$("#"+this.id_button).click(function(e){
			e.preventDefault();

			var nameInput = $("#nomePiatto").val();
			var categoryInput = $("#categoriaPiatto").val();
			var priceInput = $("#prezzoPiatto").val();
			var listInput = $("#listaIngredienti").val();
			
			$.get({
				url: "ServletModificaPiatto",
				data : {'nomePiatto' : nameInput ,
					'categoriaPiatto': categoryInput ,
					'prezzoPiatto' : priceInput ,
					'idPiatto' : id,
					'listaIngredienti' : listInput }
			})
			.done(function(data){
				//					$("#ModalAddUser").modal('hide');
				showSuccessText("Piatto modificato con successo",$("#nomePiatto").parent());
				setTimeout(function () { location.reload(1); }, 2500);
			}).
			fail(function(data){
				
				setTimeout(function () { location.reload(1); }, 2500);
				
				
				

			});

			e.stopPropagation();

		});
	}
}
