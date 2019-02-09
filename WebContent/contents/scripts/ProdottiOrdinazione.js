
var f = 0;
class navLink
{
	//active : true o false, per settare su attivo un pills al caricamento
	//tabname: nome del tab, di solito uguale al namenav
	//namenav: il nome da visualizzare
	constructor(active,tabname,namenav){
		this.div_class=this.setClass(active);
		this.tab_class= this.setTab_Class(active);
		this.id = "pills-"+tabname;
		this.data_toggle= "pill";
		this.href="#pills-"+tabname;
		this.role="tab";
		this.aria_controls="pills-"+tabname;
		this.aria_selected=active;
		this.data_name=tabname;
		this.namenav = namenav;
	}

	setClass(active)
	{
		return active? "nav-link active" : "nav-link";
	}
	setTab_Class(active)
	{
		return active? "tab-pane fade show active" : "tab-pane fade";
	}
	createNavLink()
	{
		var a= document.createElement("a");
		$(a).addClass(this.div_class);
		$(a).attr("id",this.id+"-tab");
		$(a).attr("data-toggle",this.data_toggle);
		$(a).attr("href",this.href);
		$(a).attr("role",this.role);
		$(a).attr("aria-controls",this.aria_controls);
		$(a).attr("aria-selected",this.aria_selected);
		$(a).text(this.namenav);
		$(a).css("border","2px solid #007bff");
		return a;
	}
	createTabPanel(){
		var div = document.createElement("div");
		$(div).addClass(this.tab_class);
		$(div).attr("id",this.id);
		$(div).attr("role","tabpanel");
		$(div).attr("aria-labelledby",this.id +"-tab");
		$(div).attr("data-name",this.data_name);
		return div;
	}
}


$(document).ready(function(){
	createNavPills();
});


function createNavPills(){
	var howToAppendlink = $("#pills-tab");
	var howToAppendContent = $("#pills-tabContent");
	$.get("ServletGetAllPiatti",function(data,status)  {
		var products =JSON.parse(data);
		//transformo la lista di oggetti ingredienti in una lista di stringhe
		products.forEach(function(p){
			p.listaIngredienti = concatIngredienti(p.listaIngredienti);
		}) 

		var filter = Array.from(new Set(products.map(p => p.categoriaPiatto)))
		.map(categoriaPiatto =>{
			return categoriaPiatto;
		});

		console.log(filter);
		filter.forEach(function(e){;
		if(f == 0){
			var link = new navLink(true,e,e);
			f=1;
		}else
		{
			var link = new navLink(false,e,e);
		}
		var li = document.createElement("li");
		$(li).addClass("nav-item");
		$(li).append(link.createNavLink());
		$(howToAppendlink).append(li);
		$(howToAppendContent).append(link.createTabPanel());
		});
		splitProduct(products,navLink.id);

	});
}

function splitProduct(products){
	var categorie = Object.getOwnPropertyNames(products[0]);
	product.forEach(item =>{
		categorie.forEach(c =>
		{
			if(item.categoriaPiatto == c){
				
			}
		});

	});

	var table = new Table(products,"");
	console.log(table);
	//$("div[data-name='"+ p.categoriaPiatto +"']").append(createTable);
	$("div[data-name='Primi']").append(table.createTable());

}




