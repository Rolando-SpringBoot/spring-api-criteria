package com.enagas.msc.producto.service.mapper;

import com.enagas.msc.producto.domain.Producto;
import com.enagas.msc.producto.domain.ProductoCalendario;
import com.enagas.msc.producto.service.vo.RequestGuardarProductoVO;
import com.enagas.msc.producto.service.vo.ResponseGuardarProductoVO;

/**
 * Interfaz con los métodos para el mapeo de datos del servicio GuardarProductoService
 */
public interface GuardarProductoMapperService {

	/**
	 * Converts a RequestGuardarProductoVO to a Producto entity.
	 *
	 * @param requestGuardarProductoVO
	 *            the request RequestGuardarProductoVO
	 *
	 * @return a Producto instance
	 */
	public Producto convertToProductoEntity(RequestGuardarProductoVO requestGuardarProductoVO);

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
	public Producto convertToProductoEntity(RequestGuardarProductoVO requestGuardarProductoVO, Producto entity);
	
	/**
	 * Converts a Producto to a ResponseGuardarProductoVO.
	 *
	 * @param producto
	 *            the Producto entity
	 *
	 * @return a ResponseGuardarProductoVO instance
	 */
	public ResponseGuardarProductoVO convertToVO(Producto producto);

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
	public ResponseGuardarProductoVO convertToVO(Producto producto, ResponseGuardarProductoVO responseVO);
	
	/**
	 * Converts a RequestGuardarProductoVO to a ProductoCalendario entity.
	 *
	 * @param requestGuardarProductoVO
	 *            the request RequestGuardarProductoVO
	 *
	 * @return a ProductoCalendario instance
	 */
	public ProductoCalendario convertToProductoCalendarioEntity(RequestGuardarProductoVO requestGuardarProductoVO);

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
	public ProductoCalendario convertToProductoCalendarioEntity(RequestGuardarProductoVO requestGuardarProductoVO, ProductoCalendario entity);
	
	/**
	 * Converts a ProductoCalendario to a ResponseGuardarProductoVO.
	 *
	 * @param productoCalendario
	 *            the ProductoCalendario entity
	 *
	 * @return a ResponseGuardarProductoVO instance
	 */
	public ResponseGuardarProductoVO convertToVO(ProductoCalendario productoCalendario);

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
	public ResponseGuardarProductoVO convertToVO(ProductoCalendario productoCalendario, ResponseGuardarProductoVO responseVO);
	
}