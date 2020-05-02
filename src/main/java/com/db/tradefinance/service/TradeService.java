package com.db.tradefinance.service;

import com.db.tradefinance.common.exception.ServiceException;
import com.db.tradefinance.common.service.GenericService;
import com.db.tradefinance.model.Trade;

import java.util.List;

public interface TradeService extends GenericService<Trade>{
    public List<Trade> getExpiredTrades() throws ServiceException;
    public boolean tradeVersionUpsert(Trade obj) throws ServiceException;
}
