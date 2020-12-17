import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 * This class helps to store employee information including their project list.
 *
 */
public class Employee {

	// instance variables
	private String name;
	private String ssn;
	private Connection con;
	private ArrayList<Project> projList;
	private Statement stmt;
	private ResultSet result;
	private String query;

	/**
	 * Constructor which initializes Employee name, SSN and Connection.
	 * 
	 * @param name
	 * @param ssn
	 * @param con
	 */
	public Employee(String name, String ssn, Connection con) {
		this.name = name;
		this.ssn = ssn;
		this.con = con;
		projList = new ArrayList<>();
		this.addProject();

	}

	/**
	 * This method creates project objects and adds to the project list.
	 */
	private void addProject() {
		try {
			stmt = con.createStatement();
			query = "SELECT Pname, Hours FROM PROJECT JOIN WORKS_ON ON Pnumber = Pno WHERE Essn = " + ssn
					+ " ORDER BY Pname";
			result = stmt.executeQuery(query);
			while (result.next()) {
				String name = result.getString("Pname");
				String hours = result.getString("Hours") == null ? "0.0" : result.getString("Hours");
				Project project = new Project(name, hours);

				projList.add(project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method returns the total hours of an employee.
	 * 
	 * @return total hours
	 */
	public double getTotalHours() {
		double sum = 0;
		for (int i = 0; i < projList.size(); i++) {
			sum += projList.get(i).getHours();
		}
		return sum;
	}

	/**
	 * This method displays the employee information.
	 */
	public void display() {
		System.out.println("  " + name);
		for (int i = 0; i < projList.size(); i++) {
			Project project = projList.get(i);
			project.display();
		}
		if (this.getTotalHours() != 0) {
			String dash = "";
			for (int i = 0; i < Double.toString(this.getTotalHours()).length(); i++) {
				dash = dash + "-";
			}
			System.out.printf("%31s%s", dash, "\n");

			System.out.printf("%31.1f\n", this.getTotalHours());
		}
		System.out.println();
	}

}
