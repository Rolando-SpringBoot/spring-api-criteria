package com.enagas.msc.producto.service.mapper;

import com.enagas.msc.producto.domain.ProductoCalendario;
import com.enagas.msc.producto.service.vo.RequestGenerarProductoVO;
import com.enagas.msc.producto.service.vo.ResponseGenerarProductoVO;

/**
 * Interfaz con los métodos para el mapeo de datos del servicio GenerarProductoService
 */
public interface GenerarProductoMapperService {

	/**
	 * Converts a RequestGenerarProductoVO to a productoCalendario entity.
	 *
	 * @param requestGenerarProductoVO
	 *            the request RequestGenerarProductoVO
	 *
	 * @return a ProductoCalendario instance
	 */
	public ProductoCalendario convertToProductoCalendarioEntity(RequestGenerarProductoVO requestGenerarProductoVO);

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
	public ProductoCalendario convertToProductoCalendarioEntity(RequestGenerarProductoVO requestGenerarProductoVO, ProductoCalendario entity);
	
	/**
	 * Converts a ProductoCalendario to a ResponseGenerarProductoVO.
	 *
	 * @param productoCalendario
	 *            the ProductoCalendario entity
	 *
	 * @return a ResponseGenerarProductoVO instance
	 */
	public ResponseGenerarProductoVO convertToVO(ProductoCalendario productoCalendario);

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
	public ResponseGenerarProductoVO convertToVO(ProductoCalendario productoCalendario, ResponseGenerarProductoVO responseVO);
	
}