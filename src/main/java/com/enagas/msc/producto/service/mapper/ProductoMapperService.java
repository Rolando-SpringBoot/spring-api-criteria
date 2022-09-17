package com.enagas.msc.producto.service.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enagas.msc.producto.domain.Producto;
import com.enagas.msc.producto.domain.ProductoDeOferta;
import com.enagas.msc.producto.domain.vo.RequestFiltrarPorPeriodoVO;
import com.enagas.msc.producto.domain.vo.RequestFiltrosObtieneProductoVO;
import com.enagas.msc.producto.domain.vo.RequestProductoVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoSessionVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoVO;
import com.enagas.msc.producto.service.vo.RequestFiltrosProductoVO;
import com.enagas.msc.producto.service.vo.RequestServicioFilterVO;

/**
 * Interface with methods to convert entities to VOs and viceversa.
 */
@Service
public interface ProductoMapperService {

	/**
	 * Converts a list of Productos to a list of ResponseProducto.
	 *
	 * @param producto
	 *            the Producto entity
	 * @return a list of ResponseProductoVO instances
	 */
	List<ResponseProductoVO> convertToVO(List<Producto> producto);

	/**
	 * Converts a producto to a ResponseProductoVO.
	 *
	 * @param producto
	 *            the Producto entity
	 * @return a ResponseProductoVO instance
	 */
	ResponseProductoVO convertToVO(Producto producto);
	
	List<ResponseProductoVO> convertToVOs(List<ProductoDeOferta> productoDeOferta);
	
	/**
	 * Converts a RequestProductoVO to a Producto entity.
	 *
	 * @param requestProductoVO
	 *            the request Producto VO
	 * @return a Producto instance
	 */
	Producto convertToEntity(RequestProductoVO requestProductoVO);
	
	
	RequestServicioFilterVO convertToVO(RequestFiltrarPorPeriodoVO requestProductoServicioFilterVO);

	RequestFiltrosProductoVO convertToVO(RequestFiltrosObtieneProductoVO requestFiltrosProductoVO);

	List<ResponseProductoSessionVO> convertToProductoSessionVOs(List<Producto> producto);
	
}