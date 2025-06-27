package com.ItsNHussain.Database.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ItsNHussain.Database.domain.AuthorEntity;
@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity,Long> {

}
