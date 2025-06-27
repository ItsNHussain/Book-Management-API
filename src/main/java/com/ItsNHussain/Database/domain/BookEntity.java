package com.ItsNHussain.Database.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "books")
public class BookEntity {
	@Id
	private String isbn;
	
	private String title;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "author_id")
	private AuthorEntity author;
	
	public BookEntity() {}
	
	public BookEntity(String isbn, String title, AuthorEntity author) {
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

	public AuthorEntity getAuthor() {
		return author;
	}

	public void setAuthor(AuthorEntity author) {
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
		
		BookEntity book = (BookEntity) other;
		return this.isbn == book.isbn;
		
	}
	
	@Override
	public int hashCode() {
		return isbn.hashCode();
	}

}
