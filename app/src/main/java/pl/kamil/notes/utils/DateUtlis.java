package pl.kamil.notes.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtlis {
    public static String getFormattedDate(long timestamp) {
        Date date = new Date(timestamp);
        String pattern = "MM.dd.yyyy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return simpleDateFormat.format(date);
    }
}
