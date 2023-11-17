package utility.calendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {

    /**
     * Main date variable to get everything about calendar.
     */
    private Date date = new Date();

    /**
     * Get AM or PM at time now.
     *
     * @return String
     */
    public static String getDayMark() {
        DateFormat pattern = new SimpleDateFormat("a");
        return pattern.format(new Date());
    }

    /**
     * Get hours with format hh (00 01... 11 12).
     *
     * @return String
     */
    public static String getHours() {
        DateFormat pattern = new SimpleDateFormat("hh");
        return pattern.format(new Date());
    }

    /**
     * Get minutes with format mm.
     *
     * @return String
     */
    public String getMinutes() {
        DateFormat pattern = new SimpleDateFormat("mm");
        return pattern.format(new Date());
    }

    /**
     * Get days with format EEEE (Monday,Tuesday,..., Sunday).
     *
     * @return String
     */
    public static String getDays() {
        DateFormat pattern = new SimpleDateFormat("EEEE");
        return pattern.format(new Date());
    }

    public static java.sql.Date getDateOf(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return new java.sql.Date(dateFormat.parse(date).getTime());
    }
}
