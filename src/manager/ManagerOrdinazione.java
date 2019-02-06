/*class: ManagerOrdinazione
 * author: AndreaCupito / LucaAmoriello
 * version: 1.0
 * classe utile per la gestione della classe BeanOrdinazione
 */
package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import connessione.ConnectionPool;
import model.BeanOrdinazione;
import model.BeanPiatto;

public class ManagerOrdinazione {
	
	/*
	 * metodo utile per la creazione di una nuova Ordinazione
	 * @return BeanOrdinazione != null
	 */
	public synchronized BeanOrdinazione creaOrdinazione() {
		Connection conn =  null;
		PreparedStatement ps = null;
		BeanOrdinazione ordine = new BeanOrdinazione();
		
		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("INSERT INTO Ordinazione() VALUES()");
			ps = conn.prepareStatement(sqlString);
			
			int value = ps.executeUpdate();
			
			if(value != 0) {
				System.out.println("Ordinazione creata con successo");
				return impostaIdEDataOrdine(ordine);

			}
		}
		catch(SQLException e){
			e.printStackTrace();

		}
		finally {
			try {
				ps.close();
				ConnectionPool.releaseConnection(conn);
				
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	/* metodo utile per settare id e data ad un'ordinazione già registrata
	 * @params ordine, ordinazione da inizializzare
	 * @return BeanOrdinazione.numeroOrdinazione != null, BeanOrdinazione.dataOrdinazione != null
	 */
	private synchronized BeanOrdinazione impostaIdEDataOrdine(BeanOrdinazione ordine) {
		Connection conn =  null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("SELECT * FROM Ordinazione");
			ps = conn.prepareStatement(sqlString);
			
			ResultSet res = ps.executeQuery();

			if(res.last()) {
				ordine.setNumeroOrdinazione(res.getInt("numeroOrdinazione"));
				ordine.setDataOrdine(res.getTimestamp("dataOrdine"));
				System.out.println("numero e data dell'ordine impostati");
				return ordine;

			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				ConnectionPool.releaseConnection(conn);

			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	/*
	 * metodo utile per ottenere l'ordinazione allegata ad un tavolo selezionato
	 * @params numerotavolo, numero del tavolo selezionato
	 * @return BeanOrdinazione ordine != null se l'ordine esiste, null altrimenti
	 */
	public synchronized BeanOrdinazione ottieniOrdinazione(Integer numeroTavolo) {
		
		Connection conn =  null;
		PreparedStatement ps = null;
		Integer numeroOrdine;
		Timestamp dataOrdine;
		//BeanPiatto piatto = new BeanPiatto();
		ManagerPiatto manPiatto = new ManagerPiatto();
		
		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("SELECT * FROM v_DescOrdinazioniComplete WHERE numeroTavolo = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setInt(1, numeroTavolo);
			
			ResultSet res = ps.executeQuery();
			BeanOrdinazione ordine = new BeanOrdinazione();
			ArrayList<BeanPiatto> listaPiatti = new ArrayList<BeanPiatto>();

			if(res.next()) {
				dataOrdine = res.getTimestamp("dataOrdine");
				numeroOrdine = res.getInt("numeroOrdinazione");
				ordine = new BeanOrdinazione(numeroOrdine, dataOrdine);
				listaPiatti.add(manPiatto.ottieniPiatto(res.getInt("idPiatto")));

				
			}
			
			while(res.next()) {
				
				listaPiatti.add(manPiatto.ottieniPiatto(res.getInt("idPiatto")));
				
			}
		
			ordine.setListaPiatti(listaPiatti);
			
			return ordine;
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				ConnectionPool.releaseConnection(conn);

			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
}
