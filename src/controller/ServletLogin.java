/* class: ServletLogin
 * author: AndreaCupito / LucaAmoriello
 * version: 1.0
 * 
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		BeanUtente utente = new BeanUtente();
		System.out.println("idlogin     " + idLogin);
		HttpSession session = request.getSession();

		try {	
			ManagerUtente utenteManager = new ManagerUtente();
			
			utente = utenteManager.valoriLogin(idLogin, passwordUtente);
			utente.getRuoloUtente();
			System.out.println("ruolo"+utente.getRuoloUtente());

	        	RequestDispatcher requestDispatcher;
    			session.setAttribute("BeanUtente", utente);
		
	    		if(utente.getRuoloUtente().equalsIgnoreCase("amministratore")) {
	    			response.sendRedirect(request.getContextPath() + "/homepageamministratore.jsp");

//					requestDispatcher = request.getRequestDispatcher("./homepageamministratore.jsp");
//					requestDispatcher.forward(request, response);
					System.out.println("Sono un admin");
					
	    		}
	    		else if(utente.getRuoloUtente().equalsIgnoreCase("cassiere")||utente.getRuoloUtente().equalsIgnoreCase("cameriere")){
	    			response.sendRedirect(request.getContextPath() + "/homepage.jsp");
	    			
//	    			requestDispatcher = request.getRequestDispatcher("./homepage.jsp");
//					requestDispatcher.forward(request, response);

					System.out.println("Sono uno schiavo");
	    		}else {
	    			requestDispatcher = request.getRequestDispatcher("./parts/noauth.jsp");
					requestDispatcher.forward(request, response);

					System.out.println("Sono uno stronzo");
	    		}
				
				
		}catch(Exception e){
			e.printStackTrace();

		}
	
	}

}
