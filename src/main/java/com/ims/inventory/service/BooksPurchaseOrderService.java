package com.ims.inventory.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.inventory.commons.BooksConstants;
import com.ims.inventory.model.Book;
import com.ims.inventory.model.BooksPurchaseOrderDtl;
import com.ims.inventory.model.BooksPurchaseOrderHdr;
import com.ims.inventory.model.BooksPurchaseOrderHolder;
import com.ims.inventory.model.BooksSupplier;
import com.ims.inventory.repository.BooksPurchasesOrderHdrRepository;

@Service
public class BooksPurchaseOrderService{
	
	@Autowired
	BooksPurchasesOrderHdrRepository booksPurchasesOrderHdrRepository;	

	
	@Autowired
	BooksSupplierService booksSupplierService;

	@Autowired
	BooksService booksService;
	
	public List<BooksPurchaseOrderHdr> getPurchaseOrderByCreateDt(String dateStr) throws ParseException {
		Date purchaseOrderDate = null;
		if(dateStr!=null){
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd"); 
			 purchaseOrderDate=formatter.parse(dateStr);
		}
		return booksPurchasesOrderHdrRepository.findByCreateDt(purchaseOrderDate);		
	}
	
	public List<BooksPurchaseOrderHdr> getAllPurchaseOrder(){		
		return booksPurchasesOrderHdrRepository.findAll();		
	}
		
	public BooksPurchaseOrderHdr saveBooksPurchaseOrder(Long supplierId,Set<BooksPurchaseOrderHolder> booksPurchaseOrderHolderSet) {
		Set<BooksPurchaseOrderDtl> booksPurchaseOrderDtlSet=new HashSet<BooksPurchaseOrderDtl>();
		
		booksPurchaseOrderHolderSet.forEach(h->{
			BooksPurchaseOrderDtl dtl=new BooksPurchaseOrderDtl();
			Book book=booksService.getBookById(h.getBookId());
     		dtl.setBook(book);
     		dtl.setQuantity(h.getQuantity());
     		dtl.setUnitPrice(h.getUnitPrice());
     		booksPurchaseOrderDtlSet.add(dtl);
		
		});
		BooksSupplier booksSupplier=booksSupplierService.getSupplierById(supplierId);
		BooksPurchaseOrderHdr booksPurchaseOrderHdr=new BooksPurchaseOrderHdr(booksPurchaseOrderDtlSet,booksSupplier);
		booksPurchaseOrderHdr.getBooksPurchaseOrderDtlSet().forEach(
				pod->{booksPurchaseOrderHdr.addPohId(pod);		                                                   
				System.out.println(pod.getQuantity());});		
		booksPurchaseOrderHdr.setStatus(BooksConstants.STATUS_NEW);
		booksPurchasesOrderHdrRepository.save(booksPurchaseOrderHdr);
		return booksPurchaseOrderHdr;
	}
	
	public void deleteById(Long purchaseOrderId) {
		booksPurchasesOrderHdrRepository.deleteById(purchaseOrderId);
	}
	
	public BooksPurchaseOrderHdr updatePurchaseOrder(Long purchaseId,String status) {
		BooksPurchaseOrderHdr booksPurchaseOrderHdr=booksPurchasesOrderHdrRepository.findById(purchaseId).get();
		if(booksPurchaseOrderHdr!=null) {
			if(booksPurchaseOrderHdr.getStatus().equals(BooksConstants.STATUS_NEW)) {		
				booksPurchaseOrderHdr.setStatus(status);
				if(status.equals(BooksConstants.STATUS_RECEIVED)) {
					booksPurchaseOrderHdr.getBooksPurchaseOrderDtlSet().forEach(dtl->{
						dtl.updateStock(status);
					});
				}			
			}else if(booksPurchaseOrderHdr.getStatus().equals(BooksConstants.STATUS_RECEIVED)) {
				booksPurchaseOrderHdr.setStatus(status);
				if(status.equals(BooksConstants.STATUS_CANCELLED)) {
					booksPurchaseOrderHdr.getBooksPurchaseOrderDtlSet().forEach(dtl->{
						dtl.updateStock(status);
					});
				}
			}
		}
		booksPurchasesOrderHdrRepository.save(booksPurchaseOrderHdr);
		return booksPurchaseOrderHdr;
	}
	
	
}