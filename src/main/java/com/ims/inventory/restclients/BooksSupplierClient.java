package com.ims.inventory.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ims.inventory.model.BooksSupplier;

@FeignClient("SUPPLIER-SERVICE")
public interface BooksSupplierClient {
	@GetMapping("/supplier/getSupplierById/{supplierId}")
	BooksSupplier getSupplierById(@PathVariable("supplierId") Long supplierId);
}
