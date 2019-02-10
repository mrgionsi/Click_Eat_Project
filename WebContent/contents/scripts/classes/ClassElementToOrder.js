/**
 * 
 */class elementsToOrder{
	constructor(elements){
		this.elements = elements || [];
	}
	checkifExist(element)
	{
		var exists =0 ;
		if(this.elements.length == 0){
			this.elements.push(element);
			return;
		}else
		{
			var _ = this;
			this.elements.forEach(e =>{
				if(e.nomePiatto === element.nomePiatto)
				{
					exists = 1;
					var i = _.elements.indexOf(e);
			//		console.log(e);
					_.elements[i].quantita += 1;
					return true;
				}else if((!exists === 1))exists = 0;
			});
			//if(exists === 1) return;
			if(exists ===  0){
				//console.log(element);
				this.elements.push(element);	
				return;
			}


		}
		

	}
	isToRemove(element,that,tableOrdering,howToAppend)
	{
		var _ = this;
		this.elements.forEach(e =>{
			//console.log(e.nomePiatto === element.nomePiatto);
			if(e.nomePiatto === element.nomePiatto)
			{
				if(e.quantita > 1){

					var i = _.elements.indexOf(e);
					console.log(e);
					_.elements[i].quantita -= 1;
					console.log($(that));
					//$(that).append(tableOrdering.createTable(element));
					return;
				}else{
					this.elements.pop(element);	
					//console.log($(that).parent().parent());
					//$(that).parent().parent().remove();
					return;
				}
			}
		});
	}
}