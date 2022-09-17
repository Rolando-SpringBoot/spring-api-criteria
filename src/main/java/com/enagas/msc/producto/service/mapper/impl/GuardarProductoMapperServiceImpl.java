package com.enagas.msc.producto.service.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.enagas.msc.producto.domain.Producto;
import com.enagas.msc.producto.domain.ProductoCalendario;
import com.enagas.msc.producto.service.mapper.GuardarProductoMapperService;
import com.enagas.msc.producto.service.vo.RequestGuardarProductoVO;
import com.enagas.msc.producto.service.vo.ResponseGuardarProductoVO;

/**
 * Service that implements the GuardarProductoMapperService interface.
 */
@Service
public class GuardarProductoMapperServiceImpl implements GuardarProductoMapperService {

	/** The ModelMapper instance. */
	private static final ModelMapper modelMapper = new ModelMapper();

	/**
	 * Converts a RequestGuardarProductoVO to a Producto entity.
	 *
	 * @param requestGuardarProductoVO
	 *            the request RequestGuardarProductoVO
	 *
	 * @return a Producto instance
	 */
	public Producto convertToProductoEntity(RequestGuardarProductoVO requestGuardarProductoVO) {
		Producto producto = new Producto();
		return convertToProductoEntity(requestGuardarProductoVO, producto);		
	}

	/**
	 * Converts a RequestGuardarProductoVO to a Producto entity.
	 *
	 * @param requestGuardarProductoVO
	 *            the request RequestGuardarProductoVO
	 * @param entity
	 *            the Producto entity
	 *
	 * @return a Producto instance
	 */	
	public Producto convertToProductoEntity(RequestGuardarProductoVO requestGuardarProductoVO, Producto entity) {
		Producto producto = modelMapper.map(requestGuardarProductoVO, Producto.class);		
		//Mapeo automatico: Modificar aquí por uno customizado en caso de ser necesario (add entity values to producto)
		
		return producto;	
	}
	
	/**
	 * Converts a Producto to a ResponseGuardarProductoVO.
	 *
	 * @param producto
	 *            the Producto entity
	 *
	 * @return a ResponseGuardarProductoVO instance
	 */
	public ResponseGuardarProductoVO convertToVO(Producto producto) {
		ResponseGuardarProductoVO responseGuardarProductoVO = new ResponseGuardarProductoVO();
		return convertToVO(producto, responseGuardarProductoVO);
	}

	/**
	 * Converts a Producto to a ResponseGuardarProductoVO.
	 *
	 * @param producto
	 *            the Producto entity
	 * @param responseVO
	 *            the ResponseGuardarProductoVO
	 *
	 * @return a ResponseGuardarProductoVO instance
	 */
	public ResponseGuardarProductoVO convertToVO(Producto producto, ResponseGuardarProductoVO responseVO) {
		ResponseGuardarProductoVO responseGuardarProductoVO = modelMapper.map(producto, ResponseGuardarProductoVO.class);
		//Mapeo automatico: Modificar aquí por uno customizado en caso de ser necesario (add responseVO values to responseGuardarProductoVO)
		
		return responseGuardarProductoVO;
	}
	
	/**
	 * Converts a RequestGuardarProductoVO to a ProductoCalendario entity.
	 *
	 * @param requestGuardarProductoVO
	 *            the request RequestGuardarProductoVO
	 *
	 * @return a ProductoCalendario instance
	 */
	public ProductoCalendario convertToProductoCalendarioEntity(RequestGuardarProductoVO requestGuardarProductoVO) {
		ProductoCalendario productoCalendario = new ProductoCalendario();
		return convertToProductoCalendarioEntity(requestGuardarProductoVO, productoCalendario);		
	}

	/**
	 * Converts a RequestGuardarProductoVO to a ProductoCalendario entity.
	 *
	 * @param requestGuardarProductoVO
	 *            the request RequestGuardarProductoVO
	 * @param entity
	 *            the ProductoCalendario entity
	 *
	 * @return a ProductoCalendario instance
	 */	
	public ProductoCalendario convertToProductoCalendarioEntity(RequestGuardarProductoVO requestGuardarProductoVO, ProductoCalendario entity) {
		ProductoCalendario productoCalendario = modelMapper.map(requestGuardarProductoVO, ProductoCalendario.class);		
		//Mapeo automatico: Modificar aquí por uno customizado en caso de ser necesario (add entity values to productoCalendario)
		
		return productoCalendario;	
	}
	
	/**
	 * Converts a ProductoCalendario to a ResponseGuardarProductoVO.
	 *
	 * @param productoCalendario
	 *            the ProductoCalendario entity
	 *
	 * @return a ResponseGuardarProductoVO instance
	 */
	public ResponseGuardarProductoVO convertToVO(ProductoCalendario productoCalendario) {
		ResponseGuardarProductoVO responseGuardarProductoVO = new ResponseGuardarProductoVO();
		return convertToVO(productoCalendario, responseGuardarProductoVO);
	}

	/**
	 * Converts a ProductoCalendario to a ResponseGuardarProductoVO.
	 *
	 * @param productoCalendario
	 *            the ProductoCalendario entity
	 * @param responseVO
	 *            the ResponseGuardarProductoVO
	 *
	 * @return a ResponseGuardarProductoVO instance
	 */
	public ResponseGuardarProductoVO convertToVO(ProductoCalendario productoCalendario, ResponseGuardarProductoVO responseVO) {
		ResponseGuardarProductoVO responseGuardarProductoVO = modelMapper.map(productoCalendario, ResponseGuardarProductoVO.class);
		//Mapeo automatico: Modificar aquí por uno customizado en caso de ser necesario (add responseVO values to responseGuardarProductoVO)
		
		return responseGuardarProductoVO;
	}
	
}