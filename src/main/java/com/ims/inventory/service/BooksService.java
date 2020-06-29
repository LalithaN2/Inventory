package com.ims.inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ims.inventory.model.Book;
import com.ims.inventory.model.dto.BookDTO;
import com.ims.inventory.repository.BooksRepository;

@Service
public class BooksService{
	
	@Autowired
	BooksRepository booksRepository;	
	
	@Cacheable("allBooks")	
	public List<Book> getAllBooks(){
		List<Book> bookList=new ArrayList<>();
		booksRepository.findAll().forEach(b->bookList.add(b));		
		return bookList;
	}
	
	@Cacheable(value="bookById", key="#p0")	
	public Optional<Book> getBookById(Long bookId){
		return booksRepository.findById(bookId);
	}
	
	public List<Book> getBookByName(String bookName){
		List<Book> bookList=new ArrayList<>();
		booksRepository.findByName(bookName).forEach(b->bookList.add(b));	
	    return bookList;
	}
	
	@CacheEvict(value = { "allBooks", "bookById" }, allEntries = true)
	public void deleteBook(Long bookId){
		booksRepository.deleteById(bookId);			
	}
	
	
	public Book saveBook(BookDTO bookDTO){
		Book book=new Book();
		book.setName(bookDTO.getBookName());
		book.setVolumeNumber(bookDTO.getBookVolumeNumber());
		return booksRepository.save(book);
	}
	
	@CachePut(value= "bookById", key="#p0")	
	public Book updateBook(BookDTO bookDTO){	
		Optional<Book> bk=booksRepository.findById(bookDTO.getBookId());
		Book book=new Book();
		if(bk.isPresent()) {
		book=bk.get();
		book.setName(bookDTO.getBookName());
		book.setVolumeNumber(bookDTO.getBookVolumeNumber());
		return booksRepository.save(book);
		}
		return book;
		
	}

}