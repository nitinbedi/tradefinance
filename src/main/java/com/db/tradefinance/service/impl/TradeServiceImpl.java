package com.db.tradefinance.service.impl;

import javax.annotation.PostConstruct;

import com.db.tradefinance.common.exception.DataAccessException;
import com.db.tradefinance.common.exception.ServiceException;
import com.db.tradefinance.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.tradefinance.common.service.impl.GenericServiceImpl;
import com.db.tradefinance.dao.TradeDao;
import com.db.tradefinance.model.Trade;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradeServiceImpl extends GenericServiceImpl<Trade> implements TradeService {

	@Autowired
	protected TradeDao tradeDao;
	private static final Logger LOG = LoggerFactory.getLogger(Trade.class);

	@PostConstruct
	void init() {
        init(Trade.class, tradeDao);
    }
	@Override
	public Trade add(Trade obj) throws ServiceException {
		obj.setExpired();
		return super.add(obj);
	}
	@Override
	public Trade edit(Trade obj) throws ServiceException {
		obj.setExpired();
		return super.edit(obj);
	}
	public List getExpiredTrades() throws ServiceException {
		try{
			return tradeDao.getExpiredTrades();
		}catch(DataAccessException de){
			throw translateException(de);
		}

	}
	public boolean tradeVersionUpsert(Trade obj) throws ServiceException {
		try{
			LOG.info("obj : "+obj.getId());

			List<Trade> trades = tradeDao.getEqOrHigherVersionTrades(obj);
			if(trades!=null && trades.size()>1)
			{
				return false;
			}
			else if(trades!=null && trades.size()==1)
			{
				if(obj.getVersion()< trades.get(0).getVersion())
					return false;
				if(obj.getVersion()== trades.get(0).getVersion()) {
					obj.setId(trades.get(0).getId());
					edit(obj);
				}
				else
				{
					add(obj);
				}
				return  true;

			}
			else
			{
				LOG.info("obj ******** why duplicate? "+obj.getId());
				add(obj);
				return true;
			}


		}catch(DataAccessException de){
			throw translateException(de);
		}

	}

}
