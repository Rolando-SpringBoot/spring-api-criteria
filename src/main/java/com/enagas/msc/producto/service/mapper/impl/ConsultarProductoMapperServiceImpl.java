package com.enagas.msc.producto.service.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.enagas.msc.producto.domain.Producto;
import com.enagas.msc.producto.service.mapper.ConsultarProductoMapperService;
import com.enagas.msc.producto.service.vo.RequestConsultarProductoVO;
import com.enagas.msc.producto.service.vo.ResponseConsultarProductoVO;

/**
 * Service that implements the ConsultarProductoMapperService interface.
 */
@Service
public class ConsultarProductoMapperServiceImpl implements ConsultarProductoMapperService {

	/** The ModelMapper instance. */
	private static final ModelMapper modelMapper = new ModelMapper();

	/**
	 * Converts a RequestConsultarProductoVO to a Producto entity.
	 *
	 * @param requestConsultarProductoVO
	 *            the request RequestConsultarProductoVO
	 *
	 * @return a Producto instance
	 */
	public Producto convertToProductoEntity(RequestConsultarProductoVO requestConsultarProductoVO) {
		Producto producto = new Producto();
		return convertToProductoEntity(requestConsultarProductoVO, producto);		
	}

	/**
	 * Converts a RequestConsultarProductoVO to a Producto entity.
	 *
	 * @param requestConsultarProductoVO
	 *            the request RequestConsultarProductoVO
	 * @param entity
	 *            the Producto entity
	 *
	 * @return a Producto instance
	 */	
	public Producto convertToProductoEntity(RequestConsultarProductoVO requestConsultarProductoVO, Producto entity) {
		Producto producto = modelMapper.map(requestConsultarProductoVO, Producto.class);		
		//Mapeo automatico: Modificar aquí por uno customizado en caso de ser necesario (add entity values to producto)
		
		return producto;	
	}
	
	/**
	 * Converts a Producto to a ResponseConsultarProductoVO.
	 *
	 * @param producto
	 *            the Producto entity
	 *
	 * @return a ResponseConsultarProductoVO instance
	 */
	public ResponseConsultarProductoVO convertToVO(Producto producto) {
		ResponseConsultarProductoVO responseConsultarProductoVO = new ResponseConsultarProductoVO();
		return convertToVO(producto, responseConsultarProductoVO);
	}

	/**
	 * Converts a Producto to a ResponseConsultarProductoVO.
	 *
	 * @param producto
	 *            the Producto entity
	 * @param responseVO
	 *            the ResponseConsultarProductoVO
	 *
	 * @return a ResponseConsultarProductoVO instance
	 */
	public ResponseConsultarProductoVO convertToVO(Producto producto, ResponseConsultarProductoVO responseVO) {
		ResponseConsultarProductoVO responseConsultarProductoVO = modelMapper.map(producto, ResponseConsultarProductoVO.class);
		//Mapeo automatico: Modificar aquí por uno customizado en caso de ser necesario (add responseVO values to responseConsultarProductoVO)
		
		return responseConsultarProductoVO;
	}
	
}