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

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTests {
	
	private AuthorRepository underTest;
	
	@Autowired
	public AuthorRepositoryIntegrationTests (AuthorRepository underTest) {
		this.underTest = underTest;
	}
	
	@Test
	public void testThatAuthorCanBeCreatedAndRecalled() {
		AuthorEntity author = new AuthorEntity("Bob Marley",43);
		underTest.save(author);
		Optional<AuthorEntity> result = underTest.findById(author.getId());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(author);
	}
	
	@Test
	public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
		AuthorEntity authorA = new AuthorEntity("Bob Marley",43);
		underTest.save(authorA);
		AuthorEntity authorB = new AuthorEntity("Jacob Marley",49);
		underTest.save(authorB);
		AuthorEntity authorC = new AuthorEntity("Sarah Marley",35);
		underTest.save(authorC);
		Iterable <AuthorEntity> result = underTest.findAll();
		assertThat(result).hasSize(3) .containsExactly(authorA,authorB,authorC);
	}
	
	@Test
	public void testThatAuthorCanBeUpdated() {
		AuthorEntity authorA = new AuthorEntity("Bob Marley",43);
		underTest.save(authorA);
		authorA.setName("Harry Potter");
		underTest.save(authorA);
		
		Optional<AuthorEntity> result = underTest.findById (authorA.getId());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(authorA);
		
	}
	
	@Test
	public void testThatAuthorCanBeDeleted() {
		AuthorEntity authorA = new AuthorEntity("Bob Marley",43);
		underTest.save(authorA);
		underTest.deleteById(authorA.getId());
		Optional<AuthorEntity> result = underTest.findById(authorA.getId());
		assertThat(result).isEmpty();
	}
	

}
