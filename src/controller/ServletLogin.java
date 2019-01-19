package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.ManagerUtente;
import model.BeanUtente;


@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletLogin() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idLogin = request.getParameter("idLogin");
		String passwordUtente = request.getParameter("passwordUtente");
		BeanUtente utente = null;

		try {	
			ManagerUtente utenteManager = new ManagerUtente();

			utente = utenteManager.valoriLogin(idLogin, passwordUtente);

			if(utente == null) {
				request.setAttribute("", true); //attributo da inserire, utile per gestire casi di login errato
				RequestDispatcher rq = request.getRequestDispatcher(""); //jsp da inserire
				rq.forward(request, response);
			}
			else {

				if(utente.getRuoloUtente() == "amministratore") {
					RequestDispatcher rq = request.getRequestDispatcher("./homeAmministratore.jsp"); //jsp da inserire
					rq.forward(request, response);
				}
				else if(utente.getRuoloUtente() == "cameriere") {
					RequestDispatcher rq = request.getRequestDispatcher("./homeCameriere.jsp"); //jsp da inserire
					rq.forward(request, response);
				}
				else if(utente.getRuoloUtente() == "cassiere") {
					RequestDispatcher rq = request.getRequestDispatcher("./homeCassiere.jsp"); //jsp da inserire
					rq.forward(request, response);
				}
				
			}
		}catch(Exception e){
			request.setAttribute("exception", e);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("./infopages/error.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
