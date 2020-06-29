package com.ims.inventory.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ims.inventory.commons.BooksConstants;
import com.ims.inventory.model.Book;
import com.ims.inventory.model.BooksPurchaseOrderDtl;
import com.ims.inventory.model.BooksPurchaseOrderHdr;
import com.ims.inventory.model.BooksPurchaseOrderHolder;
import com.ims.inventory.model.BooksSupplier;
import com.ims.inventory.service.BooksPurchaseOrderService;


    @RunWith(SpringJUnit4ClassRunner.class)
    @WebMvcTest(value=BooksPurchaseOrderController.class)
	
	public class BooksPurchaseOrderControllerTest{
		List<BooksPurchaseOrderHdr> hdrList=new ArrayList<BooksPurchaseOrderHdr>();
		BooksPurchaseOrderHdr hdr=new BooksPurchaseOrderHdr();
		@Autowired		
		MockMvc mockmvc;
		
		@MockBean
		BooksPurchaseOrderService booksPurchaseOrderService;
		
		  void setValues() {
		
			hdr.setId(1L);
			hdr.setCreateDt(new Date());
            hdr.setStatus("NEW");
            
            BooksSupplier supplier=new BooksSupplier();
            supplier.setId(2L);
            
            Book book=new Book();
            book.setId(3L);
            book.setName("TEST");
            book.setQuantity(20);
            book.setVolumeNumber(1);
            
            BooksPurchaseOrderDtl dtl=new BooksPurchaseOrderDtl();
            dtl.setId(1L);
            dtl.setQuantity(10);
            dtl.setUnitPrice(200);
            
            dtl.setBook(book);     
            hdr.setBookSupplier(supplier);
            hdrList.add(hdr);
	    }
						
		@Test
		@WithMockUser(roles="ADMIN")
		 public void getPurchaseOrderByCreateDtTest() throws Exception {		
			List<BooksPurchaseOrderHdr> hdrLists=Mockito.mock(List.class);
			setValues();
			
			BooksPurchaseOrderHdr hdr=Mockito.mock(BooksPurchaseOrderHdr.class);
			hdrLists.add(hdr);
                  
            String uri="/purchaseOrder/getPurchaseOrderByCreateDt?purchaseOrderDate=2020-06-27";
		    Mockito.when(booksPurchaseOrderService.getPurchaseOrderByCreateDt("2020-06-27")).thenReturn(hdrList);
		     
          //  String uri="/purchaseOrder/getAllPurchaseOrder";
		    //Mockito.when(booksPurchaseOrderService.getAllPurchaseOrder()).thenReturn(hdrList);
		
		    RequestBuilder requestBuilder=MockMvcRequestBuilders
		    		.get(uri).accept(MediaType.APPLICATION_JSON);
		    MvcResult results=mockmvc.perform(requestBuilder).andReturn();
		    
		    MockHttpServletResponse response=results.getResponse();
		    String output=results.getResponse().getContentAsString();
		 
		  //  assertThat(output).isEqualTo(mapToJson(hdrList));
		    assertEquals(HttpStatus.OK.value(), response.getStatus());
		}
		
		@Test
		@WithMockUser(roles="ADMIN")
		 public void getAllPurchaseOrderTest() throws Exception {	
			setValues();
			List<BooksPurchaseOrderHdr> hdrLists=Mockito.mock(List.class);
			
			BooksPurchaseOrderHdr hdr=Mockito.mock(BooksPurchaseOrderHdr.class);
			hdrLists.add(hdr);
            String uri="/purchaseOrder/getAllPurchaseOrder";
		    Mockito.when(booksPurchaseOrderService.getAllPurchaseOrder()).thenReturn(hdrList);
		
		    RequestBuilder requestBuilder=MockMvcRequestBuilders
		    		.get(uri).accept(MediaType.APPLICATION_JSON);
		    MvcResult results=mockmvc.perform(requestBuilder).andReturn();
		    
		    MockHttpServletResponse response=results.getResponse();
		    String output=results.getResponse().getContentAsString();
		 
		    //assertThat(output).isEqualTo(mapToJson(hdrList));
		    assertEquals(HttpStatus.OK.value(), response.getStatus());
		}
		
		@Test
		@WithMockUser(roles="ADMIN")
		 public void saveBooksPurchasOrderTest() throws Exception {		
			BooksPurchaseOrderHdr hdr=Mockito.mock(BooksPurchaseOrderHdr.class);
			Set<BooksPurchaseOrderHolder> hdrSet=new HashSet<BooksPurchaseOrderHolder>();
			BooksPurchaseOrderHolder hldr=new BooksPurchaseOrderHolder();
			hldr.setBookId(1L);
			hldr.setQuantity(10);
			hldr.setUnitPrice(100);
			hdrSet.add(hldr);
            String uri="/purchaseOrder/saveBooksPurchaseOrder?supplierId=1";
		    Mockito.when(booksPurchaseOrderService.saveBooksPurchaseOrder(1L,hdrSet)).thenReturn(hdr);
		
		    RequestBuilder requestBuilder=MockMvcRequestBuilders
		    		.post(uri).accept(MediaType.APPLICATION_JSON).content(mapToJson(hdrSet)).contentType(MediaType.APPLICATION_JSON);
		    MvcResult results=mockmvc.perform(requestBuilder).andReturn();
		    
		    MockHttpServletResponse response=results.getResponse();
		    String output=results.getResponse().getContentAsString();		 
		  
		    assertEquals(HttpStatus.OK.value(), response.getStatus());
		}
		
		@Test
		@WithMockUser(roles="ADMIN")
		 public void updatePurchaseOrderAsReceivedTest() throws Exception {		
			
            String uri="/purchaseOrder/updatePurchaseOrderAsReceived?purchaseOrderId=1";
		    Mockito.when(booksPurchaseOrderService.updatePurchaseOrder(1L,BooksConstants.STATUS_RECEIVED)).thenReturn(hdr);
		
		    RequestBuilder requestBuilder=MockMvcRequestBuilders
		    		.post(uri).accept(MediaType.APPLICATION_JSON);
		    MvcResult results=mockmvc.perform(requestBuilder).andReturn();
		    
		    MockHttpServletResponse response=results.getResponse();
		    String output=results.getResponse().getContentAsString();		 
		  
		    assertEquals(HttpStatus.OK.value(), response.getStatus());
		}
		
		@Test
		@WithMockUser(roles="ADMIN")
		 public void cancelPurchaseOrderTest() throws Exception {		
			
           String uri="/purchaseOrder/cancelPurchaseOrder?purchaseOrderId=1";
		    Mockito.when(booksPurchaseOrderService.updatePurchaseOrder(1L,BooksConstants.STATUS_CANCELLED)).thenReturn(hdr);
		
		    RequestBuilder requestBuilder=MockMvcRequestBuilders
		    		.post(uri).accept(MediaType.APPLICATION_JSON);
		    MvcResult results=mockmvc.perform(requestBuilder).andReturn();
		    
		    MockHttpServletResponse response=results.getResponse();
		    String output=results.getResponse().getContentAsString();		 
		  
		    assertEquals(HttpStatus.OK.value(), response.getStatus());
		}
		private String mapToJson(Object obj) throws JsonProcessingException {
			ObjectMapper objMapper=new ObjectMapper();
			return objMapper.writeValueAsString(obj);
		}
	}


