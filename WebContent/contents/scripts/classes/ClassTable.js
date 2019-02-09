class Table{
	//column: colonne della tabella , opzionale, prenderà i nomi dei campi
	//fields: array di elementi
	constructor(fields,id,column){
		this.column = column || Object.getOwnPropertyNames(fields[0]);
		this.fields = fields;
		this.id = "table-"+id;
	}

	createTable()
	{
		console.log(this.column);
		console.log(this.fields);
		var table = document.createElement("table");
		$(table).addClass("table table-striped table-bordered");
		$(table).attr("id",this.id);
		var thead = document.createElement("thead");
		var tr 	  = document.createElement("tr");
		this.column.forEach(function(c){
			var th = document.createElement("th");
			$(th).text(c);
			$(tr).append(th);
		})
		$(thead).append(tr);
		var col = this.column;
		var tbody = document.createElement("tbody");
		this.fields.forEach(function(f){
			var tr = document.createElement("tr");
			col.forEach(function(c){
				var td = document.createElement("td");
				$(td).text(f[c]);
				$(tr).append(td);
			})
			$(tbody).append(tr);
		})
		$(table).append(thead);
		$(table).append(tbody);
		return table;
	}
}