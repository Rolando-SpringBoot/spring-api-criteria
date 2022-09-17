package com.enagas.msc.producto.domain;

import java.sql.Timestamp;
import java.util.List;

public interface ProductoDeOferta {

	Integer getIdnProducto();

	String getCodProducto();

	Integer getIdnTipoProducto();

	Integer getIdnServAtr();

	Integer getIdnInfr();

	Integer getIdnAgrupInfr();
	
	Integer getIdnUnidadMedida();

	Integer getIdnPuntoConex();

	Timestamp getFecIniProducto();

	Timestamp getFecFinProducto();

	Timestamp getFecIniSesion();

	Timestamp getFecFinSesion();

	Integer getNumVersion();

	Timestamp getFecVersion();

	String getAudUsuAlta();

	Timestamp getAudFecAlta();

	String getAudUsuModi();

	Timestamp getAudFecModi();

	Timestamp getAudFecBaja();

	List<ProductoVer> getProductoVer();
	
}