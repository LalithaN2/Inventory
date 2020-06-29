package com.ims.inventory.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ims.inventory.model.dto.BookDTO;
import com.ims.inventory.service.BooksService;

@RestController
@RequestMapping("/books")
public class BooksController{	
	
	@Autowired
	BooksService booksService;
	
	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks() {		
		return booksService.getAllBooks();
	}	
	
	@GetMapping("/getBookById/{bookId}")
	public Optional<Book> getBookById(@PathVariable("bookId") Long bookId)  {
		return booksService.getBookById(bookId);
	}
	
	@GetMapping("/getBookByName/{bookName}")
	public List<Book> getBookByName(@PathVariable("bookName") String bookName)  {
		return booksService.getBookByName(bookName);
	}
	
	@PostMapping("/deleteBook/{bookId}")	
	public ResponseEntity<String> deleteBook(@PathVariable("bookId") Long bookId)  {
		booksService.deleteBook(bookId);
		return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
	}
	
	@PostMapping("/saveBook")
	public ResponseEntity<Book> saveBook(@RequestBody BookDTO bookDTO){
		Book savedBook=booksService.saveBook(bookDTO);
		return new ResponseEntity<>(savedBook,HttpStatus.OK);
	}
	
	@PutMapping("/updateBook")	
	public ResponseEntity<Book> updateBook(@RequestBody BookDTO bookDTO)  {
		Book savedBook=booksService.updateBook(bookDTO);
		return new ResponseEntity<>(savedBook,HttpStatus.OK);
	}
	
	
	
}