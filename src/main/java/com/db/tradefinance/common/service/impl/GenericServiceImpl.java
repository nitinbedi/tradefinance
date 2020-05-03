package com.db.tradefinance.common.service.impl;

import java.util.List;

import com.db.tradefinance.common.dao.GenericDao;
import com.db.tradefinance.common.exception.DataAccessException;
import com.db.tradefinance.common.exception.ServiceException;
import com.db.tradefinance.common.model.EntityBase;
import com.db.tradefinance.common.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericServiceImpl<T> implements GenericService<T> {
	private static final Logger LOG = LoggerFactory.getLogger(GenericServiceImpl.class);

	@SuppressWarnings("unused")
	private Class<? extends T> type;
	protected GenericDao<T> genericDao;
	protected void init(Class<? extends T> type, GenericDao<T> dao) {
        this.type = type;
        this.genericDao = dao;
    }
	
	@Override
	public T getById(Object id) throws ServiceException {
		try{
			return genericDao.getById(id);
		}catch(DataAccessException de){
			throw translateException(de);
		}
	}
	
	@Override
	public List<T> getAll() throws ServiceException{
		try{
			return genericDao.getAll();
		}catch(DataAccessException de){
			throw translateException(de);
		}
	}
	@Override
	public List<T> getAll(int pageNo) throws ServiceException{
		try{
			LOG.info("pageNo : "+pageNo);
			return pageNo>0 ? genericDao.getAll(pageNo) : genericDao.getAll();

		}catch(DataAccessException de){
			throw translateException(de);
		}
	}
	@Override
	public T add(T obj) throws ServiceException{
		try{
			EntityBase eb = (EntityBase)obj;
			eb.setCreatedDate();
			if(eb.getId()!=null && eb.getId().equals(""))
				eb.setId(null);
			genericDao.add(obj);
			return obj;
		}catch(DataAccessException de){
			throw translateException(de);
		}
	}

	@Override
	public T edit(T obj) throws ServiceException{
		try{
			EntityBase eb = (EntityBase)obj;
			eb.setCreatedDate(((EntityBase)getById(eb.getId())).getCreatedDate());
			eb.setModifiedDate();
			genericDao.modify(obj);
			return obj;
		}catch(DataAccessException de){
			throw translateException(de);
		}
	}

	@Override
	public boolean delete(T object) throws ServiceException{
		try{
			genericDao.delete(object);
			return true;
		}catch(DataAccessException de){
			throw translateException(de);
		}
	}

	@Override
	public ServiceException translateException(DataAccessException de) {
		return new ServiceException(de);
	}

}
