package com.enagas.msc.producto.service.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.enagas.msc.producto.domain.Producto;
import com.enagas.msc.producto.domain.ProductoCalendario;
import com.enagas.msc.producto.service.mapper.ValidarProductoMapperService;
import com.enagas.msc.producto.service.vo.RequestValidarProductoVO;
import com.enagas.msc.producto.service.vo.ResponseValidarProductoVO;

/**
 * Service that implements the ValidarProductoMapperService interface.
 */
@Service
public class ValidarProductoMapperServiceImpl implements ValidarProductoMapperService {

	/** The ModelMapper instance. */
	private static final ModelMapper modelMapper = new ModelMapper();

	/**
	 * Converts a RequestValidarProductoVO to a Producto entity.
	 *
	 * @param requestValidarProductoVO
	 *            the request RequestValidarProductoVO
	 *
	 * @return a Producto instance
	 */
	public Producto convertToProductoEntity(RequestValidarProductoVO requestValidarProductoVO) {
		Producto producto = new Producto();
		return convertToProductoEntity(requestValidarProductoVO, producto);		
	}

	/**
	 * Converts a RequestValidarProductoVO to a Producto entity.
	 *
	 * @param requestValidarProductoVO
	 *            the request RequestValidarProductoVO
	 * @param entity
	 *            the Producto entity
	 *
	 * @return a Producto instance
	 */	
	public Producto convertToProductoEntity(RequestValidarProductoVO requestValidarProductoVO, Producto entity) {
		Producto producto = modelMapper.map(requestValidarProductoVO, Producto.class);		
		//Mapeo automatico: Modificar aquí por uno customizado en caso de ser necesario (add entity values to producto)
		
		return producto;	
	}
	
	/**
	 * Converts a Producto to a ResponseValidarProductoVO.
	 *
	 * @param producto
	 *            the Producto entity
	 *
	 * @return a ResponseValidarProductoVO instance
	 */
	public ResponseValidarProductoVO convertToVO(Producto producto) {
		ResponseValidarProductoVO responseValidarProductoVO = new ResponseValidarProductoVO();
		return convertToVO(producto, responseValidarProductoVO);
	}

	/**
	 * Converts a Producto to a ResponseValidarProductoVO.
	 *
	 * @param producto
	 *            the Producto entity
	 * @param responseVO
	 *            the ResponseValidarProductoVO
	 *
	 * @return a ResponseValidarProductoVO instance
	 */
	public ResponseValidarProductoVO convertToVO(Producto producto, ResponseValidarProductoVO responseVO) {
		ResponseValidarProductoVO responseValidarProductoVO = modelMapper.map(producto, ResponseValidarProductoVO.class);
		//Mapeo automatico: Modificar aquí por uno customizado en caso de ser necesario (add responseVO values to responseValidarProductoVO)
		
		return responseValidarProductoVO;
	}
	
	/**
	 * Converts a RequestValidarProductoVO to a ProductoCalendario entity.
	 *
	 * @param requestValidarProductoVO
	 *            the request RequestValidarProductoVO
	 *
	 * @return a ProductoCalendario instance
	 */
	public ProductoCalendario convertToProductoCalendarioEntity(RequestValidarProductoVO requestValidarProductoVO) {
		ProductoCalendario productoCalendario = new ProductoCalendario();
		return convertToProductoCalendarioEntity(requestValidarProductoVO, productoCalendario);		
	}

	/**
	 * Converts a RequestValidarProductoVO to a ProductoCalendario entity.
	 *
	 * @param requestValidarProductoVO
	 *            the request RequestValidarProductoVO
	 * @param entity
	 *            the ProductoCalendario entity
	 *
	 * @return a ProductoCalendario instance
	 */	
	public ProductoCalendario convertToProductoCalendarioEntity(RequestValidarProductoVO requestValidarProductoVO, ProductoCalendario entity) {
		ProductoCalendario productoCalendario = modelMapper.map(requestValidarProductoVO, ProductoCalendario.class);		
		//Mapeo automatico: Modificar aquí por uno customizado en caso de ser necesario (add entity values to mscTproductoCalendario)
		
		return productoCalendario;	
	}
	
	/**
	 * Converts a ProductoCalendario to a ResponseValidarProductoVO.
	 *
	 * @param productoCalendario
	 *            the ProductoCalendario entity
	 *
	 * @return a ResponseValidarProductoVO instance
	 */
	public ResponseValidarProductoVO convertToVO(ProductoCalendario productoCalendario) {
		ResponseValidarProductoVO responseValidarProductoVO = new ResponseValidarProductoVO();
		return convertToVO(productoCalendario, responseValidarProductoVO);
	}

	/**
	 * Converts a ProductoCalendario to a ResponseValidarProductoVO.
	 *
	 * @param productoCalendario
	 *            the ProductoCalendario entity
	 * @param responseVO
	 *            the ResponseValidarProductoVO
	 *
	 * @return a ResponseValidarProductoVO instance
	 */
	public ResponseValidarProductoVO convertToVO(ProductoCalendario productoCalendario, ResponseValidarProductoVO responseVO) {
		ResponseValidarProductoVO responseValidarProductoVO = modelMapper.map(productoCalendario, ResponseValidarProductoVO.class);
		//Mapeo automatico: Modificar aquí por uno customizado en caso de ser necesario (add responseVO values to responseValidarProductoVO)
		
		return responseValidarProductoVO;
	}
	
}