package com.enagas.msc.producto.service.mapper;

import java.util.List;

import com.enagas.msc.producto.domain.ProductoVer;
import com.enagas.msc.producto.domain.vo.RequestProductoVO;
import com.enagas.msc.producto.domain.vo.RequestProductoVerVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoVerVO;

/**
 * Interface with methods to convert entities to VOs and viceversa.
 */
public interface ProductoVerMapperService {

	/**
	 * Converts a list of ProductoVers to a list of ResponseProductoVer.
	 *
	 * @param productoVer
	 *            the ProductoVer entity
	 * @return a list of ResponseProductoVerVO instances
	 */
	List<ResponseProductoVerVO> convertToVO(List<ProductoVer> productoVer);

	/**
	 * Converts a ProductoVer to a ResponseProductoVerVO.
	 *
	 * @param productoVer
	 *            the productoVer entity
	 * @return a ResponseProductoVerVO instance
	 */
	ResponseProductoVerVO convertToVO(ProductoVer productoVer);
	
	/**
	 * Converts a RequestProductoVerVO to a ProductoVer entity.
	 *
	 * @param requestProductoVerVO
	 *            the request ProductoVer VO
	 * @return a ProductoVer instance
	 */
	ProductoVer convertToEntity(RequestProductoVerVO requestProductoVerVO);
	
	/**
	 * Rellenamos los datos necesarios para insertar en la entidad PRODUCTO_VER
	 * @author mdesantb
	 * @param productoVer, requestProductoVo
	 * 				the ProductoVer, RequestProductoVO
	 * @return ProductoVer
	 */
	public ProductoVer convertToEntity (ProductoVer productoVer, RequestProductoVO requestProductoVo);

}