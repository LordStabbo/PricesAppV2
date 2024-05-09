package com.app.domain.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FormatUtil {
    static String pattern = "yyyy-MM-dd HH.mm.ss";
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

    public static LocalDateTime dateParser(String date) {
        try {
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalDateFormatException("ERROR: The date format introduced is Illegal");
        }
    }

    public static String toFormat(LocalDateTime myTime) {
        return myTime.format(formatter);
    }

    public static String formatDouble(Double myNum) {
        return String.format("%.2f", myNum);
    }
}
