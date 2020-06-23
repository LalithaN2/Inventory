package com.ims.inventory.controller;

import java.lang.invoke.MethodHandles;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ims.inventory.commons.BooksConstants;
import com.ims.inventory.model.Book;
import com.ims.inventory.model.BooksPurchaseOrderDtl;
import com.ims.inventory.model.BooksPurchaseOrderHdr;
import com.ims.inventory.model.BooksPurchaseOrderHolder;
import com.ims.inventory.service.BooksPurchaseOrderService;
import com.ims.inventory.service.BooksService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/purchaseOrder")
public class BooksPurchaseOrderController{	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	@Autowired
	BooksPurchaseOrderService booksPurchaseOrderService;
	
	@GetMapping("/getPurchaseOrderByCreateDt")
	public ResponseEntity<List<BooksPurchaseOrderHdr>> getPurchaseOrderByCreateDt(@RequestParam (value="purchaseOrderDate", required=true) String purchaseOrderDate) throws ParseException {
		logger.info("Request /getPurchaseOrderByCreateDt");
		List<BooksPurchaseOrderHdr> booksPurchaseOrderHdrList=booksPurchaseOrderService.getPurchaseOrderByCreateDt(purchaseOrderDate);
		return new ResponseEntity<List<BooksPurchaseOrderHdr>>(booksPurchaseOrderHdrList,HttpStatus.OK);	
		
	}
	
	@GetMapping("/getAllPurchaseOrder")
	public ResponseEntity<List<BooksPurchaseOrderHdr>> getAllPurchaseOrder(){
		logger.info("Request /getAllPurchaseOrder");
		List<BooksPurchaseOrderHdr> booksPurchaseOrderHdrList=booksPurchaseOrderService.getAllPurchaseOrder();
		return new ResponseEntity<List<BooksPurchaseOrderHdr>>(booksPurchaseOrderHdrList,HttpStatus.OK);	
		
	}
	
	@PostMapping("/saveBooksPurchaseOrder")
	public ResponseEntity<BooksPurchaseOrderHdr> saveBooksPurchasOrder(@RequestBody Set<BooksPurchaseOrderHolder> booksPurchaseOrderHolderSet,@RequestParam(value="supplierId", required=true) Long supplierId) {
		logger.info("Request /saveBooksPurchasOrder");
		BooksPurchaseOrderHdr booksPurchaseOrderHdr=booksPurchaseOrderService.saveBooksPurchaseOrder(supplierId,booksPurchaseOrderHolderSet);		
		return new ResponseEntity<BooksPurchaseOrderHdr>(booksPurchaseOrderHdr,HttpStatus.OK);	
    }
	

	@PostMapping("/updatePurchaseOrderAsReceived")
	public ResponseEntity<BooksPurchaseOrderHdr> updatePurchaseOrderAsReceived(@RequestParam(value="purchaseOrderId", required=true) Long purchaseOrderId ) {
		logger.info("Request /updatePurchaseOrderAsReceived");
		BooksPurchaseOrderHdr booksPurchaseOrderHdr =booksPurchaseOrderService.updatePurchaseOrder(purchaseOrderId,BooksConstants.STATUS_RECEIVED);		
		return new ResponseEntity<BooksPurchaseOrderHdr>(booksPurchaseOrderHdr,HttpStatus.OK);		
    }
	
	@PostMapping("/cancelPurchaseOrder")
	public ResponseEntity<BooksPurchaseOrderHdr> cancelPurchaseOrder(@RequestParam(value="purchaseOrderId", required=true) Long purchaseOrderId) {
		logger.info("Request /cancelPurchaseOrder");
		BooksPurchaseOrderHdr booksPurchaseOrderHdr=booksPurchaseOrderService.updatePurchaseOrder(purchaseOrderId,BooksConstants.STATUS_CANCELLED);		
		return new ResponseEntity<BooksPurchaseOrderHdr>(booksPurchaseOrderHdr,HttpStatus.OK);		
    }
	
	
	
}