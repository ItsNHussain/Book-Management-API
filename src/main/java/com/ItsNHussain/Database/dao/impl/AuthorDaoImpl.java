package com.ItsNHussain.Database.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ItsNHussain.Database.dao.AuthorDao;
import com.ItsNHussain.Database.domain.Author;

@Component
public class AuthorDaoImpl implements AuthorDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	public AuthorDaoImpl(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void create(Author author) {
		jdbcTemplate.update("INSERT INTO authors (id,name,age) VALUES (?,?,?)",author.getId(),author.getName(),author.getAge());
		
	}
	
	public static class AuthorRowMapper implements RowMapper<Author>{

		@Override
		public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Author(rs.getLong("id"),rs.getString("name"),rs.getInt("age"));
		}
		
	}

	@Override
	public Optional<Author> findOne(Long id) {
		List<Author> results = jdbcTemplate.query("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1",new AuthorRowMapper(),id);
		
		return results.stream().findFirst();
	}

	@Override
	public List<Author> find() {
		return jdbcTemplate.query("SELECT id, name, age FROM authors",new AuthorRowMapper());
	}

	@Override
	public void update(Long id, Author author) {
		jdbcTemplate.update("UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",author.getId(),author.getName(),author.getAge(),id);
		
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM authors where id = ?",id);
		
	}

}
