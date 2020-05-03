package com.db.tradefinance.test.service;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.db.tradefinance.common.exception.DataAccessException;
import com.db.tradefinance.common.exception.ServiceException;
import com.db.tradefinance.common.utils.DateUtils;
import com.db.tradefinance.controller.mvc.TradeController;
import com.db.tradefinance.dao.TradeDao;
import com.db.tradefinance.dao.impl.TradeDaoImpl;
import com.db.tradefinance.model.Trade;
import com.db.tradefinance.service.TradeService;
import com.db.tradefinance.service.impl.TradeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TradeServiceImplTest {
    @InjectMocks
    TradeServiceImpl tradeService;

    @Mock
    TradeDaoImpl tradeDao;
    private static final Logger LOG = LoggerFactory.getLogger(TradeServiceImplTest.class);
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void tradeVersionUpsertTest() throws DataAccessException, ServiceException
    {
        List<Trade> lstTrade = new ArrayList<Trade>();
        Trade trade = new Trade();
        trade.setId("5");
        trade.setTradeId("T1");
        trade.setVersion(2);
        trade.setMaturityDate(DateUtils.currentDate());

        lstTrade.add(trade);
        trade = new Trade();
        trade.setId("6");
        trade.setTradeId("T1");
        trade.setVersion(1);
        trade.setMaturityDate(DateUtils.currentDate());

        when(tradeDao.getEqOrHigherVersionTrades(trade)).thenReturn(lstTrade);
        when(tradeDao.getById("6")).thenReturn(trade);
        LOG.info(trade.toString());
        boolean result =
        tradeService.tradeVersionUpsert(trade);
        LOG.info("" + result);
        LOG.info(trade.toString());
        assertFalse(result);
    }
    @Test
    public void tradeVersionUpsertTest2() throws DataAccessException, ServiceException
    {
        List<Trade> lstTrade = new ArrayList<Trade>();
        Trade trade = new Trade();

        trade.setId("5");
        trade.setTradeId("T1");
        trade.setVersion(2);
        trade.setMaturityDate(DateUtils.currentDate());
        lstTrade.add(trade);

        trade = new Trade();
        trade.setId("6");
        trade.setTradeId("T1");
        trade.setVersion(1);
        trade.setMaturityDate(DateUtils.currentDate());

        lstTrade.add(trade);
        when(tradeDao.getEqOrHigherVersionTrades(trade)).thenReturn(lstTrade);
        when(tradeDao.getById("6")).thenReturn(trade);
        LOG.info(trade.toString());
        boolean result =
                tradeService.tradeVersionUpsert(trade);
        LOG.info("" + result);
        LOG.info(trade.toString());

        assertFalse(result);
    }

    @Test
    public void expiredTradesTest() throws DataAccessException, ServiceException
    {
        List<Trade> lstTrade = new ArrayList<Trade>();
        Trade trade = new Trade();
        trade.setId("5");
        trade.setTradeId("T1");
        trade.setVersion(2);
        trade.setMaturityDate(DateUtils.currentDate());

        lstTrade.add(trade);
        trade = new Trade();
        trade.setId("6");
        trade.setTradeId("T1");
        trade.setVersion(1);
        trade.setMaturityDate(DateUtils.currentDate());
        lstTrade.add(trade);

        when(tradeDao.getExpiredTrades()).thenReturn(lstTrade);
        when(tradeDao.getById("6")).thenReturn(trade);
        LOG.info(trade.toString());
        List result =
                tradeService.getExpiredTrades();
        LOG.info("" + result);
        LOG.info(trade.toString());
        assertArrayEquals(result.toArray(), lstTrade.toArray());
    }

}
