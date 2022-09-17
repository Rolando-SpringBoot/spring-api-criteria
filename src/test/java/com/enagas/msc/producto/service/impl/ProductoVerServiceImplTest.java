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

import com.enagas.msc.producto.domain.ProductoVer;
import com.enagas.msc.producto.domain.vo.RequestProductoVerVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoVerVO;
import com.enagas.msc.producto.repository.ProductoVerRepository;
import com.enagas.msc.producto.service.ProductoVerService;
import com.enagas.msc.producto.service.mapper.ProductoVerMapperService;

@ExtendWith(SpringExtension.class)
class ProductoVerServiceImplTest {

	@Mock
	private ProductoVerRepository productoVerRepository;
	
	@Mock
    private ProductoVerMapperService productoVerMapperService;
	
	@InjectMocks
	private ProductoVerService productoVerService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}	

	@Test
	void tGetProductoVersShouldReturnProductoVers() {
		when(productoVerRepository.findAll(Pageable.unpaged())).thenReturn(new PageImpl<>(Arrays.asList(new ProductoVer())));
		List<ResponseProductoVerVO> productoVers = productoVerService.getProductoVers(Pageable.unpaged());
		assertNotNull(productoVers);
	}
	
	@Test
	void tGetProductoVerShouldReturnProductoVerIfProductoVerExist() {
		when(productoVerRepository.findById(Mockito.any())).thenReturn(Optional.of(new ProductoVer()));
		when(productoVerMapperService.convertToVO(new ProductoVer())).thenReturn(new ResponseProductoVerVO());
		assertNotNull(productoVerService.getProductoVer(Integer.valueOf(0)));
	}
	
	@Test
	void tGetProductoVerShouldThrowNoSuchElementExceptionIfProductoVerNotExist() {
		productoVerService.getProductoVer(Integer.valueOf(0));
	}

	@Test
	void tSaveProductoVerShouldReturnProductoVer() {
		when(productoVerMapperService.convertToEntity(Mockito.any())).thenReturn(new ProductoVer());
		when(productoVerRepository.saveAndFlush((Mockito.any()))).thenReturn(new ProductoVer());
		when(productoVerMapperService.convertToVO(new ProductoVer())).thenReturn(new ResponseProductoVerVO());
		ResponseProductoVerVO productoVer = productoVerService.saveProductoVer(new RequestProductoVerVO());
		assertNotNull(productoVer);
	}
	
	@Test
	void tUpdateProductoVerShouldUpdateProductoVerIfProductoVerExist() {
		when(productoVerMapperService.convertToEntity(Mockito.any())).thenReturn(new ProductoVer());
		when(productoVerRepository.findById((Mockito.any()))).thenReturn(Optional.of(new ProductoVer()));
		productoVerService.updateProductoVer(Integer.valueOf(0), new RequestProductoVerVO());
	}
	
	@Test
	void tUpdateProductoVerShouldThrowNoSuchElementExceptionIfProductoVerNotExist() {
		productoVerService.updateProductoVer(Integer.valueOf(0), new RequestProductoVerVO());
	}
	
	@Test
	void tDeleteProductoVerShouldRemoveProductoVerIfProductoVerExist() {
		when(productoVerRepository.findById((Mockito.any()))).thenReturn(Optional.of(new ProductoVer()));
		productoVerService.deleteProductoVer(Integer.valueOf(0));
	}
	
	@Test
	void tDeleteProductoVerShouldThrowNoSuchElementExceptionIfProductoVerNotExist() {
		productoVerService.deleteProductoVer(Integer.valueOf(0));
	}
	
}