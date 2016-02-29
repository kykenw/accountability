import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
//this class saves Employees and Jobs to external files

public class Save {
	//class variables
	private static ArrayList<Employee> emp;
	private static ArrayList<Job> j;
	private static HashMap<String, Employee> map1 = new HashMap<>(); 
	private static HashMap<String, Job> map2 = new HashMap<>();
	
	//strings that define the names of the files
	private static String filename1 = "employee.save";
	private static String filename2 = "job.save";
	//method for saving employees
		public static void saveEmployee()
		{
			//assign return value of getE to emp
			emp = CreateEmp.getE();
			//enhanced for loop that iterates through Employee objects in emp
			for(Employee i : emp)
			{
				//adds new entry into map1
				map1.put(i.getName(), i);
			}
			//initialize ObjectOutputStream
			ObjectOutputStream outputStream = null;
			
			try
			{
				//initialize outputStream
				outputStream = new ObjectOutputStream(new FileOutputStream(filename1));
				//write the object to the file
				outputStream.writeObject(map1);
			}
			//catch these exceptions
			catch(FileNotFoundException ex)
			{
				ex.printStackTrace();
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				try
				{
					//close file
					if(outputStream != null)
					{
						outputStream.flush();
						outputStream.close();
					}
				}
				catch(IOException ex)
				{
					ex.printStackTrace();
				}
			}
		}
		//method for saving jobs
		public static void saveJob()
		{
			//assign return value to j
			j = JobTable.getJob();
			//enhanced for loop that iterates through job objects in j
			for(Job i : j)
			{
				//create new map entry
				map2.put(i.getName(), i);
			}
			//intialize ObjectOutputStream
			ObjectOutputStream oos = null;
			
			try
			{
				//initialize oos
				oos = new ObjectOutputStream(new FileOutputStream(filename2));
				//write object to map2
				oos.writeObject(map2);
			}
			//catch these exceptions
			catch(FileNotFoundException ex)
			{
				ex.printStackTrace();
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				try
				{
					//close file
					if(oos != null)
					{
						oos.flush();
						oos.close();
					}
				}
				catch(IOException ex)
				{
					ex.printStackTrace();
				}
			}
		}
	}



