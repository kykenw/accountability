import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
//load class handles reading of external files

public class Load {
	//class variables
	private static ArrayList<Employee> emp = new ArrayList<Employee>();
	private static ArrayList<Job> j = new ArrayList<Job>();
	//Strings that represent the file names
	private static String filename1 = "employee.save";
	private static String filename2 = "job.save";
	//Hashmap so that it is possible to save multiple things
	private static HashMap<String, Employee> map1;
	private static HashMap<String, Job> map2;

		public static void loadEmployee()
		{
			//initilize ObjectInputStream
			ObjectInputStream inputStream = null;
			//try this
			try
			{
				//initialize inputStream
				inputStream = new ObjectInputStream(new FileInputStream(filename1));
				//initialize Hashmap
				map1 = (HashMap<String, Employee>)inputStream.readObject();
					
			}
			//catch these exceptions
			catch(EOFException ex)
			{
				System.out.println("Reached end of file!");
			}
			catch(ClassNotFoundException ex)
			{
				ex.printStackTrace();
			}
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
					//close the file
					if(inputStream != null)
					{
						inputStream.close();
					}
				}
				catch(IOException ex)
				{
					ex.printStackTrace();
				}
			}
			
		}
		
		public static void loadJob()
		{
			//initialize ObjectInputStream
			ObjectInputStream iS = null;
			
			try
			{
				//initialize iS
				iS = new ObjectInputStream(new FileInputStream(filename2));
				//initialize hashmap
				map2 = (HashMap<String, Job>)iS.readObject();
				
				
			}
			//catch these exceptions
			catch(EOFException ex)
			{
				System.out.println("Reached end of file!");
			}
			catch(ClassNotFoundException ex)
			{
				ex.printStackTrace();
			}
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
					if(iS != null)
					{
						iS.close();
					}
				}
				catch(IOException ex)
				{
					ex.printStackTrace();
				}
			}
			
		}
		//method to take values from hashmap and store them in arrays
		public static void buildArray()
		{
			//all values in map1 get added to emp array
			if(map1 != null)
			{
				for(Employee value: map1.values())
				{
				emp.add(value);
				}
			}
			
			//all values in map2 get added to j array
			if(map2 != null)
			{
				for(Job v : map2.values())
				{
				j.add(v);
				}
			}
			
		}
		//method to return emp arraylist
		public static ArrayList<Employee> getEmp()
		{
			return emp;
		}
		//method to return j arraylist
		public static ArrayList<Job> getJob()
		{
			return j;
		}
}
