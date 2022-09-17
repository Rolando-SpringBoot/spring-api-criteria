package com.enagas.msc.producto.service.impl;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.naming.NotContextException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.enagas.arch.core.exception.ArchErrorCode;
import com.enagas.arch.core.exception.BusinessException;
import com.enagas.arch.core.exception.TechnicalErrorException;
import com.enagas.arch.header.EHeader;
import com.enagas.arch.shareddata.SharedData;
import com.enagas.msc.producto.config.ParametersPropertiesConfig;
import com.enagas.msc.producto.domain.Producto;
import com.enagas.msc.producto.domain.ProductoVer;
import com.enagas.msc.producto.domain.TipProducto;
import com.enagas.msc.producto.domain.vo.RequestFiltrarPorPeriodoVO;
import com.enagas.msc.producto.domain.vo.RequestFiltrosObtieneProductoVO;
import com.enagas.msc.producto.domain.vo.RequestMultipleFilterProductoVO;
import com.enagas.msc.producto.domain.vo.RequestProductVO;
import com.enagas.msc.producto.domain.vo.RequestProductoVO;
import com.enagas.msc.producto.domain.vo.ResponseDeleteProductsVO;
import com.enagas.msc.producto.domain.vo.ResponseIdentifiedFilterProductoVO;
import com.enagas.msc.producto.domain.vo.ResponseMultipleFilterProductoVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoSessionVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoVO;
import com.enagas.msc.producto.exception.AppErrorCode;
import com.enagas.msc.producto.external.service.AdminParamExternalService;
import com.enagas.msc.producto.external.service.InstalacionesExternalRestService;
import com.enagas.msc.producto.external.service.OfertaExternalRestService;
import com.enagas.msc.producto.external.service.OperacionesAuxiliaresExternalRestService;
import com.enagas.msc.producto.external.service.ServicioExternalRestService;
import com.enagas.msc.producto.external.service.SujetoExternalRestService;
import com.enagas.msc.producto.external.vo.AtributoVigenteVO;
import com.enagas.msc.producto.external.vo.ConsultarOfertaVO;
import com.enagas.msc.producto.external.vo.RequestAdminParamVO;
import com.enagas.msc.producto.external.vo.RequestCodigoEntidadVO;
import com.enagas.msc.producto.external.vo.RequestConsultOffertsVO;
import com.enagas.msc.producto.external.vo.RequestConsultarAtribVigVO;
import com.enagas.msc.producto.external.vo.RequestGenerarDescripcionSujetoVO;
import com.enagas.msc.producto.external.vo.RequestValidarAccesoVO;
import com.enagas.msc.producto.external.vo.ResponseCodigoEntidadVO;
import com.enagas.msc.producto.external.vo.ResponseConsultOffertsVO;
import com.enagas.msc.producto.external.vo.ResponseConsultarAtribVigVO;
import com.enagas.msc.producto.external.vo.ResponseConsultarOfertasVO;
import com.enagas.msc.producto.external.vo.ResponseOfertaVO;
import com.enagas.msc.producto.external.vo.ResponseServAtrAtribVigVO;
import com.enagas.msc.producto.external.vo.ResponseServAtrVO;
import com.enagas.msc.producto.external.vo.ResponseServOfertadoVO;
import com.enagas.msc.producto.external.vo.ResponseServicioVO;
import com.enagas.msc.producto.external.vo.ResponseUnidadMedidaVO;
import com.enagas.msc.producto.external.vo.ResponseUnidadPrecioVO;
import com.enagas.msc.producto.repository.ProductoCalendarioRepository;
import com.enagas.msc.producto.repository.ProductoRepository;
import com.enagas.msc.producto.repository.ProductoVerRepository;
import com.enagas.msc.producto.repository.TipoproductoRepository;
import com.enagas.msc.producto.service.ProductoService;
import com.enagas.msc.producto.service.mapper.ProductoMapperService;
import com.enagas.msc.producto.service.mapper.ProductoVerMapperService;
import com.enagas.msc.producto.service.vo.RequestFiltrosProductoVO;
import com.enagas.msc.producto.utils.EnumConstants;

/**
 * Service that implements the ProductoService interface.
 */
@Service
public class ProductoServiceImpl implements ProductoService {

	/** Logger. */
	private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);

	/** The Constant ENTITY. */
	private static final String ENTITY_PRODUCTO = "Producto";
	private static final String ENTITY_TIPPRODUCTO = "Tipo Producto";
	private static final String ENTITY_UNIDADPRECIO = "Unidad Precio";
	private static final String ENTITY_UNIDADMEDIDA = "Unidad Medida";
	private static final String ENTITY_SERVICIO = "Servicio";

	private SharedData sharedData;

	@PersistenceContext
	private EntityManager em;

	/** The Producto repository. */
	private ProductoRepository productoRepository;
	private ProductoVerRepository productoVerRepository;
	private TipoproductoRepository tipoproductoRepository;
	private ProductoCalendarioRepository productoCalendarioRepository;

	/** The producto mapper service. */
	private ProductoMapperService productoMapperService;
	private ProductoVerMapperService productoVerMapperService;

	private OfertaExternalRestService ofertaExternalRestService;
	private ServicioExternalRestService servicioExternalRestService;

	private InstalacionesExternalRestService instalacionesExternalRestService;
	private OperacionesAuxiliaresExternalRestService operacionesAuxiliaresExternalRestService;
	private SujetoExternalRestService sujetoExternalRestService;
	private AdminParamExternalService adminParamExternalService;

	private RestTemplate restTemplate;
	private List<ResponseConsultOffertsVO> responseConsultOffertsVOS = new ArrayList<>();

	/**
	 * Servicio que recuperar constantes, enumerados y otros parámetros de los
	 * ficheros .yml
	 */
	private ParametersPropertiesConfig paramProperties;

	public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapperService productoMapperService,
			OfertaExternalRestService ofertaExternalRestService, ParametersPropertiesConfig paramProperties,
			ServicioExternalRestService servicioExternalRestService, ProductoVerRepository productoVerRepository,
			ProductoVerMapperService productoVerMapperService, TipoproductoRepository tipoproductoRepository,
			ProductoCalendarioRepository productoCalendarioRepository,
			OperacionesAuxiliaresExternalRestService operacionesAuxiliaresExternalRestService,
			InstalacionesExternalRestService instalacionesExternalRestService, SharedData sharedData,
			SujetoExternalRestService sujetoExternalRestService, AdminParamExternalService adminParamExternalService,
			RestTemplate restTemplate) {
		this.productoRepository = productoRepository;
		this.productoMapperService = productoMapperService;
		this.ofertaExternalRestService = ofertaExternalRestService;
		this.paramProperties = paramProperties;
		this.servicioExternalRestService = servicioExternalRestService;
		this.productoVerRepository = productoVerRepository;
		this.productoCalendarioRepository = productoCalendarioRepository;
		this.productoVerMapperService = productoVerMapperService;
		this.tipoproductoRepository = tipoproductoRepository;
		this.operacionesAuxiliaresExternalRestService = operacionesAuxiliaresExternalRestService;
		this.instalacionesExternalRestService = instalacionesExternalRestService;
		this.sharedData = sharedData;
		this.sujetoExternalRestService = sujetoExternalRestService;
		this.adminParamExternalService = adminParamExternalService;
		this.restTemplate = restTemplate;
	}

	/**
	 * Gets all productos.
	 *
	 * @param pageable the pageable
	 * @return a list of ResponseProductoVO instances
	 * @throws NotContextException
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ResponseProductoVO> obtieneProductos(RequestFiltrosObtieneProductoVO filtros) {

		
		log.info("Método getProductos para la consulta de todos los elementos");
		
		setFecLimiteProgramada(filtros);
		List<Producto> productos = productoRepository.findProductos(em, filtros);

		if (productos.isEmpty()) {
			throw new NoSuchElementException(ENTITY_PRODUCTO);
		}

		List<ResponseProductoVO> productosVO = productoMapperService.convertToVO(productos);
		if (filtros.getCalcularPreciosyCap() != null && filtros.getCalcularPreciosyCap()) {
			obtenerPreciosYCapacidadesProd(filtros, productosVO);
		}
	
		
		log.info("La consulta ha devuelto {} registros", productos.size());
		

		// Carga los ids de los productos en una lista para obtener sus unidades de
		// medida
		List<Integer> idsUnidadesMedida = new ArrayList<>();
		for (Producto producto : productos) {
			idsUnidadesMedida.add(producto.getIdnUnidadMedida());
		}

		// Carga los ids de productos en una lista para obtener sus unidades de precio
		List<Integer> idsUnidadesPrecio = new ArrayList<>();
		for (Producto producto : productos) {
			idsUnidadesPrecio.add(producto.getIdnUnidadPrecio());
		}

		externalUnidadMedidaDeProducto(productosVO, idsUnidadesMedida, idsUnidadesPrecio);
		productosVO.forEach(f -> f.setIndEstadoProducto(filtros.getIndEstadoProducto()));
		return productosVO;
	}
	
	private void obtenerPreciosYCapacidadesProd(RequestFiltrosObtieneProductoVO filtros,
			List<ResponseProductoVO> productosVO) {
		
		List<ResponseServOfertadoVO> responseServOfertadoVO = this.operacionesAuxiliaresExternalRestService
				.getServiciosOfertados();
		Map<Integer, Boolean> mapServ = responseServOfertadoVO.stream()
				.collect(Collectors.toMap(ResponseServOfertadoVO::getIdnServAtr, ResponseServOfertadoVO::getIndSlot));
		
		RequestConsultOffertsVO requestConsultOffertsVO = new RequestConsultOffertsVO();
		requestConsultOffertsVO.setIdnListaProducto(productosVO.stream()
				.map(ResponseProductoVO::getIdnProducto).collect(Collectors.toList()));
		
		List<ResponseOfertaVO> responseConsultOffertsVO = this.ofertaExternalRestService.getConsultOnlyOffersProducts(requestConsultOffertsVO);
		
		Map<Integer, List<ResponseOfertaVO>> mapProductOffers = responseConsultOffertsVO.stream().collect(Collectors.groupingBy(ResponseOfertaVO::getIdnProducto));
		
		Integer idnEstadoEntidad;
		if (Objects.isNull(filtros.getIndEstadoProducto()) || !filtros.getIndEstadoProducto())
			idnEstadoEntidad = Integer.valueOf(paramProperties.get(EnumConstants.ENVIADA));
		else 
			idnEstadoEntidad = Integer.valueOf(paramProperties.get(EnumConstants.PROGRAMADA));
		
		productosVO.stream().forEach(p -> {
			if (Objects.isNull(mapProductOffers.get(p.getIdnProducto()))) {
				p.setPrecioMinVenta(null);
				p.setCapacidadMinVenta(null);
				p.setPrecioMaxCompra(null);
				p.setCapacidadMaxCompra(null);
			} 
			else {
				ResponseConsultarOfertasVO ofertas = new ResponseConsultarOfertasVO();
				ofertas.setCompra(new ArrayList<ResponseOfertaVO>());
				ofertas.setVenta(new ArrayList<ResponseOfertaVO>());

				ofertas.setCompra(mapProductOffers.get(p.getIdnProducto()).stream().filter(o -> o.getIdnEstadoEntidad() == idnEstadoEntidad 
					&& !o.isIndTipoOferta()).map(Function.identity()).toList());
				ofertas.setVenta(mapProductOffers.get(p.getIdnProducto()).stream().filter(o -> o.getIdnEstadoEntidad() == idnEstadoEntidad 
					&& o.isIndTipoOferta()).map(Function.identity()).toList());	

				
				if(!ofertas.getCompra().isEmpty()) {
					ResponseOfertaVO compraMaxima = ofertas.getCompra().stream().max(
							Comparator.comparing(ResponseOfertaVO::getValPrecio)).get();

					List<ResponseOfertaVO> listaComprasMaximas = new ArrayList<>();
					ofertas.getCompra().stream().forEach(c -> {
						if (c.getValPrecio().compareTo(compraMaxima.getValPrecio()) == 0)
							listaComprasMaximas.add(c);
					});

					ResponseOfertaVO compraMaximaFinal = listaComprasMaximas.stream()
							.max(Comparator.comparing(ResponseOfertaVO::getValCapacidad)).get();

					p.setPrecioMaxCompra(compraMaximaFinal.getValPrecio());
					if (!mapServ.get(p.getIdnServAtr()))
						p.setCapacidadMaxCompra(compraMaximaFinal.getValCapacidad());
					else if (mapServ.get(p.getIdnServAtr()))
						p.setCapacidadMaxCompra(null);
				}
				else {
					p.setPrecioMaxCompra(null);
					p.setCapacidadMaxCompra(null);
				}	
				
				if (!ofertas.getVenta().isEmpty()) {
					ResponseOfertaVO ventaMinima = ofertas.getVenta().stream()
							.min(Comparator.comparing(ResponseOfertaVO::getValPrecio)).get();

					List<ResponseOfertaVO> listaVentasMinimas = new ArrayList<>();
					ofertas.getVenta().stream().forEach(v -> {
						if (v.getValPrecio().compareTo(ventaMinima.getValPrecio()) == 0)
							listaVentasMinimas.add(v);
					});

					ResponseOfertaVO ventaMinimaFinal = listaVentasMinimas.stream()
							.min(Comparator.comparing(ResponseOfertaVO::getValCapacidad)).get();

					p.setPrecioMinVenta(ventaMinimaFinal.getValPrecio());
					if (!mapServ.get(p.getIdnServAtr()))
						p.setCapacidadMinVenta(ventaMinimaFinal.getValCapacidad());
					else if (mapServ.get(p.getIdnServAtr()))
						p.setCapacidadMinVenta(null);
				}
				else {
					p.setPrecioMinVenta(null);
					p.setCapacidadMinVenta(null);
				}
				log.info("La consulta ha devuelto {} registros");
			}
		});
	}

	private void setFecLimiteProgramada(RequestFiltrosObtieneProductoVO filtros) {
		if (filtros.getIndEstadoProducto() != null && filtros.getIndEstadoProducto()) {
			RequestAdminParamVO requestConsultaParametroTipoAdminVo = new RequestAdminParamVO();

			// Validación slot_expiracion
			List<String> listNomParametros = new ArrayList<>();
			listNomParametros.add(paramProperties.get(EnumConstants.PARAM_LIMITE_PROGRAMADA));
			requestConsultaParametroTipoAdminVo.setLstNomParametros(listNomParametros);
			Map<String, String> parametros = adminParamExternalService
					.consultarParametroTipoAdmin(requestConsultaParametroTipoAdminVo, restTemplate);
			// Se pasa a dias la constante expiracion
			if (parametros != null && !parametros.isEmpty()) {
				int diasParametrosExpiracion = Integer
						.parseInt(parametros.get(paramProperties.get(EnumConstants.PARAM_LIMITE_PROGRAMADA)));
				filtros.setFecLimiteProgramada(LocalDateTime.now().plusDays(diasParametrosExpiracion));
			}

		}
	}

	/**
	 * Consulta al microservicio de Sujeto y mapea los datos necesarios para obtener
	 * la unidad de medida
	 * 
	 * @param productosVO
	 * @param productosVO
	 */
	private void externalUnidadMedidaDeProducto(List<ResponseProductoVO> productosVO, List<Integer> idsUnidadesMedida,
			List<Integer> idsUnidadesPrecio) {
		List<ResponseUnidadMedidaVO> response = servicioExternalRestService
				.getUnidadMedida(idsUnidadesMedida.stream().distinct().collect(Collectors.toList()));
		List<ResponseUnidadPrecioVO> responseUnidadPrecio = operacionesAuxiliaresExternalRestService
				.getUnidadPrecio(idsUnidadesPrecio.stream().distinct().collect(Collectors.toList()));

		Map<Integer, String> map = response.stream().collect(Collectors
				.toMap(ResponseUnidadMedidaVO::getIdnUnidadMedida, ResponseUnidadMedidaVO::getCodUnidadMedida));

		Map<Integer, String> mapUnidadPrecio = responseUnidadPrecio.stream().collect(Collectors
				.toMap(ResponseUnidadPrecioVO::getIdnUnidadPrecio, ResponseUnidadPrecioVO::getCodUnidadPrecio));

		for (ResponseProductoVO productoVO : productosVO) {
			productoVO.setUnidadMedida(map.get(productoVO.getIdnUnidadMedida()));
			// TODO Cambiar cuando se defina la forma de obtener la unidad de precio
			productoVO.setUnidadPrecio(mapUnidadPrecio.get(productoVO.getIdnUnidadPrecio()));
		}
	}

	/**
	 * Gets a producto for given id.
	 *
	 * @param id the id of the producto
	 * @return a ResponseProductoVO instance
	 */
	@Override
	@Transactional(readOnly = true)
	public ResponseProductoVO getProducto(Integer id) {
		List<ResponseProductoVO> productosVO = new ArrayList<>();
		List<Integer> idsUnidadesMedida = new ArrayList<>();
		List<Integer> idsUnidadesPrecio = new ArrayList<>();

		
		log.info("Método getProducto para la consulta por id {}", id);
		

		Optional<Producto> producto = productoRepository.findById(id);
		if (!producto.isPresent()) {
			throw new NoSuchElementException(ENTITY_PRODUCTO);
		}

		ResponseProductoVO productoVO = productoMapperService.convertToVO(producto.get());
		
		
		log.info("La consulta ha devuelto los resultados: {}", productoVO);
		
		productosVO.add(productoVO);
		idsUnidadesMedida.add(productoVO.getIdnUnidadMedida());
		idsUnidadesPrecio.add(productoVO.getIdnUnidadPrecio());
		externalUnidadMedidaDeProducto(productosVO, idsUnidadesMedida, idsUnidadesPrecio);
		return productosVO.get(0);
	}

	/**
	 * Adds a producto on database.
	 *
	 * @param RequestProductoVO the RequestProductoVO
	 * @return a ResponseProductoVO instance
	 */
	@Override
	@Transactional
	public ResponseProductoVO saveProducto(RequestProductoVO requestProductoVO) {
		
		log.info("Método saveProducto para guardar los parámetros [{}]", requestProductoVO);
		
		Producto producto = productoMapperService.convertToEntity(requestProductoVO);
		producto = productoRepository.saveAndFlush(producto);
		
		log.info("Se ha creado el registro: {}", producto);
		
		return productoMapperService.convertToVO(producto);
	}

	/**
	 * Updates a producto on database.
	 *
	 * @param id                the id of the producto
	 * @param RequestProductoVO the RequestProductoVO
	 */
	@Override
	@Transactional
	public void updateProducto(Integer id, RequestProductoVO requestProductoVO) {
		
		log.info("Método updateProducto para guardar los parámetros [{},{}]", id, requestProductoVO);
		
		if (!productoRepository.findById(id).isPresent()) {
			throw new NoSuchElementException(ENTITY_PRODUCTO);
		}
		Producto producto = productoMapperService.convertToEntity(requestProductoVO);
		producto.setIdnProducto(id);
		productoRepository.saveAndFlush(producto);
		
		log.info("Se ha modificado el registro: {}", producto);
		
	}

	/**
	 * Deletes a producto on database.
	 *
	 * @param id the id of the producto
	 */
	@Override
	@Transactional
	public void deleteProducto(Integer id) {
		
		log.info("Método deleteProducto para eliminar el registro [{}]", id);
		
		if (!productoRepository.findById(id).isPresent()) {
			throw new NoSuchElementException(ENTITY_PRODUCTO);
		}
		productoRepository.deleteById(id);
		
		log.info("Se ha borrado el registro con id: {}", id);
		
	}

	@Override
	/**
	 * 
	 * Obtiene un listado de productos cuyo período de vigencia se solapa con el
	 * rango de fechas de alguno de los filtros suministrados. Cada filtro puede
	 * indicar adicionalmente el servicio sobre el que se centra la búsqueda; en
	 * caso contrario aplica a cualquiera.
	 * 
	 * @param requestFiltrarPorPeriodoVOs lista de filtros con un rango de fechas y,
	 *                                    opcionalmente, un servicio asociado
	 * @return lista de productos afectados
	 */

	@Transactional(readOnly = true)
	public List<ResponseProductoVO> filtrarPorPeriodo(List<RequestFiltrarPorPeriodoVO> requestFiltrarPorPeriodoVOs) {

		
		log.info("Método filtrarPorPeriodo para buscar productos afectados por los servicios [{}]",
					requestFiltrarPorPeriodoVOs);
		

		log.info("filtros antes: {}", requestFiltrarPorPeriodoVOs);

		// Conversión a Días de Gas de las fechas de los filtros
		for (RequestFiltrarPorPeriodoVO filtro : requestFiltrarPorPeriodoVOs) {
			// Fecha de Inicio Día Gas, ajustando a 0 horas que es como se guarda en los
			// Productos
			LocalDateTime fechaInicioDiaGas = operacionesAuxiliaresExternalRestService
					.getDiaGas(filtro.getFecIniVigencia()).get(0);
			fechaInicioDiaGas = fechaInicioDiaGas.withHour(0).truncatedTo(ChronoUnit.HOURS);
			filtro.setFecIniVigencia(fechaInicioDiaGas);
			// Fecha de Fin Día Gas, ajustando a 0 horas que es como se guarda en los
			// Productos
			if (Objects.nonNull(filtro.getFecFinVigencia())) {
				LocalDateTime fechaFinDiaGas = operacionesAuxiliaresExternalRestService
						.getDiaGas(filtro.getFecFinVigencia()).get(1);
				fechaFinDiaGas = fechaFinDiaGas.withHour(0).truncatedTo(ChronoUnit.HOURS);
				filtro.setFecFinVigencia(fechaFinDiaGas);
			}
		}

		log.info("filtros despues: {}", requestFiltrarPorPeriodoVOs);

		List<ResponseProductoVO> affectedProducts = new ArrayList<>();
		if (!CollectionUtils.isEmpty(requestFiltrarPorPeriodoVOs)) {
			affectedProducts.addAll(requestFiltrarPorPeriodoVOs.stream().map(productoMapperService::convertToVO)
					.map(servicioFilter -> productoRepository.findAffectedByService(em, servicioFilter))
					.flatMap(List::stream).distinct().map(productoMapperService::convertToVO).toList());
		}

		
		log.info("Se han obtenido los productos: {}", affectedProducts);
		

		return affectedProducts;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ResponseProductoVO> getProductos(List<Integer> ids) {
		
		log.info("Método getProducto para la consulta por id {}", ids);
		

		List<Producto> producto = productoRepository.findByIdnProductoInAndAudFecBajaIsNull(ids);
		if (producto.isEmpty()) {
			throw new NoSuchElementException(ENTITY_PRODUCTO);
		}

		
		log.info("La consulta ha devuelto los resultados: {}", producto);
		

		return productoMapperService.convertToVO(producto);
	}

	/**
	 * Servicio para actualizar los precios y capacidad del producto
	 * 
	 * @author mdesantb
	 * @param productoVo then RequestProductoVO
	 * @return ResponseProductoVO
	 */
	@Transactional
	public ResponseProductoVO updateProducto(RequestProductoVO productoVo) {
		log.info("Inicio del método updateProducto :: parámetros [{}] ", productoVo);
		ResponseProductoVO salida = new ResponseProductoVO();
		LocalDateTime fechaHoy = LocalDateTime.now();
		Producto actualizarProducto = productoRepository.findById(productoVo.getIdnProducto()).get();
		ProductoVer productoVer = productoVerRepository.findUltimaById(productoVo.getIdnProducto(), em);
		actualizarProducto.setMaxCapacidadCompra(productoVo.getCapacidadMaxCompra());
		actualizarProducto.setMinCapacidadVenta(productoVo.getCapacidadMinVenta());
		actualizarProducto.setMaxPrecioCompra(productoVo.getPrecioMaxCompra());
		actualizarProducto.setMinPrecioVenta(productoVo.getPrecioMinVenta());
		actualizarProducto.setFecFinProducto(productoVo.getFecFinProducto());
		actualizarProducto.setFecFinSesion(productoVo.getFecFinSesion());
		actualizarProducto.setFecIniProducto(productoVo.getFecIniProducto());
		actualizarProducto.setFecIniSesion(productoVo.getFecIniSesion());
		actualizarProducto.setNumVersion(productoVer.getNumVersion() + 1);
		actualizarProducto.setFecVersion(fechaHoy);
		actualizarProducto.setIdnProducto(productoVo.getIdnProducto());
		actualizarProducto.setAudUsuModi((String) this.sharedData.get(EHeader.HEADER_USR_NAME.value));
		actualizarProducto.setAudFecModi(productoVo.getAudFecModi());
		actualizarProducto.setAudFecBaja(productoVo.getAudFecBaja());

		productoRepository.updateProducto(actualizarProducto, em);

		ProductoVer actualizarProductoVer = productoVerMapperService.convertToEntity(productoVer, productoVo);
		actualizarProductoVer.setFecVersion(fechaHoy);
		// Se tiene que poner el usuario que realiza la inserción
		actualizarProductoVer.setAudFecAlta(fechaHoy);
		actualizarProductoVer.setAudUsuAlta((String) this.sharedData.get(EHeader.HEADER_USR_NAME.value));
		actualizarProductoVer.setAudFecModi(productoVo.getAudFecModi());
		actualizarProductoVer.setAudUsuModi(productoVo.getAudUsuModi());
		actualizarProductoVer.setAudFecBaja(productoVo.getAudFecBaja());

		productoVerRepository.saveAndFlush(actualizarProductoVer);
		salida = productoMapperService.convertToVO(productoRepository.findById(productoVo.getIdnProducto()).get());
		log.info("Fin del método updateProducto :: salida [{}] ", salida);
		return salida;
	}

	/**
	 * Alta de productos, accede a los procedimientos de validaciones, calculo de
	 * intervalos de tiempo y recuperacion de informacion desde otros
	 * microservicios. Metodo principal que verifica y guarda los productos.
	 * 
	 * @param requestFiltrosProductoVO modelo de entrada para filtros de busqueda
	 * @return ResponseProductoVO lista de objetos de respuesta a la busqueda.
	 */

	@Override
	@Transactional
	public List<ResponseProductoVO> addProduct(RequestProductVO requestProductVO) {
		String tipoProducto;
		Boolean insertaProductos = false;
		Boolean validaciones = false;
		List<Producto> nuevosProductos = new ArrayList<>();
		requestProductVO.setFecIniProducto(requestProductVO.getFecIniProducto().toLocalDate().atStartOfDay());
		requestProductVO.setFecFinProducto(requestProductVO.getFecFinProducto().toLocalDate().atStartOfDay());

		
		log.info("Inicio del método addProduct :: parámetros [{}] ", requestProductVO);
		

		// this.validateAcessFilter();

		// EXTRAE DEL REPOSITORIO LA ABREVIACION DEL TIPO DE PRODUCTO (HORIZONTE
		// TEMPORAL)
		Optional<TipProducto> tipProducto = this.tipoproductoRepository.findById(requestProductVO.getIdnTipoProducto());
		if (!tipProducto.isPresent())
			throw new NoSuchElementException(ENTITY_TIPPRODUCTO);
		else
			tipoProducto = tipProducto.orElse(new TipProducto()).getTxtAbrvProducto();

		Boolean isSlot = this.isServiceSlot(List.of(requestProductVO.getIdnServAtr()));

		if (tipoProducto.equals("D")) {
			if (validateDateDaily(requestProductVO))
				validaciones = true;
		} else {

			if (((requestProductVO.getIdnAgrupInfr() == null || requestProductVO.getIdnAgrupInfr().isEmpty())
					&& (requestProductVO.getIdnInfr() == null || requestProductVO.getIdnInfr().isEmpty())
					&& (requestProductVO.getIdnPuntoConex() == null || requestProductVO.getIdnPuntoConex().isEmpty()))
					&& !isSlot) {
				throw new BusinessException(AppErrorCode.BUSI_011,
						String.format(AppErrorCode.BUSI_011.getReasonPhrase()));
			}

			if (validateProductDate(requestProductVO) && validateSessionDate(requestProductVO, isSlot))
				validaciones = true;
		}

		if (validaciones) {
			nuevosProductos = generaListaProductos(requestProductVO, tipoProducto, isSlot);
			insertaProductos = true;
		}

		if (insertaProductos) {
			List<ProductoVer> listaProductoVer = new ArrayList<>();
			List<String> codigosNuevos = new ArrayList<>();
			for (Producto nuevoProducto : nuevosProductos) {
				List<Producto> productos = this.productoRepository.findProducto(em, nuevoProducto);
				if (productos.isEmpty()) {
					Producto producto = this.productoRepository.saveAndFlush(nuevoProducto);
					ProductoVer ProductoVer = seteaProductoVer(producto);
					listaProductoVer.add(ProductoVer);
				} else {
					if (nuevosProductos.size() == 1)
						throw new BusinessException(AppErrorCode.BUSI_005,
								String.format(AppErrorCode.BUSI_005.getReasonPhrase()));
					else {
						codigosNuevos.add(nuevoProducto.getCodProducto());
					}
				}
			}
			if (!codigosNuevos.isEmpty()) {
				String mensaje = codigosNuevos.stream().collect(Collectors.joining(", "));
				throw new BusinessException(AppErrorCode.BUSI_015,
						String.format(AppErrorCode.BUSI_015.getReasonPhrase() + mensaje + "."));
			}
			this.productoVerRepository.saveAll(listaProductoVer);
		}
		List<ResponseProductoVO> responseProductoVOs = productoMapperService.convertToVO(nuevosProductos);
		
		log.info("Fin del método addProduct :: salida [{}] ", responseProductoVOs);
		
		return responseProductoVOs;
	}

	/**
	 * IMPLEMENTACION QUE GENERA LA LISTA DE PRODUCTOS E INVOCA MICROSERVICIOS
	 * Metodo que genera lista de productos, invocando a otros microservicios,
	 * calculando los periodos de fechas de inicio y fin de producto y generando la
	 * nomenclatura de productos.
	 */

	private List<Producto> generaListaProductos(RequestProductVO requestProductVO, String tipoProducto,
			Boolean isSlot) {

		// METODO QUE INVOCA AL MICROSERVICIO OPERACIONES AUXILIARES, EXTRAE UNIDAD
		// PRECIO
		Integer unidadPrecio = this.getUnitPrice(requestProductVO.getIdnServAtr());

		// METODO QUE INVOCA AL MICROSERVICIO SERVICIO, EXTRAE UNIDAD MEDIDA
		Integer unidadMedida = this.getUnitMeasure(requestProductVO.getIdnServAtr());

		// METODO QUE INVOCA AL MICROSERVICIO INSTALACIONES, EXTRAE ATRIBUTOS VIGENTES
		List<ResponseConsultarAtribVigVO> responseConsultarAtribVigVOS = this
				.preparaRequestInstalaciones(requestProductVO);

		if ((requestProductVO.getIdnAgrupInfr() == null || requestProductVO.getIdnAgrupInfr().isEmpty())
				&& (requestProductVO.getIdnInfr() == null || requestProductVO.getIdnInfr().isEmpty())
				&& (requestProductVO.getIdnPuntoConex() == null || requestProductVO.getIdnPuntoConex().isEmpty())) {
			ResponseConsultarAtribVigVO responseConsultarAtribVigVO = new ResponseConsultarAtribVigVO();
			responseConsultarAtribVigVO.setIdAgrupInfr(null);
			AtributoVigenteVO atributoVigenteVO = new AtributoVigenteVO();
			atributoVigenteVO.setValorAtributo("");
			responseConsultarAtribVigVO.setListaAtributos(List.of(atributoVigenteVO));
			responseConsultarAtribVigVOS.add(responseConsultarAtribVigVO);
		}

		// METODO QUE INVOCA AL MICROSERVICIO SERVICIO, EXTRAE EL CODIGO DE SERVICIO ATR
		ResponseServAtrAtribVigVO responseServAtrAtribVigVO = this.consultaServAtrAtribVig(requestProductVO);

		List<Producto> productoS = new ArrayList<>();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd");

		// METODO QUE CALCULA EL PERIODO ENVIANDO EL REQUEST Y TIPO DE PRODUCTO
		Map<String, Integer> periodo = calculaPeriodo(requestProductVO, tipoProducto);

		/**
		 * SE DEFINE LA VARIABLE PARA DETERMINAR LA CANTIDAD DE PRODUCTOS SEGUN NUMERO
		 * DE INSTALACIONES Y PERIODO
		 */
		Integer numProductos = 0;
		String usuarioAlta = (String) this.sharedData.get(EHeader.HEADER_USR_NAME.value);
		// bucle cantidad de instalaciones recuperadas
		for (ResponseConsultarAtribVigVO responseConsultarAtribVigVO : responseConsultarAtribVigVOS) {
			AtributoVigenteVO atributoVigenteVO = responseConsultarAtribVigVO.getListaAtributos().get(0);
			String codInstalacion = atributoVigenteVO.getValorAtributo() + "_";

			if (atributoVigenteVO.getValorAtributo().equals("")) {
				codInstalacion = "";
			}

			Integer idnAgrupInfr = atributoVigenteVO.getIdAgrupInfr();
			Integer idnInfr = atributoVigenteVO.getIdInfr();
			Integer idnPuntoConex = atributoVigenteVO.getIdPuntoConex();

			for (int i = 0; i < periodo.get("periodo"); i = i + periodo.get("serie")) {

				Producto producto = new Producto();
				producto.setAudUsuAlta(usuarioAlta);
				producto.setNumVersion(1);
				producto.setFecVersion(LocalDateTime.now());
				producto.setIdnTipoProducto(requestProductVO.getIdnTipoProducto());
				producto.setIdnAgrupInfr(idnAgrupInfr);
				producto.setIdnInfr(idnInfr);
				producto.setIdnPuntoConex(idnPuntoConex);
				producto.setIdnServAtr(requestProductVO.getIdnServAtr());
				producto.setIdnUnidadMedida(unidadMedida);
				producto.setIdnUnidadPrecio(unidadPrecio);
				producto.setFecIniSesion(requestProductVO.getFecIniSesion());

				LocalDateTime fechaProducto = null;
				if (tipoProducto.equals("D")) {
					fechaProducto = requestProductVO.getFecIniProducto().plusDays(i);
					producto.setFecIniProducto(fechaProducto);
					producto.setFecFinProducto(fechaProducto);
				} else {
					fechaProducto = requestProductVO.getFecIniProducto().plusMonths(i);
					producto.setFecIniProducto(fechaProducto);
					producto.setFecFinProducto(fechaProducto.plusMonths(periodo.get("serie")));
					producto.setFecFinProducto(producto.getFecFinProducto().minusDays(1));
				}

				if (periodo.get("periodo") == 1) {
					producto.setFecFinSesion(requestProductVO.getFecFinSesion());
				} else {
					if (isSlot) {
						producto.setFecFinSesion(producto.getFecFinProducto());
						producto.setFecFinSesion(
								producto.getFecFinSesion().plusHours(requestProductVO.getFecFinSesion().getHour()));
						producto.setFecFinSesion(
								producto.getFecFinSesion().plusMinutes(requestProductVO.getFecFinSesion().getMinute()));
					} else {
						if (i == 0) {
							producto.setFecFinSesion(requestProductVO.getFecFinSesion());
						} else {
							producto.setFecFinSesion(producto.getFecIniProducto().minusDays(1));
							producto.setFecFinSesion(
									producto.getFecFinSesion().plusHours(requestProductVO.getFecFinSesion().getHour()));
							producto.setFecFinSesion(producto.getFecFinSesion()
									.plusMinutes(requestProductVO.getFecFinSesion().getMinute()));
						}
					}
				}

				producto.setCodProducto(fechaProducto.format(formato) + "_" + tipoProducto + "_" + codInstalacion
						+ responseServAtrAtribVigVO.getValor());
				productoS.add(producto);
				numProductos++;
			}
		}

		ResponseCodigoEntidadVO responseCodigoEntidadVO = consultaMicroOpAuxiliares(numProductos);

		/** SE ASIGNA LA SECUENCIA A LA NOMENCLATURA PARA CADA PRODUCTO */
		int secuencia = responseCodigoEntidadVO.getIdnInicio();
		for (Producto producto : productoS) {
			producto.setCodProducto(producto.getCodProducto() + "_" + String.format("%05d", secuencia));
			secuencia++;
		}

		return productoS;
	}

	/**
	 * FUNCION QUE PERMITE CALCULAR EL PERIODO ENTRE DOS FECHAS Y EL TIPO DE
	 * PRODUCTO, recibido desde el frontal el calculo se realiza segun el tipo de
	 * producto (diario o diferente de diario) Para el caso de diario se realiza la
	 * diferencia de dias. Para el caso diferente a diario el calculo se realiza
	 * desde la diferencia de meses (mensual, trimestral y anual) Devuelve el
	 * periodo (numero de dias o meses segun la deiferencia) Devuelve serie (el i
	 * ntervalo o saltos entre dias o meses segun el caso)
	 */
	private Map<String, Integer> calculaPeriodo(RequestProductVO requestProductVO, String tipoProducto) {
		Map<String, Integer> periodo = new HashMap<>();
		if (tipoProducto.equals("D")) {
			long period = ChronoUnit.DAYS.between(requestProductVO.getFecIniProducto().toLocalDate(),
					requestProductVO.getFecFinProducto().toLocalDate());
			periodo.put("periodo", (int) period + 1);
			periodo.put("serie", 1);
		} else {
			if (tipoProducto.equals("M"))
				periodo.put("serie", 1);
			else if (tipoProducto.equals("T"))
				periodo.put("serie", 3);
			else if (tipoProducto.equals("Y"))
				periodo.put("serie", 12);

			long period = ChronoUnit.MONTHS.between(requestProductVO.getFecIniProducto().toLocalDate(),
					requestProductVO.getFecFinProducto().toLocalDate());
			periodo.put("periodo", (int) period + 1);

		}
		return periodo;
	}

	private List<ResponseConsultarAtribVigVO> preparaRequestInstalaciones(RequestProductVO requestProductVO) {

		List<ResponseConsultarAtribVigVO> responseConsultarAtribVigVOS = new ArrayList<>();

		RequestConsultarAtribVigVO requestConsultarAtribVigVO = new RequestConsultarAtribVigVO();
		requestConsultarAtribVigVO.setFechaInicio(LocalDateTime.now());
		requestConsultarAtribVigVO.setFechaFin(LocalDateTime.now());
		List<String> parametro = new ArrayList<>();

		if (requestProductVO.getIdnAgrupInfr() != null) {
			requestConsultarAtribVigVO.setListaIdAgrupIfr(requestProductVO.getIdnAgrupInfr());
			requestConsultarAtribVigVO.setListaIdInfr(null);
			requestConsultarAtribVigVO.setListaIdPuntoConex(null);
			parametro.add(EnumConstants.AGRUP_INFR_ATRIB_VIG);
			requestConsultarAtribVigVO.setListaParmAtributo(parametro);
			responseConsultarAtribVigVOS
					.addAll(this.instalacionesExternalRestService.getInstalacion(requestConsultarAtribVigVO));
		}
		if (requestProductVO.getIdnInfr() != null) {
			requestConsultarAtribVigVO.setListaIdAgrupIfr(null);
			requestConsultarAtribVigVO.setListaIdInfr(requestProductVO.getIdnInfr());
			requestConsultarAtribVigVO.setListaIdPuntoConex(null);
			parametro.add(EnumConstants.INFR_ATRIB_VIG);
			requestConsultarAtribVigVO.setListaParmAtributo(parametro);
			responseConsultarAtribVigVOS
					.addAll(this.instalacionesExternalRestService.getInstalacion(requestConsultarAtribVigVO));
		}
		if (requestProductVO.getIdnPuntoConex() != null) {
			requestConsultarAtribVigVO.setListaIdAgrupIfr(null);
			requestConsultarAtribVigVO.setListaIdInfr(null);
			requestConsultarAtribVigVO.setListaIdPuntoConex(requestProductVO.getIdnPuntoConex());
			parametro.add(EnumConstants.PUNTO_CONEX_ATRIB_VIG);
			requestConsultarAtribVigVO.setListaParmAtributo(parametro);
			responseConsultarAtribVigVOS
					.addAll(this.instalacionesExternalRestService.getInstalacion(requestConsultarAtribVigVO));
		}
		return responseConsultarAtribVigVOS;
	}

	/**
	 * SERVICIO QUE INVOCA AL MICROSERVICIO SERVICIO (SERVICIO ATR), RECUPERA UNIDAD
	 * DE MEDIDA
	 */
	private Integer getUnitMeasure(Integer idnServAtr) {
		ResponseServicioVO responseServicioVO = this.servicioExternalRestService.getServicioAtr(idnServAtr);
		if (responseServicioVO.getUniServicio() == null)
			throw new NoSuchElementException(ENTITY_UNIDADMEDIDA);
		return responseServicioVO.getUniServicio();
	}

	/**
	 * SERVICIO QUE INVOCA AL MICROSERVICIO OPERACIONES AUXILIARES, RECUPERA
	 * IDENTIFICADOR DE UNIDAD DE PRECIO
	 */
	private Integer getUnitPrice(int idnServAtr) {
		DateTimeFormatter formatoRest = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String fecVigencia = LocalDateTime.now().format(formatoRest);
		ResponseUnidadPrecioVO responseUnidadPrecioVOS = this.operacionesAuxiliaresExternalRestService
				.getUnidadPrecio(idnServAtr, fecVigencia);
		if (responseUnidadPrecioVOS.getIdnUnidadPrecio() == null)
			throw new NoSuchElementException(ENTITY_UNIDADPRECIO);
		return responseUnidadPrecioVOS.getIdnUnidadPrecio();
	}

	/**
	 * SERVICIO, QUE INVOCA AL MICROSERVICIO OPERACIONES AUXILIARES, RECUPERA
	 * SECUENCIA
	 */
	private ResponseCodigoEntidadVO consultaMicroOpAuxiliares(Integer numProductos) {
		RequestCodigoEntidadVO requestCodigoEntidadVO = new RequestCodigoEntidadVO();
		requestCodigoEntidadVO.setCodEntidad("producto");
		requestCodigoEntidadVO.setNumEntidades(numProductos);
		return this.operacionesAuxiliaresExternalRestService.getCodigoEntidad(requestCodigoEntidadVO);
	}

	/**
	 * SERVICIO QUE INVOCA AL MICROSERVICIO SERVICIO, RECUPERA VALOR DEL ATRIBUTO
	 * (CODIGO IDENTIFICATIVO DEL SERVICIO ATR)
	 */

	private ResponseServAtrAtribVigVO consultaServAtrAtribVig(RequestProductVO requestProductVO) {
		Optional<ResponseServAtrAtribVigVO> responseServAtrAtribVigVO = Optional
				.of(servicioExternalRestService.getSerAtribVig(requestProductVO.getIdnServAtr()));
		if (!responseServAtrAtribVigVO.isPresent()) {
			throw new NoSuchElementException(ENTITY_SERVICIO);
		}
		return responseServAtrAtribVigVO.get();
	}

	/**
	 * MÉTODO QUE INVOCA AL MICROSERVICIO OP AUXILIARES, RECUPERA EL INDICADOR DE
	 * BUQUE O CISTERNA TRUE / FALSE
	 */
	public Boolean isServiceSlot(List<Integer> idnServAtr) {
		return operacionesAuxiliaresExternalRestService.isServiceSlot(idnServAtr);
	}

	/**
	 * IMPLEMENTACIÓN INICIAL PARA EL TIPO DE HORIZONTE - DIARIO
	 */
	private Boolean validateDateDaily(RequestProductVO requestProductVO) {
		Boolean estado = false;
		if (requestProductVO.getFecIniProducto().isAfter(LocalDateTime.now().toLocalDate().atStartOfDay()))
			if (requestProductVO.getFecFinProducto().equals(requestProductVO.getFecIniProducto())
					|| requestProductVO.getFecFinProducto().isAfter(requestProductVO.getFecIniProducto()))
				estado = true;
			else
				throw new BusinessException(AppErrorCode.BUSI_002,
						String.format(AppErrorCode.BUSI_002.getReasonPhrase()));
		else
			throw new BusinessException(AppErrorCode.BUSI_001, String.format(AppErrorCode.BUSI_001.getReasonPhrase()));
		return estado;
	}

	/**
	 * IMPLEMENTACIONES PARA EL TIPO DE HORIZONTE DIFERENTE A DIARIO
	 * 
	 * IMPLEMENTACIÓN QUE PERMITE VALIDAR LA FECHA DE PRODUCTO, segun
	 * espeificaciones de DT
	 */
	private Boolean validateProductDate(RequestProductVO requestProductVO) {
		Boolean estado = false;

		Integer verificaIniProducto = this.productoCalendarioRepository
				.validateProductDate(em, requestProductVO.getFecIniProducto(), null,
						requestProductVO.getIdnTipoProducto(), requestProductVO.getIdnServAtr())
				.size();

		if (requestProductVO.getFecIniProducto().isAfter(LocalDateTime.now().toLocalDate().atStartOfDay())
				&& verificaIniProducto != 0) {

			Integer verificaFinProducto = this.productoCalendarioRepository
					.validateProductDate(em, null, requestProductVO.getFecFinProducto(),
							requestProductVO.getIdnTipoProducto(), requestProductVO.getIdnServAtr())
					.size();

			if ((requestProductVO.getFecFinProducto().isAfter(LocalDateTime.now().toLocalDate().atStartOfDay())
					&& verificaFinProducto != 0)
					&& requestProductVO.getFecFinProducto().isAfter(requestProductVO.getFecIniProducto())
					&& (requestProductVO.getFecFinProducto().getYear() >= requestProductVO.getFecIniProducto()
							.getYear())) {

				Integer verificaIniFinProducto = this.productoCalendarioRepository.validateProductDate(em,
						requestProductVO.getFecIniProducto(), requestProductVO.getFecFinProducto(),
						requestProductVO.getIdnTipoProducto(), requestProductVO.getIdnServAtr()).size();

				if ((verificaIniFinProducto != 0 && requestProductVO.getIdnTipoProducto() == 1)
						|| requestProductVO.getIdnTipoProducto() != 1) {
					estado = true;
				} else {
					throw new BusinessException(AppErrorCode.BUSI_002,
							String.format(AppErrorCode.BUSI_002.getReasonPhrase()));
				}
			} else
				throw new BusinessException(AppErrorCode.BUSI_002,
						String.format(AppErrorCode.BUSI_002.getReasonPhrase()));
		} else
			throw new BusinessException(AppErrorCode.BUSI_001, String.format(AppErrorCode.BUSI_001.getReasonPhrase()));
		return estado;
	}

	/**
	 * IMPLEMENTACION QUE PERMITE VALIDAR LA FECHA DE SESION segun especificaciones
	 * de DT
	 */
	public Boolean validateSessionDate(RequestProductVO requestProductVO, Boolean isSlot) {
		Boolean estado = false;
		if (requestProductVO.getFecIniSesion().isAfter(LocalDateTime.now())
				&& requestProductVO.getFecIniSesion().isBefore(requestProductVO.getFecIniProducto()))
			if (!isSlot) {
				if ((requestProductVO.getFecFinSesion().isAfter(requestProductVO.getFecIniSesion())
						|| requestProductVO.getFecFinSesion().equals(requestProductVO.getFecIniSesion()))
						&& requestProductVO.getFecFinSesion().isBefore(requestProductVO.getFecIniProducto()))
					estado = true;
				else
					throw new BusinessException(AppErrorCode.BUSI_004,
							String.format(AppErrorCode.BUSI_004.getReasonPhrase()));
			} else {
				if ((requestProductVO.getFecFinSesion().isAfter(requestProductVO.getFecIniSesion())
						|| requestProductVO.getFecFinSesion().equals(requestProductVO.getFecIniSesion()))
						&& (requestProductVO.getFecFinSesion().toLocalDate().atStartOfDay()
								.isBefore(requestProductVO.getFecFinProducto())
								|| requestProductVO.getFecFinSesion().toLocalDate().atStartOfDay()
										.equals(requestProductVO.getFecFinProducto())))
					estado = true;
				else
					throw new BusinessException(AppErrorCode.BUSI_014,
							String.format(AppErrorCode.BUSI_014.getReasonPhrase()));
			}
		else
			throw new BusinessException(AppErrorCode.BUSI_003, String.format(AppErrorCode.BUSI_003.getReasonPhrase()));

		return estado;
	}

	private ProductoVer seteaProductoVer(Producto producto) {
		ProductoVer productoVer = new ProductoVer();
		productoVer.setIdnProducto(producto);
		productoVer.setCodProducto(producto.getCodProducto());
		productoVer.setIdnTipoProducto(producto.getIdnTipoProducto());
		productoVer.setIdnServAtr(producto.getIdnServAtr());
		productoVer.setIdnAgrupInfr(producto.getIdnAgrupInfr());
		productoVer.setIdnInfr(producto.getIdnInfr());
		productoVer.setIdnPuntoConex(producto.getIdnPuntoConex());
		productoVer.setFecIniProducto(producto.getFecIniProducto());
		productoVer.setFecFinProducto(producto.getFecFinProducto());
		productoVer.setFecIniSesion(producto.getFecIniSesion());
		productoVer.setFecFinSesion(producto.getFecFinSesion());
		productoVer.setFecVersion(producto.getFecVersion());
		productoVer.setAudFecAlta(producto.getAudFecAlta());
		productoVer.setNumVersion(producto.getNumVersion());
		productoVer.setAudUsuAlta(producto.getAudUsuAlta());
		return productoVer;
	}

	/**
	 * --------------------------------------------------- IMPLEMENTACION PARA
	 * MODIFICAR UN PRODUCTO segun espeificaciones de DT
	 * 
	 * @param requestProductVO modelo de edicion de producto
	 * @return ResponseProductoVO objeto de respuesta a la edicion.
	 */
	@Override
	@Transactional
	public ResponseProductoVO editProduct(RequestProductVO requestProductVO) {
		ResponseProductoVO responseProductoVO = new ResponseProductoVO();
		
		log.info("Inicio del método editProduct :: parámetros [{}] ", requestProductVO);
		

		// this.validateAcessFilter();

		Boolean isSlot = this.isServiceSlot(List.of(requestProductVO.getIdnServAtr()));

		if (((requestProductVO.getIdnAgrupInfr() == null || requestProductVO.getIdnAgrupInfr().isEmpty())
				&& (requestProductVO.getIdnInfr() == null || requestProductVO.getIdnInfr().isEmpty())
				&& (requestProductVO.getIdnPuntoConex() == null || requestProductVO.getIdnPuntoConex().isEmpty()))
				&& !isSlot) {
			throw new BusinessException(AppErrorCode.BUSI_011, String.format(AppErrorCode.BUSI_011.getReasonPhrase()));
		}

		requestProductVO.setFecIniProducto(requestProductVO.getFecIniProducto().toLocalDate().atStartOfDay());
		requestProductVO.setFecFinProducto(requestProductVO.getFecFinProducto().toLocalDate().atStartOfDay());

		Producto producto = this.productoRepository.findById(requestProductVO.getIdnProducto()).get();
		if (producto.getFecFinSesion().isBefore(LocalDateTime.now())
				|| producto.getFecFinSesion().equals(LocalDateTime.now())) {
			throw new BusinessException(AppErrorCode.BUSI_006, String.format(AppErrorCode.BUSI_006.getReasonPhrase()));
		}

		if (requestProductVO.getFecIniSesion().isBefore(requestProductVO.getFecIniProducto())
				&& (requestProductVO.getFecIniSesion().isBefore(requestProductVO.getFecFinSesion())
						|| requestProductVO.getFecIniSesion().equals(requestProductVO.getFecFinSesion()))
				&& (requestProductVO.getFecFinSesion().equals(requestProductVO.getFecIniSesion())
						|| requestProductVO.getFecFinSesion().isAfter(requestProductVO.getFecIniSesion()))) {

			if (requestProductVO.getFecIniSesion().equals(LocalDateTime.now())
					|| requestProductVO.getFecIniSesion().isAfter(LocalDateTime.now())) {
				if (validProductLinkOffer(requestProductVO.getIdnProducto())) {

					RequestProductoVO requestProductoVO = seteaProducto("update", requestProductVO);
					responseProductoVO = this.updateProducto(requestProductoVO);
				} else {
					throw new BusinessException(AppErrorCode.BUSI_007,
							String.format(AppErrorCode.BUSI_007.getReasonPhrase()));
				}
			} else {
				throw new BusinessException(AppErrorCode.BUSI_003, String.format(AppErrorCode.BUSI_003.getReasonPhrase()));
			}

		} else {
			throw new BusinessException(AppErrorCode.BUSI_004, String.format(AppErrorCode.BUSI_004.getReasonPhrase()));
		}
		
		log.info("Fin del método editProduct :: salida [{}] ", responseProductoVO);
		

		return responseProductoVO;
	}

	/**
	 * Validar producto vinculada a la oferta, operaciones que nos permite validar
	 * si un producto tiene vinculo a la oferta con estado "enviada" o "casada"
	 * 
	 */
	@Override
	public Boolean validProductLinkOffer(Integer idProducto) {
		Boolean estado = true;
		RequestConsultOffertsVO requestConsultOffertsVO = new RequestConsultOffertsVO();
		requestConsultOffertsVO.setIdnListaProducto(List.of(idProducto));
		responseConsultOffertsVOS = this.ofertaExternalRestService.getConsultOnlyOffers(requestConsultOffertsVO);
		for (ResponseConsultOffertsVO responseConsultOffertsVO : responseConsultOffertsVOS) {
			if (responseConsultOffertsVO.getIdnEstadoEntidad() == Integer
					.parseInt(paramProperties.get(EnumConstants.PROVISIONAL))
					|| responseConsultOffertsVO.getIdnEstadoEntidad() == Integer
							.parseInt(paramProperties.get(EnumConstants.ENVIADA))
					|| responseConsultOffertsVO.getIdnEstadoEntidad() == Integer
							.parseInt(paramProperties.get(EnumConstants.COMPATIBLE))
					|| responseConsultOffertsVO.getIdnEstadoEntidad() == Integer
							.parseInt(paramProperties.get(EnumConstants.PRECASADA))
					|| responseConsultOffertsVO.getIdnEstadoEntidad() == Integer
							.parseInt(paramProperties.get(EnumConstants.CASADA))) {
				estado = false;
				break;
			}
		}
		return estado;
	}

	/**
	 * Eliminar productos
	 * 
	 * @param idnProducts Lista de identificadores del producto
	 * @param audUsuario  Usuario de auditoria
	 */
	@Override
	@Transactional
	public List<RequestProductoVO> deleteProduct(List<Integer> idnProducts) {

		
		log.info("Inicio del método deleteProduct :: parámetros [{}] ", idnProducts);
		

		// this.validateAcessFilter();

		Boolean ejecutaBaja = true;

		List<RequestProductoVO> responseDeleteProductsVO = new ArrayList<RequestProductoVO>();

		Map<Optional<Producto>, List<ResponseConsultOffertsVO>> map = new HashMap<>();

		for (Integer idProducto : idnProducts) {
			Optional<Producto> producto = this.productoRepository.findById(idProducto);
			LocalDateTime fecFinSesion = producto.orElse(new Producto()).getFecFinSesion();
			LocalDateTime fechaActual = LocalDateTime.now();

			if (fecFinSesion.isBefore(fechaActual)) {
				throw new BusinessException(AppErrorCode.BUSI_008,
						String.format(AppErrorCode.BUSI_008.getReasonPhrase()));
			} else {

				if (!validProductLinkOffer(idProducto)) {
					ejecutaBaja = false;
				}
			}
			if(!responseConsultOffertsVOS.isEmpty())
				map.put(producto, responseConsultOffertsVOS);
		}

		if (ejecutaBaja) {
			for (Integer idProducto : idnProducts) {

				RequestProductVO requestProductVO = new RequestProductVO();
				requestProductVO.setIdnProducto(idProducto);
				RequestProductoVO requestProductoVO = seteaProducto("delete", requestProductVO);
				responseDeleteProductsVO.add(requestProductoVO);

				this.updateProducto(requestProductoVO);
			}
		} else {
			
			List<Integer> estados = List.of(Integer.parseInt(paramProperties.get(EnumConstants.PROVISIONAL)),
					Integer.parseInt(paramProperties.get(EnumConstants.ENVIADA)),
					Integer.parseInt(paramProperties.get(EnumConstants.COMPATIBLE)),
					Integer.parseInt(paramProperties.get(EnumConstants.PRECASADA)),
					Integer.parseInt(paramProperties.get(EnumConstants.CASADA)));
			
			String listProducstError = "";
			for (Map.Entry<Optional<Producto>, List<ResponseConsultOffertsVO>> product : map.entrySet()) {

				listProducstError = listProducstError.concat("Producto ");
				listProducstError = listProducstError.concat(product.getKey().orElse(new Producto()).getCodProducto());
				listProducstError = listProducstError.concat(" (");

				List<ResponseConsultOffertsVO> responseConsultOffertsVOS = product.getValue();

				listProducstError = listProducstError.concat(
						responseConsultOffertsVOS.stream().filter(r -> estados.contains(r.getIdnEstadoEntidad()))
								.map(ResponseConsultOffertsVO::getCodOferta).collect(Collectors.joining(", ")));

				listProducstError = listProducstError.concat("), ");

			}

			listProducstError = listProducstError.substring(0, listProducstError.length() - 2);

			throw new BusinessException(AppErrorCode.BUSI_009, String.format(listProducstError));

		}

		
		log.info("Fin del método addProduct :: salida [{}] ", responseDeleteProductsVO);
		

		return responseDeleteProductsVO;
	}

	/**
	 * Listado de productos
	 * 
	 * @param requestFiltrosProductoVO modelo de filtros de busqueda
	 * @return lista de objetos ResponseProductoVO
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ResponseProductoVO> getProducts(RequestFiltrosProductoVO requestFiltrosProductoVO) {

		
		log.info("Inicio del método getProducts :: parámetros [{}] ", requestFiltrosProductoVO);
		

		List<Producto> products = new ArrayList<>();

		if (requestFiltrosProductoVO.getIdnServAtr() != null && !requestFiltrosProductoVO.getIdnServAtr().isEmpty()) {

			List<Integer> cisternas = new ArrayList<>();
			List<Integer> buques = new ArrayList<>();

			requestFiltrosProductoVO.getIdnServAtr().stream().forEach(s -> {
				if (this.operacionesAuxiliaresExternalRestService.isServiceSlot(List.of(s)))
					buques.add(s);
				else
					cisternas.add(s);
			});

			if (!cisternas.isEmpty()) {
				requestFiltrosProductoVO.setIdnServAtr(cisternas);
				products.addAll(this.productoRepository.getProducts(em, requestFiltrosProductoVO));
			}
			if (!buques.isEmpty()) {
				requestFiltrosProductoVO.setIdnServAtr(buques);
				requestFiltrosProductoVO.setIdnAgrupInfr(null);
				requestFiltrosProductoVO.setIdnInfr(null);
				requestFiltrosProductoVO.setIdnPuntoConex(null);
				products.addAll(this.productoRepository.getProducts(em, requestFiltrosProductoVO));
			}
		} else {
			products.addAll(this.productoRepository.getProducts(em, requestFiltrosProductoVO));
		}

		List<ResponseProductoVO> responseProductoVOS = this.productoMapperService.convertToVO(products);
		if (responseProductoVOS.isEmpty()) {
			throw new NoSuchElementException(ENTITY_PRODUCTO);
		}

		List<Integer> idnInfr = products.stream().filter(p -> p.getIdnInfr() != null).map(Producto::getIdnInfr)
				.collect(Collectors.toList());
		List<Integer> idnAgrupInfr = products.stream().filter(p -> p.getIdnAgrupInfr() != null)
				.map(Producto::getIdnAgrupInfr).collect(Collectors.toList());
		List<Integer> idnPuntoConex = products.stream().filter(p -> p.getIdnPuntoConex() != null)
				.map(Producto::getIdnPuntoConex).collect(Collectors.toList());

		RequestProductVO requestProductVO = new RequestProductVO();
		requestProductVO
				.setIdnInfr(idnInfr.isEmpty() ? null : idnInfr.stream().distinct().collect(Collectors.toList()));
		requestProductVO.setIdnAgrupInfr(
				idnAgrupInfr.isEmpty() ? null : idnAgrupInfr.stream().distinct().collect(Collectors.toList()));
		requestProductVO.setIdnPuntoConex(
				idnPuntoConex.isEmpty() ? null : idnPuntoConex.stream().distinct().collect(Collectors.toList()));

		List<ResponseConsultarAtribVigVO> responseInstalaciones = preparaRequestInstalaciones(requestProductVO);

		Map<Integer, String> mapInfr = (idnInfr.isEmpty() ? new HashMap<>()
				: responseInstalaciones.stream().collect(Collectors.toMap(ResponseConsultarAtribVigVO::getIdInfr,
						ResponseConsultarAtribVigVO::getDesInstalacion)));
		Map<Integer, String> mapAgrupInfr = (idnAgrupInfr.isEmpty() ? new HashMap<>()
				: responseInstalaciones.stream().collect(Collectors.toMap(ResponseConsultarAtribVigVO::getIdAgrupInfr,
						ResponseConsultarAtribVigVO::getDesInstalacion)));
		Map<Integer, String> mapPuntoCOnex = (idnPuntoConex.isEmpty() ? new HashMap<>()
				: responseInstalaciones.stream().collect(Collectors.toMap(ResponseConsultarAtribVigVO::getIdPuntoConex,
						ResponseConsultarAtribVigVO::getDesInstalacion)));

		responseProductoVOS.stream().forEach(r -> {
			if (Objects.nonNull(r.getIdnInfr()))
				r.setDesInstalacion(mapInfr.get(r.getIdnInfr()));
			if (Objects.nonNull(r.getIdnAgrupInfr()))
				r.setDesInstalacion(mapAgrupInfr.get(r.getIdnAgrupInfr()));
			if (Objects.nonNull(r.getIdnPuntoConex()))
				r.setDesInstalacion(mapPuntoCOnex.get(r.getIdnPuntoConex()));
		});

		// Carga los ids de los productos en una lista para obtener sus unidades de
		// medida
		List<Integer> idsUnidadesMedida = products.stream().map(product -> product.getIdnUnidadMedida())
				.collect(Collectors.toList());
		// Carga los ids de productos en una lista para obtener sus unidades de precio
		List<Integer> idsUnidadesPrecio = products.stream().map(product -> product.getIdnUnidadPrecio())
				.collect(Collectors.toList());
		// Mapear las unidades en el producto
		externalUnidadMedidaDeProducto(responseProductoVOS, idsUnidadesMedida, idsUnidadesPrecio);

		responseProductoVOS.stream().forEach(r -> {
			r.setAudFecAlta(r.getAudFecAlta().toLocalDate().atStartOfDay());
		});
		
		responseProductoVOS.sort(Comparator.comparing(ResponseProductoVO::getFecIniProducto)
				.thenComparing(ResponseProductoVO::getAudFecAlta)
				.thenComparing(ResponseProductoVO::getDesInstalacion, Comparator.nullsFirst(Comparator.naturalOrder())));

		if (log.isDebugEnabled()) {
			log.info("Fin del método getProducts :: salida [{}] ", responseProductoVOS);
		}

		return responseProductoVOS;
	}

	private RequestProductoVO seteaProducto(String typeOperation, RequestProductVO requestProductVO) {
		Optional<Producto> producto = this.productoRepository.findById(requestProductVO.getIdnProducto());

		RequestProductoVO requestProductoVO = new RequestProductoVO();
		requestProductoVO.setIdnProducto(requestProductVO.getIdnProducto());
		requestProductoVO.setCodProducto(producto.orElse(new Producto()).getCodProducto());
		requestProductoVO.setIdnTipoProducto(producto.orElse(new Producto()).getIdnTipoProducto());
		requestProductoVO.setIdnServAtr(producto.orElse(new Producto()).getIdnServAtr());
		requestProductoVO.setFecIniProducto(producto.orElse(new Producto()).getFecIniProducto());
		requestProductoVO.setFecFinProducto(producto.orElse(new Producto()).getFecFinProducto());
		requestProductoVO.setNumVersion(new BigDecimal(producto.orElse(new Producto()).getNumVersion()));
		requestProductoVO.setFecVersion(producto.orElse(new Producto()).getFecVersion());
		requestProductoVO.setAudUsuAlta(producto.orElse(new Producto()).getAudUsuAlta());
		requestProductoVO.setAudFecAlta(producto.orElse(new Producto()).getAudFecAlta());
		requestProductoVO.setAudUsuModi(requestProductVO.getAudUsuario());
		requestProductoVO.setAudFecModi(LocalDateTime.now());

		if (typeOperation.equals("update")) {
			requestProductoVO.setFecIniSesion(requestProductVO.getFecIniSesion());
			requestProductoVO.setFecFinSesion(requestProductVO.getFecFinSesion());
		} else {
			requestProductoVO.setFecIniSesion(producto.orElse(new Producto()).getFecIniSesion());
			requestProductoVO.setFecFinSesion(producto.orElse(new Producto()).getFecFinSesion());
			requestProductoVO.setAudFecBaja(LocalDateTime.now());
		}

		return requestProductoVO;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List<ResponseProductoSessionVO> getProductsBySession(String period) {
		log.info("Empieza método getProductsBySession :: Parámetros[{}]", period);
		try {
			YearMonth.parse(period, DateTimeFormatter.ofPattern("MMyyyy"));
		} catch (DateTimeException ex) {
			throw new TechnicalErrorException(ArchErrorCode.TECH_036,
					ex.getMessage().concat(". The correct format must be MMyyyy"), ex);
		}
		List<Producto> productos = this.productoRepository.getProductsBySession(period, this.em);
		if (Objects.isNull(productos) || productos.isEmpty()) {
			throw new NoSuchElementException(ENTITY_PRODUCTO);
		}
		List<ResponseProductoSessionVO> responseProductoSessionVOs = this.productoMapperService
				.convertToProductoSessionVOs(productos);
		log.info("Termina método getProductsBySession :: Parámetros[{}]", responseProductoSessionVOs);
		return responseProductoSessionVOs;
	}

	/**
	 * A partir de un listado de búsquedas de Productos, las ejecuta una a una
	 * organizando el resultado de manera secuencial e independiente.
	 * 
	 * <p>
	 * Cada búsqueda consta de un identificador y un filtro de búsqueda. El
	 * identificador se empleará para asociar después el resultado a la misma
	 * búsqueda a la que pertenece.
	 * 
	 * @param filters Lista de varias búsquedas, cada una con su identificador y un
	 *                filtro de búsqueda
	 * @return Listado de resultados, segregados por los mismos identificadores de
	 *         las búsquedas
	 */
	@Override
	public ResponseMultipleFilterProductoVO findMultipleProducto(RequestMultipleFilterProductoVO filters) {

		List<ResponseIdentifiedFilterProductoVO> identifiedResponses = filters.getItems().stream().map(item -> {
			List<ResponseProductoVO> obtieneProductos = new ArrayList<>();
			try {
				obtieneProductos = obtieneProductos(item.getFilter());
			} catch (Exception e) {
				// Error controlado (no hay resultados)
			}
			return new ResponseIdentifiedFilterProductoVO(item.getIdnFilter(), obtieneProductos);
		}).toList();

		return new ResponseMultipleFilterProductoVO(identifiedResponses);
	}

	private void validateAcessFilter() {
		log.info("Empieza método validarSUjeto :: Parámetros [{}] ");
		LocalDateTime fechaActual = LocalDateTime.now(ZoneId.systemDefault());
		RequestValidarAccesoVO requestValidarAccesoVO = new RequestValidarAccesoVO();
		requestValidarAccesoVO.setIdSujeto(1);// Obtener desde la cabecera
		requestValidarAccesoVO.setFechaInicio(fechaActual);
		requestValidarAccesoVO.setFechaFin(fechaActual);
		Integer responseValidarAccesoVO = Optional
				.ofNullable(this.sujetoExternalRestService.accessValidateEnabling(requestValidarAccesoVO)).orElse(0);
		if (responseValidarAccesoVO == 0) {
			throw new BusinessException(AppErrorCode.BUSI_017, AppErrorCode.BUSI_017.getReasonPhrase());
		} else if (responseValidarAccesoVO == 1) {
			throw new BusinessException(AppErrorCode.BUSI_018, AppErrorCode.BUSI_018.getReasonPhrase());
		}
	}

	@Override
	public List<ResponseProductoVO> getProductsFecSession(RequestFiltrosProductoVO requestFiltrosProductoVO) {
		
		log.info("Inicio del método getProductsFecSession :: parámetros [{}] ", requestFiltrosProductoVO);
		List<Producto> products = new ArrayList<>();
		
		products.addAll(this.productoRepository.getProductsSession(em, requestFiltrosProductoVO));
		
		List<ResponseProductoVO> responseProductoVOS = this.productoMapperService.convertToVO(products);
		if (responseProductoVOS.isEmpty()) {
			throw new NoSuchElementException(ENTITY_PRODUCTO);
		}
		
		List<Integer> idnInfr = products.stream().filter(p -> p.getIdnInfr() != null).map(Producto::getIdnInfr)
				.collect(Collectors.toList());
		List<Integer> idnAgrupInfr = products.stream().filter(p -> p.getIdnAgrupInfr() != null)
				.map(Producto::getIdnAgrupInfr).collect(Collectors.toList());
		List<Integer> idnPuntoConex = products.stream().filter(p -> p.getIdnPuntoConex() != null)
				.map(Producto::getIdnPuntoConex).collect(Collectors.toList());

		RequestProductVO requestProductVO = new RequestProductVO();
		requestProductVO
				.setIdnInfr(idnInfr.isEmpty() ? null : idnInfr.stream().distinct().collect(Collectors.toList()));
		requestProductVO.setIdnAgrupInfr(
				idnAgrupInfr.isEmpty() ? null : idnAgrupInfr.stream().distinct().collect(Collectors.toList()));
		requestProductVO.setIdnPuntoConex(
				idnPuntoConex.isEmpty() ? null : idnPuntoConex.stream().distinct().collect(Collectors.toList()));

		List<ResponseConsultarAtribVigVO> responseInstalaciones = preparaRequestInstalaciones(requestProductVO);

		Map<Integer, String> mapInfr = (idnInfr.isEmpty() ? new HashMap<>()
				: responseInstalaciones.stream().collect(Collectors.toMap(ResponseConsultarAtribVigVO::getIdInfr,
						ResponseConsultarAtribVigVO::getDesInstalacion)));
		Map<Integer, String> mapAgrupInfr = (idnAgrupInfr.isEmpty() ? new HashMap<>()
				: responseInstalaciones.stream().collect(Collectors.toMap(ResponseConsultarAtribVigVO::getIdAgrupInfr,
						ResponseConsultarAtribVigVO::getDesInstalacion)));
		Map<Integer, String> mapPuntoCOnex = (idnPuntoConex.isEmpty() ? new HashMap<>()
				: responseInstalaciones.stream().collect(Collectors.toMap(ResponseConsultarAtribVigVO::getIdPuntoConex,
						ResponseConsultarAtribVigVO::getDesInstalacion)));

		responseProductoVOS.stream().forEach(r -> {
			if (Objects.nonNull(r.getIdnInfr()))
				r.setDesInstalacion(mapInfr.get(r.getIdnInfr()));
			if (Objects.nonNull(r.getIdnAgrupInfr()))
				r.setDesInstalacion(mapAgrupInfr.get(r.getIdnAgrupInfr()));
			if (Objects.nonNull(r.getIdnPuntoConex()))
				r.setDesInstalacion(mapPuntoCOnex.get(r.getIdnPuntoConex()));
		});

		// Carga los ids de los productos en una lista para obtener sus unidades de
		// medida
		List<Integer> idsUnidadesMedida = products.stream().map(product -> product.getIdnUnidadMedida())
				.collect(Collectors.toList());
		// Carga los ids de productos en una lista para obtener sus unidades de precio
		List<Integer> idsUnidadesPrecio = products.stream().map(product -> product.getIdnUnidadPrecio())
				.collect(Collectors.toList());
		// Mapear las unidades en el producto
		externalUnidadMedidaDeProducto(responseProductoVOS, idsUnidadesMedida, idsUnidadesPrecio);

		responseProductoVOS.stream().forEach(r -> {
			r.setAudFecAlta(r.getAudFecAlta().toLocalDate().atStartOfDay());
		});
		
		responseProductoVOS.sort(Comparator.comparing(ResponseProductoVO::getFecIniProducto)
				.thenComparing(ResponseProductoVO::getAudFecAlta)
				.thenComparing(ResponseProductoVO::getDesInstalacion, Comparator.nullsFirst(Comparator.naturalOrder())));

		if (log.isDebugEnabled()) {
			log.info("Fin del método getProductsFecSession :: salida [{}] ", responseProductoVOS);
		}

		return responseProductoVOS;
		
	}
}
