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
		DateFormat df = new SimpleDateFormat("yyyy/M/d");
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
		DateFormat df = new SimpleDateFormat("yyyy/M/d");
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
		String sStart = "2017/01/10";
		String sEnd = "2017/01/15";
		// convert string dates to actual dates
		DateFormat format = new SimpleDateFormat("yyyy/M/d");
		Date start = format.parse(sStart);
		Date end = format.parse(sEnd);
		Reservation r = new Reservation(start, end, "AB123456", "201");
		retval = r.addToDB();
		if (retval == 0) {
			System.out.println("OK");
		} else {
			System.out.println("NOK");
		}
		System.out.println(r);
		System.out.println("Changing the start date:");
		sStart = "2017/1/9";
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
		sEnd = "2017/1/17";
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
