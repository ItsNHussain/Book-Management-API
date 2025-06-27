package com.ItsNHussain.Database.domain.dto;


public class BookDto {
	
	private String isbn;
	
	private String title;
	
	private AuthorDto author;
	
	public BookDto() {}
	
	public BookDto(String isbn, String title, AuthorDto author) {
		this.setIsbn(isbn);
		this.setTitle(title);
		this.setAuthor(author);
		
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AuthorDto getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDto author) {
		this.author = author;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if(other == null || getClass() != other.getClass()) {
			return false;
		}
		
		BookDto book = (BookDto) other;
		return this.isbn == book.isbn;
		
	}
	
	@Override
	public int hashCode() {
		return isbn.hashCode();
	}


}
