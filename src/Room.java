
public class Room {

	private String id;
	private int type;
	
	public Room(String id, int type) {
		this.id = id;
		this.type = type;
	}
	
	// add the room to the DB
	public int addToDB() {
		return DBManager.addRoom(this);
	}

	// update the DB with the room's details
	public int updateDB() {
		return DBManager.updateRoom(this);
	}

	// delete the room from the DB
	public int deleteFromDB() {
		return DBManager.deleteRoom(this);
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", type=" + type + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public static void main(String[] args) {
		// testing
		int retval;
		System.out.println("Creating a new room:");
		Room r = new Room("666", 1);
		retval = r.addToDB();
		if (retval == 0) {
			System.out.println("OK");
		} else {
			System.out.println("NOK");
		}
		System.out.println(r);
		System.out.println("Changing the type:");
		r.setType(2);
		retval = r.updateDB();
		if (retval == 0) {
			System.out.println("OK");
		} else {
			System.out.println("NOK");
		}
		System.out.println(r);
		System.out.println("Deleting from DB:");
		retval = r.deleteFromDB();
		if (retval == 0) {
			System.out.println("OK");
		} else {
			System.out.println("NOK");
		}
	}
	
}
