<%@ page import="it.lucaamoriello.clickeat.model.BeanUtente" %>
<% 	 
	String username = null;
	String role = null;

	BeanUtente utente = (BeanUtente) session.getAttribute("BeanUtente");
	if(utente!=null){
		username = utente.getIdLogin();
		role = utente.getRuoloUtente();
	}
%>