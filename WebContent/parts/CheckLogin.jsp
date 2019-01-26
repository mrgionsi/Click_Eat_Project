<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!-- Questa jsp controlla se l'utente è loggato o meno. L'attributo in sessione adminRoles viene settato nella ServletLogin:
	true se l'utente è loggato, false otherwise.
	Questa jsp viene richiamata nella navbar, per adesso. -->
<% String uri = request.getRequestURI();

String pageName = uri.substring(uri.lastIndexOf("/")+1); 


Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");%>

<%
	if((adminRoles == null)|| (!adminRoles.booleanValue()))
	{ 
		if((!pageName.equals("login.jsp")))
			 response.sendRedirect("./login.jsp");
	 }
	%>