package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import connessione.ConnectionPool;
import model.BeanIngrediente;
import model.BeanPiatto;

public class ManagerPiatto {
	Connection conn =  null;
	PreparedStatement ps = null;
	
	public synchronized BeanPiatto ottieniPiatto(int idPiatto) {

		
		List<String> listIngredienti = new LinkedList<String>();
		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("SELECT * FROM v_DescPiattiCompleti where idPiatto = ?");
			ps = conn.prepareStatement(sqlString);
			ps.setInt(1, idPiatto);


			ResultSet res = ps.executeQuery();
			
			
			if(res.next()) {
				
				 listIngredienti = Arrays.asList(res.getString("lista_ingredienti").split(","));
			    ArrayList<BeanIngrediente> listaIngredienti = new ArrayList<BeanIngrediente>();

				
				listIngredienti.forEach(element -> {
					listaIngredienti.add(new BeanIngrediente(element));
				});
				
				return new BeanPiatto (res.getInt("idPiatto"), res.getString("nomePiatto"), res.getString("categoriaPiatto"), res.getFloat("prezzoPiatto"), listaIngredienti);
				
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
	
	public synchronized ArrayList<BeanPiatto> ottieniListaPiatti(){
		
		ArrayList<BeanPiatto> listaPiatti = new ArrayList<BeanPiatto>();
		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("SELECT * FROM v_DescPiattiCompleti");
			ps = conn.prepareStatement(sqlString);


			ResultSet res = ps.executeQuery();
			
			String nomePiatto = null;
			Float prezzoPiatto = null;
			String categoriaPiatto = null;
			Integer idPiatto = 0;
			
			while(res.next()) {
				
				nomePiatto = res.getString("nomePiatto");
				prezzoPiatto = res.getFloat("prezzoPiatto");
				categoriaPiatto = res.getString("categoriaPiatto");
				idPiatto = res.getInt("idPiatto");
				String lista_ingredienti= res.getString("lista_ingredienti");
				System.out.println(res.getString("lista_ingredienti"));
				
//			//	listIngredienti = Arrays.asList(res.getString("lista_ingredienti").split(","));
//				List<BeanIngrediente> listaIngredienti = new ArrayList<BeanIngrediente>();
//				
//				listaIngredienti.addAll(((List<BeanIngrediente>)Arrays.asList(res.getString("lista_ingredienti").split(","))));
//				listaIngredienti.set
//				
//				
////				// controllare questo manager!!!!!!!!!!!!!!!				
////				listIngredienti.forEach(element -> {
////				    listaIngredienti.add(new BeanIngrediente(element));
////				});
				//override del costruttore ove gli passo la stringo e crea lui la lista di ingredienti.
				listaPiatti.add(new BeanPiatto(idPiatto, nomePiatto, categoriaPiatto, prezzoPiatto, lista_ingredienti));
				System.out.println(idPiatto);

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

			//Controllare solo se value non � 0 non va pi� bene visto che il nome del piatto deve essere unico
			//gi� modificata la struttura della tabella
			//Aggiunto al finally il ritorno del piatto
			if(value != 0) {
				System.out.println("Registrazione effettuata con successo");
			}
		}
		catch(SQLException e){
			if(e.getErrorCode() == 1062) {
				//cos� sappiamo che non � stato veramente aggiunto
				System.out.println("Piatto gi� esistente");

				//return new BeanUtente("duplicato","duplicato"); 
			}

		}
		finally {
			try {
				ps.close();
				ConnectionPool.releaseConnection(conn);
				//deve essere creato e ritornato qui perch� il piatto potrebbe gi� esistere o meno nel db
				//Ma a prescindere deve essere ritornato
				System.out.println("PiattoCreato");
				 
				return settaIdPiatto(new BeanPiatto(nomePiatto, categoriaPiatto, prezzoPiatto));
				
				//in questo modo ritorno un piatto con id settato e non ricevo puntatori nulla quando inserisco
				// nella tabella ingredienti nel piatto

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
					String sqlString = new String("INSERT INTO IngredientiPiatto(idPiatto, idIngrediente) VALUES(?,?)");
					ps = conn.prepareStatement(sqlString);

					//questo idPiatto non pu� mai funzionare se non viene mai definito
					//qual � l'id del piatto
					//i piatti vengono inseriti nel db ma mai viene preso il loro id
					ps.setInt(1, idPiatto);
					ps.setInt(2, idIngrediente);
			
					int value = ps.executeUpdate();

					if(value != 0) {
						System.out.println("Aggiunto con successo ingrediente: " + ingrediente.getNomeIngrediente());
						//return true;
						//con questo return faceva solo il primo giro del for
					}
					
					ps.close();

				}
			} catch(SQLException e){
				e.printStackTrace();
			}
		finally {
			try {
				//ps era realmente usato e "riempita" nel for quindi puntatore nullo quando veniva chiusa
				ps.close();
				ConnectionPool.releaseConnection(conn);
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

//Questo metodo serve a settare un idPiatto preso dal db subito dopo l'inserimento del piatto stesso
private synchronized BeanPiatto settaIdPiatto(BeanPiatto piatto) {
	Connection conn =  null;
	PreparedStatement ps = null;

	try {
		conn = ConnectionPool.getConnection();
		String sqlString = new String("SELECT idPiatto FROM Piatto WHERE nomePiatto = ?");
		ps = conn.prepareStatement(sqlString);

		ps.setString(1, piatto.getNomePiatto());
		
		
		ResultSet res = ps.executeQuery();

		if(res.next()) {
			piatto.setIdPiatto(res.getInt("idPiatto"));
			System.out.println("Id piatto settato");

			
		}
	}
	catch(SQLException e){
		e.printStackTrace(); 

		
		}
	finally {
		try {
			ps.close();
			ConnectionPool.releaseConnection(conn);
			return piatto;
			

		}
		catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	return null;
}

public synchronized Boolean eliminaPiatto(Integer i){
		Connection conn =  null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getConnection();
			String sqlString = new String("DELETE FROM Piatto WHERE idPiatto = ?");
			ps = conn.prepareStatement(sqlString);

			
			ps.setInt(1, i);

			int value = ps.executeUpdate();

			if(value != 0) {
				System.out.println("Piatto eliminato con successo");
				return eliminaPiattiFromIngredientiPiatto(i);
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


private synchronized Boolean eliminaPiattiFromIngredientiPiatto(int i){
	Connection conn =  null;
	PreparedStatement ps = null;

	try {
		conn = ConnectionPool.getConnection();
		String sqlString = new String("DELETE FROM IngredientiPiatto WHERE idPiatto = ?");
		ps = conn.prepareStatement(sqlString);

		
		ps.setInt(1, i);

		int value = ps.executeUpdate();

		if(value != 0) {
			System.out.println("Piatto eliminato da IngredientiPiatto con successo");
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

public synchronized boolean InserisciPiattoIntoOrdinazione(int idPiatto, int numeroOrdinazione) {
	//o forse si dovrebbe fare inserisci listaPiattiIntoOrdinazione (?)

	Connection conn =  null;
	PreparedStatement ps = null;

	try {
		conn = ConnectionPool.getConnection();
		String sqlString = new String("INSERT INTO PiattiOrdinazioni(idPiatto, numeroOrdinazione) VALUES(?,?)");
		ps = conn.prepareStatement(sqlString);

		ps.setInt(1, idPiatto);
		ps.setInt(2, numeroOrdinazione);

		
		int value = ps.executeUpdate();

		if(value != 0) {
			return true;
		}
	}
	catch(SQLException e){

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
