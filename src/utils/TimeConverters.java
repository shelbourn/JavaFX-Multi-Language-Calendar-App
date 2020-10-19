/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

        return Timestamp.valueOf(utcZDT.toLocalDateTime());

    }

    // Method to convert UTC Timestamp to LocalDateTime (in user's locale)
    public static LocalDateTime utcTimestampToLDT(LocalDate ld, LocalTime lt, Timestamp ts) {

        ZoneId zId = ZoneId.systemDefault();
        ZoneId utcZId = ZoneId.of("UTC");
        ZonedDateTime localZDT = ZonedDateTime.of(ld, lt, zId);
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(localZDT.toInstant(), utcZId);

        return localZDT.ofInstant(utcZDT.toInstant(), zId).toLocalDateTime();
    }
}
