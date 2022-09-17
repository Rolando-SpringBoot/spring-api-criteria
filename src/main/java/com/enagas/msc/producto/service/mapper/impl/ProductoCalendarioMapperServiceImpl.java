package com.enagas.msc.producto.service.mapper.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.enagas.msc.producto.domain.ProductoCalendario;
import com.enagas.msc.producto.domain.vo.RequestProductoCalendarioVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoCalendarioVO;
import com.enagas.msc.producto.service.mapper.ProductoCalendarioMapperService;

/**
 * Service that implements the ProductoCalendarioMapperService interface.
 */
@Service
public class ProductoCalendarioMapperServiceImpl implements ProductoCalendarioMapperService {

	/** The ModelMapper instance. */
	private static final ModelMapper modelMapper = new ModelMapper();

	/**
	 * Converts a list of ProductoCalendarios to a list of ResponseMscTproductoCalendario.
	 *
	 * @param productoCalendario
	 *            the ProductoCalendario entity
	 * @return a list of ResponseProductoCalendarioVO instances
	 */
	public List<ResponseProductoCalendarioVO> convertToVO(List<ProductoCalendario> productoCalendario) {
		return modelMapper.map(productoCalendario, new TypeToken<List<ResponseProductoCalendarioVO>>() {
		}.getType());
	}
	

	/**
	 * Converts a ProductoCalendario to a ResponseProductoCalendarioVO.
	 *
	 * @param productoCalendario
	 *            the ProductoCalendario entity
	 * @return a ResponseProductoCalendarioVO instance
	 */
	public ResponseProductoCalendarioVO convertToVO(ProductoCalendario productoCalendario) {
		return modelMapper.map(productoCalendario, ResponseProductoCalendarioVO.class);
	}
	
	
	/**
	 * Converts a RequestProductoCalendarioVO to a ProductoCalendario entity.
	 *
	 * @param requestProductoCalendarioVO
	 *            the request ProductoCalendario VO
	 * @return a ProductoCalendario instance
	 */
	public ProductoCalendario convertToEntity(RequestProductoCalendarioVO requestProductoCalendarioVO) {
		return modelMapper.map(requestProductoCalendarioVO, ProductoCalendario.class);
	}

}