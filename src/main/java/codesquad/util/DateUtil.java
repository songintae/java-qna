package codesquad.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class DateUtil {
    public static String getFormattedDate(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static String getFormatYyyymmddhhmm(Date date){
        return getFormattedDate(date,"yyyy-MM-dd HH:mm");
    }
}
