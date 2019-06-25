/*class: ManagerUtente
 * author: AndreaCupito / LucaAmoriello
 * version: 1.0
 * classe utile per la gestione della classe BeanUtente
 */
package it.lucaamoriello.clickeat.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.lucaamoriello.clickeat.connessione.ConnectionPool;
import it.lucaamoriello.clickeat.model.BeanUtente;

public class ManagerUtente {
	Connection conn =  null;
	PreparedStatement ps = null;

	
	/* metodo utile per registrare un nuovo utente sul sistema, con i parametri scelti dall'amministratore
	 * @params: nomeUtente, il nome dell'utente; cognomeUtente, il cognome dell'Utente; idLogin, username per l'accesso; passwordUtente, password per l'accesso; ruoloUtente, tuolo all'interno del sistema.
	 * @return: un BeanUtente con i params inseriti.
	 */
	public synchronized BeanUtente creaUtente(String nomeUtente, String passwordUtente, String cognomeUtente, String ruoloUtente, String idLogin){
		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("INSERT INTO Utente(nomeUtente, cognomeUtente, userId, passwordUtente, ruoloUtente) VALUES(?,?,?,?,?)");
			ps = conn.prepareStatement(sqlString);

			ps.setString(4, passwordUtente);
			ps.setString(1, nomeUtente);
			ps.setString(2, cognomeUtente);
			ps.setString(3, idLogin);
			ps.setString(5, ruoloUtente);

			int value = ps.executeUpdate();
			
			if(value!=0) {

				BeanUtente utente = new BeanUtente(nomeUtente, cognomeUtente, passwordUtente, ruoloUtente, idLogin);

				return utente;
			}
		}
		catch(SQLException e){

			if(e.getErrorCode() == 1062) {
				//esiste utente 

				return new BeanUtente(1062);
			}
			if(e.getErrorCode() == 1613) {
				//timeout connession
				return new BeanUtente(1613);

			}

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

	/*metodo utile per eliminare l'utente con l'username di appartenenza
	 * @params idLogin, username dell'utente
	 * @return true se l'eliminazione ha avuto successo, false altrimenti
	 */
	public synchronized boolean eliminaUtente(String idLogin){
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("DELETE FROM Utente WHERE userId = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setString(1,idLogin);

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

	/*metodo utile per ottenere la lsita degli Utenti presenti nel sistema
	 * @return lista degli Utenti
	 */
	public synchronized ArrayList<BeanUtente> ottieniUtenti(){ 

		ArrayList<BeanUtente> listaUtenti = new ArrayList<BeanUtente>();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT * FROM Utente");

			ResultSet res = ps.executeQuery();

			while(res.next()){
				Integer idUtente = res.getInt("idUtente");
				String nomeUtente = res.getString("nomeUtente");
				String cognomeUtente = res.getString("cognomeUtente");
				String idLogin = res.getString("userId");
				String passwordUtente= res.getString("passwordUtente");
				String ruoloUtente = res.getString("ruoloUtente");

				BeanUtente utente = new BeanUtente(nomeUtente, cognomeUtente, passwordUtente, ruoloUtente, idLogin);
				utente.setIdUtente(idUtente);
				listaUtenti.add(utente);
			}
			return listaUtenti;

		} catch (SQLException e) {

			e.printStackTrace();

		}finally{

			try {
				ps.close();
				ConnectionPool.releaseConnection(conn);

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return null;
	}

	/*metodo utile per confrontare i valori di login dati in input con quelli presenti nel DB 
	 * @params idLogin, username dell'utente; passwordUtente, password dell'Utente
	 * @return BeanUtente corrispondente ai parametri dati in inuput; se i parametri non combaciano ritorna null
	 */
	public synchronized BeanUtente valoriLogin(String idLogin, String passwordUtente){ 

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT * FROM Utente WHERE userId = ? AND passwordUtente = ?");
			ps.setString(1, idLogin);
			ps.setString(2, passwordUtente);

			ResultSet res = ps.executeQuery();

			// 4. Prendi il risultato
			if(res.next()){
				String nomeUtente= res.getString("nomeUtente");
				String ruoloUtente = res.getString("ruoloUtente");
				String cognomeUtente = res.getString("cognomeUtente");
				Integer idUtente = res.getInt("idUtente");
				

				BeanUtente utenteLoggato = new BeanUtente(nomeUtente, cognomeUtente, passwordUtente, ruoloUtente, idLogin);
				utenteLoggato.setIdUtente(idUtente);
				return utenteLoggato;
			}
			else {
				//non esiste utente 

				return new BeanUtente(1329);

			}
			

		} catch (SQLException e) {

			e.getStackTrace();
			if(e.getErrorCode() == 1329) {
				//non esiste utente 
				return new BeanUtente(1329);
			}
			if(e.getErrorCode() == 1613) {
				//timeout connession
				return new BeanUtente(1613);

			}
			if(e.getMessage().contains("Could not create connection to database server")) {
				return new BeanUtente(1613);
			}
			

		}finally{

			try {

				ps.close();
				ConnectionPool.releaseConnection(conn);

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return null;
	}

	
	

}
