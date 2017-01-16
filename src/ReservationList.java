import java.util.ArrayList;

public enum ReservationList {
	INSTANCE;
	
	private ArrayList<Reservation> rl;
	
	private ReservationList() {
		rl = new ArrayList<Reservation>();
		update(); // populate the reservation list when creating it for the 1st time
	}
	
	public ArrayList<Reservation> getRL() {
		return rl;
	}
	
	public Reservation getReservation(int id) {
	    for (Reservation r: INSTANCE.rl) {
	        if (id == r.getId()) return r;
	    }
	    return null;
	}
	
	// update the reservation list from the DB
	public void update() {
		rl = DBManager.getReservationList();
	}
	
	public static void main(String[] args) {
		// testing
		for (Reservation r: INSTANCE.rl) {
			System.out.println(r);
		}
	}

}
