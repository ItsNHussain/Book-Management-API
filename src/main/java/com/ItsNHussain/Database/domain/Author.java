package com.ItsNHussain.Database.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

	private Long id;
	
	private String name;
	
	private Integer age;
	
	public Author(long id, String name, int age) {
		this.setId(id);
		this.setName(name);
		this.setAge(age);
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
		Author author = (Author) other;
		return id != null && id.equals(author.id);
	}
	
	@Override
	public int hashCode() {
		return Long.hashCode(id);
		
	}
	

	
}
