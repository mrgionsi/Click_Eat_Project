class TablewithCrudButtons extends Table{
	//column:contiene un array di elementi di questo tipo {column: "idPiatto",nome:"id Piatto",show:false}
						//column corrisponde al nome del campo nell'array fields
						//nome corrisponde al nome da scrivere al posto del nome del campo nell'array fields
						//show può essere true o false, (DEFAULT TRUE) per specificare se mostrare o meno la colonna
	//fields: array di elementi
	constructor(fields,id,column){
		this.fields = fields;
		this.column_name = column ||  Object.getOwnPropertyNames(fields[0]);
		this.column = this._setColumn();
		this.id = "table-"+id;
	}
	//verifico se è stato specificato per la colonna il nome e lo show. 
	//se gli viene passato column al costruttore prende i valori da lì, altrimenti mostra, di default, 
	//tutte le colonne e il nome colonna = al nome del campo nell'array
	_setColumn()	{
		var _ = this;
		var reloadcolumn; 
		var finalcolumn=[];
		//prendo tutti i nomi dei campi dell'array
		var col = Object.getOwnPropertyNames(_.fields[0]);
		//per ogni campo
		col.forEach(function(c){
			//creo il campo column
			var che = _.column_name.filter( ce=>(ce.column === c ));
			var check = che[0];
			//se non esiste, setto il nome uguale al nome del campo e lo rendo visibile
			if(check === undefined || typeof check === undefined) {
			var reloadcolumn=  {
						"column": c,
						 "nome":c,
						 "show":true
				}
			}else{
			var reloadcolumn= {
					"column":  c,
					"nome": check.nome || check.column ||  c,
					"show": check.show  || false
			}
			}
			finalcolumn.push(reloadcolumn);
		})
		return finalcolumn;
	}
	createTable()
	{
		var col_name = this.column_name;
		var table = document.createElement("table");
		$(table).addClass("table table-striped table-bordered");
		$(table).attr("id",this.id);
		var thead = document.createElement("thead");
		var tr 	  = document.createElement("tr");
		this.column.forEach(function(c){

			if(c.show)
			{
				var th = document.createElement("th");
				$(th).text(c.nome);
			}

			$(tr).append(th);

		})
		$(thead).append(tr);
		var col = this.column;
		var tbody = document.createElement("tbody");
		this.fields.forEach(function(f){
			var tr = document.createElement("tr");
			
			col.forEach(function(c){
				if(c.show)
				{
					$(tr).attr("data-"+c.column,f[c.column]);
					var td = document.createElement("td");
					$(td).text(f[c.column]);
					$(tr).append(td);

					$(tbody).append(tr);
				}
			});
		});
			$(table).append(thead);
			$(table).append(tbody);
			return table;
		}


	}