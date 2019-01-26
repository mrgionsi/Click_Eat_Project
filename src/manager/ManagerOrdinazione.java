package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import connessione.ConnectionPool;
import model.BeanIngrediente;
import model.BeanOrdinazione;
import model.BeanPiatto;

public class ManagerOrdinazione {
	
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
			
			if(res.next()) {
				dataOrdine = res.getTimestamp("dataOrdine");
				numeroOrdine = res.getInt("numeroOrdinazione");
				
				
				BeanOrdinazione ordine = new BeanOrdinazione(numeroOrdine, dataOrdine);

				
				
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
}
