package com.ItsNHussain.Database.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ItsNHussain.Database.dao.AuthorDao;
import com.ItsNHussain.Database.dao.impl.BookDaoImpl;
import com.ItsNHussain.Database.domain.Author;
import com.ItsNHussain.Database.domain.Book;

@SpringBootTest
@ExtendWith(SpringExtension.class )
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTests {

	private BookDaoImpl underTest;
	private AuthorDao authorDao;
	
	@Autowired
	public BookDaoImplIntegrationTests(BookDaoImpl underTest,AuthorDao authorDao) {
		this.underTest = underTest;
		this.authorDao = authorDao;
	}
	
	@Test
	public void testThatBookCanBeCreatedAndRecalled() {
		Author author = new Author(1L,"Bob Marley",40);
		Book book = new Book("1234","test title",1L);
		authorDao.create(author);
		underTest.create(book);
		Optional<Book> result = underTest.findOne(book.getIsbn());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(book);
	}
	
	@Test
	public void testThatMultipleBooksCanBeCreatedAndRecalled() {
		Author author = new Author(1L,"Bob Marley",40);
		authorDao.create(author);
		Book bookA = new Book("1234","test title",1L);
		Book bookB = new Book("1235","test title2",1L);
		Book bookC = new Book("1236","test title3",1L);
		underTest.create(bookA);
		underTest.create(bookB);
		underTest.create(bookC);
		List<Book> result = underTest.find();
		assertThat(result).hasSize(3).containsExactly(bookA,bookB,bookC);
	}
	@Test
	public void testThatBookCanBeUpdated() {
		Author author = new Author(1L,"Bob Marley",40);
		authorDao.create(author);
		Book book = new Book("1234","test title",1L);
		underTest.create(book);
		book.setTitle("updated title");
		underTest.update(book.getIsbn(), book);
		
		Optional<Book> result = underTest.findOne(book.getIsbn());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(book);
		
	}
	
	@Test
	public void testThatBookCanBeDeleted() {
		Author author = new Author(1L,"Bob Marley",40);
		authorDao.create(author);
		Book book = new Book("1234","test title",1L);
		underTest.create(book);
		underTest.delete(book.getIsbn());
		Optional<Book> result = underTest.findOne(book.getIsbn());
		assertThat(result).isEmpty();
	}
	
}
