package com.db.tradefinance.config;

import com.db.tradefinance.controller.mvc.TradeController;
import com.db.tradefinance.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.db.tradefinance.model.Book;
import com.db.tradefinance.service.BookService;

@Component
public class SetupBook {
	private static final Logger LOG = LoggerFactory.getLogger(TradeController.class);

	@Autowired
	public SetupBook(BookService bookService) {
		
		Book r1 = new Book();
		r1.setId("B1");

		Book r2 = new Book();
		r2.setId("B2");
		
		Book r3 = new Book();
		r3.setId("B3");
		
		Book r4 = new Book();
		r4.setId("B4");
		
		try {
			if (bookService.getById("B1")==null)
				bookService.add(r1);
			if (bookService.getById("B2")==null)
				bookService.add(r2);
			if (bookService.getById("B3")==null)
				bookService.add(r3);
			if (bookService.getById("B4")==null)
				bookService.add(r4);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}
	
}
