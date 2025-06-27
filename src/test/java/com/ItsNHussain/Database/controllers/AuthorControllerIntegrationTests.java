package com.ItsNHussain.Database.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ItsNHussain.Database.domain.AuthorEntity;
import com.ItsNHussain.Database.domain.dto.AuthorDto;
import com.ItsNHussain.Database.services.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTests {
	
	private AuthorService authorService;
	private MockMvc mockMvc;
	private ObjectMapper objectMapper;

	@Autowired
	public AuthorControllerIntegrationTests(MockMvc mockMvc,AuthorService authorService) {
		this.mockMvc = mockMvc;
		this.objectMapper = new ObjectMapper();
		this.authorService = authorService;
	}
	
	@Test
	public void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {
		AuthorEntity author = new AuthorEntity("Bob Marley",43);
		String authorJSON = objectMapper.writeValueAsString(author);
		mockMvc.perform(MockMvcRequestBuilders.post("/authors").contentType(MediaType.APPLICATION_JSON).content(authorJSON)).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
		AuthorEntity author = new AuthorEntity("Bob Marley",43);
		String authorJSON = objectMapper.writeValueAsString(author);
		mockMvc.perform(MockMvcRequestBuilders.post("/authors").contentType(MediaType.APPLICATION_JSON).content(authorJSON)
	    ).andExpect(
	    		MockMvcResultMatchers.jsonPath("$.id").isNumber()
	    ).andExpect(
	    		MockMvcResultMatchers.jsonPath("$.name").value("Bob Marley")
	   ).andExpect(
			   MockMvcResultMatchers.jsonPath("$.age").value(43 ));
		
	}
	
	@Test
	public void testThatListAuthorSuccessfullyReturnsHttp200() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/authors")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testThatListAuthorReturnsListOfAuthors() throws Exception {
		AuthorEntity author = new AuthorEntity("Bob Marley",43);
		authorService.save(author);
		mockMvc.perform(MockMvcRequestBuilders.get("/authors").contentType(MediaType.APPLICATION_JSON)
			    ).andExpect(
			    		MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
			    ).andExpect(
			    		MockMvcResultMatchers.jsonPath("$[0].name").value("Bob Marley")
			   ).andExpect(
					   MockMvcResultMatchers.jsonPath("$[0].age").value(43));
				
	}
	
	@Test
	public void testThatGetAuthorReturnsHttp200WhenAuthorExists() throws Exception {
		AuthorEntity author = new AuthorEntity("Bob Marley",43);
		authorService.save(author);
		mockMvc.perform(MockMvcRequestBuilders.get("/authors/1")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testThatGetAuthorReturnsHttp404WhenAuthorDoesNotExist() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/authors/99")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void testThatGetAuthorReturnsAuthorWhenAuthorExists() throws Exception {
		AuthorEntity author = new AuthorEntity("Bob Marley",43);
		authorService.save(author);
		mockMvc.perform(MockMvcRequestBuilders.get("/authors/1").contentType(MediaType.APPLICATION_JSON)
			    ).andExpect(
			    		MockMvcResultMatchers.jsonPath("$.id").value(1)
			    ).andExpect(
			    		MockMvcResultMatchers.jsonPath("$.name").value("Bob Marley")
			   ).andExpect(
					   MockMvcResultMatchers.jsonPath("$.age").value(43));
	} 
	
	@Test
	public void testThatFullUpdateAuthorReturnsHttp404WhenAuthorDoesNotExist() throws Exception {
		AuthorDto author = new AuthorDto("Bob Marley",43);
		String authorJSON = objectMapper.writeValueAsString(author);
		mockMvc.perform(MockMvcRequestBuilders.get("/authors/99")
				.contentType(MediaType.APPLICATION_JSON).content(authorJSON)).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	@Test
	public void testThatFullUpdateAuthorReturnsHttp200WhenAuthorExist() throws Exception {
		AuthorEntity author = new AuthorEntity("Bob Marley",43);
		AuthorEntity savedAuthor = authorService.save(author);
		
		AuthorDto authorTest = new AuthorDto("Bob Hartley",43);
		String authorJSON = objectMapper.writeValueAsString(authorTest);
	
		mockMvc.perform(MockMvcRequestBuilders.get("/authors/"+ savedAuthor.getId())
				.contentType(MediaType.APPLICATION_JSON).content(authorJSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testThatFullUpdateUpdatesExistingAuthor() throws Exception {
		AuthorEntity author = new AuthorEntity("Bob Marley",43);
		AuthorEntity savedAuthor = authorService.save(author);
		
		AuthorDto authorUpdated = new AuthorDto("Bob Hartley",43);
		authorUpdated.setId(author.getId());
		String authorupdatedJSON = objectMapper.writeValueAsString(authorUpdated);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/authors/"+ savedAuthor.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(authorupdatedJSON)
			    ).andExpect(
			    		MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
			    ).andExpect(
			    		MockMvcResultMatchers.jsonPath("$.name").value(authorUpdated.getName())
			   ).andExpect(
					   MockMvcResultMatchers.jsonPath("$.age").value(authorUpdated.getAge()));
	} 
	
	@Test
	public void testThatPartialUpdateExistingAuthorReturnsHttpStatus200Ok() throws Exception {
		AuthorEntity author = new AuthorEntity("Bob Marley",43);
		AuthorEntity savedAuthor = authorService.save(author);
		
		AuthorDto authorTest = new AuthorDto("Bob Hartley",43);
		String authorJSON = objectMapper.writeValueAsString(authorTest);
	
		mockMvc.perform(MockMvcRequestBuilders.patch("/authors/"+ savedAuthor.getId())
				.contentType(MediaType.APPLICATION_JSON).content(authorJSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}
		
	@Test
	public void testThatPartialUpdateExistingAuthorReturnsUpdatedAuthor() throws Exception {
		AuthorEntity author = new AuthorEntity("Bob Marley",43);
		AuthorEntity savedAuthor = authorService.save(author);
		
		AuthorDto authorTest = new AuthorDto("Bob Hartley",43);
		String authorJSON = objectMapper.writeValueAsString(authorTest);
	
		mockMvc.perform(MockMvcRequestBuilders.patch("/authors/"+ savedAuthor.getId())
				.contentType(MediaType.APPLICATION_JSON).content(authorJSON))
		.andExpect(
			    		MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
			    ).andExpect(
			    		MockMvcResultMatchers.jsonPath("$.name").value(authorTest.getName())
			   ).andExpect(
					   MockMvcResultMatchers.jsonPath("$.age").value(authorTest.getAge()));
	}
	
	@Test 
	public void testThatDeleteAuthorReturnsHttpStatus204ForNonExisitingAuthor() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/authors/999").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	@Test 
	public void testThatDeleteAuthorReturnsHttpStatus204ForExisitingAuthor() throws Exception {
		AuthorEntity author = new AuthorEntity("Bob Marley",43);
		AuthorEntity savedAuthor = authorService.save(author);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/authors/"+savedAuthor.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	
	
	
		
		
	}

