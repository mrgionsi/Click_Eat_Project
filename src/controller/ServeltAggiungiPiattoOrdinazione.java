/* class: ServletAggiungiPiattoOrdinazione
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

import manager.ManagerPiatto;

/**
 * Servlet implementation class ServeltAggiungiPiattoOrdinazione
 */
@WebServlet("/ServeltAggiungiPiattoOrdinazione")
public class ServeltAggiungiPiattoOrdinazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeltAggiungiPiattoOrdinazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idPiatto = Integer.parseInt(request.getParameter("idPiatto"));
		int numeroOrdinazione = Integer.parseInt(request.getParameter("numeroOrdinazione"));
		
		ManagerPiatto manPiatto = new ManagerPiatto();
		
		if(manPiatto.InserisciPiattoIntoOrdinazione(idPiatto, numeroOrdinazione)) {
			response.setStatus(200);
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
