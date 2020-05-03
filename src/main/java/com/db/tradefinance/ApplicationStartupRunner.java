package com.db.tradefinance;


import com.db.tradefinance.controller.mvc.TradeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value=1)
@Component
    public class ApplicationStartupRunner implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationStartupRunner.class);

    @Autowired
    UpdateExpiredTrades updateExpiredTrades;
    
        @Override
        public void run(String... args) throws Exception {
            LOG.info("*************************Application Started !!");
            updateExpiredTrades.updateExpiredTrades();
        }
    }

