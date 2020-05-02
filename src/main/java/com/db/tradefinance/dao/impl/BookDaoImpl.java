package com.db.tradefinance.dao.impl;

import com.db.tradefinance.dao.BookDao;
import com.db.tradefinance.model.Book;
import com.db.tradefinance.common.dao.impl.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl extends GenericDaoImpl<Book> implements BookDao {

	public BookDaoImpl() {
		super(Book.class);
	}
}
