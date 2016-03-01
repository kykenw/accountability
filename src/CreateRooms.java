import java.util.Random;
//this class is used for the Demo
//it generates pseudo jobs that then will get added to the list of tasks

public class CreateRooms {
//declare class variables
private String[] r = new String[Room.getTotalRooms()];
private int rNum;
private String[] type;
private static Room[] rm = new Room[Room.getTotalRooms()];
private Random ran;

public CreateRooms()
{
	//iterate through number of elements in r array
	for(int i = 0; i < r.length; i++)
	{
		//initialize upOne 1 above i
		int upOne = i + 1;
		//Initialize Random object
		ran = new Random();
		//invoke method from random object which returns a number between 0-2
		rNum =  ran.nextInt(3);
		//assign return value from getRoomTypes
		type = Room.getRoomTypes();
		//create string and assign it to an element in r array
		r[i] = type[rNum] + " room #" + upOne;
		//create new room and assign it to element in rm array
		rm[i] = new Room(i, type[rNum], r[i]);
	}
}
//method that returns rm array
public static Room[] getRm()
{
	return rm;
}
}
