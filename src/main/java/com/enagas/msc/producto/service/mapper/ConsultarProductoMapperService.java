package com.enagas.msc.producto.service.mapper;



import com.enagas.msc.producto.service.vo.RequestConsultarProductoVO;
import com.enagas.msc.producto.service.vo.ResponseConsultarProductoVO;
import com.enagas.msc.producto.domain.Producto;

/**
 * Interfaz con los métodos para el mapeo de datos del servicio ConsultarProductoService
 */
public interface ConsultarProductoMapperService {

	/**
	 * Converts a RequestConsultarProductoVO to a Producto entity.
	 *
	 * @param requestConsultarProductoVO
	 *            the request RequestConsultarProductoVO
	 *
	 * @return a Producto instance
	 */
	public Producto convertToProductoEntity(RequestConsultarProductoVO requestConsultarProductoVO);

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
	public Producto convertToProductoEntity(RequestConsultarProductoVO requestConsultarProductoVO, Producto entity);
	
	/**
	 * Converts a Producto to a ResponseConsultarProductoVO.
	 *
	 * @param producto
	 *            the Producto entity
	 *
	 * @return a ResponseConsultarProductoVO instance
	 */
	public ResponseConsultarProductoVO convertToVO(Producto producto);

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
	public ResponseConsultarProductoVO convertToVO(Producto producto, ResponseConsultarProductoVO responseVO);
	
}