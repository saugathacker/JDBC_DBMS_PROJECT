import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 
 * This class helps to connect to the SQL server.
 * 
 */
public class ConnectToSql {
	
	Connection con;
    Statement stmt;
    ResultSet result;
    String query;

    /**
     * This method return the Connection variable
     * @return con
     */
	@SuppressWarnings("deprecation")
	public Connection getCon() {
		try {
		       FileReader reader = new FileReader("example.properties");
		       Properties p = new Properties();
		       p.load(reader);

		       String dbdriver = p.getProperty("db.driver");
		       String dbuser = p.getProperty("db.user");
		       String dbpassword = p.getProperty("db.password");
		       String dburl = p.getProperty("db.url");

		       Class.forName(dbdriver).newInstance();
		       con = DriverManager.getConnection(dburl, dbuser, dbpassword);


		    } catch (Exception e) {
		       System.err.println("Exception: " + e.getMessage());
		    }
		return con;
	}
	
	/**
	 * This method helps to close the connection.
	 */
	public void disconnect() {
		try {
			if (con != null)
		        con.close();
		    } 
		catch (SQLException e) {}
	}

}
