package com.enagas.msc.producto.external.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseConsultOffertsVO {
	
	private Integer idnOferta;
	private String codProducto;
	private Integer idnTipoProducto; 
	private LocalDateTime fecIniProducto;
	private LocalDateTime fecFinProducto;
	private Integer idnServAtr; 
	private Integer idnInfr;
	private Integer idnAgrupInfr;
	private Integer idnPuntoConex;
	private Boolean indTipoOferta; 
	private String codOferta; 
	private Integer idnSujeto; 
	private Integer idnEstadoEntidad;
	private String codCto; 
	private java.math.BigDecimal valPrecio;
	private String unidadPrecio; 
	private Long valCapacidad; 
	private String unidadMedida; 
	private Boolean indAllNone; 
	private LocalDateTime fecVencimiento; 
	private LocalDateTime fecCreacion;
	private LocalDateTime fecModificacion;

}
