package com.ims.inventory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invt_supplier")
public class BooksSupplier{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="invt_supplier_id")
	private Long id;
	
	@Column(name="invt_supplier_name",nullable=false)
	private String name;
	
	@Column(name="invt_supplier_mobile")
	private Integer mobileNumber;
		
	public BooksSupplier() {
		super();
	}

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Integer mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
}