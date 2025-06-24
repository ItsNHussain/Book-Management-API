package com.ItsNHussain.Database.dao;

import java.util.List;
import java.util.Optional;

import com.ItsNHussain.Database.domain.Book;

public interface BookDao {
	public void create(Book book);
	public Optional<Book> findOne(String isbn);
	public List<Book> find();
	public void update(String isbn,Book book);
	public void delete(String isbn);

}
