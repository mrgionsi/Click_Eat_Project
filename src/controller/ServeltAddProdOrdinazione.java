package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import manager.ManagerOrdinazione;
import manager.ManagerPiatto;
import manager.ManagerTavolo;
import model.BeanOrdinazione;
import model.BeanPiatto;
import model.EFACT_BeanPiatto;
import model.ProdottiOrdinati;

/**
 * Servlet implementation class ServeltCreaNuovaOrdinazione
 */
@WebServlet("/ServeltAddProdOrdinazione")
public class ServeltAddProdOrdinazione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServeltAddProdOrdinazione() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManagerPiatto mp = new  ManagerPiatto();
		ManagerTavolo manTavolo = new ManagerTavolo();
		ManagerOrdinazione mO = new ManagerOrdinazione();
		BeanOrdinazione bO = new BeanOrdinazione();
		String JsonProducts = request.getParameter("ProdottiOrdinati");
		List<ProdottiOrdinati> listaprodotti = null;
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<EFACT_BeanPiatto> nuoviprodotti = new ArrayList<EFACT_BeanPiatto>() ;

		try {
			listaprodotti = mapper.readValue(JsonProducts,new TypeReference<List<ProdottiOrdinati>>(){});
			listaprodotti.forEach( prod ->{
				nuoviprodotti.add(new EFACT_BeanPiatto(prod.getIdPiatto(),prod.getQuantita()));
			});
		}catch(Exception e) {
			e.getStackTrace();
		}
		

		int numeroTavolo = Integer.parseInt(request.getParameter("numeroTavolo"));
		int ordinazione = Integer.parseInt(request.getParameter("numeroOrdinazione"));
		if(ordinazione != 0)
		{

			bO = mO.ottieniOrdinazione(numeroTavolo);
			ArrayList<BeanPiatto> ListaPiattiPrecedenti = bO.getListaPiatti();
			
			for(EFACT_BeanPiatto bP : nuoviprodotti)
			{
				if(ListaPiattiPrecedenti.contains(bP)) {
					mp.UpdateQtaIntoOrdinazione(bP.getIdPiatto(), ordinazione, bP.getQuantita());
					ListaPiattiPrecedenti.remove(bP);
				}
				else if(!ListaPiattiPrecedenti.contains(bP))
				{
					mp.InserisciPiattoIntoOrdinazione(bP.getIdPiatto(), ordinazione, bP.getQuantita());
				}
			}
			
			
			ListaPiattiPrecedenti.forEach( prod ->{
				mp.eliminaPiattoIntoOrdinazione(prod.getIdPiatto(), ordinazione);
				System.out.println(prod.getNomePiatto());
				});
			
			
		}else
		{
			manTavolo.setOccupato(numeroTavolo);
			int numberOrder = manTavolo.getOrdinazioneDiTavolo(numeroTavolo);
			
			
			try {

				for(ProdottiOrdinati e : listaprodotti)
				{
					System.out.println(" idPiatto === " + e.getIdPiatto() + "\n quantitaPiatto === " + e.getQuantita());

					mp.InserisciPiattoIntoOrdinazione(e.getIdPiatto(), numberOrder, e.getQuantita());
				}

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}






	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
