
package com.enagas.msc.producto.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.enagas.msc.producto.config.ParametersPropertiesConfig;
import com.enagas.msc.producto.domain.Producto;
import com.enagas.msc.producto.domain.vo.RequestFiltrarPorPeriodoVO;
import com.enagas.msc.producto.domain.vo.RequestProductoVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoVO;
import com.enagas.msc.producto.external.service.OfertaExternalRestService;
import com.enagas.msc.producto.repository.ProductoRepository;
import com.enagas.msc.producto.service.ProductoService;
import com.enagas.msc.producto.service.mapper.ProductoMapperService;
import com.enagas.msc.producto.service.vo.RequestServicioFilterVO;


@ExtendWith(SpringExtension.class)
class ProductoServiceImplTest {

	@Mock
	private ProductoRepository productoRepository;
	
	@Mock
    private ProductoMapperService productoMapperService;
	
	@Mock
	private OfertaExternalRestService ofertaExternalRestService;

	private ProductoService productoService;
	
	private ParametersPropertiesConfig paramProperties;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}	

	@Test
	void tGetProductoShouldReturnProductoIfProductoExist() {
		when(productoRepository.findById(Mockito.any())).thenReturn(Optional.of(new Producto()));
		when(productoMapperService.convertToVO(new Producto())).thenReturn(new ResponseProductoVO());
		assertNotNull(productoService.getProducto(Integer.valueOf(0)));
	}
	
	@Test
	void tGetProductoShouldThrowNoSuchElementExceptionIfProductoNotExist() {
		productoService.getProducto(Integer.valueOf(0));
	}

	@Test
	void tSaveProductoShouldReturnProducto() {
		when(productoMapperService.convertToEntity(Mockito.any())).thenReturn(new Producto());
		when(productoRepository.saveAndFlush((Mockito.any()))).thenReturn(new Producto());
		when(productoMapperService.convertToVO(new Producto())).thenReturn(new ResponseProductoVO());
		ResponseProductoVO producto = productoService.saveProducto(new RequestProductoVO());
		assertNotNull(producto);
	}
	
	@Test
	void tUpdateProductoShouldUpdateProductoIfProductoExist() {
		when(productoMapperService.convertToEntity(Mockito.any())).thenReturn(new Producto());
		when(productoRepository.findById((Mockito.any()))).thenReturn(Optional.of(new Producto()));
		productoService.updateProducto(Integer.valueOf(0), new RequestProductoVO());
	}
	
	@Test
	void tUpdateProductoShouldThrowNoSuchElementExceptionIfProductoNotExist() {
		productoService.updateProducto(Integer.valueOf(0), new RequestProductoVO());
	}
	
	@Test
	void tDeleteProductoShouldRemoveProductoIfProductoExist() {
		when(productoRepository.findById((Mockito.any()))).thenReturn(Optional.of(new Producto()));
		productoService.deleteProducto(Integer.valueOf(0));
	}
	
	@Test
	void tDeleteProductoShouldThrowNoSuchElementExceptionIfProductoNotExist() {
		productoService.deleteProducto(Integer.valueOf(0));
	}
	
	
	@Test
	void tGetAffectedByServices() {
		
		// given		List<RequestFiltrarPorPeriodoVO> serviceFilters = new ArrayList<>();
		serviceFilters.add(new RequestFiltrarPorPeriodoVO());
		ResponseProductoVO responseProductoVO = new ResponseProductoVO();
		List<Producto> expectedAffectedProducts = List.of(new Producto());
		RequestServicioFilterVO requestServicioFilterVO = new RequestServicioFilterVO();
		
		// when
		when(productoRepository.findAffectedByService(Mockito.any(), Mockito.any(RequestServicioFilterVO.class))).thenReturn(expectedAffectedProducts);
		when(productoMapperService.convertToVO(Mockito.any(RequestFiltrarPorPeriodoVO.class))).thenReturn(requestServicioFilterVO);
		when(productoMapperService.convertToVO(Mockito.any(Producto.class))).thenReturn(responseProductoVO);
		
		// then: we get results
		List<ResponseProductoVO> actualAffectedProducts = productoService.filtrarPorPeriodo(serviceFilters);
		assertEquals(expectedAffectedProducts.size(), actualAffectedProducts.size());
		
		// then: we get no results
		expectedAffectedProducts.clear();
		when(productoRepository.findAffectedByService(Mockito.any(), Mockito.any(RequestServicioFilterVO.class))).thenReturn(expectedAffectedProducts);
		assertEquals(0, actualAffectedProducts.size());
	}
	
}