package com.ims.inventory.model.dto;

public class BookDTO{
	
	private Long id;
	
	private String name;
	
	private Integer volumeNumber;

	public Long getBookId() {
		return id;
	}

	public void setBookId(Long id) {
		this.id = id;
	}

	public String getBookName() {
		return name;
	}

	public void setBookName(String name) {
		this.name = name;
	}

	public Integer getBookVolumeNumber() {
		return volumeNumber;
	}

	public void setBookVolumeNumber(Integer volumeNumber) {
		this.volumeNumber = volumeNumber;
	}
	
	
}