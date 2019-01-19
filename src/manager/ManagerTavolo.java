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
		return listaTavoli;
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

	public synchronized Boolean eliminaTavolo(String numeroTavolo){
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("DELETE FROM Tavolo WHERE numeroTavolo = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setString(1, numeroTavolo);

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
				conn.commit();
				ps.close();
				ConnectionPool.releaseConnection(conn);
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	public synchronized Boolean freeTavolo(Integer numeroTavolo){
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("UPDATE Tavolo SET numeroOrdinazione = 0 AND flagOccupato = false AND flagConto = false WHERE numeroTavolo = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setInt(1, numeroTavolo);

			int value = ps.executeUpdate();
			
			if(value != 0) {
				System.out.println("Tavolo liberato");
				return true;
			}
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
		return false;
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
	
	public synchronized Boolean setOccupato(Integer numeroTavolo){
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("UPDATE Tavolo SET flagOccupato = true WHERE numeroTavolo = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setInt(1, numeroTavolo);

			ResultSet res = ps.executeQuery();

			Boolean flagControllo = res.getBoolean("flagOccupato");

			if(flagControllo) {
				System.out.println("il tavolo è stato occupato");
				return true;
			}
			else {
				System.out.println("il tavolo non è stato occupato");
				return false;
			}
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


	public synchronized Boolean setOrdinazionePerTavolo(Integer numeroTavolo, BeanOrdinazione ordinazione){
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("UPDATE Tavolo SET numeroOrdinazione = ? WHERE numeroTavolo = ?");
			ps = conn.prepareStatement(sqlString);

			ps.setInt(1, ordinazione.getNumeroOrdinazione());
			ps.setInt(2,numeroTavolo);

			int value = ps.executeUpdate();
			
			if(value != 0) {
				System.out.println("Ordinazione registrata");
				return true;
			}else {
				System.out.println("Si è verificato un errore nell'esecuzione dell'update");
				return false;
			}

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
