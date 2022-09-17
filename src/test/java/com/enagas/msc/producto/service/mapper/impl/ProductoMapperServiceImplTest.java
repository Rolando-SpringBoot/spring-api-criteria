package com.enagas.msc.producto.service.mapper.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.enagas.msc.producto.domain.Producto;
import com.enagas.msc.producto.domain.vo.RequestProductoVO;
import com.enagas.msc.producto.service.mapper.ProductoMapperService;

@ExtendWith(SpringExtension.class)
class ProductoMapperServiceImplTest {

	private ProductoMapperService productoMapperService;

	@BeforeEach
	public void setup() {
		productoMapperService = new ProductoMapperServiceImpl();;
	}

	@Test
	void tShouldConvertListOfProductoEntityToListOfResponseProductoVOs() {
		List<Producto> producto = Arrays.asList(new Producto());
		assertNotNull(productoMapperService.convertToVO(producto));
	}

	@Test
	void tShouldConvertProductoEntityToResponseProductoVO() {
		Producto producto = new Producto();
		assertNotNull(productoMapperService.convertToVO(producto));
	}

	@Test
	void tShouldConvertRequestProductoVOToProductoEntity() {
		RequestProductoVO requestProductoVO = new RequestProductoVO();
		assertNotNull(productoMapperService.convertToEntity(requestProductoVO));
	}

}