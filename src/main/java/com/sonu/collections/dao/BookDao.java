package com.sonu.collections.dao;

import com.sonu.collections.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends CrudRepository<Book, Integer> {
	
	public Iterable<Book> findByBookName(String bookName);
	
	//select * from book join publisher join book_publisher

	
	//Named Query Section
	public Iterable<Book> retireveByBookName(String bookName);
}
