package com.ims.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.inventory.model.Book;
import com.ims.inventory.model.BooksSupplier;
import com.ims.inventory.repository.BooksRepository;
import com.ims.inventory.repository.BooksSupplierRepository;

@Service
public class BooksService{
	
	@Autowired
	BooksRepository booksRepository;	
	
	
	public List<Book> getAllBooks(){
		List<Book> bookList=new ArrayList<Book>();
		booksRepository.findAll().forEach(b->bookList.add(b));		
		return bookList;
	}
	
		
	public Book getBookById(Long bookId){
		Book book=new Book();
		book=booksRepository.findById(bookId).get();		
		return book;
	}
	
	public List<Book> getBookByName(String bookName){
		List<Book> bookList=new ArrayList<Book>();
		booksRepository.findByName(bookName).forEach(b->bookList.add(b));	
	    return bookList;
	}
}