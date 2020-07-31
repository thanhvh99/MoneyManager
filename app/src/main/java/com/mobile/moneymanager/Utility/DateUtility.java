package com.mobile.moneymanager.Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public final class DateUtility {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

    public static int compareDate(Calendar cal1, Calendar cal2) {
        if (cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)) {
            return cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        }
        return cal1.get(Calendar.DAY_OF_YEAR) - cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean isBetweenDate(Calendar time, Calendar start, Calendar end) {
        return compareDate(time, start) >= 0 && compareDate(time, end) <= 0;
    }

    public static String getSimpleDateString(Calendar calendar) {
        return formatter.format(calendar.getTime());
    }

}
