package com.enagas.msc.producto.service;

import java.util.List;

import javax.naming.NotContextException;

import com.enagas.msc.producto.domain.vo.RequestFiltrarPorPeriodoVO;
import com.enagas.msc.producto.domain.vo.RequestFiltrosObtieneProductoVO;
import com.enagas.msc.producto.domain.vo.RequestMultipleFilterProductoVO;
import com.enagas.msc.producto.domain.vo.RequestProductVO;
import com.enagas.msc.producto.domain.vo.RequestProductoVO;
import com.enagas.msc.producto.domain.vo.ResponseMultipleFilterProductoVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoSessionVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoVO;
import com.enagas.msc.producto.service.vo.RequestFiltrosProductoVO;

/**
 * Interface with the methods with the business logic related to the
 * Producto entity.
 */
public interface ProductoService {

	/**
	 * Gets all productos.
	 *
	 * @param pageable
	 *            the pageable
	 * @return a list of ResponseProductoVO instances
	 * @throws NotContextException 
	 */	 
	List<ResponseProductoVO> obtieneProductos(RequestFiltrosObtieneProductoVO filtros);

	/**
	 * Gets a producto for given id.
	 *
	 * @param id
	 *            the id of the producto
	 * @return a ResponseProductoVO instance
	 */
	ResponseProductoVO getProducto(Integer id);
	
	List<ResponseProductoVO> getProductos(List<Integer> ids);

	/**
	 * Adds a producto on database.
	 *
	 * @param RequestProductoVO
	 *            the RequestProductoVO
	 * @return a ResponseProductoVO instance
	 */
	ResponseProductoVO saveProducto(RequestProductoVO requestProductoVO);
	
	/**
	 * Updates a producto on database.
	 *
	 * @param id
	 *            the id of the producto
	 * @param RequestProductoVO
	 *            the RequestProductoVO
	 */
	void updateProducto(Integer id, RequestProductoVO requestProductoVO);

	/**
	 * Deletes a producto on database.
	 *
	 * @param id
	 *            the id of the producto
	 */
	void deleteProducto(Integer id);
	
	/**
	 * Obtiene un listado de productos cuya sesión coincide en uno o más días
	 * con un rango de fechas para un servicio concreto, siendo este último
	 * un parámetro opcional y pudiendo repetir la búsqueda para más de un
	 * rango y servicio, en forma de listado de filtros.
	 * 
	 * @param requestFiltrarPorPeriodoVOs lista de filtros con un rango y, adicionalmente, un servicio concretos
	 * @return lista de productos afectados
	 */
	List<ResponseProductoVO> filtrarPorPeriodo(List<RequestFiltrarPorPeriodoVO> requestFiltrarPorPeriodoVOs);
	
	/**
	 * Servicio para actualizar los precios y capacidad del producto
	 * @author mdesantb
	 * @param productoVo
	 * 			then RequestProductoVO
	 * @return ResponseProductoVO
	 */
	public ResponseProductoVO updateProducto(RequestProductoVO productoVo);
	
	/**
	 * A partir de un listado de búsquedas de Productos, ejecuta una a una
	 * todas ellas, organizando el resultado de manera secuencial e 
	 * independiente.
	 * 
	 * <p>Cada búsqueda consta de un identificador y un filtro de búsqueda.
	 * El identificador se empleará para asociar el resultado de nuevo a la
	 * búsqueda a la que correspondía.
	 * 
	 * @param filters Lista de varias búsquedas, cada una con su identificador y un filtro de búsqueda
	 * @return Listado de resultados, segregados por los mismos identificadores de las búsquedas
	 */
	public ResponseMultipleFilterProductoVO findMultipleProducto(RequestMultipleFilterProductoVO filters);
	
	/**Implementacion para el alta de producto*/
	
	List<ResponseProductoVO> addProduct(RequestProductVO requestProductVO);
	
	ResponseProductoVO editProduct(RequestProductVO requestProductVO);
	
	List<RequestProductoVO> deleteProduct(List<Integer> idnProducts);
	
	List<ResponseProductoVO> getProducts(RequestFiltrosProductoVO requestFiltrosProductoVO);

	/**
	 * Validar producto vinculada a la oferta, operaciones que nos permite validar
	 * si un producto tiene vinculo a la oferta con estado "enviada" o "casada"
	 * 
	 */
	Boolean validProductLinkOffer(Integer idProducto);
	
	
	
	List<ResponseProductoSessionVO> getProductsBySession(String period);

	/**
	 * Obtiene los productos cuya fecha inicio sesión es anterior o igual a la recibida por el filtro
	 * y además que la fecha de fin de sessión sea posterior a esta recibida.
	 * @param requestFiltrosProductoVO
	 * @return
	 */
	List<ResponseProductoVO> getProductsFecSession( RequestFiltrosProductoVO requestFiltrosProductoVO);

}