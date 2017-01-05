import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
	
	private int id;
	private Date start;
	private Date end;
	private String cID; // client ID
	private String rID; // room ID
	
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
}
