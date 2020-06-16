package com.ims.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ims.inventory.model.Book;

public interface BooksRepository extends CrudRepository<Book, Long>{	
	
	 List<Book> findByName(String bookName);
	
}
