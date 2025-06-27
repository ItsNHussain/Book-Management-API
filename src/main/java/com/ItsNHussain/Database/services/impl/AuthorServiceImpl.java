package com.ItsNHussain.Database.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.ItsNHussain.Database.domain.AuthorEntity;
import com.ItsNHussain.Database.repositories.AuthorRepository;
import com.ItsNHussain.Database.services.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	private AuthorRepository authorRepository;
	
	public AuthorServiceImpl (AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public AuthorEntity save(AuthorEntity author) {
		return authorRepository.save(author);
	}

	@Override
	public List<AuthorEntity> findAll() {
		//convert iterable to list and return it
		return StreamSupport.stream(authorRepository.findAll().spliterator(),false).collect(Collectors.toList());
	}

	@Override
	public Optional<AuthorEntity> findOne(Long id) {
		return authorRepository.findById(id);
	}

	@Override
	public boolean isExists(Long id) {
		return authorRepository.existsById(id);
	}

	@Override
	public AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity) {
		authorEntity.setId(id);
		
		return authorRepository.findById(id).map(existingAuthor -> {
			Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);
			Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthor::setAge);
			return authorRepository.save(existingAuthor);
		}).orElseThrow(()-> new RuntimeException("Author does not exist!"));
		
	}

	@Override
	public void delete(Long id) {
		authorRepository.deleteById(id);
		
	}

}
