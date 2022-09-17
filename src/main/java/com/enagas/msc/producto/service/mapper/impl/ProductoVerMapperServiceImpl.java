package com.enagas.msc.producto.service.mapper.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.enagas.msc.producto.domain.ProductoVer;
import com.enagas.msc.producto.domain.vo.RequestProductoVO;
import com.enagas.msc.producto.domain.vo.RequestProductoVerVO;
import com.enagas.msc.producto.domain.vo.ResponseProductoVerVO;
import com.enagas.msc.producto.service.mapper.ProductoVerMapperService;

/**
 * Service that implements the ProductoVerMapperService interface.
 */
@Service
public class ProductoVerMapperServiceImpl implements ProductoVerMapperService {

	/** The ModelMapper instance. */
	private static final ModelMapper modelMapper = new ModelMapper();

	/**
	 * Converts a list of ProductoVers to a list of ResponseProductoVer.
	 *
	 * @param productoVer
	 *            the ProductoVer entity
	 * @return a list of ResponseProductoVerVO instances
	 */
	public List<ResponseProductoVerVO> convertToVO(List<ProductoVer> productoVer) {
		return modelMapper.map(productoVer, new TypeToken<List<ResponseProductoVerVO>>() {
		}.getType());
	}
	

	/**
	 * Converts a ProductoVer to a ResponseProductoVerVO.
	 *
	 * @param productoVer
	 *            the ProductoVer entity
	 * @return a ResponseProductoVerVO instance
	 */
	public ResponseProductoVerVO convertToVO(ProductoVer productoVer) {
		return modelMapper.map(productoVer, ResponseProductoVerVO.class);
	}
	
	
	/**
	 * Converts a RequestProductoVerVO to a ProductoVer entity.
	 *
	 * @param requestProductoVerVO
	 *            the request ProductoVer VO
	 * @return a ProductoVer instance
	 */
	public ProductoVer convertToEntity(RequestProductoVerVO requestProductoVerVO) {
		return modelMapper.map(requestProductoVerVO, ProductoVer.class);
	}
	
	/**
	 * Rellenamos los datos necesarios para insertar en la entidad PRODUCTO_VER
	 * @author mdesantb
	 * @param productoVer, requestProductoVo
	 * 				the ProductoVer, RequestProductoVO
	 * @return ProductoVer
	 */
	public ProductoVer convertToEntity (ProductoVer productoVer, RequestProductoVO requestProductoVo) {
		
		ProductoVer salida = new ProductoVer();
		
		salida.setIdnAgrupInfr(productoVer.getIdnAgrupInfr());
		salida.setIdnInfr(productoVer.getIdnInfr());
		salida.setIdnProducto(productoVer.getIdnProducto());
		salida.setIdnPuntoConex(productoVer.getIdnPuntoConex());
		salida.setIdnTipoProducto(productoVer.getIdnTipoProducto());
		salida.setIdnServAtr(productoVer.getIdnServAtr());
		
//		salida.setMaxCapacidadCompra(BigDecimal.valueOf(requestProductoVo.getCapacidadMaxCompra()));
//		salida.setMinCapacidadVenta(BigDecimal.valueOf(requestProductoVo.getCapacidadMinVenta()));
//		salida.setMaxPrecioCompra(requestProductoVo.getPrecioMaxCompra());
//		salida.setMinPrecioVenta(requestProductoVo.getPrecioMinVenta());
		
		salida.setFecIniProducto(null!=requestProductoVo.getFecIniProducto()?requestProductoVo.getFecIniProducto():productoVer.getFecIniProducto());
		salida.setFecFinProducto(null!=requestProductoVo.getFecFinProducto()?requestProductoVo.getFecFinProducto():productoVer.getFecFinProducto());
		salida.setFecIniSesion(null!=requestProductoVo.getFecIniSesion()?requestProductoVo.getFecIniSesion():productoVer.getFecIniSesion());
		salida.setFecFinSesion(null!=requestProductoVo.getFecFinSesion()?requestProductoVo.getFecFinSesion():productoVer.getFecFinSesion());
		
		salida.setCodProducto(productoVer.getCodProducto());
		salida.setNumVersion(productoVer.getNumVersion()+1);
		
		return salida;
	}

}