package com.ims.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ims.inventory.model.Book;
import com.ims.inventory.service.BooksService;

@RestController
@RequestMapping("/books")
public class BooksController{	
	
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
	
	
}