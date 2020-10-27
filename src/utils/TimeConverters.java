/*
 * Helper Methods for Converting Date/Time Values
 */
package utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 *
 * @author Matthew Shelbourn <mshelbo@wgu.edu>
 */
public class TimeConverters {

    // Method to convert LocalDateTime (in user's locale) to UTC Timestamp
    public static Timestamp ldtToUTCTimestamp(LocalDate ld, LocalTime lt) {

        ZoneId zId = ZoneId.systemDefault();
        ZoneId utcZId = ZoneId.of("UTC");
        ZonedDateTime localZDT = ZonedDateTime.of(ld, lt, zId);
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(localZDT.toInstant(), utcZId);
        Timestamp zonedTS = Timestamp.valueOf(utcZDT.toLocalDateTime());

        return zonedTS;
    }

    // Method to convert UTC Timestamp to LocalDateTime (in user's locale)
    public static LocalDateTime utcTimestampToLDT(Timestamp ts) {

        ZoneId localZId = ZoneId.systemDefault();
        ZoneId utcZId = ZoneId.of("UTC");
        LocalDateTime utcLDT = ts.toLocalDateTime();
        ZonedDateTime utcZDT = utcLDT.atZone(utcZId);
        ZonedDateTime localZDT = utcZDT.withZoneSameInstant(localZId);
        LocalDateTime localLDT = localZDT.toLocalDateTime();

        return localLDT;
    }
}
