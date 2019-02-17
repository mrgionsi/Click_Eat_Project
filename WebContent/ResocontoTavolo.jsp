<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
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
		</div>
		<div class="row">
		<div class="col-12">
		<div class="h1 text-center title">
		Conto tavolo n. <span id="ntavolo"> 0</span>
		</div>
		</div>
		</div>
<script src="./contents/scripts/ResocontoTavolo.js"></script>
</body>
</html>