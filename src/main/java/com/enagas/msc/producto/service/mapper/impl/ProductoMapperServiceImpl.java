package com.enagas.msc.producto.service.mapper.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.enagas.msc.producto.domain.Producto;
import com.enagas.msc.producto.domain.ProductoDeOferta;
import com.enagas.msc.producto.domain.vo.RequestFiltrarPorPeriodoVO;
import com.enagas.msc.producto.domain.vo.RequestFiltrosObtieneProductoVO;
import com.enagas.msc.producto.domain.vo.RequestProductoVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoSessionVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoVO;
import com.enagas.msc.producto.service.mapper.ProductoMapperService;
import com.enagas.msc.producto.service.vo.RequestFiltrosProductoVO;
import com.enagas.msc.producto.service.vo.RequestServicioFilterVO;

/**
 * Service that implements the ProductoMapperService interface.
 */
@Service
public class ProductoMapperServiceImpl implements ProductoMapperService {

	/** The ModelMapper instance. */
	private static final ModelMapper modelMapper = setModelMapperInitConfig();

	/** The initial configuration */
	private static ModelMapper setModelMapperInitConfig() {
		ModelMapper modelMapper = new ModelMapper();

		Converter<LocalDateTime, Date> localDateTimeConverter = new AbstractConverter<LocalDateTime, Date>() {
			@Override
			protected Date convert(LocalDateTime localDateTime) {
				return Objects.isNull(localDateTime) ? null : java.sql.Timestamp.valueOf(localDateTime);
			}
		};
		modelMapper.addConverter(localDateTimeConverter);

		return modelMapper;
	}

	/**
	 * Converts a list of Productos to a list of ResponseProducto.
	 *
	 * @param producto the Producto entity
	 * @return a list of ResponseProductoVO instances
	 */
	public List<ResponseProductoVO> convertToVO(List<Producto> producto) {
		return modelMapper.map(producto, new TypeToken<List<ResponseProductoVO>>() {
		}.getType());
	}

	public List<ResponseProductoVO> convertToVOs(List<ProductoDeOferta> productoDeOferta) {
		return modelMapper.map(productoDeOferta, new TypeToken<List<ResponseProductoVO>>() {
		}.getType());
	}

	/**
	 * Converts a Producto to a ResponseProductoVO.
	 *
	 * @param producto the Producto entity
	 * @return a ResponseProductoVO instance
	 */
	public ResponseProductoVO convertToVO(Producto producto) {
		return modelMapper.map(producto, ResponseProductoVO.class);
	}

	/**
	 * Converts a RequestProductoVO to a Producto entity.
	 *
	 * @param requestProductoVO the request Producto VO
	 * @return a Producto instance
	 */
	public Producto convertToEntity(RequestProductoVO requestProductoVO) {
		return modelMapper.map(requestProductoVO, Producto.class);
	}

	/**
	 * Converts a RequestProductoServicioFilterVO to a RequestServicioFilterVO.
	 * 
	 * @param requestProductoServicioFilterVO the request object
	 * @return a RequestServicioFilterVO instance
	 */
	public RequestServicioFilterVO convertToVO(RequestFiltrarPorPeriodoVO requestProductoServicioFilterVO) {
		return modelMapper.map(requestProductoServicioFilterVO, RequestServicioFilterVO.class);
	}

	/**
	 * Convierte de RequestDFiltrosProductoVO a RequestFiltrosProductoVO
	 * 
	 * La diferencia está en la conversión de LocalDateTime a Date para que
	 * Hibernate puede asimilar las fechas.
	 */

	@Override
	public RequestFiltrosProductoVO convertToVO(RequestFiltrosObtieneProductoVO requestFiltrosProductoVO) {
		return modelMapper.map(requestFiltrosProductoVO, RequestFiltrosProductoVO.class);
	}

	@Override
	public List<ResponseProductoSessionVO> convertToProductoSessionVOs(List<Producto> productos) {
		return modelMapper.map(productos, new TypeToken<List<ResponseProductoSessionVO>>() {
		}.getType());
	}

}