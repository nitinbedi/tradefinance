package com.db.tradefinance;

import com.db.tradefinance.common.exception.ServiceException;
import com.db.tradefinance.common.utils.DateUtils;
import com.db.tradefinance.controller.mvc.TradeController;
import com.db.tradefinance.model.Trade;
import com.db.tradefinance.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class UpdateExpiredTrades {
    private static final Logger LOG = LoggerFactory.getLogger(TradeController.class);
    @Autowired
    private TradeService tradeService;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0 0 12 * * ?")
    public void updateExpiredTrades()  throws ServiceException {
        LOG.info("The time is now {}", dateFormat.format(new Date()));
        List<Trade> trades = null;

        do {
            trades = tradeService.getExpiredTrades();
            LOG.info("total expired trades are {}",trades.size());
            if(trades!=null && trades.size()>0) {
                for (Trade trade : trades) {
                    updateExpiredTrade( trade);
                }
            }
        }
        while (trades!=null && trades.size()>0);

    }
    @Async
    private void updateExpiredTrade(Trade trade)  throws ServiceException {
        LOG.info("Trades getting executed asynchronously"+trade.toString());
        trade.setExpired();
        tradeService.edit(trade);
    }
}
