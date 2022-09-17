package com.enagas.msc.producto.service.vo;


import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class RequestFiltrosProductoVO {

	
	 private String codProducto;	 
	 
	 private Integer idnTipoProducto;	 

	 private List<Integer> idnServAtr;
	 
	 private List<Integer> idnInfr;
	 
	 private List<Integer> idnAgrupInfr;
	 
	 private List<Integer> idnPuntoConex;


	 
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecIniSesion;
	 
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecFinSesion;
	 
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecIniProducto;
	 
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecFinProducto;


}
