
public class Room{
//class that defines what a room is
//class variables
private static int totalRooms = 100;
private static String[] roomTypes = {"Single", "Double", "Suite"};
private int number;
private String type;
private String name;
private boolean clean;

//constructor for room
public Room(int n, String t, String na) {
	this.number = n;
	this.type = t;
	this.setName(na);
	
}

//getters and setters
public int getNumber() {
	return number;
}

public void setNumber(int number) {
	this.number = number;
}

public boolean isClean() {
	return clean;
}

public void setClean(boolean clean) {
	this.clean = clean;
}

public static int getTotalRooms() {
	return totalRooms;
}

public static void setTotalRooms(int totalRooms) {
	Room.totalRooms = totalRooms;
}

public static String[] getRoomTypes() {
	return roomTypes;
}

public static void setRoomTypes(String[] roomTypes) {
	Room.roomTypes = roomTypes;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
}
