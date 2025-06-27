package com.ItsNHussain.Database.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Builder
@Entity
@Table(name = "authors")
public class AuthorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "author_id_seq")
	private Long id;
	
	private String name;
	
	private Integer age;

	protected AuthorEntity() {
	    // Required by JPA
	}

	
	public AuthorEntity(long id, String name, int age) {
		this.setId(id);
		this.setName(name);
		this.setAge(age);
	}
	
	public AuthorEntity(String name, int age) {
	    this.name = name;
	    this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public boolean equals(Object other) {
		if(this == other) {
			return true;
		}
		if (other == null || getClass() != other.getClass()) {
			return false;
		}
		AuthorEntity author = (AuthorEntity) other;
		return id != null && id.equals(author.id);
	}
	
	@Override
	public int hashCode() {
		return Long.hashCode(id);
		
	}
	

	
}
