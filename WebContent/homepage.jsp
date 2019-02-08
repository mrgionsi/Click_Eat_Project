<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="./parts/head.jsp"%>
<title>Benvenuto 
<% 
		String name = "ospite";
		name = request.getParameter("cookie");
		if(name!="ospite" || name!=null)
		{
		
			name = request.getParameter("cookie");
		}

%>
<%=name %>
</title>
</head>
<body>
<%
		if(role==null /*|| role !="amministratore" || role !="Amministratore" || role != "cameriere" || role != "Cameriere" */){
           
				request.getRequestDispatcher("./parts/noauth.jsp").forward(request, response);

        	   
           }
	%>
	<div class="container-fluid">
		<%@include file="./parts/navbar.jsp"%>
		<div class="row mt-5" id="tablesList"></div>
		<!-- 	<script type="text/javascript">tablesList();</script> questa è una caagata in mezzo alla stanza -->




	
	</div>
	
<script src="./contents/scripts/homepage.js"></script>
</body>
</html>
