import java.sql.*;
import java.util.*;

/**
 * 
 * This class helps to store the employee list for the department
 *
 */
public class Department {

	// Instance variables
	private ArrayList<Employee> empList;
	private Statement stmt;
	private ResultSet result;
	private String query;
	private String name;
	private String dno;
	private Connection con;
	private double totalHours;

	/**
	 * Constructor which initializes the Department name, number, and connection.
	 * 
	 * @param dname
	 * @param dno
	 * @param con
	 */
	public Department(String dname, String dno, Connection con) {

		name = dname;
		this.dno = dno;
		this.con = con;
		empList = new ArrayList<>();
		this.addEmployee();
	}

	/**
	 * This method creates employee object and adds to employee list.
	 */
	private void addEmployee() {
		try {
			stmt = con.createStatement();
			query = "SELECT * FROM EMPLOYEE WHERE Dno = " + dno + " ORDER BY Lname";
			result = stmt.executeQuery(query);
			while (result.next()) {
				String name = result.getString("Fname") + " " + result.getString("Lname");
				String ssn = result.getString("Ssn");
				Employee employee = new Employee(name, ssn, con);

				empList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method displays the department information.
	 */
	public void display() {
		totalHours = 0;
		System.out.println(name + "\n");
		for (int i = 0; i < empList.size(); i++) {
			Employee employee = empList.get(i);
			employee.display();
			totalHours += employee.getTotalHours();
		}
		System.out.println("Total: " + empList.size() + " Employee");

		System.out.printf("       %.1f Hours\n", totalHours);
	}

}
