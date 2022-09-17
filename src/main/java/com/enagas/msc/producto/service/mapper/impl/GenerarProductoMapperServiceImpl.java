package com.enagas.msc.producto.service.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.enagas.msc.producto.domain.ProductoCalendario;
import com.enagas.msc.producto.service.mapper.GenerarProductoMapperService;
import com.enagas.msc.producto.service.vo.RequestGenerarProductoVO;
import com.enagas.msc.producto.service.vo.ResponseGenerarProductoVO;

/**
 * Service that implements the GenerarProductoMapperService interface.
 */
@Service
public class GenerarProductoMapperServiceImpl implements GenerarProductoMapperService {

	/** The ModelMapper instance. */
	private static final ModelMapper modelMapper = new ModelMapper();

	/**
	 * Converts a RequestGenerarProductoVO to a ProductoCalendario entity.
	 *
	 * @param requestGenerarProductoVO
	 *            the request RequestGenerarProductoVO
	 *
	 * @return a ProductoCalendario instance
	 */
	public ProductoCalendario convertToProductoCalendarioEntity(RequestGenerarProductoVO requestGenerarProductoVO) {
		ProductoCalendario productoCalendario = new ProductoCalendario();
		return convertToProductoCalendarioEntity(requestGenerarProductoVO, productoCalendario);		
	}

	/**
	 * Converts a RequestGenerarProductoVO to a ProductoCalendario entity.
	 *
	 * @param requestGenerarProductoVO
	 *            the request RequestGenerarProductoVO
	 * @param entity
	 *            the ProductoCalendario entity
	 *
	 * @return a ProductoCalendario instance
	 */	
	public ProductoCalendario convertToProductoCalendarioEntity(RequestGenerarProductoVO requestGenerarProductoVO, ProductoCalendario entity) {
		ProductoCalendario productoCalendario = modelMapper.map(requestGenerarProductoVO, ProductoCalendario.class);		
		//Mapeo automatico: Modificar aquí por uno customizado en caso de ser necesario (add entity values to productoCalendario)
		
		return productoCalendario;	
	}
	
	/**
	 * Converts a ProductoCalendario to a ResponseGenerarProductoVO.
	 *
	 * @param productoCalendario
	 *            the ProductoCalendario entity
	 *
	 * @return a ResponseGenerarProductoVO instance
	 */
	public ResponseGenerarProductoVO convertToVO(ProductoCalendario productoCalendario) {
		ResponseGenerarProductoVO responseGenerarProductoVO = new ResponseGenerarProductoVO();
		return convertToVO(productoCalendario, responseGenerarProductoVO);
	}

	/**
	 * Converts a ProductoCalendario to a ResponseGenerarProductoVO.
	 *
	 * @param productoCalendario
	 *            the ProductoCalendario entity
	 * @param responseVO
	 *            the ResponseGenerarProductoVO
	 *
	 * @return a ResponseGenerarProductoVO instance
	 */
	public ResponseGenerarProductoVO convertToVO(ProductoCalendario productoCalendario, ResponseGenerarProductoVO responseVO) {
		ResponseGenerarProductoVO responseGenerarProductoVO = modelMapper.map(productoCalendario, ResponseGenerarProductoVO.class);
		//Mapeo automatico: Modificar aquí por uno customizado en caso de ser necesario (add responseVO values to responseGenerarProductoVO)
		
		return responseGenerarProductoVO;
	}
	
}