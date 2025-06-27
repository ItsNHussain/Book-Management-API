package com.ItsNHussain.Database.domain.dto;


public class AuthorDto {
	
	private Long id;
	
	private String name;
	
	private Integer age;

	protected AuthorDto() {
	    // Required by JPA
	}

	
	public AuthorDto(long id, String name, int age) {
		this.setId(id);
		this.setName(name);
		this.setAge(age);
	}
	
	public AuthorDto(String name, int age) {
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
		AuthorDto author = (AuthorDto) other;
		return id != null && id.equals(author.id);
	}
	
	@Override
	public int hashCode() {
		return Long.hashCode(id);
		
	}
	


}
