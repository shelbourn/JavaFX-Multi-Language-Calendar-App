/*
 * Various helper methods
 */
package utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class HelperMethods {

    // Global Properties
    public static Locale userLocale;

    // Retrieve User Locale
    public static Locale getUserLocale() {
        return userLocale = Locale.getDefault();
    }

    // Date & Time Methods
    // Converts LocalDate & LocalTime to LocalDateTime
    public static LocalDateTime convertToLDT(LocalDate ld, LocalTime lt) {
        return LocalDateTime.of(ld, lt);
    }

    // Formats LocalDateTime as String for database insertion
    public static String formatLDTString(LocalDateTime ldt) {
        DateTimeFormatter formattedLDTString = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        return formattedLDTString.format(ldt);
    }

    // Converts LocalDateTime to TimeStamp for database insertion
    public static Timestamp ldtToTimestamp(LocalDateTime ldt) {
        return Timestamp.valueOf(ldt);
    }

    // Converts LocalTime (24hr) into LocalTime (12hr)
    public static String twelveHrTime(LocalTime lt) {
        DateTimeFormatter formatTwelveHrTime = DateTimeFormatter.ofPattern("hh:mm a");
        return formatTwelveHrTime.format(lt);
    }

    // Converts 12-hour Time String to LocalTime
    public static LocalTime stringToLT(String timeString) {
        DateTimeFormatter stringToLT = DateTimeFormatter.ofPattern("hh:mm a");
        return LocalTime.parse(timeString, stringToLT);
    }

    // Gets Current Local Date/Time and User's Zone Id and returns a Zoned Date Time
    public static ZonedDateTime currentUserZDT() {
        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        ZoneId zId = ZoneId.systemDefault();
        ZonedDateTime userZDT = ZonedDateTime.of(ld, lt, zId);
        return userZDT;
    }

}
