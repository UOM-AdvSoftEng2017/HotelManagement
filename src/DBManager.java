import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
 * A singleton for managing the sqlite database we're using.
 * You can run SQL statements on the DB using:
 * 		DBManager.DB.runStatement(yourStatement);
 */

public enum DBManager {
	DB;

	// the database file to use
	final String filename = "hotel.db";
	
	private Connection c;
	private Statement s;

	// a database connection should be opened once, when the application starts
	private DBManager() {
		try {
			// open DB connection
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + filename);
			c.setAutoCommit(false);
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}

	// this closes the database connection. Should be run when the application
	// is exited
	public static int close() {
		try {
			// close DB connection
			DB.c.close();
			return 0;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
			return 1;
		}
	}

	// commits all pending changes to the DB
	public int commit() {
		try {
			DB.c.commit();
			return 0;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return 1;
		}
	}

	// runs any SQL statement provided as a string argument
	public static int run(String sqlStatement) {
		try {
			DB.s = DB.c.createStatement();
			DB.s.executeUpdate(sqlStatement);
			DB.s.close();
			DB.c.commit();
			return 0;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return 1;
		}	
	}
	
	// returns a list of clients from the DB
	public static ArrayList<Client> getClientList() {
		try {
			DB.s = DB.c.createStatement();
			ArrayList<Client> cl = new ArrayList<Client>();
			ResultSet rs = DB.s.executeQuery("select * from Client;");
			while (rs.next() ) {
				String id = rs.getString("cid");
				String name = rs.getString("name");
				int phone = rs.getInt("phone");
				Client c = new Client(id, name, phone);
				cl.add(c);
			}
			rs.close();
			DB.s.close();
			return cl;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return null;
		}
	}
	
	// Add a Client in the DB
	public static int addClient(Client c) {
		StringBuilder s = new StringBuilder();
		s.append("insert into Client (cid, name, phone) values (\"");
		s.append(c.getId());
		s.append("\", \"");
		s.append(c.getName());
		s.append("\", ");
		s.append(c.getPhone());
		s.append(");");
		return run(new String(s));
	}

	// Update Client details in the DB
	public static int updateClient(Client c) {
		StringBuilder s = new StringBuilder();
		s.append("update Client set name = \"");
		s.append(c.getName());
		s.append("\", phone = ");
		s.append(c.getPhone());
		s.append(" where cid == \"");
		s.append(c.getId());
		s.append("\";");
		return DBManager.run(new String(s));
	}

	// delete the Client from the DB
	public static int deleteClient(String cId) {
		StringBuilder s = new StringBuilder();
		s.append("delete from Client where cid == \"");
		s.append(cId);
		s.append("\";");
		return DBManager.run(new String(s));
	}

	// returns a list of rooms from the DB
	public static ArrayList<Room> getRoomList() {
		try {
			DB.s = DB.c.createStatement();
			ArrayList<Room> rl = new ArrayList<Room>();
			ResultSet rs = DB.s.executeQuery("select * from room;");
			while (rs.next() ) {
				String id = rs.getString("rid");
				int type = rs.getInt("type");
				Room r = new Room(id, type);
				rl.add(r);
			}
			rs.close();
			DB.s.close();
			return rl;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return null;
		}
	}
	
	// Add a Room in the DB
	public static int addRoom(Room r) {
		StringBuilder s = new StringBuilder();
		s.append("insert into room (rid, type) values (\"");
		s.append(r.getId());
		s.append("\", \"");
		s.append(r.getType());
		s.append("\");");
		return run(new String(s));
	}

	// Update room details (room type) in the DB
	public static int updateRoom(Room r) {
		StringBuilder s = new StringBuilder();
		s.append("update room set type = \"");
		s.append(r.getType());
		s.append("\" where rid == \"");
		s.append(r.getId());
		s.append("\";");
		return DBManager.run(new String(s));
	}

	// delete the Client from the DB
	public static int deleteRoom(Room r) {
		StringBuilder s = new StringBuilder();
		s.append("delete from room where rid == \"");
		s.append(r.getId());
		s.append("\";");
		return DBManager.run(new String(s));
	}

    // returns a list of room types from the DB
    public static ArrayList<RoomType> getRoomTypeList() {
        try {
            DB.s = DB.c.createStatement();
            ArrayList<RoomType> rtl = new ArrayList<RoomType>();
            ResultSet rs = DB.s.executeQuery("select * from roomtype;");
            while (rs.next() ) {
                int id = rs.getInt("rtid");
                String name = rs.getString("name");
                long price = rs.getLong("price");
                RoomType rt = new RoomType(id, name, price);
                rtl.add(rt);
            }
            rs.close();
            DB.s.close();
            return rtl;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
        }
    }

    // Add a RoomType in the DB
    public static int addRoomType(RoomType rt) {
        StringBuilder s = new StringBuilder();
        s.append("insert into roomtype (name, price) values (\"");
        s.append(rt.getName());
        s.append("\", \"");
        s.append(rt.getPrice());
        s.append("\");");
        return run(new String(s));
    }

    // Update room type details in the DB
    public static int updateRoomType(RoomType rt) {
        StringBuilder s = new StringBuilder();
        s.append("update roomtype set name = \"");
        s.append(rt.getName());
        s.append("\", price = \"");
        s.append(rt.getPrice());
        s.append("\" where rtid == \"");
        s.append(rt.getId());
        s.append("\";");
        return DBManager.run(new String(s));
    }

    // delete the Client from the DB
    public static int deleteRoomType(RoomType rt) {
        StringBuilder s = new StringBuilder();
        s.append("delete from roomtype where rtid == \"");
        s.append(rt.getId());
        s.append("\";");
        return DBManager.run(new String(s));
    }

	// returns a list of reservations from the DB
	public static ArrayList<Reservation> getReservationList() {
		try {
			DB.s = DB.c.createStatement();
			ArrayList<Reservation> rl = new ArrayList<Reservation>();
			ResultSet rs = DB.s.executeQuery("select * from reservation;");
			while (rs.next() ) {
				int id = rs.getInt("resid");
				String sStart = rs.getString("startdate");
				String sEnd = rs.getString("enddate");
				// convert string dates to actual dates
				DateFormat format = new SimpleDateFormat("d/M/yyyy");
				Date start = format.parse(sStart);
				Date end = format.parse(sEnd);
				String cID = rs.getString("Clientid");
				String rID = rs.getString("roomid");
				long price = rs.getLong("Price");
				int paid = rs.getInt("Paid");
				Reservation r = new Reservation(id, start, end, cID, rID, price, paid);
				rl.add(r);
			}
			rs.close();
			DB.s.close();
			return rl;
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return null;
		}
	}
	
	// Add a reservation in the DB
	public static int addReservation(Reservation r) {
	    int paid = 0;
	    if (r.isPaid()) paid = 1;
		StringBuilder s = new StringBuilder();
		s.append("insert into reservation (startdate, enddate, Clientid, roomid, price, paid) values (\"");
		s.append(r.getStartDateString());
		s.append("\", \"");
		s.append(r.getEndDateString());
		s.append("\", \"");
		s.append(r.getcID());
		s.append("\", \"");
		s.append(r.getrID());
		s.append("\", \"");
		s.append(r.getPrice());
		s.append("\", \"");
		s.append(paid);
		s.append("\");");
		return run(new String(s));
	}

	// Update reservation details in the DB
	public static int updateReservation(Reservation r) {
	    int paid = 0;
	    if (r.isPaid()) paid = 1;
		StringBuilder s = new StringBuilder();
		s.append("update reservation set startdate = \"");
		s.append(r.getStartDateString());
		s.append("\", enddate = \"");
		s.append(r.getEndDateString());
		s.append("\", Clientid = \"");
		s.append(r.getcID());
		s.append("\", roomid = \"");
		s.append(r.getrID());
		s.append("\", price = \"");
		s.append(r.getPrice());
		s.append("\", paid = \"");
		s.append(paid);
		s.append("\" where resid == \"");
		s.append(r.getId());
		s.append("\";");
		return DBManager.run(new String(s));
	}

	// delete a reservation from the DB
	public static int deleteReservation(Reservation r) {
		StringBuilder s = new StringBuilder();
		s.append("delete from reservation where resid == \"");
		s.append(r.getId());
		s.append("\";");
		return DBManager.run(new String(s));
	}
	
	public static void main(String[] args) {

	}
}
