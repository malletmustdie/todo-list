package ru.job4j.todo.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateFormatUtil {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String parseCreatedDate(Timestamp date) {
        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.format(date);
    }

}
