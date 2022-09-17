package com.enagas.msc.producto.service.mapper;

import java.util.List;

import com.enagas.msc.producto.domain.ProductoCalendario;
import com.enagas.msc.producto.domain.vo.RequestProductoCalendarioVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoCalendarioVO;

/**
 * Interface with methods to convert entities to VOs and viceversa.
 */
public interface ProductoCalendarioMapperService {

	/**
	 * Converts a list of ProductoCalendarios to a list of ResponseProductoCalendario.
	 *
	 * @param productoCalendario
	 *            the ProductoCalendario entity
	 * @return a list of ResponseProductoCalendarioVO instances
	 */
	List<ResponseProductoCalendarioVO> convertToVO(List<ProductoCalendario> productoCalendario);

	/**
	 * Converts a ProductoCalendario to a ResponseProductoCalendarioVO.
	 *
	 * @param productoCalendario
	 *            the ProductoCalendario entity
	 * @return a ResponseProductoCalendarioVO instance
	 */
	ResponseProductoCalendarioVO convertToVO(ProductoCalendario productoCalendario);
	
	/**
	 * Converts a RequestProductoCalendarioVO to a ProductoCalendario entity.
	 *
	 * @param requestProductoCalendarioVO
	 *            the request ProductoCalendario VO
	 * @return a ProductoCalendario instance
	 */
	ProductoCalendario convertToEntity(RequestProductoCalendarioVO requestProductoCalendarioVO);

}