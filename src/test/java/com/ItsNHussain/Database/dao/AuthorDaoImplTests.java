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

import com.ItsNHussain.Database.dao.impl.AuthorDaoImpl;
import com.ItsNHussain.Database.domain.Author;


@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {

	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private AuthorDaoImpl undertest;
	
	@Test
	public void testThatCreateAuthorGeneratesCorrectSql() {
		Author author = new Author(1L,"Abigail Rose",80);
		
		undertest.create(author);
		verify(jdbcTemplate).update(eq("INSERT INTO authors (id,name,age) VALUES (?,?,?)"),eq(1L),eq("Abigail Rose"),eq(80));
	}
	
	@Test
	public void testThatFindOneGeneratesTheCorrectSql() {
		undertest.findOne(1L);
		verify(jdbcTemplate).query(eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),eq(1L));
		
	}
	
	@Test 
	public void testThatFindManyGeneratesCorrectSql() {
		undertest.find();
		verify(jdbcTemplate).query(eq("SELECT id, name, age FROM authors"),ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any());
		
	}
	
	@Test
	public void testThatUpdateGeneratesCorrectSql() {
		Author author = new Author(1L,"Abigail Rose",80);
		undertest.update(1L,author);
		verify(jdbcTemplate).update("UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",1L,"Abigail Rose",80,1L);
	}
	
	@Test
	public void testThatDeleteGeneratesCorrectSql() {
		undertest.delete(1L);
		verify(jdbcTemplate).update("DELETE FROM authors where id = ?",1L);
	}
	
}
