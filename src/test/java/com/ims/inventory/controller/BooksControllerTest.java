package com.ims.inventory.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ims.inventory.model.Book;
import com.ims.inventory.model.dto.BookDTO;
import com.ims.inventory.service.BooksService;


    @RunWith(SpringJUnit4ClassRunner.class)
    @WebMvcTest(value=BooksController.class)
	
	public class BooksControllerTest{
    	List<Book> bookList=new ArrayList<Book>();
    	Book book=new Book();
		@Autowired		
		MockMvc mockmvc;
		
		@MockBean
		BooksService booksService;
		
		
	    void setValues() {
		book.setId(1L);
		book.setName("New Book");
		book.setQuantity(10);
		book.setVolumeNumber(1);
		book.setUnitPrice(100);
		bookList.add(book);
		
	    }
						
		@Test
		@WithMockUser(roles="ADMIN")
		 public void getAllBooks() throws Exception {		
			setValues();
            String uri="/books/getAllBooks";
		    Mockito.when(booksService.getAllBooks()).thenReturn(bookList);
		   
		    RequestBuilder requestBuilder=MockMvcRequestBuilders
		    		.get(uri).accept(MediaType.APPLICATION_JSON);
		    MvcResult results=mockmvc.perform(requestBuilder).andReturn();
		    
		    MockHttpServletResponse response=results.getResponse();
		    String output=results.getResponse().getContentAsString();
		 
		    assertThat(output).isEqualTo(mapToJson(bookList));
		    assertEquals(HttpStatus.OK.value(), response.getStatus());
		}
		
		@Test
		@WithMockUser(roles="ADMIN")
		 public void getBookById() throws Exception {		
			setValues();
			book.setId(1L);
			book.setName("New Book");
			book.setQuantity(10);
			book.setVolumeNumber(1);
			book.setUnitPrice(100);
			
			Optional bkOpt=Optional.ofNullable(book); 
			
            String uri="/books/getBookById/2";
		    Mockito.when(booksService.getBookById(2L)).thenReturn(bkOpt);
		   
		    RequestBuilder requestBuilder=MockMvcRequestBuilders
		    		.get(uri).accept(MediaType.APPLICATION_JSON);
		   
		    MvcResult results=mockmvc.perform(requestBuilder).andReturn();
		    
		    MockHttpServletResponse response=results.getResponse();
		    String output=results.getResponse().getContentAsString();
		 
		    assertThat(output).isEqualTo(mapToJson(book));
		    assertEquals(HttpStatus.OK.value(), response.getStatus());
		}
		
		@Test
		@WithMockUser(roles="ADMIN")
		 public void getBookByName() throws Exception {		
			setValues();
			Optional bkOpt=Optional.ofNullable(book); 
			
            String uri="/books/getBookByName/mybook";
		    Mockito.when(booksService.getBookByName("mybook")).thenReturn(bookList);
		   
		    RequestBuilder requestBuilder=MockMvcRequestBuilders
		    		.get(uri).accept(MediaType.APPLICATION_JSON);
		   
		    MvcResult results=mockmvc.perform(requestBuilder).andReturn();
		    
		    MockHttpServletResponse response=results.getResponse();
		    String output=results.getResponse().getContentAsString();		 
		    assertThat(output).isEqualTo(mapToJson(bookList));
		    assertEquals(HttpStatus.OK.value(), response.getStatus());
		}
		
		@Test
		@WithMockUser(roles="ADMIN")
		 public void deleteBook() throws Exception {		
		
			 String uri="/books/deleteBook/5";
	        Mockito.doNothing().when(booksService).deleteBook(5L);;
		   
		    RequestBuilder requestBuilder=MockMvcRequestBuilders
		    		.post(uri).accept(MediaType.APPLICATION_JSON);
		   
		    MvcResult results=mockmvc.perform(requestBuilder).andReturn();		    
		    MockHttpServletResponse response=results.getResponse();	
		    
		    assertEquals(HttpStatus.OK.value(), response.getStatus());
		}
		@Test
		@WithMockUser(roles="ADMIN")
		 public void saveBook() throws Exception {	
			setValues();
		 	BookDTO bookDTO=new BookDTO();
		 	bookDTO.setBookId(1L);
		 	bookDTO.setBookName("test");
		 	bookDTO.setBookVolumeNumber(1);
			
		 	String uri="/books/saveBook";
	        Mockito.when(booksService.saveBook(bookDTO)).thenReturn(book);
	        RequestBuilder requestBuilder=MockMvcRequestBuilders
		    		.post(uri).accept(MediaType.APPLICATION_JSON).content(mapToJson(bookDTO)).contentType(MediaType.APPLICATION_JSON);
		   
		    MvcResult results=mockmvc.perform(requestBuilder).andReturn();		    
		    MockHttpServletResponse response=results.getResponse();		   
		  
		    assertEquals(HttpStatus.OK.value(), response.getStatus());
		}
		
		@Test
		@WithMockUser(roles="ADMIN")
		 public void updateBook() throws Exception {	
			setValues();
		 	BookDTO bookDTO=new BookDTO();
		 	bookDTO.setBookId(1L);
		 	bookDTO.setBookName("test");
		 	bookDTO.setBookVolumeNumber(1);
			
		 	String uri="/books/updateBook";
	        Mockito.when(booksService.updateBook(bookDTO)).thenReturn(book);
	        RequestBuilder requestBuilder=MockMvcRequestBuilders
		    		.put(uri).accept(MediaType.APPLICATION_JSON).content(mapToJson(bookDTO)).contentType(MediaType.APPLICATION_JSON);
		   
		    MvcResult results=mockmvc.perform(requestBuilder).andReturn();		    
		    MockHttpServletResponse response=results.getResponse();		   
		  
		    assertEquals(HttpStatus.OK.value(), response.getStatus());
		}
		
		
		private String mapToJson(Object obj) throws JsonProcessingException {
			ObjectMapper objMapper=new ObjectMapper();
			return objMapper.writeValueAsString(obj);
		}
	}


