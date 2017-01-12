import java.util.ArrayList;

public enum RoomList {
	RL;
	
	private ArrayList<Room> rl;
	
	private RoomList() {
		rl = new ArrayList<Room>();
		update(); // populate the room list when creating it for the 1st time
	}
	
	public ArrayList<Room> getRL() {
		return rl;
	}
	
	// update the room list from the DB
	public void update() {
		rl = DBManager.getRoomList();
	}
	
	public static void main(String[] args) {
		// testing
		for (Room r: RL.rl) {
			System.out.println(r);
		}
	}

}
