package com.db.tradefinance.controller;

import com.db.tradefinance.common.exception.ServiceException;
import com.db.tradefinance.config.AppConfiguration;
import com.db.tradefinance.controller.mvc.TradeController;
import com.db.tradefinance.model.ErrorObject;
import com.db.tradefinance.model.Trade;
import com.db.tradefinance.service.BookService;
import com.db.tradefinance.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.ArrayList;
import java.util.List;

public class TradeBaseController {
    @Autowired
    private TradeService tradeService;
    @Autowired
    private BookService bookService;
    @Autowired
    private AppConfiguration appConfiguration;
    @Autowired
    MessageSource messageSource;


    private static final Logger LOG = LoggerFactory.getLogger(TradeBaseController.class);
    public List<Trade> listPage(Integer pageNo) {
        List<Trade> trades = new ArrayList<>();
        if(pageNo==null)
        {

            pageNo = new Integer(0);
        }
        try{
            LOG.info("Page number is "+pageNo);
            trades = tradeService.getAll(pageNo);
        }catch(Exception e){
            LOG.error(e.getMessage());
        }
        return trades;
    }
    public Trade getSingleTrade(String id) throws ServiceException {
        Trade trade = null;
        trade = !StringUtils.isEmpty(id)? tradeService.getById(id) : new Trade();
        return trade;
    }
    public void saveTrade(Trade trade) throws ServiceException {
        if(!tradeService.tradeVersionUpsert(trade))
        {
            List<ErrorObject> errors = new ArrayList<ErrorObject>();
            ErrorObject error = new ErrorObject("1","trade.Version.conflict","version");
            errors.add(error);
            trade.setErrors(errors);
        }

    }

    public void insertUpdate(Trade trade) throws ServiceException {
       if(StringUtils.isEmpty(trade.getId())){
            trade.setId(null);
            tradeService.add(trade);
        }
        else
          tradeService.edit(trade);
   }
}
