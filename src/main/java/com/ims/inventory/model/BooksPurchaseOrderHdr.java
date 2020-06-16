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

@Entity
@Table(name = "invt_purchase_order_hdr")
public class BooksPurchaseOrderHdr{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="invt_poh_id")
	private Long id;	
	
	@Column(name="invt_poh_create_dt")
	private Date createDt=new Date();
	
	@Column(name="invt_poh_update_dt")
	private Date updateDt=new Date();
	
	@Column(name="invt_poh_status")
	private String status;

	@OneToOne
    @JoinColumn(name="invt_poh_supplier_id",referencedColumnName ="invt_supplier_id")    
	private BooksSupplier bookSupplier;
	
	@OneToMany(mappedBy="booksPurchaseOrderHdr", cascade=CascadeType.ALL) 	
    private Set<BooksPurchaseOrderDtl> booksPurchaseOrderDtlSet=new HashSet<BooksPurchaseOrderDtl>();
	
     public BooksPurchaseOrderHdr() {
		super();
	}
	
	public BooksPurchaseOrderHdr(Set<BooksPurchaseOrderDtl> booksPurchaseOrderDtlSet,BooksSupplier booksSupplier) {
		super();		
		this.booksPurchaseOrderDtlSet=booksPurchaseOrderDtlSet;		
		this.bookSupplier=booksSupplier;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BooksSupplier getBookSupplier() {
		return bookSupplier;
	}

	public void setBookSupplier(BooksSupplier bookSupplier) {
		this.bookSupplier = bookSupplier;
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

	public Set<BooksPurchaseOrderDtl> getBooksPurchaseOrderDtlSet() {
		return booksPurchaseOrderDtlSet;
	}

	public void setBooksPurchaseOrderDtlSet(Set<BooksPurchaseOrderDtl> booksPurchaseOrderDtlSet) {
		this.booksPurchaseOrderDtlSet = booksPurchaseOrderDtlSet;
	}

	public void addPohId(BooksPurchaseOrderDtl getBooksPurchaseOrderDtl) {
		if(getBooksPurchaseOrderDtl!=null) {
			if(booksPurchaseOrderDtlSet==null) {
				booksPurchaseOrderDtlSet=new  HashSet<BooksPurchaseOrderDtl>();
			}
			getBooksPurchaseOrderDtl.setBooksPurchaseOrderHdr(this);
			booksPurchaseOrderDtlSet.add(getBooksPurchaseOrderDtl);
		}
		
	}


	
	
}