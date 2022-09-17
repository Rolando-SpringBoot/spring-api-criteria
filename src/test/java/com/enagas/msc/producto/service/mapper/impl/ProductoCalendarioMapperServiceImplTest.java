package com.enagas.msc.producto.service.mapper.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.enagas.msc.producto.domain.ProductoCalendario;
import com.enagas.msc.producto.domain.vo.RequestProductoCalendarioVO;
import com.enagas.msc.producto.service.mapper.ProductoCalendarioMapperService;

@ExtendWith(SpringExtension.class)
class ProductoCalendarioMapperServiceImplTest {

	private ProductoCalendarioMapperService productoCalendarioMapperService;

	@BeforeEach
	void setup() {
		productoCalendarioMapperService = new ProductoCalendarioMapperServiceImpl();;
	}

	@Test
	void tShouldConvertListOfProductoCalendarioEntityToListOfResponseProductoCalendarioVOs() {
		List<ProductoCalendario> productoCalendario = Arrays.asList(new ProductoCalendario());
		assertNotNull(productoCalendarioMapperService.convertToVO(productoCalendario));
	}

	@Test
	void tShouldConvertProductoCalendarioEntityToResponseProductoCalendarioVO() {
		ProductoCalendario productoCalendario = new ProductoCalendario();
		assertNotNull(productoCalendarioMapperService.convertToVO(productoCalendario));
	}

	@Test
	void tShouldConvertRequestProductoCalendarioVOToProductoCalendarioEntity() {
		RequestProductoCalendarioVO requestProductoCalendarioVO = new RequestProductoCalendarioVO();
		assertNotNull(productoCalendarioMapperService.convertToEntity(requestProductoCalendarioVO));
	}

}