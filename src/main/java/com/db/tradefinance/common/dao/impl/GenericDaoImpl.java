package com.db.tradefinance.common.dao.impl;

import java.util.List;

import com.db.tradefinance.common.exception.DataAccessException;
import com.db.tradefinance.common.dao.GenericDao;
import com.db.tradefinance.config.AppConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Pageable;

@CacheConfig(cacheResolver="dynamicCacheResolver")
public class GenericDaoImpl<T> implements GenericDao<T> {

	private static final Logger logger = LoggerFactory.getLogger(GenericDaoImpl.class);

	@Autowired
	protected MongoOperations mongoOperations;
	@Autowired
	private AppConfiguration appConfiguration;

	private Class<T> type;

	public GenericDaoImpl(Class<T> type) {
		this.type = type;
	}

	@Override
	@Caching(evict = { 
			@CacheEvict(cacheResolver="dynamicCacheResolver", allEntries = true)
		}
	)
	public void add(T object) throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("type {} add", type);
		try {
			mongoOperations.insert(object);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	@Caching(evict = { 
			@CacheEvict(cacheResolver="dynamicCacheResolver", allEntries = true), 
			@CacheEvict(cacheResolver="dynamicCacheResolver", key="#object.id") 
		}
	)
	public void modify(T object) throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("type {} modify", type);
		try {
			mongoOperations.save(object);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	public void modifyMany(List<T> object) throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("type {} modify", type);
		try {
			mongoOperations.save(object);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	@Cacheable(key="#id")
	public T getById(Object id) throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("type {} getById", type);
		try{
			return mongoOperations.findById(id, type);
		}catch(Exception e){
			throw new DataAccessException(e);
		}
	}
	
	@Override
	@Cacheable(key="#id")
	public List<T> getAllById(Object id) throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("type {} getById", type);
		try{
			return mongoOperations.find(new Query(Criteria.where("id").is(id)), type);
		}catch(Exception e){
			throw new DataAccessException(e);
		}
	}

	@Override
	@Cacheable
	public List<T> getAll() throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("type {} getAll", type);

		try {
			return mongoOperations.findAll(type);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	public List<T> getAll(int pageNo) throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("type {} getAll", type);
		pageNo= pageNo<=0 ? 1:pageNo;
		Query query = new Query();
		int firstRecord = (pageNo-1)*appConfiguration.getRecordsPerPage();

		try {
			logger.info("firstRecord ************* "+firstRecord);
			logger.info("lastRecord ************* "+appConfiguration.getRecordsPerPage());
			query.skip(firstRecord);
			query.limit(appConfiguration.getRecordsPerPage());
			return mongoOperations.find(query, type);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
	@Override
	public void delete(T object) throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("type {} delete", type);
		try {
			mongoOperations.remove(object);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}

	}

	@Override
	public MongoOperations getMongoOperations() {
		return mongoOperations;
	}
}
