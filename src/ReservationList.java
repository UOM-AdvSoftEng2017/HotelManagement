import java.util.ArrayList;

public enum ReservationList {
	RL;
	
	private ArrayList<Reservation> rl;
	
	private ReservationList() {
		rl = new ArrayList<Reservation>();
		update(); // populate the reservation list when creating it for the 1st time
	}
	
	public ArrayList<Reservation> getRL() {
		return rl;
	}
	
	public Reservation getReservation(int id) {
	    for (Reservation r: RL.rl) {
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
		for (Reservation r: RL.rl) {
			System.out.println(r);
		}
	}

}
