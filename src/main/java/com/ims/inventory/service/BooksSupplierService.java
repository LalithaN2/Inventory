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
public class BooksSupplierService{
	
	@Autowired
	BooksSupplierRepository booksSupplierRepository;		
	
		
	public BooksSupplier getSupplierById(Long supplierId){
		BooksSupplier booksSupplier=new BooksSupplier();
		booksSupplier=booksSupplierRepository.findById(supplierId).get();		
		return booksSupplier;
	}
	
	
}