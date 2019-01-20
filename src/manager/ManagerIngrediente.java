package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connessione.ConnectionPool;
import model.BeanIngrediente;


public class ManagerIngrediente {
	
	private ArrayList<BeanIngrediente> listaIngredienti = new ArrayList<BeanIngrediente> ();//lista di ingredienti
	
	public synchronized BeanIngrediente creaIngrediente(String nomeIngrediente) {
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			//String sqlString = new String("INSERT INTO Ingrediente(nomeIngrediente) VALUES(?))"); parentesi in più che rompeva le palle
			//																						e restituiva sempre ingrediente nullo
			//																						visto che non inseriva nulla nel db
			String sqlString = new String("INSERT INTO Ingrediente(nomeIngrediente) VALUES (?)");
			ps = conn.prepareStatement(sqlString);

			ps.setString(1, nomeIngrediente);

			int value = ps.executeUpdate(); // non vorrei sbagliarmi ma dalla documentazione ho letto che
			//									questo non restituisce nulla quindi non puoi prendere l'id dell'ingrediente
				
			//int idIngrediente = ps.getResultSet().getInt("idLogin"); inolte prendevi idLogin e non idIngrediente
					
			if(value != 0 ) {
				System.out.println("Ingrediente aggiunto con successo nel Database");
				
			}
			

			
		}
		catch(SQLException e){
			if(e.getErrorCode() == 1062) {
				System.out.println("Ingrediente già esiste nel Database");
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
	
	public synchronized boolean eliminaIngrediente(Integer idIngrediente){
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("DELETE FROM Ingrediente WHERE idIngrediente = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setInt(1,idIngrediente);

			int value = ps.executeUpdate();
			
			if(value != 0) {
				System.out.println("eliminazione dell'ingrediente effettuata");
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
	
	public synchronized ArrayList<BeanIngrediente> ottieniListaIngredienti(){
		Connection conn =  null;
		PreparedStatement ps = null;

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
	
	/*metodo che ricerca nella tabella Ingrediente un Ingrediente con l'id specificato e ritorna il Bean relativo ad esso*/
	public synchronized BeanIngrediente ricercaPerId(Integer idIngrediente) {
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("SELECT * FROM Ingrediente WHERE idIngrediente = ?");
			ps = conn.prepareStatement(sqlString);
			
			ps.setInt(1, idIngrediente);

			ResultSet res = ps.executeQuery();
			
			while(res.next()) {
				String nomeIngrediente = res.getString("nomeIngrediente");
				
				BeanIngrediente ingrediente = new BeanIngrediente(idIngrediente, nomeIngrediente);
				Integer indice = listaIngredienti.indexOf(ingrediente);
				ingrediente = listaIngredienti.get(indice);
				
				return ingrediente;
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

/*metodo che ricerca nella tabella Ingrediente un Ingrediente con il nome specificato e ritorna il Bean relativo ad esso*/
	public synchronized BeanIngrediente ricercaPerNome(String nomeIngrediente) {
		Connection conn =  null;
		PreparedStatement ps = null;
	
		//Ho creato un nuovo metodo per avere una funzione in più e non impasticciare troppo la prima funzione "creaUtente"
		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("SELECT idIngrediente FROM Ingrediente WHERE nomeIngrediente = ?");
			ps = conn.prepareStatement(sqlString);
			ps.setString(1, nomeIngrediente);
	
			ResultSet res = ps.executeQuery();

			int  idIngrediente = 0;// infatti questo era sempre null e quindi è null pure
	//																		la creazione dell'ingrediente
	
			while(res.next()) {
				idIngrediente = res.getInt("idIngrediente");
			}
	
	
	
			if(idIngrediente != 0) {
				BeanIngrediente ingrediente = new BeanIngrediente(idIngrediente, nomeIngrediente);
				System.out.println("Ingrediente ottenuto con successo dal Database");
	
				return ingrediente;
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


