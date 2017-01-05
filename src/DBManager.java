import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

// creates a singleton for the Database Manager
public enum DBManager {
	DB;

	// the database file to use
	final String filename = "hotel.db";
	
	// runs any SQL statement provided 
	public static void runStatement(String sqlStatement) {
		Connection c = null;
		Statement s = null;
		try {
			// open DB connection
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + DB.filename);
			  
			// create a statement and run it
			s = c.createStatement();			  
			s.executeUpdate(sqlStatement);
			
			// close DB connection
			s.close();
			c.close();
		} catch ( Exception e ) {
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  System.exit(0);
		}	
	}
	
	public static void createEmptyDB() {
		try {
			// read the DB creation statements from a file. It would be ugly to
			// put them all in a Java string.
			String sql = new String(Files.readAllBytes(Paths.get("create_db.sql")), "utf8");
			runStatement(sql);
		} catch (Exception e) {
			  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			  System.exit(0);
		}


	}
	
	public static void main(String[] args) {
		createEmptyDB();
	}

}
