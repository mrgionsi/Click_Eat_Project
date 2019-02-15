package connessione;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ConnectionPool {

	private static List<Connection> freeConn;
	
	static {
		freeConn = new LinkedList<Connection>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found:" + e.getMessage());
		}
	}

	private static synchronized Connection createDBConnection() throws SQLException {
		Connection newConnection = null;

		String username = "dbclickeat";
		String password = "dbclickeat";
		
		   String url = "jdbc:mysql://db4free.net:3306/dbclickeat?autoReconnect=true";
		   newConnection = DriverManager.getConnection(url, username, password);
		   
		
		newConnection.setAutoCommit(true);
		
		return newConnection;
		
	}
	
	public static synchronized Connection getConnection() throws SQLException {
		Connection connection;
		
		if (!freeConn.isEmpty()) {
			connection = (Connection) freeConn.get(0);
			freeConn.remove(0);
			
			try {
				if (connection.isClosed())
					connection = getConnection();
			} catch (SQLException e) {
				connection.close();
				connection = getConnection();
			}
		} else {
			connection = createDBConnection();
		}
		return connection;
	}

	public static void releaseConnection(Connection connection) throws SQLException{		
		if(connection != null) 
			freeConn.add(connection);
	}


}