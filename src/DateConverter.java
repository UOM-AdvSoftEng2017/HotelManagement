import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

// just a small class to facilitate converting between different date formats
public class DateConverter {

    // converts LocalDate to Date
    public static Date getDate(LocalDate l) {
        int day = l.getDayOfMonth();
        int month = l.getMonthValue();
        int year = l.getYear();
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return cal.getTime();
    }
}
