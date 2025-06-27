package com.ItsNHussain.Database.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ItsNHussain.Database.domain.BookEntity;

@Repository
public interface BookRepository extends CrudRepository<BookEntity,String>, PagingAndSortingRepository<BookEntity,String> {

}
