package com.ims.inventory.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "invt_sales_order")
public class BooksSalesOrder{
	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="invt_so_id")
	private Long id;	
	
	@Column(name="invt_so_code")
	private String salesOrderCode;	
	
	@Column(name="invt_so_createDt",nullable=false)
	private Date createDate=new Date();
	
	@Column(name="invt_so_updateDt",nullable=false)
	private Date updateDate=new Date();
	
	@Column(name="invt_so_status",nullable=false)
	private String status;
	
	@Column(name="invt_so_quatity",nullable=false)
	private String quantity;
	
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="id")
    @ManyToOne(optional=false)
    @JoinColumn(name="invt_book_id")
    private Book book;
	

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="id")
    @ManyToOne(optional=false)
    @JoinColumn(name="invt_cust_id")
    private BooksCustomer booksCustomer;
	
	public BooksSalesOrder() {
		super();
	}
	
	public BooksSalesOrder(Book book,BooksCustomer booksCustomer) {
		super();
		this.book=book;
		this.booksCustomer=booksCustomer;
		this.status="Pending";
		this.salesOrderCode="SO"+createDate.getTime();
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public BooksCustomer getBooksCustomer() {
		return booksCustomer;
	}

	public void setBooksCustomer(BooksCustomer booksCustomer) {
		this.booksCustomer = booksCustomer;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSalesOrderCode() {
		return salesOrderCode;
	}

	public void setSalesOrderCode(String salesOrderCode) {
		this.salesOrderCode = salesOrderCode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	
	
}