package com.ims.inventory.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ims.inventory.model.Book;
import com.ims.inventory.model.dto.BookDTO;
import com.ims.inventory.repository.BooksRepository;



@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value=BooksService.class)

class BooksServiceTest {
	
	
	
	@Autowired
	BooksService bookService;
	
	@MockBean
	BooksRepository booksRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	List<Book> bookList=new ArrayList<Book>();
	Book book=new Book();
	   void setValues() {
			book.setId(1L);
			book.setName("New Book");
			book.setQuantity(10);
			book.setVolumeNumber(1);
			book.setUnitPrice(100);
			bookList.add(book);
			
		    }
	@Test
	void getAllBooksTest() {
		setValues();
		Mockito.when(booksRepository.findAll()).thenReturn(bookList);			
		assertThat(bookService.getAllBooks()).isEqualTo(bookList);
	}
	
	@Test
	void getBookByIdTest() {		
		setValues();
		Optional<Book> bookOpt=Optional.ofNullable(book);
		Mockito.when(booksRepository.findById(1L)).thenReturn(bookOpt);		
		assertThat(bookService.getBookById(1L)).isEqualTo(bookOpt);
	}
	
	@Test
	void getBookByNameTest() {		
		setValues();		
		Mockito.when(booksRepository.findByName("mybook")).thenReturn(bookList);		
		assertThat(bookService.getBookByName("mybook")).isEqualTo(bookList);
	}
	
//	@Test
//	void deleteBookTest() {		
//		setValues();		
//		Mockito.doNothing().when(booksRepository).delete(book);		
//		assertTrue(booksRepository.delete(book));
//	}
	
	@Test
	void saveBookTest() {		
		setValues();	
		BookDTO bookDTO=new BookDTO();
	 	bookDTO.setBookName("New Book");
	 	bookDTO.setBookVolumeNumber(1);		
	 	
	 	Mockito.when(booksRepository.save(Mockito.any())).thenReturn(book);
	 	assertThat(bookService.saveBook(bookDTO)).isEqualTo(book);
		
	}
	
	@Test
	void updateBookTest() {		
		setValues();	
		BookDTO bookDTO=new BookDTO();
		bookDTO.setBookId(1L);
	 	bookDTO.setBookName("New Book");
	 	bookDTO.setBookVolumeNumber(1);	
	 	
	 	book=new Book();
	 	book.setId(bookDTO.getBookId());
	 	book.setName(bookDTO.getBookName());
	 	Optional<Book> bookOpt=Optional.ofNullable(book);
	 	
		Mockito.when(booksRepository.findById(1L)).thenReturn(bookOpt);		 	
	 	Mockito.when(booksRepository.save(bookOpt.get())).thenReturn(book);
	 	assertThat(bookService.updateBook(bookDTO)).isEqualTo(book);
		
	}


}
