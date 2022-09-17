package com.enagas.msc.producto.service.mapper.impl;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.enagas.msc.producto.domain.ProductoVer;
import com.enagas.msc.producto.domain.vo.RequestProductoVerVO;
import com.enagas.msc.producto.service.mapper.ProductoVerMapperService;

@ExtendWith(SpringExtension.class)
class ProductoVerMapperServiceImplTest {

	private ProductoVerMapperService productoVerMapperService;

	@BeforeEach
	public void setup() {
		productoVerMapperService = new ProductoVerMapperServiceImpl();;
	}

	@Test
	void tShouldConvertListOfProductoVerEntityToListOfResponseProductoVerVOs() {
		List<ProductoVer> productoVer = Arrays.asList(new ProductoVer());
		assertNotNull(productoVerMapperService.convertToVO(productoVer));
	}

	@Test
	void tShouldConvertProductoVerEntityToResponseProductoVerVO() {
		ProductoVer productoVer = new ProductoVer();
		assertNotNull(productoVerMapperService.convertToVO(productoVer));
	}

	@Test
	void tShouldConvertRequestProductoVerVOToProductoVerEntity() {
		RequestProductoVerVO requestProductoVerVO = new RequestProductoVerVO();
		assertNotNull(productoVerMapperService.convertToEntity(requestProductoVerVO));
	}

}