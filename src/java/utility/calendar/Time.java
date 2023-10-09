package utility.calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
    /**
     * Main date variable to get everything about calendar.
     */
    private final Date date = new Date();

    /**
     * Get AM or PM at time now.
     *
     * @return String
     */
    public String getDayMark() {
        DateFormat pattern = new SimpleDateFormat("a");
        return pattern.format(date);
    }

    /**
     * Get hours with format hh (00 01... 11 12).
     *
     * @return String
     */
    public String getHours() {
        DateFormat pattern = new SimpleDateFormat("hh");
        return pattern.format(date);
    }

    /**
     * Get minutes with format mm.
     *
     * @return String
     */
    public String getMinutes() {
        DateFormat pattern = new SimpleDateFormat("mm");
        return pattern.format(date);
    }

    /**
     * Get days with format EEEE (Monday,Tuesday,..., Sunday).
     *
     * @return String
     */
    public String getDays() {
        DateFormat pattern = new SimpleDateFormat("EEEE");
        return pattern.format(date);
    }
}
