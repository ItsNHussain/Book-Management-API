package com.ItsNHussain.Database.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ItsNHussain.Database.domain.BookEntity;

public interface BookService {
	BookEntity createBook(String isbn,BookEntity book);

	List<BookEntity> findAll();
	
	Page<BookEntity> findAll(Pageable pageable);

	Optional<BookEntity> findOne(String isbn);

	boolean isExists(String isbn);

	BookEntity partialUpdate(String isbn, BookEntity bookEntity);

	void delete(String isbn);

}
