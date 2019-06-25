/*Servlet: AddProdOrdinazione
 * author: Giovanni Pasquariello / LucaAmoriello
 * version: 1.0
 * classe utile per la gestione della classe BeanUtente
 */

package it.lucaamoriello.clickeat.controller;

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

import it.lucaamoriello.clickeat.manager.ManagerOrdinazione;
import it.lucaamoriello.clickeat.manager.ManagerPiatto;
import it.lucaamoriello.clickeat.manager.ManagerTavolo;
import it.lucaamoriello.clickeat.model.BeanOrdinazione;
import it.lucaamoriello.clickeat.model.BeanPiatto;
import it.lucaamoriello.clickeat.model.EFACT_BeanPiatto;
import it.lucaamoriello.clickeat.model.ProdottiOrdinati;

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
			// nella request viene inviato una lista di oggetti in formato JSON avente, id piatto e quantità
			//vengono trasformati in una lista di prodotti ordinati
			listaprodotti = mapper.readValue(JsonProducts,new TypeReference<List<ProdottiOrdinati>>(){});
			listaprodotti.forEach( prod ->{
				//trasformo la lista Da prodotti ordinati in una lista EFACT_BeanPiatto
				//la trasformo perchè successivamente utilizzo il metodo contains e gli oggetti devono essere della stessa classe
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
			//piatti già presenti nell'ordinazione
			ArrayList<BeanPiatto> ListaPiattiPrecedenti = bO.getListaPiatti();
			
			for(EFACT_BeanPiatto bP : nuoviprodotti)
			{
				//controllo se nei piatti già ordinati c'è già lo stesso piatto, lo aggiorno
				if(ListaPiattiPrecedenti.contains(bP)) {
					mp.UpdateQtaIntoOrdinazione(bP.getIdPiatto(), ordinazione, bP.getQuantita());
					//lo rimuovo dalla lista dei piatti già presenti
					ListaPiattiPrecedenti.remove(bP);
				}
				else if(!ListaPiattiPrecedenti.contains(bP))
				{
					//se un nuovo piatto non è presente nell'ordinazione, lo aggiungo
					mp.InserisciPiattoIntoOrdinazione(bP.getIdPiatto(), ordinazione, bP.getQuantita());
				}
			}
			
			//i piatti rimanenti in questa lista sono piatti ordinati precedentemente e poi cancellati, quindi devono essere eliminati
			ListaPiattiPrecedenti.forEach( prod ->{
				mp.eliminaPiattoIntoOrdinazione(prod.getIdPiatto(), ordinazione);
				System.out.println(prod.getNomePiatto());
				});
			
			
		}else
		{
			////creo una nuova ordinazione
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
