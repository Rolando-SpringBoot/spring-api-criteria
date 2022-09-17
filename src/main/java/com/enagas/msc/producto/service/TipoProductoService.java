package com.enagas.msc.producto.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.enagas.msc.producto.domain.vo.ResponseTipProductoVO;

/**
 * Interface with the methods with the business logic related to the
 * TipProducto entity.
 */
public interface TipoProductoService {

	/**
	 * Gets all tipProductos.
	 *
	 * @param pageable the pageable
	 * @return a list of ResponseTipProductoVO instances
	 */
	List<ResponseTipProductoVO> obtenerTipoProducto(List<Integer> idTipoProductoList, 
			List<Integer> idServAtr, Sort sort);
	
	/**
	 * Obtener tipo de producto, operación que permite recuperar un tipo de producto
	 * a partir de un identificador.
	 * 
	 * @param id - el identificador del tipo de Producto
	 * @return una instancia ResponseTipProductoVO
	 */
	public ResponseTipProductoVO getTipoProducto(Integer id);

}
