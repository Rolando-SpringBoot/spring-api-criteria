package com.enagas.msc.producto.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.enagas.msc.producto.domain.vo.RequestProductoCalendarioVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoCalendarioVO;

/**
 * Interface with the methods with the business logic related to the
 * ProductoCalendario entity.
 */
public interface ProductoCalendarioService {

	/**
	 * Gets all productoCalendarios.
	 *
	 * @param pageable
	 *            the pageable
	 * @return a list of ResponseProductoCalendarioVO instances
	 */	 
	List<ResponseProductoCalendarioVO> getProductoCalendarios(Pageable pageable);

	/**
	 * Gets a productoCalendario for given id.
	 *
	 * @param id
	 *            the id of the productoCalendario
	 * @return a ResponseProductoCalendarioVO instance
	 */
	ResponseProductoCalendarioVO getProductoCalendario(Integer id);

	/**
	 * Adds a productoCalendario on database.
	 *
	 * @param RequestProductoCalendarioVO
	 *            the RequestProductoCalendarioVO
	 * @return a ResponseProductoCalendarioVO instance
	 */
	ResponseProductoCalendarioVO saveProductoCalendario(RequestProductoCalendarioVO requestProductoCalendarioVO);
	
	/**
	 * Updates a productoCalendario on database.
	 *
	 * @param id
	 *            the id of the productoCalendario
	 * @param RequestProductoCalendarioVO
	 *            the RequestProductoCalendarioVO
	 */
	void updateProductoCalendario(Integer id, RequestProductoCalendarioVO requestProductoCalendarioVO);

	/**
	 * Deletes a productoCalendario on database.
	 *
	 * @param id
	 *            the id of the productoCalendario
	 */
	void deleteProductoCalendario(Integer id);
	
}