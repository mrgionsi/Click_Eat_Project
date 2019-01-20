package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import connessione.ConnectionPool;
import model.BeanOrdinazione;

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
			}
		}
		catch(SQLException e){
			e.printStackTrace();

		}
		finally {
			try {
				ps.close();
				ConnectionPool.releaseConnection(conn);
				return impostaIdEDataOrdine(ordine);
				
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	public synchronized BeanOrdinazione impostaIdEDataOrdine(BeanOrdinazione ordine) {
		Connection conn =  null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("SELECT * FROM Ordinazione WHERE numeroOrdinazione = ? AND dataOrdine");
			ps = conn.prepareStatement(sqlString);

			ps.setInt(1, ordine.getNumeroOrdinazione());
			ps.setTimestamp(2, ordine.getDataOrdine());
			
			ResultSet res = ps.executeQuery();

			if(res.next()) {
				ordine.setNumeroOrdinazione(res.getInt("numeroOrdinazione"));
				ordine.setDataOrdine(res.getTimestamp("dataOrdine"));
				System.out.println("numero e data dell'ordine impostati");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				ConnectionPool.releaseConnection(conn);
				return ordine;

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
		ManagerTavolo tavoloManager = new ManagerTavolo();
		Integer numeroOrdine = 0;
		Timestamp dataOrdine;
		
		try {
			conn = ConnectionPool.getConnection();
			
			numeroOrdine = tavoloManager.getOrdinazioneDiTavolo(numeroTavolo);
			
			String sqlString = new String("SELECT * FROM Ordinazione WHERE numeroOrdine = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setInt(1, numeroOrdine);
			
			ResultSet res = ps.executeQuery();

			if(res.next()) {
				dataOrdine = res.getTimestamp("dataOrdine");
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
