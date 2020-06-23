package com.ims.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ims.inventory.model.Book;
import com.ims.inventory.model.BooksSupplier;
import com.ims.inventory.repository.BooksRepository;
import com.ims.inventory.repository.BooksSupplierRepository;

@Service
public class BooksService{
	
	@Autowired
	BooksRepository booksRepository;	
	
	@Cacheable("allBooks")	
	public List<Book> getAllBooks(){
		System.out.println("********Sssssssssssss");
		List<Book> bookList=new ArrayList<Book>();
		booksRepository.findAll().forEach(b->bookList.add(b));		
		return bookList;
	}
	
	@Cacheable(value="bookById", key="#p0")	
	public Book getBookById(Long bookId){
		System.out.println("comingggggggggggggggg");
		Book book=booksRepository.findById(bookId).get();		
		return book;
	}
	
	public List<Book> getBookByName(String bookName){
		List<Book> bookList=new ArrayList<Book>();
		booksRepository.findByName(bookName).forEach(b->bookList.add(b));	
	    return bookList;
	}
	
	@CacheEvict(value = { "allBooks", "bookById" }, allEntries = true)
	public void deleteBook(Long bookId){
		booksRepository.deleteById(bookId);			
	}
	
	
	public Book saveBook(Book book){		
		return booksRepository.save(book);
	}
	
	@CachePut(value= "bookById", key="#p0")	
	public Book updateBook(Book book){	
		Book bk=booksRepository.findById(book.getId()).get();	
		if(bk!=null){
			return booksRepository.save(book);
		}
		return null;
		
	}

}