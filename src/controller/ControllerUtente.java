package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import manager.ManagerUtente;
import model.BeanUtente;
/* servlet Utente, si occupa della logica applicativa riguardante il dato Utente
 * author: Andrea Cupito
 * ogni operazione possibile verrà indicata con un numero intero (op):
 * ottieni listaUtenti: 1
 * aggiungere un utente al sistema: 2
 * eliminare un utente dal sistema: 3
 */
@WebServlet("/ControllerUtente")
public class ControllerUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "jsonfiles/";


	public ControllerUtente() {
		super();
	}

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String toGet = request.getParameter("op");
		System.out.println(toGet);

		System.out.println("-----------------------");
		System.out.println("Inizio metodo: doGet - Servlet: controllerUtente");
		System.out.println("-----------------------");

		if(toGet.equalsIgnoreCase("1")) {
			ManagerUtente utenteManager = new ManagerUtente();

			ArrayList<BeanUtente> listaUtenti = utenteManager.ottieniUtenti() ;
			String appPath = request.getServletContext().getRealPath("");
			String savePath = appPath + File.separator + SAVE_DIR;

			File f = new File(savePath +  "listaUtenti.json");

			if(f.exists() && !f.isDirectory()) { 
				System.out.println("Esiste");
				f.delete();
				f.createNewFile();	
			}
			else {
				System.out.println("non esiste");
				System.out.println("Creazione del file qui:" + f.getAbsolutePath());
				f.createNewFile();	
			}


			ObjectMapper mapper = new ObjectMapper();


			mapper.writeValue(f, listaUtenti);


			System.out.println("-----------------------");
			System.out.println("Fine metodo: doGet - Servlet: ControllerUtente");
			System.out.println("-----------------------");
		} else if(toGet.equalsIgnoreCase("2")) {

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
				BeanUtente utente = utenteManager.creaUtente(nomeUtente, passwordUtente, cognomeUtente, ruoloUtente, idLogin);

				/*if(utente.getIdLogin() != "duplicato" &&  utente.getPasswordUtente() != "duplicato" ) {
							RequestDispatcher rq = request.getRequestDispatcher("./infopages/success.jsp");
							rq.forward(request, response);
						}*/ //blocco da considerare per gesitire duplicati errati
				//else {
				//request.setAttribute("duplicato", true);
				
				//}
			}catch(Exception e) {
				request.setAttribute("exception", e);
				RequestDispatcher rq3 = request.getRequestDispatcher("");//jsp da inserire
				rq3.forward(request, response);
			}

		}
		else if(toGet.equalsIgnoreCase("3")) {

			Integer idUtente = Integer.parseInt(request.getParameter("idUtente"));

			try {
				ManagerUtente utenteManager = new ManagerUtente();
				utenteManager.eliminaUtente(idUtente);
				
			}catch(Exception e) {
				request.setAttribute("exception", e);
				RequestDispatcher rq3 = request.getRequestDispatcher("");//jsp da inserire
				rq3.forward(request, response);
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
