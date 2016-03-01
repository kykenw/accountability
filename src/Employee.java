import java.io.Serializable;
import java.util.ArrayList;
//this class is used to define what an employee is
//it implements Serializable in order to be saved to an external file

public class Employee implements Serializable{

private static final long serialVersionUID = 1L;
//class variables
private static String[] jobTypes = {"Regular", "Part-Time"};
private String name;
private String type;
private boolean scheduled;
private int[] availability;

//constructor for creating an Employee
public Employee(String n, String t, int[] a)
{
	this.name = n;
	this.type = t;
	this.setAvailability(a);
	
}

//getters and setters
public static String[] getJobTypes()
{
	return jobTypes;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public boolean isScheduled() {
	return scheduled;
}

public void setScheduled(boolean scheduled) {
	this.scheduled = scheduled;
}

public int[] getAvailability() {
	return availability;
}

public void setAvailability(int[] availability) {
	this.availability = availability;
}
}