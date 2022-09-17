package com.enagas.msc.producto.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enagas.msc.producto.domain.ProductoCalendario;
import com.enagas.msc.producto.domain.vo.RequestProductoCalendarioVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoCalendarioVO;
import com.enagas.msc.producto.repository.ProductoCalendarioRepository;
import com.enagas.msc.producto.service.ProductoCalendarioService;
import com.enagas.msc.producto.service.mapper.ProductoCalendarioMapperService;

/**
 * Service that implements the MscTproductoCalendarioService interface.
 */
@Service
public class ProductoCalendarioServiceImpl implements ProductoCalendarioService {

	/** Logger. */
	private static final Logger log = LoggerFactory.getLogger(ProductoCalendarioServiceImpl.class);

	/** The ProductoCalendario repository. */
	private ProductoCalendarioRepository productoCalendarioRepository;
	
	/** The ProductoCalendario mapper service. */
    private ProductoCalendarioMapperService productoCalendarioMapperService;

	/** The Constant ENTITY. */
	private static final String ENTITY = "ProductoCalendario";

	/**
	 * Instantiates a ProductoCalendarioServiceImpl.
	 *
	 * @param productoCalendarioRepository - the ProductoCalendario repository
	 * @param productoCalendarioMapperService - the ProductoCalendario mapper service
	 */
	public ProductoCalendarioServiceImpl(ProductoCalendarioRepository productoCalendarioRepository, ProductoCalendarioMapperService productoCalendarioMapperService) {
		this.productoCalendarioRepository = productoCalendarioRepository;
		this.productoCalendarioMapperService = productoCalendarioMapperService;
	}

	/**
	 * Gets all productoCalendarios.
	 *
	 * @param pageable
	 *            the pageable
	 * @return a list of ResponseProductoCalendarioVO instances
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ResponseProductoCalendarioVO> getProductoCalendarios(Pageable pageable) {
		
		
		log.info("Método getProductoCalendarios para la consulta de todos los elementos");
		
	
		List<ProductoCalendario> productoCalendarios = productoCalendarioRepository.findAll(pageable).getContent();
		
		
		log.info("La consulta ha devuelto {} registros", productoCalendarios.size());
		
		
		return productoCalendarioMapperService.convertToVO(productoCalendarios);
	}

	/**
	 * Gets a productoCalendario for given id.
	 *
	 * @param id
	 *            the id of the productoCalendario
	 * @return a ResponseProductoCalendarioVO instance
	 */
	@Override
	@Transactional(readOnly = true)
	public ResponseProductoCalendarioVO getProductoCalendario(Integer id) {
	
		
		log.info("Método getProductoCalendario para la consulta por id {}", id);
		
	
		Optional<ProductoCalendario> productoCalendario = productoCalendarioRepository.findById(id);
		if(!productoCalendario.isPresent()) {
			throw new NoSuchElementException(ENTITY);
		}
		
		
		log.info("La consulta ha devuelto los resultados: {}", productoCalendario);
		
		
		return productoCalendarioMapperService.convertToVO(productoCalendario.get());
	}

	/**
	 * Adds a productoCalendario on database.
	 *
	 * @param RequestProductoCalendarioVO
	 *            the RequestProductoCalendarioVO
	 * @return a ResponseProductoCalendarioVO instance
	 */
	@Override
	@Transactional
	public ResponseProductoCalendarioVO saveProductoCalendario(RequestProductoCalendarioVO requestProductoCalendarioVO) {
	
		
		log.info("Método saveProductoCalendario para guardar los parámetros [{}]", requestProductoCalendarioVO);
		

		ProductoCalendario productoCalendario = productoCalendarioMapperService.convertToEntity(requestProductoCalendarioVO);
		productoCalendario = productoCalendarioRepository.saveAndFlush(productoCalendario);
		
		
		log.info("Se ha creado el registro: {}", productoCalendario);
		
		
		return productoCalendarioMapperService.convertToVO(productoCalendario);
	}
	
	/**
	 * Updates a productoCalendario on database.
	 *
	 * @param id
	 *            the id of the productoCalendario
	 * @param RequestProductoCalendarioVO
	 *            the RequestProductoCalendarioVO
	 */
	@Override
	@Transactional
	public void updateProductoCalendario(Integer id, RequestProductoCalendarioVO requestProductoCalendarioVO) {
	
		
		log.info("Método updateMscTproductoCalendario para guardar los parámetros [{},{}]", id, requestProductoCalendarioVO);
		
	
		if(!productoCalendarioRepository.findById(id).isPresent()) {
			throw new NoSuchElementException(ENTITY);		
		}
		ProductoCalendario productoCalendario = productoCalendarioMapperService.convertToEntity(requestProductoCalendarioVO);
		productoCalendario.setIdnProductoCalendario(id);
		productoCalendarioRepository.saveAndFlush(productoCalendario);
		
		
		log.info("Se ha modificado el registro: {}", productoCalendario);
		
	}

	/**
	 * Deletes a productoCalendario on database.
	 *
	 * @param id
	 *            the id of the productoCalendario
	 */
	@Override
	@Transactional
	public void deleteProductoCalendario(Integer id) {
	
		
		log.info("Método deleteProductoCalendario para eliminar el registro [{}]", id);
		
	
		if(!productoCalendarioRepository.findById(id).isPresent()) {
			throw new NoSuchElementException(ENTITY);			
		}
		productoCalendarioRepository.deleteById(id);
		
		
		log.info("Se ha borrado el registro con id: {}", id);
		
	}
	
}