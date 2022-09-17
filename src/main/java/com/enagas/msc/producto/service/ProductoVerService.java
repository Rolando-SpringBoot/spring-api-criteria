package com.enagas.msc.producto.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.enagas.msc.producto.domain.vo.RequestProductoVerVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoVerVO;

/**
 * Interface with the methods with the business logic related to the
 * ProductoVer entity.
 */
public interface ProductoVerService {

	/**
	 * Gets all productoVers.
	 *
	 * @param pageable
	 *            the pageable
	 * @return a list of ResponseProductoVerVO instances
	 */	 
	List<ResponseProductoVerVO> getProductoVers(Pageable pageable);

	/**
	 * Gets a productoVer for given id.
	 *
	 * @param id
	 *            the id of the productoVer
	 * @return a ResponseProductoVerVO instance
	 */
	ResponseProductoVerVO getProductoVer(Integer id);

	/**
	 * Adds a productoVer on database.
	 *
	 * @param RequestProductoVerVO
	 *            the RequestProductoVerVO
	 * @return a ResponseProductoVerVO instance
	 */
	ResponseProductoVerVO saveProductoVer(RequestProductoVerVO requestProductoVerVO);
	
	/**
	 * Updates a productoVer on database.
	 *
	 * @param id
	 *            the id of the productoVer
	 * @param RequestProductoVerVO
	 *            the RequestProductoVerVO
	 */
	void updateProductoVer(Integer id, RequestProductoVerVO requestProductoVerVO);

	/**
	 * Deletes a productoVer on database.
	 *
	 * @param id
	 *            the id of the productoVer
	 */
	void deleteProductoVer(Integer id);
	
}