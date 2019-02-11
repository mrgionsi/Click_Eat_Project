var name = ""; 
var pwd = "";

$("#btn-login").click(function() {
	if(checkParameter())
	{
		callApi("ServletLogin");
	}
});


function checkParameter(){
	name = $("#username").val() != null? $("#username").val() : false;
	pwd = $("#password").val() != null? $("#password").val() : false;
	if(name && pwd){
		console.log(name,pwd);
		return true;
	}else 
	{
		return false;
	}
}


$("input").click(function(){
	$(".row-error").remove();
})
function callApi(apiName){

	$.post(apiName,
			{
		"idLogin":name,
		"passwordUtente":pwd},
		function(data, statusCode)
	{
//			
//			console.log(statusCode);
//			if(status==200){
//
//				console.log("butto su login");
//
//				window.location.href = "./login.jsp";
//
//			}else{
//				if($(".row-error").length > 0 )
//				{
//				}
//				else
//				{
//					console.log("Error");
//					var div = document.createElement("div");
//					$(div).addClass("row text-center row-error");
//					var texterror = document.createElement("span");
//					$(texterror).addClass("error col-12 text-center");
//					$(texterror).text("Errore username e/o password");
//					$(div).append(texterror);
//					$(".login-form").append(div);
//				}
//			}
		}).fail(function(jqXHR){
			
			if(jqXHR.status==500){
				$("#div-error").remove();
				var div = document.createElement("div");
				$(div).addClass("row text-center row-error");
				$(div).attr("id", "div-error");
				var texterror = document.createElement("span");
				$(texterror).addClass("error col-12 text-center");
				$(texterror).text("Errore di rete");
				$(div).append(texterror);
				$(".login-form").append(div);
			}
			
			if(jqXHR.status==400){
				$("#div-error").remove();
				var div = document.createElement("div");
				$(div).addClass("row text-center row-error");
				$(div).attr("id", "div-error");
				var texterror = document.createElement("span");
				$(texterror).addClass("error col-12 text-center");
				$(texterror).text("Errore username e/o password");
				$(div).append(texterror);
				$(".login-form").append(div);
			}
		}).done(function(){
			
			console.log("butto su login");

			window.location.href = "./login.jsp";
		})
		
		;
}