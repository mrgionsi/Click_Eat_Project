/*class: ManagerUtente
 * author: AndreaCupito
 * classe utile per la gestione della classe BeanUtente
 */
package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connessione.ConnectionPool;
import model.BeanUtente;

public class ManagerUtente {

	/* metodo utile per registrare un nuovo utente sul sistema, con i parametri scelti dall'amministratore
	 */
	public synchronized BeanUtente creaUtente(String nomeUtente, String passwordUtente, String cognomeUtente, String ruoloUtente, String idLogin){
		Connection conn =  null;
		PreparedStatement ps = null;

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

			if(value != 0) {

				BeanUtente utente = new BeanUtente(nomeUtente, cognomeUtente, passwordUtente, ruoloUtente, idLogin);
				System.out.println("Registrazione effettuata con successo");

				return utente;
			}
		}
		catch(SQLException e){
			if(e.getErrorCode() == 1062) {
				//return new BeanUtente("duplicato","duplicato"); 
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

	/*metodo utile per eliminare l'utente con l'identificativo di appartenenza
	 */
	public synchronized boolean eliminaUtente(Integer idUtente){
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("DELETE FROM Utente WHERE idUtente = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setInt(1,idUtente);

			int value = ps.executeUpdate();
			
			if(value != 0) {
				System.out.println("eliminazione effettuata");
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
				System.out.println("nomeUtente =======" + nomeUtente);
				
				BeanUtente utenteLoggato = new BeanUtente(nomeUtente, cognomeUtente, passwordUtente, ruoloUtente, idLogin);
				utenteLoggato.setIdUtente(idUtente);
				return utenteLoggato;
			}

		} catch (SQLException e) {
				System.out.println("SqlException in ManagerUtente");
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
	
	/*metodo utile per eliminare l'utente con l'identificativo di appartenenza
	 */
	public synchronized boolean eliminaUtenteViaIdLogin(String idLogin){
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("DELETE FROM Utente WHERE idLogin = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setString(1,idLogin);

			int value = ps.executeUpdate();
			
			if(value != 0) {
				System.out.println("eliminazione effettuata");
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

}
