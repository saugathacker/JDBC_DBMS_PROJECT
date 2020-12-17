
/**
 * 
 * This class helps to store project information.
 *
 */
public class Project {
	
	//instance variables
	private String name;
	private double hours;

	/**
	 * This constructor initializes prject name and hours worked by an employee.
	 * @param name
	 * @param hours
	 */
	public Project(String name, String hours) {
		this.name = name;
		this.hours = Double.parseDouble(hours);
	}

	/**
	 * This method returns the hours worked by an employee.
	 * @return hours
	 */
	public double getHours() {
		return hours;
	}
	
	/**
	 * This method displays the project information.
	 */
	public void display() {
		System.out.printf("     %-15s%11.1f\n", name, hours);
	}

}
