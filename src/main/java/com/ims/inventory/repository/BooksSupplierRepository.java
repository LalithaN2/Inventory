package com.ims.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ims.inventory.model.Book;
import com.ims.inventory.model.BooksSupplier;

public interface BooksSupplierRepository extends CrudRepository<BooksSupplier, Long>{	
	
}
