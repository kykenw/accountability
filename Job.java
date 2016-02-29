import java.io.Serializable;
import java.util.ArrayList;
//implements Serializable in order to save to an external file

public class Job implements Serializable{
//class variables
private String name;
private String description;
private String assigned = "";

//constructor
public Job(String n)
{
	this.name = n;
}
//getters and setters
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getAssigned() {
	return assigned;
}

public void setAssigned(String assigned) {
	this.assigned = assigned;
}

}
