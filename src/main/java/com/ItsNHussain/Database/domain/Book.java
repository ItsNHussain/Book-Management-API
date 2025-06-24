package com.ItsNHussain.Database.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
	
	private String isbn;
	private String title;
	private Long authorId;
	
	public Book(String isbn, String title, long authorId) {
		this.setIsbn(isbn);
		this.setTitle(title);
		this.setAuthorId(authorId);
		
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

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if(other == null || getClass() != other.getClass()) {
			return false;
		}
		
		Book book = (Book) other;
		return this.isbn == book.isbn;
		
	}
	
	@Override
	public int hashCode() {
		return isbn.hashCode();
	}

}
