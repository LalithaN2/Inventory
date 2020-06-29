package com.ims.inventory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BooksInventoryApplicationTests {

	@Test
	void contextLoads() {
		assertThat(this).isNotNull();
	}

}
