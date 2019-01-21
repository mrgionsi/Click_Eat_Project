<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<% String uri = request.getRequestURI();

String pageName = uri.substring(uri.lastIndexOf("/")+1); 


Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");%>

<%
	if((adminRoles == null)|| (!adminRoles.booleanValue()))
	{ 
		if(pageName.equals("login.jsp"))
		{
			
		}else response.sendRedirect("./login.jsp");
	 }
	%>