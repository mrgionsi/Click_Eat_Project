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
//			
//			//$("#btn-close").remove();
//			$(".modal-footer").append(spinner);
			
			$(".modal-footer").remove();

			
			
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
				showSuccessText("Piatto aggiunto con successo",$("#nomePiatto").parent());
				setTimeout(function () { location.reload(1); }, 2500);
			}).
			fail(function(data){
				
				var button = document.createElement("button");
				$(button).addClass("btn btn-danger");
				$("#spinner-loading").remove();

				$(button).text("Fallito");
				
				$(".modal-footer").append(button);
				setTimeout(function () { location.reload(1); }, 2500);
				
				
				

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
