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
			String prezzo = request.getParameter("prezzoPiatto");
			Float prezzoPiatto = Float.parseFloat(prezzo);
			
			String[] parts = ingredienti.split(",");
			
			int i = 0;
			while(i<parts.length) {
				System.out.println("Aggiungendo al db " + parts[i]);
				ingrediente = ingredienteManager.creaIngrediente(parts[i]);
				listaIngredienti.add(ingrediente);
				System.out.println(listaIngredienti.get(i).getNomeIngrediente());

				i++;
			}
			
			piatto = piattoManager.creaPiatto(nomePiatto, prezzoPiatto, categoriaPiatto);
			
			if(piatto!=null) {
				System.out.println("Piatto non nullo");
				System.out.println(piatto.getCategoriaPiatto());
				System.out.println(piatto.getNomePiatto());
				System.out.println(piatto.getPrezzoPiatto());
				System.out.println("Setto in locale la lista di ingredeienti");

				piatto.setListaIngredienti(listaIngredienti);
				


			}
			
			if(piatto.getListaIngredienti()!=null){
				System.out.println("Lista ingredienti non nullo");

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
