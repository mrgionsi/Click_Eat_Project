/* class: ServletAggiungiPiatto
 * author: AndreaCupito / LucaAmoriello
 * version: 1.0
 * 
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.ManagerIngrediente;
import manager.ManagerPiatto;
import model.BeanIngrediente;
import model.BeanPiatto;

/**
 * Servlet implementation class ServletAggiungiPiatto
 */
@WebServlet("/ServletAggiungiPiatto")
public class ServletAggiungiPiatto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAggiungiPiatto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			System.out.println("-----------------------");
			System.out.println("Inizio metodo: doGet - Servlet: ControllerPiatto");
			System.out.println("-----------------------");
			
			BeanPiatto piatto = new BeanPiatto();
			BeanIngrediente ingrediente = new BeanIngrediente();
			ManagerPiatto piattoManager = new ManagerPiatto();
			ManagerIngrediente ingredienteManager = new ManagerIngrediente();
			ArrayList<BeanIngrediente> listaIngredienti = new ArrayList<BeanIngrediente>();
			
			String nomePiatto = request.getParameter("nomePiatto");
			String categoriaPiatto = request.getParameter("categoriaPiatto");
			String ingredienti = request.getParameter("listaIngredienti");
			String prezzo = request.getParameter("prezzoPiatto").replace(",", ".");
			Float prezzoPiatto = Float.parseFloat(prezzo);
			
			
			
			String[] parts = ingredienti.split(",");
			
			int i = 0;
			while(i<parts.length) {
				ingrediente = ingredienteManager.creaIngrediente(parts[i]);
				listaIngredienti.add(ingrediente);

				i++;
			}
			
			piatto = piattoManager.creaPiatto(nomePiatto, prezzoPiatto, categoriaPiatto);
			
			if(piatto!=null) {
				

				piatto.setListaIngredienti(listaIngredienti);
				


			}
			
			if(piatto.getListaIngredienti()!=null){

				piattoManager.inserisciIngredientiNelPiatto(piatto);
			}
			

			System.out.println("-----------------------");
			System.out.println("Fine Servlet: ControllerPiatto");
			System.out.println("-----------------------");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
