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

function callApi(apiName){

	$.post(apiName,{"idLogin":name,"passwordUtente":pwd},function(data)
			{
		console.log(data);
			});
}