class TablewithCrudButtons extends Table{
	//column:contiene un array di elementi di questo tipo {column: "idPiatto",nome:"id Piatto",show:false}
						//column corrisponde al nome del campo nell'array fields
						//nome corrisponde al nome da scrivere al posto del nome del campo nell'array fields
						//show può essere true o false, (DEFAULT TRUE) per specificare se mostrare o meno la colonna
	//fields: array di elementi


	createTable(howToAppend)
	{
		var _ = this;
		var col_name = this.column_name;
		var table = document.createElement("table");

		$(table).addClass("table table-sm table-bordered");
		//$(table).addClass("table table-striped table-bordered");
		$(table).attr("id",this.id);
		var thead = document.createElement("thead");
		
		var tr 	  = document.createElement("tr");
		this.column.forEach(function(c){
			var th = document.createElement("th");
			$(th).text(c.nome);
			if(!(c.show))
			{
				$(th).hide();
			}

			$(tr).append(th);

		})
		$(thead).append(tr);
		var col = this.column;
		var tbody = document.createElement("tbody");
		this.fields.forEach(function(f){
			var tr = document.createElement("tr");
			
			col.forEach(function(c){
				$(tr).attr("data-"+c.column,f[c.column]);
				var td = document.createElement("td");
				$(td).attr("data-field",c.column);
				$(td).text(f[c.column]);
				$(tr).append(td);
				if(!(c.show))
				{
					$(td).hide();
				}
			});
			var tdimage = document.createElement("td");
			

			switch(_.addremove)
				{
				case "add":
					$(table).addClass("table table-striped table-bordered");

					var divimg = document.createElement("div");
					$(divimg).attr("id","add-to-ordering");
					var add = document.createElement("img");
					//$(add).attr("id","add-to-ordering");
					$(add).addClass("btn-rowtable-ordering btn-add");
					$(add).attr("src","./contents/images/add-button.png");
					$(divimg).append(add);
					$(tdimage).append(divimg);
					$(tr).append(tdimage);
					break;
				case "remove":
					$(table).addClass("table table-sm table-bordered");
					$(thead).addClass("thead-light");
					
					var remove = document.createElement("img");
					$(remove).attr("id","remove-from-ordering");
					$(remove).addClass("btn-rowtable-ordering btn-remove");
					$(remove).attr("src","./contents/images/remove-button.png");
					$(tdimage).append(remove);
					$(tr).append(tdimage);
					
					//qui gli viene detto di aggiungere al click la funzione di rimozione, e richiama ricorsivamente
					//la creazione della tabella
					$(remove).click(function(){
						var element = {"nomePiatto" : $(this).parent().parent().data("nomepiatto"),
								   //"prezzoPiatto" : $(this).parent().parent().data("prezzopiatto"),
							       "quantita" :  $(this).parent().parent().data("quantita")};
						//in questa funzione viene controllato se il campo deve essere eliminato o sottratta la quantità
						elementsOrder.isToRemove(element);
						//elimino la tabella
						$(howToAppend).children().remove();
						//creo la nuova tabella ricorsivamente, con i nuovi elementi
						$(howToAppend).append(_.createTable(howToAppend));
					});
						
					
					
					break;
				}

			$(tbody).append(tr);
		});
		
			$(table).append(thead);
			$(table).append(tbody);
			return table;
		}


	}