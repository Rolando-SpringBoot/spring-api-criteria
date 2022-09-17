package com.enagas.msc.producto.service.mapper;

import com.enagas.msc.producto.domain.Producto;
import com.enagas.msc.producto.domain.ProductoCalendario;
import com.enagas.msc.producto.service.vo.RequestValidarProductoVO;
import com.enagas.msc.producto.service.vo.ResponseValidarProductoVO;

/**
 * Interfaz con los métodos para el mapeo de datos del servicio ValidarProductoService
 */
public interface ValidarProductoMapperService {

	/**
	 * Converts a RequestValidarProductoVO to a Producto entity.
	 *
	 * @param requestValidarProductoVO
	 *            the request RequestValidarProductoVO
	 *
	 * @return a Producto instance
	 */
	public Producto convertToProductoEntity(RequestValidarProductoVO requestValidarProductoVO);

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
	public Producto convertToProductoEntity(RequestValidarProductoVO requestValidarProductoVO, Producto entity);
	
	/**
	 * Converts a Producto to a ResponseValidarProductoVO.
	 *
	 * @param producto
	 *            the Producto entity
	 *
	 * @return a ResponseValidarProductoVO instance
	 */
	public ResponseValidarProductoVO convertToVO(Producto producto);

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
	public ResponseValidarProductoVO convertToVO(Producto producto, ResponseValidarProductoVO responseVO);
	
	/**
	 * Converts a RequestValidarProductoVO to a ProductoCalendario entity.
	 *
	 * @param requestValidarProductoVO
	 *            the request RequestValidarProductoVO
	 *
	 * @return a ProductoCalendario instance
	 */
	public ProductoCalendario convertToProductoCalendarioEntity(RequestValidarProductoVO requestValidarProductoVO);

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
	public ProductoCalendario convertToProductoCalendarioEntity(RequestValidarProductoVO requestValidarProductoVO, ProductoCalendario entity);
	
	/**
	 * Converts a ProductoCalendario to a ResponseValidarProductoVO.
	 *
	 * @param productoCalendario
	 *            the ProductoCalendario entity
	 *
	 * @return a ResponseValidarProductoVO instance
	 */
	public ResponseValidarProductoVO convertToVO(ProductoCalendario productoCalendario);

	/**
	 * Converts a productoCalendario to a ResponseValidarProductoVO.
	 *
	 * @param productoCalendario
	 *            the ProductoCalendario entity
	 * @param responseVO
	 *            the ResponseValidarProductoVO
	 *
	 * @return a ResponseValidarProductoVO instance
	 */
	public ResponseValidarProductoVO convertToVO(ProductoCalendario productoCalendario, ResponseValidarProductoVO responseVO);
	
}