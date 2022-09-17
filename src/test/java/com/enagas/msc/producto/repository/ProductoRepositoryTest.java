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

import com.enagas.msc.producto.domain.Producto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductoRepositoryTest {

	@Autowired
	private ProductoRepository productoRepository;

	@BeforeEach
	public void setup() {
		productoRepository.deleteAll();
	}

	@Test
	void tSaveProducto() {
		assertNotNull(productoRepository.saveAndFlush(new Producto()));
	}
	
	@Test
	void tGetProductos() {
		assertNotNull(productoRepository.findAll(Pageable.unpaged()));
	}
	
	@Test
	void tProductoExistsById() {
		Producto producto = new Producto();
		productoRepository.saveAndFlush(producto);
		assertTrue(productoRepository.existsById(producto.getIdnProducto()));		
	}

	@Test
	void tProductoNotExistsById() {
		assertFalse(productoRepository.existsById(Integer.valueOf(0)));
	}

	@Test
	void tProductoCount() {
		Producto producto = new Producto();
		productoRepository.saveAndFlush(producto);
		assertEquals(1, productoRepository.count());
	}

	@Test
	void tDeleteProductoById() {
		Producto producto = new Producto();
		productoRepository.saveAndFlush(producto);
		productoRepository.deleteById(producto.getIdnProducto());
		assertFalse(productoRepository.findById(producto.getIdnProducto()).isPresent());
	}

	@Test
	void tDeleteAllProductos() {
		productoRepository.saveAndFlush(new Producto());
		productoRepository.saveAndFlush(new Producto());
		productoRepository.deleteAll();
		assertEquals(0, productoRepository.count());
	}

}