package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		RequestDispatcher rq = null;
		String redirectPage = "";
		
		System.out.println("idlogin     " + idLogin);
		try {	
			ManagerUtente utenteManager = new ManagerUtente();
			
			utente = utenteManager.valoriLogin(idLogin, passwordUtente);
			if(utente == null) {
				/*request.setAttribute("data", true); //attributo da inserire, utile per gestire casi di login errato
				rq = request.getRequestDispatcher(""); //jsp da inserire
				rq.forward(request, response);
				*/
	    		redirectPage = "/login.jsp";

			}
			else {
				/*switch(utente.getRuoloUtente().toLowerCase()) {
				case  "amministratore":
					 rq = request.getRequestDispatcher("./homeAmministratore.jsp"); //jsp da inserire
						rq.forward(request, response);

				break;
				case "cameriere":
					 rq = request.getRequestDispatcher("./homeCameriere.jsp"); //jsp da inserire

					break;
				case "cassiere":
					 rq = request.getRequestDispatcher("./homeCassiere.jsp"); //jsp da inserire
					break;
				}
				*/
				Cookie cookie = new Cookie("username",utente.getNomeUtente());
	        	Cookie cookierole = new Cookie("role",utente.getRuoloUtente());
	        	System.out.println(cookie);
	        	cookie.setMaxAge(3600);
	        	cookierole.setMaxAge(3600);
	        	
	    		response.addCookie(cookie);
	    		response.addCookie(cookierole);
	    		redirectPage = "/homepage.jsp";
			}
		}catch(Exception e){
    		redirectPage = "/login.jsp";

		}finally {
				response.getWriter().print(redirectPage);
				response.sendRedirect(request.getContextPath() + redirectPage);
		}
		return;
	}

}
