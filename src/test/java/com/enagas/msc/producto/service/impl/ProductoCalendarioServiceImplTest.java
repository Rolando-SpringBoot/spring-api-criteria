package com.enagas.msc.producto.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.enagas.msc.producto.domain.ProductoCalendario;
import com.enagas.msc.producto.domain.vo.RequestProductoCalendarioVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoCalendarioVO;
import com.enagas.msc.producto.repository.ProductoCalendarioRepository;
import com.enagas.msc.producto.service.ProductoCalendarioService;
import com.enagas.msc.producto.service.mapper.ProductoCalendarioMapperService;

@ExtendWith(SpringExtension.class)
class ProductoCalendarioServiceImplTest {

	@Mock
	private ProductoCalendarioRepository productoCalendarioRepository;
	
	@Mock
    private ProductoCalendarioMapperService productoCalendarioMapperService;
	
	@InjectMocks
	private ProductoCalendarioService productoCalendarioService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}	

	@Test
	void tGetProductoCalendariosShouldReturnProductoCalendarios() {
		when(productoCalendarioRepository.findAll(Pageable.unpaged())).thenReturn(new PageImpl<>(Arrays.asList(new ProductoCalendario())));
		List<ResponseProductoCalendarioVO> productoCalendarios = productoCalendarioService.getProductoCalendarios(Pageable.unpaged());
		assertNotNull(productoCalendarios);
	}
	
	@Test
	void tGetProductoCalendarioShouldReturnProductoCalendarioIfProductoCalendarioExist() {
		when(productoCalendarioRepository.findById(Mockito.any())).thenReturn(Optional.of(new ProductoCalendario()));
		when(productoCalendarioMapperService.convertToVO(new ProductoCalendario())).thenReturn(new ResponseProductoCalendarioVO());
		assertNotNull(productoCalendarioService.getProductoCalendario(Integer.valueOf(0)));
	}
	
	@Test
	void tGetProductoCalendarioShouldThrowNoSuchElementExceptionIfProductoCalendarioNotExist() {
		productoCalendarioService.getProductoCalendario(Integer.valueOf(0));
	}

	@Test
	void tSaveProductoCalendarioShouldReturnProductoCalendario() {
		when(productoCalendarioMapperService.convertToEntity(Mockito.any())).thenReturn(new ProductoCalendario());
		when(productoCalendarioRepository.saveAndFlush((Mockito.any()))).thenReturn(new ProductoCalendario());
		when(productoCalendarioMapperService.convertToVO(new ProductoCalendario())).thenReturn(new ResponseProductoCalendarioVO());
		ResponseProductoCalendarioVO productoCalendario = productoCalendarioService.saveProductoCalendario(new RequestProductoCalendarioVO());
		assertNotNull(productoCalendario);
	}
	
	@Test
	void tUpdateProductoCalendarioShouldUpdateProductoCalendarioIfProductoCalendarioExist() {
		when(productoCalendarioMapperService.convertToEntity(Mockito.any())).thenReturn(new ProductoCalendario());
		when(productoCalendarioRepository.findById((Mockito.any()))).thenReturn(Optional.of(new ProductoCalendario()));
		productoCalendarioService.updateProductoCalendario(Integer.valueOf(0), new RequestProductoCalendarioVO());
	}
	
	@Test
	void tUpdateProductoCalendarioShouldThrowNoSuchElementExceptionIfProductoCalendarioNotExist() {
		productoCalendarioService.updateProductoCalendario(Integer.valueOf(0), new RequestProductoCalendarioVO());
	}
	
	@Test
	void tDeleteProductoCalendarioShouldRemoveProductoCalendarioIfProductoCalendarioExist() {
		when(productoCalendarioRepository.findById((Mockito.any()))).thenReturn(Optional.of(new ProductoCalendario()));
		productoCalendarioService.deleteProductoCalendario(Integer.valueOf(0));
	}
	
	@Test
	void tDeleteProductoCalendarioShouldThrowNoSuchElementExceptionIfProductoCalendarioNotExist() {
		productoCalendarioService.deleteProductoCalendario(Integer.valueOf(0));
	}
	
}