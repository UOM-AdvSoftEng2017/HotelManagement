import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

// just a small class to facilitate converting between different date formats
public class DateConverter {

    // converts LocalDate to Date
    public static Date getDate(LocalDate l) {
        int day = l.getDayOfMonth();
        int month = l.getMonthValue() - 1;
        int year = l.getYear();
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return cal.getTime();
    }

    // converts Date to LocalDate
    public static LocalDate getLocalDate(Date d) {
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    // compare between two dates. Although it's using deprecated functions, it's
    // the easiest way to compare dates without taking time into account.
    public static int compareDates(Date d1, Date d2) {
        if (d1.getYear() > d2.getYear()) return 1;
        if (d1.getYear() < d2.getYear()) return -1;
        if (d1.getMonth() > d2.getMonth()) return 1;
        if (d1.getMonth() < d2.getMonth()) return -1;
        if (d1.getDate() > d2.getDate()) return 1;
        if (d1.getDate() < d2.getDate()) return -1;
        return 0;
    }

    public static void main(String[] args) throws ParseException {
        // convert from Date to LocalDate
        String ds1 = "13/01/2017";
        DateFormat format = new SimpleDateFormat("d/M/yyyy");
        Date d1 = format.parse(ds1);
        LocalDate ld1 = getLocalDate(d1);
        System.out.println(d1 + " to " + ld1);
        // convert from LocalDate to Date
        LocalDate ld2 = LocalDate.of(2017, 02, 15);
        Date d2 = getDate(ld2);
        System.out.println(ld2 + " to " + d2);
    }
}
