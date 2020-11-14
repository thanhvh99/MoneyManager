package com.mobile.moneymanager.Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public final class DateUtility {

    public static int compareDate(Calendar cal1, Calendar cal2) {
        if (cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)) {
            return cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        }
        return cal1.get(Calendar.DAY_OF_YEAR) - cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean isSameMonth(Calendar cal1, Calendar cal2) {
        if (cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)) {
            return false;
        }
        return cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH) == 0;
    }

    public static boolean isBetweenDate(Calendar time, Calendar start, Calendar end) {
        return compareDate(time, start) >= 0 && compareDate(time, end) <= 0;
    }

    public static String dateToString(Calendar calendar, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.US);
        return formatter.format(calendar.getTime());
    }

    public static int hashMonth(Calendar calendar) {
        return calendar.get(Calendar.YEAR) * 12 + calendar.get(Calendar.MONTH);
    }
}
