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

import com.ItsNHussain.Database.dao.impl.AuthorDaoImpl;
import com.ItsNHussain.Database.domain.Author;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImplIntegrationTests {
	
	private AuthorDaoImpl underTest;
	
	@Autowired
	public AuthorDaoImplIntegrationTests (AuthorDaoImpl underTest) {
		this.underTest = underTest;
	}
	
	@Test
	public void testThatAuthorCanBeCreatedAndRecalled() {
		Author author = new Author(1L,"Bob Marley",43);
		underTest.create(author);
		Optional<Author> result = underTest.findOne(author.getId());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(author);
	}
	
	@Test
	public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
		Author authorA = new Author(1L,"Bob Marley",43);
		underTest.create(authorA);
		Author authorB = new Author(100L,"Jacob Marley",49);
		underTest.create(authorB);
		Author authorC = new Author(150L,"Sarah Marley",35);
		underTest.create(authorC);
		List<Author> result = underTest.find();
		assertThat(result).hasSize(3) .containsExactly(authorA,authorB,authorC);
	}
	
	@Test
	public void testThatAuthorCanBeUpdated() {
		Author authorA = new Author(1L,"Bob Marley",43);
		underTest.create(authorA);
		authorA.setName("Harry Potter");
		underTest.update(authorA.getId(), authorA);
		
		Optional<Author> result = underTest.findOne(authorA.getId());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(authorA);
		
	}
	
	@Test
	public void testThatAuthorCanBeDeleted() {
		Author authorA = new Author(1L,"Bob Marley",43);
		underTest.create(authorA);
		underTest.delete(1L);
		Optional<Author> result = underTest.findOne(1L);
		assertThat(result).isEmpty();
	}
	

}
