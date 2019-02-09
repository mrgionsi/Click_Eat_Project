function concatIngredienti(lista){
	var result = "";
	lista.forEach(item =>{
		result += item.nomeIngrediente +",";
	});
	return result;
}