package com.db.tradefinance.controller.mvc;

import java.util.ArrayList;
import java.util.List;

import com.db.tradefinance.common.exception.ServiceException;
import com.db.tradefinance.config.AppConfiguration;
import com.db.tradefinance.controller.TradeBaseController;
import com.db.tradefinance.model.Book;
import com.db.tradefinance.service.BookService;
import com.db.tradefinance.service.TradeService;
import com.db.tradefinance.validation.TradeValidations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.db.tradefinance.model.Trade;

@Controller
public class TradeController extends TradeBaseController {

	private static final Logger LOG = LoggerFactory.getLogger(TradeController.class);
	@Autowired
	//@Qualifier("tradeValidations")
	private TradeValidations tradeValidations;

	@Autowired
	private TradeService tradeService;
	@Autowired
	private BookService bookService;
	@Autowired
	private AppConfiguration appConfiguration;
	/*@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(tradeValidations);
	}*/

	@RequestMapping(path = {"/", "/{pageNo}"})
	public String listPage(Model model, @PathVariable(required = false) Integer pageNo){
		LOG.info("path variable value "+pageNo);
		List<Trade> trades = listPage( pageNo);
		model.addAttribute("trades", trades);
		model.addAttribute("config", appConfiguration);
		
		return "list";
	}

	@RequestMapping("/save")
	public String savePage(@RequestParam(value="q", required=false) String id, Model model) throws ServiceException {
		
		Trade trade = getSingleTrade( id);;
		List<Book> books = null;
		try{
			LOG.info("fetching the books details"+bookService.getAll().size());
			books = bookService.getAll();
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
		}
//Arrays.asList(Trade.BOOK_B1, Trade.BOOK_B2)
		LOG.info("fetching the books details"+books.size());
		model.addAttribute("bookList", books);
		model.addAttribute("trade", trade);
		model.addAttribute("config", appConfiguration);
		return "save";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveTrade(@ModelAttribute  @Validated Trade trade, BindingResult bindingResult, Model model)
                                                                                            throws ServiceException {
		List<Book> books = null;
		LOG.info("inside saveTrade method");
        saveTrade( trade);
        tradeValidations.bindErrors(trade, bindingResult);
        LOG.info("bindingResult.getAllErrors().size " + bindingResult.getAllErrors().size());

        if (bindingResult.getAllErrors().size()>0) {
            LOG.info("bindingResult.getAllErrors().getMessage " +
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
			LOG.info("bindingResult.getAllErrors().getCode " +
					bindingResult.getAllErrors().get(0).getCode());
        }

        if (bindingResult.hasErrors()) {
			LOG.info("Returning save page");
			try{
				LOG.info("fetching the books details"+bookService.getAll().size());
				books = bookService.getAll();
				model.addAttribute("bookList", books);
				model.addAttribute("config", appConfiguration);
			}catch(Exception e){
				LOG.error(e.getMessage(), e);
			}
			return "save";
		}
		return "redirect:/";
	}
	
	@RequestMapping("/delete")
	public String deleteBooking(@ModelAttribute Trade trade) throws ServiceException {
		tradeService.delete(trade);
		return "redirect:/";
	}
	
}
