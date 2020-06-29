package com.ims.inventory.service;

import java.lang.invoke.MethodHandles;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.inventory.commons.BooksConstants;
import com.ims.inventory.model.Book;
import com.ims.inventory.model.BooksPurchaseOrderDtl;
import com.ims.inventory.model.BooksPurchaseOrderHdr;
import com.ims.inventory.model.BooksPurchaseOrderHolder;
import com.ims.inventory.model.BooksSupplier;
import com.ims.inventory.repository.BooksPurchasesOrderHdrRepository;
import com.ims.inventory.restclients.BooksSupplierClient;

@Service
public class BooksPurchaseOrderService{
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	@Autowired
	BooksPurchasesOrderHdrRepository booksPurchasesOrderHdrRepository;	

	
	@Autowired
	BooksSupplierClient booksSupplierClient;


	@Autowired
	BooksService booksService;
	
	public List<BooksPurchaseOrderHdr> getPurchaseOrderByCreateDt(String dateStr) throws ParseException {
		logger.info("service: getPurchaseOrderByCreateDt");
		Date purchaseOrderDate = null;
		if(dateStr!=null){
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd"); 
			 purchaseOrderDate=formatter.parse(dateStr);
		}
		return booksPurchasesOrderHdrRepository.findByCreateDt(purchaseOrderDate);		
	}
	
	public List<BooksPurchaseOrderHdr> getAllPurchaseOrder(){		
		logger.info("service: getAllPurchaseOrder");		
		return booksPurchasesOrderHdrRepository.findAll();		
	}
		
	public BooksPurchaseOrderHdr saveBooksPurchaseOrder(Long supplierId,Set<BooksPurchaseOrderHolder> booksPurchaseOrderHolderSet) {
		logger.info("service: saveBooksPurchaseOrder");
		Set<BooksPurchaseOrderDtl> booksPurchaseOrderDtlSet=new HashSet<>();
		
		booksPurchaseOrderHolderSet.forEach(h->{
			BooksPurchaseOrderDtl dtl=new BooksPurchaseOrderDtl();
			Book book=booksService.getBookById(h.getBookId()).get();
     		dtl.setBook(book);
     		dtl.setQuantity(h.getQuantity());
     		dtl.setUnitPrice(h.getUnitPrice());
     		booksPurchaseOrderDtlSet.add(dtl);
		
		});
		BooksSupplier booksSupplier=booksSupplierClient.getSupplierById(supplierId);
		
		BooksPurchaseOrderHdr booksPurchaseOrderHdr=new BooksPurchaseOrderHdr(booksPurchaseOrderDtlSet,booksSupplier);
		booksPurchaseOrderHdr.getBooksPurchaseOrderDtlSet().forEach(
				pod->booksPurchaseOrderHdr.addPohId(pod)	                                                   
				);		
		booksPurchaseOrderHdr.setStatus(BooksConstants.STATUS_NEW);
		booksPurchasesOrderHdrRepository.save(booksPurchaseOrderHdr);
		return booksPurchaseOrderHdr;
	}
	
	public BooksPurchaseOrderHdr updatePurchaseOrder(Long purchaseId,String status) {
		logger.info("service: updatePurchaseOrder");		
		BooksPurchaseOrderHdr booksPurchaseOrderHdr=new BooksPurchaseOrderHdr();
		Optional<BooksPurchaseOrderHdr> booksPurchaseOrderHdrChk=booksPurchasesOrderHdrRepository.findById(purchaseId);
		if(booksPurchaseOrderHdrChk.isPresent()) {
			booksPurchaseOrderHdr=booksPurchaseOrderHdrChk.get();
			if(booksPurchaseOrderHdr.getStatus().equals(BooksConstants.STATUS_NEW)) {		
				booksPurchaseOrderHdr.setStatus(status);
				if(status.equals(BooksConstants.STATUS_RECEIVED)) {
					booksPurchaseOrderHdr.getBooksPurchaseOrderDtlSet().forEach(dtl->
						dtl.updateStock(status)
					);
				}			
			}else if(booksPurchaseOrderHdr.getStatus().equals(BooksConstants.STATUS_RECEIVED)) {
				booksPurchaseOrderHdr.setStatus(status);
				if(status.equals(BooksConstants.STATUS_CANCELLED)) {
					booksPurchaseOrderHdr.getBooksPurchaseOrderDtlSet().forEach(dtl->
						dtl.updateStock(status)
					);
				}
			}
			booksPurchasesOrderHdrRepository.save(booksPurchaseOrderHdr);
		}
				
		return booksPurchaseOrderHdr;
	}
	
	
}