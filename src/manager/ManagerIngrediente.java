/*class: ManagerOrdinazione
 * author: AndreaCupito / LucaAmoriello
 * version: 1.0
 * classe utile per la gestione della classe BeanIngrediente
 */
package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connessione.ConnectionPool;
import model.BeanIngrediente;


public class ManagerIngrediente {
	
	/*
	 * metodo utile per creare un'entità Ingrediente
	 * @params nomeIngrediente, nome dell'ingrediente da creare
	 * @return BeanIngrediente ingrediente, con i parametri inseriti; SQLException altrimenti
	 */
	public synchronized BeanIngrediente creaIngrediente(String nomeIngrediente) {
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("INSERT INTO Ingrediente(nomeIngrediente) VALUES (?)");
			ps = conn.prepareStatement(sqlString);

			ps.setString(1, nomeIngrediente);

			int value = ps.executeUpdate(); 
			if(value != 0 ) {
				
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
				return ricercaPerNome(nomeIngrediente);
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return null;

	}

	/*
	 * metodo utile per eliminare un'entità Ingrediente
	 * @params idIngrediente, identificativo univoco dell'ingrediente selezionato
	 * @return true se l'eliminazione ha avuto successo, false altrimenti
	 */
	public synchronized boolean eliminaIngrediente(String nomeIngrediente){
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("DELETE FROM Ingrediente WHERE nomeIngrediente = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setString(1,nomeIngrediente);

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
	
	
	
	/*
	 * metodo utile per ottenere la lista di dingredienti presenti nel sistema
	 * @return ArrayList<BeanIngrediente> != null se ci sono ingredienti nel sistema, ArrayList<BeanIngrediente> = null altrimenti
	 */
	public synchronized ArrayList<BeanIngrediente> ottieniListaIngredienti(){
		Connection conn =  null;
		PreparedStatement ps = null;
		ArrayList<BeanIngrediente> listaIngredienti = new ArrayList<BeanIngrediente> ();//lista di ingredienti
		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("SELECT * FROM Ingrediente");
			ps = conn.prepareStatement(sqlString);


			ResultSet res = ps.executeQuery();
			
			while(res.next()) {
				Integer idIngrediente = res.getInt("idIngrediente");
				String nomeIngrediente = res.getString("nomeIngrediente");
				
				BeanIngrediente ingrediente = new BeanIngrediente(idIngrediente, nomeIngrediente);
				listaIngredienti.add(ingrediente);
			}
			
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
	
	/*metodo che ricerca nella tabella Ingrediente un Ingrediente con l'id specificato 
	 * @params idIngrediente, identificativo univoco dell'entità ingrediente
	 * @return BeanIngrediente con l'idIngrediente specificato*/
	public synchronized BeanIngrediente ricercaPerId(Integer idIngrediente) {
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("SELECT nomeIngrediente FROM Ingrediente WHERE idIngrediente = ?");
			ps = conn.prepareStatement(sqlString);
			
			ps.setInt(1, idIngrediente);

			ResultSet res = ps.executeQuery();
						
			if(res.next()) {
				return new BeanIngrediente(idIngrediente, res.getString("nomeIngrediente"));

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

	/*metodo che ricerca nella tabella Ingrediente un Ingrediente con il nome specificato 
	 * @params nomeIngrediente, nome dell'ingrediente selezionato
	 * @return BeanIngrediente relativo al nome indicato
	 */
	public synchronized BeanIngrediente ricercaPerNome(String nomeIngrediente) {
		Connection conn =  null;
		PreparedStatement ps = null;
	
		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("SELECT idIngrediente FROM Ingrediente WHERE nomeIngrediente = ?");
			ps = conn.prepareStatement(sqlString);
			ps.setString(1, nomeIngrediente);
	
			ResultSet res = ps.executeQuery();

			

			if(res.next()) {
				return new BeanIngrediente(res.getInt("idIngrediente"), nomeIngrediente);
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


