package com.enagas.msc.producto.external.vo;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class RequestConsultOffertsVO {
	
	/**MICRO PRODUCTO*/
	
	private List<Integer> idnListaProducto;	
	
	private String codProducto;
	
	private Integer idnTipoProducto;
	
	private Integer idnServAtr;
	
	private List<Integer> idnAgrupInfr;
	
	private List<Integer> idnInfr;
	
	private List<Integer> idnPuntoConex;
	
	/**MICRO OFERTA*/
	private String codOferta;
	
	private Boolean indTipoOferta;
	
	private Integer idnEstadoEntidad;
	
	private Integer idnSujeto;
	
	private String codCto;
	
	private Boolean indAllNone = false; 
	
	/**MICRO PRODUCTO*/
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fecIniProducto;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fecFinProducto; 
	
	/**MICRO OFERTA*/
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fecIniVencimiento;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fecFinVencimiento; 

}
