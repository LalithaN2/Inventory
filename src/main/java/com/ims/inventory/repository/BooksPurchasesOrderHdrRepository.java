package com.ims.inventory.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ims.inventory.model.BooksPurchaseOrderHdr;

public interface BooksPurchasesOrderHdrRepository extends CrudRepository<BooksPurchaseOrderHdr, Long>{	
	
	@Query("select poHdr from BooksPurchaseOrderHdr poHdr where date(createDt)=?1")
	List<BooksPurchaseOrderHdr> findByCreateDt(Date d1);
	
	List<BooksPurchaseOrderHdr> findAll();
	
}
