package com.ims.inventory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invt_customer")
public class BooksCustomer{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="invt_cust_id")
	private Integer id;
	
	@Column(name="invt_cust_name",nullable=false)
	private String name;
	
	@Column(name="invt_cust_mobile")
	private Integer mobileNumber;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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