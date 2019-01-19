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
				
				BeanPiatto piatto = new BeanPiatto(nomePiatto,categoriaPiatto,listaIngredienti,prezzoPiatto, idPiatto);
				listaIngredienti = new ArrayList<BeanIngrediente>( ingredientiNelPiatto(idPiatto) ); //probabile errore
				listaPiatti.add(piatto);
			}
			
			return listaPiatti;
		}
		catch(SQLException e){
				e.printStackTrace(); 

		}
		finally {
			try {
				conn.commit();
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
				conn.commit();
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
