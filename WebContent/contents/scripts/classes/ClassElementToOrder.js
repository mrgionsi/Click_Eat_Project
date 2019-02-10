/**
 * 
 */class elementsToOrder{
	constructor(elements){
		this.elements = elements || [];
	}
	//controlla se esiste già l'elemento nella lista dei piatti selezionati
	checkifExist(element)
	{
		var exists =0 ;
		//se non c'è nessun piatto selezionato, lo inserisce senza alcun controllo
		if(this.elements.length == 0){
			this.elements.push(element);
			return;
		}else
		{
			var _ = this;
			//controlla se il piatto esiste già
			this.elements.forEach(e =>{
				if(e.nomePiatto === element.nomePiatto)
				{
					//se c'è incrementa la quantità
					exists = 1;
					var i = _.elements.indexOf(e);
					_.elements[i].quantita += 1;
					return true;
				}else if((!exists === 1))exists = 0; //questo perchè viene richiamata  più volte di seguito
			});
			if(exists ===  0){
				this.elements.push(element);	
				return;
			}


		}
		

	}
	// controlla se un elemento esiste già, per essere eliminato
	isToRemove(element)
	{
		var _ = this;
		this.elements.forEach(e =>{
			if(e.nomePiatto === element.nomePiatto)
			{
				//controlla se il piatto c'è, e verifica qui sotto se deve essere diminuita la quantita o eliminato 
				if(e.quantita > 1){

					var i = _.elements.indexOf(e);
					_.elements[i].quantita -= 1;
					return;
				}else{
					console.log("ELEMENTO DA ELIMINARE   " + element.nomePiatto);
					//elements.pop(element) non ha funzionato!!
					var index = this.elements.indexOf(e);
					if (index !== -1) this.elements.splice(index, 1);
					return;
				}
			}
		});
	}
}