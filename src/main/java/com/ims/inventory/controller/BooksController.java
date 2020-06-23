package com.ims.inventory.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ims.inventory.model.Book;
import com.ims.inventory.service.BooksService;

@RestController
@RequestMapping("/books")
public class BooksController{	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	@Autowired
	BooksService booksService;
	
	@GetMapping("/getAllBooks")
	private List<Book> getAllBooks() {
		return booksService.getAllBooks();
	}	
	
	@GetMapping("/getBookById/{bookId}")
	private Book getBookById(@PathVariable("bookId") Long bookId)  {
		return booksService.getBookById(bookId);
	}
	
	@GetMapping("/getBookByName/{bookName}")
	private List<Book> getBookByName(@PathVariable("bookName") String bookName)  {
		return booksService.getBookByName(bookName);
	}
	
	@PostMapping("/deleteBook/{bookId}")	
	private ResponseEntity<String> deleteBook(@PathVariable("bookId") Long bookId)  {
		booksService.deleteBook(bookId);
		return new ResponseEntity<String>("Deleted successfully",HttpStatus.OK);
	}
	
	@PostMapping("/saveBook")
	private ResponseEntity<Book> saveBook(@RequestBody Book book){
		Book savedBook=booksService.saveBook(book);
		return new ResponseEntity<Book>(savedBook,HttpStatus.OK);
	}
	
	@PutMapping("/updateBook")	
	private ResponseEntity<Book> updateBook(@RequestBody Book book)  {
		Book savedBook=booksService.updateBook(book);
		return new ResponseEntity<Book>(savedBook,HttpStatus.OK);
	}
	
	
	
}