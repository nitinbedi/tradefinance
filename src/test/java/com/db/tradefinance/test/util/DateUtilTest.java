package com.db.tradefinance.test.util;

import com.db.tradefinance.common.utils.DateUtils;
import com.db.tradefinance.controller.mvc.TradeController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateUtilTest {
    private static final Logger LOG = LoggerFactory.getLogger(TradeController.class);
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void presentOrFutureDateTest() throws ParseException {
        boolean result = DateUtils.presentOrFutureDate(new Date());
        assertTrue(result);

        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1998");
        result = DateUtils.presentOrFutureDate(date1);
        assertFalse(result);

        date1=new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2021");
        result = DateUtils.presentOrFutureDate(date1);
        assertTrue(result);
    }


}
