/*class: ManagerTavolo
 * author: AndreaCupito / LucaAmoriello
 * version: 1.0
 * classe utile per la gestione della classe BeanTavolo
 */
package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connessione.ConnectionPool;
import model.BeanOrdinazione;
import model.BeanTavolo;

public class ManagerTavolo {

	/*
	 * metodo utile per ottenere la lsita dei tavoli registrati
	 * @return ArrayList <BeanTavolo> 
	 */
	public synchronized ArrayList<BeanTavolo> ottieniTavoli(){ 

		ArrayList<BeanTavolo> listaTavoli = new ArrayList<BeanTavolo>();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT * FROM Tavolo");

			ResultSet res = ps.executeQuery();

			while(res.next()){

				Integer numeroTavolo = res.getInt("numeroTavolo");
				Integer numeroOrdinazione = res.getInt("numeroOrdinazione");
				Boolean flagOccupato = (Boolean) res.getBoolean("flagOccupato");
				Boolean flagConto = (Boolean) res.getBoolean("flagConto");
				
				BeanTavolo tavolo = new BeanTavolo(numeroTavolo);
				tavolo.setNumeroOrdinazione(numeroOrdinazione);
				tavolo.setFlagConto(flagConto);
				tavolo.setFlagOccupato(flagOccupato);

				listaTavoli.add(tavolo);
			}

		} catch (SQLException e2) {
				e2.printStackTrace();
			

		}finally{

			try {
				ps.close();
				ConnectionPool.releaseConnection(conn);
				return listaTavoli;

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return null;
	}

	/*
	 * metodo utile per creare un'entità di BeanTavolo
	 * @params numeroTavolo, identificativo univoco del tavolo
	 * @return true se la creazione ha avuto successo, false altrimenti
	 */
	public synchronized Boolean creaTavolo(Integer numeroTavolo) {
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("INSERT INTO Tavolo(numeroTavolo) VALUES(?)");
			ps = conn.prepareStatement(sqlString);

			ps.setInt(1, numeroTavolo);

			int value = ps.executeUpdate();
			
			
			System.out.println(value);
			if(value != 0) {

				BeanTavolo tavolo = new BeanTavolo(numeroTavolo);
				System.out.println("Registrazione effettuata con successo");

				return true;
			}
		}
		catch(SQLException e){
			if(e.getErrorCode() == 1062) {
				e.printStackTrace();
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
		return false;
	}

	/*
	 * metodo utile per eliminare un Tavolo registrato nel sistema
	 * @params numeroTavolo, identificativo univoco dell'entit tavolo
	 * @return true se l'eliminazione ha avuto successo, false altrimenti
	 */
	public synchronized Boolean eliminaTavolo(Integer numeroTavolo){
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("DELETE FROM Tavolo WHERE numeroTavolo = ?");
			ps = conn.prepareStatement(sqlString);

			
			ps.setInt(1, numeroTavolo);

			int value = ps.executeUpdate();

			if(value != 0) {
				System.out.println("tavolo eliminato con successo");
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

	/*
	 * metodo utile per liberare un Tavolo registrato nel sistema
	 * @params tavolo, entità tavolo selezionata
	 * @return BeanTavolo tavolo modificato
	 */
	public synchronized BeanTavolo freeTavolo(BeanTavolo tavolo){
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("UPDATE Tavolo SET numeroOrdinazione = 0 AND flagOccupato = false AND flagConto = false WHERE numeroTavolo = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setInt(1, tavolo.getNumeroTavolo());

			int value = ps.executeUpdate();
			
			if(value != 0) {
				System.out.println("Tavolo liberato");
				tavolo.setFlagConto(false);
				tavolo.setFlagOccupato(false);
				tavolo.setNumeroOrdinazione(0);
				
				return tavolo;

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

	/*
	 * metodo utile per conoscere lo stato di un Tavolo registrato nel sistema
	 * @params numeroTavolo, identificativo univoco dell'entit tavolo
	 * @return true se il tavolo è occupato, false altrimenti
	 */
	public synchronized Boolean isOccupato(Integer numeroTavolo){
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("SELECT flagOccupato FROM Tavolo WHERE numeroTavolo = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setInt(1, numeroTavolo);

			ResultSet res = ps.executeQuery();

			Boolean flagControllo = res.getBoolean("flagOccupato");

			if(flagControllo) {
				System.out.println("tavolo Occupato");
				return true;
			}
			else {
				System.out.println("tavolo libero");
				return false;
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

	/*
	 * metodo utile per conoscere lo stato, relativo alla segnalazione del conto, di un Tavolo registrato nel sistema
	 * @params numeroTavolo, identificativo univoco dell'entit tavolo
	 * @return true se il tavolo conto è stato emesso, false altrimenti
	 */
	public synchronized Boolean isContoPresente(Integer numeroTavolo){
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("SELECT flagConto FROM Tavolo WHERE numeroTavolo = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setInt(1, numeroTavolo);

			ResultSet res = ps.executeQuery();

			Boolean flagControllo = res.getBoolean("flagConto");

			if(flagControllo) {
				System.out.println("Il Conto è stato richiesto per il tavolo");
				return true;
			}
			else {
				System.out.println("il conto non è stato richiesto per il tavolo");
				return false;
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
	
	/*
	 * metodo utile per settare lo stato di un Tavolo ad occupato registrato nel sistema
	 * @params numeroTavolo, identificativo univoco dell'entit tavolo
	 * @return true se il tavolo è stato occupato, false altrimenti
	 */
	public synchronized Boolean setOccupato(Integer numeroTavolo){
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("UPDATE Tavolo SET flagOccupato = true WHERE numeroTavolo = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setInt(1, numeroTavolo);

			int value = ps.executeUpdate();


			if(value != 0) {
				System.out.println("il tavolo è stato occupato");
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

	/*
	 * metodo utile per ottenere un Tavolo registrato nel sistema
	 * @params numeroTavolo, identificativo univoco dell'entit tavolo
	 * @return BeanTavolo tavolo, istanza del tavolo selezionato
	 */
	public synchronized BeanTavolo getTavolo(Integer numeroTavolo) {
		
		Connection conn =  null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("SELECT * FROM Tavolo WHERE numeroTavolo = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setInt(1,numeroTavolo);

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				return new BeanTavolo(numeroTavolo, rs.getInt("numeroOrdinazione"), rs.getBoolean("falgOccupato"), rs.getBoolean("flagConto"));

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
	
	/*
	 * metodo utile per ottenere il numero dell'ordinazione allegata ad un Tavolo
	 * @params numeroTavolo, identificativo univoco dell'entit tavolo
	 * @return Integere numeroOrdinazione se il tavolo ha un ordinazione allegata, null altrimenti
	 */
	public synchronized Integer getOrdinazioneDiTavolo(Integer numeroTavolo) {
		
		Connection conn =  null;
		PreparedStatement ps = null;
		Integer numeroOrdine = 0;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("SELECT numeroOrdinazione FROM Tavolo WHERE numeroTavolo = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setInt(1,numeroTavolo);

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				rs.getInt("numeroOrdinazione");
				return numeroOrdine;
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
	
	
}
