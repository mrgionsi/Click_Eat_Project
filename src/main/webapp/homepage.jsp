<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="refresh" content="20">

<%@include file="./parts/head.jsp"%>
<title>Ordinazioni 
</title>
</head>
<body>
	<div class="container-fluid">
		<%@include file="./parts/navbar.jsp"%>
		<%
		
		if (utente == null  || role==null) {
			response.sendRedirect("./login.jsp");
			return;
		}
		
		
		%>
		<div class="row mt-5" id="tablesList"></div>
		<!-- 	<script type="text/javascript">tablesList();</script> questa è una caagata in mezzo alla stanza -->




	
	</div>
	
<script src="./contents/scripts/homepage.js"></script>
</body>
</html>
