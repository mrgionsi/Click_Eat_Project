/*class: ManagerPiatto
 * author: AndreaCupito / LucaAmoriello
 * version: 1.0
 * classe utile per la gestione della classe BeanPiatto
 */
package it.lucaamoriello.clickeat.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import it.lucaamoriello.clickeat.connessione.ConnectionPool;
import it.lucaamoriello.clickeat.model.BeanIngrediente;
import it.lucaamoriello.clickeat.model.BeanPiatto;

public class ManagerPiatto {
	Connection conn =  null;
	PreparedStatement ps = null;
	
	/*metodo utile per ottenere i dettagli di un singolo piatto, specificato tramite l'id
	 * @params idPiatto, identificativo univoco della singola entità Piatto
	 * @return BeanPiatto != null se il piatto è stato trovato nel DB
	 */
	public synchronized BeanPiatto ottieniPiatto(int idPiatto) {

		
		List<String> listIngredienti = new LinkedList<String>();
		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("SELECT * FROM v_DescPiattiCompleti where idPiatto = ?");
			ps = conn.prepareStatement(sqlString);
			ps.setInt(1, idPiatto);


			ResultSet res = ps.executeQuery();
			
			
			if(res.next()) {
				//trasformo la lista degli ingredienti, in arrivo come stringa in un oggetto beanIngrediente
				 listIngredienti = Arrays.asList(res.getString("lista_ingredienti").split(","));
			    ArrayList<BeanIngrediente> listaIngredienti = new ArrayList<BeanIngrediente>();

			
				listIngredienti.forEach(element -> {
					listaIngredienti.add(new BeanIngrediente(element));
				});
				//aggiungo al piatto la lista degli ingredienti
				return new BeanPiatto (res.getInt("idPiatto"), res.getString("nomePiatto"), res.getString("categoriaPiatto"), res.getFloat("prezzoPiatto"), listaIngredienti);
				
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
	
	/* metodo utile per ottenere la lista completa dei piatti registrati
	 * @return listaPiatti, ArrayList <BeanPiatto> con i Piatto presenti nel sistema
	 */
	public synchronized ArrayList<BeanPiatto> ottieniListaPiatti(){
		
		ArrayList<BeanPiatto> listaPiatti = new ArrayList<BeanPiatto>();
		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("SELECT * FROM v_DescPiattiCompleti");
			ps = conn.prepareStatement(sqlString);


			ResultSet res = ps.executeQuery();
			
			String nomePiatto = null;
			Float prezzoPiatto = null;
			String categoriaPiatto = null;
			Integer idPiatto = 0;
			String lista_ingredienti="";
			while(res.next()) {
				
				nomePiatto = res.getString("nomePiatto");
				prezzoPiatto = res.getFloat("prezzoPiatto");
				categoriaPiatto = res.getString("categoriaPiatto");
				idPiatto = res.getInt("idPiatto");
				lista_ingredienti= res.getString("lista_ingredienti");


				listaPiatti.add(new BeanPiatto(idPiatto, nomePiatto, categoriaPiatto, prezzoPiatto, lista_ingredienti));

			}
			
			return listaPiatti;
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
	
	
	/*metodo utile per ottenere la lsita di ingredienti presenti in un Piatto
	 * @params idPiatto, identificativo univoco della singola entità Piatto
	 * @return listaIngredienti, ArrayList<BeanInrgediente> contentente gli ingredienti del Piatto
	 */
	public synchronized ArrayList<BeanIngrediente> ingredientiNelPiatto(Integer idPiatto){
		Connection conn =  null;
		PreparedStatement ps = null;
		ArrayList<BeanIngrediente> listaIngredienti= new ArrayList<BeanIngrediente>();

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("SELECT idIngrediente FROM IngredientiPiatto WHERE idPiatto = ?");
			ps = conn.prepareStatement(sqlString);
			
			ps.setInt(1, idPiatto);


			ResultSet res = ps.executeQuery();
			ManagerIngrediente ingredienteManager = new ManagerIngrediente();
			BeanIngrediente ingrediente = new BeanIngrediente();	
			
			while(res.next()) {
				Integer idIngrediente = res.getInt("idIngrediente");
				ingrediente = ingredienteManager.ricercaPerId(idIngrediente);
				listaIngredienti.add(ingrediente);
			}
			if(listaIngredienti.isEmpty())
				return null;
			else
				return listaIngredienti;

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
	
	/*metodo utile per creare una nuova entità Piatto
	 * @params nomePiatto, nome del piatto da inserire; prezzoPiatto, prezzo del piatto da inserire; categoriaPiatto, categoria del piatto (es. primo, secondo, ecc)
	 * @return BeanPiatto != null se l'inserimento ha avuto successo
	 */
	public synchronized BeanPiatto creaPiatto(String nomePiatto, Float prezzoPiatto, String categoriaPiatto) {
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("INSERT INTO Piatto(nomePiatto, prezzoPiatto, categoriaPiatto) VALUES(?,?,?)");
			ps = conn.prepareStatement(sqlString);

			ps.setString(1, nomePiatto);
			ps.setFloat(2, prezzoPiatto);
			ps.setString(3, categoriaPiatto);
			
			int value = ps.executeUpdate();

			//Controllare solo se value non � 0 non va pi� bene visto che il nome del piatto deve essere unico
			//gi� modificata la struttura della tabella
			//Aggiunto al finally il ritorno del piatto
			if(value != 0) {

			}
		}
		catch(SQLException e){
			if(e.getErrorCode() == 1062) {
				//cos� sappiamo che non � stato veramente aggiunto

				//return new BeanUtente("duplicato","duplicato"); 
			}

		}
		finally {
			try {
				ps.close();
				ConnectionPool.releaseConnection(conn);
				//deve essere creato e ritornato qui perch� il piatto potrebbe gi� esistere o meno nel db
				//Ma a prescindere deve essere ritornato
				 
				return settaIdPiatto(new BeanPiatto(nomePiatto, categoriaPiatto, prezzoPiatto));
				
				//in questo modo ritorno un piatto con id settato e non ricevo puntatori nulla quando inserisco
				// nella tabella ingredienti nel piatto

			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	/*metodo utile per inserire ingredienti all'interno di un piatto registrato
	 * @params piatto, piatto da modificare
	 * @return true se la modifica ha avuto successo, false altriemnti
	 */
	public synchronized Boolean inserisciIngredientiNelPiatto(BeanPiatto piatto) {
		Connection conn =  null;
		PreparedStatement ps = null;
		Integer idPiatto = 0;
		Integer idIngrediente = 0;
		ArrayList<BeanIngrediente> listaIngredienti = new ArrayList<BeanIngrediente> ();
		
		try {
			conn = ConnectionPool.getConnection();
			idPiatto = piatto.getIdPiatto();
			listaIngredienti.addAll(piatto.getListaIngredienti());
			
				for(BeanIngrediente ingrediente: listaIngredienti) {
					
					idIngrediente = ingrediente.getIdIngrediente();
					String sqlString = new String("INSERT INTO IngredientiPiatto(idPiatto, idIngrediente) VALUES(?,?)");
					ps = conn.prepareStatement(sqlString);

					
					ps.setInt(1, idPiatto);
					ps.setInt(2, idIngrediente);
			
					int value = ps.executeUpdate();
					
					ps.close();

				}
			} catch(SQLException e){
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
	 * questo metodo serve a settare un idPiatto preso dal db subito dopo l'inserimento del piatto stesso
	 * @params BeanPiatto piatto, istanza del piatto inserito
	 * @return piatto.idPiatto != null
	 */
	private synchronized BeanPiatto settaIdPiatto(BeanPiatto piatto) {
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("SELECT idPiatto FROM Piatto WHERE nomePiatto = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setString(1, piatto.getNomePiatto());


			ResultSet res = ps.executeQuery();

			if(res.next()) {
				piatto.setIdPiatto(res.getInt("idPiatto"));
				
			}
		}
		catch(SQLException e){
			e.printStackTrace(); 


		}
		finally {
			try {
				ps.close();
				ConnectionPool.releaseConnection(conn);
				return piatto;


			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	/*
	 * metodo utile per eliminare un Piatto mediante il suo identificativo
	 * @params idPiatto, identificativo univoco della singola entità Piatto
	 * @return true se l'eliminazione ha avuto successo, false altrimenti
	 */
	public synchronized Boolean eliminaPiatto(Integer idPiatto){


		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("DELETE FROM Piatto WHERE idPiatto = ?");
			ps = conn.prepareStatement(sqlString);


			ps.setInt(1, idPiatto);

			int value = ps.executeUpdate();

			if(value != 0) {

				return eliminaPiattiFromIngredientiPiatto(idPiatto);
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
		return false;
	}

	/*
	 * metodo utile per eliminare il piatto dalla tabella IngredientiPiatto che associa ad un istanza di Piatto i relativi ingredienti
	 * @params idPiatto, identificativo univoco dell'entità Piatto
	 * @return true se l'eliminazione ha avuto successo, false altrimenti
	 */
	private synchronized Boolean eliminaPiattiFromIngredientiPiatto(int idPiatto){
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("DELETE FROM IngredientiPiatto WHERE idPiatto = ?");
			ps = conn.prepareStatement(sqlString);


			ps.setInt(1, idPiatto);

			int value = ps.executeUpdate();

			if(value != 0) {
				return true;
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
		return false;
	}

	public synchronized boolean InserisciPiattoIntoOrdinazione(int idPiatto, int numeroOrdinazione,int quantita) {

		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("INSERT INTO PiattiOrdinazioni(idPiatto, numeroOrdinazione, quantitaPiatto) VALUES(?,?,?)");
			ps = conn.prepareStatement(sqlString);

			ps.setInt(1, idPiatto);
			ps.setInt(2, numeroOrdinazione);
			ps.setInt(3, quantita);


			int value = ps.executeUpdate();

			if(value != 0) {
				return true;
			}
		}
		catch(SQLException e){

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
		return false;
	}
	
	public synchronized boolean UpdateQtaIntoOrdinazione(int idPiatto, int numeroOrdinazione,int quantita) {


		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("Update PiattiOrdinazioni SET quantitaPiatto = ? WHERE idPiatto = ? AND numeroOrdinazione = ?");
			ps = conn.prepareStatement(sqlString);
		
			ps.setInt(1, quantita);
			ps.setInt(2, idPiatto);
			ps.setInt(3, numeroOrdinazione);


			int value = ps.executeUpdate();

			if(value != 0) {
				return true;
			}
		}
		catch(SQLException e){

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
		return false;
	}

	public boolean eliminaPiattoIntoOrdinazione(Integer idPiatto, int ordinazione) {
		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("delete from PiattiOrdinazioni WHERE idPiatto = ? AND numeroOrdinazione = ?");
			ps = conn.prepareStatement(sqlString);
		
			ps.setInt(1, idPiatto);
			ps.setInt(2, ordinazione);


			int value = ps.executeUpdate();

			if(value != 0) {
				return true;
			}
		}
		catch(SQLException e){

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
		return false;
		
	}
	

}
