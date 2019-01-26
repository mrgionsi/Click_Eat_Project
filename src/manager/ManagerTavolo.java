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

				BeanTavolo tavolo = new BeanTavolo(numeroTavolo);
				tavolo.setNumeroOrdinazione(numeroOrdinazione);

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

	public synchronized BeanTavolo creaTavolo(Integer numeroTavolo) {
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

				return tavolo;
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
		return null;
	}

	public synchronized Boolean eliminaTavolo(Integer i){
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("DELETE FROM Tavolo WHERE numeroTavolo = ?");
			ps = conn.prepareStatement(sqlString);

			
			ps.setInt(1, i);

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
	
	public synchronized BeanTavolo setOccupato(BeanTavolo tavolo){
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("UPDATE Tavolo SET flagOccupato = true WHERE numeroTavolo = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setInt(1, tavolo.getNumeroTavolo());

			int value = ps.executeUpdate();


			if(value != 0) {
				System.out.println("il tavolo è stato occupato");
				tavolo.setFlagOccupato(true);
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


	public synchronized BeanTavolo setOrdinazionePerTavolo(BeanTavolo tavolo, BeanOrdinazione ordinazione){
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("UPDATE Tavolo SET numeroOrdinazione = ? WHERE numeroTavolo = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setInt(1, ordinazione.getNumeroOrdinazione());
			ps.setInt(2, tavolo.getNumeroTavolo());

			int value = ps.executeUpdate();
			
			if(value != 0) {
				System.out.println("Ordinazione registrata");
				tavolo.setNumeroOrdinazione(ordinazione.getNumeroOrdinazione());
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
