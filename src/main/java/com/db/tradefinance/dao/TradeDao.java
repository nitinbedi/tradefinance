package com.db.tradefinance.dao;

import com.db.tradefinance.common.dao.GenericDao;
import com.db.tradefinance.common.exception.DataAccessException;
import com.db.tradefinance.model.Trade;

import java.util.List;

public interface TradeDao extends GenericDao<Trade>{
    public List<Trade> getExpiredTrades() throws DataAccessException;
    public List<Trade> getEqOrHigherVersionTrades(Trade trade) throws DataAccessException;
    public Trade getByTradeIdAndVersion(String tradeId, Integer version) throws DataAccessException;
}
