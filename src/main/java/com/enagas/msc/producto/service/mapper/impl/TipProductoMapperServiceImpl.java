package com.enagas.msc.producto.service.mapper.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.enagas.msc.producto.domain.TipProducto;
import com.enagas.msc.producto.domain.vo.ResponseTipProductoVO;
import com.enagas.msc.producto.service.mapper.TipProductoMapperService;

/**
 * Service that implements the TipProductoMapperService interface.
 */
@Service
public class TipProductoMapperServiceImpl implements TipProductoMapperService {

	/** The ModelMapper instance. */
	private static final ModelMapper modelMapper = new ModelMapper();

	/**
	 * Converts a list of TipProductos to a list of ResponseTipProducto.
	 *
	 * @param tipProducto the MscTtipProducto entity
	 * @return a list of ResponseTipProductoVO instances
	 */
	public List<ResponseTipProductoVO> convertToVO(List<TipProducto> tipProducto) {
		return modelMapper.map(tipProducto, new TypeToken<List<ResponseTipProductoVO>>() {
		}.getType());
	}

	/**
	 * Converts a TipProducto to a ResponseTipProductoVO.
	 *
	 * @param tipProducto the TipProducto entity
	 * @return a ResponseTipProductoVO instance
	 */
	public ResponseTipProductoVO convertToVO(TipProducto tipProducto) {
		return modelMapper.map(tipProducto, ResponseTipProductoVO.class);
	}

}