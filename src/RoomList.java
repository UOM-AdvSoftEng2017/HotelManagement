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
	
	public Room getRoom(String roomID) {
	    for (Room r: RL.rl) {
	        if (roomID.equals(r.getId())) return r;
	    }
	    return null;
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
