package com.enagas.msc.producto.service.mapper;

import java.util.List;

import com.enagas.msc.producto.domain.TipProducto;
import com.enagas.msc.producto.domain.vo.ResponseTipProductoVO;

/**
 * Interface with methods to convert entities to VOs and viceversa.
 */
public interface TipProductoMapperService {

	/**
	 * Converts a list of TipProductos to a list of ResponseTipProducto.
	 *
	 * @param TipProducto the TipProducto entity
	 * @return a list of ResponseTipProductoVO instances
	 */
	List<ResponseTipProductoVO> convertToVO(List<TipProducto> tipProducto);

	/**
	 * Converts a TipProducto to a ResponseTipProductoVO.
	 *
	 * @param tipProducto the TipProducto entity
	 * @return a ResponseTipProductoVO instance
	 */
	ResponseTipProductoVO convertToVO(TipProducto tipProducto);

}
