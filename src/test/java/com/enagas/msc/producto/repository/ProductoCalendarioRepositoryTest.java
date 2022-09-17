package com.enagas.msc.producto.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.enagas.msc.producto.domain.ProductoCalendario;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductoCalendarioRepositoryTest {

	@Autowired
	private ProductoCalendarioRepository productoCalendarioRepository;

	@BeforeEach
	public void setup() {
		productoCalendarioRepository.deleteAll();
	}

	@Test
	void tSaveProductoCalendario() {
		assertNotNull(productoCalendarioRepository.saveAndFlush(new ProductoCalendario()));
	}
	
	@Test
	void tGetProductoCalendarios() {
		assertNotNull(productoCalendarioRepository.findAll(Pageable.unpaged()));
	}
	
	@Test
	void tProductoCalendarioExistsById() {
		ProductoCalendario productoCalendario = new ProductoCalendario();
		productoCalendarioRepository.saveAndFlush(productoCalendario);
		assertTrue(productoCalendarioRepository.existsById(productoCalendario.getIdnProductoCalendario()));		
	}

	@Test
	void tProductoCalendarioNotExistsById() {
		assertFalse(productoCalendarioRepository.existsById(Integer.valueOf(0)));
	}

	@Test
	void tProductoCalendarioCount() {
		ProductoCalendario productoCalendario = new ProductoCalendario();
		productoCalendarioRepository.saveAndFlush(productoCalendario);
		assertEquals(1, productoCalendarioRepository.count());
	}

	@Test
	void tDeleteProductoCalendarioById() {
		ProductoCalendario productoCalendario = new ProductoCalendario();
		productoCalendarioRepository.saveAndFlush(productoCalendario);
		productoCalendarioRepository.deleteById(productoCalendario.getIdnProductoCalendario());
		assertFalse(productoCalendarioRepository.findById(productoCalendario.getIdnProductoCalendario()).isPresent());
	}

	@Test
	void tDeleteAllProductoCalendarios() {
		productoCalendarioRepository.saveAndFlush(new ProductoCalendario());
		productoCalendarioRepository.saveAndFlush(new ProductoCalendario());
		productoCalendarioRepository.deleteAll();
		assertEquals(0, productoCalendarioRepository.count());
	}

}