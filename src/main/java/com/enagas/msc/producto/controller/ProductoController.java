package com.enagas.msc.producto.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import javax.naming.NotContextException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enagas.arch.core.exception.BusinessException;
import com.enagas.msc.producto.domain.vo.RequestFiltrarPorPeriodoVO;
import com.enagas.msc.producto.domain.vo.RequestFiltrosObtieneProductoVO;
import com.enagas.msc.producto.domain.vo.RequestMultipleFilterProductoVO;
import com.enagas.msc.producto.domain.vo.RequestProductVO;
import com.enagas.msc.producto.domain.vo.RequestProductoVO;
import com.enagas.msc.producto.domain.vo.RequestProductoVerVO;
import com.enagas.msc.producto.domain.vo.ResponseMultipleFilterProductoVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoCalendarioVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoSessionVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoVerVO;
import com.enagas.msc.producto.domain.vo.ResponseTipProductoVO;
import com.enagas.msc.producto.exception.AppErrorCode;
import com.enagas.msc.producto.service.ProductoCalendarioService;
import com.enagas.msc.producto.service.ProductoService;
import com.enagas.msc.producto.service.ProductoVerService;
import com.enagas.msc.producto.service.TipoProductoService;
import com.enagas.msc.producto.service.vo.RequestFiltrosProductoVO;

/**
 * Controlador con endpoints relacionados al servicio Producto.
 */
@RestController
@RequestMapping("/producto")
public class ProductoController {

	/** Logger. */
	private static final Logger log = LoggerFactory.getLogger(ProductoController.class);

	/** The tproducto service. */
	private ProductoService productoService;

	/** The tproductoVer service. */
	private ProductoVerService productoVerService;

	/** The tproductoCalendario service. */
	private ProductoCalendarioService productoCalendarioService;

	/** The tipoProductoService service. */
	private TipoProductoService tipoProductoService;

	public ProductoController(ProductoService productoService, ProductoVerService productoVerService,
			ProductoCalendarioService productoCalendarioService, TipoProductoService tipoProductoService) {
		this.productoService = productoService;
		this.productoVerService = productoVerService;
		this.productoCalendarioService = productoCalendarioService;
		this.tipoProductoService = tipoProductoService;
	}

	/**
	 * Obtiene todos los productos.
	 *
	 * @param filtros
	 * @return una lista de instancias Producto
	 * @throws NotContextException
	 */

	@PostMapping(value = "/obtieneProductos", produces = { "application/json" })
	public ResponseEntity<List<ResponseProductoVO>> obtieneProductos(
			@RequestBody @Valid RequestFiltrosObtieneProductoVO filtros) {
		log.info("Empieza método getProductos :: Parámetros [{}] ", filtros);

		if(filtros.getFecIniProducto() != null && filtros.getFecFinProducto() != null){
			if (filtros.getFecIniProducto().isAfter(filtros.getFecFinProducto())) {
				throw new BusinessException(AppErrorCode.BUSI_010, String.format(AppErrorCode.BUSI_010.getReasonPhrase()));
			}
		}
		
		List<ResponseProductoVO> responseProductoVOs = productoService.obtieneProductos(filtros);
		log.info("Termina método getProductos :: Resultado [{}]", responseProductoVOs);
		return new ResponseEntity<List<ResponseProductoVO>>(responseProductoVOs, OK);
	}

	/**
	 * Gets a Producto for given id.
	 *
	 * @param id the id of the Producto
	 * @return a Producto instance
	 */
	@GetMapping(value = "/tproducto/{id}", produces = { "application/json" })
	public ResponseEntity<ResponseProductoVO> getProducto(@PathVariable Integer id) {
		log.info("Empieza método getProducto :: Parámetros [{}] ", id);
		ResponseProductoVO responseProductoVO = productoService.getProducto(id);
		log.info("Termina método getProducto :: Resultado [{}]", responseProductoVO);
		return new ResponseEntity<>(responseProductoVO, OK);
	}

	@PostMapping(value = "/tproducto/ids", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<List<ResponseProductoVO>> getProductos(@RequestBody List<Integer> ids) {
		log.info("Empieza método getProducto :: Parámetros [{}] ", ids);
		List<ResponseProductoVO> responseProductoVO = productoService.getProductos(ids);
		log.info("Termina método getProducto :: Resultado [{}]", responseProductoVO);
		return new ResponseEntity<>(responseProductoVO, OK);
	}

	/**
	 * Gets all ProductoVers.
	 *
	 * @param pageable the pageable
	 * @return a list of ProductoVer instances
	 */
	@GetMapping(value = "/tproductovers", produces = { "application/json" })
	public ResponseEntity<List<ResponseProductoVerVO>> getProductoVers(Pageable pageable) {
		log.info("Empieza método getProductoVers :: Parámetros [{}] ", pageable);
		List<ResponseProductoVerVO> responseProductoVerVOs = productoVerService.getProductoVers(pageable);
		log.info("Termina método getProductoVers :: Resultado [{}]", responseProductoVerVOs);
		return new ResponseEntity<>(responseProductoVerVOs, OK);
	}

	/**
	 * Gets a ProductoVer for given id.
	 *
	 * 
	 * @param id the id of the ProductoVer
	 * @return a ProductoVer instance
	 */
	@GetMapping(value = "/tproductover/{id}", produces = { "application/json" })
	public ResponseEntity<ResponseProductoVerVO> getProductoVer(@PathVariable Integer id) {
		log.info("Empieza método getProductoVer :: Parámetros [{}] ", id);
		ResponseProductoVerVO responseProductoVerVO = productoVerService.getProductoVer(id);
		log.info("Termina método getProductoVer :: Resultado [{}]", responseProductoVerVO);
		return new ResponseEntity<>(responseProductoVerVO, OK);
	}

	/**
	 * Adds a ProductoVer on database.
	 *
	 * @param RequestProductoVerVO the RequestProductoVerVO
	 * @return a ProductoVer instance
	 */
	@PostMapping(value = "/tproductover/add", consumes = { "application/json" }, produces = { "application/json" })

	public ResponseEntity<ResponseProductoVerVO> addProductoVer(
			@Valid @RequestBody RequestProductoVerVO requestProductoVerVO) {
		log.info("Empieza método addProductoVer :: Parámetros [{}] ", requestProductoVerVO);
		ResponseProductoVerVO responseProductoVerVO = productoVerService.saveProductoVer(requestProductoVerVO);
		log.info("Termina método addProductoVer :: Resultado [{}]", responseProductoVerVO);
		return new ResponseEntity<>(responseProductoVerVO, CREATED);
	}

	/**
	 * Updates a ProductoVer on database.
	 * 
	 * @param id                   the id of the ProductoVer
	 * @param RequestProductoVerVO the request ProductoVerVO
	 * @return a ResponseEntity instance
	 */

	@PutMapping(value = "/tproductover/update/{id}", consumes = { "application/json" }, produces = {
			"application/json" })
	public ResponseEntity<HttpStatus> updateProductoVer(@PathVariable Integer id,
			@Valid @RequestBody RequestProductoVerVO requestProductoVerVO) {
		log.info("Empieza método updateProductoVer :: Parámetros [{}, {}] ", id, requestProductoVerVO);
		productoVerService.updateProductoVer(id, requestProductoVerVO);
		log.info("Termina método updateProductoVer");
		return new ResponseEntity<>(NO_CONTENT);

	}

	/**
	 * Gets all ProductoCalendarios.
	 *
	 * @param pageable the pageable
	 * @return a list of ProductoCalendario instances
	 */
	@GetMapping(value = "/tproductocalendarios", produces = { "application/json" })
	public ResponseEntity<List<ResponseProductoCalendarioVO>> getProductoCalendarios(Pageable pageable) {
		log.info("Empieza método getProductoCalendarios :: Parámetros [{}] ", pageable);
		List<ResponseProductoCalendarioVO> responseProductoCalendarioVOs = productoCalendarioService
				.getProductoCalendarios(pageable);
		log.info("Termina método getProductoCalendarios :: Resultado [{}]", responseProductoCalendarioVOs);
		return new ResponseEntity<>(responseProductoCalendarioVOs, OK);
	}

	/**
	 * Gets a ProductoCalendario for given id.
	 *
	 * @param id the id of the ProductoCalendario
	 * @return a ProductoCalendario instance
	 */
	@GetMapping(value = "/tproductocalendario/{id}", produces = { "application/json" })
	public ResponseEntity<ResponseProductoCalendarioVO> getProductoCalendario(@PathVariable Integer id) {
		log.info("Empieza método getProductoCalendario :: Parámetros [{}] ", id);
		ResponseProductoCalendarioVO responseProductoCalendarioVO = productoCalendarioService.getProductoCalendario(id);
		log.info("Termina método getProductoCalendario :: Resultado [{}]", responseProductoCalendarioVO);
		return new ResponseEntity<>(responseProductoCalendarioVO, OK);
	}

	/**
	 * Obtiene una lista de productos a partir de un listado de filtros, cuyo
	 * criterio de búsqueda es:<br>
	 * Dado un rango de fechas, la sesión del producto ha de recaer parcial o
	 * totalmente dentro de éste. Adicionalmente, puede indicarse un servicio sobre
	 * el que concretar aún más esa búsqueda.
	 *
	 * <p>
	 * Dentro de cada filtro, la fecha inicial del rango es obligatoria; no así la
	 * final, en cuya ausencia se asume que el rango es de duración indeterminada.
	 * <br>
	 * Algo parecido aplica al servicio. Si no se indica, la búsqueda englobará a
	 * cualquiera relacionado con ese producto(s).
	 * 
	 * @param servicioFilters lista de filtros, cada uno constituido por un rango de
	 *                        fechas y, opcionalmente, un servicio asociado
	 * @return lista de productos afectados
	 */
	@PostMapping(value = "/tproducto/filtrarporperiodo", produces = { "application/json" })
	public ResponseEntity<List<ResponseProductoVO>> filtrarPorPeriodo(
			@RequestBody List<RequestFiltrarPorPeriodoVO> servicioFilters) {
		log.info("Empieza método getAffectedByServices :: Parámetros [{}] ", servicioFilters);
		List<ResponseProductoVO> affectedProducts = productoService.filtrarPorPeriodo(servicioFilters);
		log.info("Termina método getAffectedByServices :: Resultado [{}]", affectedProducts);
		return new ResponseEntity<>(affectedProducts, OK);
	}

	/**
	 * Gets all TipProductos.
	 * 
	 * @param idTipoProductoList a list of TipProduct ids
	 * @param ordPorCol          the name of the column to sort by
	 * @param direccion          the order management (asc o desc)
	 * @return a list of TipProducto instances
	 */
	@GetMapping(value = "/obtenerTipoProducto", produces = { "application/json" })
	public ResponseEntity<List<ResponseTipProductoVO>> obtenerTipoProducto(
			@RequestParam(name = "ids", required = false) List<Integer> idTipoProductoList,
			@RequestParam(name = "idnServAtr", defaultValue = "", required = false) List<Integer> idnServAtr,
			@RequestParam(name = "ordporcol", defaultValue = "numOrden", required = false) String ordPorCol,
			@RequestParam(name = "direccion", defaultValue = "asc", required = false) String direccion) {

		log.info("Empieza metodo getTipProductos :: Parametros [{},{},{}] ", idTipoProductoList, ordPorCol, direccion);

		Sort sort = Sort.by(Direction.fromString(direccion), ordPorCol);
		List<ResponseTipProductoVO> responseTipProductoVOs = this.tipoProductoService
				.obtenerTipoProducto(idTipoProductoList, idnServAtr, sort);

		log.info("Termina método getTipProductos :: Resultado [{}]", responseTipProductoVOs);
		return new ResponseEntity<>(responseTipProductoVOs, OK);
	}
	
	/**
	 * Obtener tipo de producto, operación que permite recuperar un tipo de producto
	 * por un id.
	 * 
	 * @param id identificador del tipo de producto
	 * @return tipo de producto
	 */
	@GetMapping(value = "/tipoProducto/id", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseTipProductoVO> getTipoProducto(@RequestParam Integer id) {
		log.info("Empieza método getTipoProducto :: Parámetros [{}] ", id);
		ResponseTipProductoVO response = this.tipoProductoService.getTipoProducto(id);
		log.info("Termina método getTipoProducto :: Resultado [{}]", response);
		return new ResponseEntity<>(response, OK);
	}

	/**
	 * Dar de alta productos, bajo validaciones.
	 * 
	 * @param requestProductVO modelo con datos necesarios para el alta
	 * @return respuesta contiene mensaje de respuesta al alta
	 */
	@PostMapping(value = "/add-product", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<List<ResponseProductoVO>> addProduct(@Valid @RequestBody RequestProductVO requestProductVO) {
		log.info("Empieza método add-producto :: Parámetros [{}] ", requestProductVO);
		List<ResponseProductoVO> responseProductoVOs = productoService.addProduct(requestProductVO);
		log.info("Termina método add-producto :: Resultado [{}] ", responseProductoVOs);
		return new ResponseEntity<List<ResponseProductoVO>>(responseProductoVOs, OK);
	}

	/**
	 * Actualizar producto, nos permite actualizar la información del producto
	 * 
	 * @param requestProductVO modelo que contiene datos a modificar
	 * @return respuesta objeto de respuesta con los datos nuevos.
	 */
	@PutMapping(value = "/edit-product", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<ResponseProductoVO> editProduct(@Valid @RequestBody RequestProductVO requestProductVO) {
		log.info("Empieza método edit-product :: Parámetros [{}] ", requestProductVO);
		ResponseProductoVO respuesta = productoService.editProduct(requestProductVO);
		log.info("Termina método edit-product :: Resultado [{}] ", respuesta);
		return new ResponseEntity<ResponseProductoVO>(respuesta, OK);

	}

	/**
	 * Modificar producto, operación que nos permite modificar información de un
	 * producto determinado
	 * 
	 * @param id                the id of the Producto
	 * @param RequestProductoVO the request ProductoVO
	 * @return a ResponseEntity instance
	 */
	@PutMapping(value = "/tproducto/update/{id}", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<HttpStatus> updateProducto(@PathVariable Integer id,
			@Valid @RequestBody RequestProductoVO requestProductoVO) {
		log.info("Empieza método updateProducto :: Parámetros [{}, {}] ", id, requestProductoVO);
		productoService.updateProducto(id, requestProductoVO);
		log.info("Termina método updateProducto");
		return new ResponseEntity<>(NO_CONTENT);
	}

	/**
	 * Se actualiza los precios y capacidades del producto seleccionado
	 * 
	 * @author mdesantb
	 * @param requestProductoVo the RequestProductoVO
	 * @return producto actualizado
	 */
	@PostMapping(value = "/tproducto/updateproducto/", consumes = { "application/json" }, produces = {
			"application/json" })
	public ResponseEntity<ResponseProductoVO> updateProducto(@RequestBody RequestProductoVO requestProductoVo) {
		log.info("Empieza método updateProducto :: Parámetros [{}] ", requestProductoVo);
		ResponseProductoVO respuesta = productoService.updateProducto(requestProductoVo);
		log.info("Termina método updateProducto :: Resultado [{}] ", respuesta);
		return new ResponseEntity<ResponseProductoVO>(respuesta, OK);
	}

	/**
	 * Eliminar producto
	 * 
	 * @param idnProducts lista de identificadores de productos a eliminar
	 * @return <List<RequestProductoVO>> modelo de respuesta a la operación.
	 */
	@DeleteMapping(value = "/delete-product", produces = { "application/json" })
	public ResponseEntity<List<RequestProductoVO>> deleteProduct(@RequestParam List<Integer> idnProducts) {
		log.info("Empieza método delete-product :: Parámetros [{}] ", idnProducts);
		if (idnProducts.isEmpty() || idnProducts == null) {
			throw new BusinessException(AppErrorCode.BUSI_012,
					String.format(AppErrorCode.BUSI_012.getReasonPhrase()));
		}
		List<RequestProductoVO> responseDeleteProductsVO = productoService.deleteProduct(idnProducts);
		log.info("Termina método delete-product :: Resultado [{}] ", idnProducts);
		return new ResponseEntity<List<RequestProductoVO>>(responseDeleteProductsVO, OK);
	}

	/**
	 * Metodo para obtener productos, bajo filtros de entrada.
	 * 
	 * @param requestFiltrosProductoVO modelo de entrada para filtros de busqueda
	 * @return ResponseProductoVO lista de objetos de respuesta a la busqueda.
	 */
	@PostMapping(value = "/get-products", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<List<ResponseProductoVO>> getProducts(
			@Valid @RequestBody RequestFiltrosProductoVO requestFiltrosProductoVO) {
		log.info("Empieza método get-products :: Parámetros [{}] ", requestFiltrosProductoVO);
		if (requestFiltrosProductoVO.getFecIniProducto() != null
				&& requestFiltrosProductoVO.getFecFinProducto() != null) {
			if (requestFiltrosProductoVO.getFecFinProducto()
					.isBefore(requestFiltrosProductoVO.getFecIniProducto())) {
				throw new BusinessException(AppErrorCode.BUSI_010,
						String.format(AppErrorCode.BUSI_010.getReasonPhrase()));
			}
		}
		List<ResponseProductoVO> responseProductoVOS = productoService.getProducts(requestFiltrosProductoVO);
		log.info("Termina método get-products :: Resultado [{}] ", responseProductoVOS);
		return new ResponseEntity<>(responseProductoVOS, OK);

	}
	
	/**
	 * Metodo para obtener productos, bajo filtros de entrada. Exclusivo para obtener productos con fecha inicio session 
	 * anterior a la recibida en el filtro.
	 * 
	 * @param requestFiltrosProductoVO modelo de entrada para filtros de busqueda
	 * @return ResponseProductoVO lista de objetos de respuesta a la busqueda.
	 */
	@PostMapping(value = "/get-products-session", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<List<ResponseProductoVO>> getProductsSession(
			@Valid @RequestBody RequestFiltrosProductoVO requestFiltrosProductoVO) {
		log.info("Empieza método get-products-session :: Parámetros [{}] ", requestFiltrosProductoVO);
		if (requestFiltrosProductoVO.getFecIniProducto() != null
				&& requestFiltrosProductoVO.getFecFinProducto() != null) {
			if (requestFiltrosProductoVO.getFecFinProducto()
					.isBefore(requestFiltrosProductoVO.getFecIniProducto())) {
				throw new BusinessException(AppErrorCode.BUSI_010,
						String.format(AppErrorCode.BUSI_010.getReasonPhrase()));
			}
		}
		List<ResponseProductoVO> responseProductoVOS = productoService.getProductsFecSession(requestFiltrosProductoVO);
		log.info("Termina método get-products-session :: Resultado [{}] ", responseProductoVOS);
		return new ResponseEntity<>(responseProductoVOS, OK);

	}

	/**
	 * Listar productos por sesión
	 * 
	 * @param period Periodo MMYYYY
	 * @return Lista de productos en el periodo ingresado
	 */
	@GetMapping(value = "/products-session", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResponseProductoSessionVO>> getProductsBySession(
			@RequestParam(name = "period", required = true) String period) {
		log.info("Empieza método getProductsBySession :: Parámetros[{}]", period);
		List<ResponseProductoSessionVO> responseProductoSessionVOs = this.productoService.getProductsBySession(period);
		log.info("Termina método getProductsBySession :: Parámetros[{}]", responseProductoSessionVOs);
		return new ResponseEntity<>(responseProductoSessionVOs, HttpStatus.OK);
	}

	/**
	 * Validar producto vinculada a la oferta, operaciones que nos permite validar
	 * si un producto tiene vinculo a la oferta con estado "enviada" o "casada o "provisional" o
	 * "compatible" o "precasada"
	 * 
	 * @param idnProduct Identificador del producto
	 * @return TRUE or FALSE
	 */
	@GetMapping("/valid-product")
	public ResponseEntity<Boolean> validProductLinkOffer(@RequestParam(name = "idnProducto") Integer idnProduct) {
		return new ResponseEntity<Boolean>(productoService.validProductLinkOffer(idnProduct), OK);
	}

	/**
	 * Permite la ejecución simultánea de varias búsquedas independientes.
	 * 
	 * <p>Cada búsqueda está aislada del resto gracias al encapsulamiento de
	 * su identificador y de su resultado en un objeto propio. Esto nos
	 * permite tratar las búsquedas como una colección de objetos.
	 * 
	 * <p>Es un wrapper del método {@link #obtieneProductos obtieneProductos}
	 * 
	 * @param request Listado de búsquedas, cada una particularizada por un identificador y un filtro de búsqueda
	 * @return Listado de resultados, cada uno con su identificador original y un listado de los productos obtenidos
	 */
	@PostMapping(value = "/obtenerBusquedasProductos", produces = { "application/json" })
	public ResponseEntity<ResponseMultipleFilterProductoVO> findMultipleProducto(
			@RequestBody @Valid RequestMultipleFilterProductoVO request) {

		log.info("Empieza método findMultipleProducto :: Parámetros [{}] ", request);

		ResponseMultipleFilterProductoVO response = productoService.findMultipleProducto(request);

		log.info("Termina método findMultipleProducto :: Resultado [{}]", response);
		return new ResponseEntity<>(response, OK);
    }
	
}
