package com.ItsNHussain.Database.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ItsNHussain.Database.domain.BookEntity;
import com.ItsNHussain.Database.domain.dto.BookDto;
import com.ItsNHussain.Database.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {
	
	private MockMvc mockMvc;
	private ObjectMapper objectMapper;
	private BookService bookService;

	@Autowired
	public BookControllerIntegrationTests(MockMvc mockMvc, BookService bookService) {
		this.mockMvc = mockMvc;
		this.objectMapper = new ObjectMapper();
		this.bookService = bookService;
	}
	
	@Test
	public void testThatCreateBookReturnsHttpStatus201Created() throws Exception {
		BookDto bookDto = new BookDto("1234","title",null);
		String bookJSON = objectMapper.writeValueAsString(bookDto);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/books/"+bookDto.getIsbn()).contentType(MediaType.APPLICATION_JSON).content(bookJSON)).andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void testThatCreateBookReturnsCreatedBookDto() throws Exception {
		BookDto bookDto = new BookDto("1234","title",null);
		String bookJSON = objectMapper.writeValueAsString(bookDto);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/books/"+bookDto.getIsbn()).contentType(MediaType.APPLICATION_JSON).content(bookJSON)
			    ).andExpect(
			    		MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
			    ).andExpect(
			    		MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
			   );
				  
	}
	@Test
	public void testThatListBooksReturnsHttpStatus200Ok() throws Exception {
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/books")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testThatListBooksReturnsBooks() throws Exception{
		BookEntity bookEntity = new BookEntity("1234","title",null);
		bookService.createBook(bookEntity.getIsbn(),bookEntity);
		mockMvc.perform(MockMvcRequestBuilders.get("/books").contentType(MediaType.APPLICATION_JSON)
			    ).andExpect(
			    		MockMvcResultMatchers.jsonPath("$[0].isbn").value(bookEntity.getIsbn())
			    ).andExpect(
			    		MockMvcResultMatchers.jsonPath("$[0].title").value(bookEntity.getTitle())
			   );
				  
	}
	
	@Test
	public void testThatgetBookReturnsHttpStatus200OkWhenBookExists() throws Exception {
		BookEntity bookEntity = new BookEntity("1234","title",null);
		bookService.createBook(bookEntity.getIsbn(),bookEntity);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/books/" + bookEntity.getIsbn())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testThatgetBookReturnsHttpStatus404WhenBookDoesNotExist() throws Exception {
		BookEntity bookEntity = new BookEntity("1234","title",null);
		mockMvc.perform(MockMvcRequestBuilders.get("/books/" + bookEntity.getIsbn())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void testThatUpdateBookReturnsHttpStatus200Ok() throws Exception {
		BookEntity testBookEntityA = new BookEntity("1234","title",null);
		BookEntity savedBookEntity = bookService.createBook(testBookEntityA.getIsbn(),testBookEntityA);
		
		BookDto testBookA = new BookDto("1234","title",null);
		testBookA.setIsbn(savedBookEntity.getIsbn());
		String bookJSON = objectMapper.writeValueAsString(testBookA);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/books/" + savedBookEntity.getIsbn())
				.contentType(MediaType.APPLICATION_JSON).content(bookJSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}
	
	@Test
	public void testThatUpdateBookReturnsUpdatedBook() throws Exception {
		BookEntity testBookEntityA = new BookEntity("1234","title",null);
		BookEntity savedBookEntity = bookService.createBook(testBookEntityA.getIsbn(),testBookEntityA);
		
		BookDto testBookA = new BookDto("1234"," updated",null);
		testBookA.setIsbn(savedBookEntity.getIsbn());
		String bookJSON = objectMapper.writeValueAsString(testBookA);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/books/"+savedBookEntity.getIsbn()).contentType(MediaType.APPLICATION_JSON).content(bookJSON)
			    ).andExpect(
			    		MockMvcResultMatchers.jsonPath("$.isbn").value(savedBookEntity.getIsbn())
			    ).andExpect(
			    		MockMvcResultMatchers.jsonPath("$.title").value(testBookA.getTitle())
			   );
				  
	}
	
	@Test
	public void testThatPartialUpdateReturnsHttpStatus200Ok() throws Exception {
		BookEntity testBookEntityA = new BookEntity("1234","title",null);
		BookEntity savedBookEntity = bookService.createBook(testBookEntityA.getIsbn(),testBookEntityA);
		
		BookDto testBookA = new BookDto("1234"," updated",null);
		testBookA.setIsbn(savedBookEntity.getIsbn());
		String bookJSON = objectMapper.writeValueAsString(testBookA);
		
		mockMvc.perform(MockMvcRequestBuilders.patch("/books/" + savedBookEntity.getIsbn())
				.contentType(MediaType.APPLICATION_JSON).content(bookJSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testThatPartialUpdateReturnsUpdatedBook() throws Exception {
		BookEntity testBookEntityA = new BookEntity("1234","title",null);
		BookEntity savedBookEntity = bookService.createBook(testBookEntityA.getIsbn(),testBookEntityA);
		
		BookDto testBookA = new BookDto("1234"," updated",null);
		testBookA.setIsbn(savedBookEntity.getIsbn());
		String bookJSON = objectMapper.writeValueAsString(testBookA);
		
		mockMvc.perform(MockMvcRequestBuilders.patch("/books/" + savedBookEntity.getIsbn())
				.contentType(MediaType.APPLICATION_JSON).content(bookJSON))
		.andExpect(
	    		MockMvcResultMatchers.jsonPath("$.isbn").value(savedBookEntity.getIsbn())
	    ).andExpect(
	    		MockMvcResultMatchers.jsonPath("$.title").value(testBookA.getTitle())
	   );
		  
	}
	
	@Test
	public void testThatDeleteNonExistantBookReturnsHttpStatus204NoContent() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/books/djijdaijad").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	@Test
	public void testThatDeleteExistantBookReturnsHttpStatus204NoContent() throws Exception {
		BookEntity testBookEntityA = new BookEntity("1234","title",null);
		BookEntity savedBookEntity = bookService.createBook(testBookEntityA.getIsbn(),testBookEntityA);
		mockMvc.perform(MockMvcRequestBuilders.delete("/books/"+ savedBookEntity.getIsbn()).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	

}
