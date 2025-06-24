package com.ItsNHussain.Database.dao;

import java.util.List;
import java.util.Optional;

import com.ItsNHussain.Database.domain.Author;

public interface AuthorDao {
	public void create(Author author);
	public Optional<Author> findOne(Long id);
	public List<Author> find();
	public void update(Long id, Author author);
	public void delete(Long id);
}
