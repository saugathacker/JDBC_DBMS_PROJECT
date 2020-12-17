import java.sql.*;

/**
 * 
 * This class sets up the connection and helps display the data to the main class.
 * 
 */
public class Display {
	
	private Statement stmt;
	private String query;
	private ResultSet result;
	private Connection con;


	/**
	 * This constructor initializes Connection variable
	 * @param con
	 */
	public Display (Connection con) {
		this.con = con;
	}	
	
	/**
	 * This method displays output with the help of query.
	 */
	public void output() {
		try {
			//Getting result from querry
	          stmt = con.createStatement();
	          query = "SELECT * FROM DEPARTMENT ORDER BY Dname";
	          this.result = stmt.executeQuery(query);
	          
	          //iterating the results and creating department object
	          while(result.next()) {
	        	  String dname = result.getString("Dname");
	        	  String dno = result.getString("Dnumber");
				
				
	        	  Department department = new Department(dname, dno, con);
	        	  department.display();
	        	  if(!result.isLast()) {
	        		  System.out.println("");
	        	  }
	          }
			} catch (SQLException e) {
				e.printStackTrace();
				}
	}

}
