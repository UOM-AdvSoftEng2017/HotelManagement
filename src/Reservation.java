import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
	
	private int id;
	private Date start;
	private Date end;
	private String cID; // client ID
	private String rID; // room ID
	
	// used when creating a reservation. The reservation ID has not yet
	// been created by the DB
	public Reservation(Date start, Date end, String cID, String rID) {
		this.id = -1;
		this.start = start;
		this.end = end;
		this.cID = cID;
		this.rID = rID;
	}

	// used when reading a reservation from the DB
	public Reservation(int id, Date start, Date end, String cID, String rID) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.cID = cID;
		this.rID = rID;
	}
	
	public boolean valid() {
	    for (Reservation r: ReservationList.RL.getRL()) {
	        if (this.rID.equals(r.rID)){
	            if ((this.getEnd().compareTo(r.getStart()) > 0) && (this.getEnd().compareTo(r.getEnd()) <= 0)) {
	                return false;
	            }
                if ((r.getEnd().compareTo(this.getStart()) > 0) && (r.getEnd().compareTo(this.getEnd()) <= 0)) {
                    return false;
                }
	        }
	    }
	    return true;
	}

	// add the room to the DB
	public int addToDB() {
		return DBManager.addReservation(this);
	}

	// update the DB with the room's details
	public int updateDB() {
		return DBManager.updateReservation(this);
	}

	// delete the room from the DB
	public int deleteFromDB() {
	    if (this.id == -1) return 3;
		return DBManager.deleteReservation(this);
	}
	
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", start=" + start + ", end=" + end + ", cID=" + cID + ", rID=" + rID + "]";
	}

	public int getId() {
		return id;
	}
	
	public Date getStart() {
		return start;
	}
	
	// returns the start date formatted as a string
	// (we don't care about time of day)
	public String getStartDateString() {
		DateFormat df = new SimpleDateFormat("d/M/yyyy");
		return df.format(this.start);
	}
	
	public void setStart(Date start) {
		this.start = start;
	}
	
	public Date getEnd() {
		return end;
	}
	
	// returns the end date formatted as a string
	// (we don't care about time of day)
	public String getEndDateString() {
		DateFormat df = new SimpleDateFormat("d/M/yyyy");
		return df.format(this.end);
	}
	
	public void setEnd(Date end) {
		this.end = end;
	}
	
	public String getcID() {
		return cID;
	}
	
	public void setcID(String cID) {
		this.cID = cID;
	}
	
	public String getrID() {
		return rID;
	}
	
	public void setrID(String rID) {
		this.rID = rID;
	}

	public static void main(String[] args) throws ParseException {
		// testing
		int retval;
		System.out.println("Creating a new reservation:");
		String sStart = "13/01/2017";
		String sEnd = "17/01/2017";
		// convert string dates to actual dates
		DateFormat format = new SimpleDateFormat("d/M/yyyy");
		Date start = format.parse(sStart);
		Date end = format.parse(sEnd);
		Reservation r = new Reservation(start, end, "AB123456", "201");
		if (r.valid()) System.out.println("Reservation is valid");
		else System.out.println("Reservation is invalid");
		System.out.println("Creating a new reservation:");
        sStart = "10/01/2017";
        sEnd = "15/01/2017";
        start = format.parse(sStart);
        end = format.parse(sEnd);
        r = new Reservation(start, end, "AB123456", "201");
        //Reservation invalid = new Reservation(start, end, "AB123456", "201");
        //if (invalid.valid()) System.out.println("Reservation is valid");
        //else System.out.println("Reservation is invalid");
		retval = r.addToDB();
		if (retval == 0) {
			System.out.println("OK");
		} else {
			System.out.println("NOK");
		}
		System.out.println("Updating the reservation list.");
		ReservationList.RL.update();
	    System.out.println("Getting the last reservation from the DB");
	    r = ReservationList.RL.getRL().get(ReservationList.RL.getRL().size() - 1);
		System.out.println(r);
		System.out.println("Changing the start date:");
		sStart = "9/1/2017";
		start = format.parse(sStart);
		r.setStart(start);
		retval = r.updateDB();
		if (retval == 0) {
			System.out.println("OK");
		} else {
			System.out.println("NOK");
		}
		System.out.println(r);
		System.out.println("Changing the end date:");
		sEnd = "17/1/2017";
		end = format.parse(sEnd);
		r.setEnd(end);
		retval = r.updateDB();
		if (retval == 0) {
			System.out.println("OK");
		} else {
			System.out.println("NOK");
		}
		System.out.println(r);
		System.out.println("Changing room:");
		r.setrID("202");
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
