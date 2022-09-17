package com.enagas.msc.producto.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.enagas.msc.producto.domain.ProductoVer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductoVerRepositoryTest {

	@Autowired
	private ProductoVerRepository productoVerRepository;

	@BeforeEach
	public void setup() {
		productoVerRepository.deleteAll();
	}

	@Test
	void tSaveProductoVer() {
		assertNotNull(productoVerRepository.saveAndFlush(new ProductoVer()));
	}
	
	@Test
	void tGetProductoVers() {
		assertNotNull(productoVerRepository.findAll(Pageable.unpaged()));
	}
	
	@Test
	void tProductoVerExistsById() {
		ProductoVer productoVer = new ProductoVer();
		productoVerRepository.saveAndFlush(productoVer);
		assertTrue(productoVerRepository.existsById(productoVer.getIdnProductoVer()));		
	}

	@Test
	void tProductoVerNotExistsById() {
		assertFalse(productoVerRepository.existsById(Integer.valueOf(0)));
	}

	@Test
	void tProductoVerCount() {
		ProductoVer productoVer = new ProductoVer();
		productoVerRepository.saveAndFlush(productoVer);
		assertEquals(1, productoVerRepository.count());
	}

	@Test
	void tDeleteProductoVerById() {
		ProductoVer productoVer = new ProductoVer();
		productoVerRepository.saveAndFlush(productoVer);
		productoVerRepository.deleteById(productoVer.getIdnProductoVer());
		assertFalse(productoVerRepository.findById(productoVer.getIdnProductoVer()).isPresent());
	}

	@Test
	void tDeleteAllProductoVers() {
		productoVerRepository.saveAndFlush(new ProductoVer());
		productoVerRepository.saveAndFlush(new ProductoVer());
		productoVerRepository.deleteAll();
		assertEquals(0, productoVerRepository.count());
	}

}