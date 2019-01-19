package connessione;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class ConnectionPool {
  private static List<Connection> freeDbConnections;

  static {
    freeDbConnections = new LinkedList<Connection>();
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("DB driver not found:"+ e.getMessage());
    } 
  }
  
  private static synchronized Connection createDBConnection() throws SQLException {
    
    int lport=3306;
    String rhost="172.31.44.86";
    String host="18.191.83.131";
    int rport=3306;
    String user="ec2-user";
    String password="Gionsi12345.";
    String dbuserName = "lucaamoriello";
    String dbpassword = "lucapass";
    String url ="jdbc:mysql://localhost:"+lport+"/clickeat";
        String driverName="com.mysql.jdbc.Driver";
        Connection conn = null;
        Session session = null;
        
        try {
        java.util.Properties config = new java.util.Properties(); 
        config.put("StrictHostKeyChecking", "no");
        JSch jsch = new JSch();
        session=jsch.getSession(user, host, 22);
        session.setPassword(password);
        session.setConfig(config);
        session.connect();
        System.out.println("Connected");
        int assinged_port=session.setPortForwardingL(lport, rhost, rport);
          System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
        System.out.println("Port Forwarded");
        
        //mysql database connectivity
            Class.forName(driverName).newInstance();
            conn = DriverManager.getConnection (url, dbuserName, dbpassword);
            
            conn.setAutoCommit(true);
            
            System.out.println ("Database connection established");
            System.out.println("DONE");
        return conn;

      }catch(Exception e){
        e.printStackTrace();
      }finally{
        if(conn != null && !conn.isClosed()){
          //System.out.println("Closing Database Connection");
          //conn.close();
        }
        if(session !=null && session.isConnected()){
          //System.out.println("Closing SSH Connection");
          //session.disconnect();
        }
      }
    return conn;
  }



  public static synchronized Connection getConnection() throws SQLException {
    Connection connection;

    if (!freeDbConnections.isEmpty()) {
      connection = (Connection) freeDbConnections.get(0);
      freeDbConnections.remove(0);

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

  public static synchronized void releaseConnection(Connection connection) throws SQLException {
    if(connection != null) freeDbConnections.add(connection);
  }
}