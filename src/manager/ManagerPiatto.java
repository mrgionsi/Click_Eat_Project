package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connessione.ConnectionPool;
import model.BeanIngrediente;
import model.BeanPiatto;

public class ManagerPiatto {
	
	public synchronized ArrayList<BeanPiatto> ottieniListaPiatti(){
		Connection conn =  null;
		PreparedStatement ps = null;
		ArrayList<BeanPiatto> listaPiatti = new ArrayList<BeanPiatto>();

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("SELECT * FROM Piatto");
			ps = conn.prepareStatement(sqlString);


			ResultSet res = ps.executeQuery();
			
			String nomePiatto = null;
			Float prezzoPiatto = null;
			String categoriaPiatto = null;
			Integer idPiatto = 0;
			ArrayList<BeanIngrediente> listaIngredienti = new ArrayList<BeanIngrediente>();
			
			while(res.next()) {
				
				nomePiatto = res.getString("nomePiatto");
				prezzoPiatto = res.getFloat("prezzoPiatto");
				categoriaPiatto = res.getString("categoriaPiatto");
				idPiatto = res.getInt("idPiatto");
				
				BeanPiatto piatto = new BeanPiatto(nomePiatto,categoriaPiatto,prezzoPiatto);
				listaIngredienti.addAll( ingredientiNelPiatto(idPiatto)) ; //probabile errore
				piatto.setListaIngredienti(listaIngredienti);
				listaPiatti.add(piatto);
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

			if(value != 0) {

				BeanPiatto piatto = new BeanPiatto(nomePiatto, categoriaPiatto, prezzoPiatto);
				System.out.println("Registrazione effettuata con successo");

				return piatto;
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
					String sqlString = new String("INSERT INTO IngredientiPiatto(idPiatto,idIngrediente) VALUES(?,?)");
					ps = conn.prepareStatement(sqlString);

					ps.setInt(1, idPiatto);
					ps.setInt(2, idIngrediente);
			
					int value = ps.executeUpdate();

					if(value != 0) {
						System.out.println("Registrazione effettuata con successo");
						return true;
					}
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
}
