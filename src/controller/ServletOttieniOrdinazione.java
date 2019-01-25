package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import manager.ManagerOrdinazione;


/**
 * Servlet implementation class ServletOttieniOrdinazione
 */
@WebServlet("/ServletOttieniOrdinazione")
public class ServletOttieniOrdinazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletOttieniOrdinazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		int numeroTavolo = Integer.parseInt(request.getParameter("numeroTavolo"));
		
		PrintWriter out = response.getWriter();
			
		ManagerOrdinazione manOrdinazione = new ManagerOrdinazione();
		
		Gson gson = new Gson();
		
		//scusate se è brutto
		out.print(gson.toJson(manOrdinazione.ottieniOrdinazione(numeroTavolo))); //passo l'ordinazione alla response
		}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
