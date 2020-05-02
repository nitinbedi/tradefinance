package com.db.tradefinance.service.impl;

import javax.annotation.PostConstruct;

import com.db.tradefinance.service.BookService;
import com.db.tradefinance.dao.BookDao;
import com.db.tradefinance.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.tradefinance.common.service.impl.GenericServiceImpl;

@Service
public class BookServiceImpl extends GenericServiceImpl<Book> implements BookService {

	@Autowired
	protected BookDao bookDao;
	
	@PostConstruct
	void init() {
        init(Book.class, bookDao);
    }
	
}
