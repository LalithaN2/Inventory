package com.ims.inventory.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "invt_book")

public class Book{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="invt_book_id",nullable=false)
	private Long id;
	
	@Column(name="invt_book_name",nullable=false)
	private String name;
	
	@Column(name="invt_book_volume",nullable=false)
	private Integer volumeNumber;
	
	@Column(name="invt_book_quantity",nullable=false)
	private Integer quantity;
	
	@Column(name="invt_book_unitprice",nullable=false)
	private Integer unitPrice;

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

	public Integer getVolumeNumber() {
		return volumeNumber;
	}

	public void setVolumeNumber(Integer volumeNumber) {
		this.volumeNumber = volumeNumber;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	
	
	
}