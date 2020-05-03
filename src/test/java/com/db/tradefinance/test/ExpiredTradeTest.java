package com.db.tradefinance.test;

import com.db.tradefinance.UpdateExpiredTrades;
import com.db.tradefinance.common.exception.ServiceException;
import com.db.tradefinance.common.utils.DateUtils;
import com.db.tradefinance.controller.mvc.TradeController;
import com.db.tradefinance.model.Trade;
import com.db.tradefinance.service.impl.TradeServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class ExpiredTradeTest {
    @InjectMocks
    UpdateExpiredTrades updateExpiredTrades;
    @Mock
    TradeServiceImpl tradeService;
    private static final Logger LOG = LoggerFactory.getLogger(ExpiredTradeTest.class);
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void updateExpiredTradesTest() throws ServiceException {
        List<Trade> trades = new ArrayList<Trade>();
        Trade trade = new Trade();
        trade.setId("6");
        trade.setTradeId("T1");
        trade.setVersion(2);
        trade.setCounterPartyID("CP-1");
        trade.setBookid("BI1");
        trade.setMaturityDate(DateUtils.currentDate());
        trades.add(trade);

        trade = new Trade();
        trade.setId("5");
        trade.setTradeId("T1");
        trade.setVersion(1);
        trade.setCounterPartyID("CP-1");
        trade.setBookid("BI1");
        trade.setMaturityDate(DateUtils.currentDate());
        trades.add(trade);

        when(tradeService.edit(trade)).thenReturn(trade);

        when(tradeService.getExpiredTrades()).thenReturn(trades);

        /*updateExpiredTrades.updateExpiredTrades();

        Assert.assertTrue(trades.get(0).getExpired().equals("N"));
        Assert.assertFalse(trades.get(1).getExpired().equals("Y"));
*/
    }

}
