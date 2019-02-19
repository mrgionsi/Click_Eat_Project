/* class: ServletLogin
 * author: Giovanni Pasquariello / LucaAmoriello
 * version: 1.0
 * 
 */
package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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
		BeanUtente utente;
		HttpSession session = request.getSession();
		
		try {	
			ManagerUtente utenteManager = new ManagerUtente();

			utente = utenteManager.valoriLogin(idLogin, passwordUtente);
			
			if(utente.getErrorCode()==1329) {

				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			
			if(utente.getErrorCode()==1613) {
				response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
				return;


			}
			if(utente.getErrorCode()==1329) {
				
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			}
			
			utente.getRuoloUtente();

    			session.setAttribute("BeanUtente", utente);
		
	    		if(utente.getRuoloUtente().equalsIgnoreCase("amministratore")) {
	    	
	    			response.sendRedirect(request.getContextPath() + "/homepageamministratore.jsp");
//					requestDispatcher = request.getRequestDispatcher("./homepageamministratore.jsp");
//					requestDispatcher.forward(request, response);
					
	    		}
	    		else if(utente.getRuoloUtente().equalsIgnoreCase("cassiere")||utente.getRuoloUtente().equalsIgnoreCase("cameriere")){
	    		
	    			response.sendRedirect(request.getContextPath() + "/homepage.jsp");
	    			
//	    			requestDispatcher = request.getRequestDispatcher("./homepage.jsp");
//					requestDispatcher.forward(request, response);
	    		}

				
				
		}
		catch(Exception e1) {
			e1.getStackTrace();

		}
			
	}

}
