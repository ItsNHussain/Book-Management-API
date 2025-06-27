package com.ItsNHussain.Database.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ItsNHussain.Database.domain.AuthorEntity;
import com.ItsNHussain.Database.domain.BookEntity;

@SpringBootTest
@ExtendWith(SpringExtension.class )
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTests {

	private BookRepository underTest;
	private AuthorRepository authorDao;
	
	@Autowired
	public BookRepositoryIntegrationTests(BookRepository underTest,AuthorRepository authorDao) {
		this.underTest = underTest;
		this.authorDao = authorDao;
	}
	
	@Test
	public void testThatBookCanBeCreatedAndRecalled() {
		AuthorEntity author = new AuthorEntity("Bob Marley",40);
		BookEntity book = new BookEntity("1234","test title",author);
		
		underTest.save(book);
		Optional<BookEntity> result = underTest.findById(book.getIsbn());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(book);
	}
	
	@Test
	public void testThatMultipleBooksCanBeCreatedAndRecalled() {
		AuthorEntity author = new AuthorEntity("Bob Marley",40);
		BookEntity bookA = new BookEntity("1234","test title",author);
		BookEntity bookB = new BookEntity("1235","test title2",author);
		BookEntity bookC = new BookEntity("1236","test title3",author);
		underTest.save(bookA);
		underTest.save(bookB);
		underTest.save(bookC);
		Iterable<BookEntity> result = underTest.findAll();
		assertThat(result).hasSize(3).containsExactly(bookA,bookB,bookC);
	}
//	@Test
	public void testThatBookCanBeUpdated() {
		AuthorEntity author = new AuthorEntity("Bob Marley",40);
		BookEntity book = new BookEntity("1234","test title",author);
		underTest.save(book);
		book.setTitle("updated title");
		underTest.save(book);
		
		Optional<BookEntity> result = underTest.findById(book.getIsbn());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(book);
		
	}
	
	@Test
	public void testThatBookCanBeDeleted() {
		AuthorEntity author = new AuthorEntity("Bob Marley",40);
		BookEntity book = new BookEntity("1234","test title",author);
		underTest.save(book);
		underTest.deleteById(book.getIsbn());
		Optional<BookEntity> result = underTest.findById(book.getIsbn());
		assertThat(result).isEmpty();
	}
	
}



