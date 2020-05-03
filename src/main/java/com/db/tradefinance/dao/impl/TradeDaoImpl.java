package com.db.tradefinance.dao.impl;

import com.db.tradefinance.common.exception.DataAccessException;
import com.db.tradefinance.common.utils.DateUtils;
import com.db.tradefinance.config.AppConfiguration;
import com.db.tradefinance.dao.TradeDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.db.tradefinance.common.dao.impl.GenericDaoImpl;
import com.db.tradefinance.model.Trade;

import java.util.List;

@Repository
public class TradeDaoImpl extends GenericDaoImpl<Trade> implements TradeDao {
	private static final Logger logger = LoggerFactory.getLogger(TradeDaoImpl.class);
	@Autowired
	private AppConfiguration appConfiguration;

	public TradeDaoImpl() {
		super(Trade.class);
	}

	public List<Trade> getExpiredTrades() throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("type {} getById", Trade.class);
		try{
			return mongoOperations.find(new Query(Criteria.where("MaturityDate").lt(DateUtils.currentDate()).
					and("Expired").is("N")).limit(appConfiguration.getRecordfetchlimit()), Trade.class);
		}catch(Exception e){
			throw new DataAccessException(e);
		}
	}

	public List<Trade> getEqOrHigherVersionTrades(final Trade trade) throws DataAccessException {
			logger.info("getEqOrHigherVersionTrades : "+trade.getTradeId());

		try{
		    Query query = new Query(Criteria.where("tradeId").is(trade.getTradeId()).
                    and("version").gte(trade.getVersion()));

			return mongoOperations.find(query, Trade.class);
		}catch(Exception e){
			throw new DataAccessException(e);
		}
	}

	public Trade getByTradeIdAndVersion(String tradeId, Integer version) throws DataAccessException
	{

		try{
			Query query = new Query(Criteria.where("tradeId").is(tradeId).
					and("version").is(version));
			return mongoOperations.findOne(query, Trade.class);
		}catch(Exception e){
			throw new DataAccessException(e);
		}
	}

}
