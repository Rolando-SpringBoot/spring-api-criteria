package com.enagas.msc.producto.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enagas.msc.producto.domain.ProductoVer;
import com.enagas.msc.producto.domain.vo.RequestProductoVerVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoVerVO;
import com.enagas.msc.producto.repository.ProductoVerRepository;
import com.enagas.msc.producto.service.ProductoVerService;
import com.enagas.msc.producto.service.mapper.ProductoVerMapperService;

/**
 * Service that implements the ProductoVerService interface.
 */
@Service
public class ProductoVerServiceImpl implements ProductoVerService {

	/** Logger. */
	private static final Logger log = LoggerFactory.getLogger(ProductoVerServiceImpl.class);

	/** The ProductoVer repository. */
	private ProductoVerRepository productoVerRepository;
	
	/** The ProductoVer mapper service. */
    private ProductoVerMapperService productoVerMapperService;

	/** The Constant ENTITY. */
	private static final String ENTITY = "ProductoVer";

	/**
	 * Instantiates a ProductoVerServiceImpl.
	 *
	 * @param productoVerRepository - the ProductoVer repository
	 * @param productoVerMapperService - the ProductoVer mapper service
	 */
	public ProductoVerServiceImpl(ProductoVerRepository productoVerRepository, ProductoVerMapperService productoVerMapperService) {
		this.productoVerRepository = productoVerRepository;
		this.productoVerMapperService = productoVerMapperService;
	}

	/**
	 * Gets all productoVers.
	 *
	 * @param pageable
	 *            the pageable
	 * @return a list of ResponseProductoVerVO instances
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ResponseProductoVerVO> getProductoVers(Pageable pageable) {
		
		
		log.info("Método getProductoVers para la consulta de todos los elementos");
		
	
		List<ProductoVer> productoVers = productoVerRepository.findAll(pageable).getContent();
		
		
		log.info("La consulta ha devuelto {} registros", productoVers.size());
		
		
		return productoVerMapperService.convertToVO(productoVers);
	}

	/**
	 * Gets a productoVer for given id.
	 *
	 * @param id
	 *            the id of the productoVer
	 * @return a ResponseProductoVerVO instance
	 */
	@Override
	@Transactional(readOnly = true)
	public ResponseProductoVerVO getProductoVer(Integer id) {
	
		
		log.info("Método getMscTproductoVer para la consulta por id {}", id);
		
	
		Optional<ProductoVer> productoVer = productoVerRepository.findById(id);
		if(!productoVer.isPresent()) {
			throw new NoSuchElementException(ENTITY);
		}
		
		
		log.info("La consulta ha devuelto los resultados: {}", productoVer);
		
		
		return productoVerMapperService.convertToVO(productoVer.get());
	}

	/**
	 * Adds a productoVer on database.
	 *
	 * @param RequestProductoVerVO
	 *            the RequestProductoVerVO
	 * @return a ResponseProductoVerVO instance
	 */
	@Override
	@Transactional
	public ResponseProductoVerVO saveProductoVer(RequestProductoVerVO requestProductoVerVO) {
	
		
		log.info("Método saveProductoVer para guardar los parámetros [{}]", requestProductoVerVO);
		

		ProductoVer productoVer = productoVerMapperService.convertToEntity(requestProductoVerVO);
		productoVer = productoVerRepository.saveAndFlush(productoVer);
		
		
		log.info("Se ha creado el registro: {}", productoVer);
		
		
		return productoVerMapperService.convertToVO(productoVer);
	}
	
	/**
	 * Updates a productoVer on database.
	 *
	 * @param id
	 *            the id of the productoVer
	 * @param RequestProductoVerVO
	 *            the RequestProductoVerVO
	 */
	@Override
	@Transactional
	public void updateProductoVer(Integer id, RequestProductoVerVO requestProductoVerVO) {
	
		
		log.info("Método updateProductoVer para guardar los parámetros [{},{}]", id, requestProductoVerVO);
		
	
		if(!productoVerRepository.findById(id).isPresent()) {
			throw new NoSuchElementException(ENTITY);		
		}
		ProductoVer productoVer = productoVerMapperService.convertToEntity(requestProductoVerVO);
		productoVer.setIdnProductoVer(id);
		productoVerRepository.saveAndFlush(productoVer);
		
		
		log.info("Se ha modificado el registro: {}", productoVer);
		
	}

	/**
	 * Deletes a productoVer on database.
	 *
	 * @param id
	 *            the id of the productoVer
	 */
	@Override
	@Transactional
	public void deleteProductoVer(Integer id) {
	
		
		log.info("Método deleteProductoVer para eliminar el registro [{}]", id);
		
	
		if(!productoVerRepository.findById(id).isPresent()) {
			throw new NoSuchElementException(ENTITY);			
		}
		productoVerRepository.deleteById(id);
		
		
		log.info("Se ha borrado el registro con id: {}", id);
		
	}
	
}