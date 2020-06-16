package com.ims.inventory.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ims.inventory.commons.BooksConstants;

@Entity
@Table(name = "invt_purchase_order_dtl")
public class BooksPurchaseOrderDtl{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="invt_pod_id",nullable=false)
	@JsonIgnore
	private Long id;
//	
//	@Column(name="invt_poh_id",nullable=false)
//	private Long pohId;	
	
	@Column(name="invt_pod_create_dt")
	@JsonIgnore
	private Date createDt=new Date();
	
	@Column(name="invt_pod_update_dt")
	@JsonIgnore
	private Date updateDt=new Date();
	

	 @Column(name="invt_pod_quantity")
     private int quantity;
	
	 @Column(name="invt_pod_unitprice")
     private int unitPrice;
	
	 @OneToOne(cascade=CascadeType.ALL)
     @JoinColumn(name="invt_pod_book_id" )	 
	 //@JsonIgnore
     private Book book=new Book();
	 
	 @ManyToOne
	 @JoinColumn(name="invt_poh_id",nullable=false)
	 @JsonIgnore
	 private BooksPurchaseOrderHdr booksPurchaseOrderHdr;
	 	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {		
		this.quantity = quantity;
	}

	
	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	
	public void setQuantity(int quantity) {
		this.quantity = quantity;				
	}		
		

	public BooksPurchaseOrderHdr getBooksPurchaseOrderHdr() {
		return booksPurchaseOrderHdr;
	}

	public void setBooksPurchaseOrderHdr(BooksPurchaseOrderHdr booksPurchaseOrderHdr) {
		this.booksPurchaseOrderHdr = booksPurchaseOrderHdr;
	}

	public void updateStock(String status) {
		if( status==BooksConstants.STATUS_RECEIVED) {
			book.setQuantity(book.getQuantity()+quantity);
			if(book.getUnitPrice()<unitPrice) {
				book.setUnitPrice(unitPrice);
			}
		}else if(status==BooksConstants.STATUS_CANCELLED) {
			book.setQuantity(book.getQuantity()-quantity);
		}
	}
	
	
}