package com.ItsNHussain.Database.dao;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ItsNHussain.Database.dao.impl.BookDaoImpl;
import com.ItsNHussain.Database.domain.Author;
import com.ItsNHussain.Database.domain.Book;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {
	
	@Mock
	JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	BookDaoImpl underTest;
	
	@Test 
	public void testThatCreateBookGeneratesCorrectSql() {
		Book book = new Book("1234","test title",1L);
		underTest.create(book);
		
		verify(jdbcTemplate).update(eq("INSERT INTO books (isbn,title,author_id) VALUES(?,?,?)"),eq("1234"),eq("test title"),eq(1L));
	}
	
	@Test
	public void testThatFindOneBookGeneratesCorrectSql() {
		underTest.findOne("1234");
		verify(jdbcTemplate).query(eq("SELECT isbn, title, author_id from books WHERE isbn= ? LIMIT 1"),ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),eq("1234"));
	}
	
	@Test
	public void testThatFindGeneratesCorrectSql() {
		underTest.find();
		verify(jdbcTemplate).query(eq("SELECT isbn, title, author_id from books"),ArgumentMatchers.<BookDaoImpl.BookRowMapper>any());
	}
	
	@Test
	public void testThatUpdateGeneratesCorrectSql() {
		Book book = new Book("1234","test title",1L);
		underTest.update("1234",book);
		
		verify(jdbcTemplate).update("UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?","1234","test title",1L,"1234");
	}
	
	@Test 
	public void testThatDeleteGeneratesCorrectSql() {
		underTest.delete("1234");
		verify(jdbcTemplate).update("DELETE from books where isbn = ?","1234");
	}

}
