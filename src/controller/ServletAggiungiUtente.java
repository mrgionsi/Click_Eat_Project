/* class: ServletAggiungiUtente
 * author: AndreaCupito / LucaAmoriello
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

import manager.ManagerUtente;
import model.BeanUtente;

/**
 * Servlet implementation class ServletAggiungiUtente
 */
@WebServlet("/ServletAggiungiUtente")
public class ServletAggiungiUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAggiungiUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BeanUtente utente = new BeanUtente();
		System.out.println("Nuovo Utente");
		String nomeUtente = request.getParameter("nomeUtente");
		String cognomeUtente = request.getParameter("cognomeUtente");
		String ruoloUtente = request.getParameter("ruoloUtente");
		String passwordUtente = request.getParameter("passwordUtente");
		String idLogin = request.getParameter("userId");
		
		System.out.println(nomeUtente);

		System.out.println(cognomeUtente);

		System.out.println(ruoloUtente);

		System.out.println(idLogin);

		try {
			ManagerUtente utenteManager = new ManagerUtente();
			System.out.println("PROVO A CREARE UN UTENTE");
			utente = utenteManager.creaUtente(nomeUtente, passwordUtente, cognomeUtente, ruoloUtente, idLogin);

			System.out.print(utente.getErrorCode());
			if(utente.getErrorCode()==1062) {
				System.out.print("setto bad");

				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			}else {
				System.out.print("setto ok");

				response.setStatus(200);

			}
		}catch(Exception e) {

		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
