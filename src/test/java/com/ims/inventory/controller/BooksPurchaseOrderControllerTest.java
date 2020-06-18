package com.ims.inventory.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.ims.inventory.commons.BooksConstants;
import com.ims.inventory.model.BooksPurchaseOrderHdr;
import com.ims.inventory.model.BooksPurchaseOrderHolder;
import com.ims.inventory.service.BooksPurchaseOrderService;
	@SpringBootTest
	class BooksPurchaseOrderControllerTest{
		@Autowired		
		BooksPurchaseOrderController booksPurchaseOrderController;
		
		@MockBean
		BooksPurchaseOrderService booksPurchaseOrderService;
		
				
		@Test
		 void getPurchaseOrderByCreateDtTest() throws ParseException {		
			when(booksPurchaseOrderService.getPurchaseOrderByCreateDt(Mockito.anyString())).thenReturn(Mockito.mock(List.class));
			ResponseEntity<List<BooksPurchaseOrderHdr>> result=booksPurchaseOrderController.getPurchaseOrderByCreateDt(Mockito.anyString());
			assertEquals(200, result.getStatusCodeValue());
		}
		
		@Test
		 void getAllPurchaseOrderTest() throws ParseException {		
			when(booksPurchaseOrderService.getAllPurchaseOrder()).thenReturn(Mockito.mock(List.class));
			ResponseEntity<List<BooksPurchaseOrderHdr>> result=booksPurchaseOrderController.getAllPurchaseOrder();
			assertEquals(200, result.getStatusCodeValue());
		}
		
		@Test
		 void savePurchaseOrderTest() throws ParseException {		
			Set<BooksPurchaseOrderHolder> dtlSet=new HashSet<BooksPurchaseOrderHolder>();
			BooksPurchaseOrderHolder hdr=new BooksPurchaseOrderHolder();
			hdr.setBookId(1L);
			hdr.setQuantity(10);
			hdr.setUnitPrice(50);
			dtlSet.add(hdr);
			when(booksPurchaseOrderService.saveBooksPurchaseOrder(1L, dtlSet)).thenReturn(Mockito.mock(BooksPurchaseOrderHdr.class));
			ResponseEntity<BooksPurchaseOrderHdr> result=booksPurchaseOrderController.saveBooksPurchasOrder(dtlSet,1L);
			assertEquals(200, result.getStatusCodeValue());
		}
		
		@Test
		 void updatePurchaseOrderAsReceivedTest() throws ParseException {	
			Long purchaseId=1L;
			when(booksPurchaseOrderService.updatePurchaseOrder(purchaseId,BooksConstants.STATUS_RECEIVED)).thenReturn(Mockito.mock(BooksPurchaseOrderHdr.class));
			ResponseEntity<BooksPurchaseOrderHdr> result=booksPurchaseOrderController.updatePurchaseOrderAsReceived(purchaseId);
			assertEquals(200, result.getStatusCodeValue());
		}
		
		@Test
		 void cancelPurchaseOrderTest() throws ParseException {		
			Long purchaseId=1L;
			when(booksPurchaseOrderService.updatePurchaseOrder(purchaseId,BooksConstants.STATUS_CANCELLED)).thenReturn(Mockito.mock(BooksPurchaseOrderHdr.class));
			ResponseEntity<BooksPurchaseOrderHdr> result=booksPurchaseOrderController.cancelPurchaseOrder(purchaseId);
			assertEquals(200, result.getStatusCodeValue());
		}
		
	}


