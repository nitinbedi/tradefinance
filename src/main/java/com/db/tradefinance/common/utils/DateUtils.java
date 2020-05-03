package com.db.tradefinance.common.utils;

import com.db.tradefinance.controller.mvc.TradeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

public class DateUtils  {
    private static final Logger LOG = LoggerFactory.getLogger(DateUtils.class);
    public static boolean presentOrFutureDate(final Date date) {
        if(date==null) return false;
        LOG.info("***** check future validations ******* "+(date) + " " + (currentDate() ));
        LOG.info("***** check future validations 2 ******* "+(date.compareTo(currentDate() )) + " " +
                                                                                        date.after(currentDate() ));
        // Your date must be after today or today (== not before today)

        return ((date.compareTo(currentDate() ) == 0)
                || date.after(currentDate() ));

    }

    public static Date currentDate() {
        return removeTimeFromDate(null);
    }
    public static Date removeTimeFromDate(final Date date ) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND,0);
        if(date!=null)
            calendar.setTime(date);
        return calendar.getTime();
    }
}
