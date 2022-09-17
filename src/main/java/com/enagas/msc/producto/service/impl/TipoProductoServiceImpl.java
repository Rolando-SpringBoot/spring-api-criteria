package com.enagas.msc.producto.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enagas.msc.producto.domain.Producto;
import com.enagas.msc.producto.domain.TipProducto;
import com.enagas.msc.producto.domain.vo.RequestFiltrosObtieneProductoVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoVO;
import com.enagas.msc.producto.domain.vo.ResponseTipProductoVO;
import com.enagas.msc.producto.external.service.OperacionesAuxiliaresExternalRestService;
import com.enagas.msc.producto.repository.ProductoRepository;
import com.enagas.msc.producto.repository.TipoproductoRepository;
import com.enagas.msc.producto.service.TipoProductoService;
import com.enagas.msc.producto.service.mapper.ProductoMapperService;
import com.enagas.msc.producto.service.mapper.TipProductoMapperService;

import lombok.extern.slf4j.Slf4j;

/**
 * Service that implements the TipProductoService interface.
 */
@Slf4j
@Service
public class TipoProductoServiceImpl implements TipoProductoService {

	/** The entitymanager. */
	@PersistenceContext
	private EntityManager entityManager;

	/** The tipoProductoRepository repository. */
	private TipoproductoRepository tipoProductoRepository;

	/** The TipProducto mapper service. */
	private TipProductoMapperService tipProductoMapperService;
	
	private ProductoMapperService productoMapperService;

	private OperacionesAuxiliaresExternalRestService operacionesAuxiliaresExternalRestService;

	private ProductoRepository productoRepository;

	/**
	 * Instantiates a TipProductoServiceImpl.
	 *
	 * @param tipProductoRepository    - the TipProducto repository
	 * @param tipProductoMapperService - the TipProducto mapper service
	 */
	public TipoProductoServiceImpl(TipoproductoRepository tipoProductoRepository,
			TipProductoMapperService tipProductoMapperService,
			OperacionesAuxiliaresExternalRestService operacionesAuxiliaresExternalRestService,
			ProductoRepository productoRepository, ProductoMapperService productoMapperService) {
		this.tipoProductoRepository = tipoProductoRepository;
		this.tipProductoMapperService = tipProductoMapperService;
		this.operacionesAuxiliaresExternalRestService = operacionesAuxiliaresExternalRestService;
		this.productoRepository = productoRepository;
		this.productoMapperService = productoMapperService;
	}

	/**
	 * Gets all tipProductos.
	 *
	 * @param idTipoProductoList a list of TipProduct ids
	 * @param sort               the sort class
	 * @return a list of ResponseTipProductoVO instances
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
	@Override
	public List<ResponseTipProductoVO> obtenerTipoProducto(List<Integer> idTipoProductoList,
			List<Integer> idServAtr, Sort sort) {

		log.info("Método getTipProductos para la consulta de los tipos de producto :: Parametros [{}]", sort);

		List<TipProducto> tipProductos = new ArrayList<>();

		if(idServAtr.isEmpty()) {
			tipProductos = Optional
					.ofNullable(this.tipoProductoRepository.obtenerTipoProducto(idTipoProductoList,
							// Buscando que el objeto Sort ordene por las columnas correctas
							sort.stream()
									.filter(o -> Arrays.stream(ResponseTipProductoVO.class.getDeclaredFields())
											.anyMatch(f -> f.getName().equals(o.getProperty())))
									.findAny().isPresent() ? sort : Sort.unsorted(),
							entityManager))
					.orElseGet(() -> Collections.emptyList());
		}
		else {
			List<Integer> idenTipProductos = new ArrayList<>();

			if(isServiceSlot(idServAtr)) {
				Integer idTipProducto = tipoProductoRepository.findByTxtAbrvProducto("M").getIdnTipoProducto();
				idenTipProductos.add(idTipProducto);
			}
			else {
				List<Integer> idnTipProducto = this.operacionesAuxiliaresExternalRestService.getTypeProductAssociatedServices(idServAtr);
				idenTipProductos.addAll(idnTipProducto);
			}
			
			idenTipProductos = idenTipProductos.stream().distinct().collect(Collectors.toList());
			tipProductos = tipoProductoRepository.obtenerTipoProducto(idenTipProductos, sort, entityManager);
		}

		log.info("La consulta ha devuelto {} registros", tipProductos.size());

		return this.tipProductoMapperService.convertToVO(tipProductos);
	}

	public Boolean isServiceSlot(List<Integer> idnServAtr) {
		return operacionesAuxiliaresExternalRestService.isServiceSlot(idnServAtr);
	}

	/**
	 * Obtener tipo de producto, operación que permite recuperar un tipo de producto
	 * a partir de un identificador.
	 * 
	 * @param id - el identificador del tipo de Producto
	 * @return una instancia ResponseTipProductoVO
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
	@Override
	public ResponseTipProductoVO getTipoProducto(Integer id) {
		log.info("Método getTipoProducto para obtener un Tipo de Producto por id {}", id);
		Optional<TipProducto> tipoProducto = this.tipoProductoRepository.findById(id);
		ResponseTipProductoVO objetoRespuesta = new ResponseTipProductoVO();
		if (tipoProducto.isPresent()) {
			objetoRespuesta = this.tipProductoMapperService.convertToVO(tipoProducto.get());
		}
		log.info("La consulta ha devuelto los resultados: {}", tipoProducto);
		return objetoRespuesta;
	}

}