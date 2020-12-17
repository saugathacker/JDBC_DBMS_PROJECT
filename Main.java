import java.sql.*;
//Name: Saugat K. Shrestha     
//
//Course: CSCI 4055   Date Due: 1st Dec, 2020
//
//Instructor: Dr. Lon Smith
//
//Program Description: This program connects to SQL COMPANY database, and displays the data in a format.

public class Main {
	//Main Class
	public static void main(String args[]) {
	   
		//Connecting to SQL database
	   	ConnectToSql sqlConnect = new ConnectToSql();
	    Connection con = sqlConnect.getCon();
      
	    //Displaying the formatted output
         try {
			if (!con.isClosed()) 
			 {
			    
			    Display display = new Display(con);
			    display.output();
			  
			 }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
         
         //Closing the connection
         sqlConnect.disconnect();

   }
}
