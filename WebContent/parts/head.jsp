
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="./contents/library/bootstrap4.0/css/bootstrap.css">
<link rel="stylesheet" href="./contents/css/mystyle.css">

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->


<script src="./contents/library/jQuery.js"></script>
<script src="./contents/library/popper.min.js" ></script>
<script src="./contents/library/bootstrap4.0/js/bootstrap.min.js"></script>



<<<<<<< HEAD
=======
<%
    	String username= "ospite";
		String role = null;
    	Cookie[] cookies = request.getCookies();
   if(cookies!= null){
    for(Cookie c : cookies)
    {
    	if(c.getName().equals("username"))
    	{
    		
    		 username = c.getValue();
    	}
    	
    	if(c.getName().equals("role"))
    	{
    		
    		 role = c.getValue();
    	}
   	} 
   }
%>

>>>>>>> 2bdd352e9ba11c526f2f4b2ede212c34305b168f
